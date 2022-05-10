
public class StaticTest
{
	private int A;
	private int B;
	//ctor
	//1- Nombre identico a la clase
	//2- Sin valor de retorno (void)
	//3- Public*
	public StaticTest( int a, int b )
	{
		//inicializacion de las variables/recursos
		A = a;
		B = b;
	}
	
	
	public Rectangulo rect;
	//no static -> pertenece a la instancia
	//   static -> pertenece (tambien) a la clase
	public int nonStatic;
	public static int Static;
	
	public static int count;
	public StaticTest()
	{
		rect = new Rectangulo(1.2,3.3);
		count++;
	}

	public void nonStaticMethod()
	{
		System.out.println("nonStaticMethod()");
	}
	
	public static void staticMethod()
	{
		System.out.println("staticMethod()");
	}
}
