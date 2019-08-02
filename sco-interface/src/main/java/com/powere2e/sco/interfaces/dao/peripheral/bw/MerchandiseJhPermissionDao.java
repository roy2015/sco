package com.powere2e.sco.interfaces.dao.peripheral.bw;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 商品进货权限DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public interface MerchandiseJhPermissionDao extends Dao {

	/**
	 * 添加商品进货权限
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandiseJhPermission(Map<String, Object> map);

}