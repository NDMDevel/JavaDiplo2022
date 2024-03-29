
public class Punto
{
	public double x;
	public double y;
	
	public Punto()
	{
		x = 0.0;
		y = 0.0;
	}
	public Punto(double x,double y)
	{
		this.x = x;
		this.y = y;
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
	public boolean equals(Punto p)
	{
//		return ( x == p.x && y == p.y );
		if( x == p.x && y == p.y )
			return true;
		else
			return false;
	}
}
