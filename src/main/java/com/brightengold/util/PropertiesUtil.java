package com.brightengold.util;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getPropertyValue(String path, String key) {
		FileInputStream fis = null;
		try {
			Properties prop = new Properties();  
			fis = new FileInputStream(path);
			prop.load(fis);
			return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null){
					fis.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
