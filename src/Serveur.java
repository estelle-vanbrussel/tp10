import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Serveur {

    List<Client> clients = new ArrayList<>();

    public void run(){
        try {
            ServerSocket serverSocket = new ServerSocket(8088);
            for (int nClient = 0; nClient < 99999 ; ++nClient) {
                Socket socket = serverSocket.accept();

                Client client = new Client(socket,this);
                new Thread(client).start();
            }
            serverSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void subscribeToBroadcast(Client client){
        clients.add(client);
    }
    public void unsubscribeToBroadcast(Client client){
        clients.remove(client);
    }
    public void broadcastMessage(String message){
        for (Client client: clients) {
            client.sendMessage(message);
        }
    }

}
