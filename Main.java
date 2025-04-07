import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    try {
      @SuppressWarnings("resource")
      Scanner scanner = new Scanner(System.in);
      Server server = new Server();
      Client cliente = new Client();

      System.out.print("Digite a porta do servidor: ");
      server.port = scanner.nextInt();
      Thread taskServer = new Thread(server);

      System.out.print("Digite o IP do destinatario: ");
      cliente.ip = scanner.next();
      System.out.print("Digite a porta do destinatario: ");
      cliente.port = scanner.nextInt();
      Thread taskClient = new Thread(cliente);

      taskServer.start();

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      taskClient.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}