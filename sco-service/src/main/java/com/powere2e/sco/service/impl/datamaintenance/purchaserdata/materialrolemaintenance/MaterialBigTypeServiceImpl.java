package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigTypeDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialBigType;

/**
 * 原料大类业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialBigTypeServiceImpl extends ServiceImpl implements MaterialBigTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2256814658689808380L;
	private MaterialBigTypeDao materialBigTypeDao;

	public static MaterialBigTypeService getInstance() {
		return (MaterialBigTypeService) ConfigFactory.getInstance().getBean("materialBigTypeService");
	}

	// 获得原料大类DAO实例
	public MaterialBigTypeDao getMaterialBigTypeDao() {
		return materialBigTypeDao;
	}

	// 设置原料大类DAO实例
	public void setMaterialBigTypeDao(MaterialBigTypeDao materialBigTypeDao) {
		this.materialBigTypeDao = materialBigTypeDao;
	}

	// 查询
	@Override
	public List<MaterialBigType> listMaterialBigType(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMaterialBigTypeDao().listMaterialBigType(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMaterialBigType(MaterialBigType materialBigType) {
		this.getMaterialBigTypeDao().insertMaterialBigType(materialBigType.toMap());
	}

	// 删除
	@Override
	public void deleteMaterialBigType(String materialBigTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", materialBigTypeCode.split(","));
		this.getMaterialBigTypeDao().deleteMaterialBigType(map);
	}

	// 修改
	@Override
	public void updateMaterialBigType(MaterialBigType materialBigType) {
		this.getMaterialBigTypeDao().updateMaterialBigType(materialBigType.toMap());
	}

	// 加载一个原料大类
	@Override
	public MaterialBigType loadMaterialBigType(String materialBigTypeName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeName", materialBigTypeName);
		return this.getMaterialBigTypeDao().loadMaterialBigType(map);
	}

	// 加载一个原料大类
	@Override
	public MaterialBigType loadMaterialBigTypeByCode(String materialBigTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", materialBigTypeCode);
		return this.getMaterialBigTypeDao().loadMaterialBigType(map);
	}

	// 查询存在数量
	@Override
	public Integer serachMaterialSmallType(String materialBigTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialBigTypeCode", materialBigTypeCode);
		return this.getMaterialBigTypeDao().searchMaterialSmallType(map);
	}
}