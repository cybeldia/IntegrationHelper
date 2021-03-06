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

@Component
public class SungardHTEOptionsView extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox benefitComboBox;
	private JLabel lblPleaseSelectA;
	private JComboBox employeeComboBox;

	public SungardHTEOptionsView() {
		WebLookAndFeel.install();
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(SungardHTEOptionsView.class.getResource("/com/shaffer/integrationhelper/view/favicons.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][]"));
		{
			lblPleaseSelectA = new JLabel("Please select a class for the employee job:");
			contentPanel.add(lblPleaseSelectA, "cell 0 0");
		}
		{
			employeeComboBox = new JComboBox();
			employeeComboBox.setModel(
					new DefaultComboBoxModel(new String[] { "net.executime.dataimport.HteEmployeeBuildHostInterface",
							"net.executime.dataimport.HteEmployeeBuildHostInterfacePlusHR",
							"net.executime.dataimport.HteEmployeeBuildHostInterfaceMultiLibrary" }));
			contentPanel.add(employeeComboBox, "cell 0 1,growx");
		}
		{
			JLabel lblHTEBenefitTxt = new JLabel("Please select a class for the benefit job:");
			contentPanel.add(lblHTEBenefitTxt, "cell 0 3");
		}
		{
			benefitComboBox = new JComboBox();
			benefitComboBox.setModel(new DefaultComboBoxModel(new String[] { "net.executime.dataimport.HteBenefitBuild",
					"net.executime.dataimport.HteDateBenefitBuild", "net.executime.dataimport.HteLastCheckBenefitBuild",
					"net.executime.dataimport.HtePreviousBalanceBenefifBuild",
					"net.executime.dataimport.HteDateActiveStatusBenefitBuild",
					"net.executime.dataimport.HteDateActiveStatusBenefitBuild",
					"net.executime.dataimport.HteBenefitBuildMultiLibrary" }));
			contentPanel.add(benefitComboBox, "cell 0 4,growx");
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
	
	

}
