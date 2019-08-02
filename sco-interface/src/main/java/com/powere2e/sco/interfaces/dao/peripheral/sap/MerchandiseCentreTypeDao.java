package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品中分类dao接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:39:54
 * @version 1.0
 */
public interface MerchandiseCentreTypeDao extends Dao {
	/**
	 * 新增商品中分类
	 * 
	 * @param map
	 */
	public void insertMerchandiseCentreType(Map<String, Object> map);
	
	/**
	 * 删除中分类
	 */
	public void deleteMerchandiseCentreType();

}
