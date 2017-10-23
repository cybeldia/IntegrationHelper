package com.shaffer.integrationhelper.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

@Root (name = "server", strict = false)
public class v5DataSource {

	//@Path("/*[local-name()='server']/*[local-name()='profile']/*[local-name()='subsystem'][4]/*[local-name()='datasources']/*[local-name()='datasource']/*[local-name()='connection-url']")
	//@Namespace(reference = "urn:jboss:domain:datasources:4.0")
	@Element(name = "connection-url")
	private String connectionURL;



	//@Path("/*[local-name()='server']/*[local-name()='profile']/*[local-name()='subsystem'][4]//*[local-name()='drivers']/*[local-name()='driver']/*[local-name()='driver-class']")
	//@Namespace(reference = "urn:jboss:domain:datasources:4.0")
	@Element(name = "driver-class")
	private String driverClass;
	


	//@Path("/*[local-name()='server']/*[local-name()='profile']/*[local-name()='subsystem'][4]")
	//@Namespace(reference = "urn:jboss:domain:datasources:4.0")
	@Element(name = "user-name")
	private String userName;
	

	//@Path("/*[local-name()='server']/*[local-name()='profile']/*[local-name()='subsystem'][4]")
	//@Namespace(reference = "urn:jboss:domain:datasources:4.0")
	@Element(name = "password")
	private String password;


	public String getConnectionURL() {
		return connectionURL;
	}

	public void setConnectionURL(String connectionURL) {
		this.connectionURL = connectionURL;
	}

	public String getDriverClass() {
		return driverClass;
	}

	public void setDriverClass(String driverClass) {
		this.driverClass = driverClass;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
