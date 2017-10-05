package service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import controller.*;

import javax.swing.JTextArea;

import org.springframework.stereotype.Service;

import service.ProcessorThreadService;

//Class should control what processor to use based on the payroll system
@Service("processorThread")
public class ProcessorThread implements Runnable, ProcessorThreadService {

	private String payrollSystem;
	private String filePath;
	private List<String> payPeriods;
	private List<String> employeeTypes;
	private List<String> employeeStatus;
	private List<String> departments;

	public void ProcessorThread(String payrollSystem, String filePath) {
		this.payrollSystem = payrollSystem;
		this.filePath = filePath;
	}

	@Override
	public void run() {
		if (payrollSystem.equals("InCode")) {
			InCodeProcessor inCodeProcessor = new InCodeProcessor();
			try {
				
				inCodeProcessor.InCodeValidator(filePath);
				inCodeProcessor.ValidateDepartments(payPeriods);
				inCodeProcessor.ValidateEmployeeStatus(employeeStatus);
				inCodeProcessor.ValidateEmployeeTypes(employeeTypes);
				inCodeProcessor.ValidatePayPeriods(payPeriods);
			
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void setPayrollSystem(String payrollSystem) {
		this.payrollSystem = payrollSystem;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setPayPeriods(List<String> payPeriods) {
		this.payPeriods = payPeriods;
	}

	public void setEmployeeTypes(List<String> employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

	public void setEmployeeStatus(List<String> employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}
	
	
}
