import java.io.*;
import java.net.*;

public class Server implements Runnable {
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
        client = server.accept();
        System.out.println("Servidor conectado: " + client.getInetAddress()+"\n");

        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        out = new PrintWriter(client.getOutputStream(), true);

        String message;
        while ((message = in.readLine()) != null) {
          System.out.println(client.getInetAddress()+":"+port+" | Escreveu: "+message);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}