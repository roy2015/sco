package com.powere2e.sco.peripheral.utils;

/**
 * 配置文件信息
 * 
 * @author joyce.li
 * @since 2015年8月28日 上午10:38:47
 * @version 1.0
 */
public class PeripheralConfigFile {
	// 文件根目录
	public static final String FILE_ROOT_PATH = "peripheral.root.file.path";
	// 默认文件根目录
	public static final String DEFAULT_FILE_ROOT_PATH = "D:/test1";
	// 文件编码
	public static final String FILE_CHARSET_CODE = "peripheral.file.charset.code";
	// 默认文件编码
	public static final String DEFAULT_FILE_CHARSET_CODE = "UTF-8";
	// 文件每多少行处理一次
	public static final String PROCESS_LINE_COUNT = "peripheral.process.linecount";
	// 默认处理行数
	public static final int DEFAULT_PROCESS_LINE_COUNT = 100;
	// BW路径名
	public static String BW_PACKAGE = "BW";
	// SAP路径名
	public static String SAP_PACKAGE = "SAP";
	// backup路径名
	public static String BACKUP_PACKAGE = "backup";
	// 数据分隔符
	public static final String LINE_SEPARATOR = "|";
	// BW模块名
	public static final String MERCHANDISE_SELL_DIRECT_DAY = "MERCHANDISE_SELL_DIRECT_DAY";// 日直营
	public static final String MERCHANDISE_SELL_JOIN_DAY = "MERCHANDISE_SELL_JOIN_DAY";// 日加盟
	public static final String MERCHANDISE_SELL_D_J_DAY = "MERCHANDISE_SELL_D_J_DAY";// 日直营+日加盟
	public static final String MERCHANDISE_SELL_DIRECT_MONTH = "MERCHANDISE_SELL_DIRECT_MONTH";// 月直营
	public static final String MERCHANDISE_SELL_JOIN_MONTH = "MERCHANDISE_SELL_JOIN_MONTH";// 月加盟
	public static final String MERCHANDISE_SELL_D_J_MONTH = "MERCHANDISE_SELL_D_J_MONTH";// 月直营+月加盟
	public static final String MERCHANDISE_ORDER = "MERCHANDISE_ORDER";// 商品订货单信息
	public static final String MERCHANDISE_RECEIPT = "MERCHANDISE_RECEIPT";// 商品收货单信息
	public static final String MERCHANDISE_JH_PERMISSION = "MERCHANDISE_JH_PERMISSION";// 商品进货权限
	// SAP模块模块名
	public static final String MERCHANDISE = "MERCHANDISE";// 商品
	public static final String MERCHANDISE_HISTORY = "MERCHANDISE_HISTORY";// 商品历史物料号
	public static final String MERCHANDISE_SUPPLIER = "SUPPLIER";// 供应商
	public static final String SUPPLIER_HISTORY = "SUPPLIER_HISTORY";// 供应商历史物料号
	public static final String MERCHANDISE_CENTRE_TYPE = "MERCHANDISE_CENTRE_TYPE";// 商品中分类
	public static final String MERCHANDISE_SMALL_TYPE = "MERCHANDISE_SMALL_TYPE";// 商品小分类
	public static final String MERCHANDISE_DETAIL_TYPE = "MERCHANDISE_DETAIL_TYPE";// 商品明细类
	public static final String MERCHANDISE_PURCHASE_PRICE = "MERCHANDISE_PURCHASE_PRICE";// 商品进货价格信息
	public static final String MERCHANDISE_PURCHASE_PRICE_GROUP = "MERCHANDISE_PURCHASE_PRICE_GROUP";// 商品进货价格信息-去重复
	public static final String MERCHANDISE_PROMOTION_PURCHASE = "MERCHANDISE_PROMOTION_PURCHASE";// 商品促销进货价
	public static final String MERCHANDISE_PRICE = "MERCHANDISE_PRICE";// 商品销售价格信息
	public static final String MERCHANDISE_PRICE_GROUP = "MERCHANDISE_PRICE_GROUP";// 商品销售价格信息-去重复
	public static final String MERCHANDISE_PROMOTION_SELL = "MERCHANDISE_PROMOTION_SELL";// 商品促销销售价
	public static final String REGION = "REGION";// 区域信息
	public static final String WAREHOUSE = "WAREHOUSE";// 仓库
	public static final String WAREHOUSE_SITE = "WAREHOUSE_SITE";// 仓位
	public static final String WORKINGDAY = "WORKINGDAY";// 工作日
}