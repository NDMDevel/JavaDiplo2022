import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main
{
	public static void main(String[] args) throws IOException, ClassNotFoundException
	{
		/************** CLIENT ******************/
		Socket client = new Socket("127.0.0.1",4444);

		ObjectInputStream input = new ObjectInputStream(client.getInputStream());

//		Scanner scan = new Scanner(client.getInputStream());
//		while( scan.hasNextLine() == false );
//		System.out.println( scan.nextLine() );
				
		List list = (List)input.readObject();
		list.show();
		
		client.close();
	}
	public static void main1(String[] args) throws IOException
	{
		Socket client = new Socket("127.0.0.1",1236);
		Scanner scan  = new Scanner( client.getInputStream() );		
		client.getOutputStream().write("hello server\n".getBytes());
		
		//espero al server que envie datos...
		while( scan.hasNextLine() == false );
		System.out.println(scan.nextLine());
		
		client.close();
	}
}
