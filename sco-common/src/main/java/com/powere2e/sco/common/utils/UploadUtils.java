package com.powere2e.sco.common.utils;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.frame.web.exception.EscmException;

/**
 * @author Evan.Zhao
 * @version 1.0
 * @since 2013年9月29日
 */
public class UploadUtils extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6246412285708954892L;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {

	}

	// 日期格式器，用于生成文件夹
	public static final String yyyyMMdd = "yyyyMMdd";

	private File[] uploads;// 客户端上传的文件对象列表
	private String[] uploadFileNames;// 文件名
	private String[] uploadContentTypes;// 文件的MIME类型

	public File[] getUploads() {
		return uploads;
	}
	
	/**
	 * 上传文件
	 * 
	 * @param allowedType
	 * @return
	 */
	public List<File> doUpload(String... allowedType) {
		if (uploads == null) {
			throw new EscmException("未获取到页面选则的文件");
		}
		if (allowedType != null && allowedType.length > 0) {
			List<String> allowedTypeList = Arrays.asList(allowedType);
			for (int i = 0; i < uploads.length; i++) {
				String[] strs = uploadFileNames[i].split("\\.");
				if (!allowedTypeList.contains(strs[strs.length - 1].toUpperCase())) {
					throw new EscmException("文件类型不合法", allowedType);
				}
			}
		}
		return this.copyFileToDest(this.getSavePath());
	}

	/**
	 * 上传文件自定义保存路径
	 * 
	 * @param saveDir
	 *            文件上传后保存路径
	 * @param allowedType
	 *            允许上传文件类型
	 * @return
	 */
	public List<File> doUploadBySaveDir(String saveDir, String... allowedType) {
		if (StringUtils.isBlank(saveDir)) {
			throw new EscmException("文件上传后的保存路径不能为空");
		}
		if (uploads == null) {
			throw new EscmException("未获取到页面选则的文件");
		}
		if (allowedType != null && allowedType.length > 0) {
			List<String> allowedTypeList = Arrays.asList(allowedType);
			for (int i = 0; i < uploads.length; i++) {
				String[] strs = uploadFileNames[i].split("\\.");
				if (!allowedTypeList.contains(strs[strs.length - 1].toUpperCase())) {
					throw new EscmException("文件类型不合法", allowedType);
				}
			}
		}
		if (!saveDir.endsWith("/") && !saveDir.endsWith("\\")) saveDir += "/";
		return this.copyFileToDest(new File(saveDir));
	}

	/**
	 * 上传方法，将上传的临时文件复制到指定的目录中
	 * 
	 * @param baseDir
	 *            指定存放文件目录
	 * @return 复制后的文件集合
	 */
	private List<File> copyFileToDest(File baseDir) {
		return copyFileToDest(baseDir, 20);
	}
	
	/**
	 * 上传文件自定义保存路径
	 * 
	 * @param saveDir
	 *            文件上传后保存路径
	 * @param allowedType
	 *            允许上传文件类型
	 * @param maxSize
	 *            上传的文件的最大值(单位M)
	 * @return
	 */
	public List<File> doUploadBySaveDir(String saveDir, int maxSize, String... allowedType) {
		if (StringUtils.isBlank(saveDir)) {
			throw new EscmException("文件上传后的保存路径不能为空");
		}
		if (uploads == null) {
			throw new EscmException("未获取到页面选则的文件");
		}
		if (allowedType != null && allowedType.length > 0) {
			List<String> allowedTypeList = Arrays.asList(allowedType);
			for (int i = 0; i < uploads.length; i++) {
				String[] strs = uploadFileNames[i].split("\\.");
				if (!allowedTypeList.contains(strs[strs.length - 1].toUpperCase())) {
					throw new EscmException("文件类型不合法", allowedType);
				}
			}
		}
		if (!saveDir.endsWith("/") && !saveDir.endsWith("\\")) saveDir += "/";
		return this.copyFileToDest(new File(saveDir), maxSize);
	}


	/**
	 * 上传方法，将上传的临时文件复制到指定的目录中
	 * 
	 * @param baseDir
	 *            指定存放文件目录
	 * @param maxSize
	 *            上传的文件的最大值(单位M)
	 * @return 复制后的文件集合
	 */
	private List<File> copyFileToDest(File baseDir, int maxSize) {
		// 判断文件的存在性
		if (uploads == null || uploads.length <= 0) {
			LoggerUtil.logger.error("上传文件时异常:未获取到需上传的文件");
			throw new EscmException("未获取到需上传的文件");
		}

		// 判断目录的存在性
		if (!baseDir.exists()) {
			baseDir.mkdirs();
		}

		List<File> files = new ArrayList<File>();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < uploads.length; i++) {
			if (uploads[i].length() > maxSize * 1024 * 1024) {
				sb.append("上传的文件[".concat(uploadFileNames[i]).concat("]超过允许上传文件最大值" + maxSize + "M<br>"));
				continue;
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			DecimalFormat df = new DecimalFormat("00");
			String str = df.format(this.asInteger("fileNo"));
			String newFileName = StringUtils.substringBeforeLast(uploadFileNames[i], ".") + "_" + sdf.format(new Date()) + str + "." + StringUtils.substringAfterLast(uploadFileNames[i], ".");
			try {
				File dest = new File(baseDir, newFileName);
				LoggerUtil.logger.error("上传文件日志[正常日志]:[保存路径：" + dest.getPath() + "]");
				if (StrUtils.getLength(newFileName) > 100 ||StrUtils.getLength(dest.getPath()) > 300) {
					sb.append("上传的文件[".concat(uploadFileNames[i]).concat("]文件名称过长,请适当修改其长度<br>")); //限制文件名称及保存路径长度
					continue;
				}
				FileUtils.copyFile(uploads[i], dest);
				files.add(dest);
				LoggerUtil.logger.error("文件日志[正常日志]:[文件复制成功?：" + dest.exists() + "]");
			} catch (IOException e) {
				LoggerUtil.logger.error("上传文件时异常:<br>" + StrUtils.getStackMsgToStr(e));
				throw new EscmException("IO错误", new String[] { "复制临时上传文件失败！" });
			}
		}
		if (sb.length() > 0) {
			LoggerUtil.logger.error("上传文件时异常:<br>" + sb.toString());
			throw new EscmException(sb.toString());
		}
		return files;
	}
	
	/**
	 * 上传方法，将上传的临时文件复制到默认的目录中
	 * 
	 * @return 复制后的文件集合
	 */
	private File getSavePath() {
		// 构建上传文件根目录
		String savePath = ConfigPath.getUploadFilePath().concat(
				new SimpleDateFormat(yyyyMMdd).format(new Date())).concat("/");
		return new File(savePath);
	}
	
	/**
	 * 根据文件地址，删除文件
	 * 
	 * @param filePath
	 *            文件地址
	 * @return
	 */
	public void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
			LoggerUtil.logger.error("UploadUtils.java工具类删除文件["+ file.getPath() +"]");
		}
	}

	public File[] getUpload() {
		return uploads;
	}

	public void setUpload(File[] upload) {
		this.uploads = upload;
	}

	public String[] getUploadFileName() {
		return uploadFileNames;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileNames = uploadFileName;
	}

	public String[] getUploadContentType() {
		return uploadContentTypes;
	}

	public void setUploadContentType(String[] contentTypes) {
		this.uploadContentTypes = contentTypes;
	}
}
