package jGuiWindowBuilder;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.net.URL;

import javax.swing.Action;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import com.sun.tools.javac.Main;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;

public class GuiApp {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPasswordField passwordField;
	private final Action btnConnectAction = new SwingAction();
	private JButton btnConnect;
	private JTable table;
	private final Action action = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiApp window = new GuiApp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GuiApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		try
		{
			for( var look : UIManager.getInstalledLookAndFeels() )
				System.out.println(look);
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URL urlCheckIcon = GuiApp.class.getResource("/img/png_files/check.png");
		
		frame.setIconImage(new ImageIcon(urlCheckIcon).getImage());
		frame.setBounds(100, 100, 495, 404);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		btnConnect = new JButton("Connect");
		btnConnect.setAction(btnConnectAction);
//		{
		URL urlBtnIcon = GuiApp.class.getResource("/img/png_files/button_green_play.png");
		ImageIcon btnIcon = new ImageIcon(urlBtnIcon/*"E:\\Devel\\diplo-java-workspace\\jGuiWindowBuilder\\src\\img\\png_files\\button_green_play.png"*/);
		ImageIcon icon = new ImageIcon(btnIcon.getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH));
		btnConnect.setIcon(icon);
//		}
		btnConnect.setBounds(10, 122, 156, 23);
		frame.getContentPane().add(btnConnect);
		
		JLabel lblNewLabel = new JLabel("Ip (Host)");
		lblNewLabel.setBounds(10, 11, 46, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(80, 8, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Tcp Port");
		lblNewLabel_1.setBounds(10, 38, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setText("3306");
		textField_1.setBounds(80, 35, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("User");
		lblNewLabel_2.setBounds(10, 69, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(80, 66, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Pass");
		lblNewLabel_3.setBounds(10, 97, 46, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(80, 91, 86, 20);
		frame.getContentPane().add(passwordField);
		
//		int[] vec = {1,2,3};
		String[] colNames = { "id" , "Temp" , "Hum" , "Presion" };
		String[][] tableData = { {"1" , "12.3" , "25.5" , "18.2"} ,
								 {"2" , "62.5" , "12.3" , "85.7"} , 
								 {"3" , "33.5" , "22.3" , "5.7" } };
//		table = new JTable(tableData,colNames);
		table = new JTable(new AbstractTableModel()
		{
			@Override
			public int getRowCount() {
				return 3;
			}
			@Override
			public int getColumnCount() {
				return 4;
			}
			@Override
		    public String getColumnName(int column) {
				if( column == 1 )
					return "id";
				if( column == 2 )
					return "Temp";
				if( column == 3 )
					return "Hum";
				if( column == 4 )
					return "Presion";
				return "";
		    }
			@Override
			public Object getValueAt(int rowIndex, int columnIndex) {
				if( rowIndex == 1 && columnIndex == 1 )
					return 12.3;
				if( rowIndex == 1 && columnIndex == 2 )
					return -33.25;
				return 0;
			}
			@Override
		    public boolean isCellEditable(int rowIndex, int columnIndex) {
				if( /*rowIndex == 2 &&*/ columnIndex == getColumnCount()-1 )
					return true;
		        return false;
		    }
			@Override
		    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
			{
				if( rowIndex == 1 && columnIndex == 2 )
				{
					System.out.println("modify [1,2]");
				}
		    }
		});
		table.setValueAt("data", 1, 2);
		table.setBounds(12, 157, 455, 196);
		frame.getContentPane().add(table);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenu mnNewMenu_1 = new JMenu("Sub menu");
		mnNewMenu.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Refresh");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		
		JCheckBoxMenuItem chckbxmntmNewCheckItem = new JCheckBoxMenuItem("New check item");
		mnNewMenu_1.add(chckbxmntmNewCheckItem);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Update");
		mnNewMenu_1.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Exit");
		mntmNewMenuItem_3.setAction(action);
		mnNewMenu.add(mntmNewMenuItem_3);
		
		
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Connect");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e)
		{
			System.out.println("Connect clicked");
			btnConnect.setBackground(new Color(10,100,90,50));
//			btnConnect.setBackground(Color.CYAN);
		}
	}
	public JButton getBtnConnect() {
		return btnConnect;
	}
	public JTextField getTextField_2() {
		return textField_2;
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e)
		{
			System.exit(0);
		}
	}
}
