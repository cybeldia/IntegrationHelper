import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JTable;

public class QueryExecuter {
	
	PreparedStatement updateMapping = null;
	String updateString =
			"UPDATE employee_build_mapping "
			+ " SET host_attribute_name = ?"
			+ " WHERE employee_build_mapping_id = ?";
	
	public void QueryExecuter(JTable tbl, Connection conn) {
		
		try {
			conn.setAutoCommit(false);
			updateMapping = conn.prepareStatement(updateString);
			
			for (int row = 0; row < tbl.getRowCount(); row++) {
				updateMapping.setString(1, tbl.getValueAt(row, 6).toString());
				updateMapping.setInt(2, (int)tbl.getValueAt(row, 0));
				updateMapping.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
