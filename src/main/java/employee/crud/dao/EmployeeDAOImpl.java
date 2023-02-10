package employee.crud.dao;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
try {
			
			System.out.println("Start to udpate user to database");
			
			
			String updateStatement = 
				"UPDATE employee SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
			
			PreparedStatement stmt = connection.prepareStatement(updateStatement);
			
			stmt.setString( 1, employee.getName() );
			stmt.setString( 2, employee.getEmail() );
			stmt.setString( 3, employee.getPhone() );
			stmt.setString( 4, employee.getAddress() );
			stmt.setInt( 5, employee.getId() );
			
			
			int result = stmt.executeUpdate();
			
			if( result == 1) {  System.out.println("User is updated"); }
			
			
			
			return result == 1 ? true : false;
			
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			return false;
		} 
	}

	@Override
	public boolean deleteEmployee(int employeeId) {
		
		try {
			System.out.println("Start to delete user from database");
			
			
			String deleteEmployee = 
				"DELETE FROM employee WHERE id = ?";
			
			PreparedStatement stmt = connection.prepareStatement(deleteEmployee);
			
			stmt.setInt( 1, employeeId ); 
			
			
			int result = stmt.executeUpdate();
			
			if( result == 1) {  System.out.println("User is deleted"); } 
			else 			 {  System.out.println("User don't exist"); }
			
			return result == 1 ? true : false;
			
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			return false;
		} 
	
	
	}

	@Override
	public List<Employee> getAllEmployees() {

		try {
			System.out.println("Start to get all users from database");
			
			
			String selectStatement = 
				"SELECT * FROM employee";
			
			//"SELECT * FROM employee WHERE id = ?";
			
			PreparedStatement stmt = connection.prepareStatement(selectStatement);
			 
			
			
			ResultSet resultSet =  stmt.executeQuery();
			List<Employee> employees = new ArrayList<>();
			
			while( resultSet.next() ) {
				
				Employee employee = new Employee();
						 employee.setId    ( resultSet.getInt   ("id") );
						 employee.setEmail ( resultSet.getString("email") );
						 employee.setName  ( resultSet.getString("name") );
						 employee.setPhone ( resultSet.getString("phone") );
						 
						 employees.add(employee);
			}
			
			return employees;
			 
			
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			return null;
		} 
	}

	@Override
	public Employee getEmployee(int employeeId) {
		try {
			System.out.println("Start to select user from database");
			
			
			String selectStatement   =  "SELECT * FROM employee WHERE id = ?";
			
			PreparedStatement stmt   = connection.prepareStatement(selectStatement);
			stmt.setInt(1, employeeId); 
			
			
			ResultSet 		resultSet =  stmt.executeQuery();  
			List<Employee>  employees = new ArrayList<>();
			
			
			while( resultSet.next() ) 
			{
				
				Employee employee = new Employee();
				
						 employee.setId    ( resultSet.getInt   ("id") );
						 employee.setEmail ( resultSet.getString("email") );
						 employee.setName  ( resultSet.getString("name") );
						 employee.setPhone ( resultSet.getString("phone") );
						 
						 employees.add(employee);
			}
			
			return employees.get(0); 
			
		}catch(Exception e) {
			
			System.out.println(e.getMessage());
			return null;
			
		} 
	}
	
	
	public static void main(String[]a) {
		
		
		
//		Employee employee  = new Employee("lucas martines","lucas@gmail.com.br","11966342233","Rua Carina");
//				 employee . setId(11);
//		
//		
//		EmployeeDAOImpl dao = new EmployeeDAOImpl(); 
//		
//						dao.updateEmployee(employee);
		
		
//		EmployeeDAOImpl dao = new EmployeeDAOImpl(); 
//		
//		dao.deleteEmployee(4);

//		EmployeeDAOImpl dao = new EmployeeDAOImpl(); 
//		
//		List<Employee> employees = dao.getAllEmployees();
//
//		employees.forEach(System.out::println);
		
		EmployeeDAOImpl dao = new EmployeeDAOImpl(); 
		
		System.out.println(dao.getEmployee(3));;
	}
}
