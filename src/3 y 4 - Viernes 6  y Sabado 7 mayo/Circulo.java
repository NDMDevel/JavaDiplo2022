
public class Circulo
{
	private double radio;
	public  Punto  centro;

	public Circulo()
	{
		radio = 0.0;
		centro = new Punto();
	}
	public Circulo(double r)
	{
		setRadio(r);
		centro = new Punto();
	}
	public double getRadio()
	{
		return radio;
	}
	public void setRadio(double r)
	{
		if( r < 0.0 )
			r = 0.0;
		radio = r;
	}
	public void show()
	{
		System.out.println("rario: " + radio);
	}
	public double permietro()
	{
		return 2.0*Math.PI*radio;
	}
	public double area()
	{
		return Math.PI * Math.pow(radio, 2.0);
	}
	public boolean intercepta( Circulo c )
	{
		//codigo/algoritmo
		double dist = centro.distTo( c.centro );
		if( dist > radio + c.radio )
			return false;
		else
			return true;
	}
	public String toString()
	{
		return "circulo de prueba";
	}
}
