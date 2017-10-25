package com.shaffer.integrationhelper.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import com.alee.laf.WebLookAndFeel;

import net.miginfocom.swing.MigLayout;
import java.awt.Color;

@Component
public class SungardIFASOptionsView extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox benefitComboBox;
	private JLabel lblPleaseSelectA;
	private JComboBox employeeComboBox;
	private JLabel lblPleaseSelectA_1;
	private JComboBox departmentComboBox;
	private JLabel ifasWarning;

	public SungardIFASOptionsView() {
		WebLookAndFeel.install();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SungardIFASOptionsView.class.getResource("/com/shaffer/integrationhelper/view/favicons.png")));
		setBounds(100, 100, 501, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][]"));
		{
			lblPleaseSelectA = new JLabel("Please select a class for the employee job:");
			contentPanel.add(lblPleaseSelectA, "cell 0 0");
		}
		{
			employeeComboBox = new JComboBox();
			employeeComboBox.setModel(
					new DefaultComboBoxModel(new String[] {"net.executime.dataimport.SungardIfasActiveOnlyEmployeeBuild", "net.executime.dataimport.SungardIfasActiveOnlyInformixEmployeeBuild"}));
			contentPanel.add(employeeComboBox, "cell 0 1,growx");
		}
		{
			JLabel lblHTEBenefitTxt = new JLabel("Please select a class for the benefit job:");
			contentPanel.add(lblHTEBenefitTxt, "cell 0 3");
		}
		{
			benefitComboBox = new JComboBox();
			benefitComboBox.setModel(new DefaultComboBoxModel(new String[] {"net.executime.dataimport.SungardIfasBenefitBuild", "net.executime.dataimport.SungardIfasInformixBenefitBuild"}));
			contentPanel.add(benefitComboBox, "cell 0 4,growx");
		}
		{
			lblPleaseSelectA_1 = new JLabel("Please select a class for the department job:");
			contentPanel.add(lblPleaseSelectA_1, "cell 0 6");
		}
		{
			departmentComboBox = new JComboBox();
			departmentComboBox.setModel(new DefaultComboBoxModel(new String[] {"net.executime.dataimport.organizationunit.SungardIfasDepartmentBuild", "net.executime.dataimport.organizationunit.SungardIfasInformixDepartmentBuild", "net.executime.dataimport.organizationunit.SungardIfasDepartmentBuild2", "net.executime.dataimport.organizationunit.SungardIfasInformixDepartmentBuild2", "net.executime.dataimport.organizationunit.SungardIfasDepartmentBuild3", "net.executime.dataimport.organizationunit.SungardIfasInformixDepartmentBuild3", "net.executime.dataimport.organizationunit.SungardIfasDepartmentBuild4", "net.executime.dataimport.organizationunit.SungardIfasInformixDepartmentBuild4", ""}));
			contentPanel.add(departmentComboBox, "cell 0 7,growx");
		}
		{
			ifasWarning = new JLabel("Please note that there are SQL and Informix options for each class");
			ifasWarning.setForeground(Color.RED);
			contentPanel.add(ifasWarning, "cell 0 8");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");

				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");

				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public void setOkButton(JButton okButton) {
		this.okButton = okButton;
	}

	public JButton getCancelButton() {
		return cancelButton;
	}

	public void setCancelButton(JButton cancelButton) {
		this.cancelButton = cancelButton;
	}

	public JComboBox getComboBox() {
		return benefitComboBox;
	}

	public void setComboBox(JComboBox comboBox) {
		this.benefitComboBox = comboBox;
	}

	public JComboBox getBenefitComboBox() {
		return benefitComboBox;
	}

	public void setBenefitComboBox(JComboBox benefitComboBox) {
		this.benefitComboBox = benefitComboBox;
	}

	public JComboBox getEmployeeComboBox() {
		return employeeComboBox;
	}

	public void setEmployeeComboBox(JComboBox employeeComboBox) {
		this.employeeComboBox = employeeComboBox;
	}

	public JComboBox getDepartmentComboBox() {
		return departmentComboBox;
	}

	public void setDepartmentComboBox(JComboBox departmentComboBox) {
		this.departmentComboBox = departmentComboBox;
	}

}
