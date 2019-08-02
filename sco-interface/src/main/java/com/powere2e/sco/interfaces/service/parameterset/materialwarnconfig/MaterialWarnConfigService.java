package com.powere2e.sco.interfaces.service.parameterset.materialwarnconfig;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfig;
import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfigJoint;
import com.powere2e.security.model.Option;
/**
 * 原料行情预警设置Service接口
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月8日
 */
public interface MaterialWarnConfigService extends Service {
	/**
	 * 原料行情预警设置查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回原料行情预警设置列表
	 */
	public List<MaterialWarnConfigJoint> listMaterialWarnConfig(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个原料行情预警设置
	 *
	 * @param map
	 *				
	 * @return
	 */
	public MaterialWarnConfig loadMaterialWarnConfig(String configCode);
	/**
	 * 添加原料行情预警设置
	 *
	 * @param map
	 *				
	 */
	public void insertMaterialWarnConfig(MaterialWarnConfig materialWarnConfig);
	/**
	 * 删除原料行情预警设置
	 *
	 * @param map 
	 *				必须参数id为要删除的原料行情预警设置id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMaterialWarnConfig(String configCode);
	/**
	 * 修改原料行情预警设置
	 *
	 * @param map 
	 *				必须参数id为要修改原料行情预警设置的id号，不能为数组
	 */
	public void updateMaterialWarnConfig(MaterialWarnConfig materialWarnConfig);
	/**
	 * 根据条件查询预警记录
	 * @param materialCode
	 * @param warnType
	 * @param thresholdValue
	 * @return
	 */
	public MaterialWarnConfig listMaterialWarnConfig(String materialCode,
			String warnType, BigDecimal thresholdValue);
	/**
	 * 查询原料行情预警方式
	 * @param map
	 * @return
	 */
	public List<Option> listWarnType();
}