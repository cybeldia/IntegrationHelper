package com.shaffer.integrationhelper;

import java.awt.EventQueue;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import com.shaffer.integrationhelper.controller.*;
import com.shaffer.integrationhelper.diconfiguration.DIConfiguration;
import com.shaffer.integrationhelper.model.*;
import com.shaffer.integrationhelper.service.IProcessorThread;
import com.shaffer.integrationhelper.service.impl.Validator;
import com.shaffer.integrationhelper.view.*;

public class Main {
	
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
					MainView mainView = context.getBean(MainView.class);
					//com.shaffer.integrationhelper.view.MainView mainView = new com.shaffer.integrationhelper.view.MainView();
					com.shaffer.integrationhelper.view.EmployeeOptionsView employeeOptionsView = new com.shaffer.integrationhelper.view.EmployeeOptionsView();
					mainView.getFrmIntegrationAssistant().setVisible(true);
					//MainController mainController = new MainController(mainView, employeeOptionsView);
					//mainController.initialize();
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
