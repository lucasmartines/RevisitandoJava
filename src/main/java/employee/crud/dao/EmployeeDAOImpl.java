package employee.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import employee.crud.beans.Employee;
import employee.crud.database.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO
{
	
	private static Connection connection = DBConnection.connection;
	
	
	
	@Override
	public boolean addEmployee(Employee employee) 
	{
		try {
			
			System.out.println("Start to insert user to database");
			
			
			String insertBySql = 
				"INSERT INTO employee (name,email,phone,address) VALUES (?,?,?,?)" ;
			
			PreparedStatement stmt = connection.prepareStatement(insertBySql);
			
			stmt.setString( 1, employee.getName() );
			stmt.setString( 2, employee.getEmail() );
			stmt.setString( 3, employee.getPhone() );
			stmt.setString( 4, employee.getAddress() );
			
			
			int result = stmt.executeUpdate();
			
			if( result == 1) {  System.out.println("User is in database"); }
			
			
			
			return result == 1 ? true : false;
			
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			return false;
		} 
	}

	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee getEmployee(int employeeId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static void main(String[]a) {
		
		
		
		Employee employee  = new Employee("lucas","lucas@plyn.com.br","11966342222","Rua do Pacificador");
		
		EmployeeDAOImpl dao = new EmployeeDAOImpl(); 
		
						dao.addEmployee(employee);
		
		
		
	}
}
