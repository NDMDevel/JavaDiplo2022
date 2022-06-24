
/*	1) sobrecarga de append que recibe lista
 *  2) copyFrom
 *  
 *  3) insertList
 *  4) swapList
 *  x) static concat
 */

public class List implements Recordable,FileRecordable
{
	private Node header;
	private int  size;
	public List()
	{
		System.out.println("List() ctor");
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
		System.out.println("show() de List()");
		//declarativo, pero malo en performance
		for( int i=0 ; i<size ; i++ )
		{
			Node aux = getNode(i);
			System.out.println(aux.data);
		}
		
		//criptico, pero bueno en performance
//		Node aux = header;
//		int idx = 0;
//		while( aux.next != null )
//		{
//			//avanzo en la lista
//			aux = aux.next;
//			System.out.println( "["+idx+"] = " + aux.data );
//			idx++;
//		}
	}
	public int getData(int pos)
	{
		if( pos < 0 || pos >= size )
		{
			System.out.println("error getData(): pos debe ser mayor o igual que cero");
			System.exit( -1 );//throw new OutOfBound("la pos debe estar dentro de los margenes");
		}
//		Node aux = getNode(pos);
//		return aux.data;
		return getNode(pos).data;
	}
	public void setData(int pos,int data)
	{
		if( pos < 0 )
		{
			System.out.println("error setData(): pos debe ser mayor o igual que cero");
			System.exit( -1 );
		}
		getNode(pos).data = data;
//		Node aux = getNode(pos);
//		aux.data = data;
	}
	public void insertData(int pos, int data)
	{
		if( pos < 0 )
		{
			System.out.println("error insertData(): pos debe ser mayor o igual que cero");
			System.exit( -1 );
		}
		Node aux  = getNode(pos-1);
		
		Node temp = new Node(data);
		temp.next = /*getNode(pos-1)*/aux.next;
		/*getNode(pos-1)*/aux.next = temp;
		size++;
		
//		Node temp = new Node(data);
//		Node aux  = temp.next;
//		getNode(pos)
		
	}
	public void removeData(int pos)
	{
		if( pos < 0 )
		{
			System.out.println("error removeData(): pos debe ser mayor o igual que cero");
			System.exit( -1 );
		}
		Node temp = getNode(pos-1);
		temp.next = temp.next.next;
		size--;
//		Node prev = getNode(pos-1);
//		Node sig  = getNode(pos).next;//prev.next;
//		sig = sig.next;
//		prev.next = sig;
//		size--;
	}
	public int  getSize() { return size; }
	public void append1(List lst)
	{
		int len = lst.getSize();
		for( int j=0 ; j<len ; j++ )
			append(lst.getData(j));		
	}
	public void append2(List l1)
	{
		Node insertNode = l1.header.next;
		Node lastNode = getNode(size - 1); 
		for(int i = 0; i < l1.size; i++)
		{ 
			Node NodeAux = new Node(insertNode.data); 
			lastNode.next = NodeAux;
			size++; 
			lastNode = NodeAux; 
			insertNode = insertNode.next; 
		}
	}
	public void copyFrom(List l1)
	{
//		List aux = l1;
//		header = l1.header;
//		size = l1.size;
		header.next = null;
		size = 0;
//		append1(l1);
		for( int i=0 ; i<l1.getSize() ; i++ )
			insertData(i,l1.getNode(i).data);//append(l1.getNode(i).data);
	}
	public void showThis()
	{
		//this: una referencia a la instancia que invoca (a showThis en este caso)
		System.out.println( this );
	}
	public List sort()
	{
		//codigo de burbuja...
		//
//		List aux = new List();
//		for()
//			aux.append(getData(i));
		return this;
	}
	public String _toString()
	{
/*
len: 4
[0]: 12
[1]: 22
[2]: 112
[3]: 232
 */
		return "";
	}
	public String toString()//"hello \n world"
	{
		/*
		 * 	len: 3
		 * 	[0]: 234
		 * 	[1]: 003
		 * 
		 * 
		 * */
		String auxStr = "";
		Node aux = header;
		int count = 0;
		//"C:\\users\\xxxx\\xxx"
		while(aux.next != null)
		{ 
			aux = aux.next;
			auxStr += String.format("[%d]: %d \n", count, aux.data);
			//auxStr += String.format("[%d:%d]\n",count,aux.data); 
			count ++;
		}
		return String.format("\nSize: %d\n", count) + auxStr;
	}
	@Override
	public String saveToString()
	{
		//10 30 233 34994 394994 4949
		String str = "";
		for( int i=0 ; i<size ; i++ )
		{
//			if( i == size-1 )
//				str = str + getData(i);
//			else
				str = str + getData(i) + " ";
		}
//		str = str.trim();
		str = str.substring(0,str.length()-1);
		return str;
	}
	@Override
	public void loadFromString(String backup)
	{
		//limpio la lista
		header.next = null;
		size = 0;
		
		//interpreto los datos de la cadena
		String[] values = backup.split(" ");
		for( int i=0 ; i<values.length ; i++ )
		{
//			System.out.println(values[i]);
			int val = Integer.valueOf(values[i]);
			append( val );
		}
	}
}
