package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.WarehouseSiteDao;
/**
 * 仓位DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class WarehouseSiteDaoImpl extends DaoImpl implements WarehouseSiteDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2319504287067451998L;

	//添加
	@Override
	public void insertWarehouseSite(Map<String, Object> map){
		this.insert(WarehouseSiteDao.class, "saveWarehouseSite", map);
	}

	@Override
	public void deleteWarehouseSite() {
		this.delete(WarehouseSiteDao.class, "deleteWarehouseSite", null);		
	}
	
}