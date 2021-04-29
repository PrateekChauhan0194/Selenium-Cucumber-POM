package com.qa.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private Properties prop;
	private String propFilePath;
	
	/**
	 * This is to load the properties from config.properties file
	 * @return It returns the Properties object which can be used to get and set the properties in config.properties
	 */
	public Properties initProp(String propFilePath) {
		prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(propFilePath);
			prop.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return prop;
	}
	
	public Properties getGlobalConfigProp() {
		propFilePath = "./src/test/resources/config/config.properties";
		return this.initProp(propFilePath);
	}

}
