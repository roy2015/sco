package com.powere2e.sco.dao.impl.peripheral.sap;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.sap.RegionDao;
/**
 * 区域信息DAO接口的实现
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class RegionDaoImpl extends DaoImpl implements RegionDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9147526156073302379L;

	//添加
	@Override
	public void insertRegion(Map<String, Object> map){
		this.insert(RegionDao.class, "saveRegion", map);
	}

	@Override
	public void deleteRegion() {
		this.delete(RegionDao.class, "deleteRegion", null);		
	}
	
	
}