package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallTypeDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallTypeService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.MaterialSmallType;

/**
 * 原料小类业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialSmallTypeServiceImpl extends ServiceImpl implements MaterialSmallTypeService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2090027390112246671L;
	private MaterialSmallTypeDao materialSmallTypeDao;

	public static MaterialSmallTypeService getInstance() {
		return (MaterialSmallTypeService) ConfigFactory.getInstance().getBean("materialSmallTypeService");
	}

	// 获得原料小类DAO实例
	public MaterialSmallTypeDao getMaterialSmallTypeDao() {
		return materialSmallTypeDao;
	}

	// 设置原料小类DAO实例
	public void setMaterialSmallTypeDao(MaterialSmallTypeDao materialSmallTypeDao) {
		this.materialSmallTypeDao = materialSmallTypeDao;
	}

	// 查询
	@Override
	public List<MaterialSmallType> listMaterialSmallType(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMaterialSmallTypeDao().listMaterialSmallType(map, pageInfo);
	}

	// 添加
	@Override
	public void insertMaterialSmallType(MaterialSmallType materialSmallType) {
		this.getMaterialSmallTypeDao().insertMaterialSmallType(materialSmallType.toMap());
	}

	// 删除
	@Override
	public void deleteMaterialSmallType(String materialSmallTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialSmallTypeCode", materialSmallTypeCode.split(","));
		this.getMaterialSmallTypeDao().deleteMaterialSmallType(map);
	}

	// 修改
	@Override
	public void updateMaterialSmallType(MaterialSmallType materialSmallType) {
		this.getMaterialSmallTypeDao().updateMaterialSmallType(materialSmallType.toMap());
	}

	// 加载一个原料小类
	@Override
	public MaterialSmallType loadMaterialSmallType(String materialSmallTypeName, String materialBigTypeCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialSmallTypeName", materialSmallTypeName);
		map.put("materialBigTypeCode", materialBigTypeCode);
		return this.getMaterialSmallTypeDao().loadMaterialSmallType(map);
	}

	// 查询存在数量
	@Override
	public Integer serachMaterial(String materialSmallTypeCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialSmallTypeCode", materialSmallTypeCode);
		return this.getMaterialSmallTypeDao().searchMaterial(map);
	}

	// 根据编号查询
	@Override
	public MaterialSmallType loadMaterialSmallTypeCode(String materialSmallTypeCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialSmallTypeCode", materialSmallTypeCode);
		return this.getMaterialSmallTypeDao().loadMaterialSmallType(map);
	}
}