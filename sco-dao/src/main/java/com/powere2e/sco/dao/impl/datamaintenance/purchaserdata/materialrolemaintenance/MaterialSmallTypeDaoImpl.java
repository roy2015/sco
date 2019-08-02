package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallTypeDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallType;

/**
 * 原料小类DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialSmallTypeDaoImpl extends DaoImpl implements MaterialSmallTypeDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6596737442752132503L;

	// 查询
	@Override
	public List<MaterialSmallType> listMaterialSmallType(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MaterialSmallTypeDao.class, "searchMaterialSmallType", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMaterialSmallType(Map<String, Object> map) {
		this.insert(MaterialSmallTypeDao.class, "saveMaterialSmallType", map);
	}

	// 删除
	@Override
	public void deleteMaterialSmallType(Map<String, Object> map) {
		this.delete(MaterialSmallTypeDao.class, "deleteMaterialSmallType", map);
	}

	// 修改
	@Override
	public void updateMaterialSmallType(Map<String, Object> map) {
		this.update(MaterialSmallTypeDao.class, "updateMaterialSmallType", map);
	}

	// 装载一个原料小类
	@Override
	public MaterialSmallType loadMaterialSmallType(Map<String, Object> map) {
		return (MaterialSmallType) this.get(MaterialSmallTypeDao.class, "searchMaterialSmallType", map);
	}
	//查询原料中存在该原料小类的数量
	@Override
	public Integer searchMaterial(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return (Integer) this.get(MaterialSmallTypeDao.class, "searchMaterial", map);
	}
}