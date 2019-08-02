package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;

/**
 * 原料业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialServiceImpl extends ServiceImpl implements MaterialService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 969651263133349231L;
	private MaterialDao materialDao;

	public static MaterialService getInstance() {
		return (MaterialService) ConfigFactory.getInstance().getBean("materialService");
	}

	// 获得原料DAO实例
	public MaterialDao getMaterialDao() {
		return materialDao;
	}

	// 设置原料DAO实例
	public void setMaterialDao(MaterialDao materialDao) {
		this.materialDao = materialDao;
	}

	// 查询
	@Override
	public List<Material> listMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMaterialDao().listMaterial(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMaterial(Material material) {
		this.getMaterialDao().insertMaterial(material.toMap());
	}

	// 添加地址
	@Override
	public void insertMaterialRegion(Map<String, Object> map) {
		this.getMaterialDao().insertMaterialRegion(map);
	}

	// 查询
	@Override
	public Material loadchMaterial(Material material) {
		return this.getMaterialDao().loadMaterial(material.toMap());
	}

	// 删除
	@Override
	public void deleteMaterial(String materialCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialCode", materialCode.split(","));
		this.getMaterialDao().deleteMaterial(map);
	}

	// 修改
	@Override
	public void updateMaterial(Material material) {
		this.getMaterialDao().updateMaterial(material.toMap());
	}

	// 加载一个原料
	@Override
	public Material loadMaterial(String materialCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialCode", materialCode);
		return this.getMaterialDao().loadMaterial(map);
	}

	// 查询存在数量
	@Override
	public Integer serachMaterialId() {
		return this.getMaterialDao().searchMaterialId();
	}

	// 添加原料地区价格
	@Override
	public void insertMaterialPrice(Map<String, Object> map) {
		this.materialDao.insertMaterialPrice(map);
	}

	// 修改原料地区
	@Override
	public void updateMaterialRegion(Map<String, Object> map) {
		this.materialDao.updateMaterialRegion(map);
	}

	// 修改原料价格
	@Override
	public void updateMaterialPrice(Map<String, Object> map) {
		this.materialDao.updateMaterialPrice(map);
	}

}