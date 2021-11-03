package streamOneThead.client;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
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
        int port = 3000;
        String host = "127.0.0.1";
        //Client client = new Client();
        Socket socket = new Socket(host, port);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        String inputMsg = getInputMsg();
        sendMessage(outputStream, inputMsg);
        String answer = getServerMsg(inputStream);
        System.out.println(answer);
        inputStream.close();
        outputStream.close();

    }

    public void sendMessage(OutputStream outputStream, String inputMsg) throws IOException {
        if (inputMsg == null) {
            throw new NullPointerException("You must enter a valid value to send the message.");
        }
        outputStream.write(inputMsg.getBytes(StandardCharsets.UTF_8));
    }

    private String getServerMsg(InputStream inputStream) throws IOException {
        byte[] array = new byte[50];
        int count = inputStream.read(array);
        return new String(array, 0, count);
    }

    private String getInputMsg() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String msg = bufferedReader.readLine();
        bufferedReader.close();
        return msg;
    }
}
