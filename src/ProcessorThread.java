import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JTextArea;

public class ProcessorThread implements Runnable {
	//All processing will run on this thread. Logic to determine what payroll processor to use will be handled here as well. 
	private final MainApp app;
	private final JTextArea parsedLinesTextArea;
	private final JTextArea errorsTextArea;
	
	//Reference problem didn't need to be passed here as the variables are class level variables in the other files. This was just experimenting.
	public ProcessorThread(MainApp appRef, JTextArea parsedLinestextAreaRef, JTextArea errorsTextArea) {
		this.app = appRef;
		this.parsedLinesTextArea = parsedLinestextAreaRef;
		this.errorsTextArea = errorsTextArea;
	}
	
	@Override
	public void run() {
		try {
			String payrollSystem = app.getPayrollSystem();
			if(payrollSystem.equals("InCode")) {
			InCodeProcessor inCodeProcessor = new InCodeProcessor();
			InCodeProcessor.InCodeValidator(this.app, this.parsedLinesTextArea, this.errorsTextArea);
			}
			else {
				System.out.println("Please select a valid payroll system");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
