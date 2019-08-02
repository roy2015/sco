package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品明细类dao接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:40:18
 * @version 1.0
 */
public interface MerchandiseDetailTypeDao extends Dao {
	/**
	 * 新增商品明细类
	 * 
	 * @param map
	 */
	public void insertMerchandiseDetailType(Map<String, Object> map);
	
	/**
	 * 删除商品明细类
	 */
	public void deleteMerchandiseDetailType();

}
