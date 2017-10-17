package com.shaffer.integrationhelper.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;

import com.shaffer.integrationhelper.model.ApplicationSettings;

public class QueryExecuter {
	
	@Autowired
	private ApplicationSettings applicationSettings;
	private PreparedStatement updateMapping = null;
	
	//Update employee build mapping
	private String updateString = "UPDATE employee_build_mapping " + " SET host_attribute_name = ? ,"
			+ " update_flag = ? ,"
			+"  rehire_update_flag = ? ,"
			+ " host_attribute_type = ? "
			+ " WHERE employee_build_mapping_id = ?";
	
	//Create InCode jobs
	private String createInCodeEmployeeJob = "INSERT INTO scheduled_job (active, class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES(0,'net.executime.dataimport.EmployeeIntegrator', 600, 'Imports employee information from payroll', current_timestamp, 'Employee Integration', '24:00', '22:00')";
	
	
	
	//Configure default admin properties
	private String passSalariedEntries = "update property set value = 'true' where property_id = 150";
	private String exportOnlyTimeEntryDetails = "update property set value = 'true' where property_id = 240";
	private String enableMTPDownload = "update property set value = 'true' where property_id = 334";
	private String enableTimeKeeping = "update property set value = 'true' where property_id = 461";
	private String enablePreview = "update property set value = 'true' where property_id = 624";
	
	//Configure default locations
	private String updateLocation ="update location set name = '0001', description = '' where location_id = 1";
	private String deleteOutpost = "delete from location where location_id = 2";
	
	//Configure default departments
	
	
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
				//Set host Attribute name
				updateMapping.setString(1, tbl.getValueAt(row, 6).toString());
				//Set update flag
				updateMapping.setString(2, tbl.getValueAt(row, 4).toString());
				//Set rehire_update
				updateMapping.setString(3, tbl.getValueAt(row, 5).toString());
				//Set attribute type
				updateMapping.setString(4, tbl.getValueAt(row, 7).toString());
				//Build Mapping ID
				updateMapping.setInt(5, (int) tbl.getValueAt(row, 0));
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
			if (payrollSystem.equals("InCode"))
				conn.prepareStatement(createInCodeEmployeeJob).execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ResultSet GetCurrentMapping() throws Exception {

		PreparedStatement st = conn.prepareStatement("SELECT * FROM employee_build_mapping");
		ResultSet rs = st.executeQuery();
		return rs;
	}

}
