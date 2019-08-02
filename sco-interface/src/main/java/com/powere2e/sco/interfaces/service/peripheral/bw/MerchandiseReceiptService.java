package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;

import com.powere2e.frame.commons.service.Service;

/**
 * 商品收货单信息Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface MerchandiseReceiptService extends Service {

	/**
	 * 添加商品收货单信息
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandiseReceipt();

	/**
	 * 添加商品收货单信息
	 * 
	 * @param map
	 * 
	 */
	public void completeInsertMerchandiseReceipt(File file,String charsetCode,int processCount,String fileRootPath);

}