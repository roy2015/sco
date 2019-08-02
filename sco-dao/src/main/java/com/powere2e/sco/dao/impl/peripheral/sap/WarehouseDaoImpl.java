package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.WarehouseDao;
/**
 * 仓库DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class WarehouseDaoImpl extends DaoImpl implements WarehouseDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6132340137375355012L;

	//添加
	@Override
	public void insertWarehouse(Map<String, Object> map){
		this.insert(WarehouseDao.class, "saveWarehouse", map);
	}

	@Override
	public void deleteWarehouse() {
		this.delete(WarehouseDao.class, "deleteWarehouse", null);		
	}
	
}