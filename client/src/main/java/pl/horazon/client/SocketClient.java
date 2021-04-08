package pl.horazon.client;


import org.apache.commons.lang3.RandomStringUtils;
import pl.horazon.barrel.common.pojo.Init;
import pl.horazon.barrel.common.pojo.Msg;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

public class SocketClient {

    //public ConcurrentLinkedQueue<Msg> queue = new ConcurrentLinkedQueue<Msg>();
    public LinkedBlockingQueue<Msg> queue = new LinkedBlockingQueue<Msg>();
    //public SynchronousQueue<Msg> queue = new SynchronousQueue<Msg>();

    //--------------------------------------------------------

    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startConnection(String ip, int port) throws IOException {
        clientSocket = new Socket(ip, port);
        out = new ObjectOutputStream(clientSocket.getOutputStream());
        in = new ObjectInputStream(clientSocket.getInputStream());

        Runnable r = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                    //Msg msg = queue.poll();
                    Msg msg = queue.take();

                    out.writeObject(msg);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                while(true){
                    try {
                        Object line = null;
                        line = in.readObject();

                        msgConsumer.accept((Msg) line);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Thread t = new Thread(r);
        t.setDaemon(true);

        t.start();

        Thread t2 = new Thread(r2);
        t2.setDaemon(true);

        t2.start();


        queue.add(new Init("login_" + RandomStringUtils.randomAlphabetic(5)));
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }


    private Consumer<Msg> msgConsumer;

    public void setMsgConsumer(Consumer<Msg> msgConsumer) {
        this.msgConsumer = msgConsumer;
    }
}
