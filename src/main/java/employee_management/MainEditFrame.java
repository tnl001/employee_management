package employee_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.text.AbstractDocument;

/**
 * This class contains the structure and layout of the Edit Employee window
 * Other Sources: SpringUtilities.java from oracle was used to help with the layout
 * @author ltai2
 *
 */
public class MainEditFrame {
	// Components to be used
	protected JFrame wd = new JFrame();

	private JPanel first_p = new JPanel();
	private JPanel second_p = new JPanel();
	private JPanel main_p = new JPanel();

	private JButton save_button = new JButton("Save");

	// labels and names to be used
	private String[] labels = {"id: ", "firstname: ", "lastname: ", "age: ", "phone: ", "email: ", "weekly hour: ", "hourly pay: ", "monthly salary: "};
	private String[] names = {"id", "firstname", "lastname", "age", "phone", "email", "weekly hour", "hourly pay", "monthly salary"};
	
	// This Arraylist contains the JTextFields created by the current frame
	protected static ArrayList<JTextField> inputFields = new ArrayList<>();
	
	/**
	 * Default constructor for the MainEditFrame
	 */
	public MainEditFrame() {
		this.first_p.setLayout(new SpringLayout());
		this.first_p.setPreferredSize(new Dimension(400, 300));
		// this.first_p.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
		
		// create a for loop that generates JLabels and JTextFields using the labels array
		for (int i = 0; i < this.labels.length; i++) {
			JLabel label = new JLabel(labels[i]);
			this.first_p.add(label);
			
			// Creating a textfield and set its name to be identified with
			// Textfields will start out empty
			JTextField input = new JTextField(10);
			input.setName(names[i]);
			input.setText("");
			
			// Adding the textfields into the class' arraylist
			inputFields.add(input);
			
			// Setting the label to the textfield and adding it to the first panel of this frame
			label.setLabelFor(input);
			first_p.add(input);
		}
		
		// create a compact grid using the SpringUtilities class from oracle
		SpringUtilities.makeCompactGrid(this.first_p, this.labels.length, 2, 5, 5, 10, 10);
		
		
		
		// Add event listener for the Add Employee and Clear button
		this.save_button.addActionListener(new SaveEmployeeEvent());
		
		// create a second panel that contains the button
		this.second_p.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.second_p.setPreferredSize(new Dimension(400,50));
		// this.second_p.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
		this.second_p.add(this.save_button);
		
		// MAIN PANEL that contains both the first and second panels
		this.main_p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.main_p.setPreferredSize(new Dimension(400,350));
		// this.main_p.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
		this.main_p.add(this.first_p);
		this.main_p.add(this.second_p);		
		
		// add the main panel into the main frame
		this.wd.add(this.main_p);		
		this.wd.setTitle("Employee's Information");
		this.wd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.wd.setSize(400, 500);
		this.wd.setLocationRelativeTo(null);
		this.wd.setResizable(false);
		this.wd.pack();
		this.wd.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		// Disable editing for id input field
		for (int i = 0; i < inputFields.size(); i++) {
			if (inputFields.get(i).getName() == "id") {
				inputFields.get(i).setEditable(false);
			}
					
			if (inputFields.get(i).getName() == "monthly salary") {
				inputFields.get(i).setEditable(false);
			}
					
			if (inputFields.get(i).getName() == "weekly hour") {
				inputFields.get(i).addFocusListener(new FocusEvent(inputFields));
				inputFields.get(i).addKeyListener(new KeyPressEvent(inputFields));
				((AbstractDocument)inputFields.get(i).getDocument()).setDocumentFilter(new IntFilter());
			}
			
			if (inputFields.get(i).getName() == "hourly pay") {
				inputFields.get(i).addFocusListener(new FocusEvent(inputFields));
				inputFields.get(i).addKeyListener(new KeyPressEvent(inputFields));
				((AbstractDocument)inputFields.get(i).getDocument()).setDocumentFilter(new IntFilter());
			}
		}
	}
	
	
	/**
	 * Inner nested event handler class for the Save button
	 */
	class SaveEmployeeEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean correctFormat = true;		
			
			// Loop through the inputFields and check the format of each using a switch statement
			// If the format is wrong, a message dialog will pop up
			// Same approach for all cases
			// Did not need to check for "id" because it will not be editable
			for (int i = 0; i < MainEditFrame.inputFields.size(); i++) {
				switch (MainEditFrame.inputFields.get(i).getName()) {
					case "firstname":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Firstname is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Firstname cannot be an integer!");						
								correctFormat = false;
							} catch (NumberFormatException ex) {
								// Do nothing
							}
						}
						break;
					case "lastname":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Lastname is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Lastname cannot be an integer!");						
								correctFormat = false;
							} catch (NumberFormatException ex) {
								// Do nothing
							}
						}
						break;
					case "age":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Age is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Age must be an integer!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
					case "phone":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Phone number is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Long.parseLong(MainEditFrame.inputFields.get(i).getText());
								if (MainEditFrame.inputFields.get(i).getText().length() != 10) {
									JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Invalid phone number!");
									correctFormat = false;
								}
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Phone number must contain only integers!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
					case "email":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Email is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Invalid email address!");
								correctFormat = false;
							} catch (NumberFormatException ex) {
								
								// Check if the email contains @ AND .com or .edu
								if (MainEditFrame.inputFields.get(i).getText().contains("@") && 
									MainEditFrame.inputFields.get(i).getText().contains(".com") ||
									MainEditFrame.inputFields.get(i).getText().contains(".edu")) {
									
									// Check if the string before the @ character is null
									if (MainEditFrame.inputFields.get(i).getText()
											.substring(0, MainEditFrame.inputFields.get(i).getText().indexOf("@")).isEmpty()) {
										JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Invalid email address!");
										correctFormat = false;
									} else if (MainEditFrame.inputFields.get(i).getText() // if substring contains any characters that is not in the regex
												.substring(0, MainEditFrame.inputFields.get(i).getText().indexOf("@")).matches(".*[^A-Za-z1-9].*")) {
										JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Invalid email address!");
										correctFormat = false;
									}else {
										// Do nothing
									}
								} else {
									JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Invalid email address!");
									correctFormat = false;
								}
								
							}
						}
						break;
					case "weekly hour":
						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Weekly Hour is empty!");
							
							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Weekly Hour must be an integer!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
//					case "monthly salary":
//						if (MainEditFrame.inputFields.get(i).getText().isEmpty()) {			
//							JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Monthly Salary is empty!");
//							
//							System.out.println(MainEditFrame.inputFields.get(i).getName() + " is empty");
//							correctFormat = false;
//						} else {
//							try {
//								Integer.parseInt(MainEditFrame.inputFields.get(i).getText());
//							} catch (NumberFormatException ex) {
//								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Monthly Salary must be an integer!");						
//								System.out.println(ex);
//								correctFormat = false;
//							}
//						}
//						break;
				} // end of switch statement												
			}
			
			// Collect data from the inputFields
			if (correctFormat == true) {
				// Collect data from the inputFields
				String newIdData = null;
				String newFnData = null;
				String newLnData = null;
				String newAgeData = null;
				String newPhoneData = null;
				String newEmailData = null;
				String newWhData = null;
				String newHpData = null;
				String newMsData = null;
				
				// Collect the data from the input fields
				for (int i = 0; i < MainEditFrame.inputFields.size(); i++) {
					switch(MainEditFrame.inputFields.get(i).getName()) {
						case "id":
							newIdData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "firstname":
							newFnData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "lastname":
							newLnData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "age":
							newAgeData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "phone":
							newPhoneData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "email":
							newEmailData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "weekly hour":
							newWhData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "hourly pay":
							newHpData = MainEditFrame.inputFields.get(i).getText();
							break;
						case "monthly salary":
							if (newWhData == null || newHpData == null) {
								JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Both weekly hour AND hourly pay must be filled!");	
								break;
							} else {
								int ms = Integer.parseInt(newHpData) * Integer.parseInt(newWhData) * 4;
								newMsData = String.valueOf(ms);
							}
							break;
					}
				}
				
				
				
				System.out.println(newIdData);
				
				Connection conn;
				Statement stm;
				
				// Execute this mysql statement to update the database
				String sql_stm = String.format("UPDATE employee"
						+ "\nSET firstname = '%s', lastname = '%s', age = '%s', phone = '%s', email = '%s', weekly_hour = '%s', pay_rate = '%s', monthly_salary = '%s'"
						+ "\nWHERE id = '%s';", newFnData, newLnData, newAgeData, newPhoneData, newEmailData, newWhData, newHpData, newMsData, newIdData);
				System.out.println(sql_stm);
				
				
				try {
					// Look for the mysql driver class in CLASSPATH
					Class.forName(Data.DRIVER);
					
					// Create a connection to the database, set up a statement from the connection, and execute the statement
					conn = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
					stm = conn.createStatement();
					stm.executeUpdate(sql_stm);	
					
					// Updating the displaying table by editing the table model itself
					int rowInd = MainFrame.main_table.getSelectedRow();
					System.out.println(rowInd);
					Data.model.setValueAt(newFnData, rowInd, 1);
					Data.model.setValueAt(newLnData, rowInd, 2);
					Data.model.setValueAt(newAgeData, rowInd, 3);
					Data.model.setValueAt(newPhoneData, rowInd, 4);
					Data.model.setValueAt(newEmailData, rowInd, 5);
					Data.model.setValueAt(newWhData, rowInd, 6);
					Data.model.setValueAt(newHpData, rowInd, 7);
					Data.model.setValueAt(newMsData, rowInd, 8);
					
				} catch(SQLIntegrityConstraintViolationException exc) {
					JOptionPane.showMessageDialog(MainEditFrame.this.wd, "Duplicate ID entry!");
				} catch(Exception exc) {
					System.out.println(exc);
				}
				
			} // end of if statement - collecting data
		}
	}
}
