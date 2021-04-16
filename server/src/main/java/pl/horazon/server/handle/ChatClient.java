package pl.horazon.server.handle;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.*;
import pl.horazon.barrel.common.pojo.domain.GroupChatMsg;
import pl.horazon.barrel.common.pojo.system.EndConnectionBarrelMsg;
import pl.horazon.barrel.common.pojo.system.Init;
import pl.horazon.barrel.common.thread.CommInThread;
import pl.horazon.barrel.common.thread.CommOutThread;
import pl.horazon.server.SocketServer;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;

public class ChatClient {

    private final Socket socket;
    private final ObjectOutputStream out;
    private final ObjectInputStream in;
    private final CommInThread commInThread;
    private final CommOutThread commOutThread;

    public ChatClient(Socket socket) throws IOException {

        Logger.info("Hello World!");

        this.socket = socket;
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());

        commInThread = new CommInThread<BarrelMsg>(in, this::handle);
        commInThread.setEndSignal(EndConnectionBarrelMsg.class);

        commOutThread = new CommOutThread(out);

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("task-name-%d")
                .setDaemon(true)
                .build();

        factory.newThread(commInThread).start();
        factory.newThread(commOutThread).start();

        classToConsumerMap = new HashMap<>();
        classToConsumerMap.put(GroupChatMsg.class, o -> NewMsgHandler.handleNewMsg((GroupChatMsg) o));
        classToConsumerMap.put(Init.class, o -> handleInit((Init) o));
        classToConsumerMap.put(EndConnectionBarrelMsg.class, o -> handleEndCommunication((EndConnectionBarrelMsg) o));
    }

    private void handle(Object barrelMsg) {
        classToConsumerMap.get(barrelMsg.getClass()).accept(barrelMsg);
    }

    private void handleInit(Init msg){
        System.out.println("Received: Init " + msg.getLogin());
        SocketServer.users.add(msg.getLogin());
        send(SocketServer.getUsers());
    }

    private void handleEndCommunication(EndConnectionBarrelMsg msg){
        System.out.println("End conn");
        stopConnection();
    }

    private Map<Class, Consumer> classToConsumerMap;

    public void stopConnection() {

        try (
                Socket s = socket;
                InputStream i = in;
                OutputStream o = out;
        ) {
            send(new EndConnectionBarrelMsg());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void send(BarrelMsg barrelMsg) {
        commOutThread.queue.add(barrelMsg);
    }
}
