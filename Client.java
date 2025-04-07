import java.io.*;
import java.net.*;

public class Client implements Runnable {
  String ip;
  int port;
  Socket socket;
  BufferedReader in;
  PrintWriter out;
  BufferedReader userInput;
  String username;

  @Override
  public void run() {
    try {
      username = System.getProperty("user.name");
    } catch (SecurityException e) {
      username = "Usuário";
    }
    userInput = new BufferedReader(new InputStreamReader(System.in));

    while (true) {
      boolean connected = false;
      boolean wait = false;
      String loop[] = { "/", "-", "\\", "|" };
      int indexLoop = 0;

      while (!connected) {
        try {
          if (!wait) System.out.println("\nTentando conectar a " + ip + " na porta " + port);
          else {
            System.out.print("\b" + loop[indexLoop]);
            indexLoop = (indexLoop + 1) % loop.length;
          }
          wait = true;
          socket = new Socket(ip, port);
          System.out.println("\nConexão de 'Client' estabelecida.");
          connected = true;

          in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
          if (!wait) System.err.println("\nFalha na conexão, tentando novamente...");
          try {
            Thread.sleep(125);
          } catch (InterruptedException ex) {
            ex.printStackTrace();
          }
        }
      }

      try {
        String userMessage;
        while (true) {
          userMessage = userInput.readLine();
          out.println(username + ":" + userMessage);
          in.readLine();
        }

      } catch (IOException e) {
        System.err.println("\nConexão com o servidor perdida: " + e.getMessage());
      } finally {
        try {
          if (out != null) out.close();
          if (in != null) in.close();
          if (socket != null) socket.close();
        } catch (IOException ex) {}
      }
    }
  }
}