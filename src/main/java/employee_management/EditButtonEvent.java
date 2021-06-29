package employee_management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

/**
 * This class contains the event handler of the Edit button in the main window
 * @author ltai2
 *
 */
public class EditButtonEvent implements ActionListener {
	MainEditFrame editFrame = new MainEditFrame();
	
	@Override
	public void actionPerformed(ActionEvent e) {		
		try {
			// Get the selected/highlighted row
			int rowInd = MainFrame.main_table.getSelectedRow();
			
			
			System.out.println(rowInd);
			// Below are the steps that make the input fields display data from the selected row
			Object idData = MainFrame.main_table.getValueAt(rowInd, 0);
			Object fnData = MainFrame.main_table.getValueAt(rowInd, 1);
			Object lnData = MainFrame.main_table.getValueAt(rowInd, 2);
			Object ageData = MainFrame.main_table.getValueAt(rowInd, 3);
			Object phoneData = MainFrame.main_table.getValueAt(rowInd, 4);
			Object emailData = MainFrame.main_table.getValueAt(rowInd, 5);
			Object whData = MainFrame.main_table.getValueAt(rowInd, 6);
			Object hpData = MainFrame.main_table.getValueAt(rowInd, 7);
			Object msData = MainFrame.main_table.getValueAt(rowInd, 8);
			
			for (int i = 0; i < MainEditFrame.inputFields.size(); i++) {
				switch(MainEditFrame.inputFields.get(i).getName()) {
					case "id":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(idData));
						break;
					case "firstname":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(fnData));
						break;
					case "lastname":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(lnData));
						break;
					case "age":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(ageData));
						break;
					case "phone":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(phoneData));
						break;
					case "email":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(emailData));
						break;
					case "weekly hour":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(whData));
						break;
					case "hourly pay":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(hpData));
						break;
					case "monthly salary":
						MainEditFrame.inputFields.get(i).setText(String.valueOf(msData));
						break;
				}
			}
			
			this.editFrame.wd.setVisible(true);
			
		} catch(ArrayIndexOutOfBoundsException exceptions) {
			JOptionPane.showMessageDialog(MainFrame.main_table, "Nothing has been selected");
			// System.out.println(exceptions);
		}
	}

}
