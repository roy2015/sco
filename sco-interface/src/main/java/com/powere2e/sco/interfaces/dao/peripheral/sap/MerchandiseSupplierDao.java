package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 供应商
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:43:05
 * @version 1.0
 */
public interface MerchandiseSupplierDao extends Dao {
	/**
	 * 供应商
	 * 
	 * @param map
	 */
	public void insertMerchandiseSupplier(Map<String, Object> map);

	/**
	 * 删除供应商
	 */
	public void deleteMerchandiseSupplier();
}
