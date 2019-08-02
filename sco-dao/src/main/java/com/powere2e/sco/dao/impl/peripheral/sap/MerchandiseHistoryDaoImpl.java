package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseHistoryDao;

/**
 * 商品历史物料号dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:53:55
 * @version 1.0
 */
public class MerchandiseHistoryDaoImpl extends DaoImpl implements MerchandiseHistoryDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	/**
	 * 
	 */
	@Override
	public void insertMerchandiseHistory(Map<String, Object> map) {
		this.insert(MerchandiseHistoryDao.class, "saveMerchandiseHistory", map);
	}

	@Override
	public void deleteMerchandiseHistory() {
		this.delete(MerchandiseHistoryDao.class, "deleteMerchandiseHistory", null);
	}

}
