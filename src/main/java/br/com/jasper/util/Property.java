package br.com.jasper.util;

import java.io.InputStream;
import java.util.Properties;

public class Property {

	private static final String FILE_NAME = "config.properties";

	public static Property INSTANCE;

	private String userName;

	private String password;

	private String host;

	private String userProfile;

	public static Property getPropertie() {
		if (INSTANCE == null) {
			INSTANCE = new Property();
		}
		return INSTANCE;
	}

	private Property() {

		try {

			InputStream is = getClass().getClassLoader().getResourceAsStream(FILE_NAME);

			Properties prop = new Properties();
			prop.load(is);

			this.userName = prop.getProperty("db.username");
			this.password = prop.getProperty("db.password");
			this.host = prop.getProperty("db.host");
			this.userProfile = System.getenv("USERPROFILE") + "\\downloads\\";

		} catch (Exception e) {
			System.exit(0);
		}

	}

	public String getUserName() {
		return userName;
	}

	public String getPassword() {
		return password;
	}

	public String getHost() {
		return host;
	}

	public String getUserProfile() {
		return userProfile;
	}

}