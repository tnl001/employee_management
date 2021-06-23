package employee_management;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

/**
 * This class contains the event handler of the delete button in the main window
 * @author ltai2
 *
 */
public class DeleteButtonEvent implements ActionListener {

	@Override
	/**
	 * Create connection to the database and execute the DELETE statement
	 * @param e of type ActionEvent
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		
		// Create connection and statement
		Connection conn;
		Statement stm;
		
		
		try {
			// Get the index of the selected/highlighted row of the main table
			int rowInd = MainFrame.main_table.getSelectedRow();
			
			// Get the ID value of that row (the index of the ID column is 0)
			Object idToRemove = Data.model.getValueAt(rowInd, 0);
			
			// Mysql statement that deletes the row using the ID number
			String sql_stm = String.format("DELETE FROM employee WHERE id=%s;", idToRemove);
			
			System.out.println(idToRemove);
			
			// Below are the usual steps to connect the application to the mysql database and execute the statement
			Class.forName(Data.DRIVER);
			
			conn = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
			stm = conn.createStatement();
			stm.executeUpdate(sql_stm);
			
			// Update the table model
			Data.model.removeRow(rowInd);
			
		} catch(ArrayIndexOutOfBoundsException exception) {
			JOptionPane.showMessageDialog(MainFrame.main_table, "Nothing to remove!");
		} catch(Exception exception) {
			System.out.println(exception);
		}
		
	}

}
