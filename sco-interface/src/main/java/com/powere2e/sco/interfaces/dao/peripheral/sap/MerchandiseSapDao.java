package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 商品DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandiseSapDao extends Dao {
	
	/**
	 * 添加商品
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSap(Map<String, Object> map);
	
	/**
	 * 删除商品
	 */
	public void deleteMerchandiseSap();
}