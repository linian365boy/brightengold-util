package com.brightengold.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;

/** 静态页面引擎 */
public class HTMLGenerator extends BaseLog{
	HttpClient httpClient = null;
	GetMethod method = null;
	StringBuffer sb = null;
	InputStream in = null;
	BufferedReader br = null;
	String line = null;
	String page = null;
	String webappname = null;
	BufferedWriter bw = null;
	
	public HTMLGenerator(String webappname){
		this.webappname = webappname;
	}
	
	public boolean createHtmlPage(String url,String fileName,String username){
		boolean status = false;
		int statusCode = 0;
		String loginUrl = webappname+"/admin/j_spring_security_check";
		try{
			//创建一个HttpClient实例充当模拟浏览器
			httpClient = new HttpClient();
			PostMethod postMethod = new PostMethod(loginUrl);
			//设置登陆时要求的信息，一般就用户名和密码，验证码自己处理了
			if(username!=null){
				NameValuePair[] data = {
		                new NameValuePair("j_username", username),
		                new NameValuePair("j_password", CommonConstants.p)
		        };
		        postMethod.setRequestBody(data);
			}
	        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
	        httpClient.executeMethod(postMethod);
	        //获得登陆后的 Cookie
            //Cookie[] cookies=httpClient.getState().getCookies();
            //String tmpcookies= "";
	        //for(Cookie c:cookies){
            //    tmpcookies += c.toString()+";";
            //}
            
			//get方法实例
			method = new GetMethod(url);
			//method.setRequestHeader("cookie",tmpcookies);
			
			statusCode = httpClient.executeMethod(method);
			httpClient.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"UTF-8");
			//设置默认的恢复策略，在发生异常时候将自动重试3次
			method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
			//设置Get方法提交参数时使用的字符集,以支持中文参数的正常传递
			method.addRequestHeader("Content-type","text/html;charset=UTF-8");
			
			//执行Get方法并取得返回状态码，200表示正常，其它代码为异常
			if(statusCode!=200){
				logger.fatal("静态页面引擎在解析"+url+"产生静态页面"+fileName+"时出错!");
			}else{
				sb = new StringBuffer();
				in = method.getResponseBodyAsStream();
				br = new BufferedReader(new InputStreamReader(in,Charset.forName("UTF-8")));
				while((line=br.readLine())!=null){
					sb.append(line+"\n");
				}
				if(br!=null) br.close();
				if(in!=null) in.close();
				
				page = sb.toString();
				//将页面中的相对路径替换成绝对路径，以确保页面资源正常访问
				page = formatPage(page);
				//将解析结果写入指定的静态HTML文件中，实现静态HTML生成
				writeHtml(page,fileName);
				status = true;
			}
			
		}catch(Exception e){
			logger.fatal("静态页面引擎在解析"+url+"产生静态页面"+fileName+"时出错:"+e.getMessage());	
		}finally{
			//释放http连接
			if(method!=null){
				method.releaseConnection();
			}
		}
		return status;
	}
	
	public String formatPage(String page){
		page = page.replaceAll("\\.\\./\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./\\.\\./", webappname+"/");
		page = page.replaceAll("\\.\\./",webappname+"/");
		return page;
	}
	
	public void writeHtml(String page,String fileName) throws IOException{
		bw = new BufferedWriter(new FileWriter(fileName));
		bw.write(page);
		if(bw!=null) bw.close();
	}
	
	//测试方法
	public static void main(String[] args){
		HTMLGenerator h = new HTMLGenerator("");
		h.createHtmlPage("http://www.hao123.com","c:/a.html",null);
		System.out.println("导出成功！");
	}
	
}

