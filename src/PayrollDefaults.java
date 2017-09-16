import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PayrollDefaults {
	
	public JTable setInCodeDefaults(JTable tm) {
		
		tm.setValueAt("Test", 1, 1);
		return tm;
	}

}
