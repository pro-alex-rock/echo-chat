package symbolOneThread.client1;

import java.io.*;
import java.net.Socket;

public class Session {
    private final Socket socket;

    public Session(String host, int port) throws IOException {
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            throw new IOException("Failed to create Socket. StackTrace:" + e);
        }
    }

    public String getMessage() throws IOException {
        String answer;
        try(DataInputStream dataInputStream = new DataInputStream(socket.getInputStream())) {
            answer = dataInputStream.readUTF();
        } catch (IOException e) {
            throw new IOException("Failed to receive message. StackTrace:" + e);
        }
        return answer;
    }

    public void sendMessage(String inputMessage) throws IOException {
        try(DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            dataOutputStream.writeUTF(inputMessage);
        } catch (IOException e) {
            throw new IOException("Failed to send message:" + inputMessage + "\nStackTrace:" + e);
        }
    }
}
