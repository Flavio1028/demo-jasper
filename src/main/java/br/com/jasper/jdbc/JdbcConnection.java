package br.com.jasper.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import br.com.jasper.util.Property;

public class JdbcConnection {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

	private JdbcConnection() {
	}

	public static Connection connection() {
		Connection connection = null;
		Property prop = Property.getPropertie();
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(prop.getHost(), prop.getUserName(), prop.getPassword());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		}
		return connection;
	}

}