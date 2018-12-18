import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class MainFrame extends JFrame
{
	Container c;
	JButton btnAdd, btnView, btnUpdate, btnDelete;
	
	MainFrame()
	{
		c = getContentPane();
		c.setLayout(new FlowLayout());
		
		btnAdd = new JButton("Add");
		btnView = new JButton("View");
		btnUpdate = new JButton("Update");
		btnDelete = new JButton("Delete");
		
		c.add(btnAdd);
		c.add(btnView);
		c.add(btnUpdate);
		c.add(btnDelete);
		
		btnAdd.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				AddFrame a = new AddFrame();
				dispose();
			}
		});
		
		btnView.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				ViewFrame a = new ViewFrame();
				dispose();
			}
		});
		
		btnUpdate.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				UpdateFrame a = new UpdateFrame();
				dispose();
			}
		});
		
		btnDelete.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				DeleteFrame a = new DeleteFrame();
				dispose();
			}
		});
		
		setTitle("JDBC CRUD Demo");
		setSize(500,150);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	
	}//End of Constructor 	
		
	public static void main(String args[])
	{
		MainFrame m = new MainFrame();
	}// End of Main
	
}// End of Class

class DatabaseHandler
{
	static Connection con;
	
	static void getCon()
	{
		try
		{
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			con=DriverManager.getConnection("jdbc:mysql://localhost/<DATABASE_NAME>?useSSL=false", "<USERNAME>", "<PASSWORD>"); //Enter your database name, username and password.
		}// End of Try
		
		catch(SQLException e)
		{
			System.out.println(e);
		}// End of Catch
		
	}// End of getCon
	
	public void addUser(int id, String name)
	{
		getCon();
		try
		{
			String sql = "insert into user values(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			pst.setString(2, name);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " Records inserted");
		}// End of try
		
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Insert issue");
		}
		// End of catch
		
	}// End of addEmployee
	
	public String viewUser()
	{
		StringBuffer sb = new StringBuffer();
		getCon();
		
		try 
		{
			String sql = "select * from user";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int r = rs.getInt(1);
				String n = rs.getString(2);
			    sb.append("ID: "+ r +" Name: " + n +"\n");
			}
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Some issues");
		}
		return sb.toString();
	}// End of viewEmployee
	
	public void updateUser(String name, int id)
	{
		getCon();
		try
		{
			String sql = "update user set uname = (?) where uid = (?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, id);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " Records updated");
		}// End of try
		
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Update issue");
		}
		// End of catch
		
	}// End of addEmployee
	
	
	public void deleteUser(int id)
	{
		getCon();
		try
		{
			String sql = "delete from user where uid=(?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, id);
			int r = pst.executeUpdate();
			JOptionPane.showMessageDialog(new JDialog(), r + " Records deleted");
		}// End of Try
		
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(new JDialog(), "Delete issue");
		}// End of catch 
		
	}

}// End of class

	