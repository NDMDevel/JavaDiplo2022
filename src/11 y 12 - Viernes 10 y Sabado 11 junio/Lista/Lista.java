

public class Lista
{
	public static void main(String[] args)
	{
		List L = new List();
		L.append(10);
		L.append(33);
		L.append(24);
		L.append(155);
		L.append(2353);
		L.saveToFile("c:\\users\\lista.txt");
		String backup = L.saveToString();
		System.out.println(backup);
		L.append(0);
		L.append(0);
		L.append(0);
		L.append(0);
		L.append(0);
		L.removeData(3);
		L.show();
		System.out.println("-----------");
		L.loadFromString(backup);
		L.show();
		System.out.println("-----------");
		
		
		SubClass sub = new SubClass();
		sub.saveToString();
		sub.loadFromString("algo");
		
		Pila pila = new Pila();
		pila.push(33);
		pila.push(12);
		pila.push(7);
		pila.show();
		int val = pila.pop();
		System.out.println( val );
		val = pila.pop();
		System.out.println( val );
		System.out.println("-----------");
		
//		SubClass sub = new SubClass();
//		sub.setX(330);
//		sub.showX();
//		boolean b = sub.isGreaterThan(234);
//		SuperClass sup = new SuperClass();
//		sup.setX(77);
//		sup.showX();
		System.out.println("-----------");
		//------------------------
		List l = new List();
		l.append(3); 
		l.append(32);
		l.append(75);
//		String x = l.saveToString();
		
//		l.loadFromString( x );
		System.out.println(l);
		System.out.println("------------------------");
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
