package employee_management;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

/**
 * This class contains the structure and layout of the Login window
 * Other Sources: SpringUtilities.java from oracle was used to help with the layout
 * @author ltai2
 *
 */
public class LoginFrame {
	private JFrame main = new JFrame();
	
	// labels
	private JLabel usr = new JLabel("USERNAME: ");
	private JLabel pw = new JLabel("PASSWORD: ");
	
	// textfields
	private JTextField usrInput = new JTextField();
	private JPasswordField pwInput = new JPasswordField();
	
	// panels
	private JPanel firstP = new JPanel();
	private JPanel secondP = new JPanel();
	private JPanel mainP = new JPanel();
	
	// button
	private JButton loginButton = new JButton("Login");
	
	public LoginFrame() {
		
		// firstP's settings
		this.firstP.setLayout(new SpringLayout());
		this.firstP.setPreferredSize(new Dimension(500,80));
		
		this.firstP.add(usr);
		this.firstP.add(usrInput);
		
		this.firstP.add(pw);	
		this.firstP.add(pwInput);
		
		this.usr.setLabelFor(usrInput);
		this.pw.setLabelFor(pwInput);
		
		this.usrInput.setName("user");
		this.pwInput.setName("pass");
		
		SpringUtilities.makeCompactGrid(firstP, 2, 2, 6, 6, 6, 6);
		
		
		// secondP's settings
		this.secondP.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		this.secondP.setPreferredSize(new Dimension(500,50));
		this.secondP.add(loginButton);
		
		// mainP setting
		// Use BoxLayout to vertically align the other 2 panels
		this.mainP.setLayout(new BoxLayout(this.mainP, BoxLayout.Y_AXIS));
		// this.mainP.setPreferredSize(new Dimension(500, 500));
		this.mainP.add(firstP);
		this.mainP.add(secondP);
		
		this.main.add(mainP);
		
		// login button event
		this.loginButton.addActionListener(new LoginButtonEvent());
		
		// main frame settings
		// this.main.add(firstP);
		// this.main.add(secondP);
		this.main.add(mainP);
		
		
		this.main.setTitle("SECURED LOGIN");
		this.main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.main.setSize(500,500);

		
		this.main.setLocationRelativeTo(null);
		this.main.setResizable(false);
		this.main.pack();
		// this.main.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		this.main.setVisible(true);
	}
	
	
	/**
	 * Inner class to handle login event
	 */
	class LoginButtonEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean correctFormat = true;
			Connection conn;
			Statement stm;
			String sql_stm = "SELECT * FROM accounts;";
			
			// Gather data from input fields
			String usrIn = LoginFrame.this.usrInput.getText();
			
			char[] pwChar = LoginFrame.this.pwInput.getPassword();
			String pwIn = "";
			
			// Check if username is empty
			if (usrIn.length() == 0) {
				JOptionPane.showMessageDialog(LoginFrame.this.main, "Username cannot be empty!");
				correctFormat = false;
			}
			
			// Check if pwChar is empty and stringify it
			if (pwChar.length == 0) {
				JOptionPane.showMessageDialog(LoginFrame.this.main, "Password cannot be empty!");
				correctFormat = false;
			} else {
				for (int i = 0; i < pwChar.length; i++) {
					pwIn = pwIn + pwChar[i];
				}
			}
			
			System.out.println("Correct Format?: " + correctFormat);
			
			// If the formats are correct, proceed with checking the inputs
			if (correctFormat == true) {
				try {
					
					// Look for the driver class and establish a connection with the mysql server
					Class.forName(Data.DRIVER);
					conn = DriverManager.getConnection(Data.CONNECTION, Data.USERNAME, Data.PASSWORD);
					
					// Create and execute the mysql statement
					stm = conn.createStatement();
					ResultSet result = stm.executeQuery(sql_stm);
					
					while (result.next()) {
						// Check if username and password are correct
						if (usrIn.equals(result.getString("usernames")) && pwIn.equals(result.getString("passwords"))) {
							// System.out.println(usrIn);
							// System.out.println(pwIn);
							
							// Upon successful login, open the main frame and hide the login frame
							MainFrame home = new MainFrame();
							LoginFrame.this.main.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(LoginFrame.this.main, "Incorrect username or password!");
							// System.out.println(pwIn);
							// System.out.println("Wrong username or password");
						}
						
					}
					
				} catch(Exception ex) {
					System.out.println(ex);
				}
			}
			
			
		}
		
	}
}
