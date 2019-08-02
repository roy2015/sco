package com.powere2e.sco.dao.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialDao;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;

/**
 * 原料DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialDaoImpl extends DaoImpl implements MaterialDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1558621519373219220L;

	// 查询
	@Override
	public List<Material> listMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MaterialDao.class, "searchMaterial", map, pageInfo);
	}

	// 添加
	@Override
	public void insertMaterial(Map<String, Object> map) {
		this.insert(MaterialDao.class, "saveMaterial", map);
	}

	// 删除
	@Override
	public void deleteMaterial(Map<String, Object> map) {
		this.delete(MaterialDao.class, "deleteMaterial", map);
	}

	// 修改
	@Override
	public void updateMaterial(Map<String, Object> map) {
		this.update(MaterialDao.class, "updateMaterial", map);
	}

	// 装载一个原料
	@Override
	public Material loadMaterial(Map<String, Object> map) {
		return (Material) this.get(MaterialDao.class, "loadMaterial", map);
	}

	// 添加地区
	@Override
	public void insertMaterialRegion(Map<String, Object> map) {
		this.insert(MaterialDao.class, "saveMaterialRegion", map);
	}

	// 查询当前原料编号
	@Override
	public Integer searchMaterialId() {
		return (Integer) this.get(MaterialDao.class, "searchMaterialId", null);
	}

	@Override
	public void insertMaterialPrice(Map<String, Object> map) {
		Integer i=(Integer) this.get(MaterialDao.class, "loadMaterialPrice", map);
		if(i==0)
		this.insert(MaterialDao.class, "insertMaterialPrice", map);
	}

	@Override
	public void updateMaterialRegion(Map<String, Object> map) {
		this.update(MaterialDao.class, "updateMaterialRegion", map);
	}

	@Override
	public void updateMaterialPrice(Map<String, Object> map) {
		this.update(MaterialDao.class, "updateMaterialPrice", map);
	}
	
}