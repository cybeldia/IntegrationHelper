package com.shaffer.integrationhelper.service.impl;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.shaffer.integrationhelper.model.v4DataSource;

public class XMLToDBConnection {
	
	public Connection DBConnection(String filePath) throws Exception {
		Serializer serializer = new Persister();
		File source = new File(filePath);

		v4DataSource datasource = serializer.read(v4DataSource.class, source);
		Class.forName(datasource.getDriverClass());
		Connection conn = DriverManager.getConnection(datasource.getConnectionURL(), datasource.getUserName(),
				datasource.getPassword());
		return conn;
	}
	
}
