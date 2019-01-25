package yyf.common.JDBC;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;

public class DruidPool {
	private DruidDataSource druidDataSource;
	private static DruidPool druidPool = null;

	private DruidPool() {
		if (druidDataSource == null) {
         synchronized (HikariDataSource.class) {
				initDB();
         }
        }
	}

	private void initDB() {
		try {
			druidDataSource = new DruidDataSource();
			druidDataSource.setUrl(DruidPoolProperty.datasourceJdbcUrl);
			druidDataSource.setDriverClassName(DruidPoolProperty.datasourceDriverClassname);
			druidDataSource.setUsername(DruidPoolProperty.datasourceUsername);
			druidDataSource.setPassword(DruidPoolProperty.datasourcePassword);
			druidDataSource.setFilters(DruidPoolProperty.datasourceFilters);
			druidDataSource.setMaxActive(DruidPoolProperty.datasourceMaxActive);
			druidDataSource.setInitialSize(DruidPoolProperty.datasourceInitialSize);
			druidDataSource.setMaxWait(DruidPoolProperty.datasourceMaxWait);
			druidDataSource.setMinIdle(DruidPoolProperty.datasourceMinIdle);
			druidDataSource.setTimeBetweenEvictionRunsMillis(DruidPoolProperty.datasourceTimeBetweenEvictionRunsMillis);
			druidDataSource.setMinEvictableIdleTimeMillis(DruidPoolProperty.datasourceMinEvictAbleIdleTimeMillis);
			druidDataSource.setTestWhileIdle(DruidPoolProperty.datasourceTestWhileIdle);
			druidDataSource.setTestOnBorrow(DruidPoolProperty.datasourceTestOnBorrow);
			druidDataSource.setTestOnReturn(DruidPoolProperty.datasourceTestOnReturn);
			druidDataSource.setPoolPreparedStatements(DruidPoolProperty.datasourcePoolPreparedStatements);
			druidDataSource.setMaxOpenPreparedStatements(DruidPoolProperty.datasourceMaxOpenPreparedStatements);
			druidDataSource.setAsyncInit(DruidPoolProperty.datasourceAsyncInit);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static DruidPool getInstance() {
		if (druidPool == null) {
			synchronized (DruidPool.class) {
				druidPool = new DruidPool();
				return druidPool;
			}
		}
		return druidPool;
	}

	public Connection getConnection() {
		Connection connection = null;
		if (druidDataSource == null) {
			getInstance();
		}
		try {
			connection = druidDataSource.getConnection();
		} catch (Exception e) {
			return connection;
		}
		return connection;
	}

	public Boolean close(Connection connection) {
		try {
			connection.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void close(Connection connection, Statement statement, ResultSet resultSet) {
		try {
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (statement != null) {
				statement.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
	}

}
