import java.io.*;
import java.net.*;

public class Cliente implements Runnable {
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
    while (!connected) { // Loop para tentar reconectar até conseguir
      try {
        if (!wait) System.out.println("Tentando conectar a " + ip + " na porta " + port + "\r");
        else {
          System.out.println("\b"+loop[indexLoop]);
          if (indexLoop >= 4) indexLoop = 0;
          else indexLoop++;
        }
        wait = true;
        socket = new Socket(ip, port); // Tenta se conectar
        System.out.println("Conexão estabelecida.");
        connected = true; // Se conectar com sucesso, sai do loop

        // Criação dos fluxos de entrada e saída
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);
        userInput = new BufferedReader(new InputStreamReader(System.in));

        // Loop para enviar e receber mensagens
        String userMessage;
        while (true) {
          // Enviar mensagem
          // System.out.print("Digite uma mensagem para enviar: ");
          userMessage = userInput.readLine();
          out.println(userMessage); // Envia mensagem para o servidor

          // Receber resposta
          String serverResponse = in.readLine();
          System.out.println(serverResponse);
        }
      } catch (IOException e) {
        if (!wait)
          System.err.println("Falha na conexão, tentando novamente...");
        try {
          Thread.sleep(2000); // Espera 2 segundos antes de tentar novamente
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
  }
}