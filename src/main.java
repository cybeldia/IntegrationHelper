import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;

public class main {

	public static void main(String[] args) throws IOException {

		System.out.println("Select payroll system for which to integrate with: ");
		System.out.println("1: InCode");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		if (input.equals("InCode")) {
			InCodeProcessor inCodeProcessor = new InCodeProcessor();
			//inCodeProcessor.InCodeValidator();
		} else {
			System.out.println("Please enter a valid payroll system");
		}
	}
}
