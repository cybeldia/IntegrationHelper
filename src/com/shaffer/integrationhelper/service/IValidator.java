package com.shaffer.integrationhelper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.model.InCodeEmployee;

@Service
public interface IValidator {

	public List<String> ValidatePayPeriods(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ValidateEmployeeTypes(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ValidateEmployeeStatus(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ValidateDepartments(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
}
