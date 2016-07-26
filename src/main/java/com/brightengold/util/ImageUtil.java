package com.brightengold.util;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;


public class ImageUtil {
	public static String validateImage(InputStream is, long size, int minWidth,
			int maxWidth, int minHeight, int maxHeight) throws IOException {
		// 文件大小
		if (is.available() > size) {
			return "3";
		}
		// 文件尺寸
		Image image = ImageIO.read(is);
		int imgWidth = image.getWidth(null);
		int imgHeight = image.getHeight(null);
		if (imgWidth < minWidth || imgWidth > maxWidth || imgHeight < minHeight
				|| imgHeight > maxHeight) {
			return "4";
		}
		return null;
	}
	
	/**
	 * 验证图片大小，长宽像素
	 * @param is
	 * @param size
	 * @param minWidth
	 * @param maxWidth
	 * @return
	 * @throws IOException
	 */
	public static String validateDescImage(InputStream is, long size,
			int minWidth, int maxWidth) throws IOException {
		// 文件大小
		if (is.available() > size) {
			return "3";
		}
		// 文件尺寸
		Image image = ImageIO.read(is);
		int imgWidth = image.getWidth(null);
		int imgHeight = image.getHeight(null);
		if (imgWidth < minWidth || imgWidth > maxWidth || imgHeight < 10) {
			return "4";
		}
		return null;
	}
}
