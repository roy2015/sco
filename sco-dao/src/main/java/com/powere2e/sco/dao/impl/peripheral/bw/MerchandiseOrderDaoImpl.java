package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseOrderDao;
/**
 * 商品订货单信息DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseOrderDaoImpl extends DaoImpl implements MerchandiseOrderDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7943168853002685733L;

	//添加
	@Override
	public void insertMerchandiseOrder(Map<String, Object> map){
		this.insert(MerchandiseOrderDao.class, "saveMerchandiseOrder", map);
	}
	
}