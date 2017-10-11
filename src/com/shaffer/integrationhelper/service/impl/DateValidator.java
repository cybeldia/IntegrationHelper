package com.shaffer.integrationhelper.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

import javax.swing.JTextArea;

import com.shaffer.integrationhelper.model.InCodeEmployee;

public class DateValidator {
	//Handles the basic validation of date formats
	public static boolean isValidDate(InCodeEmployee employee, String dateFormat, JTextArea errorsTextArea)
	{
		if(employee == null) {
			return false;
		}
		
		//SDF setLenient doesn't recognize when trailing characters are off - changed to Java 8 TemporalAccessor
		//SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		//sdf.setLenient(false);
		
		try {
			//Date date = sdf.parse(employee.getBirthDate());
			TemporalAccessor ta = DateTimeFormatter.ofPattern(dateFormat).parse(employee.getBirthDate());
		} catch (Exception e) {
			String error = "Birthday is formatted incorrectly for employee: " + employee.getLastName() + ". " + "Format is currently: " + employee.getBirthDate() + System.lineSeparator();
			errorsTextArea.append(error);
			return false;
		}
		
		return true;
	}
}
