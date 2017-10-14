package com.shaffer.integrationhelper;

import java.awt.EventQueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.shaffer.integrationhelper.controller.MainController;
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
					// com.shaffer.integrationhelper.view.MainView mainView = new
					// com.shaffer.integrationhelper.view.MainView();
					com.shaffer.integrationhelper.view.EmployeeOptionsView employeeOptionsView = new com.shaffer.integrationhelper.view.EmployeeOptionsView();
					mainView.getFrmIntegrationAssistant().setVisible(true);
					// MainController mainController = new MainController(mainView,
					// employeeOptionsView);
					// mainController.initialize();
					MainController mainController = context.getBean(MainController.class);
					mainController.setMainView(mainView);
					mainController.setEmployeeOptionsView(employeeOptionsView);
					mainController.initialize();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
