package employee_management;

import java.awt.event.*;
import java.sql.Time;
import java.util.Timer;
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
		System.out.println(addFrame.wd.isVisible());
		addFrame.wd.setVisible(true);
		System.out.println(addFrame.wd.isVisible());
		
//		int whInd = 0;
//		int msInd = 0;
//		
//		for (int i = 0; i < MainAddFrame.inputFields.size(); i++) {
//			if ( MainAddFrame.inputFields.get(i).getName() == "monthly salary") {
//				MainAddFrame.inputFields.get(i).setEditable(false);
//				msInd = i;
//			}
//			
//			if ( MainAddFrame.inputFields.get(i).getName() == "weekly hour") {
//				whInd = i;
//			}
//		}
//		
//		while (addFrame.wd.isVisible()) {
//			System.out.println("wh is null");
//			if (MainAddFrame.inputFields.get(whInd).getText().equals("")) {
//				continue;
//			} else {
//				break;
//			}
//		}
	}
}
