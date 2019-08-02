package com.powere2e.sco.dao.impl.parameterset.materialwarnconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.parameterset.materialwarnconfig.MaterialWarnConfigDao;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfig;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfigJoint;
import com.powere2e.security.model.Option;

/**
 * 原料行情预警设置DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月8日
 */
public class MaterialWarnConfigDaoImpl extends DaoImpl implements
		MaterialWarnConfigDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4899237975534467381L;

	// 查询
	@Override
	public List<MaterialWarnConfigJoint> JiontMAterialWarnCodfig(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MaterialWarnConfigDao.class,
				"JiontMAterialWarnCodfig", map, pageInfo);
	}
	
	@Override
	public List<Option> listWarnType() {
		return this.query(MaterialWarnConfigDao.class, "listWarnType", null, null);
	}
	// 添加
	@Override
	public void insertMaterialWarnConfig(Map<String, Object> map) {
		this.insert(MaterialWarnConfigDao.class, "saveMaterialWarnConfig", map);
	}

	// 删除
	@Override
	public void deleteMaterialWarnConfig(Map<String, Object> map) {
		this.delete(MaterialWarnConfigDao.class, "deleteMaterialWarnConfig",
				map);
	}

	// 修改
	@Override
	public void updateMaterialWarnConfig(Map<String, Object> map) {
		this.update(MaterialWarnConfigDao.class, "updateMaterialWarnConfig",
				map);
	}

	// 装载一个原料行情预警设置
	@Override
	public MaterialWarnConfig loadMaterialWarnConfig(Map<String, Object> map) {
		return (MaterialWarnConfig) this.get(MaterialWarnConfigDao.class,
				"searchMaterialWarnConfig", map);
	}

	// 根据原料预警方式，阀值查询预警记录
	@Override
	public MaterialWarnConfig listMAterialWarnCodfig(Map<String, Object> map) {
		return (MaterialWarnConfig) this.get(MaterialWarnConfigDao.class,
				"searchMaterialWarnConfigByInfo", map);
	}
}