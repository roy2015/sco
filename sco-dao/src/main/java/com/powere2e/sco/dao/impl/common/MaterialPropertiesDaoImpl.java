package com.powere2e.sco.dao.impl.common;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.common.MaterialPropertiesDao;
import com.powere2e.security.model.Option;

/**
 * 原料相关属性查询 Dao实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class MaterialPropertiesDaoImpl extends DaoImpl implements
		MaterialPropertiesDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4216741668798933859L;

	@Override
	public List<Option> listMaterialBigType(Map<String, Object> map) {
		return this.query(MaterialPropertiesDao.class, "listMaterialBigType",
				map, null);
	}

	@Override
	public List<Option> listMaterialSmallType(Map<String, Object> map) {
		return this.query(MaterialPropertiesDao.class, "listMaterialSmallType",
				map, null);
	}

	@Override
	public List<Option> listWebsiteMaterialName(Map<String, Object> map) {
		return this.query(MaterialPropertiesDao.class, "listWebsiteMaterialName",
				map, null);
	}
	@Override
	public List<Option> listWebsiteMaterialSiteName(Map<String, Object> map) {
		return this.query(MaterialPropertiesDao.class, "listWebsiteMaterialSiteName",
				map, null);
	}
}