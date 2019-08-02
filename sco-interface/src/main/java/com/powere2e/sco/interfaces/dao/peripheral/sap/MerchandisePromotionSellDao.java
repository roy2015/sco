package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 商品促销销售价DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePromotionSellDao extends Dao {
	
	/**
	 * 添加商品促销销售价
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandisePromotionSell(Map<String, Object> map);
	
	/**
	 * 删除商品促销销售价
	 */
	public void deleteMerchandisePromotionSell();
	
}