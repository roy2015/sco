package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.MerchandiseSupplierHistoryDao;
/**
 * 供应商历史物料号DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseSupplierHistoryDaoImpl extends DaoImpl implements MerchandiseSupplierHistoryDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5385356459478813628L;

	//添加
	@Override
	public void insertMerchandiseSupplierHistory(Map<String, Object> map){
		this.insert(MerchandiseSupplierHistoryDao.class, "saveMerchandiseSupplierHistory", map);
	}

	@Override
	public void deleteMerchandiseSupplierHistory() {
		this.delete(MerchandiseSupplierHistoryDao.class, "deleteMerchandiseSupplierHistory", null);		
	}
	
}