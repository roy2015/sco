package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseSellJoinMonthDao;
/**
 * 商品区域销售情况(月加盟)DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellJoinMonthDaoImpl extends DaoImpl implements MerchandiseSellJoinMonthDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5472082284657369267L;

	//添加
	@Override
	public void insertMerchandiseSellJoinMonth(Map<String, Object> map){
		this.insert(MerchandiseSellJoinMonthDao.class, "saveMerchandiseSellJoinMonth", map);
	}
	
}