package employee_management;

import java.sql.*;

/**
 * Main class that will be started upon running the program
 * @author ltai2
 *
 */
public class Home {

	public static void main(String[] args) {
		// New instance of Connection and Statement
		Connection con;
		Statement stm;
				
		// SQL statement that will be used to execute sql statement later
		String find_accounts_tbl = "SELECT * FROM accounts";
		String find_employee_tbl = "SELECT * FROM employee";
		
		String create_accounts_tbl = "CREATE TABLE IF NOT EXISTS `accounts` (\r\n" + 
				"  `id` int NOT NULL AUTO_INCREMENT,\r\n" + 
				"  `usernames` varchar(255) DEFAULT NULL,\r\n" + 
				"  `passwords` varchar(255) DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`id`)\r\n" + 
				")";
		String insert_accounts_tbl = "INSERT INTO `accounts` VALUES (1,'admin','password')";
		
		String create_employee_tbl = "CREATE TABLE IF NOT EXISTS `employee` (\r\n" + 
				"  `id` int NOT NULL,\r\n" + 
				"  `firstname` varchar(255) DEFAULT NULL,\r\n" + 
				"  `lastname` varchar(255) DEFAULT NULL,\r\n" + 
				"  `age` int DEFAULT NULL,\r\n" + 
				"  `phone` varchar(255) DEFAULT NULL,\r\n" + 
				"  `email` varchar(255) DEFAULT NULL,\r\n" + 
				"  `weekly_hour` int DEFAULT NULL,\r\n" + 
				"  `monthly_salary` int DEFAULT NULL,\r\n" + 
				"  PRIMARY KEY (`id`)\r\n" + 
				")";
		
		// Check for accounts table initialization
		try {
			// Look for the mysql driver class in CLASSPATH
			Class.forName(Data.DRIVER);
						
			// Make connection using the getConnection method and the 3 constant variables
			con = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
						
			// Create a new sql statement and execute the statement provided above
			// Load the result into a ResultSet object
			stm = con.createStatement();
			ResultSet result = stm.executeQuery(find_accounts_tbl);
			
		} catch (Exception e) {
			System.out.println(e);
			try {
				Class.forName(Data.DRIVER);
				con = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
				stm = con.createStatement();
				stm.execute(create_accounts_tbl);
				stm.executeUpdate(insert_accounts_tbl);
			} catch (Exception ex) {
				System.out.println(ex);
			}
			
		}
		
		
		// Check for employee table initialization
		try {
			// Look for the mysql driver class in CLASSPATH
			Class.forName(Data.DRIVER);
						
			// Make connection using the getConnection method and the 3 constant variables
			con = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
						
			// Create a new sql statement and execute the statement provided above
			// Load the result into a ResultSet object
			stm = con.createStatement();
			ResultSet result = stm.executeQuery(find_employee_tbl);
			
		} catch (Exception e) {
			System.out.println(e);
			try {
				Class.forName(Data.DRIVER);
				con = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
				stm = con.createStatement();
				stm.execute(create_employee_tbl);
			} catch (Exception ex) {
				System.out.println(ex);
			}
		}
		
		
		LoginFrame home = new LoginFrame();
	}

}
