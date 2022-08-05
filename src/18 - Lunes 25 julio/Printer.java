
public class Printer <T>
{
	private T data;
	public Printer()
	{
	}
	public Printer(T arg)
	{
		data = arg;
	}
	public void show()
	{
		System.out.println(data);
	}
}
