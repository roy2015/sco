package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 商品Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandiseSapService extends Service {
	
	/**
	 * 添加商品
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSap();
	
	public void completeInsertMerchandiseSap(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除商品
	 */
	public void deleteMerchandiseSap();
}