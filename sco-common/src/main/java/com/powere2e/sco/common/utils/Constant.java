package com.powere2e.sco.common.utils;

import java.math.BigDecimal;

/**
 * 公共常量
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2014年9月13日
 */
public class Constant {

	/**
	 * 默认时间格式(yyyy-MM-dd)
	 */
	public static final String DATA_INTEFACE_DATEFORMATE = "yyyy-MM-dd";

	/**
	 * 时间格式(yyyy-MM)
	 */
	public static final String DATA_INTEFACE_DATEFORMATE_MONTH = "yyyy-MM";

	/**
	 * 时间格式(yyyyMMddHHmmss)
	 */
	public static final String DATA_TIME_DATEFORMATE = "yyyyMMddHHmmss";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATA_TIME_DATEFORMATE_CHI = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 默认保留精度
	 */
	public static final int DEFAULT_PRECISION = 5;

	/**
	 * 默认取值规则(四舍五入)
	 */
	public static final int DEFAULT_ROUNDINGMODE = BigDecimal.ROUND_HALF_UP;

	/**
	 * excel中金额、数量默认保留位数(2位)
	 */
	public static final String DEFAULT_AMT_FORMAT = "0.00";

	/**
	 * 默认编码1(GBK)
	 */
	public static final String DEFAULT_ENCODED_1 = "GBK";

	/**
	 * 默认编码2(ISO8859-1)
	 */
	public static final String DEFAULT_ENCODED_2 = "ISO8859-1";

	/**
	 * 默认编码3(UTF-8)
	 */
	public static final String DEFAULT_ENCODED_3 = "UTF-8";

	/**
	 * 批量导入时每次的数量(默认100)
	 */
	public static final int BATCH_SIZE = 100;

	/**
	 * 生成报表文件默认后缀名
	 */
	public static final String DEFAULT_REPORT_SUFFIX = ".html";

	/**
	 * 配置文件名称
	 */
	public static final String CONFIG_FILE_NAME = "path.properties";

	/**
	 * 报表文件路径配置的键名
	 */
	public static final String REPORT_FILE_PATH = "report.file.path";

	/**
	 * 上传文件保存路径配置的键名
	 */
	public static final String UPLOAD_FILE_SAVE_PATH = "upload.file.save.path";

	/**
	 * 核算表扫描版上传路径的键名
	 */
	public static final String ACCOUNTING_FILE_UPLOAD_PATH = "accounting.file.upload.path";

	/**
	 * 投料表扫描版上传路径的键名
	 */
	public static final String INGREDIENT_FILE_UPLOAD_PATH = "ingredient.file.upload.path";

	/**
	 * 辅料意向品询价单意向品图片附件上传路径的键名
	 */
	public static final String ACCESSORYINTENTION_ENQUIRY_FILE_UPLOAD_PATH = "accessoryintention.enquiry.file.upload.path";

	/**
	 * 辅料意向品电子版报价单上传路径的键名
	 */
	public static final String ACCESSORYINTENTION_QUOTEDELECTRONIC_FILE_UPLOAD_PATH = "accessoryintention.quotedelectronic.file.upload.path";

	/**
	 * 辅料意向品扫描版报价单上传路径的键名
	 */
	public static final String ACCESSORYINTENTION_QUOTEDSCAN_FILE_UPLOAD_PATH = "accessoryintention.quotedscan.file.upload.path";

	/**
	 * 辅料意向品打样信息样品图片上传路径的键名
	 */
	public static final String ACCESSORYINTENTION_PROOFINGPICTURE_FILE_UPLOAD_PATH = "accessoryintention.proofingpicture.file.upload.path";

	/**
	 * 供应商附件上传路径的键名
	 */
	public static final String SUPPLIER_ATTACHMENT_FILE_UPLOAD_PATH = "supplier.attachment.file.upload.path";

	/**
	 * 供应商证件上传路径的键名
	 */
	public static final String SUPPLIER_CERTIFICATE_FILE_UPLOAD_PATH = "supplier.certificate.file.upload.path";

	/**
	 * 辅料OA申请分析报表本地报表上传路径的键名
	 */
	public static final String OA_REPORT_NATIVE_FILE_PATH = "oa.report.native.file.path";

	/**
	 * 辅料OA申请分析报表申购单上传路径的键名
	 */
	public static final String OA_REPORT_SUBSCRIBE_FILE_PATH = "oa.report.subscribe.file.path";

	/**
	 * 辅料OA申请大货信息上传路径的键名
	 */
	public static final String OA_DH_FILE_PATH = "oa.dh.file.path";
	/**
	 * 商品快速调价OA申请文件上传路径
	 */
	public static final String OA_FASTADJUSTPRICE_FILE_PATH = "oa.fastadjustprice.file.path";
	/**
	 * 商品快速引进OA申请文件上传路径
	 */
	public static final String OA_FASTIMPORT_FILE_PATH = "oa.fastimport.file.path";

	/**
	 * 共享目录路径
	 */
	public static final String NFS_FILE_PATH = "nfs.file.path";

}
