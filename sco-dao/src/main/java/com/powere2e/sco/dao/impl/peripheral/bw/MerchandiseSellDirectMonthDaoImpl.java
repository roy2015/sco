package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDirectMonthDao;

/**
 * 商品区域销售情况(月直营)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDirectMonthDaoImpl extends DaoImpl implements MerchandiseSellDirectMonthDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1589569129232670578L;

	// 添加
	@Override
	public void insertMerchandiseSellDirectMonth(Map<String, Object> map) {
		this.insert(MerchandiseSellDirectMonthDao.class, "saveMerchandiseSellDirectMonth", map);
	}

}