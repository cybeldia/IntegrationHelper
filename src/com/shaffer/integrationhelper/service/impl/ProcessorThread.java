package com.shaffer.integrationhelper.service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.swing.JTextArea;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.controller.*;
import com.shaffer.integrationhelper.model.InCodeEmployee;
import com.shaffer.integrationhelper.service.IProcessorThread;

//Class should control what processor to use based on the payroll system


public class ProcessorThread implements Runnable, IProcessorThread {
	
	private String payrollSystem;
	private String filePath;
	private List<InCodeEmployee> employeeList;
	
	@Override
	public void run() {
		if (payrollSystem.equals("InCode")) {
			InCodeProcessor inCodeProcessor = new InCodeProcessor();
			try {
				
				inCodeProcessor.InCodeValidator(filePath);
				employeeList = inCodeProcessor.getList();
			
			} catch (IllegalStateException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
	
	public String ParsedLines() {
		String parsedLines ="";
		for(InCodeEmployee employee : employeeList) {
			parsedLines += employee.toString();
		}
		return parsedLines;
	}

	public String getPayrollSystem() {
		return payrollSystem;
	}


	public void setPayrollSystem(String payrollSystem) {
		this.payrollSystem = payrollSystem;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public List<InCodeEmployee> getEmployeeList() {
		return employeeList;
	}
	
}
