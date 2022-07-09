import java.io.Serializable;

public class MyRunnable
			 extends Counter
			 implements Runnable,
			 			Serializable
{
	private String name;
	public MyRunnable(String name)
	{
		this.name = name;
	}
	@Override
	public void run()
	{
		// TODO Auto-generated method stub
		for( int i=0 ; i<7 ; i++ )
		{
			System.out.println(name + ": " + i);
			//no puedo invocar a sleep porque no heredo
			//de Thread
			//sleep(500);
		}
	}
}
