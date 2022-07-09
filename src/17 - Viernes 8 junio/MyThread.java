
public class MyThread extends Thread
{
	private String name;
	private Counter counter;
	public MyThread(String name,Counter counter)
	{
		this.name    = name;
		this.counter = counter;
	}
	@Override
	public void run()
	{
//		try
//		{
			for( int i=0 ; i<100000 ; i++ )
			{
//				System.out.println(name + ": " + i);
				counter.increment();
//				sleep(1000);
			}
//		}
//		catch(InterruptedException e)
//		{
			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
