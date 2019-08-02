package com.powere2e.sco.dao.impl.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.peripheral.bw.MerchandiseJhPermissionDao;

/**
 * 商品进货权限DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseJhPermissionDaoImpl extends DaoImpl implements MerchandiseJhPermissionDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -387707006891218184L;

	// 添加
	@Override
	public void insertMerchandiseJhPermission(Map<String, Object> map) {
		this.insert(MerchandiseJhPermissionDao.class, "saveMerchandiseJhPermission", map);
	}

}