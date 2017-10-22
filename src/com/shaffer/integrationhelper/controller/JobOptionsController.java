package com.shaffer.integrationhelper.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.view.SungardHTEOptionsView;

@Component
public class JobOptionsController {

	@Autowired
	private SungardHTEOptionsView sungardHTEOptionsView;
	@Autowired
	private ApplicationSettings applicationSettings;
	private ActionListener actionListener;

	public void initialize() {
		updateSungardOptions();
	}

	public void updateSungardOptions() {
		actionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				applicationSettings
						.setBenefitJob((sungardHTEOptionsView.getComboBox().getSelectedItem().toString().trim()));
				sungardHTEOptionsView.setVisible(false);
			}
		};
		sungardHTEOptionsView.getOkButton().addActionListener(actionListener);
		sungardHTEOptionsView.getCancelButton().addActionListener(actionListener);
	}

}
