package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 新品引进物料信息 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月15日
 */
public interface WlInfoNewDao extends Dao {

	/**
	 * 物料信息列表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 进度信息list
	 */
	public List<MerchandiseWlInfo> listWlInfoNew(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 更新物料号维护
	 * 
	 * @param map
	 *            SAP的供应商、物料号
	 */
	public void insertIntentionMerchandiseSap(Map<String, Object> map);

	/**
	 * 更新商品合同价
	 * 
	 * @param map 价格、价格地区
	 */
	public void insertMerchandiseContractPrice(Map<String, Object> map);

	/**
	 * 维护意向品供应商关联表
	 * 
	 * @param map
	 *            相关关联信息
	 */
	public void updateIntentionSupplierMerchandise(Map<String, Object> map);
	
}