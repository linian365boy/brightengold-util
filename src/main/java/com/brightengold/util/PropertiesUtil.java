package com.brightengold.util;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getPropertyValue(String string, String key) {
		try {
			Properties prop = new Properties();  
			InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(string);
			    prop.load(in);   
			    return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
