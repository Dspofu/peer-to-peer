import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Servere server = new Servere();
    Cliente cliente = new Cliente();

    // Configuração da porta do servidor
    System.out.print("Digite a porta do servidor\n> ");
    server.port = sc.nextInt();
    Thread taskServer = new Thread(server); 

    // Configuração do cliente
    System.out.print("Digite o IP do destinatario\n> ");
    cliente.ip = sc.next();
    System.out.print("Digite a porta do destinatario\n> ");
    cliente.port = sc.nextInt();
    Thread taskClient = new Thread(cliente);

    // Inicializa o servidor primeiro
    taskServer.start();

    // Dá um pequeno delay antes de iniciar o cliente para garantir que o servidor esteja pronto
    try {
        Thread.sleep(1000);  // Aguardar 1 segundo para garantir que o servidor esteja pronto
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    // Agora, inicia o cliente
    taskClient.start();
  }
}
