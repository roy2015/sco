package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品历史物料号dao接口
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:41:27
 * @version 1.0
 */
public interface MerchandiseHistoryDao extends Dao {
	/**
	 * 商品历史物料号
	 * 
	 * @param map
	 */
	public void insertMerchandiseHistory(Map<String, Object> map);

	/**
	 * 删除商品历史物料号
	 */
	public void deleteMerchandiseHistory();
}
