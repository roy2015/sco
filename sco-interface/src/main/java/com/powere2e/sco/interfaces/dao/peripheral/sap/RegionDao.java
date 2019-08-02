package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 区域信息DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface RegionDao extends Dao {
	
	/**
	 * 添加区域信息
	 *
	 * @param map
	 *				
	 */
	public void insertRegion(Map<String, Object> map);
	
	/**
	 * 删除添加区域信息
	 */
	public void deleteRegion();
	
}