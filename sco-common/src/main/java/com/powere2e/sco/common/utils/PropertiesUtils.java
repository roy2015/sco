package com.powere2e.sco.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;

/**
 * 配置文件读取工具
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月3日
 * @version 1.0
 */
public class PropertiesUtils {

	/**
	 * 读取resources下配置文件
	 * 
	 * @param fileName
	 *            配置文件名称
	 * @return 配置文件内容
	 */
	public static Properties loadProperties(String fileName) {
		Properties props = new Properties();
		if (StringUtils.isBlank(fileName)) return props;
		// 加载notice配置文件
		InputStream in = PropertiesUtils.class.getResourceAsStream("/"
				.concat(fileName));
		try {
			props.load(in);
		} catch (Exception e) {
			throw new RuntimeException("加载配置文件[" + fileName + "]失败！", e);
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					throw new RuntimeException("加载配置文件时流异常！", e);
				}
			}
		}
		return props;
	}

}
