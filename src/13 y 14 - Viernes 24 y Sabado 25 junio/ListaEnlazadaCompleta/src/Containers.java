
public interface Containers
{
	public void setData	(int pos,int data) 	throws InvalidPos;
	public int  getData	(int pos) 			throws InvalidPos;
	public void insert	(int pos,int data)	throws InvalidPos;
	public void remove	(int pos)			throws InvalidPos;
	public int  length	();
}
