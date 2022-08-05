
public interface Containers <T>
{
	public void setData	(int pos,T data);
	public T    getData	(int pos);
	public void insert	(int pos,T data);
	public void remove	(int pos);
	public int  length	();
//	public boolean greaterThan(int pos,T val);
}
