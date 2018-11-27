import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class App {

    public static void main(String[] args) {
        Serveur serveur = new Serveur();
        serveur.run();
    }
}
