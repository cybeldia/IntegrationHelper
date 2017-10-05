package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import View.*;
import model.*;
import net.proteanit.sql.DbUtils;
import service.*;
import service.impl.ProcessorThread;


public class MainController {
	private MainView mainView;
	private EmployeeOptionsView employeeOptionsView;
	private ActionListener actionListener;
	
	private List<String> payPeriods;
	private List<String> employeeTypes;
	private List<String> employeeStatus;
	private List<String> departments;


	public MainController(MainView mainView, EmployeeOptionsView employeeOptionsView) {
		this.mainView = mainView;
		this.employeeOptionsView = employeeOptionsView;
	}

	// Initialize Controller
	public void initialize() {
		FlatFileValidationSettings();
		FlatFilePicker();
		ServerXMLPicker();
		PopulateDefaults();
		SetEmployeeValidationFields();
	}

	// Flat file validation Settings button
	public void FlatFileValidationSettings() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeOptionsView.setVisible(true);
			}
		};
		mainView.getBtnEmployeeCheckerSettings().addActionListener(actionListener);
	}

	// Flat file picker
	public void FlatFilePicker() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String pickedFile = chooser.getSelectedFile().getPath();
					mainView.getFileText().setText(pickedFile);
				}
			}
		};
		mainView.getBtnChooseFile().addActionListener(actionListener);
	}
	
	//Process flat file
	public void ProcessFlatFile() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		};
		mainView.getBtnProcess().addActionListener(actionListener);
	}

	// Server XML File Picker
	public void ServerXMLPicker() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String pickedFile = chooser.getSelectedFile().getPath();
					mainView.getServerXmlTextField().setText(pickedFile);
				}
			}
		};
		mainView.getBtnChooseFileServer().addActionListener(actionListener);
	}

	// Add Test Database Connection
	//Add Database Execute

	//Populate payroll defaults
	public void PopulateDefaults() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (mainView.getPayrollComboBox().getSelectedItem().toString().equals("InCode")) {
					PayrollDefaults pd = new PayrollDefaults();
					pd.setInCodeDefaults(mainView.getTable());
					mainView.getTable().repaint();
				}
			}
		};
		mainView.getBtnPopulateTable().addActionListener(actionListener);

	}
	
	public void SetEmployeeValidationFields() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!employeeOptionsView.getPayPeriodTextField().getText().isEmpty()) {
				payPeriods = new ArrayList<String>(Arrays.asList(employeeOptionsView.getPayPeriodTextField().getText().split("\\s*,\\s*")));
				}
				else {
					payPeriods = null;
				}
				
				if(!employeeOptionsView.getEmployeeTypesTextField().getText().isEmpty()) {
				employeeTypes = new ArrayList<String>(Arrays.asList(employeeOptionsView.getEmployeeTypesTextField().getText().split("\\s*,\\s*")));
				}
				else {
					employeeTypes = null;
				}
				
				if(!employeeOptionsView.getEmployeeStatusTextField().getText().isEmpty()) {
				employeeStatus = new ArrayList<String>(Arrays.asList(employeeOptionsView.getEmployeeStatusTextField().getText().split("\\s*,\\s*")));
				}
				else {
					employeeStatus = null;
				}
				
				if(!employeeOptionsView.getDepartmentsTextField().getText().isEmpty()) {
				departments = new ArrayList<String>(Arrays.asList(employeeOptionsView.getDepartmentsTextField().getText().split("\\s*,\\s*")));
				}
				else {
					departments = null;
				}
				employeeOptionsView.setVisible(false);
			}
		};
		employeeOptionsView.getOkButton().addActionListener(actionListener);

	}
	
}
