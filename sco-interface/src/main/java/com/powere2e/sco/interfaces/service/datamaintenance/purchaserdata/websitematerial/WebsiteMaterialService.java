package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.websitematerial;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.websitematerial.WebsiteMaterial;
import com.powere2e.security.model.Option;

/**
 * 公示网站原料数据维护 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public interface WebsiteMaterialService extends Service {

	/**
	 * 查询已有网站价格数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 已有网站价格数据list
	 */
	public List<WebsiteMaterial> listExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询缺少网站价格数据
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 缺少价格数据list
	 */
	public List<WebsiteMaterial> listNotExistsWebsiteMaterial(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 通过物料编码查询物料及网站信息
	 * 
	 * @param map 
	 * 			:[materialCode:物料编码   priceRegionCode:价格地区编码 ]
	 * @return 对应物料信息
	 */
	public WebsiteMaterial loadWebsiteMaterialByMateCode(Map<String, Object> map);

	/**
	 * 录入网站价格数据
	 * 
	 * @param websiteMaterial
	 *            网站价格实例
	 */
	public void insertWebMaterialPrice(WebsiteMaterial websiteMaterial);

	/**
	 * 删除网站价格数据
	 * 
	 * @param map
	 *            相关过滤条件
	 */
	public void deleteWebsiteMaterialPrice(Map<String, Object> map);

	/**
	 * 查询公示网站原料名称下拉框
	 * 
	 * @param map
	 *            查询参数
	 * @return 原料ID、名称
	 */
	public List<Option> listMaterialNameOption(Map<String, Object> map);

	/**
	 * 查询价格区域下拉框
	 * 
	 * @param map
	 *            查询条件
	 * @return 价格区域ID、名称
	 */
	public List<Option> listMaterialRegionOption(Map<String, Object> map);

	/**
	 * 查询网站名称下拉框
	 * 
	 * @param map
	 *            查询条件
	 * @return 网站名称ID、名称
	 */
	public List<Option> listMaterialWebNameOption(Map<String, Object> map);

	/**
	 * 查询网站地址下拉框
	 * 
	 * @param map
	 *            查询条件
	 * @return 网站地址ID、地址
	 */
	public List<Option> listMaterialWebUrlOption(Map<String, Object> map);

}