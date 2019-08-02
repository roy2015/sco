package com.powere2e.sco.common.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.powere2e.frame.web.template.StringTemplateLoader;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * freemarker模板转换工具类
 * 
 * @since: 2012-4-17
 * @version 1.0
 */
public class FreeMarkerUtil {
	private static Configuration cfg;// 初始化FreeMarker配置

	private static Configuration getConfiguration() {
		if (cfg == null) {
			cfg = new Configuration();
			// 设置FreeMarker的模版文件位置
			try {
				cfg.setClassicCompatible(true);// 允许属性为空
				TemplateLoader templateLoader = new FileTemplateLoader(
						new File(PathUtils.getReportFtlPath(null)));
				cfg.setTemplateLoader(templateLoader);
				// cfg.setDirectoryForTemplateLoading(new
				// File(ConfigPath.APP_PATH.concat(ConfigPath.FTL_TEMPLATE_PATH)));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return cfg;
	}

	/**
	 * 根据模板文件和参数创建目标文件
	 * 
	 * @param ftlFileName
	 *            模板文件名，不带路径
	 * @param saveFilePath
	 *            目标文件，带完整路径
	 * @param paraMap
	 *            参数
	 * @return 存储路径
	 */
	public static String buildFile(String ftlFileName, String saveFilePath,
			Map<String, Object> paraMap) {
		File saveFile = new File(saveFilePath);
		File saveDir = saveFile.getParentFile();
		if (!saveDir.exists()) {
			saveDir.mkdirs();
		}
		Writer out = null;
		try {
			out = new OutputStreamWriter(new FileOutputStream(saveFilePath),
					Constant.DEFAULT_ENCODED_3);
			Template template = getConfiguration().getTemplate(ftlFileName);
			template.process(paraMap, out);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			if (out != null)
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return saveFilePath;
	}

	/**
	 * 解析字符串模板
	 * 
	 * @param ftlString
	 *            字符串
	 * @param params
	 *            参数
	 * @return
	 * @throws Exception
	 */
	public static String parseFtlString(String ftlString,
			Map<String, Object> params) throws Exception {
		Configuration cfg = new Configuration();
		cfg.setTemplateLoader(new StringTemplateLoader(ftlString));
		cfg.setDefaultEncoding("UTF-8");
		Template template = cfg.getTemplate("");
		StringWriter writer = new StringWriter();
		template.process(params, writer);
		String str = writer.toString();
		writer.close();
		return str;
	}

	/**
	 * 生成Html文件(默认路径)
	 * 
	 * @param ftlDirName
	 *            模板上层目录及名称
	 * @param fileName
	 *            生成文件完整路径
	 * @param map
	 *            模板所需参数
	 * @return 生成html路径
	 */
	public static String generateHtml(String ftlDirName, String fileName,
			Map<String, Object> map) {
		String saveDir = PathUtils.getSaveReportPath(fileName);
		buildFile(ftlDirName, saveDir, map);
		return saveDir;
	}

	/**
	 * 生成Html文件(自定义路径)
	 * 
	 * @param ftlDir
	 *            模板上层目录及名称
	 * @param tarPath
	 *            生成文件路径
	 * @param fileName
	 *            生成文件完整路径
	 * @param map
	 *            模板所需参数
	 * @return 生成html路径
	 */
	public static String generateHtml(String ftlDirName, String tarPath,
			String fileName, Map<String, Object> map) {
		String saveDir = tarPath.concat("/").concat(fileName)
				.concat(Constant.DEFAULT_REPORT_SUFFIX);// 完整路径
		buildFile(ftlDirName, saveDir, map);
		return saveDir;
	}

}