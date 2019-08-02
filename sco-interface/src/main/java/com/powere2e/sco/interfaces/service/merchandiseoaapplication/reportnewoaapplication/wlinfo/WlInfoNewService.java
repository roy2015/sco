package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 新品引进  物料信息Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月15日
 */
public interface WlInfoNewService extends Service {

	/**
	 * 查询物料信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<MerchandiseWlInfo> listWlInfoNew(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 保存物料信息
	 * 
	 * @param wlInfoList
	 *            物料信息
	 * @param applicationCode
	 *            申请号
	 * @param intentionAndSupplierCodes
	 *            意向品、供应商
	 */
	public void insertWlInfoNew(List<MerchandiseWlInfo> wlInfoList,
			String applicationCode, String intentionAndSupplierCodes);

	/**
	 * 维护意向品供应商关联表
	 * 
	 * @param map
	 *            相关关联信息
	 */
	public void updateIntentionSupplierMerchandise(Map<String, Object> map);

}