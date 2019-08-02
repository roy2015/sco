package com.powere2e.sco.interfaces.dao.common;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.security.model.Option;

/**
 * 原料相关属性查询 Dao接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public interface MaterialPropertiesDao extends Service {

	/**
	 * 查询原料(大)类别
	 * 
	 * @param map
	 *            查询参数
	 * @return 大类list
	 */
	public List<Option> listMaterialBigType(Map<String, Object> map);

	/**
	 * 查询原料(小)类别
	 * 
	 * @param map
	 *            查询参数
	 * @return 大类list
	 */
	public List<Option> listMaterialSmallType(Map<String, Object> map);

	/**
	 * 查询原料名称(公示网站原料名称)
	 * 
	 * @param map
	 *            查询参数
	 * @return 公示网站原料名称list
	 */
	public List<Option> listWebsiteMaterialName(Map<String, Object> map);

	/**
	 * 仓位名称下拉列表
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listWebsiteMaterialSiteName(Map<String, Object> map);

}