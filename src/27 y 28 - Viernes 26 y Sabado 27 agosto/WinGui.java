package jSqlViewer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.markers.SeriesMarkers;

import javax.swing.JPanel;

public class WinGui {
	
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
	private JTextField txtQuery;
	private JButton btnUpdate;
	private JButton btnExecute;
	private JButton btnConnect;
	private final Action action_2 = new SwingAction_2();
	private final Action action_3 = new SwingAction_3();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					WinGui window = new WinGui();
					window.frame.setVisible(true);
//					Thread.currentThread().setName("SWING Thread");
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
	public WinGui() {
		initialize();
	}

	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setBounds(100, 100, 447, 461);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 0, 428, 204);
		frame.getContentPane().add(scrollPane);
		tableModel = new CustomTableModel();
		table = new JTable(tableModel);
		
		table.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {}
			@Override
			public void mousePressed(MouseEvent e) {}
			@Override
			public void mouseExited(MouseEvent e) {}
			@Override
			public void mouseEntered(MouseEvent e) {}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if( SwingUtilities.isRightMouseButton(e) )
				{
					JPopupMenu menu = new JPopupMenu();
					menu.add("Set as X");
					menu.add("Set as Y");
					JMenuItem optionPlot = new JMenuItem("Plot");
					optionPlot.addActionListener(new SwingAction_3());
//					optionPlot.addActionListener(new ActionListener() {
//						@Override
//						public void actionPerformed(ActionEvent e) {
//							
//						}
//					});
					menu.add(optionPlot);
					
//					menu.setVisible(true);
					menu.show(e.getComponent(),e.getX(),e.getY());

				}
					
//				if( e.getButton() == MouseEvent.BUTTON1);
			}
		});
		table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		table.setCellSelectionEnabled(true);
		
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
		lblHost.setBounds(10, 221, 70, 15);
		frame.getContentPane().add(lblHost);
		
		JLabel lblTcpport = new JLabel("TcpPort");
		lblTcpport.setBounds(10, 249, 70, 15);
		frame.getContentPane().add(lblTcpport);
		
		JLabel lblUser = new JLabel("User");
		lblUser.setBounds(10, 276, 70, 15);
		frame.getContentPane().add(lblUser);
		
		JLabel lblPass = new JLabel("Pass");
		lblPass.setBounds(10, 303, 70, 15);
		frame.getContentPane().add(lblPass);
		
		JLabel lblDatabase = new JLabel("DataBase");
		lblDatabase.setBounds(10, 326, 70, 15);
		frame.getContentPane().add(lblDatabase);
		
		txtHost = new JTextField();
		txtHost.setText("localhost");
		txtHost.setBounds(98, 221, 114, 19);
		frame.getContentPane().add(txtHost);
		txtHost.setColumns(10);
		
		txtPort = new JTextField();
		txtPort.setText("3306");
		txtPort.setBounds(98, 247, 114, 19);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		
		txtUser = new JTextField();
		txtUser.setText("damian");
		txtUser.setBounds(98, 274, 114, 19);
		frame.getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(98, 301, 114, 19);
		frame.getContentPane().add(txtPass);
		
		txtDB = new JTextField();
		txtDB.setText("diplo_db");
		txtDB.setBounds(98, 324, 114, 19);
		frame.getContentPane().add(txtDB);
		txtDB.setColumns(10);
		
		btnConnect = new JButton("Connect");
		btnConnect.setAction(action);
		btnConnect.setBounds(263, 216, 117, 25);
		frame.getContentPane().add(btnConnect);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setAction(action_1);

		btnUpdate.setBounds(263, 271, 117, 25);
		frame.getContentPane().add(btnUpdate);
		btnUpdate.setEnabled(false);
		
		txtQuery = new JTextField();
		txtQuery.setEnabled(false);
		txtQuery.setBounds(10, 379, 370, 33);
		frame.getContentPane().add(txtQuery);
		txtQuery.setColumns(10);
		
		JLabel lblSqlQuery = new JLabel("Sql Query:");
		lblSqlQuery.setBounds(10, 353, 106, 15);
		frame.getContentPane().add(lblSqlQuery);
		
		btnExecute = new JButton("New button");
		btnExecute.setAction(action_2);
		btnExecute.setBounds(394, 375, 34, 33);
		frame.getContentPane().add(btnExecute);
		btnExecute.setEnabled(false);
		
		JButton btnPlot = new JButton("Plot");
		btnPlot.setAction(action_3);
		btnPlot.setBounds(263, 303, 117, 25);
		frame.getContentPane().add(btnPlot);
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
			sqlThread.setOnConnectCallback(new Callback()
			{
				@Override
				public void execute()
				{
					if( sqlThread.isConnected() )
					{
						JOptionPane.showMessageDialog(null,"The DB is OK");
						txtQuery.setEnabled(true);
						btnExecute.setEnabled(true);
						btnUpdate.setEnabled(true);
						System.out.println("The DB is ok");
						btnUpdate.getAction().actionPerformed(null);
					}
					else
					{
						JOptionPane.showMessageDialog(null,"The DB is NOK");
						sqlThread.interrupt();
						System.out.println("The DB is not ok");
						btnConnect.setEnabled(true);
					}
				}
			});
			btnConnect.setEnabled(false);
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
			System.out.println("update action: " + Thread.currentThread().getName());
			sqlThread.appendQuery("SELECT * FROM meteo;",new Callback(){
				@Override
				public void execute()
				{
					System.out.println("execute thread: " + Thread.currentThread().getName());
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
						EventQueue.invokeLater
						(
							new Runnable()
							{
								@Override
								public void run()
								{
									System.out.println("invokeLater (jtable Update): " + Thread.currentThread().getName());
									((AbstractTableModel)table.getModel()).fireTableStructureChanged();
								}
							}
						);
					}
					catch (SQLException e)
					{
						e.printStackTrace();
					}
					
				}
			});
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Execute");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e)
		{
			//SHOW TABLES;
			//INSERT INTO xxx
			sqlThread.appendQuery(txtQuery.getText(),new Callback()
			{
				@Override
				public void execute()
				{
					ResultSet result = sqlThread.getResultSet();
					if( result != null )
					{
						//la muestro en la tabla
						try
						{
							updateTable(result);
						}
						catch (SQLException e)
						{
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
	private void updateTable(ResultSet sqlResult) throws SQLException
	{
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
		
		//ahora aloco dataCache
		dataCache = new String[rowCount][colCount];
		sqlResult.beforeFirst();
		
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
//		tableModel.fireTableStructureChanged();
		EventQueue.invokeLater
		(
			new Runnable()
			{
				@Override
				public void run()
				{
					//System.out.println("invokeLater (jtable Update): " + Thread.currentThread().getName());
					((AbstractTableModel)table.getModel()).fireTableStructureChanged();
				}
			}
		);
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
			var data = dataCache[rowIndex][columnIndex];
			if( data == null )
				return "";
			return data;
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
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Plot");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			int[] rows = table.getSelectedRows();
			int[] cols = table.getSelectedColumns();
			System.out.println("Rows Selected:");
			System.out.println(rows.length);
			for( var row : rows )
				System.out.println(row);

			System.out.println("Cols Selected:");
			System.out.println(cols.length);
			for( var col : cols )
				System.out.println(col);
			
			if( rows.length != 0 && cols.length != 0 )
			{
				String[] colNames = new String[cols.length];
				for( int i=0 ; i<colNames.length ; i++ )
					colNames[i] = colNamesCache[cols[i]];
				XYThread xyThread = new XYThread(dataCache,rows,cols,colNames);
				xyThread.start();
			}
		}
	}
}
