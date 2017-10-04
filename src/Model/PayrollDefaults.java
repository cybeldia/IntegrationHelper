package Model;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PayrollDefaults {
	
	public JTable setInCodeDefaults(JTable tbl) {
		
		if ((tbl.getRowCount() > 0) && (tbl.getColumnCount() > 0))
		{
			tbl.setValueAt("employeeId", 1, 6); 
			tbl.setValueAt("lastName", 2, 6); 
			tbl.setValueAt("firstName", 3, 6);
			tbl.setValueAt("phoneNumber", 4, 6);
			tbl.setValueAt("nameLogin", 6, 6);
			tbl.setValueAt("employeeNumber", 7, 6);
			tbl.setValueAt("payCycle", 8, 6);
			tbl.setValueAt("departmentName", 9, 6);
			tbl.setValueAt("employeeType", 10, 6);
			tbl.setValueAt("nameLogin", 11, 6);
			tbl.setValueAt("employeeNumber", 15, 6);
			tbl.setValueAt("status", 21, 6);
			tbl.setValueAt("hireDate", 23, 6);
			tbl.setValueAt("scheduleSet", 24, 6);
			tbl.setValueAt("middleInitial", 25, 6);
		}
		return tbl;
	}

}
