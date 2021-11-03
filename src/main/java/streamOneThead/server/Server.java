package streamOneThead.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.start();
    }

    public void start() throws IOException {
        int port = 3010;
        try(ServerSocket serverSocket = new ServerSocket(port);
            Socket socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();) {
                String msg = getClientMessage(inputStream);
                sendEcho(outputStream, msg);
        }
    }

    private static void sendEcho(OutputStream outputStream, String msg) throws IOException {
        try {
            outputStream.write(("echo: " + msg).getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IOException("Unable to write echo message. " + e);
        }
    }

    private static String getClientMessage(InputStream inputStream) throws IOException {
        try {
            byte[] array = new byte[50];
            int count = inputStream.read(array);
            return new String(array, 0, count);
        } catch (IOException e) {
            throw new IOException("Unable to read client message. " + e);
        }
    }
}
