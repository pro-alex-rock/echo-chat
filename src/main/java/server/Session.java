package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class Session/* extends Thread*/ {
    private final Socket socket;

    public Session(Socket socketClient) {
        this.socket = socketClient;
    }

    /*public void run() {
        String clientMsg;
        try(DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            OutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream())) {
            while (true) {
                clientMsg = dataInputStream.readUTF();
                dataOutputStream.write(("echo: " + clientMsg).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            try {
                throw new IOException("Failed to interact with client: " + e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }*/

    public void run() {
        String clientMsg;
        DataInputStream dataInputStream = null;
        OutputStream dataOutputStream = null;
        try {
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            while (true) {
                clientMsg = dataInputStream.readUTF();
                dataOutputStream.write(("echo: " + clientMsg).getBytes(StandardCharsets.UTF_8));
            }
        } catch (IOException e) {
            try {
                throw new IOException("Failed to interact with client: " + e);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } finally {
                try {
                    Objects.requireNonNull(dataInputStream).close();
                    Objects.requireNonNull(dataOutputStream).close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }
}
