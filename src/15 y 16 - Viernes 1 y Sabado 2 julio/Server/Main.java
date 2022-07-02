import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		/************ SERVER ***************/
		int tcpPort = 4444;
		ServerSocket server = new ServerSocket(tcpPort);
		System.out.println("Esperando conexion");
		Socket client = server.accept();
		System.out.println("cliente conectado");
		
		List list = new List();
		list.append(100);
		list.append(200);
		list.append(300);
		
		ObjectOutputStream out = new ObjectOutputStream( client.getOutputStream() );
		out.writeObject( list );
		
//		client.getOutputStream().write("lista enviada\n".getBytes());
		
		client.close();
		server.close();
	}
	public static void main1(String[] args)
	{
		//codigo del server
	}
}
