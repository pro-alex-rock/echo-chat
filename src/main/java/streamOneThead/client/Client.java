package streamOneThead.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {

    //this block is for tests. It replaces the console input:
    {
        String expected = "Test string";
        InputStream inputStream = new ByteArrayInputStream(expected.getBytes());
        System.setIn(inputStream);
    }

    public static void main(String[] args) throws IOException {

        Client client = new Client();
        client.start();
    }

    public void start() throws IOException {
        try(Socket socket = new Socket("127.0.0.1", 3010);
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();) {
            String inputMsg = getInputMsg();
            sendMessage(outputStream, inputMsg);
            String answer = getServerMsg(inputStream);
            System.out.println(answer);
        }
    }

    public void sendMessage(OutputStream outputStream, String inputMsg) throws IOException {
        if (inputMsg == null) {
            throw new NullPointerException("You must enter a valid value to send the message.");
        }
        try {
            outputStream.write(inputMsg.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new IOException("Unable to write client message. " + e);
        }
    }

    private String getServerMsg(InputStream inputStream) throws IOException {
        try {
            byte[] array = new byte[50];
            int count = inputStream.read(array);
            return new String(array, 0, count);
        } catch (IOException e) {
            throw new IOException("Unable to read echo message from server. " + e);
        }
    }

    private String getInputMsg() throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            return bufferedReader.readLine();
        } catch (IOException e) {
            throw new IOException("Unable to read console message" + e);
        }
    }
}
