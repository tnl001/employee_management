package employee_management;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class IntFilter extends DocumentFilter {
	
	@Override
	public void insertString(DocumentFilter.FilterBypass fb, int offset, String string, AttributeSet attr) {
		boolean isInt = true;
		
		// loop thru each character to check if it is an int
		for (int i = 0; i < string.length(); i++) {
			if (Character.isDigit(string.charAt(i)) != true) {
				isInt = false;
				break;
			}
		}
		
		if (isInt == true) {
			try {
				super.insertString(fb, offset, string, attr);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) {
		boolean isInt = true;
		
		// loop thru each character to check if it is an int
		for (int i = 0; i < text.length(); i++) {
			if (Character.isDigit(text.charAt(i)) != true) {
				isInt = false;
				break;
			}
		}
		
		if (isInt == true) {
			try {
				super.replace(fb, offset, length, text, attrs);
			} catch (BadLocationException e) {
				e.printStackTrace();
			}
		}
	}
}
