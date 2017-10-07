package com.shaffer.integrationhelper.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.model.InCodeEmployee;

@Service
public interface IValidator {

	public List<String> ICValidatePayPeriods(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ICValidateEmployeeTypes(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ICValidateEmployeeStatus(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
	public List<String> ICValidateDepartments(String fieldText, List<InCodeEmployee> employees, String payrollSystem);
}
