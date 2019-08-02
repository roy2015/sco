package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 仓库DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface WarehouseDao extends Dao {
	
	/**
	 * 添加仓库
	 *
	 * @param map
	 *				
	 */
	public void insertWarehouse(Map<String, Object> map);
	
	/**
	 * 删除仓库
	 */
	public void deleteWarehouse();
}