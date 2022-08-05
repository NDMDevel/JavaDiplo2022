import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;

public class List<T> implements Iterable<T>
{
	private Node<T> header;
	private int     size;
	public List()
	{
		header = new Node</*T*/>();
		size = 0;
	}
	public void append(T data)
	{
		Node<T> temp = new Node<T>(data);
		try
		{
			Node<T> aux = getNode(size-1);
			aux.next = temp;
			size++;
		}
		catch( InvalidPos e)
		{
			
		}
	}
	private Node<T> getNode(int pos) throws InvalidPos
	{
		if( pos == -1 )
			return header;
		if( pos < -1 || pos >= size )
			throw new InvalidPos("pos fuera de rango");
		
		Node<T> aux = header.next;
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
		Node<T> aux = header;
		int idx = 0;
		while( aux.next != null )
		{
			//avanzo en la lista
			aux = aux.next;
			System.out.println( "["+idx+"] = " + aux.data );
			idx++;
		}
	}
	public T getData(int pos) throws InvalidPos
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
	public void setData(int pos,T data) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error setData(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error setData(pos,data): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		getNode(pos).data = data;
	}
	public void insert(int pos, T data) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error insert(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error insert(pos,data): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		Node<T> aux  = getNode(pos-1);
		
		Node<T> temp = new Node<>(data);
		temp.next = aux.next;
		aux.next = temp;
		size++;
	}
	public void remove(int pos) throws InvalidPos
	{
		if( pos < 0 || pos >= size )
		{
//			System.out.println("error remove(): pos debe ser mayor o igual que cero");
//			System.exit( -1 );
			throw new InvalidPos("error remove(pos): pos debe ser mayor o igual a 0 y menor que " + size);
		}
		Node<T> temp = getNode(pos-1);
		temp.next = temp.next.next;
		size--;
	}
	public int  length() { return size; }
	public void append(List<T> lst)
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
	public void copyFrom(List<T> l1)
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
	public static <V> void concat(List<V> l1,List<V> l2)
	{
		//xxx
	}
	@Override
	public Iterator<T> iterator()
	{
		ListIterator<T> x = new ListIterator<T>();
		return x;
	}
	class ListIterator<T> implements Iterator<T>
	{
		//IMPLEMENTO comportamiento de "CURSOR"
		private Node<T> target = (Node<T>) header;
		@Override
		public boolean hasNext()
		{
			return target.next != null;	//return 3+2;
		}
		@Override
		public T next()
		{
			target = target.next;
			return target.data;
		}
	}
}
