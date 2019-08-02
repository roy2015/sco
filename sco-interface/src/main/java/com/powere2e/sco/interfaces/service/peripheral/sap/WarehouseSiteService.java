package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 仓位Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface WarehouseSiteService extends Service {
	
	/**
	 * 添加仓位
	 *
	 * @param map
	 *				
	 */
	public void insertWarehouseSite();
	/**
	 * 添加仓位
	 * @param file
	 */
	public void completeInsertWarehouseSite(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除仓位
	 */
	public void deleteWarehouseSite();
}