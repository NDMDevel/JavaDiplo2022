
public class Lista
{
	public static void main(String[] args)
	{
		List l = new List();
		l.append(3); 
		l.append(32);
		l.append(75);

		l.setData(0, -205);
		l.setData(2, 18);
		
		l.insertData(0,88);
//		l.removeData(2);
		
//		System.out.println(l.getData(0));
//		System.out.println(l.getData(1));
//		System.out.println(l.getData(2));
		l.show();
		System.out.println("------------------");
		l.removeData(1);
		l.show();
		System.out.println("------------------");
		
		List la = new List();
		la.append(10);
		
		List lb = new List();
		lb.append(10); 
		lb.append(20);
		lb.append(30); 
		lb.append(40);
		lb.append(50);

		l.append1( lb );
		l.show();

		System.out.println("------------------");
		la.copyFrom(lb);
		la.show();
				
		lb.setData(1, 1000);
		System.out.println("------------------");
		la.show();
		
		System.out.println("------------------");
		l.showThis();
		System.out.println( l );
		
		List ordenada = l.sort();
	}
}
