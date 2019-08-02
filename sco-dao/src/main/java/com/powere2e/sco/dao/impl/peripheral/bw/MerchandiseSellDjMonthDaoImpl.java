package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellDjMonthDao;
/**
 * 商品区域销售情况(月直营+加盟)DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDjMonthDaoImpl extends DaoImpl implements MerchandiseSellDjMonthDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -304297366974121976L;

	//添加
	@Override
	public void insertMerchandiseSellDjMonth(Map<String, Object> map){
		this.insert(MerchandiseSellDjMonthDao.class, "saveMerchandiseSellDjMonth", map);
	}
	
}