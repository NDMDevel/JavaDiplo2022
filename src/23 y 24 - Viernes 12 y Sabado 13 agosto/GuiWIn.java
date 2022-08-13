package jMainWIndow;

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

public class GuiWIn {

	private JFrame frame;
	private JTable table;
	String ip 		;
	String tcpPort  ;
	String user 	;
	String pass 	;
	String db_name	;
	String url;//  = String.format("jdbc:mysql://%s:%s/%s",ip,tcpPort,db_name);
	Statement st;// 	 = conn.createStatement();
	Connection sqlConn;// = DriverManager.getConnection(url,user,pass);
	ResultSet result;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run() {
				try {
					GuiWIn window = new GuiWIn();
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
	public GuiWIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(250, 250));
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		table = new JTable(new AbstractTableModel() {
			@Override
			public void setValueAt(Object aValue, int rowIndex, int columnIndex)
			{
				System.out.println(String.format("setValueAt(%s,%d,%d)",
												 aValue.toString(),
												 rowIndex,
												 columnIndex));
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
			public Object getValueAt(int rowIndex, int columnIndex)
			{
				// TODO Auto-generated method stub
				return rowIndex + " " + columnIndex;
			}
			@Override
			public int getRowCount() {
				// TODO Auto-generated method stub
				return 6;
			}
			@Override
			public String getColumnName(int columnIndex) {
				// TODO Auto-generated method stub
				if( columnIndex == 0 )
					return "Id";
				if( columnIndex == 1 )
					return "Temp";
				if( columnIndex == 2 )
					return "Hum";
				if( columnIndex == 3 )
					return "Presion";
				return "";
			}
			@Override
			public int getColumnCount() {
				// TODO Auto-generated method stub
				return 4;
			}
			@Override
			public Class<?> getColumnClass(int columnIndex) {
				// TODO Auto-generated method stub
//				if( columnIndex == 0 )
//					return Integer.class;
				return String.class;
			}
			//dejo la implementacion por defecto
			//@Override
			//public void addTableModelListener(TableModelListener l)
			//{
			//	//????
			//}
		});
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
/*		table.setDefaultRenderer(Integer.class, new TableCellRenderer()
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
					cell.setForeground(Color.BLUE);
					if( row % 2 == 0 )
						cell.setBackground(Color.LIGHT_GRAY);
				}
				else
				{
					cell.setForeground(Color.RED);
					cell.setBackground(new Color(0,0,255,100));
					if( hasFocus )
						cell.setBorder(BorderFactory.createLineBorder(Color.GREEN,2));
				}
				return cell;
			}
		});*/

		scrollPane.setViewportView(table);
	}
}
