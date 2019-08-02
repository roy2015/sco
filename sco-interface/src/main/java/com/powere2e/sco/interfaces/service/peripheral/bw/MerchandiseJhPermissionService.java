package com.powere2e.sco.interfaces.service.peripheral.bw;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 商品进货权限Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandiseJhPermissionService extends Service {
	
	/**
	 * 添加商品进货权限
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseJhPermission();
	
	public void completeInsertMerchandiseJhPermission(File file,String charsetCode,int processCount,String fileRootPath);
	
}