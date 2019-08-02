package com.powere2e.sco.dao.impl.common;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.common.MasterDataTypeDao;
import com.powere2e.security.model.Option;

/**
 * SAP主数据中的类型及管理员维护的细分类DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public class MasterDataTypeDaoImpl extends DaoImpl implements MasterDataTypeDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6020623347736113547L;

	@Override
	public List<Option> listCenterType(Map<String, Object> map) {
		return this.query(MasterDataTypeDao.class, "listCenterType", map, null);
	}

	@Override
	public List<Option> listSmallType(Map<String, Object> map) {
		return this.query(MasterDataTypeDao.class, "listSmallType", map, null);
	}

	@Override
	public List<Option> listDetailType(Map<String, Object> map) {
		return this.query(MasterDataTypeDao.class, "listDetailType", map, null);
	}

	@Override
	public List<Option> listFineType(Map<String, Object> map) {
		return this.query(MasterDataTypeDao.class, "listFineType", map, null);
	}

	@Override
	public List<Option> listSaleType() {
		return this.query(MasterDataTypeDao.class, "listSaleType", null, null);
	}

	@Override
	public List<Option> listRegion() {
		return this.query(MasterDataTypeDao.class, "listRegion", null, null);
	}

	@Override
	public String nextID(Map<String, Object> map) {
		return (String)this.get(MasterDataTypeDao.class, "nextID", map);
	}

	@Override
	public List<Option> listWarehouseOption() {
		return this.query(MasterDataTypeDao.class, "listWarehouseOption", null, null);
	}

}