package com.shaffer.integrationhelper.service.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

public class QueryExecuter {
	
	private PreparedStatement updateMapping = null;
	private String updateString =
			"UPDATE employee_build_mapping "
			+ " SET host_attribute_name = ?"
			+ " WHERE employee_build_mapping_id = ?";
	private String createInCodeEmployeeJob = 
			"INSERT INTO scheduled_job (class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES('test', 600, 'test desciprtion', current_timestamp, 'test job', '24:00', '22:00')";
	
	private String payrollSystem;
	private Connection conn;
	private JTable tbl;
	
	public QueryExecuter(String payrollSystem, Connection conn, JTable tbl) {
		this.payrollSystem = payrollSystem;
		this.conn = conn;
		this.tbl = tbl;

	}
	
	public void ExecuteMappingQuery() {
		
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
	
	public void CreateScheduledJobs() {
		try {
		conn.setAutoCommit(false);
		if(payrollSystem.equals("InCode"))
		conn.prepareStatement(createInCodeEmployeeJob).execute();
		conn.commit();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet GetCurrentMapping() throws Exception {

		PreparedStatement st = conn.prepareStatement("SELECT * FROM employee_build_mapping");
		ResultSet rs = st.executeQuery();
		return rs;
	}

}
