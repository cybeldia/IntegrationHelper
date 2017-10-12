package com.shaffer.integrationhelper.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.model.InCodeEmployee;

@Service
public interface IProcessorThread {
	
	public void run();
	public void setPayrollSystem(String payrollSystem);
	public void setFilePath(String filePath);
	public void setFileType(String fileType);
	public List<?> getEmployeeList();
	public List<?> getBenefitList();
}
