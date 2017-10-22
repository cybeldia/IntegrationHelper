package com.shaffer.integrationhelper.model;

import javax.swing.JTable;

public class PayrollDefaults {

	public JTable setInCodeDefaults(JTable tbl) {

		if ((tbl.getRowCount() > 0) && (tbl.getColumnCount() > 0)) {
			// Clear old values
			for (int row = 0; row < tbl.getRowCount(); row++) {
				tbl.setValueAt("0", row, 4);
				tbl.setValueAt("0", row, 5);
				tbl.setValueAt("", row, 6);
			}
			// Set Host Attribute values
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

			// Set Update Flags and Host attribute type

			// Employee number
			tbl.setValueAt("1", 1, 4);
			tbl.setValueAt("1", 1, 5);
			tbl.setValueAt("1", 1, 7);
			// Last Name
			tbl.setValueAt("1", 2, 4);
			tbl.setValueAt("1", 2, 5);
			tbl.setValueAt("1", 2, 7);
			// First Name
			tbl.setValueAt("1", 3, 4);
			tbl.setValueAt("1", 3, 5);
			tbl.setValueAt("1", 3, 7);
			// Phone Number
			tbl.setValueAt("1", 4, 4);
			tbl.setValueAt("1", 4, 5);
			tbl.setValueAt("1", 4, 7);
			// Password
			tbl.setValueAt("1", 6, 7);
			// Unique ID
			tbl.setValueAt("1", 7, 7);
			// Pay Cycle
			tbl.setValueAt("1", 8, 4);
			tbl.setValueAt("1", 8, 5);
			tbl.setValueAt("1", 8, 7);
			// Department
			tbl.setValueAt("1", 9, 4);
			tbl.setValueAt("1", 9, 5);
			tbl.setValueAt("1", 9, 7);
			// Employee Type
			tbl.setValueAt("1", 10, 4);
			tbl.setValueAt("1", 10, 5);
			tbl.setValueAt("1", 10, 7);
			// Login (re-hire only)
			tbl.setValueAt("1", 11, 5);
			tbl.setValueAt("1", 11, 7);
			// Badge ID (re-hire only)
			tbl.setValueAt("1", 15, 5);
			tbl.setValueAt("1", 15, 7);
			// Status
			tbl.setValueAt("1", 21, 4);
			tbl.setValueAt("1", 21, 5);
			tbl.setValueAt("1", 21, 7);
		}
		return tbl;
	}

	public JTable setHTEDefaults(JTable tbl) {

		if ((tbl.getRowCount() > 0) && (tbl.getColumnCount() > 0)) {
			// Clear old values
			for (int row = 0; row < tbl.getRowCount(); row++) {
				tbl.setValueAt("0", row, 4);
				tbl.setValueAt("0", row, 5);
				tbl.setValueAt("", row, 6);
			}
			// Set Host Attribute values
			tbl.setValueAt("EmployeeNumber", 1, 6);
			tbl.setValueAt("LastName", 2, 6);
			tbl.setValueAt("FirstName", 3, 6);
			tbl.setValueAt("PhoneNumber", 4, 6);
			tbl.setValueAt("EmailAddress", 5, 6);
			tbl.setValueAt("NameLogin", 6, 6);
			tbl.setValueAt("UniqueKey", 7, 6);
			tbl.setValueAt("PayFrequency", 8, 6);
			tbl.setValueAt("DepartmentActivityNumber", 9, 6);
			tbl.setValueAt("TimeKeepingType", 10, 6);
			tbl.setValueAt("NameLogin", 11, 6);
			tbl.setValueAt("EmployeeNumber", 15, 6);
			tbl.setValueAt("Status", 21, 6);
			tbl.setValueAt("HireDate", 23, 6);
			tbl.setValueAt("scheduleSet", 24, 6);
			tbl.setValueAt("MiddleInit", 25, 6);
			tbl.setValueAt("Suffix", 26, 6);

			// Set Update Flags and Host attribute type

			// Employee number
			tbl.setValueAt("1", 1, 4);
			tbl.setValueAt("1", 1, 5);
			tbl.setValueAt("1", 1, 7);
			// Last Name
			tbl.setValueAt("1", 2, 4);
			tbl.setValueAt("1", 2, 5);
			tbl.setValueAt("1", 2, 7);
			// First Name
			tbl.setValueAt("1", 3, 4);
			tbl.setValueAt("1", 3, 5);
			tbl.setValueAt("1", 3, 7);
			// Phone Number
			tbl.setValueAt("1", 4, 4);
			tbl.setValueAt("1", 4, 5);
			tbl.setValueAt("1", 4, 7);
			// Password
			tbl.setValueAt("1", 6, 7);
			// Unique ID
			tbl.setValueAt("1", 7, 7);
			// Pay Cycle
			tbl.setValueAt("1", 8, 4);
			tbl.setValueAt("1", 8, 5);
			tbl.setValueAt("1", 8, 7);
			// Department
			tbl.setValueAt("1", 9, 4);
			tbl.setValueAt("1", 9, 5);
			tbl.setValueAt("1", 9, 7);
			// Employee Type
			tbl.setValueAt("1", 10, 4);
			tbl.setValueAt("1", 10, 5);
			tbl.setValueAt("1", 10, 7);
			// Login (re-hire only)
			tbl.setValueAt("1", 11, 5);
			tbl.setValueAt("1", 11, 7);
			// Badge ID (re-hire only)
			tbl.setValueAt("1", 15, 5);
			tbl.setValueAt("1", 15, 7);
			// Status
			tbl.setValueAt("1", 21, 4);
			tbl.setValueAt("1", 21, 5);
			tbl.setValueAt("1", 21, 7);
		}
		return tbl;
	}

}
