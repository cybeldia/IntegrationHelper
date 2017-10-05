package service;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import model.InCodeEmployee;

public interface InCodeProcessorService extends ProcessorService {
	public void InCodeValidator(String filePath) throws IllegalStateException, FileNotFoundException, IOException;
	public int HeaderCount(String filePath);
	public List<InCodeEmployee> ValidateDates();

	
}
