package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 供应商历史物料号Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface MerchandiseSupplierHistoryService extends Service {
	
	/**
	 * 添加供应商历史物料号
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSupplierHistory();
	
	/**
	 * 添加供应商历史物料号
	 * @param file
	 */
	public void completeInsertMerchandiseSupplierHistory(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除供应商历史物料号
	 */
	public void deleteMerchandiseSupplierHistory();
	
}