package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;



/**
 * 商品历史物料号service接口
 * @author Joyce.li
 *  @since 2015年8月18日 上午10:46:46
 *  @version 1.0
 */
public interface MerchandiseHistoryService extends Service {
	/**
	 * 商品历史物料号
	 */
	public void insertMerchandiseHistory();

	/**
	 * 商品历史物料号
	 * @param file
	 */
	public void completeInsertMerchandiseHistory(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除商品历史物料号
	 */
	public void deleteMerchandiseHistory();

}
