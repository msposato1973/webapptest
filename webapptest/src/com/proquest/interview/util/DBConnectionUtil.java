package com.proquest.interview.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.proquest.interview.constant.ConstatPropertis;

public class DBConnectionUtil {

	public DBConnectionUtil() {}
	
	public static Properties loadPropertiesFile() throws Exception {

		Properties prop = new Properties();
		InputStream in = new FileInputStream(ConstatPropertis.DB_FILE);
		prop.load(in);
		in.close();
		return prop;
	}

	public static Connection getConnection() {
		
		Properties props = new Properties();
		FileInputStream fis = null;
		Connection con = null;
		try {
			
			props = loadPropertiesFile();
		
			String driverClass = props.getProperty(ConstatPropertis.DB_DRIVER);
			String url = props.getProperty(ConstatPropertis.DB_URL);
			String username = props.getProperty(ConstatPropertis.DB_USERNAME);
			String password = props.getProperty(ConstatPropertis.DB_PASSWORD);

			System.out.println("driverClass : "+driverClass);
			System.out.println("url : "+url);
			System.out.println("username : "+username);
			System.out.println("password : "+password);
			Class.forName(driverClass);

			con = DriverManager.getConnection(url, username, password);

			if (con != null) {
				System.out.println("connection created successfully using properties file");
			}else {
				System.out.println(" unable to create connection");
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
			
		return con;
	}

}
