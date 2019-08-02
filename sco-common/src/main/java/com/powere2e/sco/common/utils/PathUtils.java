package com.powere2e.sco.common.utils;

import java.util.Calendar;
import java.util.Properties;

import com.powere2e.frame.commons.config.ConfigPath;

/**
 * 获取路径工具类
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月1日
 * @version 1.0
 */
public class PathUtils {


	/**
	 * 我的报表保存路径
	 */
	public String REPORT_FILE_PATH;

	/**
	 * 核算表扫描版上传路径
	 */
	public String ACCOUNTING_FILE_UPLOAD_PATH;

	/**
	 * 投料表扫描版上传路径
	 */
	public String INGREDIENT_FILE_UPLOAD_PATH;

	/**
	 * 辅料意向品询价单意向品图片附件上传路径
	 */
	public String ACCESSORYINTENTION_ENQUIRY_FILE_UPLOAD_PATH;

	/**
	 * 辅料意向品电子版报价单上传路径
	 */
	public String ACCESSORYINTENTION_QUOTEDELECTRONIC_FILE_UPLOAD_PATH;

	/**
	 * 辅料意向品扫描版报价单上传路径
	 */
	public String ACCESSORYINTENTION_QUOTEDSCAN_FILE_UPLOAD_PATH;

	/**
	 * 辅料意向品打样信息样品图片上传路径
	 */
	public String ACCESSORYINTENTION_PROOFINGPICTURE_FILE_UPLOAD_PATH;

	/**
	 * 供应商附件上传路径
	 */
	public String SUPPLIER_ATTACHMENT_FILE_UPLOAD_PATH;

	/**
	 * 供应商证件上传路径
	 */
	public String SUPPLIER_CERTIFICATE_FILE_UPLOAD_PATH;

	/**
	 * 辅料OA申请分析报表本地报表上传路径
	 */
	public String OA_REPORT_NATIVE_FILE_PATH;

	/**
	 * 辅料OA申请分析报表申购单上传路径
	 */
	public String OA_REPORT_SUBSCRIBE_FILE_PATH;

	/**
	 * 辅料OA申请大货信息上传路径
	 */
	public String OA_DH_FILE_PATH;
	
	/**
	 * 商品快速调价OA申请文件上传路径
	 */
	public String OA_FASTADJUSTPRICE_FILE_PATH;
	
	/**
	 * 商品快速引入OA申请文件上传路径
	 */
	public String OA_FASTIMPORT_FILE_PATH;
	
	/**
	 * 共享目录路径
	 */
	public String NFS_FILE_PATH;
	
	/**
	 * 读取文件上传路径配置
	 */
	public static final Properties pro;
	
	static {
		pro = PropertiesUtils.loadProperties(Constant.CONFIG_FILE_NAME);
	}

	public static String getReportFilePath() {
		return getSaveYMDPath((pro.getProperty(Constant.REPORT_FILE_PATH)));
	}

	public static String getAccountingFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.ACCOUNTING_FILE_UPLOAD_PATH));
	}

	public static String getIngredientFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.INGREDIENT_FILE_UPLOAD_PATH));
	}

	public static String getAccessoryintentionEnquiryFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.ACCESSORYINTENTION_ENQUIRY_FILE_UPLOAD_PATH));
	}

	public static String getAccessoryintentionQuotedelectronicFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.ACCESSORYINTENTION_QUOTEDELECTRONIC_FILE_UPLOAD_PATH));
	}

	public static String getAccessoryintentionQuotedscanFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.ACCESSORYINTENTION_QUOTEDSCAN_FILE_UPLOAD_PATH));
	}

	public static String getAccessoryintentionProofingpictureFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.ACCESSORYINTENTION_PROOFINGPICTURE_FILE_UPLOAD_PATH));
	}

	public static String getSupplierAttachmentFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.SUPPLIER_ATTACHMENT_FILE_UPLOAD_PATH));
	}

	public static String getSupplierCertificateFileUploadPath() {
		return getSaveYMDPath(pro.getProperty(Constant.SUPPLIER_CERTIFICATE_FILE_UPLOAD_PATH));
	}

	public static String getOaReportNativeFilePath() {
		return getSaveYMDPath(pro.getProperty(Constant.OA_REPORT_NATIVE_FILE_PATH));
	}

	public static String getOaReportSubscribeFilePath() {
		return getSaveYMDPath(pro.getProperty(Constant.OA_REPORT_SUBSCRIBE_FILE_PATH));
	}

	public static String getOaDhFilePath() {
		return getSaveYMDPath(pro.getProperty(Constant.OA_DH_FILE_PATH));
	}

	public static String getOaFastadjustpriceFilePath() {
		return getSaveYMDPath(pro.getProperty(Constant.OA_FASTADJUSTPRICE_FILE_PATH));
	}

	public static String getOaFastimportFilePath() {
		return getSaveYMDPath(pro.getProperty(Constant.OA_FASTIMPORT_FILE_PATH));
	}

	public static String getNfsFilePath() {
		return pro.getProperty(Constant.NFS_FILE_PATH);
	}

	/**
	 * 报表使用模板完整路径
	 * 
	 * @param ftlName
	 *            模板名称
	 * @return 完整路径
	 */
	public static String getReportFtlPath(String ftlName) {
		return ConfigPath.getAppFileRoot().concat("WEB-INF/template/sco/template/").concat(ftlName == null ? "" : ftlName).concat("/");
	}

	/**
	 * 生成报表存放路径
	 * 
	 * @param saveDir
	 *            文件名称及上级目录
	 * 
	 * @return 完整路径
	 */
	public static String getSaveReportPath(String saveDir) {
		return getReportFilePath().concat("/").concat(saveDir == null ? "" : saveDir.concat(Constant.DEFAULT_REPORT_SUFFIX));
	}

	/**
	 * 获得增加年月日的路径
	 * 
	 * @param saveDir
	 *            文件名称及上级目录
	 * 
	 * @return 完整路径
	 */
	private static String getSaveYMDPath(String saveDir) {
		Calendar calendar = Calendar.getInstance();
		String year = calendar.get(Calendar.YEAR) + "";
		String month = (calendar.get(Calendar.MONTH) + 1) + "";
		String day = calendar.get(Calendar.DATE) + "";
		return (saveDir == null ? "" : StrUtils.concatUploadPathString().concat(
				StrUtils.concatStr(new StringBuffer("/"), saveDir, year, month, day)
				));
	}

}
