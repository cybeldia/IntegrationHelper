package com.shaffer.integrationhelper.model;

import java.io.Serializable;

import com.opencsv.bean.CsvBindByName;

public class InCodeBenefit implements Serializable {
	@CsvBindByName(column = "Emp #", required = true)
	private String employeeNumber;
	@CsvBindByName(column = "Hours Code", required = true)
	private String hoursCode;
	@CsvBindByName(column = "Current Balance", required = true)
	private String currentBalance;

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public String getHoursCode() {
		return hoursCode;
	}

	public void setHoursCode(String hoursCode) {
		this.hoursCode = hoursCode;
	}

	public String getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(String currentBalance) {
		this.currentBalance = currentBalance;
	}

	@Override
	public String toString() {
		return "InCodeBenefit [employeeNumber=" + employeeNumber + ", hoursCode=" + hoursCode + ", currentBalance="
				+ currentBalance + "]" + System.lineSeparator();
	}
}
