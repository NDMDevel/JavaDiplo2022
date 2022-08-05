import java.util.ArrayList;
import java.util.LinkedList;

public class Main
{
	public static void main(String[] args)
	{
		SubTest<ClassOk> test = new SubTest<>();
		VectorDinamico<String> vec = new VectorDinamico<>(3);
		vec.setData(0,"hello 1");
		vec.setData(1,"hello 2");
		vec.setData(2,"hello 3");
		vec.show();
		System.out.println("--------- 1 ----------");
		
		Printer<VectorDinamico<String>> printVec = new Printer<>(vec);
		printVec.show();
		
		System.out.println("-------- 2 -----------");
		
		print(vec); 
		
		System.out.println("--------- 3 ----------");
		
		VectorDinamico<Integer> vec1 = new VectorDinamico<>(3);
		vec1.setData(0,100);
		vec1.setData(1,200);
		vec1.setData(2,300);
		printVectorDinamico(vec1);
		sort(vec1);
		printVectorDinamico(vec);
		sort(vec);
		//printVectorDinamico(10);
		
		System.out.println("---------- 4 ---------");
		
		VectorDinamico<Punto> pvec = new VectorDinamico<>(2);
		pvec.setData(0,new Punto( 10, 20));
		pvec.setData(1,new Punto(100,200));
		printVectorDinamico(pvec);
//		sort(pvec);
		
		System.out.println("---------- 5 ---------");
		//List lista = new List();
		Printer<Integer> print = new Printer<Integer>(10);
		print.show();
		Printer<Double> printD = new Printer<Double>(12.5);
		printD.show();
		
		//int,double,float
		//Wrapped types: Integer(int), Double(double)
//		print(20,"cad1",10);//Integer String Integer
//		print("Hello","cad2","cad");//String String String
//		print(12.3f,"cad3",2.3);//Float String Double
//		print(-150,"otra cosa",5260);//Integer String Integer
	}
//	public static <T> T div(T num,T den)
//	{
//		return num.div(den);
//	}
	//Wild Card: ?
	public static void printVectorDinamico(VectorDinamico<?> vec)
	{
		vec.show();
	}
	public static void sort(VectorDinamico<? extends Comparable<?>> vec)
	{
		//bubble sort....
	}
	public static <T> void print(T val)
	{
		System.out.println(val);
	}
/*	public static void print(Integer val,String str,Integer arg)
	{
		System.out.println(val + str + arg);
	}
	public static void print(String val,String str,String arg)
	{
		System.out.println(val + str + arg);
	}
	public static void print(Float val,String str,Double arg)
	{
		System.out.println(val + str + arg);
	}*/
}
