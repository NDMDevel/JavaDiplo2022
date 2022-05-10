
public class Rectangulo
{
	//atributos
	private double L1;
	private double L2;

	//contructor (ctor)
	//defautl ctor (contructor por defecto)
	public Rectangulo()
	{
		System.out.println("ctor Rectangulo()");
		L1 = 0.0;
		L2 = 0.0;
	}
	//constructor especializado
	public Rectangulo(double l1,double l2)
	{
		System.out.println("ctor Rectangulo(double,double)");
		setL1(l1);//L1 = l1;
		setL2(l2);//L2 = l2;
	}
	public Rectangulo(double l)
	{
		System.out.println("ctor Rectangulo(double)");
		setL1(l);//L1 = l1;
		setL2(l);//L2 = l2;
	}
	public double perimetro()
	{
		return L1*2.0 + L2*2.0;
	}
	public double area()
	{
		return L1*L2;
	}
	public void show()
	{
		System.out.println("L1: " + L1);
		System.out.println("L2: " + L2);
	}
	public void setL1(double l)
	{
		if( l < 0.0 )
			l = 0.0;
		L1 = l;
	}
	public void setL2(double l)
	{
		if( l < 0.0 )
			l = 0.0;
		L2 = l;
	}
	public double getL1()
	{
		return L1;
	}
	
}
