import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class ViewFrame extends JFrame 
{
	Container c;
	JTextArea taData;
	JScrollPane spData;
	JButton btnBack;
	JPanel p1, p2; 
	
	ViewFrame()
	{
		c = getContentPane();
		
		p1 = new JPanel();
		taData = new JTextArea(3, 20);
		spData = new JScrollPane(taData);
		p1.add(spData);
		c.add(p1);
		
		p2 = new JPanel();
		btnBack = new JButton("Back");
		p2.add(btnBack);
		c.add("South", p2);
		
		DatabaseHandler d = new DatabaseHandler();
		String s = d.viewUser();
		taData.setText(s);
		
		btnBack.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				MainFrame a = new MainFrame();
				dispose();
			}
		});
		
		setTitle("View User");
		setSize(500, 150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}

		
		