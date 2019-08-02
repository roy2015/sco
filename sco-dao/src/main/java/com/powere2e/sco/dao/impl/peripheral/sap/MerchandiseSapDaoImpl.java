package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSapDao;

/**
 * 商品DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseSapDaoImpl extends DaoImpl implements MerchandiseSapDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4203975116582184599L;

	// 添加
	@Override
	public void insertMerchandiseSap(Map<String, Object> map) {
		this.insert(MerchandiseSapDao.class, "saveMerchandise", map);
	}

	@Override
	public void deleteMerchandiseSap() {
		this.delete(MerchandiseSapDao.class, "deleteMerchandise", null);
	}

}