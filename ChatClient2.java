import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.lang.*;
public class ChatClient2  extends JFrame implements ActionListener , Runnable
{
	JTextField tf1;
	JTextArea ta1;
	JScrollPane p1;
	JButton b1;
	
	ServerSocket ss ; 
	Socket s2 ; 
	PrintWriter pw ; 
	BufferedReader br ; 
	
	public ChatClient2() 
	{
		setResizable(false);
		setTitle("Chat Client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(Color.green);
		
		tf1=new JTextField();
		ta1 = new JTextArea();
		p1 = new JScrollPane(ta1);
		b1 = new JButton("Send");

		add(tf1);
		add(p1);
		add(b1);
		
		tf1.setBounds(20, 30, 150, 20);
		b1.setBounds(180, 30, 80, 20);
		p1.setBounds(20, 60, 240, 200);
		
		setVisible(true);
		setSize(300, 340);
		
		try 
		{
			//ss = new ServerSocket(3000); // Only for server
			//s1 = ss.accept(); // Only for server 
			s2 = new Socket(InetAddress.getByName("localhost"),3000);
			
			pw = new PrintWriter(s2.getOutputStream(),true);
			br = new BufferedReader(new InputStreamReader(s2.getInputStream()));
					
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		//b1.addActionListener(this);
		//tf1.addActionListener(this);
		new Thread(this).start();
		b1.addActionListener(this);
	}
	public void actionPerformed(ActionEvent e) 
	{
		String text = tf1.getText();
		tf1.setText(""); 
		pw.println (text);
		ta1.append( "Me : "+text +"\t"+new Date() +"\n");		
	}
	public void run() 
	{
		while(true)
		{
			try 
			{
				String text = br.readLine();
				ta1.append("Sender : "+text +"\t"+new Date() +"\n");	
			} 
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) 
	{
		new ChatClient2 ();	
	}
}










