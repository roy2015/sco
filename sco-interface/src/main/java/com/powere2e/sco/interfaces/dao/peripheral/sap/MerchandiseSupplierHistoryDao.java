package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 供应商历史物料号DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface MerchandiseSupplierHistoryDao extends Dao {

	/**
	 * 添加供应商历史物料号
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSupplierHistory(Map<String, Object> map);
	
	/**
	 * 删除供应商历史物料号
	 */
	public void deleteMerchandiseSupplierHistory();
}