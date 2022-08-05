import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class Main {

	public static void main(String[] args)
	{
		List<Double> list = new List<>();
		list.append(100.0);
		list.append(-20.0);
		list.append(30.0);
//		list.show();
		Iterator<Double> iter = list.iterator();
		while( iter.hasNext() )
			System.out.println(iter.next());
//		for( var item : list )
//			System.out.println(item);
		
		
//		ArrayList<Integer> array = new ArrayList<>();
		LinkedList<Integer> array = new LinkedList<>();
		array.add(50);
		array.add(60);
		array.add(70);
		array.add(80);
		array.remove(1);
		System.out.println(array.get(2));
		int N = array.size();
		
		System.out.println("-----------------------");
//		for( int i=0 ; i<array.size(); i++ )
//			System.out.println(array.get(i));
		for( Integer item : array )
			System.out.println(item);

		System.out.println("-----------------------");
		array.clear();
		System.out.println(array.size());
	}
}
