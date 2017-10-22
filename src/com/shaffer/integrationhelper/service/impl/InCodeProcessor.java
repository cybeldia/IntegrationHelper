package com.shaffer.integrationhelper.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;
import com.shaffer.integrationhelper.model.InCodeBenefit;
import com.shaffer.integrationhelper.model.InCodeEmployee;

public class InCodeProcessor implements ApplicationEventPublisherAware {

	private List<InCodeEmployee> employeeList;
	private List<InCodeBenefit> benefitList;
	private ApplicationEventPublisher applicationEventPublisher = null;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	public void processEmployee(String filePath) throws IllegalStateException, FileNotFoundException, IOException {
		// Validator handles the actual parsing of the InCode file.
		int expectedColumnCount = 85;
		String csvFileName = filePath;
		if (expectedColumnCount == headerCount(csvFileName)) {
			// Parse Csv into list of employees, later a new class will need to made that
			// inherits from employee called InCode employee
			List<InCodeEmployee> list = new CsvToBeanBuilder<InCodeEmployee>(new FileReader(csvFileName))
					.withType(InCodeEmployee.class).withThrowExceptions(true).withIgnoreLeadingWhiteSpace(true)
					.withVerifyReader(true).build().parse();
			this.employeeList = list;
		} else {
			JOptionPane.showMessageDialog(null, "Error: File does not match InCode employee format");
		}

	}

	public void processBenefit(String filePath) throws IllegalStateException, FileNotFoundException, IOException {
		// Validator handles the actual parsing of the InCode file.
		int expectedColumnCount = 3;
		String csvFileName = filePath;
		if (expectedColumnCount == headerCount(csvFileName)) {
			// Parse Csv into list of employees, later a new class will need to made that
			// inherits from employee called InCode employee
			List<InCodeBenefit> list = new CsvToBeanBuilder<InCodeBenefit>(new FileReader(csvFileName))
					.withType(InCodeBenefit.class).withThrowExceptions(true).withIgnoreLeadingWhiteSpace(true)
					.withVerifyReader(true).build().parse();
			this.benefitList = list;
		} else {
			JOptionPane.showMessageDialog(null, "Error: File does not match InCode benefit format");
		}
	}

	// Maybe seperate this out into another class later once the app handles more
	// than InCode.
	public int headerCount(String path) {

		int columnCount = 0;
		try {
			CSVReader csvReader = new CSVReader(new FileReader(path), ',', '\'');
			String[] header = csvReader.readNext();
			if (header != null) {
				columnCount = header.length;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Please select a file to process");
		}
		return columnCount;
	}

	public List<InCodeEmployee> getEmployeeList() {
		return employeeList;
	}

	public List<InCodeBenefit> getBenefitList() {
		return benefitList;
	}

}
