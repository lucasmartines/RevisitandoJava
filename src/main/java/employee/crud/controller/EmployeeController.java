package employee.crud.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import employee.crud.beans.Employee;
import employee.crud.dao.EmployeeDAO;
import employee.crud.dao.EmployeeDAOImpl;


@WebServlet("/")
public class EmployeeController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	
	
	EmployeeDAO employeeDaoImpt = null;
	
    
   

    @Override
    public void init(ServletConfig config) throws ServletException {
    	super.init(config);
    	
    	employeeDaoImpt = new EmployeeDAOImpl();
    	
    	System.out.println(employeeDaoImpt);
    }
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		doPost(request,response);
	}
 

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getServletPath();
		
		System.out.println("action " + action);
		
		 switch(action) 
		 {
		 	case "/add":{
		 		
		 		addNewEmployee( request , response );
		 		break;
		 	}
	 		case "/update":{
 	 			updateEmployee( request , response );	 			
	 			break;
	 		}
	 		case "/delete":{
	 			deleteEmployee( request, response );
	 			break;	 			
	 		}
	 		case "/list":{
	 			getAllEmployees( request , response );
	 			break;
	 		}
	 		case "/get":{
	 			getEmployee( request , response );	 			
	 			break;
	 		}
//	 		default:
//	 			getAllEmployees(request, response);
//	 			break;
		 }
		
		 
	}
	
	private void getEmployee(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		System.out.println("get employee");
		System.out.println( request.getParameter("id"));
		
	   
		int    id 	   = Integer.parseInt( request.getParameter("id") )  ;
		
				
		Employee employee = employeeDaoImpt.getEmployee(id);
		
		
		System.out.println(" Employee details: " + employee );
 
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			 
			e.printStackTrace();
		} 
		
		
	}

	private void getAllEmployees(HttpServletRequest request, HttpServletResponse response) {
		
		System.out.println("get all employees");
		
		List<Employee> allEmployees = employeeDaoImpt.getAllEmployees();
		
		System.out.println("a quantidade de employees: " + allEmployees.size());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			 
			e.printStackTrace();
		}  
		
	}

	private void updateEmployee(HttpServletRequest request, HttpServletResponse response) 
	{
		
		System.out.println( request.getParameter("id"));
		
	   
		int    id 	   = Integer.parseInt( request.getParameter("id") )  ;
		
	 
		
		
		String name    = request.getParameter("name");
		String email   = request.getParameter("email");
		String phone   = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee( id , name , email , phone , address );
		
		System.out.println(" Employee details: " + employee );
		
		employeeDaoImpt.updateEmployee(employee);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			 
			e.printStackTrace();
		} 
		
		
	}

	private void addNewEmployee(HttpServletRequest request, HttpServletResponse response) {
		 
		String name    = request.getParameter("name");
		String email   = request.getParameter("email");
		String phone   = request.getParameter("phone");
		String address = request.getParameter("address");
		
		Employee employee = new Employee(name,email,phone,address);
		
		System.out.println(" Employee details: " + employee );
		
		employeeDaoImpt.addEmployee(employee);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) {
		 
		int    id 	   = Integer.parseInt( request.getParameter("id") )  ;
				
		if( employeeDaoImpt.deleteEmployee( id ) ) {
			
			System.out.println("Deleted employee: " + id );

		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
		
			
		try {
			dispatcher.forward(request, response);
		} catch (ServletException | IOException e) {
			 
			e.printStackTrace();
		} 
		
	}
	

}
