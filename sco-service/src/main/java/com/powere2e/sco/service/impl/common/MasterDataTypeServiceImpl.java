package com.powere2e.sco.service.impl.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.common.MasterDataTypeDao;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.security.model.Option;

/**
 * SAP主数据中的类型及管理员维护的细分类Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public class MasterDataTypeServiceImpl extends ServiceImpl implements MasterDataTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4372840581672102817L;

	private MasterDataTypeDao masterDataTypeDao;

	// 获取MasterDataType Dao实例
	public MasterDataTypeDao getMasterDataTypeDao() {
		return masterDataTypeDao;
	}

	// 设置MasterDataType Dao实例
	public void setMasterDataTypeDao(MasterDataTypeDao masterDataTypeDao) {
		this.masterDataTypeDao = masterDataTypeDao;
	}

	/**
	 * 方便其他模块调用
	 * 
	 * @return Service实例
	 */
	public static MasterDataTypeService getInstance() {
		return (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	@Override
	public List<Option> listCenterType(Map<String, Object> map) {
		return this.masterDataTypeDao.listCenterType(map);
	}

	@Override
	public List<Option> listSmallType(Map<String, Object> map) {
		return this.masterDataTypeDao.listSmallType(map);
	}

	@Override
	public List<Option> listDetailType(Map<String, Object> map) {
		return this.masterDataTypeDao.listDetailType(map);
	}

	@Override
	public List<Option> listFineType(Map<String, Object> map) {
		return this.masterDataTypeDao.listFineType(map);
	}

	@Override
	public List<Option> listSaleType() {
		return this.masterDataTypeDao.listSaleType();
	}

	@Override
	public List<Option> listRegion() {
		return this.masterDataTypeDao.listRegion();
	}

	@Override
	public String nextID(String sequenceName) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("sequenceName", sequenceName);
		return this.masterDataTypeDao.nextID(map);
	}

	@Override
	public List<Option> listWarehouseOption() {
		return this.masterDataTypeDao.listWarehouseOption();
	}
}