package employee_management;

import java.awt.event.*;

/**
 * This class contains the event handler of the clear button in the Add Employee window
 * @author ltai2
 *
 */
public class ClearButtonEvent implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// Clear text from all textfields
		for (int i = 0; i < MainAddFrame.inputFields.size(); i++) {
			MainAddFrame.inputFields.get(i).setText("");
		}
	}

}
