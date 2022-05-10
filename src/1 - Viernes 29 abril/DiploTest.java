import java.util.Scanner;

public class DiploTest
{
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);
		int vec[] = new int[3];
		//for( int idx=0 ; idx<3 ; idx++ )
		int idx = 0;
		while( idx < vec.length )
		{
			System.out.print("Ingrese vec[" + idx + "] = ");
			vec[idx] = scan.nextInt();
			idx++;
		}
		scan.close();
		idx = 0;
		while( idx < vec.length )
		{
			System.out.println("vec[" + idx + "] = " + vec[idx]);
			idx++;
		}
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
