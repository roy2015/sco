package com.powere2e.sco.service.impl.parameterset.materialwarnconfig;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.parameterset.materialwarnconfig.MaterialWarnConfigDao;
import com.powere2e.sco.interfaces.service.parameterset.materialwarnconfig.MaterialWarnConfigService;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfig;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfigJoint;
import com.powere2e.security.model.Option;

/**
 * 原料行情预警设置业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月8日
 */
public class MaterialWarnConfigServiceImpl extends ServiceImpl implements MaterialWarnConfigService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -278164181778682332L;
	private MaterialWarnConfigDao materialWarnConfigDao;

	public static MaterialWarnConfigService getInstance() {
		return (MaterialWarnConfigService) ConfigFactory.getInstance().getBean("materialWarnConfigService");
	}

	// 获得原料行情预警设置DAO实例
	public MaterialWarnConfigDao getMaterialWarnConfigDao() {
		return materialWarnConfigDao;
	}

	// 设置原料行情预警设置DAO实例
	public void setMaterialWarnConfigDao(MaterialWarnConfigDao materialWarnConfigDao) {
		this.materialWarnConfigDao = materialWarnConfigDao;
	}

	// 查询
	@Override
	public List<MaterialWarnConfigJoint> listMaterialWarnConfig(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMaterialWarnConfigDao().JiontMAterialWarnCodfig(map, pageInfo);
	}

	// 查询原料行情预警方式
	@Override
	public List<Option> listWarnType() {
		return this.getMaterialWarnConfigDao().listWarnType();
	}

	// 添加
	@Override
	public void insertMaterialWarnConfig(MaterialWarnConfig materialWarnConfig) {
		this.getMaterialWarnConfigDao().insertMaterialWarnConfig(materialWarnConfig.toMap());
	}

	// 删除
	@Override
	public void deleteMaterialWarnConfig(String configCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode.split(","));
		this.getMaterialWarnConfigDao().deleteMaterialWarnConfig(map);
	}

	// 修改
	@Override
	public void updateMaterialWarnConfig(MaterialWarnConfig materialWarnConfig) {
		this.getMaterialWarnConfigDao().updateMaterialWarnConfig(materialWarnConfig.toMap());
	}

	// 加载一个原料行情预警设置
	@Override
	public MaterialWarnConfig loadMaterialWarnConfig(String configCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("configCode", configCode);
		return this.getMaterialWarnConfigDao().loadMaterialWarnConfig(map);
	}

	// 查询原料行情预警设置
	@Override
	public MaterialWarnConfig listMaterialWarnConfig(String materialCode, String warnType, BigDecimal thresholdValue) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("materialCode", materialCode);
		map.put("warnType", warnType);
		map.put("thresholdValue", thresholdValue);
		return this.getMaterialWarnConfigDao().listMAterialWarnCodfig(map);
	}
}