import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main
{
	//AWT -> Swing -> JavaFX
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setTitle("Gui java test");
		frame.setBounds(480, 350, 210, 165);//antes era: frame.setSize(210,165);

		frame.setResizable(false);
//		frame.setBackground(new Color(255,0,0));

		JLabel lblIpHost = new JLabel("IP (Host)");
		//label.setText("Hello SWING");
		lblIpHost.setBounds(5, 5, 100, 25);	// X Y WIDTH HEIGTH
		Color color = new Color(128,100,80);//RGB
		lblIpHost.setForeground(color);
		
		JTextField txtIpHost = new JTextField();
		txtIpHost.setBounds(105,5,100,25);
		
		JLabel lblTcpPort = new JLabel("Tcp Port");
		lblTcpPort.setBounds(5, 5+25+5, 100, 25);

		JTextField txtTcpPort = new JTextField("3306");
		txtTcpPort.setBounds(105, 5+25+5, 100, 25);
		txtTcpPort.setToolTipText("Insert tcp port");
		
		JLabel lblRootPass = new JLabel("Root pass");
		lblRootPass.setBounds(5, 5+25+5+25+5, 100, 25);

		JPasswordField txtRootPass = new JPasswordField();
		txtRootPass.setBounds(105, 5+25+5+25+5, 100, 25);
		
//		System.out.println(new String(txtRootPass.getPassword()));
		System.out.println(txtRootPass.getPassword());

		JButton btnConnect = new JButton("Connect");
		btnConnect.setBounds(5,5+25+5+25+5+25+5,200,25);
		
//		MyActionListener myAl = new MyActionListener();
//		btnConnect.addActionListener(myAl);

		btnConnect.addActionListener
		(
			new ActionListener()
			{
				@Override
				public void actionPerformed(ActionEvent e)
				{
					// TODO Auto-generated method stub
					String host = txtIpHost.getText();
					String port = txtTcpPort.getText();
					String pass = new String(txtRootPass.getPassword());
					System.out.println(host);
					System.out.println(port);
					System.out.println(pass);
				}
			}
		);
		btnConnect.addMouseMotionListener(new MouseMotionListener()
		{
			@Override
			public void mouseMoved(MouseEvent e)
			{
				// TODO Auto-generated method stub
				System.out.println(e.getX() + " , " + e.getY());
			}
			@Override
			public void mouseDragged(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		btnConnect.addMouseListener(new MouseListener()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				// TODO Auto-generated method stub
				System.out.println("mouseReleased");
			}
			@Override
			public void mousePressed(MouseEvent e)
			{
				// TODO Auto-generated method stub
				System.out.println("mousePressed");
			}
			@Override
			public void mouseExited(MouseEvent e)
			{
				// TODO Auto-generated method stub
				System.out.println("mouseExited");
			}
			@Override
			public void mouseEntered(MouseEvent e)
			{
				// TODO Auto-generated method stub
				System.out.println("mouseEntered");
			}
			@Override
			public void mouseClicked(MouseEvent e)
			{
				// TODO Auto-generated method stub
				
			}
		});
		
		ImageIcon icon = new ImageIcon("gear.png");
		frame.setIconImage(icon.getImage());
		btnConnect.setIcon(icon);
		
		frame.add(lblIpHost);
		frame.add(txtIpHost);
		frame.add(lblTcpPort);
		frame.add(txtTcpPort);
		frame.add(lblRootPass);
		frame.add(txtRootPass);
		frame.add(btnConnect);		
		
		frame.setVisible(true);
	}

}
