import java.io.*;
import java.net.*;

public class Servere implements Runnable {
    ServerSocket server;
    int port;
    Socket client;
    BufferedReader in;
    PrintWriter out;

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("Servidor iniciado na porta: " + port);

            while (true) {
                // Aguarda até que o cliente se conecte
                client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());

                // Criação dos fluxos de entrada e saída para comunicação
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);

                // Loop para ler e responder mensagens
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println(message);
                    // Responde ao cliente
                    out.println(message);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
