package employee_management;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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


/**
 * This class contains the structure and layout of the Add Employee window
 * Other Sources: SpringUtilities.java from oracle was used to help with the layout
 * @author ltai2
 *
 */
public class MainAddFrame {
	
	// Components that will be used
	protected JFrame wd = new JFrame();
	
	private JPanel first_p = new JPanel();
	private JPanel second_p = new JPanel();
	private JPanel main_p = new JPanel();
	
	private JButton add_button = new JButton("Add Employee");
	private JButton clear_button = new JButton("Clear");
	
	
	// Labels and names to be used
	private String[] labels = {"id: ", "firstname: ", "lastname: ", "age: ", "phone: ", "email: ", "weekly hour: ", "monthly salary: "};
	private String[] names = {"id", "firstname", "lastname", "age", "phone", "email", "weekly hour", "monthly salary"};

	

	protected static int whInd = 0;
	protected static int msInd = 0;
	

	// This Arraylist contains the JTextFields created by the current frame
	protected static ArrayList<JTextField> inputFields = new ArrayList<>();
	
	
	/**
	 * Default constructor for MainAddFrame that will create the Add Employee's window
	 * Contains input fields and 2 buttons
	 */
	public MainAddFrame() {
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
		this.add_button.addActionListener(new AddEmployeeEvent());
		this.clear_button.addActionListener(new ClearButtonEvent());
		
		// create an add button and a second panel that contains the button
		this.second_p.setLayout(new FlowLayout(FlowLayout.TRAILING));
		this.second_p.setPreferredSize(new Dimension(400,50));
		// this.second_p.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
		this.second_p.add(this.add_button);
		this.second_p.add(this.clear_button);
		
		
		// MAIN PANEL that contains both the first and second panels
		
		this.main_p.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.main_p.setPreferredSize(new Dimension(400,350));
		// this.main_p.setBorder(BorderFactory.createLineBorder(new Color(255,0,0)));
		this.main_p.add(this.first_p);
		this.main_p.add(this.second_p);
		
		
		// add the main panel into the main frame
		this.wd.add(this.main_p);
		
		this.wd.setTitle("Add Employee");
		this.wd.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.wd.setSize(400, 500);
		this.wd.setLocationRelativeTo(null);
		this.wd.setResizable(false);
		this.wd.pack();
		this.wd.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		
		for (int i = 0; i < inputFields.size(); i++) {
			if (inputFields.get(i).getName() == "monthly salary") {
				msInd = i;
				inputFields.get(i).setEditable(false);
			}
			
			if (inputFields.get(i).getName() == "weekly hour") {
				whInd = i;
				inputFields.get(i).addFocusListener(new FocusEvent(inputFields));
				inputFields.get(i).addKeyListener(new KeyPressEvent(inputFields));
				
			}
		}
		
//		if (addFrame.wd.isVisible()) {
////			while ( MainAddFrame.inputFields.get(whInd).getText() != null) {
////				int whNum = 12 * Integer.parseInt( MainAddFrame.inputFields.get(whInd).getText()) * 4;
////				MainAddFrame.inputFields.get(msInd).setText(String.valueOf(whNum));
////				System.out.println(whNum);
////			}
//			
//			System.out.println(MainAddFrame.inputFields.get(whInd).getText());
//		}
		
		
	}
	
	
	/**
	 * Inner nested event handler class for the Add Employee button
	 */
	class AddEmployeeEvent implements ActionListener {

		@Override
		/*
		 * Overriding the actionPerformed method from ActionListener interface
		 * Creating a connection to the SQL server and then execute a query that
		 * inserts a new employee by collecting information from the input boxes
		 * of the Add frame.
		 * @param e of type ActionEvent
		 * @return void
		 */
		public void actionPerformed(ActionEvent e) {
			boolean correctFormat = true;

			// Loop through the inputFields and check the format of each using a switch statement
			// If the format is wrong, a message dialog will pop up
			// Same approach for all cases
			for (int i = 0; i < MainAddFrame.inputFields.size(); i++) {
				switch (MainAddFrame.inputFields.get(i).getName()) {
					case "id":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "ID is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "ID must be an integer!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
					case "firstname":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Firstname is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Firstname cannot be an integer!");						
								correctFormat = false;
							} catch (NumberFormatException ex) {
								// Do nothing
							}
						}
						break;
					case "lastname":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Lastname is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Lastname cannot be an integer!");						
								correctFormat = false;
							} catch (NumberFormatException ex) {
								// Do nothing
							}
						}
						break;
					case "age":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Age is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Age must be an integer!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
					case "phone":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Phone number is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Long.parseLong(MainAddFrame.inputFields.get(i).getText());
								if (MainAddFrame.inputFields.get(i).getText().length() != 10) {
									JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Invalid phone number!");
									correctFormat = false;
								}
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Phone number must contain only integers!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
					case "email":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Email is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Invalid email address!");
								correctFormat = false;
							} catch (NumberFormatException ex) {
								
								// Check if the email contains @ AND .com or .edu
								if (MainAddFrame.inputFields.get(i).getText().contains("@") && 
									MainAddFrame.inputFields.get(i).getText().contains(".com") ||
									MainAddFrame.inputFields.get(i).getText().contains(".edu")) {
									
									// Check if the string before the @ character is null
									if (MainAddFrame.inputFields.get(i).getText()
											.substring(0, MainAddFrame.inputFields.get(i).getText().indexOf("@")).isEmpty()) {
										JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Invalid email address!");
										correctFormat = false;
									} else if (MainAddFrame.inputFields.get(i).getText() // if substring contains any characters that is not in the regex
												.substring(0, MainAddFrame.inputFields.get(i).getText().indexOf("@")).matches(".*[^A-Za-z1-9].*")) {
										JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Invalid email address!");
										correctFormat = false;
									}else {
										// Do nothing
									}
								} else {
									JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Invalid email address!");
									correctFormat = false;
								}
								
							}
						}
						break;
					case "weekly hour":
						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Weekly Hour is empty!");
							
							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
							correctFormat = false;
						} else {
							try {
								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
							} catch (NumberFormatException ex) {
								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Weekly Hour must be an integer!");						
								System.out.println(ex);
								correctFormat = false;
							}
						}
						break;
//					case "monthly salary":
//						if (MainAddFrame.inputFields.get(i).getText().isEmpty()) {			
//							JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Monthly Salary is empty!");
//							
//							System.out.println(MainAddFrame.inputFields.get(i).getName() + " is empty");
//							correctFormat = false;
//						} else {
//							try {
//								Integer.parseInt(MainAddFrame.inputFields.get(i).getText());
//							} catch (NumberFormatException ex) {
//								JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Monthly Salary must be an integer!");						
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
				String idData = null;
				String fnData = null;
				String lnData = null;
				String ageData = null;
				String phoneData = null;
				String emailData = null;
				String whData = null;
				String msData = null;
				
				for (int i = 0; i < MainAddFrame.inputFields.size(); i++) {
					switch(MainAddFrame.inputFields.get(i).getName()) {
						case "id":
							idData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "firstname":
							fnData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "lastname":
							lnData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "age":
							ageData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "phone":
							phoneData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "email":
							emailData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "weekly hour":
							whData = MainAddFrame.inputFields.get(i).getText();
							break;
						case "monthly salary":
							int ms = Data.PAYRATE * Integer.parseInt(whData) * 4;
							msData = String.valueOf(ms);
							break;
					}
				}
				
				// System.out.printf("id: %s%nfn: %s%nln: %s%nage: %s%nphone: %s%nemail: %s%nwh: %s%nms: %s%n", idData, fnData, lnData,
				// 					ageData, phoneData, emailData, whData, msData);
				
				// Creating a connection and a statement
				Connection conn;
				Statement stm;
				String sql_stm = String.format("INSERT INTO employee(id, firstname, lastname, age, phone, email, weekly_hour, monthly_salary)"
						+ "\nVALUES('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", idData, fnData, lnData, ageData, phoneData, emailData, whData, msData);
				System.out.println(sql_stm);
				
				try {
					// Look for the mysql driver class in CLASSPATH
					Class.forName(Data.DRIVER);
					
					// Create a connection to the database, set up a statement from the connection, and execute the statement
					conn = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
					stm = conn.createStatement();
					stm.executeUpdate(sql_stm);	
					Data.model.addRow(new Object[] {idData, fnData, lnData, ageData, phoneData, emailData, whData, msData});
					
				} catch(SQLIntegrityConstraintViolationException exc) {
					JOptionPane.showMessageDialog(MainAddFrame.this.wd, "Duplicate ID entry!");
				} catch(Exception exc) {
					System.out.println(exc);
				}
				
			} // end of if statement - collecting data
		}
	}
	
//	class FocusEvent implements FocusListener {
//
//		
//		@Override
//		public void focusGained(java.awt.event.FocusEvent e) {
//			System.out.println("focus gained");
//			
//		}
//
//		@Override
//		public void focusLost(java.awt.event.FocusEvent e) {
//			System.out.println("focus lost");
//			String whNum = MainAddFrame.inputFields.get(MainAddFrame.whInd).getText();
//			
//			if (whNum.isEmpty()) {
//				return;
//			} else {
//				int salary_m = Data.PAYRATE * Integer.parseInt(whNum) * 4;
//				MainAddFrame.inputFields.get(MainAddFrame.msInd).setText(String.valueOf(salary_m));
//				System.out.println(salary_m);
//			}
//			
//		}
//
//	}
//	
//	class KeyPressEvent implements KeyListener {
//
//		@Override
//		public void keyTyped(java.awt.event.KeyEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//
//		@Override
//		public void keyPressed(java.awt.event.KeyEvent e) {
//			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
//				System.out.println("enter pressed");
//				String whNum = MainAddFrame.inputFields.get(MainAddFrame.whInd).getText();
//				
//				if (whNum.isEmpty()) {
//					return;
//				} else {
//					int salary_m = Data.PAYRATE * Integer.parseInt(whNum) * 4;
//					MainAddFrame.inputFields.get(MainAddFrame.msInd).setText(String.valueOf(salary_m));
//					System.out.println(salary_m);
//				}
//			}
//			
//		}
//
//		@Override
//		public void keyReleased(java.awt.event.KeyEvent e) {
//			// TODO Auto-generated method stub
//			
//		}
//		
//	}
}
