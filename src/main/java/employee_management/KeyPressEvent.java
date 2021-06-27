package employee_management;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JTextField;

class KeyPressEvent implements KeyListener {

	protected ArrayList<JTextField> inputFields = new ArrayList<>();
	
	// Constructor
	public KeyPressEvent(ArrayList<JTextField> inputFieldsArg) {
		this.inputFields = inputFieldsArg;
	}
	
	@Override
	public void keyTyped(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(java.awt.event.KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			System.out.println("enter pressed");
			String whNum = this.inputFields.get(MainAddFrame.whInd).getText();
			
			if (whNum.isEmpty()) {
				return;
			} else {
				int salary_m = Data.PAYRATE * Integer.parseInt(whNum) * 4;
				this.inputFields.get(MainAddFrame.msInd).setText(String.valueOf(salary_m));
				System.out.println(salary_m);
			}
		}
		
	}

	@Override
	public void keyReleased(java.awt.event.KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
