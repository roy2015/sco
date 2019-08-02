package com.powere2e.sco.interfaces.service.common;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.security.model.Option;

/**
 * SAP主数据中的类型及管理员维护的细分类Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public interface MasterDataTypeService extends Service {

	/**
	 * 中分类数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 中分类数据列表
	 */
	public List<Option> listCenterType(Map<String, Object> map);

	/**
	 * 小分类数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 小分类数据列表
	 */
	public List<Option> listSmallType(Map<String, Object> map);

	/**
	 * 明细类数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 明细类数据列表
	 */
	public List<Option> listDetailType(Map<String, Object> map);

	/**
	 * 细分类数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 细分类数据列表
	 */
	public List<Option> listFineType(Map<String, Object> map);
	
	/**
	 * 销售方式数据查询
	 * 
	 * @return 销售方式数据列表
	 */
	public List<Option> listSaleType();
	
	/**
	 * 区域信息数据查询
	 * 
	 * @return 区域信息数据列表
	 */
	public List<Option> listRegion();
	/**
	 * 获取sequence值
	 * @param sequenceName sequence名
	 * @return sequence值
	 */
	public String nextID(String sequenceName);

	/**
	 * 获取仓库下拉数据
	 * 
	 * @return 仓库编码、名称
	 */
	public List<Option> listWarehouseOption();

}