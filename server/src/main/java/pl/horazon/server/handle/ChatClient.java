package pl.horazon.server.handle;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.Init;
import pl.horazon.barrel.common.pojo.Msg;
import pl.horazon.barrel.common.pojo.TestMsg1;
import pl.horazon.barrel.common.pojo.TestMsg2;
import pl.horazon.server.SocketServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;


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

        commInThread = new CommInThread(this, in);
        commOutThread = new CommOutThread(out);

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("task-name-%d")
                .setDaemon(true)
                .build();

        factory.newThread(commInThread).start();
        factory.newThread(commOutThread).start();
    }

    public void send(Msg msg) {
        commOutThread.queue.add(msg);
    }
}

class CommInThread implements Runnable {

    private final ObjectInputStream in;
    private final ChatClient chatClient;

    CommInThread(ChatClient chatClient, ObjectInputStream in) {
        this.in = in;
        this.chatClient = chatClient;
    }

    @Override
    public void run() {
        try {

            Object recivedObject;

            while ((recivedObject = in.readObject()) != null) {


                Logger.info("New MSG");
                if (recivedObject instanceof TestMsg1 t) {
                    System.out.println("Received: TestMsg1 " + t.getText());
                    SocketServer.sockets.stream()
                            .filter(obj -> !Objects.equals(obj, this))
                            .forEach(socketRequest -> {
                                socketRequest.send(t);
                            });
                }

                if (recivedObject instanceof TestMsg2 t) {
                    System.out.println("Received: TestMsg2 " + t.getText());
                    SocketServer.sockets.stream()
                            .filter(obj -> !Objects.equals(obj, this))
                            .forEach(socketRequest -> {
                                socketRequest.send(t);
                            });
                }

                if (recivedObject instanceof Init t) {
                    System.out.println("Received: Init " + t.getLogin());
                    SocketServer.users.add(t.getLogin());
                    chatClient.send(SocketServer.getUsers());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

class CommOutThread implements Runnable {

    private final ObjectOutputStream out;
    public SynchronousQueue<Msg> queue = new SynchronousQueue<Msg>();

    CommOutThread(ObjectOutputStream out) {
        this.out = out;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Msg msg = queue.take();

                out.writeObject(msg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
