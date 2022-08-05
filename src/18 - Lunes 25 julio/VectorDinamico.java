
public class VectorDinamico <T>
	implements Containers<T> 
{
	private T[] vec;
	public VectorDinamico(int size)
	{
		vec = (T[]) new Object[size];
	}
	@Override
	public void setData(int pos,T val)
	{
		vec[pos] = val;
	}
	@Override
	public T    getData(int pos)
	{
		return vec[pos];
	}
	@Override
	public void insert(int pos,T data)
	{
		//....
	}
	@Override
	public void remove(int pos)
	{
		//....
	}
	@Override
	public int length()
	{
		return vec.length;
	}
	public void show()
	{
		//range for
		for( T item : vec )
			System.out.println(item);
		//[0]: 10.2
		//[1]: 12.3
	}
	public String toString()
	{
		String ret = "";
		for( int i=0 ; i<vec.length ; i++ )
			ret = ret + String.format("[%d]: %s\n",i,vec[i].toString());
		return ret;
	}
}
