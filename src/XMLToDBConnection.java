import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class XMLToDBConnection {
	
	public Connection DBConnection(MainApp app) throws Exception {
		Serializer serializer = new Persister();
		File source = new File(app.getServerFile());

		DataSources datasource = serializer.read(DataSources.class, source);
		Class.forName(datasource.getDriverClass());
		Connection conn = DriverManager.getConnection(datasource.getConnectionURL(), datasource.getUserName(),
				datasource.getPassword());
		return conn;
	}
	
}
