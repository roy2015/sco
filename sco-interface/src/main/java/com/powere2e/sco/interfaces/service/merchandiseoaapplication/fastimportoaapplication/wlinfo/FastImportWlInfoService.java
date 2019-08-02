package com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastimportoaapplication.wlinfo;

import java.util.Map;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 快速引入物料信息 Service接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月19日
 * @version 1.0
 */
public interface FastImportWlInfoService extends Service {

	/**
	 * 新增物料信息
	 * 
	 * @param wlInfoFastAdjust
	 *            物料实例
	 * @param applicationCode
	 *            审批单号
	 * @param intentionAndSupplierCodes
	 *            意向供编号+意向品应商
	 */
	public void insertWlInfoFastImport(MerchandiseWlInfo wlInfoFastAdjust,
			String applicationCode, String intentionAndSupplierCodes);

	/**
	 * 查询某条物料信息数据
	 * 
	 * @param map
	 *            查询参数
	 * @return 物料信息
	 */
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map);

	/**
	 * 删除物料信息
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @param intSup
	 *            意向品编号+供应商编号
	 * @param intSupReg
	 *            意向品编号+供应商编号+地区
	 * @param intentionAndSupplierCodes
	 *            意向品+供应商
	 */
	public void completeDeleteWlInfo(String applicationCode, String intSup,
			String intSupReg, String intentionAndSupplierCodes);

	/**
	 * 更新物料信息
	 * 
	 * @param wlInfoFastAdjust
	 *            物料实例
	 * @param oldRegion
	 *            更改前的地区编号
	 * @param applicationCode
	 *            审批单号
	 * @param intentionAndSupplierCodes
	 *            意向品+意向供应商编号
	 */
	public void updateWlInfoFastAdjust(MerchandiseWlInfo wlInfoFastAdjust,
			String oldRegion, String applicationCode,
			String intentionAndSupplierCodes);

}