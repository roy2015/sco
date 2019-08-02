package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSupplierDao;

/**
 * 商品供应商dao实现
 * @author Joyce.li
 *  @since 2015年8月18日 上午10:54:24
 *  @version 1.0
 */
public class MerchandiseSupplierDaoImpl extends DaoImpl implements MerchandiseSupplierDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6269131400191752884L;

	/**
	 * 
	 */
	@Override
	public void insertMerchandiseSupplier(Map<String, Object> map) {
		this.insert(MerchandiseSupplierDao.class, "saveMerchandiseSupplier", map);
	}

	@Override
	public void deleteMerchandiseSupplier() {
		this.delete(MerchandiseSupplierDao.class, "deleteMerchandiseSupplier", null);
	}

	

}
