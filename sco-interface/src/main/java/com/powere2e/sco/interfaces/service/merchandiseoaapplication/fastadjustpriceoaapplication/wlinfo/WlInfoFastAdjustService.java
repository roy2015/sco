package com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.wlinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 快速调价  物料信息Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月21日
 */
public interface WlInfoFastAdjustService extends Service {

	/**
	 * 查询物料信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<MerchandiseWlInfo> listWlInfoFastAdjust(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询物料读取价格信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<MerchandiseWlInfo> listWlInfoDetail(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 验证所选地区是否已被关联
	 * 
	 * @param map
	 *            查询条件
	 */
	public Boolean ifWlInfoExists(Map<String, Object> map);
	
	/**
	 * 新增物料信息
	 * 
	 * @param wlInfo
	 *            物料信息
	 * @param applicationCode
	 *            申请号
	 * @param intentionAndSupplierCodes
	 *            意向品、供应商
	 */
	public void insertWlInfoFastAdjust(MerchandiseWlInfo wlInfo,
			String applicationCode, String intentionAndSupplierCodes);
	
	/**
	 * 更新物料信息
	 * 
	 * @param wlInfo
	 *            物料信息
	 * @param oldRegion
	 *            原来的区域
	 * @param applicationCode
	 *            申请号
	 * @param intentionAndSupplierCodes
	 *            意向品、供应商
	 */
	public void updateWlInfoFastAdjust(MerchandiseWlInfo wlInfo,
			String oldRegion, String applicationCode, String intentionAndSupplierCodes);
	
	/**
	 * 删除物料信息
	 * 
	 * @param map 过滤条件
	 */
	public void deleteWlInfoFastAdjust(MerchandiseWlInfo[] dataArray,
			String applicationCode, String intentionAndSupplierCodes);

	/**
	 * 查询某个物料信息
	 * 
	 * @param map
	 *            查询条件
	 * @return 返回某个物料信息
	 */
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map);

}