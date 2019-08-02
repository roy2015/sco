package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 仓位DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface WarehouseSiteDao extends Dao {
	
	/**
	 * 添加仓位
	 *
	 * @param map
	 *				
	 */
	public void insertWarehouseSite(Map<String, Object> map);
	
	/**
	 * 删除添加仓位
	 */
	public void deleteWarehouseSite();
	
}