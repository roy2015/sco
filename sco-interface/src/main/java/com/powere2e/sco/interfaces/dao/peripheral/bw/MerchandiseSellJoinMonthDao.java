package com.powere2e.sco.interfaces.dao.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
/**
 * 商品区域销售情况(月加盟)DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public interface MerchandiseSellJoinMonthDao extends Dao {
	
	/**
	 * 添加商品区域销售情况(月加盟)
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseSellJoinMonth(Map<String, Object> map);
	
}