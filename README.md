## Java
### Minha revisão ao estudo do java, focando na parte JAVA WEB


O objetivo é revisar os conceitos:
 - Servlet
 - java beans
 - DAO Object e DB Connection
 - Mavem
 - SQL ( para as queries )
 - MVC no java 

 
Bem como aprimorar meus conhecimentos em java básico


Esse projeto é feito com base no curso de java EE do udemy e serve mais para eu 
conhecer melhor o java na parte web


## Pedaços de código que recortei que posso usar para relembrar depois sobre a parte do banco de dados

## DAO 
Sei que dao está meio datado por causa do JPA, mas já tenho um projeto que fiz 
usando JPA então para ver algo diferente e deixar registrado no git eu decidi usar o DAO
mesmo.


### conectando no banco de dados
#### Criar uma simples conexão com o banco de dados
``` 
public static final String dbUrl = "jdbc:mysql://localhost:3306/employee_db";
public static final String dbUserName = "root";
public static final String dbUserPass = "";


Class.forName("com.mysql.jdbc.Driver");
Connection connection = DriverManager.getConnection(dbUrl,dbUserName,dbUserPass);
```
Esse comando retorna um Connection que será usado posteriormente para fazer tudo o que eu quiser 
no banco de dados
#### Não esquecer de instalar o driver mysql
mysql-connector-java

### Resumo rápido com o DAO
Pelo que ví o DAO me permite integrar o java beam com os registros do banco de dados 
ele faz isso usando o preparedStatement para queries modificadoras, em conjunto com os comandos
setString() e setInt():


```
String updateStatement = 
	 "UPDATE employee SET name = ?, email = ?, phone = ?, address = ? WHERE id = ?";
     PreparedStatement stmt = connection.prepareStatement(comando sql);
     stmt.setString( posicaoInterrogação lá , o nome    )
     stmt.setInt   ( posiçãoInterrogação lá , o inteiro )
```

Para queries leitoras é feito o caminho inverso BD -> Java Bean
```
PreparedStatement stmt = connection.prepareStatement(SELECT * FROM employee ); 

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
            return employees
			
```

se quiser trazer apenas um para  a lista então filtre o id
stmt.setInt( posição do ? na query de seleção tendo o id no filtro SQL) 

# estudo de controller 
A classe controller extende da HTTPServlet
e tem a anotation @WebServlet("/urlFinal")

com o HttpServletRequest e HttpServletResponse nos métodos get e post é possivel ter acesso 
as configurações do servlet

# o get parameter é usado para pegar os parametros post

request.getParameter("nome")


# também tem o dispacher
```
RequestDispatcher dispatcher = request.getRequestDispatcher("/employeeView.jsp");
                  dispatcher.forward(request, response);
```

só funciona o x-www-form-urlencoded para receber parametros POST 