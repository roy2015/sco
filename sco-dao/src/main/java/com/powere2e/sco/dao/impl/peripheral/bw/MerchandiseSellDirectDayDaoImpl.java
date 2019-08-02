package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDirectDayDao;
import com.powere2e.sco.model.peripheral.bw.MerchandiseSellDirectDay;

/**
 * 商品区域销售情况(日直营)单Dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月17日 上午10:24:46
 * @version 1.0
 */
public class MerchandiseSellDirectDayDaoImpl extends DaoImpl implements MerchandiseSellDirectDayDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	/**
	 * 插入商品区域销售情况(日直营)单
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void insertMerchandiseSellDirectDay(MerchandiseSellDirectDay merchandiseSellDirectDay) {
		this.insert(MerchandiseSellDirectDayDao.class, "saveMerchandiseSellDirectDay", merchandiseSellDirectDay.toMap());
	}

	/**
	 * 删除商品区域销售情况(日直营)单
	 * 
	 * @param merchandiseSellDirectDay
	 */
	public void delateMerchandiseSellDirectDay(Map<String, Object> map) {
		this.delete(MerchandiseSellDirectDayDao.class, "deleteMerchandiseSellDirectDay", map);
	}

}
