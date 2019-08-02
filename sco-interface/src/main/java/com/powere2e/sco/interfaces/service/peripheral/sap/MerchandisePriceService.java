package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品销售价格信息Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePriceService extends Service {

	/**
	 * 同步商品销售价格信息
	 *
	 * @param map
	 *
	 */
	public void insertMerchandisePrice();

	/**
	 * 新增商品价格
	 * 
	 * @param file
	 * @param charsetCode
	 * @param processCount
	 * @param fileRootPath
	 */
	public void insertMerchandisePrice(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 新增去重复后的商品价格信息
	 * 
	 * @param file
	 * @param charsetCode
	 * @param processCount
	 * @param fileRootPath
	 */
	public void insertMerchandisePriceGroup(File file, String charsetCode, int processCount, String fileRootPath);

	/**
	 * 删除商品销售价格信息
	 */
	public void deleteMerchandisePrice();
}