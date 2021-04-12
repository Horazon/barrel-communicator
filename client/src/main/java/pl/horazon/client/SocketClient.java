package pl.horazon.client;


import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.apache.commons.lang3.RandomStringUtils;
import pl.horazon.barrel.common.pojo.Init;
import pl.horazon.barrel.common.pojo.BarrelMsg;
import pl.horazon.barrel.common.thread.CommInThread;
import pl.horazon.barrel.common.thread.CommOutThread;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.function.Consumer;

public class SocketClient {

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    private CommInThread commInThread;
    private CommOutThread commOutThread;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

        commOutThread = new CommOutThread(out);

        ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("task-name-%d")
                .setDaemon(true)
                .build();

        factory.newThread(commOutThread).start();

        commInThread = new CommInThread<BarrelMsg>(in, this::handle);

        factory.newThread(commInThread).start();

        this.send((new Init("login_" + RandomStringUtils.randomAlphabetic(5))));
    }

    private void handle(BarrelMsg barrelMsg) {
        msgConsumer.accept((BarrelMsg) barrelMsg);
    }

    public void send(BarrelMsg barrelMsg) {
        commOutThread.getQueue().add(barrelMsg);
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


    private Consumer<BarrelMsg> msgConsumer;

    public void setMsgConsumer(Consumer<BarrelMsg> msgConsumer) {
        this.msgConsumer = msgConsumer;
    }
}
