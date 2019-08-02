package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellJoinDayDao;

/**
 * 商品区域销售情况(日加盟)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellJoinDayDaoImpl extends DaoImpl implements MerchandiseSellJoinDayDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6391528473377881970L;

	// 添加
	@Override
	public void insertMerchandiseSellJoinDay(Map<String, Object> map) {
		this.insert(MerchandiseSellJoinDayDao.class, "saveMerchandiseSellJoinDay", map);
	}

	/**
	 * 删除商品区域销售情况(日加盟)
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void delateMerchandiseSellJoinDay(Map<String, Object> map) {
		this.delete(MerchandiseSellJoinDayDao.class, "deleteMerchandiseSellJoinDay", map);
	}
}