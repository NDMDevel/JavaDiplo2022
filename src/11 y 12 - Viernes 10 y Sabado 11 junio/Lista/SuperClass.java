
public class SuperClass
{
	private int x;
	public SuperClass()
	{
		setX(0);
	}
	public int getX() 
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public void showX()
	{
		System.out.println("x = " + x);
	}
}
