package ex5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

    private Socket socket;

    public Client(String url, int port) throws IOException {
        socket = new Socket(url, port);
    }

    public void sendMessage(String message) throws IOException {
        PrintWriter writer = new PrintWriter(
                socket.getOutputStream(),
                true);
        writer.println(message);
    }

    public void close() throws IOException {
        socket.close();
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            for(;;) {
                String line = reader.readLine();
                if (line==null) break;
                System.out.println(line);
            }
            close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
