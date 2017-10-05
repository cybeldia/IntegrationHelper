package service;

import java.util.List;

import model.InCodeEmployee;

public interface ProcessorService {
	public List<String> ValidatePayPeriods(List<String> payPeriods);
	public List<String> ValidateEmployeeTypes(List<String> employeeTypes);
	public List<String> ValidateEmployeeStatus(List<String> employeeStatus);
	public List<String> ValidateDepartments(List<String> departments);
}
