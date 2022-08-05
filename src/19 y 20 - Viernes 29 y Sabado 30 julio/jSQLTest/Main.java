package jSQLTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.Locale;

public class Main
{
	public static void main(String[] args) throws SQLException, ClassNotFoundException
	{
		//		0  1  	   2 	3	 4
		//args: ip tcpPort user pass db
		Class.forName("com.mysql.cj.jdbc.Driver");//("com.mysql.jdbc.Driver");
		String ip 		= args[0];
		String tcpPort  = args[1];
		String user 	= args[2];
		String pass 	= args[3];
		String db_name	= args[4];
		String url  = String.format("jdbc:mysql://%s:%s/%s",ip,tcpPort,db_name);
		//String url = "jdbc:mysql://localhost:3307/diplo_db";
		Connection conn  = DriverManager.getConnection(url,user,pass);
		Statement st 	 = conn.createStatement();
		
		//inserto datos en la tabla
		double temp = -502.3;
		double hum	= 180.6;
		double pre	= 303.58;

		System.out.println(String.format(Locale.US,"INSERT INTO meteo (Temp,Hum,Presion) VALUES (%f,%f,%f);",temp,hum,pre));
//		st.execute(String.format("INSERT INTO meteo (Temp,Hum,Presion) VALUES (%f,%f,%f);",temp,hum,pre));
//		st.execute(String.format("INSERT INTO meteo (Temp,Hum,Presion) VALUES (10.2,22.3,25.8);"));
		
		//leo todos los datos de la tabla "meteo"
		ResultSet result = st.executeQuery("SELECT * FROM meteo;");
		int colCount = result.getMetaData().getColumnCount();
		
		//imprimo headers
		for( int col=1 ; col<=colCount ; col++ )
		{
			var colName = result.getMetaData().getColumnName(col);
			var colType = result.getMetaData().getColumnTypeName(col);
			System.out.print(String.format("%s(%s)\t",colName,colType));
//			System.out.print(colName+"("+colType+")"+"\t");
		}
		System.out.println();

		//imprimo datos
		while( result.next() )
		{
			for( int col=1 ; col<=colCount ; col++ )
				System.out.print(result.getString(col) + "\t");
			System.out.println();
		}
		conn.close();
	}
}
