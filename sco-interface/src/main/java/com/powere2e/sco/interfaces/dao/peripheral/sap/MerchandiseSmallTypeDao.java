package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;


/**
 * 商品小分类dao接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:41:51
 * @version 1.0
 */
public interface MerchandiseSmallTypeDao extends Dao {
	/**
	 * 商品小分类
	 * 
	 * @param map
	 */
	public void insertMerchandiseSmallType(Map<String, Object> map);

	/**
	 * 删除商品小分类
	 */
	public void deleteMerchandiseSmallType();
}
