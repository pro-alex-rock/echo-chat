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
        int port = 3000;
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();


        String msg = getClientMessage(inputStream, socket);
        sendEcho(outputStream,socket, msg);
        outputStream.flush();
        outputStream.close();
        inputStream.close();
    }

    private static void sendEcho(OutputStream outputStream, Socket socket, String msg) throws IOException {
        outputStream.write(("echo: " + msg).getBytes(StandardCharsets.UTF_8));
    }

    private static String getClientMessage(InputStream inputStream, Socket socket) throws IOException {

        byte[] array = new byte[50];
        int count = inputStream.read(array);

        return new String(array, 0, count);
    }
}
