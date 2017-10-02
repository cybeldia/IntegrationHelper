import java.io.Serializable;
import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class InCodeEmployee implements Serializable {
	
	@CsvBindByName (column = "Employee ID", required = true)
	private String employeeId;
	@CsvBindByName (column = "Payroll Number", required = true)
	private String payrollNumber;
	@CsvBindByName (column = "Employee Number", required = true)
	private String employeeNumber;
	@CsvBindByName (column = "Full Name")
	private String fullName;
	@CsvBindByName (column = "Last Name", required = true)
	private String lastName;
	@CsvBindByName (column = "First Name", required = true)
	private String firstName;
	@CsvBindByName (column = "MI")
	private String mi;
	@CsvBindByName (column = "Address 1")
	private String address1;
	@CsvBindByName (column = "Address 2")
	private String address2;
	@CsvBindByName (column = "City")
	private String city;
	@CsvBindByName (column = "State")
	private String State;
	@CsvBindByName (column = "Zip")
	private String zipCode;
	@CsvBindByName (column = "Social Security")
	private String SSN;
	@CsvBindByName (column = "Pay Cycle",required = true)
	private String payCycle;
	@CsvBindByName (column = "Other Cycle")
	private String otherCycle;
	@CsvBindByName (column = "Filing Marital Status")
	private String filingStatus;
	@CsvBindByName (required = true)
	private String status;
	@CsvBindByName (column = "Pay Type", required = true)
	private String payType;
	@CsvBindByName (column = "Scheduled Hours")
	private String scheduledHours;
	@CsvBindByName (column = "Annual Hours")
	private String annualHours;
	@CsvBindByName
	private String dependants;
	@CsvBindByName (column = "Dependents State")
	private String dependentsState;
	@CsvBindByName (column = "FICA Status")
	private String ficaStatus;
	@CsvBindByName (column = "Home State")
	private String homeState;
	@CsvBindByName (column = "Work State")
	private String workState;
	@CsvBindByName (column = "Federal Exempt")
	private String federalExempt;
	@CsvBindByName (column = "Fed Fixed Withholding")
	private String federalFixedWitholding;
	@CsvBindByName (column = "Addl Fed Withholding")
	private String additionalFederalWitholding;
	@CsvBindByName (column = "State Exempt")
	private String stateExempt;
	@CsvBindByName (column = "State Fixed Withholding")
	private String stateFixedWitholding;
	@CsvBindByName (column = "Addl State Withholding")
	private String additionalStateWitholding;
	@CsvBindByName (column = "State Exempt Amount")
	private String stateExemptAmount;
	@CsvBindByName (column = "State Tax Table")
	private String stateTaxTable;
	@CsvBindByName (column = "Withholding Percent")
	private String witholdingPercent;
	@CsvBindByName (column = "Estimated Check")
	private String estimatedCheck;
	@CsvBindByName (column = "EIC Status")
	private String EICStatus;
	@CsvBindByName (column = "Actual Marital Status")
	private String maritialStatus;
	@CsvBindByName (column = "Rate Class")
	private String rateClass;
	@CsvBindByName (column = "Longevity Code")
	private String longevityCode;
	@CsvBindByName (column = "Title")
	private String title;
	@CsvBindByName (column = "Department", required = true)
	private String department;
	@CsvBindByName (column = "Full-Part Time")
	private String fullPartTime;
	@CsvBindByName (column = "Employee Type", required = true)
	private String employeeType;
	@CsvBindByName (column = "Birth Date")
	private String birthDate;
	@CsvBindByName (column = "Sex")
	private String sex;
	@CsvBindByName (column = "Race")
	private String race;
	@CsvBindByName (column = "Credit Union")
	private String creditUnion;
	@CsvBindByName (column = "Phone")
	private String phoneNumber;
	@CsvBindByName (column = "Last Increase")
	private String lastIncrease;
	@CsvBindByName (column = "Hire Date")
	private String hireDate;
	@CsvBindByName (column = "Fire Date")
	private String terminationDate;
	@CsvBindByName (column = "Longevity Date")
	private String longevityDate;
	@CsvBindByName (column = "Leave Date")
	private String leaveDate;
	@CsvBindByName (column = "Service Date")
	private String serviceDate;
	@CsvBindByName (column = "EE04 Category")
	private String EE04Category;
	@CsvBindByName (column = "Retirement Code")
	private String retirementCode;
	@CsvBindByName (column = "Emergency Contact")
	private String emergencyContact;
	@CsvBindByName (column = "Emergency Relation")
	private String emergencyRelation;
	@CsvBindByName (column = "Emergency Phone")
	private String emergencyPhone;
	@CsvBindByName (column = "Emergency Address")
	private String emergencyAddress;
	@CsvBindByName (column = "Emergency CSZ")
	private String emergencyCSZ;
	@CsvBindByName (column = "Termination Code")
	private String terminationCode;
	@CsvBindByName (column = "Military Code")
	private String militaryCode;
	@CsvBindByName (column = "Veteran Code")
	private String veteranCode;
	@CsvBindByName (column = "Disability Code")
	private String disabilityCode;
	@CsvBindByName (column = "I9 Flag")
	private String I9Flag;
	@CsvBindByName (column = "Disabled Veteran")
	private String disabledVeteran;
	@CsvBindByName (column = "Days Worked")
	private String daysWorked;
	@CsvBindByName (column = "Hours Per Day")
	private String hoursPerDay;
	@CsvBindByName (column = "State Employ Date")
	private String stateEmployementDate;
	@CsvBindByName (column = "Last Physical Date")
	private String lastPhysicalDate;
	@CsvBindByName (column = "Next Physical Date")
	private String nextPhysicalDate;
	@CsvBindByName (column = "Drug Test Date")
	private String drugTestDate;
	@CsvBindByName (column = "Cobra Date")
	private String cobraDate;
	@CsvBindByName (column = "Drivers License")
	private String driversLicense;
	@CsvBindByName (column = "DL State")
	private String driversLicenseState;
	@CsvBindByName (column = "DL Expire Date")
	private String driversLicenseExpire;
	@CsvBindByName (column = "DL Type")
	private String driversLicenseType;
	@CsvBindByName (column = "Deceased")
	private String deceased;
	@CsvBindByName (column = "Child Care Amount")
	private String childCare;
	@CsvBindByName (column = "Drug Test Group")
	private String drugTestGroup;
	@CsvBindByName (column = "Unlisted Phone")
	private String unlistedPhone;
	@CsvBindByName (column = "Annual Pay")
	private String annualPay;
	@CsvBindByName (column = "Work Shed Code")
	private String workScheduleCode;
	@CsvBindByName (column = "Review Date")
	private String reviewDate;

	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getPayrollNumber() {
		return payrollNumber;
	}
	public void setPayrollNumber(String payrollNumber) {
		this.payrollNumber = payrollNumber;
	}
	public String getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMi() {
		return mi;
	}
	public void setMi(String mi) {
		this.mi = mi;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSSN() {
		return SSN;
	}
	public void setSSN(String sSN) {
		SSN = sSN;
	}
	public String getPayCycle() {
		return payCycle;
	}
	public void setPayCycle(String payCycle) {
		this.payCycle = payCycle;
	}
	public String getOtherCycle() {
		return otherCycle;
	}
	public void setOtherCycle(String otherCycle) {
		this.otherCycle = otherCycle;
	}
	public String getFilingStatus() {
		return filingStatus;
	}
	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getScheduledHours() {
		return scheduledHours;
	}
	public void setScheduledHours(String scheduledHours) {
		this.scheduledHours = scheduledHours;
	}
	public String getAnnualHours() {
		return annualHours;
	}
	public void setAnnualHours(String annualHours) {
		this.annualHours = annualHours;
	}
	public String getDependants() {
		return dependants;
	}
	public void setDependants(String dependants) {
		this.dependants = dependants;
	}
	public String getDependantsState() {
		return dependentsState;
	}
	public void setDependantsState(String dependantsState) {
		this.dependentsState = dependantsState;
	}
	public String getFicaStatus() {
		return ficaStatus;
	}
	public void setFicaStatus(String ficaStatus) {
		this.ficaStatus = ficaStatus;
	}
	public String getHomeState() {
		return homeState;
	}
	public void setHomeState(String homeState) {
		this.homeState = homeState;
	}
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	public String getFederalExempt() {
		return federalExempt;
	}
	public void setFederalExempt(String federalExempt) {
		this.federalExempt = federalExempt;
	}
	public String getFederalFixedWitholding() {
		return federalFixedWitholding;
	}
	public void setFederalFixedWitholding(String federalFixedWitholding) {
		this.federalFixedWitholding = federalFixedWitholding;
	}
	public String getAdditionalFederalWitholding() {
		return additionalFederalWitholding;
	}
	public void setAdditionalFederalWitholding(String additionalFederalWitholding) {
		this.additionalFederalWitholding = additionalFederalWitholding;
	}
	public String getStateExempt() {
		return stateExempt;
	}
	public void setStateExempt(String stateExempt) {
		this.stateExempt = stateExempt;
	}
	public String getStateFixedWitholding() {
		return stateFixedWitholding;
	}
	public void setStateFixedWitholding(String stateFixedWitholding) {
		this.stateFixedWitholding = stateFixedWitholding;
	}
	public String getAdditionalStateWitholding() {
		return additionalStateWitholding;
	}
	public void setAdditionalStateWitholding(String additionalStateWitholding) {
		this.additionalStateWitholding = additionalStateWitholding;
	}
	public String getStateExemptAmount() {
		return stateExemptAmount;
	}
	public void setStateExemptAmount(String stateExemptAmount) {
		this.stateExemptAmount = stateExemptAmount;
	}
	public String getStateTaxTable() {
		return stateTaxTable;
	}
	public void setStateTaxTable(String stateTaxTable) {
		this.stateTaxTable = stateTaxTable;
	}
	public String getWitholdingPercent() {
		return witholdingPercent;
	}
	public void setWitholdingPercent(String witholdingPercent) {
		this.witholdingPercent = witholdingPercent;
	}
	public String getEstimatedCheck() {
		return estimatedCheck;
	}
	public void setEstimatedCheck(String estimatedCheck) {
		this.estimatedCheck = estimatedCheck;
	}
	public String getEICStatus() {
		return EICStatus;
	}
	public void setEICStatus(String eICStatus) {
		EICStatus = eICStatus;
	}
	public String getMaritialStatus() {
		return maritialStatus;
	}
	public void setMaritialStatus(String maritialStatus) {
		this.maritialStatus = maritialStatus;
	}
	public String getRateClass() {
		return rateClass;
	}
	public void setRateClass(String rateClass) {
		this.rateClass = rateClass;
	}
	public String getLongevityCode() {
		return longevityCode;
	}
	public void setLongevityCode(String longevityCode) {
		this.longevityCode = longevityCode;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getFullPartTime() {
		return fullPartTime;
	}
	public void setFullPartTime(String fullPartTime) {
		this.fullPartTime = fullPartTime;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getCreditUnion() {
		return creditUnion;
	}
	public void setCreditUnion(String creditUnion) {
		this.creditUnion = creditUnion;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getLastIncrease() {
		return lastIncrease;
	}
	public void setLastIncrease(String lastIncrease) {
		this.lastIncrease = lastIncrease;
	}
	public String getHireDate() {
		return hireDate;
	}
	public void setHireDate(String hireDate) {
		this.hireDate = hireDate;
	}
	public String getTerminationDate() {
		return terminationDate;
	}
	public void setTerminationDate(String terminationDate) {
		this.terminationDate = terminationDate;
	}
	public String getLongevityDate() {
		return longevityDate;
	}
	public void setLongevityDate(String longevityDate) {
		this.longevityDate = longevityDate;
	}
	public String getLeaveDate() {
		return leaveDate;
	}
	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getEE04Category() {
		return EE04Category;
	}
	public void setEE04Category(String eE04Category) {
		EE04Category = eE04Category;
	}
	public String getRetirementCode() {
		return retirementCode;
	}
	public void setRetirementCode(String retirementCode) {
		this.retirementCode = retirementCode;
	}
	public String getEmergencyContact() {
		return emergencyContact;
	}
	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}
	public String getEmergencyRelation() {
		return emergencyRelation;
	}
	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}
	public String getEmergencyPhone() {
		return emergencyPhone;
	}
	public void setEmergencyPhone(String emergencyPhone) {
		this.emergencyPhone = emergencyPhone;
	}
	public String getEmergencyAddress() {
		return emergencyAddress;
	}
	public void setEmergencyAddress(String emergencyAddress) {
		this.emergencyAddress = emergencyAddress;
	}
	public String getEmergencyCSZ() {
		return emergencyCSZ;
	}
	public void setEmergencyCSZ(String emergencyCSZ) {
		this.emergencyCSZ = emergencyCSZ;
	}
	public String getTerminationCode() {
		return terminationCode;
	}
	public void setTerminationCode(String terminationCode) {
		this.terminationCode = terminationCode;
	}
	public String getMilitaryCode() {
		return militaryCode;
	}
	public void setMilitaryCode(String militaryCode) {
		this.militaryCode = militaryCode;
	}
	public String getVeteranCode() {
		return veteranCode;
	}
	public void setVeteranCode(String veteranCode) {
		this.veteranCode = veteranCode;
	}
	public String getDisabilityCode() {
		return disabilityCode;
	}
	public void setDisabilityCode(String disabilityCode) {
		this.disabilityCode = disabilityCode;
	}
	public String getI9Flag() {
		return I9Flag;
	}
	public void setI9Flag(String i9Flag) {
		I9Flag = i9Flag;
	}
	public String getDisabledVeteran() {
		return disabledVeteran;
	}
	public void setDisabledVeteran(String disabledVeteran) {
		this.disabledVeteran = disabledVeteran;
	}
	public String getDaysWorked() {
		return daysWorked;
	}
	public void setDaysWorked(String daysWorked) {
		this.daysWorked = daysWorked;
	}
	public String getHoursPerDay() {
		return hoursPerDay;
	}
	public void setHoursPerDay(String hoursPerDay) {
		this.hoursPerDay = hoursPerDay;
	}
	public String getStateEmployementDate() {
		return stateEmployementDate;
	}
	public void setStateEmployementDate(String stateEmployementDate) {
		this.stateEmployementDate = stateEmployementDate;
	}
	public String getLastPhysicalDate() {
		return lastPhysicalDate;
	}
	public void setLastPhysicalDate(String lastPhysicalDate) {
		this.lastPhysicalDate = lastPhysicalDate;
	}
	public String getNextPhysicalDate() {
		return nextPhysicalDate;
	}
	public void setNextPhysicalDate(String nextPhysicalDate) {
		this.nextPhysicalDate = nextPhysicalDate;
	}
	public String getDrugTestDate() {
		return drugTestDate;
	}
	public void setDrugTestDate(String drugTestDate) {
		this.drugTestDate = drugTestDate;
	}
	public String getCobraDate() {
		return cobraDate;
	}
	public void setCobraDate(String cobraDate) {
		this.cobraDate = cobraDate;
	}
	public String getDriversLicense() {
		return driversLicense;
	}
	public void setDriversLicense(String driversLicense) {
		this.driversLicense = driversLicense;
	}
	public String getDriversLicenseState() {
		return driversLicenseState;
	}
	public void setDriversLicenseState(String driversLicenseState) {
		this.driversLicenseState = driversLicenseState;
	}
	public String getDriversLicenseExpire() {
		return driversLicenseExpire;
	}
	public void setDriversLicenseExpire(String driversLicenseExpire) {
		this.driversLicenseExpire = driversLicenseExpire;
	}
	public String getDriversLicenseType() {
		return driversLicenseType;
	}
	public void setDriversLicenseType(String driversLicenseType) {
		this.driversLicenseType = driversLicenseType;
	}
	public String getDeceased() {
		return deceased;
	}
	public void setDeceased(String deceased) {
		this.deceased = deceased;
	}
	public String getChildCare() {
		return childCare;
	}
	public void setChildCare(String childCare) {
		this.childCare = childCare;
	}
	public String getDrugTestGroup() {
		return drugTestGroup;
	}
	public void setDrugTestGroup(String drugTestGroup) {
		this.drugTestGroup = drugTestGroup;
	}
	public String getUnlistedPhone() {
		return unlistedPhone;
	}
	public void setUnlistedPhone(String unlistedPhone) {
		this.unlistedPhone = unlistedPhone;
	}
	public String getAnnualPay() {
		return annualPay;
	}
	public void setAnnualPay(String annualPay) {
		this.annualPay = annualPay;
	}
	public String getWorkScheduleCode() {
		return workScheduleCode;
	}
	public void setWorkScheduleCode(String workScheduleCode) {
		this.workScheduleCode = workScheduleCode;
	}
	public String getReviewDate() {
		return reviewDate;
	}
	public void setReviewDate(String reviewDate) {
		this.reviewDate = reviewDate;
	}
	
	 public String toString() {
	     return ReflectionToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE).replaceAll("\\s+", "") + System.lineSeparator();
	 }
	
}
