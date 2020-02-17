package homework6;

import java.io.IOException;
import java.net.ConnectException;
import java.net.Socket;

public class Client {
    private String serverAddress = "localhost";
    private int serverPort = 11111;

    public Client() {
        try (Socket socket = new Socket(serverAddress, serverPort)) {
            System.out.println(">>> Server connected!");
//            new ClientFlow(socket, "Client");
            new ClientFlowWaiting(socket, "Client");

        } catch (ConnectException e) {
            System.out.println("Server response error!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client();
    }
}
