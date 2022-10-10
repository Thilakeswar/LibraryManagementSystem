package com.knightwatch.Library_Management_System.repository.mysql.manual;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

public class MySQLDataSource
{

	public static Logger LOGGER = LoggerFactory.getLogger(MySQLDataSource.class);
	public static String connectionUrl = null;
	public static String driver = null;
	public static String userName = null;
	public static String password = null;

	public static Properties properties = new Properties();

	static
	{
		InputStream inputStream = null;
		try
		{
			File file = ResourceUtils.getFile("classpath:application.properties");
			inputStream = new FileInputStream(file);
			properties.load(inputStream);
			String activeProfile = (String) properties.get("spring.profiles.active");
			inputStream.close();

			file = ResourceUtils.getFile("classpath:application-" + activeProfile + ".properties");
			inputStream = new FileInputStream(file);

			properties.load(inputStream);

			userName = (String) properties.get("spring.datasource.username");
			password = (String) properties.get("spring.datasource.password");
			connectionUrl = (String) properties.get("spring.datasource.url");
			driver = (String) properties.get("spring.datasource.driver-class-name");
		}
		catch(Exception e)
		{
			LOGGER.trace("Exception occurred in reading properties due to ", e);
		}
		finally
		{
			if(inputStream != null)
			{
				try
				{
					inputStream.close();
				}
				catch(IOException e)
				{
					LOGGER.trace("Exception occurred in closing inputStream due to ", e);
				}
			}
		}

	}

	public static Connection getNewDBConnection() throws ClassNotFoundException, SQLException
	{
		try
		{
			Class.forName(driver);
			Connection con = DriverManager.getConnection(connectionUrl, userName, password);
			return con;
		}
		catch(Exception e)
		{
			LOGGER.trace("Exception occurred in getting connection due to ", e);
			throw e;
		}
	}
}
