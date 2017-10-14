package com.shaffer.integrationhelper.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.model.PayrollDefaults;
import com.shaffer.integrationhelper.service.IProcessor;
import com.shaffer.integrationhelper.service.IValidator;
import com.shaffer.integrationhelper.service.impl.QueryExecuter;
import com.shaffer.integrationhelper.service.impl.XMLToDBConnection;
import com.shaffer.integrationhelper.view.EmployeeOptionsView;
import com.shaffer.integrationhelper.view.MainView;

import net.proteanit.sql.DbUtils;

@Component
public class MainController {
	private MainView mainView;
	private EmployeeOptionsView employeeOptionsView;
	private ActionListener actionListener;

	@Autowired
	private IProcessor processor;
	@Autowired
	private IValidator validator;

	@Autowired
	private ApplicationSettings applicationSettings;

	public MainController(MainView mainView, EmployeeOptionsView employeeOptionsView) {
		this.mainView = mainView;
		this.employeeOptionsView = employeeOptionsView;

	}

	// Initialize Controller -- add event functions here
	public void initialize() {
		flatFileValidationSettings();
		flatFilePicker();
		serverXMLPicker();
		populateDefaults();
		setValidationFields();
		processFlatFile();
		testDBConnection();
		executeQuery();
		setPayrollSystem();
		setFileType();
	}

	// Flat file validation Settings button
	public void flatFileValidationSettings() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				employeeOptionsView.setVisible(true);
			}
		};
		mainView.getBtnEmployeeCheckerSettings().addActionListener(actionListener);
	}

	// Flat file picker
	public void flatFilePicker() {
		actionListener = new ActionListener() {
			@Override
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
	public void processFlatFile() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainView.getErrorsTextArea().setText("");
				mainView.getParsedLinesTextArea().setText("");
				try {
					applicationSettings.setFlatFileTextField(mainView.getFileText().getText());
					processor.run();

				} catch (Exception exception) {
					// mainView.getErrorsTextArea().setText("Error with file. Please check
					// formatting");
					exception.printStackTrace();
				}
			}
		};
		mainView.getBtnProcess().addActionListener(actionListener);
	}

	// Server XML File Picker
	public void serverXMLPicker() {
		actionListener = new ActionListener() {
			@Override
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
	public void testDBConnection() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					XMLToDBConnection connection = new XMLToDBConnection();
					Connection conn = connection.DBConnection(mainView.getServerXmlTextField().getText());
					QueryExecuter executer = new QueryExecuter(
							mainView.getPayrollComboBox().getSelectedItem().toString(), conn, mainView.getTable());
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

	public void executeQuery() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					XMLToDBConnection connection = new XMLToDBConnection();
					Connection conn = connection.DBConnection(mainView.getServerXmlTextField().getText());
					QueryExecuter executer = new QueryExecuter(
							mainView.getPayrollComboBox().getSelectedItem().toString(), conn, mainView.getTable());
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
	public void populateDefaults() {
		actionListener = new ActionListener() {
			@Override
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

	public void setValidationFields() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setDepartments(employeeOptionsView.getDepartmentsTextField().getText());
				applicationSettings.setPayPeriods(employeeOptionsView.getPayPeriodTextField().getText());
				applicationSettings.setEmployeeStatus(employeeOptionsView.getEmployeeStatusTextField().getText());
				applicationSettings.setEmployeeTypes(employeeOptionsView.getEmployeeTypesTextField().getText());
				employeeOptionsView.setVisible(false);
			}
		};
		employeeOptionsView.getOkButton().addActionListener(actionListener);

	}

	// Handle changing app settings
	public void setPayrollSystem() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setPayrollSystem(mainView.getPayrollComboBox().getSelectedItem().toString());
				System.out.println(applicationSettings.getPayrollSystem());

			}
		};
		mainView.getPayrollComboBox().addActionListener(actionListener);
	}

	public void setFileType() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setFileType(mainView.getFileTypeComboBox().getSelectedItem().toString());
			}
		};
		mainView.getFileTypeComboBox().addActionListener(actionListener);
	}

	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void setEmployeeOptionsView(EmployeeOptionsView employeeOptionsView) {
		this.employeeOptionsView = employeeOptionsView;
	}

}
