package symbolMiltiThread.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public class Server {

    public static void main(String[] args) throws IOException {
        new Server().openChannel();
    }
    public void openChannel() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            while (true) {
                Socket socket = serverSocket.accept();
                Session session = new Session(socket);
                session.start();
            }
        }
    }
}
