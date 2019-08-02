package com.powere2e.sco.service.impl.common;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.common.MaterialPropertiesDao;
import com.powere2e.sco.interfaces.service.common.MaterialPropertiesService;
import com.powere2e.security.model.Option;

/**
 * 原料相关属性查询 Service实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class MaterialPropertiesServiceImpl extends ServiceImpl implements
		MaterialPropertiesService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7723779616553779704L;
	private MaterialPropertiesDao materialPropertiesDao;

	// 获取MaterialTypeDao实例
	public MaterialPropertiesDao getMaterialPropertiesDao() {
		return materialPropertiesDao;
	}
	
	// 设置MaterialTypeDao实例
	public void setMaterialPropertiesDao(MaterialPropertiesDao materialPropertiesDao) {
		this.materialPropertiesDao = materialPropertiesDao;
	}

	@Override
	public List<Option> listMaterialBigType(Map<String, Object> map) {
		return this.materialPropertiesDao.listMaterialBigType(map);
	}

	@Override
	public List<Option> listMaterialSmallType(Map<String, Object> map) {
		return this.materialPropertiesDao.listMaterialSmallType(map);
	}

	@Override
	public List<Option> listWebsiteMaterialName(Map<String, Object> map) {
		return this.materialPropertiesDao.listWebsiteMaterialName(map);
	}

	@Override
	public List<Option> listWebsiteMaterialSiteName(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return this.materialPropertiesDao.listWebsiteMaterialSiteName(map);
	}

}