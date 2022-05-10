
public class Punto
{
	public double x;
	public double y;
	
	public Punto()
	{
		x = 0.0;
		y = 0.0;
	}
	public Punto(double x1,double y1)
	{
		x = x1;
		y = y1;
	}
	public void show()
	{
		System.out.println("( " + x + " , " + y + " )");
	}
	public double distOrigen()
	{
		return Math.sqrt( x*x + y*y );
//		return Math.sqrt( Math.pow(x, 2) + Math.pow(y, 2) );
	}
	public double distTo( Punto p )
	{
		double dX = x - p.x;
		double dY = y - p.y;
		return Math.sqrt( dX*dX + dY*dY );
	}
}
