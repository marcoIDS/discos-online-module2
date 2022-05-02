package org._2binstitute.discos.online.repository;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class MySQLDataSource{

	private final static String USER_NAME = "discos_owner";
	private final static String USER_PASSWORD = "discos_password";
	private final static String URL_CONNECTION = "jdbc:mysql://localhost:3306/";
	private final static String DATABASE_NAME = "discos_online";
	
	private DataSource datasource;
	
	public MySQLDataSource() {
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUsername(USER_NAME);
		basicDataSource.setPassword(USER_PASSWORD);
		basicDataSource.setUrl(URL_CONNECTION + DATABASE_NAME);
		datasource = basicDataSource;
	}
	
	public DataSource getDataSource() {
		return datasource;
	}
	
}

