package View;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class MainView {
	
	//JFrame
	private JFrame frmIntegrationAssistant;
	//Text Fields
	private JTextField serverXmlTextField;
	private JTextField fileText;
	//Text Area
	private JTextArea parsedLinesTextArea;
	private JTextArea errorsTextArea;
	//Combo Boxes
	private JComboBox<String> payrollComboBox;
	private JComboBox<String> fileTypeComboBox;
	private JComboBox<String> versionComboBox;
	//??
	private EmployeeOptionsView employeeOptionsView;
	//Tables
	private JTable table;
	//Check Boxes
	private JCheckBox scheduledJobsCheckBox;
	private JCheckBox departmentsCheckBox;
	private JCheckBox locationCheckBox;
	private JCheckBox adminPropertiesCheckBox;
	//Buttons
	private JButton btnEmployeeCheckerSettings;
	private JButton btnChooseFile;
	private JButton btnChooseFileServer;
	private JButton btnTestDatabaseConnection;
	private JButton btnPopulateTable;
	private JButton btnExecute;
	private JButton btnProcess;

	public MainView() {
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

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmIntegrationAssistant.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		payrollComboBox = new JComboBox<String>();
		payrollComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "InCode", "Generic" }));

		JPanel panel = new JPanel();
		tabbedPane.addTab("Settings", null, panel, null);
		panel.setLayout(new MigLayout("", "[28px][][][][grow][][][][]", "[][][][20px][][][][][][][][][]"));
		panel.add(payrollComboBox, "cell 1 3,alignx left,aligny top");

		JLabel lblGeneral = new JLabel("General:");
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblGeneral, "cell 0 0");

		JLabel lblPayrollSystem = new JLabel("Payroll System:");
		lblPayrollSystem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPayrollSystem, "cell 0 3,alignx left");

		JSeparator separator_2 = new JSeparator();
		panel.add(separator_2, "cell 0 4 9 1");

		JLabel lblFileType = new JLabel("File Type:");
		lblFileType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblFileType, "cell 0 5,alignx left");

		fileTypeComboBox = new JComboBox<String>();
		fileTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Employee", "Benefit", "Accounts" }));
		panel.add(fileTypeComboBox, "cell 1 5,alignx left");

		JLabel lblExecutimeVersion = new JLabel("ExecuTime Version:");
		lblExecutimeVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblExecutimeVersion, "cell 0 6,alignx left");

		versionComboBox = new JComboBox<String>();
		versionComboBox.setModel(new DefaultComboBoxModel(new String[] { "4.x", "5.x" }));
		panel.add(versionComboBox, "cell 1 6,alignx left");

		JLabel lblDatabaseConfigSettings = new JLabel("Database Config Settings:");
		lblDatabaseConfigSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblDatabaseConfigSettings, "cell 0 8");

		JLabel lblCreateScheduledJobs = new JLabel("Create scheduled jobs");
		lblCreateScheduledJobs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCreateScheduledJobs, "cell 0 9");

		scheduledJobsCheckBox = new JCheckBox("");
		panel.add(scheduledJobsCheckBox, "cell 1 9");

		JLabel lblSetupDefaultDepartments = new JLabel("Setup default departments");
		lblSetupDefaultDepartments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultDepartments, "cell 0 10");

		departmentsCheckBox = new JCheckBox("");
		panel.add(departmentsCheckBox, "cell 1 10");

		JLabel lblSetupDefaultLocations = new JLabel("Setup default Locations");
		lblSetupDefaultLocations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultLocations, "cell 0 11");

		locationCheckBox = new JCheckBox("");
		panel.add(locationCheckBox, "cell 1 11");

		JLabel lblSetupDefaultAdmin = new JLabel("Setup default admin properties");
		lblSetupDefaultAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultAdmin, "cell 0 12");

		adminPropertiesCheckBox = new JCheckBox("");
		panel.add(adminPropertiesCheckBox, "cell 1 12");

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

		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		panel_1.add(tabbedPane_1, "cell 0 13 1 4,grow");
		tabbedPane_1.setOpaque(true);

		JScrollPane errorsPane = new JScrollPane();
		tabbedPane_1.addTab("Errors", null, errorsPane, null);

		 errorsTextArea = new JTextArea();
		errorsPane.setViewportView(errorsTextArea);

		JScrollPane parsedLinesPane = new JScrollPane();
		tabbedPane_1.addTab("Parsed Lines", null, parsedLinesPane, null);

		JTextArea parsedLinesTextArea = new JTextArea();
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
