package yyf.common.JDBC;

import java.sql.Connection;

import com.zaxxer.hikari.HikariDataSource;

import yyf.comment.configuration.CustomProperty;

public class JDBCPool {
	private HikariDataSource HikariPool;
	private static JDBCPool jdbcPool = null;
	private JDBCPool() {
	   if (HikariPool == null) {
         synchronized (HikariDataSource.class) {
				initDB();
         }
        }
	}

	private void initDB() {
		try {
			HikariPool = new HikariDataSource();
			HikariPool.setJdbcUrl(CustomProperty.datasourceJdbcUrl);
			HikariPool.setDriverClassName(CustomProperty.datasourceDriverClassname);
			HikariPool.setUsername(CustomProperty.datasourceUsername);
			HikariPool.setPassword(CustomProperty.datasourcePassword);
			HikariPool.setReadOnly(CustomProperty.datasourceReadonly);
			HikariPool.setConnectionTimeout(CustomProperty.datasourceConnectionTimeout);
			HikariPool.setValidationTimeout(CustomProperty.datasourceValidationTimeout);
			HikariPool.setLoginTimeout(CustomProperty.datasourceLoginTimeout);
			HikariPool.setIdleTimeout(CustomProperty.datasourceIdleTimeout);
			HikariPool.setMaxLifetime(CustomProperty.datasourceMaxLifetime);
			HikariPool.setMaximumPoolSize(CustomProperty.datasourceMaximunSize);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	public static JDBCPool getInstance() {
		if (jdbcPool == null) {
			synchronized (JDBCPool.class) {
				jdbcPool = new JDBCPool();
				return jdbcPool;
			}
		}
		return jdbcPool;
	}
	
	public Connection getConnection() {
		try {
			Connection connection = HikariPool.getConnection();
			return connection;
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean close(Connection connection) {
		try {
			HikariPool.evictConnection(connection);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
