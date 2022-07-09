
public class Counter
{
	private long i;
	public Counter()
	{
		i = 0;
	}
	public /*synchronized*/ void increment()
	{
//		i++;
		long temp = i;
		temp = temp + 1;
		i = temp;
	}
	public void show()
	{
		System.out.println("i: "+i);
	}
}
