package com.shaffer.integrationhelper.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shaffer.integrationhelper.model.InCodeEmployee;
import com.shaffer.integrationhelper.service.IValidator;

public class Validator implements IValidator {
	
	private List<String> payPeriods;
	private List<String> employeeTypes;
	private List<String> employeeStatus;
	private List<String> departments;
	
	
	@Override
	public List<String> ValidateDepartments(String fieldText, List<InCodeEmployee> employees, String payrollSystem) {
		List<String> inCorrectEmployeeStatus = new ArrayList<String>();
		if(!(fieldText == null)) {
		departments = new ArrayList<String>(Arrays.asList(fieldText.split("\\s*,\\s*")));
		}
		
		for (InCodeEmployee employee : employees) {
			if(!payPeriods.contains(employee.getDepartment().toString())) {
				inCorrectEmployeeStatus.add("The department " + employee.getDepartment().toString() + " is incorrect." + System.lineSeparator());
			}
		}
		return inCorrectEmployeeStatus;
	}
	
	@Override
	public List<String> ValidateEmployeeStatus(String fieldText, List<InCodeEmployee> employees, String payrollSystem) {
		List<String> inCorrectEmployeeStatus = new ArrayList<String>();
		if(!(fieldText == null)) {
		employeeStatus = new ArrayList<String>(Arrays.asList(fieldText.split("\\s*,\\s*")));
		}
		
		for (InCodeEmployee employee : employees) {
			if(!payPeriods.contains(employee.getStatus().toString())) {
				inCorrectEmployeeStatus.add("The employee status " + employee.getStatus().toString() + " is incorrect." + System.lineSeparator());
			}
		}
		return inCorrectEmployeeStatus;
	}
	@Override
	public List<String> ValidateEmployeeTypes(String fieldText, List<InCodeEmployee> employees, String payrollSystem) {
		
		List<String> inCorrectEmployeeTypes = new ArrayList<String>();
		if(!(fieldText == null)) {
		employeeTypes = new ArrayList<String>(Arrays.asList(fieldText.split("\\s*,\\s*")));
		}
		
		for (InCodeEmployee employee : employees) {
			if(!payPeriods.contains(employee.getEmployeeType().toString())) {
				inCorrectEmployeeTypes.add("The employee type " + employee.getEmployeeType().toString() + " is incorrect." + System.lineSeparator());
			}
		}
		return inCorrectEmployeeTypes;
	}
	@Override
	public List<String> ValidatePayPeriods(String fieldText, List<InCodeEmployee> employees, String payrollSystem) {
		List<String> inCorrectPayPeriods = new ArrayList<String>();
		if(!(fieldText == null)) {
		payPeriods = new ArrayList<String>(Arrays.asList(fieldText.split("\\s*,\\s*")));
		}
		
		for (InCodeEmployee employee : employees) {
			if(!payPeriods.contains(employee.getPayCycle().toString())) {
				inCorrectPayPeriods.add("The pay period " + employee.getPayCycle().toString() + " is incorrect." + System.lineSeparator());
			}
		}
		return inCorrectPayPeriods;
	}
	
	
	

}
