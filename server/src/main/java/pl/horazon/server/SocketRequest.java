package pl.horazon.server;

import pl.horazon.barrel.common.pojo.TestMsg1;
import pl.horazon.barrel.common.pojo.TestMsg2;

import java.io.*;
import java.net.Socket;

public class SocketRequest extends Thread{
    private final Socket clientSocket;

    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketRequest(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {
            System.out.println("Thread " + Thread.currentThread().getName());
            System.out.println("process...");
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());

            Object str;

            while ((str = in.readObject()) != null) {


                if (str instanceof TestMsg1 t) {
                    // can use s here
                    System.out.println("TestMsg1 " + t.getText());

//                    SocketServer.sockets.stream().forEach(socketRequest -> {
//                        socketRequest.sendA("TestMsg2 " + t.getText());
//                    });
                }

                if (str instanceof TestMsg2 t) {
                    // can use s here
                    System.out.println("TestMsg2 " + t.getText());

//                    SocketServer.sockets.stream().forEach(socketRequest -> {
//                        System.out.println(socketRequest.getName());
//                        socketRequest.sendA("TestMsg2 " + t.getText());
//                    });
                }

                // send to client
                //out.writeObject(new TestMsg1("chujnia"));
                //out.writeObject(new TestMsg1("chujnia"));


            }

            in.close();
            out.close();
            clientSocket.close();

            System.out.println("done...");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void sendA(String s) {
        System.out.println(Thread.currentThread().getName() + " sending");
        try {
            out.writeObject(new TestMsg1("chujnia"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
