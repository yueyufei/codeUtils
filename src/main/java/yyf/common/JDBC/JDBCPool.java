package yyf.common.JDBC;

import java.sql.Connection;

import com.zaxxer.hikari.HikariDataSource;

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
			HikariPool.setJdbcUrl(JDBCPoolProperty.datasourceJdbcUrl);
			HikariPool.setDriverClassName(JDBCPoolProperty.datasourceDriverClassname);
			HikariPool.setUsername(JDBCPoolProperty.datasourceUsername);
			HikariPool.setPassword(JDBCPoolProperty.datasourcePassword);
			HikariPool.setReadOnly(JDBCPoolProperty.datasourceReadonly);
			HikariPool.setConnectionTimeout(JDBCPoolProperty.datasourceConnectionTimeout);
			HikariPool.setValidationTimeout(JDBCPoolProperty.datasourceValidationTimeout);
			HikariPool.setLoginTimeout(JDBCPoolProperty.datasourceLoginTimeout);
			HikariPool.setIdleTimeout(JDBCPoolProperty.datasourceIdleTimeout);
			HikariPool.setMaxLifetime(JDBCPoolProperty.datasourceMaxLifetime);
			HikariPool.setMaximumPoolSize(JDBCPoolProperty.datasourceMaximunSize);
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
