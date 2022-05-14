
public class Clases
{
	public static void main(String args[])
	{
		Punto pa = new Punto(  0 , 1 );
		Punto pb = new Punto(  0 , 2 );
		Punto pc = null; 
		System.out.println( pa );
		System.out.println( pb );
		System.out.println( pc );
		pc = new Punto();
		System.out.println( pc );
		if( pa.equals(pb) )//if( pa == pb )
			System.out.println("son iguales");
		else
			System.out.println("no son iguales");
			
		Circulo c1 = new Circulo(10);
		c1.centro.x = 34;
		c1.centro.y = 12.3;
		Circulo c2 = new Circulo(10);
		c2.centro.x = 34;
		c2.centro.y = 12.3;
		if( c1.equals(c2) )
			System.out.println("son iguales");
		else
			System.out.println("no son iguales");
		
		//int[] vec = new int[8];
		//vec = new int[15];
		
		
//		VectorDinamico vector1 = new VectorDinamico();
		VectorDinamico vector = new VectorDinamico( 8 );
		vector.show();
		for( int i=0 ; i<vector.length() ; i++ )
			vector.setItem(i,0);
		vector.setItem( 0 , -3 );
		vector.setItem( 1 ,  2 );
		vector.setItem( 2 ,  7 );
		vector.setItem( 3 , -8 );
		vector.setItem( 4 ,  3 );
		vector.setItem( 5 ,  4 );
		vector.setItem( 6 , 10 );
		vector.setItem( 7 , 12 );
		int val = vector.getItem( 3 );
		vector.sortVector();
//		vector.sortVector(true);
		System.out.println("Vector ordenado asc:");
		vector.show();
		vector.sortVector(false);
		System.out.println("Vector ordenado desc:");
		vector.show();
		
		System.out.println( vector.getItem(0) );
		System.out.println( vector.getItem(7) );
		
		//ahora tenga 15 elementos (ya no 8)
		vector.show();
		vector.resize(8);
		vector.show();
		
		vector.removeItem(2);
		vector.show();
		
		vector.addItem(2,-55);
		vector.show();
//		System.out.println( vector );
	}
}
