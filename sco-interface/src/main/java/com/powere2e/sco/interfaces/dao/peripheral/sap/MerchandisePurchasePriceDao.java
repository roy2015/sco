package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.peripheral.sap.MerchandisePurchasePrice;
/**
 * 商品进货价格信息DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePurchasePriceDao extends Dao {
	
	/**
	 * 添加商品进货价格信息
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandisePurchasePrice(Map<String, Object> map);
	
	/**
	 * 删除商品进货价格信息
	 */
	public void deleteMerchandisePurchasePrice();
	
	/**
	 * 根据一条数据，查询是否有相同的数据
	 * 
	 * @param map
	 */
	public List<MerchandisePurchasePrice> querySameMerchandisePurchasePrice(Map<String, Object> map);
	
	/**
	 * 添加商品进货价格的重复数据
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandisePurchasePriceGroup(Map<String, Object> map);
}