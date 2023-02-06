package employee.crud.dao;

import java.util.List;
import employee.crud.beans.Employee;

public interface EmployeeDAO {
	
	public boolean addEmployee(Employee employee);
	
	public boolean updateEmployee(Employee employee);
	
	public boolean deleteEmployee(int employeeId);
	
	public List<Employee> getAllEmployees();
	
	public Employee getEmployee(int employeeId);
}
