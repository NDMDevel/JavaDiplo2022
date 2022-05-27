
public class List
{
	private Node header;
	private int  size;
	public List()
	{
		header = new Node();
		size = 0;
	}
	public void append(int data)
	{
		Node temp = new Node(data);
		//temp.data = data;
		//temp.next = null;
//		Node aux = header;
//		while( aux.next != null )
//		{
//			//avanzo en la lista
//			aux = aux.next;
//		}
//		aux.next = temp;
		Node aux = getNode(size-1);
		aux.next = temp;
		size++;
	}
	private Node getNode(int pos)
	{
		if( pos == -1 )
			return header;
		if( pos < -1 || pos >= size )
			System.exit( -1 );
		
		Node aux = header.next;
		int count = 0;
		while( count < pos )
		{
			//avanzo en la lista
			aux = aux.next;
			count++;
		}
		return aux;
	}
	public void show()
	{
		Node aux = header;
		while( aux.next != null )
		{
			//avanzo en la lista
			aux = aux.next;
			System.out.println( aux.data );
		}
	}}
