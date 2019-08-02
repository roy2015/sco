package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 商品订货单信息Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface MerchandiseOrderService extends Service {
	
	/**
	 * 添加商品订货单信息
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseOrder();
	
	/**
	 * 添加商品订货单信息
	 *
	 * @param map
	 *				
	 */
	public void completeInsertMerchandiseOrder(File file,String charsetCode,int processCount,String fileRootPath);
	
}