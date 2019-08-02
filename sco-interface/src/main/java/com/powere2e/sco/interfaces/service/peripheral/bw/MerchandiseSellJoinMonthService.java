package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 商品区域销售情况(月加盟)Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public interface MerchandiseSellJoinMonthService extends Service {
	
	/**
	 * 添加商品区域销售情况(月加盟)
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSellJoinMonth();
	
	/**
	 * 添加商品区域销售情况(月加盟)
	 *
	 * @param map
	 *				
	 */
	public void completeInsertMerchandiseSellJoinMonth(File file,String charsetCode,int processCount,String fileRootPath);
	
}