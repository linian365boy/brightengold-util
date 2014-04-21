package com.brightengold.util;

public class CommonConstants {

	public static String p = getProperty("p");
	
	public static String getProperty(String key){
		return PropertiesUtil.getPropertyValue("/common.properties", key);
	}
	
}
