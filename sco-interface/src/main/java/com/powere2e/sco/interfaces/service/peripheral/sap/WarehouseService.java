package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 仓库Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface WarehouseService extends Service {
	
	/**
	 * 添加仓库
	 *
	 * @param map
	 *				
	 */
	public void insertWarehouse();
	/**
	 * 添加仓库
	 * @param file
	 */
	public void completeInsertWarehouse(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除仓库
	 */
	public void deleteWarehouse();
	
}