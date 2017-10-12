package com.shaffer.integrationhelper.service;

import java.util.List;
import java.util.*;

import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.model.InCodeEmployee;


@Service
public interface IValidator {
	
	public List<String> Validate(String fileType,String enteredDepartments, String enteredEmployeeTypes, String enteredEmployeeStatus, String enteredPayPeriods, List<?> employees);

}
