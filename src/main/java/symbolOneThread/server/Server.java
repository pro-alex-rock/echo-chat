package symbolOneThread.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public class Server {

    public static void main(String[] args) throws IOException {
        start();
    }

    public static void start() throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                String msg = bufferedReader.readLine() ;
                sendEcho(bufferedWriter, msg);
            }
        }
    }

    private static void sendEcho(BufferedWriter bufferedWriter, String msg) throws IOException {
        try {
            bufferedWriter.write(("echo: " + msg));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new IOException("Unable to write echo message. " + e);
        }
    }
}
