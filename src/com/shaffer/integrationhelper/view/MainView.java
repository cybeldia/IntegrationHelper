package com.shaffer.integrationhelper.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.alee.laf.WebLookAndFeel;
import com.shaffer.integrationhelper.events.ErrorEvent;
import com.shaffer.integrationhelper.events.ParsedLineEvent;

import net.miginfocom.swing.MigLayout;

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
	private JButton btnJobOptions;

	public MainView() throws Exception {

		// BeautyEyeLNFHelper.frameBorderStyle =
		// BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow;
		// UIManager.put("RootPane.setupButtonVisible", false);
		// org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		WebLookAndFeel.install();

		frmIntegrationAssistant = new JFrame();
		frmIntegrationAssistant.setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MainView.class.getResource("/com/shaffer/integrationhelper/view/favicons.png")));
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
		panel.setLayout(null);

		JSeparator separator_5 = new JSeparator();
		separator_5.setBounds(7, 7, 1, 1);
		panel.add(separator_5);

		JLabel lblGeneral = new JLabel("General:");
		lblGeneral.setBounds(7, 98, 57, 17);
		lblGeneral.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblGeneral);

		JLabel lblPayrollSystem = new JLabel("Payroll System:");
		lblPayrollSystem.setBounds(7, 129, 95, 17);
		lblPayrollSystem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblPayrollSystem);

		payrollComboBox = new JComboBox<String>();
		payrollComboBox.setBounds(203, 126, 83, 20);
		payrollComboBox.setModel(new DefaultComboBoxModel(new String[] { "InCode", "Sungard HTE", "Sungard IFAS" }));
		panel.add(payrollComboBox);

		JLabel lblFileType = new JLabel("File Type:");
		lblFileType.setBounds(6, 160, 58, 17);
		lblFileType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblFileType);

		fileTypeComboBox = new JComboBox<String>();
		fileTypeComboBox.setBounds(203, 157, 83, 20);
		fileTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Employee", "Benefit", "Accounts" }));
		panel.add(fileTypeComboBox);

		JLabel lblExecutimeVersion = new JLabel("ExecuTime Version:");
		lblExecutimeVersion.setBounds(7, 191, 120, 17);
		lblExecutimeVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblExecutimeVersion);

		versionComboBox = new JComboBox<String>();
		versionComboBox.setBounds(203, 188, 83, 20);
		versionComboBox.setModel(new DefaultComboBoxModel(new String[] { "4.x", "5.x" }));
		panel.add(versionComboBox);

		JLabel lblDatabaseConfigSettings = new JLabel("Database Config Settings:");
		lblDatabaseConfigSettings.setBounds(7, 216, 179, 17);
		lblDatabaseConfigSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblDatabaseConfigSettings);

		JLabel lblCreateScheduledJobs = new JLabel("Create scheduled jobs");
		lblCreateScheduledJobs.setBounds(7, 244, 135, 17);
		lblCreateScheduledJobs.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblCreateScheduledJobs);

		scheduledJobsCheckBox = new JCheckBox("");
		scheduledJobsCheckBox.setBounds(203, 244, 21, 21);
		panel.add(scheduledJobsCheckBox);

		JLabel lblSetupDefaultDepartments = new JLabel("Setup default departments");
		lblSetupDefaultDepartments.setBounds(7, 272, 163, 17);
		lblSetupDefaultDepartments.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultDepartments);

		departmentsCheckBox = new JCheckBox("");
		departmentsCheckBox.setBounds(203, 268, 21, 21);
		panel.add(departmentsCheckBox);

		JLabel lblSetupDefaultLocations = new JLabel("Setup default Locations");
		lblSetupDefaultLocations.setBounds(7, 300, 143, 17);
		lblSetupDefaultLocations.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultLocations);

		locationCheckBox = new JCheckBox("");
		locationCheckBox.setBounds(203, 296, 21, 21);
		panel.add(locationCheckBox);

		JLabel lblSetupDefaultAdmin = new JLabel("Setup default admin properties");
		lblSetupDefaultAdmin.setBounds(7, 328, 187, 17);
		lblSetupDefaultAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(lblSetupDefaultAdmin);

		adminPropertiesCheckBox = new JCheckBox("");
		adminPropertiesCheckBox.setBounds(203, 328, 21, 21);
		panel.add(adminPropertiesCheckBox);

		JLabel lblVersion = new JLabel("Alpha Version: 0.02");
		lblVersion.setBounds(7, 487, 279, 14);
		panel.add(lblVersion);

		JLabel logo = new JLabel("New label");
		logo.setIcon(new ImageIcon(
				MainView.class.getResource("/com/shaffer/integrationhelper/view/IntegrationAssistantLogo.png")));
		logo.setBounds(223, 7, 293, 69);
		panel.add(logo);

		btnJobOptions = new JButton("Job Options");
		btnJobOptions.setBounds(230, 243, 89, 23);
		panel.add(btnJobOptions);
		btnJobOptions.setVisible(false);

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

		// remove scrollPane to stop stack overflow on window builder - remember to add
		// back later
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

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Payroll XML Builder", null, panel_3, null);

		JLabel lblComingSoon = new JLabel("Coming soon");
		panel_3.add(lblComingSoon);

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

	public JButton getBtnJobOptions() {
		return btnJobOptions;
	}

	public void setBtnJobOptions(JButton btnJobOptions) {
		this.btnJobOptions = btnJobOptions;
	}

}
