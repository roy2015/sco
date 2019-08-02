package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 商品促销销售价Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePromotionSellService extends Service {
	
	/**
	 * 添加商品促销销售价
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandisePromotionSell();
	
	public void completeInsertMerchandisePromotionSell(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除商品促销销售价
	 */
	public void deleteMerchandisePromotionSell();
}