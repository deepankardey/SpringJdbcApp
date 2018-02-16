package com.imcs.jdbc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

public class ReadDBPropertiesFile {
	static private Properties properties = null;
	static private File file = null;
	static private FileInputStream fileInput = null;
	
	static {
		try {
			file = new File("db.properties");
			fileInput = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key) {
		Enumeration<?> enuKeys = properties.keys();
		while (enuKeys.hasMoreElements()) {
			if(enuKeys.nextElement().equals(key))
				return properties.getProperty(key);
		}
		return null;
	}
}
