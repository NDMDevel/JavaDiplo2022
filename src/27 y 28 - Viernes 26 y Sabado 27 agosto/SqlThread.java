package jSqlViewer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlThread extends Thread
{
	private String host;
	private String port;
	private String dbName;
	private String user;
	private String pass;
	private Connection sqlConn;
	private Statement  sqlSt;
	private String query;	//Vector<String> queueQuery;
	private Callback callback;
	private Callback onConnect;
	private boolean dbConnected;
	private boolean running;
	private ResultSet sqlResult;
	
	public SqlThread(String host, String port, String dbName, String user, String pass)
	{
		Thread.currentThread().setName("SqlThread");
		this.host = host;
		this.port = port;
		this.dbName = dbName;
		this.user = user;
		this.pass = pass;
		running = false;
	}
	public void appendQuery(String query,
							Callback callback)
	{
		this.query = query;
		this.callback = callback;
		interrupt();
	}
	public void stopThread()
	{
		running = false;
		interrupt();
	}
	public ResultSet getResultSet()
	{
		var aux = sqlResult;
		sqlResult = null;
		return aux;
	}
	public boolean isConnected()
	{
		return dbConnected;
	}
	public void setOnConnectCallback(Callback onConnect)
	{
		this.onConnect = onConnect;
	}
	@Override
	public void run()
	{
		//inicio conexion con base de datos
		try
		{
			String url = String.format("jdbc:mysql://%s:%s/%s",host,port,dbName);
			sqlConn = DriverManager.getConnection(url,user,pass);
			sqlSt   = sqlConn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					  ResultSet.CONCUR_READ_ONLY);
			dbConnected = true;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			dbConnected = false;
		}
		if( onConnect != null )
			onConnect.execute();
		
		//bucle en que ejecuto las querys
		running = true;
		while( running )
		{
			if( query != null )
			{
				try
				{
					boolean hasResult = sqlSt.execute(query);
					System.out.println("base de datos consultada: " + query);
					if( hasResult )
					{
						sqlResult = sqlSt.getResultSet();
						
						//------------------------------------------------------
						//--------- DEBUG muestro datos en cosola --------------
						//------------------------------------------------------
						//imprimo headers
						int colCount = sqlResult.getMetaData().getColumnCount();
						for( int col=1 ; col<=colCount ; col++ )
						{
							var colName = sqlResult.getMetaData().getColumnName(col);
							var colType = sqlResult.getMetaData().getColumnTypeName(col);
							System.out.print(String.format("%s(%s)\t",colName,colType));
						}
						System.out.println();

						//imprimo datos
						while( sqlResult.next() )
						{
							for( int col=1 ; col<=colCount ; col++ )
								System.out.print(sqlResult.getString(col) + "\t");
							System.out.println();
						}
						sqlResult.beforeFirst();
					}
					if( callback != null )
						callback.execute();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				query = null;
			}
			try
			{
				//1000 segs
				System.out.println("sqlThread sleeping");
				sleep(1000000);
			}
			catch(InterruptedException e)
			{
				//e.printStackTrace();
//				if( getState() == Thread.State.RUNNABLE )
				System.out.println(getState());
				System.out.println("sqlThread awake!!");
			}
		}
		
		//cierro conexion con db
		try
		{
			sqlConn.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
};

/*
public class SqlThread extends Thread
{
//	private String host;
//	private String user;
//	private String pass;
	private Callback callback;
	public SqlThread(Callback callback)
	{
		this.callback = callback;
	}
	@Override
	public void run()
	{
		int cnt = 0;
		while( true )
		{
			System.out.println("cnt: " + cnt);
			cnt++;
			if( cnt == 10 )
			{
				//avisar a la hebra madre
				callback.execute();
			}
			try
			{
				sleep(500);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
	}
}
*/
