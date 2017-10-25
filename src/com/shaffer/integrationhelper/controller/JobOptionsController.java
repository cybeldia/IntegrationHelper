package com.shaffer.integrationhelper.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.view.SungardHTEOptionsView;
import com.shaffer.integrationhelper.view.SungardIFASOptionsView;

@Component
public class JobOptionsController {

	@Autowired
	private SungardHTEOptionsView sungardHTEOptionsView;
	@Autowired
	private SungardIFASOptionsView sungardIFASOptionsView;
	@Autowired
	private ApplicationSettings applicationSettings;
	private ActionListener actionListener;

	public void initialize() {
		updateSungardOptions();
		updateIFASOptions();
	}

	public void updateSungardOptions() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings
						.setBenefitJob((sungardHTEOptionsView.getBenefitComboBox().getSelectedItem().toString().trim()));
				applicationSettings
						.setEmployeeJob(sungardHTEOptionsView.getEmployeeComboBox().getSelectedItem().toString().trim());
				sungardHTEOptionsView.setVisible(false);
			}
		};
		sungardHTEOptionsView.getOkButton().addActionListener(actionListener);
		sungardHTEOptionsView.getCancelButton().addActionListener(actionListener);
	}
	
	public void updateIFASOptions() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings
						.setBenefitJob((sungardIFASOptionsView.getBenefitComboBox().getSelectedItem().toString().trim()));
				applicationSettings
						.setEmployeeJob(sungardIFASOptionsView.getEmployeeComboBox().getSelectedItem().toString().trim());
				applicationSettings
						.setDepartmentJob(sungardIFASOptionsView.getDepartmentComboBox().getSelectedItem().toString().trim());
				sungardIFASOptionsView.setVisible(false);
			}
		};
		sungardIFASOptionsView.getOkButton().addActionListener(actionListener);
		sungardIFASOptionsView.getCancelButton().addActionListener(actionListener);
	}

}
