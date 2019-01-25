package yyf.common.JDBC;

import java.io.FileInputStream;
import java.util.Properties;

public class DruidPoolProperty {
	public static final String datasourceJdbcUrl;
	public static final String datasourceDriverClassname;
	public static final String datasourceUsername;
	public static final String datasourcePassword;
	public static final String datasourceFilters;
	public static final int datasourceMaxActive;
	public static final int datasourceInitialSize;
	public static final long datasourceMaxWait;
	public static final int datasourceMinIdle;
	public static final long datasourceTimeBetweenEvictionRunsMillis;
	public static final long datasourceMinEvictAbleIdleTimeMillis;
	public static final boolean datasourceTestWhileIdle;
	public static final boolean datasourceTestOnBorrow;
	public static final boolean datasourceTestOnReturn;
	public static final boolean datasourcePoolPreparedStatements;
	public static final int datasourceMaxOpenPreparedStatements;
	public static final boolean datasourceAsyncInit;
	


	private static String FILE_NAME = "conf/DruidPool.properties";
	static {
		Properties props = new Properties();
		try {
			props.load(new FileInputStream(FILE_NAME));
			datasourceJdbcUrl = props.getProperty("datasource.jdbc.url");
			datasourceDriverClassname = props.getProperty("datasource.driver.classname");
			datasourceUsername = props.getProperty("datasource.username");
			datasourcePassword = props.getProperty("datasource.password");
			datasourceFilters = props.getProperty("datasource.filters");
			datasourceMaxActive = Integer.parseInt(props.getProperty("datasource.maxActive"));
			datasourceInitialSize = Integer.parseInt(props.getProperty("datasource.initialSize"));
			datasourceMaxWait = Long.parseLong(props.getProperty("datasource.maxWait"));
			datasourceMinIdle = Integer.parseInt(props.getProperty("datasource.minIdle"));
			datasourceTimeBetweenEvictionRunsMillis = Long
					.parseLong(props.getProperty("datasource.timeBetweenEvictionRunsMillis"));
			datasourceMinEvictAbleIdleTimeMillis = Long
					.parseLong(props.getProperty("datasource.minEvictAbleIdleTimeMillis"));
			datasourceTestWhileIdle = Boolean.parseBoolean(props.getProperty("datasource.testWhileIdle"));
			datasourceTestOnBorrow = Boolean.parseBoolean(props.getProperty("datasource.testOnBorrow"));
			datasourceTestOnReturn = Boolean.parseBoolean(props.getProperty("datasource.testOnReturn"));
			datasourcePoolPreparedStatements = Boolean
					.parseBoolean(props.getProperty("datasource.poolPreparedStatements"));
			datasourceMaxOpenPreparedStatements = Integer
					.parseInt(props.getProperty("datasource.maxOpenPreparedStatements"));
			datasourceAsyncInit = Boolean.parseBoolean(props.getProperty("datasource.asyncInit"));
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}



}
