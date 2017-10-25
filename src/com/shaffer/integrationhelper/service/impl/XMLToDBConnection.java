package com.shaffer.integrationhelper.service.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shaffer.integrationhelper.model.ApplicationSettings;
import com.shaffer.integrationhelper.model.v4DataSource;

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
			File source = new File(filePath);

			SAXReader reader = new SAXReader();
			Document document = reader.read(filePath);

			Node connectionURLNode = document.selectSingleNode(
					"/*[name()='server']/*[name()='profile']/*[name()='subsystem'][4]/*[name()='datasources']/*[name()='datasource']/*[name()='connection-url']");
			String connectionURL = connectionURLNode.getStringValue();

			Node driverClassNode = document.selectSingleNode(
					"/*[name()='server']/*[name()='profile']/*[name()='subsystem'][4]//*[name()='drivers']/*[name()='driver']/*[name()='driver-class']");
			String driverClass = driverClassNode.getStringValue();

			Node usernameNode = document.selectSingleNode(
					"/*[name()='server']/*[name()='profile']/*[name()='subsystem'][4]/*[name()='datasources']/*[name()='datasource']/*[name()='security']/*[name()='user-name']");
			String username = usernameNode.getStringValue();

			Node passwordNode = document.selectSingleNode(
					"/*[name()='server']/*[name()='profile']/*[name()='subsystem'][4]/*[name()='datasources']/*[name()='datasource']/*[name()='security']/*[name()='password']");
			String password = passwordNode.getStringValue();

			Class.forName(driverClass);
			conn = DriverManager.getConnection(connectionURL, username, password);
		}
		return conn;
	}

}
