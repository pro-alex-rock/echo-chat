package client1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.start();
    }

    void start() throws IOException {
        int port = 3000;
        String host = "127.0.0.1";
        Session session = new Session(host, port);

        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                String consoleMsg = bufferedReader.readLine();
                if (consoleMsg.equals("exit")) {
                    break;
                }
                session.sendMessage(consoleMsg);

                String fromServerMsg = session.getMessage();
                System.out.println(fromServerMsg);
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }
}
