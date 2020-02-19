package homework6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientFlowWaiting {
    private Socket socket;
    private String name;
    private volatile boolean finished = false;
    private final Object monitor = new Object();

    public String getName() {
        return name;
    }

    public Socket getSocket() {
        return socket;
    }

    public ClientFlowWaiting(Socket socket, String name) {
        this.socket = socket;
        this.name = name;
        System.out.println(">>> " + this.name + " ready! (wait used)");
        System.out.println(">>> For disconnect enter /end" + "\n");

        try (
                DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
                DataInputStream in = new DataInputStream(this.socket.getInputStream());
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in))
        ) {
            Thread toAnotherSide = new Thread(() -> {
                try {
                    String string;

                    while (true) {
                        // waiting for user input
                        synchronized (input) {
                            while (true) {
                                if (input.ready()) {
                                    string = input.readLine();
                                    break;
                                } else {
                                    input.wait(200);
                                }
                            }
                        }

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
                } catch (InterruptedException e) {
                    System.out.println("Terminate console input thread!");
                }
            }, "toAnotherSide-" + name);

            Thread fromAnotherSide = new Thread(() -> {
                String string;

                try {
                    while (true) {
                        // waiting for incoming message
                        synchronized (monitor) {
                            while (true) {
                                if (in.available() > 0) {
                                    string = in.readUTF();
                                    break;
                                } else {
                                    monitor.wait(200);
                                }
                            }
                        }

                        if (finished) break;

                        if (string.equals("/end")) {
                            out.writeUTF(string);
                            out.flush();
                            finished = true;
                            socket.close();
                            System.out.println(">>> Disconnect by incoming message...");
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
                } catch (InterruptedException e) {
                    System.out.println("fromAnotherSide wait interrupted!");
                    e.printStackTrace();
                }
            }, "fromAnotherSide-" + name);

            toAnotherSide.start();
            fromAnotherSide.start();
            fromAnotherSide.join();
            toAnotherSide.interrupt();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
