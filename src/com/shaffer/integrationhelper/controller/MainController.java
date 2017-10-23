package com.shaffer.integrationhelper.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.model.PayrollDefaults;
import com.shaffer.integrationhelper.service.IProcessor;
import com.shaffer.integrationhelper.service.IValidator;
import com.shaffer.integrationhelper.service.impl.QueryExecuter;
import com.shaffer.integrationhelper.view.BenefitOptionsView;
import com.shaffer.integrationhelper.view.EmployeeOptionsView;
import com.shaffer.integrationhelper.view.MainView;
import com.shaffer.integrationhelper.view.SungardHTEOptionsView;

import net.proteanit.sql.DbUtils;

@Component
public class MainController {
	private MainView mainView;
	private EmployeeOptionsView employeeOptionsView;
	private BenefitOptionsView benefitOptionsView;
	private ActionListener actionListener;

	@Autowired
	private SungardHTEOptionsView sungardHTEOptionsView;
	@Autowired
	private IProcessor processor;
	@Autowired
	private IValidator validator;
	@Autowired
	private ApplicationSettings applicationSettings;
	@Autowired
	private QueryExecuter queryExecuter;

	public MainController(MainView mainView, EmployeeOptionsView employeeOptionsView,
			BenefitOptionsView benefitOptionsView) {
		this.mainView = mainView;
		this.employeeOptionsView = employeeOptionsView;
		this.benefitOptionsView = benefitOptionsView;
	}

	// Initialize Controller -- add event functions here
	public void initialize() {
		flatFileValidationSettings();
		flatFilePicker();
		serverXMLPicker();
		populateDefaults();
		setEmployeeValidationFields();
		setBenefitValidationFields();
		processFlatFile();
		testDBConnection();
		executeQuery();
		setPayrollSystem();
		setFileType();
		setJobOptions();
		setVersion();
		scheduleJobCheckBoxListener();

	}

	// Flat file validation Settings button
	public void flatFileValidationSettings() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (applicationSettings.getFileType().equals("Employee")) {
					employeeOptionsView.setVisible(true);
				} else if (applicationSettings.getFileType().equals("Benefit")) {
					benefitOptionsView.setVisible(true);
				}
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

					applicationSettings.setDatabaseTextField(mainView.getServerXmlTextField().getText());
					mainView.getTable().setModel(DbUtils.resultSetToTableModel(queryExecuter.getCurrentMapping()));

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

	// Executes queries to setup a system
	public void executeQuery() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					queryExecuter.executeMappingQuery(mainView.getTable());
					if (mainView.getScheduledJobsCheckBox().isSelected()) {
						queryExecuter.createScheduledJobs();
					}
					if (mainView.getAdminPropertiesCheckBox().isSelected()) {
						queryExecuter.executeAdminProperties();
					}
					if (mainView.getLocationCheckBox().isSelected()) {
						queryExecuter.cleanUpLocations();
					}
					if (mainView.getDepartmentsCheckBox().isSelected()) {
						queryExecuter.setupOrgUnits();
					}
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
				PayrollDefaults pd = new PayrollDefaults();
				if (applicationSettings.getPayrollSystem().equals("InCode")) {
					pd.setInCodeDefaults(mainView.getTable());
					mainView.getTable().repaint();
				} else if (applicationSettings.getPayrollSystem().equals("Sungard HTE")) {
					pd.setHTEDefaults(mainView.getTable());
				}
			}
		};
		mainView.getBtnPopulateTable().addActionListener(actionListener);

	}

	// Sets fields to validate for an employee file in the application settings
	public void setEmployeeValidationFields() {
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
		employeeOptionsView.getCancelButton().addActionListener(actionListener);

	}

	// Sets fields to validate for the benefit file in the application settings
	public void setBenefitValidationFields() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setBenefits(benefitOptionsView.getBenefitTextField().getText());
				benefitOptionsView.setVisible(false);
			}
		};
		benefitOptionsView.getOkButton().addActionListener(actionListener);
		employeeOptionsView.getCancelButton().addActionListener(actionListener);

	}

	// Handle changing payroll system in app settings also listens for when to show
	// job options box in conjunction with the checkbox listener
	public void setPayrollSystem() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setPayrollSystem(mainView.getPayrollComboBox().getSelectedItem().toString());
				System.out.println(applicationSettings.getPayrollSystem());
				showJobOptionsButton();
			}
		};
		mainView.getPayrollComboBox().addActionListener(actionListener);
	}

	// Changes file type in application settings
	public void setFileType() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setFileType(mainView.getFileTypeComboBox().getSelectedItem().toString());
			}
		};
		mainView.getFileTypeComboBox().addActionListener(actionListener);
	}

	// Logic to show whether or not the job option box should show. Based on payroll
	// systems flagged with options
	public void showJobOptionsButton() {
		if (mainView.getScheduledJobsCheckBox().isSelected()
				&& applicationSettings.getHasJobSettings().contains(applicationSettings.getPayrollSystem())) {
			mainView.getBtnJobOptions().setVisible(true);
		} else {
			mainView.getBtnJobOptions().setVisible(false);
		}
	}

	// Checkbox listener for showing job options button
	public void scheduleJobCheckBoxListener() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showJobOptionsButton();
			}
		};
		mainView.getScheduledJobsCheckBox().addActionListener(actionListener);
	}

	// Opens the correct job options view based on the payroll system
	public void setJobOptions() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (applicationSettings.getPayrollSystem().equals("Sungard HTE")) {
					sungardHTEOptionsView.setVisible(true);
				}
			}
		};
		mainView.getBtnJobOptions().addActionListener(actionListener);
	}

	// Listens on version checkbox for changes and sets them in the
	// applicationSettings
	public void setVersion() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings.setVersion(mainView.getVersionComboBox().getSelectedItem().toString());
				System.out.println(applicationSettings.getVersion());
			}
		};
		mainView.getVersionComboBox().addActionListener(actionListener);

	}

	// Setters for managing views - needs to be replaced now that I'm using spring.
	// I can autowire these singleton components
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void setEmployeeOptionsView(EmployeeOptionsView employeeOptionsView) {
		this.employeeOptionsView = employeeOptionsView;
	}

	public void setBenefitOptionsView(BenefitOptionsView benefitOptionsView) {
		this.benefitOptionsView = benefitOptionsView;
	}

}
