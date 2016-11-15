package com.brightengold.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	private final static Logger logger = LoggerFactory.getLogger(FreemarkerUtil.class);
	public static Template getTemplate(String name) {
		try {
			Configuration cfg = new Configuration();
			cfg.setClassForTemplateLoading(FreemarkerUtil.class, "/template");
			Template temp = cfg.getTemplate(name);
			return temp;
		} catch (IOException e) {
			logger.error("获取模板报错",e);
		}
		return null;
	}

	public static void print(String name, Map<String, Object> root) {
		try {
			Template temp = getTemplate(name);
			temp.process(root, new PrintWriter(System.out));
		} catch (Exception e) {
			logger.error("print 打印报错",e);
		}
	}

	public static void fprint(String name, Map<String, Object> root,
			String outFile, String fileName) {
		FileWriter out = null;
		try {
			File file = new File(outFile);
			if (!file.exists()) {
				file.mkdirs();
			}
			out = new FileWriter(file + File.separator + fileName);
			Template temp = getTemplate(name);
			temp.setEncoding("UTF-8");
			temp.process(root, out);
		} catch (IOException e) {
			logger.error("fprint 打印报错",e);
		} catch (TemplateException e) {
			logger.error("fprint 打印报错",e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					logger.error("fprint 打印，流关闭报错",e);
				}
		}
	}
}
