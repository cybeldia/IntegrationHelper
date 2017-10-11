package com.shaffer.integrationhelper.controller;

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
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.shaffer.integrationhelper.model.*;
import com.shaffer.integrationhelper.service.IProcessorThread;
import com.shaffer.integrationhelper.service.IValidator;
import com.shaffer.integrationhelper.service.impl.QueryExecuter;
import com.shaffer.integrationhelper.service.impl.XMLToDBConnection;
import com.shaffer.integrationhelper.view.*;

import net.proteanit.sql.DbUtils;

@Component
public class MainController {
	private MainView mainView;
	private EmployeeOptionsView employeeOptionsView;
	private ActionListener actionListener;

	private List<String> payPeriods;
	private List<String> employeeTypes;
	private List<String> employeeStatus;
	private List<String> departments;

	private List<String> inCorrectPayPeriods;
	private List<String> inCorrectDepartments;
	private List<String> inCorrectEmployeeTypes;
	private List<String> inCorrectEmployeeStatus;

	@Autowired
	private IProcessorThread processor;
	@Autowired
	private IValidator validator;

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
		ProcessFlatFile();
		TestDBConnection();
		ExecuteQuery();
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

	// Process flat file
	public void ProcessFlatFile() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String payrollSystem = mainView.getPayrollComboBox().getSelectedItem().toString();
				String filePath = mainView.getFileText().getText();

				mainView.getErrorsTextArea().setText("");
				mainView.getParsedLinesTextArea().setText("");
				try {
					processor.setPayrollSystem(payrollSystem);
					processor.setFilePath(filePath);
					processor.run();

					if (payrollSystem == "InCode") {
						ValidateInCode();
					}

				} catch (Exception exception) {
					mainView.getErrorsTextArea().setText("Error with file. Please check formatting");
				}
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
	public void TestDBConnection() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					XMLToDBConnection connection = new XMLToDBConnection();
					Connection conn = connection.DBConnection(mainView.getServerXmlTextField().getText());
					QueryExecuter executer = new QueryExecuter(mainView.getPayrollComboBox().getSelectedItem().toString(), conn, mainView.getTable());
					mainView.getTable().setModel(DbUtils.resultSetToTableModel(executer.GetCurrentMapping()));
					conn.close();
					JOptionPane.showMessageDialog(null, "Connection successful");
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Connection unsuccessful");
					ex.printStackTrace();
				}
			}

		};
		mainView.getBtnTestDatabaseConnection().addActionListener(actionListener);
	}

	public void ExecuteQuery() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					XMLToDBConnection connection = new XMLToDBConnection();
					Connection conn = connection.DBConnection(mainView.getServerXmlTextField().toString());
					QueryExecuter executer = new QueryExecuter(mainView.getPayrollComboBox().getSelectedItem().toString(), conn, mainView.getTable());
					executer.ExecuteMappingQuery();
					if (mainView.getScheduledJobsCheckBox().isSelected()) {
						executer.CreateScheduledJobs();
					}
					conn.close();
					JOptionPane.showMessageDialog(null, "Execution Successful");
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		};
		mainView.getBtnExecute().addActionListener(actionListener);
	}

	// Populate payroll defaults
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

	public void ValidateInCode() {
		List<InCodeEmployee> employees = processor.getEmployeeList();
		validator.ICValidate(employeeOptionsView.getDepartmentsTextField().getText(),
				employeeOptionsView.getEmployeeTypesTextField().getText(),
				employeeOptionsView.getEmployeeStatusTextField().getText(),
				employeeOptionsView.getPayPeriodTextField().getText(), employees);
	}

	public void SetEmployeeValidationFields() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				employeeOptionsView.setVisible(false);
			}
		};
		employeeOptionsView.getOkButton().addActionListener(actionListener);

	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void setEmployeeOptionsView(EmployeeOptionsView employeeOptionsView) {
		this.employeeOptionsView = employeeOptionsView;
	}

}
