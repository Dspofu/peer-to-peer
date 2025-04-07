import java.io.*;
import java.net.*;

public class Server implements Runnable {
  ServerSocket server;
  int port;

  @Override
  public void run() {
    try {
      server = new ServerSocket(port);
      System.out.println("Servidor iniciado na porta: " + port);

      while (true) {
        Socket client = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
          client = server.accept();
          System.out.println("\nServidor conectado: " + client.getInetAddress() + "\n");

          in = new BufferedReader(new InputStreamReader(client.getInputStream()));
          out = new PrintWriter(client.getOutputStream(), true);
          String message;
          while ((message = in.readLine()) != null) {
            String username = "<desconhecido>";
            String actualMessage = message;

            if (message.contains(":")) {
              int separatorIndex = message.indexOf(":");
              username = message.substring(0, separatorIndex);
              actualMessage = message.substring(separatorIndex + 1).trim();
            }

            System.out.println("\n[" + username + "] | Escreveu: " + actualMessage);
            out.println("OK");
          }
        } catch (IOException e) {
          System.err.println("\nErro de conexão com o cliente: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}