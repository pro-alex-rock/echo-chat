package symbolMiltiThread.server;

import java.io.*;
import java.net.Socket;

public class Session extends Thread {
    private final Socket socket;

    public Session(Socket socketClient) {
        this.socket = socketClient;
    }

    public void run() {
        connect();
    }

    private void connect() {
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String msg = getClientMsg(bufferedReader);
            sendEcho(bufferedWriter, msg);
        } catch (IOException e) {}
    }
    private static void sendEcho(BufferedWriter bufferedWriter, String msg) throws IOException {
        try {
            bufferedWriter.write(("echo: " + msg));
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new IOException("Unable write echo message. " + e);
        }
    }

    private String getClientMsg(BufferedReader bufferedReader) throws IOException {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IOException("Unable read client message" + e);
        }
    }
}
