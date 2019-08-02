package com.powere2e.sco.interfaces.service.peripheral.sap;

import java.io.File;

import com.powere2e.frame.commons.service.Service;
/**
 * 区域信息Service接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface RegionService extends Service {
	
	/**
	 * 添加区域信息
	 *
	 * @param map
	 *				
	 */
	public void insertRegion();
	
	/**
	 * 添加区域信息
	 * @param file
	 */
	public void completeInsertRegion(File file,String charsetCode,int processCount,String fileRootPath);

	/**
	 * 删除区域
	 */
	public void deleteRegion();
	
}