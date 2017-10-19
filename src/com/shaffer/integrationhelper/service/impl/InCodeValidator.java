package com.shaffer.integrationhelper.service.impl;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.shaffer.integrationhelper.events.ErrorEvent;
import com.shaffer.integrationhelper.model.InCodeBenefit;
import com.shaffer.integrationhelper.model.InCodeEmployee;
import com.shaffer.integrationhelper.service.IValidator;

public class InCodeValidator implements IValidator, ApplicationEventPublisherAware {

	private List<String> payPeriods;
	private List<String> employeeTypes;
	private List<String> employeeStatus;
	private List<String> departments;
	
	private List<String> benefits;

	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	// Create method to determine validation.
	@Override
	public void validateEmployee(List<?> employees, String enteredDepartments, String enteredEmployeeTypes,
			String enteredEmployeeStatus, String enteredPayPeriods) {

		// Save overall Errors
		List<String> errorsList = new ArrayList<String>();

		// Handle Departments
		if (enteredDepartments != null) {
			departments = new ArrayList<String>(Arrays.asList(enteredDepartments.split("\\s*,\\s*")));
		}

		if (enteredEmployeeTypes != null) {
			employeeTypes = new ArrayList<String>(Arrays.asList(enteredEmployeeTypes.split("\\s*,\\s*")));
		}

		if (enteredEmployeeStatus != null) {
			employeeStatus = new ArrayList<String>(Arrays.asList(enteredEmployeeStatus.split("\\s*,\\s*")));
		}

		if (enteredPayPeriods != null) {
			payPeriods = new ArrayList<String>(Arrays.asList(enteredPayPeriods.split("\\s*,\\s*")));
		}
		
		if(enteredDepartments.trim().isEmpty() && enteredEmployeeTypes.trim().isEmpty() && enteredEmployeeStatus.trim().isEmpty() && enteredPayPeriods.trim().isEmpty() ) {
			errorsList.add("No validation parameters supplied");
		}

		if (employees != null) {
			for (Object employeeObject : employees) {
				InCodeEmployee employee = (InCodeEmployee) employeeObject;
				if (!departments.contains(employee.getDepartment().toString())
						&& !enteredDepartments.trim().isEmpty()) {
					errorsList.add("The department " + employee.getDepartment().toString() + " is incorrect."
							+ System.lineSeparator());
				}

				if (!employeeStatus.contains(employee.getStatus().toString())
						&& !enteredEmployeeStatus.trim().isEmpty()) {
					errorsList.add("The employee status " + employee.getStatus().toString() + " is incorrect."
							+ System.lineSeparator());
				}

				if (!employeeTypes.contains(employee.getEmployeeType().toString())
						&& !enteredEmployeeTypes.trim().isEmpty()) {
					errorsList.add("The employee type " + employee.getEmployeeType().toString() + " is incorrect."
							+ System.lineSeparator());
				}

				if (!payPeriods.contains(employee.getPayCycle().toString()) && !enteredPayPeriods.trim().isEmpty()) {
					errorsList.add("The pay period " + employee.getPayCycle().toString() + " is incorrect."
							+ System.lineSeparator());
				}

				if (!(isValidDate(employee.getBirthDate(), "MM/dd/yyyy"))) {
					errorsList.add("The date " + employee.getBirthDate().toString() + " is incorrectly formatted."
							+ System.lineSeparator());
				}
			}
		}

		this.applicationEventPublisher.publishEvent(new ErrorEvent(this, errorsList));
	}
	
	public void validateBenefit(List<?> benefitList, String enteredBenefits) {

		// Save overall Errors
		List<String> errorsList = new ArrayList<String>();

		// Handle Benefits
		if (enteredBenefits != null && !enteredBenefits.isEmpty()) {
			benefits = new ArrayList<String>(Arrays.asList(enteredBenefits.split("\\s*,\\s*")));
		}

		if (benefits != null) {
			for (Object benefitObject : benefitList) {
				InCodeBenefit benefit = (InCodeBenefit)benefitObject;
				if (!benefits.contains(benefit.getHoursCode().toString().trim())
						&& !enteredBenefits.trim().isEmpty()) {
					errorsList.add("The hours code " + benefit.getHoursCode().toString() + " is incorrect."
							+ System.lineSeparator());
				}
			}
		}

		this.applicationEventPublisher.publishEvent(new ErrorEvent(this, errorsList));
	}

	public Boolean isValidDate(String date, String dateFormat) {
		if (date != null) {
			try {
				TemporalAccessor ta = DateTimeFormatter.ofPattern(dateFormat).parse(date);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}
