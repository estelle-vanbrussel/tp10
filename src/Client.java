import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable{

    private Socket socket;
    private Serveur serveur;

    public Client(Socket socket, Serveur serveur) {
        this.socket = socket;
        this.serveur = serveur;
    }

    @Override
    public void run() {
        try {
            serveur.subscribeToBroadcast(this);
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(),
                    true);

            for(;;) {
                String line = reader.readLine();
                if (line==null) break;
                serveur.broadcastMessage("["+line+"]");
            }
                socket.close();
                serveur.unsubscribeToBroadcast(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String message){
        try {
            PrintWriter writer = new PrintWriter(
                    socket.getOutputStream(),
                    true);
            writer.println(message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
