package com.setup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
	
	public static Properties readProperties() {
		
		String fileName = "src\\test\\resources\\PropertiesFiles\\config.properties";
		Properties prop = null;
		
		try {
			FileInputStream fis = new FileInputStream(fileName);
			prop = new Properties();
			prop.load(fis);
		}
		catch (FileNotFoundException e) {
			System.out.println("File Name is not correct, Please check the file name");
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
		
	}

}