package com.shaffer.integrationhelper.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.model.v4DataSource;
import com.shaffer.integrationhelper.model.v5DataSource;

@Component
public class XMLToDBConnection {

	@Autowired
	private ApplicationSettings applicationSettings;

	private Serializer serializer;
	private Connection conn;

	public Connection DBConnection(String filePath) throws Exception {

		// Logic to parse v4 server.xml
		if (applicationSettings.getVersion().equals("4.x")) {
			serializer = new Persister();
			File source = new File(filePath);

			v4DataSource datasource = serializer.read(v4DataSource.class, source);
			Class.forName(datasource.getDriverClass());
			conn = DriverManager.getConnection(datasource.getConnectionURL(), datasource.getUserName(),
					datasource.getPassword());

		}
		// Logic to parse v5 config.xml
		else if (applicationSettings.getVersion().equals("5.x")) {
			serializer = new Persister();
			File source = new File(filePath);

			v5DataSource datasource = serializer.read(v5DataSource.class, source);
			Class.forName(datasource.getDriverClass());
			conn = DriverManager.getConnection(datasource.getConnectionURL(), datasource.getUserName(),
					datasource.getPassword());
		}
		return conn;
	}

}
