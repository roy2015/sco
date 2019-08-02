package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSmallTypeDao;

/**
 * 商品小分类dao实现
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:54:24
 * @version 1.0
 */
public class MerchandiseSmallTypeDaoImpl extends DaoImpl implements MerchandiseSmallTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	/**
	 * 
	 */
	@Override
	public void insertMerchandiseSmallType(Map<String, Object> map) {
		this.insert(MerchandiseSmallTypeDao.class, "saveMerchandiseSmallType", map);
	}

	@Override
	public void deleteMerchandiseSmallType() {
		this.delete(MerchandiseSmallTypeDao.class, "deleteMerchandiseSmallType", null);
	}

}
