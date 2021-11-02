package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        int port = 3000;
        Server server = new Server();
        server.start(port);
    }

    /*private void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            while (true) {
                Session sessionClient = new Session(server.accept());
                sessionClient.run();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    private void start(int port) {
        try (ServerSocket server = new ServerSocket(port)) {
            Session sessionClient = new Session(server.accept());
            sessionClient.run();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private void sendMessage(Socket clientSocket, String message) throws IOException {
        DataOutputStream outputStream = new DataOutputStream(clientSocket.getOutputStream());
        outputStream.writeUTF(message);
        outputStream.close();
    }

    private String readMessage(Socket clientSocket) throws IOException {
        DataInputStream inputStream = new DataInputStream(clientSocket.getInputStream());
        String answer = inputStream.readUTF();
        inputStream.close();
        return "echo: " + answer;
    }
}
