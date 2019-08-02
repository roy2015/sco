package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.peripheral.sap.MerchandisePrice;

/**
 * 商品销售价格信息DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandisePriceDao extends Dao {

	/**
	 * 添加商品销售价格信息
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandisePrice(Map<String, Object> map);

	/**
	 * 删除商品销售价格信息
	 */
	public void deleteMerchandisePrice();

	/**
	 * 根据一条数据，查询是否有相同的数据
	 * 
	 * @param map
	 */
	public List<MerchandisePrice> querySameMerchandisePrice(Map<String, Object> map);
	
	/**
	 * 添加商品销售价格的重复数据
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandisePriceGroup(Map<String, Object> map);
}