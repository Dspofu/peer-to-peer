import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

public class Main{

  public static void reservarPort(int myPort, SocketAddress ip){ 
    try { 
      ServerSocket server = new ServerSocket(myPort); 

      System.out.println("Esperando conexao" + server.getLocalPort()); 
      Socket client = server.accept(); 

      System.out.println("Porta reservada com sucesso"); 
      
      System.out.println("Conectada a " + client.getInetAddress().getHostAddress()+" na porta" + client.getPort()); 
      client.connect(ip); 
      System.out.println("Conexão sendo fechada");
      server.close();
      System.out.println("Porta fechada com sucesso.");
      } catch (IOException e) { 
      System.err.println("Houve um erro ao tentar separar a porta 8888, favor verificar as portas da maquina."); 
      } catch (Exception er) { 
      System.err.println("Houve algun erro inesperado, favor verificar."); 
      } 
  }

  public static void inputUser() { 
    Scanner sc = new Scanner(System.in); 
    System.out.print("\nInsira a porta que deseja reservar para sua maquina\n>");
    int myPort = sc.nextInt(); 
    System.out.print("Insira o IP que deseja conectar.\n>"); 
    String msgIp = sc.next(); 
    System.out.print("Insira a porta do IP que deseja conectar.\n>"); 
    Integer msgPort = sc.nextInt(); 
    sc.close(); 
    SocketAddress ip = new InetSocketAddress (msgIp, msgPort);
    reservarPort(myPort, ip); 
  }

  public static void main(String[] args) { 
    inputUser(); 
  } 
}