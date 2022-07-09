import java.util.concurrent.Semaphore;

public class SemThread extends Thread
{
	private String name;
	private Semaphore sem;
	private Counter cnt;
	public SemThread(String name,
					 Semaphore sem,
					 Counter cnt)
	{
		this.name = name;
		this.sem  = sem;
		this.cnt  = cnt;
	}
	@Override
	public void run()
	{
		try
		{
			for( int i=0 ; i<100000 ; i++ )
			{
				sem.acquire();
//				System.out.println(name + ": " + i);
				cnt.increment();
				sem.release();
			}
		}
		catch( InterruptedException e )
		{
			e.printStackTrace();
		}
	}
}
