package com.shaffer.integrationhelper.service.impl;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.shaffer.integrationhelper.model.InCodeEmployee;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InCodeProcessor {

	private List<InCodeEmployee> list;

	public void InCodeValidator(String filePath) throws IllegalStateException, FileNotFoundException, IOException {
		// Validator handles the actual parsing of the InCode file.
		int expectedColumnCount = 85;
		String csvFileName = filePath;

		if (expectedColumnCount == HeaderCount(csvFileName)) {
			// Parse Csv into list of employees, later a new class will need to made that
			// inherits from employee called InCode employee
			List<InCodeEmployee> list = new CsvToBeanBuilder<InCodeEmployee>(new FileReader(csvFileName))
					.withType(InCodeEmployee.class).withThrowExceptions(true).withIgnoreLeadingWhiteSpace(true)
					.withVerifyReader(true).build().parse();
			this.list = list;

		}
	}

	// Maybe seperate this out into another class later once the app handles more
	// than InCode.
	public int HeaderCount(String path) {

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

	public List<InCodeEmployee> getList() {
		return list;
	}
	
}
