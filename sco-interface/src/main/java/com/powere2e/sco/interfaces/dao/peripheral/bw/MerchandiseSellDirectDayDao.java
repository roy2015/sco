package com.powere2e.sco.interfaces.dao.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellDirectDay;

/**
 * 商品区域销售情况(日直营)单Dao
 * 
 * @author Joyce.li
 * @since 2015年8月17日 上午10:21:38
 * @version 1.0
 */
public interface MerchandiseSellDirectDayDao extends Dao {
	/**
	 * 插入商品区域销售情况(日直营)单
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void insertMerchandiseSellDirectDay(MerchandiseSellDirectDay merchandiseSellDirectDay);

	/**
	 * 删除商品区域销售情况(日直营)单
	 * 
	 * @param map
	 */
	public void delateMerchandiseSellDirectDay(Map<String, Object> map);

}
