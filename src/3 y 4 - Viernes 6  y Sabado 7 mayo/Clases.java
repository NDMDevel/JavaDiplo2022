
/*
Class Circulo
	ctor defecto
	ctor especializado
	metodos:
		setters/getters
		show: imprima en cosola las variables (attributos)
			  de la clase
		perimetro: 2.pi.r
		area: pi.r²
		
Class Punto
	ctor defecto
	ctor especializado
	metodos:
		setters/getters
		show: imprima en cosola las variables (attributos)
			  de la clase
		distOrigen: devuelve la distancia al origen
		distTo: devuelve distancia a otro punto
		
*/
public class Clases
{
	//funciones
	public static void main(String args[])
	{
//		Punto pa = new Punto(  0 , 1 );
//		Punto pb = new Punto(  0 , 2 );
//		System.out.println( pa.distOrigen() );
//		System.out.println( pb.distTo( pa ) );

		Circulo c1 = new Circulo();
		c1.centro.x = 2;
		c1.centro.y = 1;
		c1.setRadio(1);

		Circulo c2 = new Circulo();
		c2.centro.x = 4;
		c2.centro.y = 2;
		c2.setRadio(0.5);
		
		if( c1.intercepta( c2 ) )
			System.out.println("Se interceptan");
		else
			System.out.println("No se interceptan");
		
//		int x = 34;
		System.out.println( c1 );//c1.show();
		
	}
}
