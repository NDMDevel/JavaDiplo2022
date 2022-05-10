import java.util.Scanner;

public class DiploTest
{
	public static void showVec(int vec[])
	{
		for (int i = 0; i < vec.length; i++)
			System.out.println("[" + i + "] = " + vec[i]);
	}
	public static int suma(int a,int b)
	{
		int res = a + b;
		return res;
	}
	public static int[] sumaVec(int a[],int b[])
	{
		int res[] = new int[a.length];
		for (int i = 0; i < res.length; i++)
		{
			res[i] = a[i] + b[i];
		}
		return res;
	}
	public static void main(String args[])
	{
		int [] vec = {10,20,30};	//vec[0], vec[1], vec[2]
		System.out.println("vec:");
		showVec(vec);
		int other[] = {3,2,1};	//vec[0], vec[1], vec[2]
		System.out.println("other:");
		showVec(other);
		
		int x = suma(3,2);
		System.out.println("x = " + x);
		int s[] = sumaVec(vec,other);
		showVec(s);
		/*
		//eliminar vec[1]
		int vecTemp[] = new int[5];
		for (int i = 0; i < vecTemp.length; i++)
			vecTemp[i] = -1;
		vecTemp[0] = vec[0];
		vecTemp[4] = vec[2];
		vec = vecTemp;
		for (int i = 0; i < vec.length; i++)
			System.out.println("vec[" + i + "] = " + vec[i]);
		*/
		
		/*
		//esto es un comentario
		boolean varBool = false;
		byte varByte = 55;	//8 bits
		short varShort;	//16 bits
		int varInt = 34;		//32 bits
		long varLong;	//64 bits
		char varChar = '#';	//16 bits
		float varFloat = 3.14f;		//32 bits
		double varDouble = 3.14d;	//64 bits
		
		int test = (int) varFloat;
		boolean testB = test != 3;
		if( !testB || varInt>5 )
		{
			int x = 3;
			System.out.println("test es 3");
		}
		else
		{
			double v = 234.36;
			System.out.println("test no es 3");			
		}
//		System.out.println(test);
//		System.out.println("el valor de varByte es: " + varByte);
		
		Scanner scan = new Scanner(System.in);

		System.out.print("Ingrese un int:");
		int var = scan.nextInt();
		System.out.println("El int ingresado es: " + var);
		
		int vec[];
		vec = new int[30];
		vec = new int[40];
//		for( int idx=0 ; idx<3 ; idx=idx+1 )
//		for( int idx=0 ; idx<3 ; idx++ )
		for( int idx=0 ; idx<vec.length ; idx+=1 )
		{
			System.out.println("vec[" + idx + "] = " + vec[idx]);
		}
		scan.close();
		*/
	}
}
