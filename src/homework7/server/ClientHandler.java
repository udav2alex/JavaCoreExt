package homework7.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private Socket socket = null;
    private DataInputStream in;
    private DataOutputStream out;
    private Server server;
    private String nick;
    private String login;

    ClientHandler(Socket socket, Server server) {
        try {
            this.socket = socket;
            this.server = server;
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    //цикл аутентификации
                    while (true) {
                        String str = in.readUTF();
                        if (str.startsWith("/auth ")) {
                            String[] token = str.split(" ");
                            String newNick = server
                                    .getAuthService()
                                    .getNicknameByLoginAndPassword(token[1], token[2]);

                            if (newNick != null) {
                                sendMsg("/authok " + newNick);
                                nick = newNick;
                                login = token[1];
                                server.subscribe(this);
                                System.out.println("Клиент " + nick + " подключился");
                                break;
                            } else {
                                sendMsg("Неверный логин / пароль");
                            }
                        }
                    }

                    //цикл работы
                    while (true) {
                        String str = in.readUTF();
                        if (str.equals("/end")) {
                            out.writeUTF("/end");
                            break;
                        }

                        if (str.startsWith("/w ")) {
                            String[] token = str.split(" ", 3);

                            ClientHandler partner = server.searchForConnectedNick(token[1]);
                            if (partner != null) {
                                partner.sendMsg(token[2]);
                                this.sendMsg(token[2]);
                            } else {
                                this.sendMsg("No such user connected!");
                            }

                        } else {
                            server.broadcastMsg(str);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    server.unsubscribe(this);
                    try {
                        in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Клиент отключился");
                }
            }).start();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public String getNick() {
        return nick;
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
