package jMainWIndow;

import java.awt.GraphicsEnvironment;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;

import org.math.plot.Plot2DPanel;

public class Main {

	public static void main(String[] args) throws SQLException
	{
		String ip 		= args[0];
		String tcpPort  = args[1];
		String user 	= args[2];
		String pass 	= args[3];
		String db_name	= args[4];
		String url  = String.format("jdbc:mysql://%s:%s/%s",ip,tcpPort,db_name);
		Connection conn  = DriverManager.getConnection(url,user,pass);
		Statement st 	 = conn.createStatement();

		Connection sqlConn = DriverManager.getConnection(url,user,pass);
		
		
		for( var font : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames() )
			System.out.println(font);
        // TODO Auto-generated method stub
		Sub   sub = new Sub();
		Super sup = new Super();
//		sub.method();
//		sup.method();
		
		Super[] vec = {new Sub(),new Super(),new Sub()};
		for( int i=0 ; i<vec.length ; i++ )
			vec[i].method();
//		for( var item : vec )
//			item.method();
		print(vec[0]);
		Other other = new Other();
		print(other);
		print(new Super()
		{
			@Override
			public void method()
			{
				System.out.println("que se yo!!");
			}
		});
		
		some(new MyInterface()
		{
			@Override
			public void show() {
				// TODO Auto-generated method stub				
			}
			@Override
			public void sys() {
				// TODO Auto-generated method stub				
			}
		});
		
	}
	public static void some(MyInterface inter)
	{
		inter.show();
		inter.sys();
	}
	public static void print(Super s)
	{
		s.method();
	}
}
