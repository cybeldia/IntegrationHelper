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
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

@Component
public class SungardHTEOptions extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox comboBox;

	public SungardHTEOptions() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(SungardHTEOptions.class.getResource("/com/shaffer/integrationhelper/view/favicons.png")));
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new MigLayout("", "[grow]", "[][][][][]"));
		{
			JLabel lblHTEBenefitTxt = new JLabel("Please select a class for the benefit job:");
			contentPanel.add(lblHTEBenefitTxt, "cell 0 0");
		}
		{
			comboBox = new JComboBox();
			comboBox.setModel(new DefaultComboBoxModel(new String[] {"net.executime.dataimport.HteBenefitBuild", "net.executime.dataimport.HteDateBenefitBuild", "net.executime.dataimport.HteLastCheckBenefitBuild", "net.executime.dataimport.HtePreviousBalanceBenefifBuild", "net.executime.dataimport.HteDateActiveStatusBenefitBuild", "net.executime.dataimport.HteDateActiveStatusBenefitBuild", "net.executime.dataimport.HteBenefitBuildMultiLibrary"}));
			contentPanel.add(comboBox, "cell 0 1,growx");
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

}
