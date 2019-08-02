package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品进货价格信息Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePurchasePriceService extends Service {

	/**
	 * 同步商品进货价格信息
	 * 
	 * @param file
	 * @param charsetCode
	 * @param processCount
	 * @param fileRootPath
	 */
	public void insertMerchandisePurchasePrice();

	/**
	 * 添加商品进货价格信息
	 * 
	 */
	public void insertMerchandisePurchasePrice(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 添加去重复商品进货价格信息
	 * 
	 */
	public void insertMerchandisePurchasePriceGroup(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 删除商品进货价格信息
	 */
	public void deleteMerchandisePurchasePrice();

}