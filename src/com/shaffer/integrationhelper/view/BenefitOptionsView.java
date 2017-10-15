package com.shaffer.integrationhelper.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.springframework.stereotype.Component;

import net.miginfocom.swing.MigLayout;

@Component
public class BenefitOptionsView extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField benefitTextField;
	private JButton okButton;
	private JButton cancelButton;

	public BenefitOptionsView() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		{
			JLabel lblInputSettingFor = new JLabel("Input settings for the benefit file below separated by a comma");
			contentPanel.add(lblInputSettingFor, "cell 0 0");
		}
		{
			JLabel lblBenefitNames = new JLabel("Benefit Names:");
			contentPanel.add(lblBenefitNames, "flowx,cell 0 1");
		}
		{
			benefitTextField = new JTextField();
			benefitTextField.setToolTipText("Field is case-sensitive");
			contentPanel.add(benefitTextField, "cell 0 1,growx");
			benefitTextField.setColumns(10);
		}
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

	public JPanel getContentPanel() {
		return contentPanel;
	}

	public void setContentPanel(JPanel contentPanel) {
		this.contentPanel = contentPanel;
	}

	public JTextField getBenefitTextField() {
		return benefitTextField;
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

}
