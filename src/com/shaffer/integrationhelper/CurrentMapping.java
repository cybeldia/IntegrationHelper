package com.shaffer.integrationhelper;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

public class CurrentMapping {
	public ResultSet GetCurrentMapping(Connection conn) throws Exception {

		PreparedStatement st = conn.prepareStatement("SELECT * FROM employee_build_mapping");
		ResultSet rs = st.executeQuery();
		return rs;
	}
}
