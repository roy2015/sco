package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseCentreTypeDao;

/**
 * 商品中分类dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:54:24
 * @version 1.0
 */
public class MerchandiseCentreTypeDaoImpl extends DaoImpl implements MerchandiseCentreTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	@Override
	public void insertMerchandiseCentreType(Map<String, Object> map) {
		this.insert(MerchandiseCentreTypeDao.class, "saveMerchandiseCentreType", map);
	}

	// 删除商品中分类
	@Override
	public void deleteMerchandiseCentreType() {
		this.delete(MerchandiseCentreTypeDao.class, "deleteMerchandiseCentreType", null);
	}

}
