import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		int tcpPort = 4444;
		ServerSocket server = new ServerSocket(tcpPort);
		System.out.println("esperando clientes....");
		Socket sock = server.accept();
		
		System.out.println("----- Cliente conectado ------");
		
		Scanner scan = new Scanner(sock.getInputStream());
		while( scan.hasNextLine() == false )
		{
			//hago nada, espero que el cliente me envie datos
		}
		System.out.println( scan.nextLine() );
		//termino la conexion con el cliente
		sock.close();
		
		//cierro el server
		server.close();
	}
}
