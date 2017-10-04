
import java.awt.EventQueue;

import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import Controller.*;
import View.*;
import Model.*;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.generalNoTranslucencyShadow; 
					UIManager.put("RootPane.setupButtonVisible", false);
					org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
					View.MainView mainView = new View.MainView();
					mainView.getFrmIntegrationAssistant().setVisible(true);
					EmployeeOptionsView employeeOptionsView = new EmployeeOptionsView();
					MainController mainController = new MainController(mainView, employeeOptionsView);
					mainController.initialize();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
