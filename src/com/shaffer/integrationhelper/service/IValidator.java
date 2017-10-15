package com.shaffer.integrationhelper.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public abstract interface IValidator {
	public void validateEmployee(List<?> employee, String enteredDepartments, String enteredEmployeeTypes,
			String enteredEmployeeStatus, String enteredPayPeriods);
	
	public void validateBenefit(List<?> benefits, String enteredBenefits);
}
