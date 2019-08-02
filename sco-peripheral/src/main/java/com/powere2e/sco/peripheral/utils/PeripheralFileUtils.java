package com.powere2e.sco.peripheral.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import com.powere2e.sco.common.utils.LoggerUtil;

/**
 * 对文件操作方法
 * 
 * @author joyce.li
 * @since 2015年8月28日 上午10:38:47
 * @version 1.0
 */
public class PeripheralFileUtils {
	public static final String SP = File.separator;
	private static Properties prop = null;
	public static Logger logger = Logger.getLogger("cn.peripheral");

	public static Properties getInstance() {
		if (prop == null) {
			prop = new Properties();
			try {
				prop = PropertiesLoaderUtils.loadAllProperties("peripheral.properties");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return prop;
	}

	/**
	 * 对原始目录的文件备份
	 * 
	 * @param sourFile
	 * @return
	 */
	public static String backupFile(File sourceFile, String fileRootPath, String interfaceType) {
		// 获取当前时间
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");// 可以方便地修改日期格式
		String date = dateFormat.format(now);
		String backupPath = fileRootPath.concat(SP).concat(PeripheralConfigFile.BACKUP_PACKAGE).concat(SP).concat(interfaceType).concat(SP).concat(date);// 创建备份目录
		// 判断此文件是否存在，若不存在就创建
		PeripheralFileUtils.createDirByFile(backupPath);
		String path = null;
		try {
			if (sourceFile.exists()) {
				path = backupPath.concat(SP).concat(sourceFile.getName());
				FileUtils.copyFile(sourceFile, new File(path));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

		return path;
	}

	/**
	 * 为一个文件创建目录
	 * 
	 * @param fileDir
	 * 
	 */
	public static void createDirByFile(String fileDir) {
		File f = new File(fileDir);
		if (!(f.exists())) {
			f.mkdirs();
		}
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 *            要删除的文件
	 */
	public static void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
				LoggerUtil.logger.error("PeripheralFileUtils.deleteFile删除文件["+ file.getPath() +"]");
			} else if (file.isDirectory()) {
				File[] files = file.listFiles();
				if (files != null) {
					for (File f : files) {
						deleteFile(f);
					}
				}
			}
		}
	}

	/**
	 * 根据相应的key获取对应的value
	 * 
	 * @param keyProperty
	 * @return
	 */
	public static String getProperty(String keyProperty) {
		prop = getInstance();
		String value = prop.getProperty(keyProperty);
		return value;
	}

}