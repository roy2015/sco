package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.fastimportoaapplication.wlinfo;

import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo.MerchandiseWlInfo;

/**
 * 商品快速引进物料信息 DAO接口
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public interface FastImportWlInfoDao extends Dao {

	/**
	 * 查询某物料信息
	 * 
	 * @param map
	 *            查询参数
	 * @return 物料信息
	 */
	public MerchandiseWlInfo loadWlInfo(Map<String, Object> map);

	/**
	 * 删除物料信息
	 * 
	 * @param map
	 *            删除物料信息
	 * 
	 */
	public void deleteWlInfo(Map<String, Object> map);

	/**
	 * 删除审批单中商品对应sap的数据
	 * 
	 * @param map
	 *            相关参数
	 */
	public void deleteSAPData(Map<String, Object> map);

	/**
	 * 更新意向品关联表对应的数据
	 * 
	 * @param map
	 */
	public void updateISMData(Map<String, Object> map);

}