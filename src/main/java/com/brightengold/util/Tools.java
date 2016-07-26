package com.brightengold.util;

import java.io.File;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
	//创建日期格式
	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private static SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
	/** 取得随机主文件名 */
	public synchronized static String getRndFilename(){
		return String.valueOf(System.currentTimeMillis());
	}

	public static boolean delFile(String path) {
		boolean flag = false;
		File file = new File(path);
		if(!file.exists()){
			return flag;
		}
		if(file.isDirectory()){
			return flag;
		}
		if(file.delete()){
			flag = true;
			return flag;
		}
		return flag;
	}
	
	public static String getExtname(String fileName){
		return fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
	}
	
	public boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if(!isNum.matches() ){
			return false;
		}
		return true;
	}
	
	 /** 
     * 加密解密算法 执行一次加密，两次解密 
     */   
    public static String convertMD5(String inStr){  
        char[] a = inStr.toCharArray();  
        for (int i = 0; i < a.length; i++){  
            a[i] = (char) (a[i] ^ 't');  
        }  
        String s = new String(a);  
        return s;  
    }
    
    
    /** 按yyyy-MM-dd格式格式化日期 */
	public static String formatDate(Date date,boolean flag){   
		if (date==null){
			return "";
		}else{
			if(flag==true){
				return dft.format(date);
			}else{
				return df.format(date);
			}
		}
    }
    
    public static void main(String[] args) {
    	//String s = "cfcd208495d565ef66e7dff9f98764da";
		//System.out.println(URLEncoder.encode(convertMD5(s)));
		//System.out.println(convertMD5(URLEncoder.encode(convertMD5(s))));
		//System.out.println(URLDecoder.decode(convertMD5(s)));
    	String s = "1";
    	System.out.println(convertMD5(s));
	}
    
}
