package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import View.*;
import Model.*;
import Service.*;
import net.proteanit.sql.DbUtils;

public class MainController {
	private MainView mainView;
	private EmployeeOptionsView employeeOptionsView;
	private ActionListener actionListener;

	public MainController(MainView mainView, EmployeeOptionsView employeeOptionsView) {
		this.mainView = mainView;

	}

	// Initialize Controller
	public void initialize() {
		FlatFileValidationSettings();
		FlatFilePicker();
		ServerXMLPicker();
		PopulateDefaults();
	}

	// Flat file validation Settings button
	public void FlatFileValidationSettings() {
		actionListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainView.getEmployeeOptionsView().setVisible(true);
				mainView.getEmployeeOptionsView().requestFocus();
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
				//Runnable runnable = new ProcessorThread(window, parsedLinesTextArea, errorsTextArea);
				//Thread thread = new Thread(runnable);
				//thread.start();
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
}
