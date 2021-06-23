package employee_management;

import javax.swing.*;
import javax.swing.table.*;
import java.sql.*;

/**
 * This class contains the collecting of data from the database and display them to a java table model
 * @author ltai2
 *
 */
public class Data {
	// username and pass for database
	protected static final String DRIVER = "org.h2.Driver";
	protected static final String USERNAME = "usr1";
	protected static final String PASSWORD = "";
	protected static final String CONNECTION = "jdbc:h2:~/db";
	
	// Columns' names for the table model
	private static String[] column_names = {"ID", "Firstname", "Lastname", "Age", "Phone", "Email", "WeeklyHour", "Salary (Monthly)"};
	
	// Creating a table model with specified columns' names and 0 rows
	@SuppressWarnings("serial")
	protected static DefaultTableModel model = new DefaultTableModel(column_names, 0) {
		@Override
		// Disable cell editing
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	
	// Creating a JTable object to use the table model later
	protected JTable table = new JTable();
	
	/**
	 * Default constructor that will make connection to SQL server and retrieve data
	 */
	public Data() {
		
		// New instance of Connection and Statement
		Connection con_1;
		Statement stm;
		
		// SQL statement that will be used to execute sql statement later
		String sql_stm = "SELECT * FROM employee";
		
		try {
			
			// Look for the mysql driver class in CLASSPATH
			Class.forName(DRIVER);
			
			// Make connection using the getConnection method and the 3 constant variables
			con_1 = DriverManager.getConnection(CONNECTION, USERNAME, PASSWORD);
			
			// Create a new sql statement and execute the statement provided above
			// Load the result into a ResultSet object
			stm = con_1.createStatement();
			ResultSet result = stm.executeQuery(sql_stm);
			
			// Loop through the ResultSet object and basically retrieve the data of each row
			// Then, add these data into a new row of the table model
			while(result.next()) {
				int id = result.getInt("id");
				String firstname = result.getString("firstname");
				String lastname = result.getString("lastname");
				int age = result.getInt("age");
				String phone = result.getString("phone");
				String email = result.getString("email");
				int weekly_hour = result.getInt("weekly_hour");
				int monthly_sal = result.getInt("monthly_salary");
				
				model.addRow(new Object[] {id, firstname, lastname, age, phone, email, weekly_hour, monthly_sal});
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		// Use the resulting table model for the JTable so that it contains the data from mysql database
		this.table.setModel(model);	
	}
	
	public DefaultTableModel getModel() {
		return model;
	}
}
