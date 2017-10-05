
import java.awt.EventQueue;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import View.*;
import controller.*;
import di.configuration.DIConfiguration;
import model.*;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
				    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow; 
					UIManager.put("RootPane.setupButtonVisible", false);
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					View.MainView mainView = new View.MainView();
					View.EmployeeOptionsView employeeOptionsView = new View.EmployeeOptionsView();
					mainView.getFrmIntegrationAssistant().setVisible(true);
					MainController mainController = new MainController(mainView, employeeOptionsView);
					mainController.initialize();
					
					ProcessorThread processorThread  = context.getBean(ProcessorThread.class);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
