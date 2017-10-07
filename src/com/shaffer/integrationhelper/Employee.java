package com.shaffer.integrationhelper;
import java.io.Serializable;
import java.lang.reflect.Field;

import com.opencsv.bean.CsvBindByName;

public class Employee implements Serializable {
	
	//Basic fields required by all employee's across all payroll systems
	@CsvBindByName
	private String name;
	@CsvBindByName
	private String birthday;
	@CsvBindByName
	private String address;
	@CsvBindByName
	private String employeeType;
	@CsvBindByName
	private String payPeriod;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getPayPeriod() {
		return payPeriod;
	}
	public void setPayPeriod(String payPeriod) {
		this.payPeriod = payPeriod;
	}
	
	@Override
	public String toString() {
		return name +  ", " + birthday + ", " + address + ", " + employeeType + ", " + payPeriod + System.lineSeparator();
	}
	 
	//I doubt that this will be needed later. OpenCsv has an annotation to require certain fields and this method incorrectly checks null on all of them
	public boolean isNull() {
		Field fields[] = this.getClass().getDeclaredFields();
		for(Field f : fields) {
			try {
				Object value = f.get(this);
				if(value == null) {
					System.out.println("Some values are null. Number of fields does not match expected schema.");
					return false;
				}
			}
			catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
