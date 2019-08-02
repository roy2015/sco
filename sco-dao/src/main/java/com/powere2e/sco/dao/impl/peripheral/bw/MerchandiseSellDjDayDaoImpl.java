package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDjDayDao;

/**
 * 商品区域销售情况(日直营+加盟)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDjDayDaoImpl extends DaoImpl implements MerchandiseSellDjDayDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9010758988668397478L;

	// 添加
	@Override
	public void insertMerchandiseSellDjDay(Map<String, Object> map) {
		this.insert(MerchandiseSellDjDayDao.class, "saveMerchandiseSellDjDay", map);
	}

	/**
	 * 删除商品区域销售情况(日直营+加盟)
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void delateMerchandiseSellDjDay(Map<String, Object> map) {
		this.delete(MerchandiseSellDjDayDao.class, "deleteMerchandiseSellDjDay", map);
	}
}