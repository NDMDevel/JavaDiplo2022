
public class Main
{
	public static void main(String[] args)
	{
		List l = new List();
		
		l.append(10);
		l.append(-20);
		l.append(30);
		l.append(15);
		l.sort();
		l.show();
		
//		String str = l.saveToString();
		l.saveToFile("backup.dat");
		l.append(100);
		l.append(200);
		l.append(300);
		l.show();
		
//		l.loadFromString(str);
		l.loadFromFile("backup.dat");
		l.show();
	}

}
