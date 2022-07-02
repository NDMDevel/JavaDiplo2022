import java.io.Serializable;

public class Node implements Serializable
{
	public int  data;
	public Node next;
	public Node()
	{
		this.data = 0; 
		this.next = null;
	}
	public Node(int data)
	{
		this.data = data;
		this.next = null;
	}
}
