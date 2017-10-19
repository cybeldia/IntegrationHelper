package com.shaffer.integrationhelper;

import java.awt.EventQueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shaffer.integrationhelper.controller.MainController;
import com.shaffer.integrationhelper.view.BenefitOptionsView;
import com.shaffer.integrationhelper.view.EmployeeOptionsView;
import com.shaffer.integrationhelper.view.MainView;

public class Main {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
							DIConfiguration.class);
					MainView mainView = context.getBean(MainView.class);

					EmployeeOptionsView employeeOptionsView = new EmployeeOptionsView();
					BenefitOptionsView benefitOptionsView = new BenefitOptionsView();
					mainView.getFrmIntegrationAssistant().setVisible(true);

					MainController mainController = context.getBean(MainController.class);
					mainController.setMainView(mainView);
					mainController.setEmployeeOptionsView(employeeOptionsView);
					mainController.setBenefitOptionsView(benefitOptionsView);
					mainController.initialize();
					
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
