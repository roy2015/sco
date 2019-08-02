package com.powere2e.sco.common.utils;

import java.io.File;

/**
 * 对文件操作方法
 * 
 * @author joyce.li
 * @since 2015年8月28日 上午10:38:47
 * @version 1.0
 */
public class FileUtils {

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
	public static void deleteFile(String fileStr) {
		File file = new File(fileStr);
		if (file.exists()) {
			file.delete();
			LoggerUtil.logger.error("FileUtils工具类1删除文件["+ file.getPath() +"]");
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
				LoggerUtil.logger.error("FileUtils工具类2删除文件["+ file.getPath() +"]");
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

}