package com.shaffer.integrationhelper.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.shaffer.integrationhelper.events.ParsedLineEvent;
import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.service.IProcessor;
import com.shaffer.integrationhelper.service.IValidator;

//Class should control what processor to use based on the payroll system

public class ProcessorThread implements Runnable, IProcessor, ApplicationEventPublisherAware {
	@Autowired
	private ApplicationSettings applicationSettings;
	@Autowired
	private IValidator validator;

	private String payrollSystem;
	private String filePath;
	private String fileType;

	private String payPeriods;
	private String employeeTypes;
	private String employeeStatus;
	private String departments;
	
	private String benefits;

	private List<?> employeeList;
	private List<?> benefitList;

	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void run() {

		// Get settings
		payrollSystem = applicationSettings.getPayrollSystem();
		fileType = applicationSettings.getFileType();
		filePath = applicationSettings.getFlatFileTextField();

		payPeriods = applicationSettings.getPayPeriods();
		employeeTypes = applicationSettings.getEmployeeTypes();
		employeeStatus = applicationSettings.getEmployeeStatus();
		departments = applicationSettings.getDepartments();
		
		benefits = applicationSettings.getBenefits();

		// Determine payroll system and file type
		// InCode Logic
		if (payrollSystem.equals("InCode")) {
			InCodeProcessor inCodeProcessor = new InCodeProcessor();
			try {
				if (fileType.equals("Employee")) {
					inCodeProcessor.processEmployee(filePath);
					employeeList = inCodeProcessor.getEmployeeList();
					validator.validateEmployee(employeeList, departments, employeeTypes, employeeStatus, payPeriods);
					if(employeeList != null) {
					this.applicationEventPublisher.publishEvent(new ParsedLineEvent(this, employeeList));
					}

				} else if (fileType.equals("Benefit")) {
					System.out.println(applicationSettings.getBenefits());
					inCodeProcessor.processBenefit(filePath);
					benefitList = inCodeProcessor.getBenefitList();
					validator.validateBenefit(benefitList, benefits);
					if(benefitList != null) {
					this.applicationEventPublisher.publishEvent(new ParsedLineEvent(this, benefitList));
					}
				}
			} catch (IOException e) {
				
			}
		}
	}

	public String getPayrollSystem() {
		return payrollSystem;
	}

	@Override
	public void setPayrollSystem(String payrollSystem) {
		this.payrollSystem = payrollSystem;
	}

	public String getFilePath() {
		return filePath;
	}

	@Override
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public List<?> getEmployeeList() {
		return employeeList;
	}

	public String getFileType() {
		return fileType;
	}

	@Override
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	@Override
	public List<?> getBenefitList() {
		return benefitList;
	}

}
