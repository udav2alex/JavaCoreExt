package homework6;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static String serverAddress = "localhost";
    private static int serverPort = 11111;

    public Server() {

        try (ServerSocket serverSocket = new ServerSocket(serverPort, serverPort)) {
            System.out.println(">>> Start server...");

            Socket socket = serverSocket.accept();
            System.out.println(">>> Client connected!");

//            new ClientFlow(socket, "Server");
            new ClientFlowWaiting(socket, "Server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server();
    }
}
