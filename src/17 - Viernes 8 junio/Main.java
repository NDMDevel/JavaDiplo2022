import java.util.concurrent.Semaphore;

public class Main
{
	public static void main(String[] args) throws InterruptedException
	{
		//Dos metodos para logra multithreading (ejecucion multiple)
		//1: usando (heredando) la clase Thread
		//2: implementado la interface Runnable
		
		//Metodo 1:
		Counter cnt = new Counter();
		cnt.increment();
		cnt.increment();
		cnt.show();
		
		MyThread t1 = new MyThread("t1",cnt);
		MyThread t2 = new MyThread("t2",cnt);
		System.out.println("Main 1");
		t1.start();
		t2.start();
		System.out.println("Main 2");
		
		t1.join();
		t2.join();
		cnt.show();
		
		//Metodo 2:
		MyRunnable r1 = new MyRunnable("r1");
		MyRunnable r2 = new MyRunnable("r2");
		Thread tr1 	  = new Thread(r1);
		Thread tr2 	  = new Thread(r2);
		tr1.start();
		tr2.start();
		System.out.println("Main 3");
		
		tr1.join();
		tr2.join();
		System.out.println("Main: todas las hebras terminadas");
				
		//Semaforos
		Counter cnt1 = new Counter();
		Semaphore sem = new Semaphore(1/*,true*/);
		SemThread st1 = new SemThread("st1",sem,cnt1);
		SemThread st2 = new SemThread("st2",sem,cnt1);
		SemThread st3 = new SemThread("st3",sem,cnt1);
		SemThread st4 = new SemThread("st4",sem,cnt1);
		SemThread st5 = new SemThread("st5",sem,cnt1);
		SemThread st6 = new SemThread("st6",sem,cnt1);
		st1.start();
		st2.start();
		st3.start();
		st4.start();
		st5.start();
		st6.start();

		st1.join();
		st2.join();
		st3.join();
		st4.join();
		st5.join();
		st6.join();
		cnt1.show();	//600000
		System.out.println("Main: terminado");
	}

}
