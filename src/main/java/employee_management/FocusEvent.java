package employee_management;

import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.JTextField;

public class FocusEvent implements FocusListener {

	protected ArrayList<JTextField> inputFields = new ArrayList<>();
	
	// Constructor
	public FocusEvent(ArrayList<JTextField> inputFieldsArg) {
		this.inputFields = inputFieldsArg;
	}
	
	
	@Override
	public void focusGained(java.awt.event.FocusEvent e) {
		System.out.println("focus gained");
		
	}

	@Override
	public void focusLost(java.awt.event.FocusEvent e) {
		System.out.println("focus lost");
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
