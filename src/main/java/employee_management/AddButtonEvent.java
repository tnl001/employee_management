package employee_management;

import java.awt.event.*;
/**
 * This class contains the event handler of the add button in the main window
 * @author ltai2
 *
 */
public class AddButtonEvent implements ActionListener {
	MainAddFrame addFrame = new MainAddFrame();	
	
	@Override
	/**
	 * Overriding the actionPerformed method from ActionListener interface
	 * @param e of type ActionEven
	 * @return void
	 */
	public void actionPerformed(ActionEvent e) {
		addFrame.wd.setVisible(true);
	}
}
