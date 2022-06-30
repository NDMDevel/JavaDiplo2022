import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class List implements Containers,Recordable,FileRecordable
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
		try
		{
			Node aux = getNode(size-1);
			aux.next = temp;
			size++;
		}
		catch( InvalidPos e)
		{
			
		}
	}
	private Node getNode(int pos) throws InvalidPos
	{
		if( pos == -1 )
			return header;
		if( pos < -1 || pos >= size )
			throw new InvalidPos("pos fuera de rango");
		
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
		int idx = 0;
		while( aux.next != null )
		{
			//avanzo en la lista
			aux = aux.next;
			System.out.println( "["+idx+"] = " + aux.data );
			idx++;
		}
	}
	@Override
	public int getData(int pos) throws InvalidPos
	{
//		if( pos < 0 || pos >= size )
//		{
//			System.out.println("error getData(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );//throw new OutOfBound("la pos debe estar dentro de los margenes");
//		}
//		Node aux = getNode(pos);
//		return aux.data;
		return getNode(pos).data;
	}
	@Override
	public void setData(int pos,int data) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error setData(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error setData(pos,data): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		getNode(pos).data = data;
	}
	@Override
	public void insert(int pos, int data) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error insert(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error insert(pos,data): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		Node aux  = getNode(pos-1);
		
		Node temp = new Node(data);
		temp.next = aux.next;
		aux.next = temp;
		size++;
	}
	@Override
	public void remove(int pos) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error remove(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error remove(pos): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		Node temp = getNode(pos-1);
		temp.next = temp.next.next;
		size--;
	}
	@Override
	public int  length() { return size; }
	public void append(List lst)
	{
		//Este try se necesita porque getData puede lanzar una excepcion (throw).
		//Sin embargo, para que esto suceda, el indice j debe ser menor que cero
		//o mayor que len. Esto nunca sucedera dado que el for itera j desde cero
		//hasta len-1, de modo que nunca se producira la excepcion.
		//Aun asi, debemos usar try porque el compilador nos exige atender la posible
		//generacion de la excepcion...
		try
		{
			int len = lst.length();
			for( int j=0 ; j<len ; j++ )
				append(lst.getData(j));
		}
		catch( InvalidPos e )
		{
			//nunca deberia llegar aca...
		}
	}
	public void copyFrom(List l1)
	{
		header.next = null;
		size = 0;
		try
		{
			for( int i=0 ; i<l1.length() ; i++ )
				insert(i,l1.getNode(i).data);
		}
		catch( InvalidPos e )
		{
			//no deberia llegar aca, porque se produce excepcion solo si
			//insert o getNode son accedidos con un indice fuera de rango
			//lo cual esta garantizado que no suceda por el for que itera
			//desde cero hasta length - 1
		}
	}
	public void showThis()
	{
		//this: una referencia a la instancia que invoca (a showThis en este caso)
		System.out.println( this );
	}
	public List sort()
	{
		try
		{
			for( int i=size-1 ; i>0 ; i-- )
				for( int j=0 ; j<i ; j++ )
				{
					int x = getData(j);
					int y = getData(i);
					if( x > y )
					{
						int aux = x;
						setData(j,y);
						setData(i,aux);
					}
				}
		}
		catch(InvalidPos e)
		{
			
		}
		return this;
	}
	public String toString()
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
		while(aux.next != null)
		{ 
			aux = aux.next;
			auxStr += String.format("[%d]: %d \n", count, aux.data);
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
			try
			{
				str = str + getData(i) + " ";
			}
			catch( InvalidPos e )
			{
				
			}
		}
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
	@Override
	public void saveToFile(String path)
	{
		try
		{
			RandomAccessFile file = new RandomAccessFile(path, "rw");
			for( int i=0 ; i<size ; i++ )
				file.writeInt( getData(i) );
			file.close();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch(InvalidPos e)
		{
			e.printStackTrace();
		}
		/*finally
		{
			System.out.println("algun error no capturado en los catch anteriores");
		}*/
	}
	@Override
	public void loadFromFile(String path)	//aaaabbbbccccdddd
	{
		RandomAccessFile file = null;
		try
		{
			file = new RandomAccessFile(path, "r");
//			loadFromString( file.readLine() );
			header.next = null;
			size = 0;
			while( true )
				append( file.readInt() );
			//2232 1000 85000 24
			//4    4    5     2
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			
		}
		try
		{
			if( file != null )
				file.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
