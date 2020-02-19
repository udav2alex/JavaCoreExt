package homework6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientFlow {
    private Socket socket;
    private String name;
    private DataOutputStream out = null;
    private DataInputStream in = null;
    private volatile boolean finished = false;

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public ClientFlow(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        System.out.println(">>> " + this.name + " ready!");
        System.out.println(">>> For disconnect enter /end"+ "\n");

        try {
            out = new DataOutputStream(this.socket.getOutputStream());
            in = new DataInputStream(this.socket.getInputStream());

            Thread toAnotherSide = new Thread(() -> {
                try {
                    Scanner input = new Scanner(System.in);

                    while (true) {
                        String string = input.nextLine();

                        if (finished) break;

                        out.writeUTF(string);
                        out.flush();

                        if (string.equals("/end")) {
                            finished = true;
                            System.out.println(">>> Disconnect by user input...");
                            break;
                        }
                    }
                } catch (IOException e) {
                    if (socket.isOutputShutdown() || socket.isClosed()) {
                        System.out.println(">>> Output stream lost...");
                    } else {
                        e.printStackTrace();
                    }
                }
            }, "toAnotherSide-" + name);

            Thread fromAnotherSide = new Thread(() -> {
                try {
                    while (true) {
                        String string = in.readUTF();

                        if (finished) break;

                        if (string.equals("/end")) {
                            out.writeUTF(string);
                            out.flush();
                            finished = true;
                            socket.close();
                            System.out.println(">>> Disconnect by incoming message... Press <ENTER>!");
                            break;
                        } else {
                            System.out.println(string);
                        }
                    }
                } catch (IOException e) {
                    if (socket.isInputShutdown() || socket.isClosed()) {
                        System.out.println(">>> Input stream lost...");
                    } else {
                        e.printStackTrace();
                    }
                }
            }, "fromAnotherSide-" + name);

            toAnotherSide.start();
            fromAnotherSide.start();
            toAnotherSide.join();
            fromAnotherSide.join();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (out != null) out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
