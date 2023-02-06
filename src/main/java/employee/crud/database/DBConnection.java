package employee.crud.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static final String dbUrl = "jdbc:mysql://localhost:3306/employee_db";
	public static final String dbUserName = "root";
	public static final String dbUserPass = "";

	public static Connection connection = getConnection();
	
	
	public static Connection getConnection()
	{
		
		
		System.out.println("Starting connection with database");
		
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbUrl,dbUserName,dbUserPass);
			
			if( connection != null ) 
			{
				
				System.out.println("Connected with database");
				return connection;
			}else {
				System.out.println("Failed to connect in database");
				return null;
			}
			
		}
		catch(Exception e ) 
		{
			e.printStackTrace(); 
			return null;
		}
	}
	
	
	
	public static void main(String [] m) {
		System.out.println(DBConnection.connection);
	}
}
