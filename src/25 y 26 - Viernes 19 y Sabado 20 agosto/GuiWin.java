package jMainWindow;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Frame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;
import java.util.concurrent.PriorityBlockingQueue;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import com.mysql.cj.Query;

import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class GuiWin {

	private JFrame frame;
	private JTable table;
	//private String url;//  = String.format("jdbc:mysql://%s:%s/%s",ip,tcpPort,db_name);
//	private Connection sqlConn;// = DriverManager.getConnection(url,user,pass);
//	private Statement sqlSt;// 	 = conn.createStatement();
//	private ResultSet sqlResult;
	private String[]   colNamesCache;
	private String[][] dataCache;
	private JTextField txtHost;
	private JTextField txtPort;
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JTextField txtDB;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private CustomTableModel tableModel;
	private SqlThread sqlThread;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					GuiWin window = new GuiWin();
					window.frame.setVisible(true);
					System.out.println("gui thread:"+Thread.currentThread().getName());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		System.out.println("main thread: "+Thread.currentThread().getName());
	}

	/**
	 * Create the application.
	 */
	public GuiWin() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setBounds(100, 100, 440, 313);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 428, 119);
		frame.getContentPane().add(scrollPane);
		tableModel = new CustomTableModel();
		table = new JTable(tableModel);
		table.setBounds(25, 25, 250, 100);
		table.getTableHeader().setDefaultRenderer(new TableCellRenderer()
		{
			@Override
			public Component getTableCellRendererComponent(
								JTable table,
								Object value,
								boolean isSelected,
								boolean hasFocus,
								int row,
								int column)
			{
				// TODO Auto-generated method stub
				JLabel cell = new JLabel(value.toString());
				cell.setOpaque(true);
				if( column == 1 )
				{
					cell.setBackground(Color.YELLOW);
					cell.setForeground(Color.RED);
				}
				else
				{
					cell.setBackground(Color.BLACK);
					cell.setForeground(Color.WHITE);
				}
				cell.setFont(new Font("Serif",Font.BOLD|Font.ITALIC,14));
				
				return cell;
			}
		});
		table.setDefaultRenderer(String.class, new TableCellRenderer()
		{
			@Override
			public Component getTableCellRendererComponent(
								JTable table,
								Object value,
								boolean isSelected,
								boolean hasFocus,
								int row,
								int column)
			{
				JLabel cell = new JLabel(value.toString());
				cell.setOpaque(true);
				if( !isSelected)
				{
					cell.setForeground(Color.BLACK);
					if( row % 2 == 0 )
						cell.setBackground(Color.LIGHT_GRAY);
				}
				else
				{
					cell.setForeground(Color.WHITE);
					cell.setBackground(new Color(0,0,255,100));
					if( hasFocus )
						cell.setBorder(BorderFactory.createLineBorder(Color.RED,2));
				}
				return cell;
			}
		});

		scrollPane.setViewportView(table);
		
		JLabel lblHost = new JLabel("Host");
		lblHost.setBounds(10, 131, 70, 15);
		frame.getContentPane().add(lblHost);
		
		JLabel lblTcpport = new JLabel("TcpPort");
		lblTcpport.setBounds(10, 159, 70, 15);
		frame.getContentPane().add(lblTcpport);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 186, 70, 15);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(10, 213, 70, 15);
		frame.getContentPane().add(lblPass);
		
		JLabel lblDatabase = new JLabel("DataBase");
		lblDatabase.setBounds(10, 236, 70, 15);
		frame.getContentPane().add(lblDatabase);
		
		txtHost = new JTextField();
		txtHost.setText("localhost");
		txtHost.setBounds(98, 131, 114, 19);
		frame.getContentPane().add(txtHost);
		txtHost.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("3306");
		txtPort.setBounds(98, 157, 114, 19);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setText("damian");
		txtUser.setBounds(98, 184, 114, 19);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(98, 211, 114, 19);
		frame.getContentPane().add(txtPass);
		
		txtDB = new JTextField();
		txtDB.setText("diplo_db");
		txtDB.setBounds(98, 234, 114, 19);
		frame.getContentPane().add(txtDB);
		txtDB.setColumns(10);
		
		JButton btnConnect = new JButton("Connect");
		btnConnect.setAction(action);
		btnConnect.setBounds(263, 126, 117, 25);
		frame.getContentPane().add(btnConnect);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setAction(action_1);
		btnUpdate.setBounds(263, 181, 117, 25);
		frame.getContentPane().add(btnUpdate);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Connect");
			putValue(SHORT_DESCRIPTION, "Try connection to DB");
		}
		public void actionPerformed(ActionEvent e)
		{
			System.out.println(String.format("%s:%s/%s (user: %s)",txtHost.getText(),txtPort.getText(),txtDB.getText(),txtUser.getText()));
			sqlThread = new SqlThread(txtHost.getText(),
									  txtPort.getText(),
									  txtDB.getText(),
									  txtUser.getText(),
									  new String(txtPass.getPassword()));
			sqlThread.start();//aqui inicia conexion ("lenta") con el
							  //server de base de datos (en paralelo)
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Update");
			putValue(SHORT_DESCRIPTION, "Execute sql Query");
		}
		public void actionPerformed(ActionEvent e)
		{
			sqlThread.appendQuery("SELECT * FROM meteo;",new Callback(){
				@Override
				public void execute()
				{
					try
					{
						ResultSet sqlResult = sqlThread.getResultSet();
						int colCount = sqlResult.getMetaData().getColumnCount();
						//aloco memoria para colNamesCache (ahora que se cuantas columnas tengo)
						colNamesCache = new String[colCount];
						//cargo los header names de la tabla
						for( int col=1 ; col<=colCount ; col++ )
							colNamesCache[col-1] = sqlResult.getMetaData().getColumnName(col);
						
						//cargo los datos (filas y col)
						int rowCount = 0;
						while( sqlResult.next() )
							rowCount++;
//							for( int col=1 ; col<=colCount ; col++ )
//								System.out.print(result.getString(col) + "\t");
//							System.out.println();
						
						//ahora aloco dataCache
						dataCache = new String[rowCount][colCount];
						sqlResult.beforeFirst();
						
//						for( int row=0 ; row<rowCount ; row++ )
//						{
//							sqlResult.next();
//							for( int col=1 ; col<=colCount ; col++ )
//								dataCache[row][col-1] =  sqlResult.getString(col);
//						}
						int row = 0;
						while( sqlResult.next() )
						{
							for( int col=1 ; col<=colCount ; col++ )
							{
								dataCache[row][col-1] =  sqlResult.getString(col);
								System.out.println(dataCache[row][col-1]);
							}
							row++;
						}
//						tableModel.fireTableStructureChanged();
						((AbstractTableModel)table.getModel()).fireTableStructureChanged();

					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
					
				}
			});
/*			try
			{
				sqlResult = sqlSt.executeQuery("SELECT * FROM meteo;");
				int colCount = sqlResult.getMetaData().getColumnCount();
				
				//aloco memoria para colNamesCache (ahora que se cuantas columnas tengo)
				colNamesCache = new String[colCount];
				//cargo los header names de la tabla
				for( int col=1 ; col<=colCount ; col++ )
					colNamesCache[col-1] = sqlResult.getMetaData().getColumnName(col);
				
				//cargo los datos (filas y col)
				int rowCount = 0;
				while( sqlResult.next() )
					rowCount++;
//					for( int col=1 ; col<=colCount ; col++ )
//						System.out.print(result.getString(col) + "\t");
//					System.out.println();
				
				//ahora aloco dataCache
				dataCache = new String[rowCount][colCount];
				sqlResult.beforeFirst();
				
//				for( int row=0 ; row<rowCount ; row++ )
//				{
//					sqlResult.next();
//					for( int col=1 ; col<=colCount ; col++ )
//						dataCache[row][col-1] =  sqlResult.getString(col);
//				}
				int row = 0;
				while( sqlResult.next() )
				{
					for( int col=1 ; col<=colCount ; col++ )
					{
						dataCache[row][col-1] =  sqlResult.getString(col);
						System.out.println(dataCache[row][col-1]);
					}
					row++;
				}
//				tableModel.fireTableStructureChanged();
				((AbstractTableModel)table.getModel()).fireTableStructureChanged();
			}
			catch (SQLException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}*/
		}
	}
	private class CustomTableModel extends AbstractTableModel
	{
		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex)
		{
			System.out.println(String.format("setValueAt(%s,%d,%d)",
											 aValue.toString(),
											 rowIndex,
											 columnIndex));
			dataCache[rowIndex][columnIndex] = aValue.toString();
		}
		//me quedo con la implementacion por defecto que hace AbstractTableModel
		//@Override
		//public void removeTableModelListener(TableModelListener l)
		//{
		//	//????
		//}
		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex)
		{
			if( columnIndex == 0 )
				return false;
			return true;
		}
		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			return dataCache[rowIndex][columnIndex];
		}
		@Override
		public int getRowCount() {
			if( dataCache == null )
				return 0;
			return dataCache.length;
		}
		@Override
		public String getColumnName(int columnIndex) {
			return colNamesCache[columnIndex];
		}
		@Override
		public int getColumnCount() {
			if( dataCache == null )
				return 0;
			int colCount = colNamesCache.length;
			return colCount;
		}
		@Override
		public Class<?> getColumnClass(int columnIndex) {
			// TODO Auto-generated method stub
//			if( columnIndex == 0 )
//				return Integer.class;
			return String.class;
		}
		//dejo la implementacion por defecto
		//@Override
		//public void addTableModelListener(TableModelListener l)
		//{
		//	//????
		//}
	}
}