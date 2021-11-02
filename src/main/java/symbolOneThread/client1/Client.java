package symbolOneThread.client1;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author Oleksandr Haleta
 * 2021
 */
public class Client {

    public static void main(String[] args) throws IOException {
        int port = 3000;
        String host = "127.0.0.1";
        start(port, host);
    }

    public static void start(int port, String host) throws IOException {
        Client client = new Client();
        Socket socket = new Socket(host, port);

        InputStream inputStream = socket.getInputStream();
        OutputStream outputStream = socket.getOutputStream();

        String inputMsg = client.getInputMsg();
        client.sendMessage(outputStream, inputMsg);
        String answer = client.getServerMsg(inputStream);
        System.out.println(answer);
        inputStream.close();
        outputStream.close();

    }

    private void sendMessage(OutputStream outputStream, String inputMsg) throws IOException {
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
