package yyf.comment.configuration;

import java.io.FileInputStream;
import java.util.Properties;

public class CustomProperty {
	public static final String datasourceJdbcUrl;
	public static final String datasourceDriverClassname;
	public static final String datasourceUsername;
	public static final String datasourcePassword;
	public static final Boolean datasourceReadonly;
	public static final Long datasourceConnectionTimeout;
	public static final Long datasourceValidationTimeout;
	public static final Integer datasourceLoginTimeout;
	public static final Long datasourceIdleTimeout;
	public static final Long datasourceMaxLifetime;
	public static final Integer datasourceMaximunSize;
	
	public static final String esAddress;


	private static String FILE_NAME = "viewpoint.properties";
	static {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(FILE_NAME));
			datasourceJdbcUrl = props.getProperty("datasource.jdbc.url");
			datasourceDriverClassname = props.getProperty("datasource.driver.classname");
			datasourceUsername = props.getProperty("datasource.username");
			datasourcePassword = props.getProperty("datasource.password");
			datasourceReadonly = Boolean.parseBoolean(props.getProperty("datasource.readonly"));
			datasourceConnectionTimeout = Long.parseLong(props.getProperty("datasource.connection.timeout"));
			datasourceValidationTimeout = Long.parseLong(props.getProperty("datasource.validation.timeout"));
			datasourceLoginTimeout = Integer.parseInt(props.getProperty("datasource.logintimeout"));
			datasourceIdleTimeout = Long.parseLong(props.getProperty("datasource.idle.timeout"));
			datasourceMaxLifetime = Long.parseLong(props.getProperty("datasource.max.lifetime"));
			datasourceMaximunSize = Integer.parseInt(props.getProperty("datasource.maximum.pool.size"));
			
			esAddress = props.getProperty("es.address");

			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}



}
