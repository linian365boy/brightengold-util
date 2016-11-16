package com.brightengold.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Tools {
	//创建日期格式
	private static SimpleDateFormat df = new SimpleDateFormat(ConstantVariable.DFSTR);
	private static SimpleDateFormat dft = new SimpleDateFormat(ConstantVariable.DFTSTR);
	
	private static final Logger logger = LoggerFactory.getLogger(Tools.class);
		
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
	
    /**
	 * getJsonStrFromPath:从绝对路径 解析json文件，获取json串
	 * @author tanfan 
	 * @param path
	 * @return 
	 * @since JDK 1.7
	 */
    public static String getJsonStrFromPath(String path) {
        BufferedReader read = null;
        String line = null;
        StringBuffer sb = new StringBuffer();
        try {
        	read = new BufferedReader(new FileReader(new File(path)));
        	line=read.readLine();
        	while(line!=null){
        		sb.append(line);
        		line = read.readLine();
        	}
        } catch (IOException e) {
        	logger.error("getJsonStrFromPath error",e);
        }finally{
        	if(read!=null){
        		try {
					read.close();
				} catch (IOException e) {
					logger.error("getJsonStrFromPath read close error",e);
				}
        	}
        }
        return sb.toString();
    }
    
    
    public static boolean saveOrUpdateWebConfig(String path, String jsonStr) {
    	boolean flag = false;
    	BufferedWriter writer = null;
    	try{
    		writer = new BufferedWriter(new FileWriter(new File(Tools.class.getClassLoader().getResource(path).toURI()), false));
    		writer.write(jsonStr);
    		writer.flush();
    		flag = true;
    	}catch(IOException | URISyntaxException e){
    		logger.error("saveOrUpdateWebConfig error",e);
    	}finally{
    		if(writer!=null){
    			try {
					writer.close();
				} catch (IOException e) {
					logger.error("saveOrUpdateWebConfig writer close error",e);
				}
    		}
    	}
		return flag;
	}
    
    /**
     * 验证邮箱
     * @param email
     * @return
     */
    public static boolean checkEmail(String email){
        boolean flag = false;
        try{
                String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
                Pattern regex = Pattern.compile(check);
                Matcher matcher = regex.matcher(email);
                flag = matcher.matches();
            }catch(Exception e){
                flag = false;
            }
        return flag;
    }
    
    public static void main(String[] args) {
    	String jsonStr = Tools.getJsonStrFromPath("webConfig.json");
    	System.out.println(jsonStr);
	}
    
}
