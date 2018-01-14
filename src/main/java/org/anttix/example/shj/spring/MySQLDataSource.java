package org.anttix.example.shj.spring;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.dialect.HSQLDialect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("db-mysql")
public class MySQLDataSource {
	private static final String DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/spring-hibernate-jersey";
	
	/**
	 * User following query to create dev user with dev password
	 * CREATE USER 'dev'@'localhost' IDENTIFIED BY 'dev';
     * GRANT ALL PRIVILEGES ON *.* TO 'dev'@'localhost' WITH GRANT OPTION;
	 */
	private static final String DB_USER = "dev";
	private static final String DB_PASSWORD = "dev";

	private static final int CONN_POOL_SIZE = 5;

	@Bean
	public DataSource dataSource() {
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(DRIVER_CLASS_NAME);
		bds.setUrl(DB_URL);
		bds.setUsername(DB_USER);
		bds.setPassword(DB_PASSWORD);
		bds.setInitialSize(CONN_POOL_SIZE);

		return bds;
	}
	
	@Bean(name = "hibernateProperties")
	public Properties hibernateProperties() {
		return new Properties() {
			private static final long serialVersionUID = 1779427567126967954L;
			{
				setProperty(AvailableSettings.DIALECT, HSQLDialect.class.getName());
				setProperty(AvailableSettings.HBM2DDL_AUTO, "create-drop");
				setProperty(AvailableSettings.SHOW_SQL, "true");
				setProperty(AvailableSettings.USE_SQL_COMMENTS, "true");
				//setProperty(AvailableSettings.HBM2DDL_IMPORT_FILES, "/test-data.sql");
			}
		};
	}
}
