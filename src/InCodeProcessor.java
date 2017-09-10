import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import org.apache.commons.beanutils.PropertyUtils;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class InCodeProcessor {

	@SuppressWarnings("unchecked")
	public static void InCodeValidator(MainApp app, JTextArea parsedLinesTextArea, JTextArea errorsTextArea)
			throws IllegalStateException, IOException {
		// Validator handles the actual parsing of the InCode file.
		int expectedColumnCount = 5;
		String csvFileName = app.getFile();

		// Resets text areas before processing
		parsedLinesTextArea.setText(null);
		errorsTextArea.setText(null);

		if (expectedColumnCount == HeaderCount(csvFileName)) {
			// Parse Csv into list of employees, later a new class will need to made that
			// inherits from employee called InCode employee
			List<Employee> list = new CsvToBeanBuilder<Employee>(new FileReader(csvFileName)).withType(Employee.class)
					.withThrowExceptions(true).withVerifyReader(true).build().parse();
			// Loop through employees check that fields are valid
			for (Object object : list) {
				Employee employee = (Employee) object;
				// System.out.println(employee.toString());
				DateValidator.isValidDate(employee, "mm/dd/yyyy", errorsTextArea);
				parsedLinesTextArea.append(employee.toString());
				// Check that csv matches user provided pay periods
				if (!app.getEmployeeOptions().returnPayPeriod().contains(employee.getPayPeriod().trim())) {
					errorsTextArea.append("Pay period does not match provided list: " + employee.getPayPeriod()
							+ System.lineSeparator());
				}
				
				if (!app.getEmployeeOptions().returnEmployeeTypes().contains(employee.getEmployeeType().trim())) {
					errorsTextArea.append("Employee type does not match provided list: " + employee.getEmployeeType()
							+ System.lineSeparator());
				}
				
			}

		} else {
			parsedLinesTextArea
					.append("Number of columns does not match expected number for InCode" + System.lineSeparator());
			errorsTextArea
					.append("Number of columns does not match expected number for InCode" + System.lineSeparator());
		}

	}

	// The mapping strategy below is not needed anymore, but I'm leaving it in as a
	// reference.
	@SuppressWarnings("rawtypes")
	public static ColumnPositionMappingStrategy setColumnMapping() {
		ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
		strategy.setType(Employee.class);
		String[] columns = new String[] { "name", "birthday", "address", "employeeType", "payPeriod" };
		strategy.setColumnMapping(columns);
		return strategy;

	}

	// Maybe seperate this out into another class later once the app handles more
	// than InCode.
	public static int HeaderCount(String path) throws IOException {

		int columnCount = 0;
		try {
			CSVReader csvReader = new CSVReader(new FileReader(path), ',', '\'');
			String[] header = csvReader.readNext();
			if (header != null) {
				columnCount = header.length;
			}
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Error: Please select a file to process");
		}
		return columnCount;
	}

}
