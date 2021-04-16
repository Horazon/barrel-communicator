package pl.horazon.server;

import org.tinylog.Logger;
import pl.horazon.barrel.common.pojo.system.User;
import pl.horazon.barrel.common.pojo.system.UserList;
import pl.horazon.server.handle.ChatClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class SocketServer {

    private static final int PORT = 6666;

    private ServerSocket serverSocket;
    private Socket clientSocket;

    public static final List<ChatClient> sockets = new ArrayList<>();

    public static final List<String> users = new ArrayList<>();

    public void start(int port) throws IOException {
        Logger.info("Starting server");

        serverSocket = new ServerSocket(port);

        Logger.info("Starting server complete");
        while (true) {

            Logger.info("Waiting for client...");
            clientSocket = serverSocket.accept();

            Logger.info("New client connected. Starting new client thread.");
            ChatClient sr = new ChatClient(clientSocket);
            sockets.add(sr);
        }
    }

    public static UserList getUsers() {

        return new UserList(users.stream()
                .map(s -> new User(new Random().nextLong(), s))
                .collect(Collectors.toList()));
    }

    public void stop() throws IOException {
        clientSocket.close();
        serverSocket.close();
    }

    public static void main(String[] args) throws IOException {
        SocketServer server = new SocketServer();
        server.start(PORT);
    }
}
