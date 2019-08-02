package com.powere2e.sco.dao.impl;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.SupplierDataDao;
import com.powere2e.security.model.Option;

/**
 * 	查询供应商列表下拉框dao
 *  @author Joyce.li
 *  @since 2015年3月20日 上午10:13:32
 *  @version 1.0
 */
public class SupplierDataDaoImpl extends DaoImpl implements SupplierDataDao {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5604813311823679773L;

	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplier(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierDataDao.class, "listAllSupplier", map, pageInfo);
	}
	/**
	 * 供应商下拉框数据查询
	 */
	public List<Option> listAllSupplierFl(Map<String, Object> map,PageInfo pageInfo){
		return this.query(SupplierDataDao.class, "listAllSupplierFl", map, pageInfo);
	}

	@Override
	public List<Option> listMasterSupplier(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(SupplierDataDao.class, "listMasterSupplier", map, pageInfo);
	}

}