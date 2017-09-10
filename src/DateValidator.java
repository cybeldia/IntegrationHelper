import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JTextArea;

public class DateValidator {
	//Handles the basic validation of date formats
	public static boolean isValidDate(Employee employee, String dateFormat, JTextArea errorsTextArea)
	{
		if(employee == null) {
			return false;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);
		
		try {
			Date date = sdf.parse(employee.getBirthday());
		} catch (ParseException e) {
			String error = "Birthday is formatted incorrectly for employee: " + employee.getName() + ". " + "Format is currently: " + employee.getBirthday() + System.lineSeparator();
			errorsTextArea.append(error);
			return false;
		}
		
		return true;
	}
}
