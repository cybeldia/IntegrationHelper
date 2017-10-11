package com.shaffer.integrationhelper;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JTabbedPane;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import java.awt.TextArea;
import javax.swing.JTextPane;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.FrameBorderStyle;

import com.shaffer.integrationhelper.service.impl.QueryExecuter;
import com.shaffer.integrationhelper.service.impl.XMLToDBConnection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JSplitPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;

import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class MainApp {

	private JFrame frmIntegrationAssistant;
	private static JTextField fileText;
	private static JTextArea parsedLinesTextArea;
	private static MainApp window;
	private static JComboBox<String> payrollComboBox;
	private static EmployeeOptions employeeOptions;
	private static JTextField serverXmlTextField;
	private JTable table;
	private JCheckBox scheduledJobsCheckBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow; 
					UIManager.put("RootPane.setupButtonVisible", false);
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();

					window = new MainApp();
					window.frmIntegrationAssistant.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainApp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
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

		MainApp.payrollComboBox = new JComboBox<String>();
		payrollComboBox
		.setModel(new DefaultComboBoxModel<String>(new String[] { "InCode", "Generic"}));
		
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
								
										JComboBox<String> comboBox_1 = new JComboBox<String>();
										comboBox_1.setModel(new DefaultComboBoxModel<String>(new String[] { "Employee", "Benefit", "Accounts" }));
										panel.add(comboBox_1, "cell 1 5,alignx left");
										
										JLabel lblExecutimeVersion = new JLabel("ExecuTime Version:");
										lblExecutimeVersion.setFont(new Font("Tahoma", Font.PLAIN, 14));
										panel.add(lblExecutimeVersion, "cell 0 6,alignx left");
										
										JComboBox<String> comboBox = new JComboBox<String>();
										comboBox.setModel(new DefaultComboBoxModel(new String[] {"4.x", "5.x"}));
										panel.add(comboBox, "cell 1 6,alignx left");
										
										JLabel lblDatabaseConfigSettings = new JLabel("Database Config Settings:");
										lblDatabaseConfigSettings.setFont(new Font("Tahoma", Font.BOLD, 14));
										panel.add(lblDatabaseConfigSettings, "cell 0 8");
										
										JLabel lblCreateScheduledJobs = new JLabel("Create scheduled jobs");
										lblCreateScheduledJobs.setFont(new Font("Tahoma", Font.PLAIN, 14));
										panel.add(lblCreateScheduledJobs, "cell 0 9");
										
										JCheckBox scheduledJobsCheckBox = new JCheckBox("");
										panel.add(scheduledJobsCheckBox, "cell 1 9");
										
										JLabel lblSetupDefaultDepartments = new JLabel("Setup default departments");
										lblSetupDefaultDepartments.setFont(new Font("Tahoma", Font.PLAIN, 14));
										panel.add(lblSetupDefaultDepartments, "cell 0 10");
										
										JCheckBox departmentsCheckBox = new JCheckBox("");
										panel.add(departmentsCheckBox, "cell 1 10");
										
										JLabel lblSetupDefaultLocations = new JLabel("Setup default Locations");
										lblSetupDefaultLocations.setFont(new Font("Tahoma", Font.PLAIN, 14));
										panel.add(lblSetupDefaultLocations, "cell 0 11");
										
										JCheckBox locationCheckBox = new JCheckBox("");
										panel.add(locationCheckBox, "cell 1 11");
										
										JLabel lblSetupDefaultAdmin = new JLabel("Setup default admin properties");
										lblSetupDefaultAdmin.setFont(new Font("Tahoma", Font.PLAIN, 14));
										panel.add(lblSetupDefaultAdmin, "cell 0 12");
										
										JCheckBox adminPropertiesCheckBox = new JCheckBox("");
										panel.add(adminPropertiesCheckBox, "cell 1 12");

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Flat File Helper", null, panel_1, null);
		panel_1.setLayout(new MigLayout("", "[grow]", "[][grow][][][][][][][][][][][][grow][grow][][grow]"));
		
		MainApp.employeeOptions = new EmployeeOptions();
		
		JButton btnEmployeeCheckerSettings = new JButton("Fields to validate");
		btnEmployeeCheckerSettings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainApp.employeeOptions.setVisible(true);
				MainApp.employeeOptions.requestFocus();
			}
		});
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

		JTextArea errorsTextArea = new JTextArea();
		errorsPane.setViewportView(errorsTextArea);

		JScrollPane parsedLinesPane = new JScrollPane();
		tabbedPane_1.addTab("Parsed Lines", null, parsedLinesPane, null);

		JTextArea parsedLinesTextArea = new JTextArea();
		parsedLinesPane.setViewportView(parsedLinesTextArea);

		JButton btnChooseFile = new JButton("Choose File:");
		btnChooseFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String pickedFile = chooser.getSelectedFile().getPath();
					fileText.setText(pickedFile);
				}

			}
		});
		panel_1.add(btnChooseFile, "flowx,cell 0 0,alignx left");

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Database Config", null, panel_2, null);
		panel_2.setLayout(new MigLayout("", "[grow][]", "[][][][grow][]"));
		
		JLabel lblPleaseSelectThe = new JLabel("Please select the server.xml for your instance:");
		lblPleaseSelectThe.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel_2.add(lblPleaseSelectThe, "cell 0 0");
		
		JButton btnChooseFile_1 = new JButton("Choose File:");
		btnChooseFile_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();

				int returnVal = chooser.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String pickedFile = chooser.getSelectedFile().getPath();
					serverXmlTextField.setText(pickedFile);
				}
			}
		});
		panel_2.add(btnChooseFile_1, "flowx,cell 0 1");
		
		serverXmlTextField = new JTextField();
		panel_2.add(serverXmlTextField, "cell 0 1,growx");
		serverXmlTextField.setColumns(10);
		
		JButton btnTestDatabaseConnection = new JButton("Test Database Connection");
		btnTestDatabaseConnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					XMLToDBConnection connection = new XMLToDBConnection();
					CurrentMapping cm = new CurrentMapping();
					Connection conn = connection.DBConnection(MainApp.this);
					table.setModel(DbUtils.resultSetToTableModel(cm.GetCurrentMapping(conn)));
					conn.close();
					JOptionPane.showMessageDialog(null, "Connection successful");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Connection unsuccessful");
					e.printStackTrace();
				}
			}
		});
		panel_2.add(btnTestDatabaseConnection, "cell 1 1");
		
		JSeparator separator_1 = new JSeparator();
		panel_2.add(separator_1, "cell 0 2 2 1,growx");
		
		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane, "cell 0 3 2 1,grow");
		
		table = new JTable();
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
			}
		));
		scrollPane.setViewportView(table);
		
		JButton btnPopulateTable = new JButton("Populate Payroll Defaults");
		btnPopulateTable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(MainApp.payrollComboBox.getSelectedItem().toString().equals("InCode")) {
					PayrollDefaults pd = new PayrollDefaults();
					pd.setInCodeDefaults(table);
					table.repaint();
				}
			}
		});
		panel_2.add(btnPopulateTable, "cell 0 4");
		
		JButton btnExecute = new JButton("Execute");
		btnExecute.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				XMLToDBConnection connection = new XMLToDBConnection();
				Connection conn = connection.DBConnection(MainApp.this);
				QueryExecuter executer = new QueryExecuter();
				executer.QueryExecuter(table, conn);
				if (MainApp.payrollComboBox.getSelectedItem().toString().equals("InCode") && scheduledJobsCheckBox.isSelected()) {
					executer.createScheduledJobs(conn);
				}
				conn.close();
				JOptionPane.showMessageDialog(null, "Execution Successful");
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		panel_2.add(btnExecute, "cell 1 4,alignx right");

		JButton btnProcess = new JButton("Process");
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Runnable runnable = new ProcessorThread(window, parsedLinesTextArea, errorsTextArea);
				Thread thread = new Thread(runnable);
				thread.start();
			}
		});
		panel_1.add(btnProcess, "cell 0 11,alignx center");

		fileText = new JTextField();
		panel_1.add(fileText, "cell 0 0");
		fileText.setColumns(25);
	}

	public String getPayrollSystem() {
		return MainApp.payrollComboBox.getSelectedItem().toString();
	}

	public String getFile() {
		return MainApp.fileText.getText();
	}
	
	public String getServerFile() {
		return MainApp.serverXmlTextField.getText();
	}
	
	public EmployeeOptions getEmployeeOptions() {
		return MainApp.employeeOptions;
	}

}
