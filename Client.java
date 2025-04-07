import java.io.*;
import java.net.*;

public class Client implements Runnable {
  String ip;
  int port;
  Socket socket;
  BufferedReader in;
  PrintWriter out;
  BufferedReader userInput;

  @Override
  public void run() {
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
        out = new PrintWriter(socket.getOutputStream(), true);
        userInput = new BufferedReader(new InputStreamReader(System.in));

        String userMessage;
        while (true) {
          userMessage = userInput.readLine();
          out.println(userMessage);
          in.readLine();
        }
      } catch (IOException e) {
        if (!wait) System.err.println("Falha na conexão, tentando novamente...");
        try {
          Thread.sleep(125);
        } catch (InterruptedException ex) { ex.printStackTrace(); }
      }
    }
  }
}