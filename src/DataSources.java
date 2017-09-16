import org.simpleframework.xml.*;


@Root
public class DataSources {
	
	@Element(name="local-tx-datasource")
	private String localTXDatasource;
	
	@Element(name="jndi-name")
	private String jndiName;
	
	@Element(name="connection-url")
	private String connectionURL;
	
	@Element(name="driver-class")
	private String driverClass;

	@Element(name="user-name")
	private String userName;
	
	@Element(name="password")
	private String password;
	
	@Element(name="autoReconnect", required=false)
	private String autoReconnect;
	
	@Element(name="check-valid-connection-sql", required=false)
	private String checkValid;
	
	@Element(name="failOverReadOnly", required=false)
	private String failOverReadOnly;
	
	@Element(name="initialTimeout", required=false)
	private String initialTimeout;
	
	@Element(name="min-pool-size", required=false)
	private String minPool;
	
	@Element(name="max-pool-size", required=false)
	private String maxPool;
	
	@Element(name="blocking-timeout-millis", required=false)
	private String blockingTimeout;
	
	@Element(name="idle-timeout-minutes", required=false)
	private String idleTimeOut;
	
	@Element(name="set-tx-query-timeout",required=false)
	private String setTxQuery;
	
	@Element(name="query-timeout", required=false)
	private String queryTimeout;
	
	@Element(name="metadata", required=false)
	private String metaData;
	
	@Element(name="type-mapping", required=false)
	private String typeMapping;
	
	

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
