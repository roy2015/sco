package com.powere2e.sco.interfaces.dao.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 商品收货单信息DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public interface MerchandiseReceiptDao extends Dao {
	
	/**
	 * 添加商品收货单信息
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseReceipt(Map<String, Object> map);
	
}