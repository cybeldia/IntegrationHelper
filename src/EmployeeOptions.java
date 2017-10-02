import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.event.ActionEvent;

public class EmployeeOptions extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField payPeriodTextField;
	private JTextField employeeTypesTextField;
	private JTextField employeeStatusTextField;
	private JTextField departmentsTextField;
	
	private static List<String> payPeriods;
	private static List<String> employeeTypes;
	private static List<String> employeeStatus;
	private static List<String> departments;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EmployeeOptions dialog = new EmployeeOptions();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EmployeeOptions() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		{
			JLabel lblInputSettingFor = new JLabel("Input settings for the employee file below separated by a comma");
			contentPanel.add(lblInputSettingFor, "cell 0 0");
		}
		{
			JLabel lblPayPeriodNames = new JLabel("Pay Period Names:");
			contentPanel.add(lblPayPeriodNames, "flowx,cell 0 1");
		}
		{
			JLabel lblEmployeeTypes = new JLabel("Employee Types:");
			contentPanel.add(lblEmployeeTypes, "flowx,cell 0 2");
		}
		{
			JLabel lblEmployeeStatus = new JLabel("Employee Status:");
			contentPanel.add(lblEmployeeStatus, "flowx,cell 0 3");
		}
		{
			payPeriodTextField = new JTextField();
			payPeriodTextField.setToolTipText("Field is case-sensitive");
			contentPanel.add(payPeriodTextField, "cell 0 1,growx");
			payPeriodTextField.setColumns(10);
		}
		{
			employeeTypesTextField = new JTextField();
			employeeTypesTextField.setToolTipText("Field is case-sensitive");
			contentPanel.add(employeeTypesTextField, "cell 0 2,growx");
			employeeTypesTextField.setColumns(10);
		}
		{
			employeeStatusTextField = new JTextField();
			employeeStatusTextField.setToolTipText("Field is case-sensitive");
			contentPanel.add(employeeStatusTextField, "cell 0 3,growx");
			employeeStatusTextField.setColumns(10);
		}
		{
			JLabel lblDepartments = new JLabel("Departments");
			contentPanel.add(lblDepartments, "flowx,cell 0 4");
		}
		{
			departmentsTextField = new JTextField();
			departmentsTextField.setToolTipText("Field is case-sensitive");
			departmentsTextField.setColumns(10);
			contentPanel.add(departmentsTextField, "cell 0 4,grow");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						/*
						String[] payPeriodsArray = payPeriodTextField.getText().split(",");
						String[] employeeTypesArray = employeeTypesTextField.getText().split(",");
						String[] employeeStatusArray = employeeStatusTextField.getText().split(",");
						*/
						if(!payPeriodTextField.getText().isEmpty()) {
						payPeriods = new ArrayList<String>(Arrays.asList(payPeriodTextField.getText().split("\\s*,\\s*")));
						}
						if(!employeeTypesTextField.getText().isEmpty()) {
						employeeTypes = new ArrayList<String>(Arrays.asList(employeeTypesTextField.getText().split("\\s*,\\s*")));
						}
						if(!employeeStatusTextField.getText().isEmpty()) {
						employeeStatus = new ArrayList<String>(Arrays.asList(employeeStatusTextField.getText().split("\\s*,\\s*")));
						}
						
						if(!departmentsTextField.getText().isEmpty()) {
						departments = new ArrayList<String>(Arrays.asList(employeeStatusTextField.getText().split("\\s*,\\s*")));
						}
						setVisible(false);
						
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public List<String> returnPayPeriod() {
		return EmployeeOptions.payPeriods;
	}
	
	public List<String> returnEmployeeTypes() {
		return EmployeeOptions.employeeTypes;
	}
	
	public List<String> returnEmployeeStatus() {
		return EmployeeOptions.employeeStatus;
	}

}
