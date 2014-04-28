package com.brightengold.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class PropertiesUtil {

	public static String getPropertyValue(String path, String key) {
		InputStream in = null;
		try {
			Properties prop = new Properties();  
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			    prop.load(in);   
			    return prop.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static void updatePropertyValue(String path, String key, String value){
		OutputStream fos = null;
		InputStream in = null;
		try {
			Properties prop = new Properties(); 
			in = PropertiesUtil.class.getClassLoader().getResourceAsStream(path);
			prop.load(in); 
			in.close();
			fos = new FileOutputStream(new File(path));
			prop.setProperty(key, value);
			prop.store(fos, "update '"+key+"' value");
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try{
				if(fos!=null){
					fos.close();
				}
				if(in!=null){
					in.close();
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

}
