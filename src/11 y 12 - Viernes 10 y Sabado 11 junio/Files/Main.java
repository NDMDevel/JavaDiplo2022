import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main
{
	public static void main(String[] args) throws Exception
	{
		RandomAccessFile file;
		file = new RandomAccessFile("file_nuevo.txt","rw");
		int x = 34;
		String str = "";
		str = str.format("el valor de x es %d",x);
		file.writeBytes(str);

		file.seek(6);	//desplaza el pointer del archivo
		System.out.println(file.readLine());
		System.out.println(file.getFilePointer());	
//			file.seek( file.getFilePointer() - 7 );
		
//			file.setLength(15);	//define la longuitud del archivo
		//System.out.println(file.readLine());

//			System.out.println(file.readLine());
//			file.seek( file.length() - 1 );
//			file.writeBytes("hello file!!\n");
//			file.writeBytes("otro texto");
		file.close();
	}
}
