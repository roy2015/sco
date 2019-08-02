package com.powere2e.sco.interfaces.dao.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品区域销售情况(日直营+加盟)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public interface MerchandiseSellDjDayDao extends Dao {

	/**
	 * 添加商品区域销售情况(日直营+加盟)
	 *
	 * @param map
	 *
	 */
	public void insertMerchandiseSellDjDay(Map<String, Object> map);

	/**
	 * 删除商品区域销售情况(日直营+加盟)
	 * 
	 * @param map
	 */
	public void delateMerchandiseSellDjDay(Map<String, Object> map);
}