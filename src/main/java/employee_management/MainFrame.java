package employee_management;

import javax.swing.*;
import java.awt.*;

/**
 * This class contains the structure and layout of the main window
 * @author ltai2
 *
 */
public class MainFrame {
	
	// Components that will be used
	private JFrame main_window;
	protected static JTable main_table;
	private JScrollPane main_scroll;
	private JPanel table_panel;
	private JPanel buttons_panel;
	private JPanel main_panel;
	
	private JButton edit_button;
	private JButton add_button;
	private JButton del_button;
	
	protected static Data data = new Data();
	
	/**
	 * Default constructor for MainFrame that will create the main window
	 * Contains the main table and 3 buttons.
	 */
	public MainFrame() {
		
		
		// Create the entire main frame
		this.main_window = new JFrame();
		this.main_window.setTitle("Home");
		this.main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.main_window.setSize(1000, 1000);
		this.main_window.setLocationRelativeTo(null);
		this.main_window.setResizable(false);
		
		
		
		
		

		// TABLE / SCROLL
		// Create the table and the scroll panel and put the table inside the scroll panel
		main_table = data.table;
		
		// Disable re-ordering ability of columns
		main_table.getTableHeader().setReorderingAllowed(false);
		
		this.main_scroll = new JScrollPane(main_table);
		this.main_scroll.setBorder(BorderFactory.createEmptyBorder());
		this.main_scroll.getViewport().setBackground(new Color(100,100,100));
		this.main_scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		this.main_scroll.setPreferredSize(new Dimension(900,500));
		this.main_scroll.setBackground(Color.black);
		
		
		

		// EDIT, ADD, AND DELETE BUTTONS
		this.edit_button = new JButton("Edit");
		this.edit_button.setPreferredSize(new Dimension(100,20));
		this.edit_button.addActionListener(new EditButtonEvent());
		
		this.add_button = new JButton("Add");
		this.add_button.setPreferredSize(new Dimension(100,20));
		this.add_button.addActionListener(new AddButtonEvent());
		
		this.del_button = new JButton("Delete");
		this.del_button.setPreferredSize(new Dimension(100,20));
		this.del_button.addActionListener(new DeleteButtonEvent());
		
		
		
 
		// PANEL FOR TABLE AND SCROLL
		// Create the panel for table and put the scroll inside the panel
		this.table_panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.table_panel.setPreferredSize(new Dimension(1000,500));
		this.table_panel.setBackground(new Color(200,200,200));
		this.table_panel.add(this.main_scroll);
		
		
		
		

		// PANEL FOR BUTTONS
		// Create the panel for buttons
		this.buttons_panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		this.buttons_panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		// The remaining 50px will be allocated for the buttons (550-500 = 50)
		this.buttons_panel.setPreferredSize(new Dimension(1000,50));
		this.buttons_panel.add(this.edit_button);
		this.buttons_panel.add(this.add_button);
		this.buttons_panel.add(this.del_button);
		
		
		
		
		
		// Add the table panel into the frame
		// this.main_window.add(this.table_panel);
		// this.main_window.add(this.buttons_panel);		
		this.main_panel = new JPanel();
		this.main_panel.setLayout(new BoxLayout(this.main_panel, BoxLayout.Y_AXIS));
		this.main_panel.add(this.table_panel);
		this.main_panel.add(this.buttons_panel);
		
		// Display the frame on screen
		this.main_window.add(this.main_panel);
		this.main_window.pack();
		// this.main_window.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0));
		this.main_window.setVisible(true);
		
	}
	

	public JTable getTable() {
		return main_table;
	}

	public Data getData() {
		return data;
	}
	
}
