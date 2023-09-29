import java.sql.*;
import javax.swing.*; 

public class EmployeeData {
	public static Connection ConnectDB()
	{
		try 
		{
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\91953\\eclipse-workspace\\Employee management system\\employee_data.db");
			JOptionPane.showMessageDialog(null, "Connection Made");
			return conn;
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, "Connection Error");
			return null;
		}
	}
}
