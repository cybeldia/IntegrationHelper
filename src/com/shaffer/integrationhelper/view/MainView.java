package com.shaffer.integrationhelper.view;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.events.ErrorEvent;
import com.shaffer.integrationhelper.events.ParsedLineEvent;


import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@Component
public class MainView implements ApplicationListener<ErrorEvent> {

	// JFrame
	private JFrame frmIntegrationAssistant;
	// Text Fields
	private JTextField serverXmlTextField;
	private JTextField fileText;
	// Text Area
	private JTextArea parsedLinesTextArea;
	private JTextArea errorsTextArea;
	// Combo Boxes
	private JComboBox<String> payrollComboBox;
	private JComboBox<String> fileTypeComboBox;
	private JComboBox<String> versionComboBox;
	// ??
	@Autowired
	private EmployeeOptionsView employeeOptionsView;
	@Autowired
	private BenefitOptionsView benefitOptionsView;
	// Tables
	private JTable table;
	// Check Boxes
	private JCheckBox scheduledJobsCheckBox;
	private JCheckBox departmentsCheckBox;
	private JCheckBox locationCheckBox;
	private JCheckBox adminPropertiesCheckBox;
	// Buttons
	private JButton btnEmployeeCheckerSettings;
	private JButton btnChooseFile;
	private JButton btnChooseFileServer;
	private JButton btnTestDatabaseConnection;
	private JButton btnPopulateTable;
	private JButton btnExecute;
	private JButton btnProcess;

	public MainView() throws Exception {

		BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
		UIManager.put("RootPane.setupButtonVisible", false);
		org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		frmIntegrationAssistant = new JFrame();
		frmIntegrationAssistant.setTitle("Integration Assistant");
		frmIntegrationAssistant.setBounds(100, 100, 800, 600);
		frmIntegrationAssistant.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmIntegrationAssistant.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		frmIntegrationAssistant.getContentPane().setLayout(new BorderLayout(0, 0));

		JTabbedPane tabbedPane = new JTabbedPane(SwingConstants.TOP);
		frmIntegrationAssistant.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(new MigLayout("", "[1px][187px][71px]", "[1px][17px][20px][20px][20px][17px][21px][21px][21px][21px]"));

		JSeparator separator_5 = new JSeparator();
		panel.add(separator_5, "cell 0 0,alignx left,growy");

		JLabel lblGeneral = new JLabel("General:");
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblGeneral, "cell 1 1,alignx left,aligny top");

		JLabel lblPayrollSystem = new JLabel("Payroll System:");
		lblPayrollSystem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPayrollSystem, "cell 1 2,alignx left,aligny center");

		payrollComboBox = new JComboBox<String>();
		payrollComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "InCode", "Generic" }));
		panel.add(payrollComboBox, "cell 2 2,alignx left,aligny top");

		JLabel lblFileType = new JLabel("File Type:");
		lblFileType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblFileType, "cell 1 3,alignx left,aligny top");

		fileTypeComboBox = new JComboBox<String>();
		fileTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Employee", "Benefit", "Accounts" }));
		panel.add(fileTypeComboBox, "cell 2 3,alignx left,aligny top");

		JLabel lblExecutimeVersion = new JLabel("ExecuTime Version:");
		lblExecutimeVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblExecutimeVersion, "cell 1 4,alignx left,aligny top");

		versionComboBox = new JComboBox<String>();
		versionComboBox.setModel(new DefaultComboBoxModel(new String[] { "4.x", "5.x" }));
		panel.add(versionComboBox, "cell 2 4,alignx left,aligny top");

		JLabel lblDatabaseConfigSettings = new JLabel("Database Config Settings:");
		lblDatabaseConfigSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblDatabaseConfigSettings, "cell 1 5,alignx left,aligny top");

		JLabel lblCreateScheduledJobs = new JLabel("Create scheduled jobs");
		lblCreateScheduledJobs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCreateScheduledJobs, "cell 1 6,alignx left,aligny center");

		scheduledJobsCheckBox = new JCheckBox("");
		panel.add(scheduledJobsCheckBox, "cell 2 6,alignx left,aligny top");

		JLabel lblSetupDefaultDepartments = new JLabel("Setup default departments");
		lblSetupDefaultDepartments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultDepartments, "cell 1 7,alignx left,aligny center");

		departmentsCheckBox = new JCheckBox("");
		panel.add(departmentsCheckBox, "cell 2 7,alignx left,aligny top");

		JLabel lblSetupDefaultLocations = new JLabel("Setup default Locations");
		lblSetupDefaultLocations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultLocations, "cell 1 8,alignx left,aligny center");

		locationCheckBox = new JCheckBox("");
		panel.add(locationCheckBox, "cell 2 8,alignx left,aligny top");

		JLabel lblSetupDefaultAdmin = new JLabel("Setup default admin properties");
		lblSetupDefaultAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultAdmin, "cell 1 9,alignx left,aligny center");

		adminPropertiesCheckBox = new JCheckBox("");
		panel.add(adminPropertiesCheckBox, "cell 2 9,alignx left,aligny top");

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Flat File Helper", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][grow][][][][][][][][][][][][grow][grow][][grow]"));

		employeeOptionsView = new EmployeeOptionsView();

		btnEmployeeCheckerSettings = new JButton("Fields to validate");

		panel_1.add(btnEmployeeCheckerSettings, "cell 0 1");

		JSeparator separator_3 = new JSeparator();
		panel_1.add(separator_3, "cell 0 2");

		JSeparator separator = new JSeparator();
		panel_1.add(separator, "cell 0 12");

		JTabbedPane tabbedPane_1 = new JTabbedPane(SwingConstants.TOP);
		panel_1.add(tabbedPane_1, "cell 0 13 1 4,grow");
		tabbedPane_1.setOpaque(true);

		JScrollPane errorsPane = new JScrollPane();
		tabbedPane_1.addTab("Errors", null, errorsPane, null);

		errorsTextArea = new JTextArea();
		errorsPane.setViewportView(errorsTextArea);

		JScrollPane parsedLinesPane = new JScrollPane();
		tabbedPane_1.addTab("Parsed Lines", null, parsedLinesPane, null);

		parsedLinesTextArea = new JTextArea();
		parsedLinesPane.setViewportView(parsedLinesTextArea);

		btnChooseFile = new JButton("Choose File:");

		panel_1.add(btnChooseFile, "flowx,cell 0 0,alignx left");

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Database Config", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[grow][]", "[][][][grow][]"));

		JLabel lblPleaseSelectThe = new JLabel("Please select the server.xml for your instance:");
		lblPleaseSelectThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblPleaseSelectThe, "cell 0 0");

		btnChooseFileServer = new JButton("Choose File:");

		panel_2.add(btnChooseFileServer, "flowx,cell 0 1");

		serverXmlTextField = new JTextField();
		panel_2.add(serverXmlTextField, "cell 0 1,growx");
		serverXmlTextField.setColumns(10);

		btnTestDatabaseConnection = new JButton("Test Database Connection");

		panel_2.add(btnTestDatabaseConnection, "cell 1 1");

		JSeparator separator_1 = new JSeparator();
		panel_2.add(separator_1, "cell 0 2 2 1,growx");

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, "cell 0 3 2 1,grow");

		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] {}));
		scrollPane.setViewportView(table);

		btnPopulateTable = new JButton("Populate Payroll Defaults");

		panel_2.add(btnPopulateTable, "cell 0 4");

		btnExecute = new JButton("Execute");

		panel_2.add(btnExecute, "cell 1 4,alignx right");

		btnProcess = new JButton("Process");

		panel_1.add(btnProcess, "cell 0 11,alignx center");

		fileText = new JTextField();
		panel_1.add(fileText, "cell 0 0");
		fileText.setColumns(25);

	}

	@Override
	public void onApplicationEvent(ErrorEvent event) {
		for (String string : event.getError()) {
			errorsTextArea.append(string);
		}
	}

	@EventListener
	public void onApplicationEvent(ParsedLineEvent event) {
		String employee;
		for (Object inCodeEmployee : event.getLines()) {
			employee = inCodeEmployee.toString();
			parsedLinesTextArea.append(employee);
		}
	}

	public JFrame getFrmIntegrationAssistant() {
		return frmIntegrationAssistant;
	}

	public void setFrmIntegrationAssistant(JFrame frmIntegrationAssistant) {
		this.frmIntegrationAssistant = frmIntegrationAssistant;
	}

	public JTextField getServerXmlTextField() {
		return serverXmlTextField;
	}

	public void setServerXmlTextField(JTextField serverXmlTextField) {
		this.serverXmlTextField = serverXmlTextField;
	}

	public JTextField getFileText() {
		return fileText;
	}

	public void setFileText(JTextField fileText) {
		this.fileText = fileText;
	}

	public JTextArea getParsedLinesTextArea() {
		return parsedLinesTextArea;
	}

	public void setParsedLinesTextArea(JTextArea parsedLinesTextArea) {
		this.parsedLinesTextArea = parsedLinesTextArea;
	}

	public JTextArea getErrorsTextArea() {
		return errorsTextArea;
	}

	public void setErrorsTextArea(JTextArea errorsTextArea) {
		this.errorsTextArea = errorsTextArea;
	}

	public JComboBox<String> getPayrollComboBox() {
		return payrollComboBox;
	}

	public void setPayrollComboBox(JComboBox<String> payrollComboBox) {
		this.payrollComboBox = payrollComboBox;
	}

	public JComboBox<String> getFileTypeComboBox() {
		return fileTypeComboBox;
	}

	public void setFileTypeComboBox(JComboBox<String> fileTypeComboBox) {
		this.fileTypeComboBox = fileTypeComboBox;
	}

	public JComboBox<String> getVersionComboBox() {
		return versionComboBox;
	}

	public void setVersionComboBox(JComboBox<String> versionComboBox) {
		this.versionComboBox = versionComboBox;
	}

	public EmployeeOptionsView getEmployeeOptionsView() {
		return employeeOptionsView;
	}

	public void setEmployeeOptionsView(EmployeeOptionsView employeeOptionsView) {
		this.employeeOptionsView = employeeOptionsView;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JCheckBox getScheduledJobsCheckBox() {
		return scheduledJobsCheckBox;
	}

	public void setScheduledJobsCheckBox(JCheckBox scheduledJobsCheckBox) {
		this.scheduledJobsCheckBox = scheduledJobsCheckBox;
	}

	public JCheckBox getDepartmentsCheckBox() {
		return departmentsCheckBox;
	}

	public void setDepartmentsCheckBox(JCheckBox departmentsCheckBox) {
		this.departmentsCheckBox = departmentsCheckBox;
	}

	public JCheckBox getLocationCheckBox() {
		return locationCheckBox;
	}

	public void setLocationCheckBox(JCheckBox locationCheckBox) {
		this.locationCheckBox = locationCheckBox;
	}

	public JCheckBox getAdminPropertiesCheckBox() {
		return adminPropertiesCheckBox;
	}

	public void setAdminPropertiesCheckBox(JCheckBox adminPropertiesCheckBox) {
		this.adminPropertiesCheckBox = adminPropertiesCheckBox;
	}

	public JButton getBtnEmployeeCheckerSettings() {
		return btnEmployeeCheckerSettings;
	}

	public void setBtnEmployeeCheckerSettings(JButton btnEmployeeCheckerSettings) {
		this.btnEmployeeCheckerSettings = btnEmployeeCheckerSettings;
	}

	public JButton getBtnChooseFile() {
		return btnChooseFile;
	}

	public void setBtnChooseFile(JButton btnChooseFile) {
		this.btnChooseFile = btnChooseFile;
	}

	public JButton getBtnChooseFileServer() {
		return btnChooseFileServer;
	}

	public void setBtnChooseFileServer(JButton btnChooseFileServer) {
		this.btnChooseFileServer = btnChooseFileServer;
	}

	public JButton getBtnTestDatabaseConnection() {
		return btnTestDatabaseConnection;
	}

	public void setBtnTestDatabaseConnection(JButton btnTestDatabaseConnection) {
		this.btnTestDatabaseConnection = btnTestDatabaseConnection;
	}

	public JButton getBtnPopulateTable() {
		return btnPopulateTable;
	}

	public void setBtnPopulateTable(JButton btnPopulateTable) {
		this.btnPopulateTable = btnPopulateTable;
	}

	public JButton getBtnExecute() {
		return btnExecute;
	}

	public void setBtnExecute(JButton btnExecute) {
		this.btnExecute = btnExecute;
	}

	public JButton getBtnProcess() {
		return btnProcess;
	}

	public void setBtnProcess(JButton btnProcess) {
		this.btnProcess = btnProcess;
	}
}
