import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class PayrollDefaults {
	
	public JTable setInCodeDefaults(JTable tbl) {
		
		if (!(tbl.getRowCount() > 0) && (tbl.getColumnCount() > 0))
		{
			tbl.setValueAt("Test", 1, 1);
		}
		return tbl;
	}

}
