package com.shaffer.integrationhelper.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;

@Component
public class QueryExecuter {

	@Autowired
	private ApplicationSettings applicationSettings;
	@Autowired
	private XMLToDBConnection xmlToDBConnection;
	private PreparedStatement updateMapping = null;
	private PreparedStatement benefitJobPS = null;
	private PreparedStatement employeeJobPS = null;

	// Update employee build mapping
	private String updateString = "UPDATE employee_build_mapping " + " SET host_attribute_name = ? ,"
			+ " update_flag = ? ," + "  rehire_update_flag = ? ," + " host_attribute_type = ? "
			+ " WHERE employee_build_mapping_id = ?";

	// Create employee job
	private String createEmployeeJob = "INSERT INTO scheduled_job (active, class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES(0,'net.executime.dataimport.EmployeeIntegrator', 600, 'Imports employee information from payroll', current_timestamp, 'Employee Integration', '24:00', '21:00')";
	// Create InCode jobs
	private String setInCodeEmployeeHost = "update property set value = 'net.executime.dataimport.TylerEmployeeBuildHostInterface' where property_id = 126";

	private String createInCodeBenefitJob = "INSERT INTO scheduled_job (active, class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES(0,'net.executime.dataimport.TylerBenefitBuild', 600, 'Imports benefit information from payroll', current_timestamp, 'Benefit Integration', '24:00', '23:00')";

	// Create HTE jobs
	private String setHTEEmployeeHost = "update property set value = ? where property_id = 126";

	private String createHTEDepartmentJob = "INSERT INTO scheduled_job (active, class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES(0,'net.executime.dataimport.organizationunit.SungardHteDepartmentBuild', 600, 'Imports department information from payroll', current_timestamp, 'Department Integration', '24:00', '20:00')";

	private String createHTEBenefitJob = "INSERT INTO scheduled_job (active, class_name, deadlock_timeout, description, modification_timestamp, name, repeat_interval, start_time) "
			+ " VALUES(0,?, 600, 'Imports benefit information from payroll', current_timestamp, 'Benefit Integration', '24:00', '22:00')";

	// Configure default admin properties
	private String passSalariedEntries = "update property set value = 'true' where property_id = 150";
	private String exportOnlyTimeEntryDetails = "update property set value = 'true' where property_id = 240";
	private String enableMTPDownload = "update property set value = 'true' where property_id = 334";
	private String enableTimeKeeping = "update property set value = 'true' where property_id = 461";
	private String enablePreview = "update property set value = 'true' where property_id = 624";

	// Configure default locations
	private String updateLocation = "update location set name = '0001', description = '' where location_id = 1";
	private String deleteOutpost = "delete from location where location_id = 2";

	// Configure Org units
	private String deleteDepartments = "delete from department where department_id in (2, 3, 4)";
	private String updateDepartment = "update department set name = 'Test Department', description = 'For Test and Admin Users' where department_id = 1 ";
	private String deleteDivision = "delete from division where division_id = 2";
	private String updateDivision = "update division set name = 'Test Division', description = 'For Test and Admin Users' where division_id = 1";
	private String updateCompany = "update company set name = 'Test Company', description = 'For Test and Admin Users' where company_id = 1";

	public QueryExecuter() {

	}

	public void executeMappingQuery(JTable tbl) throws Exception {
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		try {

			conn.setAutoCommit(false);
			updateMapping = conn.prepareStatement(updateString);

			for (int row = 0; row < tbl.getRowCount(); row++) {
				// Set host Attribute name
				updateMapping.setString(1, tbl.getValueAt(row, 6).toString());
				// Set update flag
				updateMapping.setString(2, tbl.getValueAt(row, 4).toString());
				// Set rehire_update
				updateMapping.setString(3, tbl.getValueAt(row, 5).toString());
				// Set attribute type
				updateMapping.setString(4, tbl.getValueAt(row, 7).toString());
				// Build Mapping ID
				updateMapping.setInt(5, (int) tbl.getValueAt(row, 0));
				updateMapping.executeUpdate();
				conn.commit();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			conn.close();
		}

	}

	public void createScheduledJobs() throws Exception {
		
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		try {
			conn.setAutoCommit(false);
			conn.prepareStatement(createEmployeeJob).execute();
			if (applicationSettings.getPayrollSystem().equals("InCode")) {
				conn.prepareStatement(setInCodeEmployeeHost).execute();
				conn.prepareStatement(createInCodeBenefitJob).execute();
			} else if (applicationSettings.getPayrollSystem().equals("Sungard HTE")) {
				benefitJobPS = conn.prepareStatement(createHTEBenefitJob);
				benefitJobPS.setString(1, applicationSettings.getBenefitJob());
				employeeJobPS = conn.prepareStatement(setHTEEmployeeHost);
				employeeJobPS.setString(1, applicationSettings.getEmployeeJob());
				conn.prepareStatement(createHTEDepartmentJob).execute();
				benefitJobPS.executeUpdate();
				employeeJobPS.executeUpdate();
			}
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public CachedRowSet getCurrentMapping() throws Exception {

		
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
		try {
			PreparedStatement st = conn.prepareStatement("SELECT * FROM employee_build_mapping");
			ResultSet rs = st.executeQuery();
			crs.populate(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		return crs;
	}

	public void executeAdminProperties() throws Exception {
		
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		try {
			conn.setAutoCommit(false);
			conn.prepareStatement(passSalariedEntries).execute();
			conn.prepareStatement(exportOnlyTimeEntryDetails).execute();
			conn.prepareStatement(enableMTPDownload).execute();
			conn.prepareStatement(enableTimeKeeping).execute();
			conn.prepareStatement(enablePreview).execute();
			conn.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void cleanUpLocations() throws Exception {
	
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		try {
			conn.setAutoCommit(false);
			conn.prepareStatement(updateLocation).execute();
			conn.prepareStatement(deleteOutpost).execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

	public void setupOrgUnits() throws Exception {
		
		Connection conn = xmlToDBConnection.DBConnection(applicationSettings.getDatabaseTextField());
		try {
			conn.setAutoCommit(false);
			conn.prepareStatement(deleteDepartments).execute();
			conn.prepareStatement(updateDepartment).execute();
			conn.prepareStatement(deleteDivision).execute();
			conn.prepareStatement(updateDivision).execute();
			conn.prepareStatement(updateCompany).execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
	}

}
