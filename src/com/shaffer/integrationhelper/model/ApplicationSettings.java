package com.shaffer.integrationhelper.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class ApplicationSettings {

	// General settings
	private String payrollSystem = "InCode";
	private String fileType = "Employee";
	private String version = "4.x";

	// File paths
	private String flatFileTextField;
	private String databaseTextField;

	// Checkbox settings
	private Boolean scheduledJobs;
	private Boolean defaultDepartments;
	private Boolean defaultLocations;
	private Boolean defaulAdminProperties;

	// Validator Settings
	private String payPeriods = " ";
	private String employeeTypes = " ";
	private String employeeStatus = " ";
	private String departments = " ";

	private String benefits = " ";

	private String benefitJob = " ";

	// add all payroll systems that have job options here
	private List<String> hasJobSettings = new ArrayList<>(Arrays.asList("Sungard HTE"));

	public String getPayrollSystem() {
		return payrollSystem;
	}

	public void setPayrollSystem(String payrollSystem) {
		this.payrollSystem = payrollSystem;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getFlatFileTextField() {
		return flatFileTextField;
	}

	public void setFlatFileTextField(String flatFileTextField) {
		this.flatFileTextField = flatFileTextField;
	}

	public String getDatabaseTextField() {
		return databaseTextField;
	}

	public void setDatabaseTextField(String databaseTextField) {
		this.databaseTextField = databaseTextField;
	}

	public Boolean getScheduledJobs() {
		return scheduledJobs;
	}

	public void setScheduledJobs(Boolean scheduledJobs) {
		this.scheduledJobs = scheduledJobs;
	}

	public Boolean getDefaultDepartments() {
		return defaultDepartments;
	}

	public void setDefaultDepartments(Boolean defaultDepartments) {
		this.defaultDepartments = defaultDepartments;
	}

	public Boolean getDefaultLocations() {
		return defaultLocations;
	}

	public void setDefaultLocations(Boolean defaultLocations) {
		this.defaultLocations = defaultLocations;
	}

	public Boolean getDefaulAdminProperties() {
		return defaulAdminProperties;
	}

	public void setDefaulAdminProperties(Boolean defaulAdminProperties) {
		this.defaulAdminProperties = defaulAdminProperties;
	}

	public String getPayPeriods() {
		return payPeriods;
	}

	public void setPayPeriods(String payPeriods) {
		this.payPeriods = payPeriods;
	}

	public String getEmployeeTypes() {
		return employeeTypes;
	}

	public void setEmployeeTypes(String employeeTypes) {
		this.employeeTypes = employeeTypes;
	}

	public String getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(String employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public String getDepartments() {
		return departments;
	}

	public void setDepartments(String departments) {
		this.departments = departments;
	}

	public String getBenefits() {
		return benefits;
	}

	public void setBenefits(String benefits) {
		this.benefits = benefits;
	}

	public String getBenefitJob() {
		return benefitJob;
	}

	public void setBenefitJob(String benefitJob) {
		this.benefitJob = benefitJob;
	}

	public List<String> getHasJobSettings() {
		return hasJobSettings;
	}

	public void setHasJobSettings(List<String> hasJobSettings) {
		this.hasJobSettings = hasJobSettings;
	}

}
