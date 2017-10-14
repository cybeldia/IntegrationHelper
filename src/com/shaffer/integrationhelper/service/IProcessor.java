package com.shaffer.integrationhelper.service;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface IProcessor {

	public void run();

	public void setPayrollSystem(String payrollSystem);

	public void setFilePath(String filePath);

	public void setFileType(String fileType);

	public List<?> getEmployeeList();

	public List<?> getBenefitList();
}
