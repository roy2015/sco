package com.powere2e.sco.interfaces.dao.masterdata;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品上下市信息 Dao接口
 * 
 * @author lipengjie
 * @since 2016年3月30日
 * @version 1.0
 */
public interface MerchandiseMarketDao extends Dao {
	
	/**
	 * 新增商品上下市信息(批量)
	 * @param map key为list的商品上下市信息集合
	 */
	public void insertMerchandiseMarket(Map<String, Object> map);
}