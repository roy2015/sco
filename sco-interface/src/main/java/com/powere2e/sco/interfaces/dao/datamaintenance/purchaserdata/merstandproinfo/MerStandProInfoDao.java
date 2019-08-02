package com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.merstandproinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.ApplicationScheduleM2;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;

/**
 * 商品引进标准进度信息维护 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public interface MerStandProInfoDao extends Dao {

	/**
	 * 查询标准进度信息
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 标准进度信息list
	 */
	public List<MerStandProInfo> listMerMerStandProInfo(
			Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 新增不准信息数据
	 * 
	 * @param map
	 *            : <li>merStandProInfo 实例数据</li>
	 */
	public void insertMerStandProInfo(Map<String, Object> map);

	/**
	 * 修改标准进度信息
	 * 
	 * @param map
	 *            : <li>merStandProInfo 相关参数</li>
	 */
	public void updateMerStandProInfo(Map<String, Object> map);

	/**
	 * 删除标准进度信息
	 * 
	 * @param map
	 *            : <li>merStandProInfo 相关参数</li>
	 */
	public void deleteMerStandProInfo(Map<String, Object> map);

	/**
	 * 生效日期是否存在
	 * 
	 * @param map
	 *            : <li>merStandProInfo 相关参数</li>
	 * @return 返回存在数据的信息
	 */
	public String ifMerStandProInfoExists(Map<String, Object> map);

	/**
	 * 根据审批号和商品信息查询信息
	 * 
	 * @param map 查询参数 
	 * 		<li>oaApplicationCode SCO申请单号</li>
	 *      <li>intentionCode SCO意向品编号</li>
	 * 		<li>supplierCode SCO供应商编号</li>
	 * @return 审批进度信息
	 */
	public ApplicationScheduleM2 searchAppByCodeAndMerchandise(Map<String, Object> map);

	/**
	 * 根据意向品创建日期查询生效日期最近的标准进度数据
	 * 
	 * @param map
	 * 		<li>appSchedule 意向品相关信息</li>
	 * @return 标准进度信息
	 */
	public MerStandProInfo searchMerStandProInfoByCreated(
			Map<String, Object> map);

	/**
	 * 更新/记录审批单进度信息
	 * 
	 * @param map 
	 * 			appSchedule 进度信息
	 */
	public void completeAppSchedule(Map<String, Object> map);

	/**
	 * 根据条件查询审批商品信息
	 * 
	 * @param map
	 *            查询条件
	 * @return 审批进度信息
	 */
	public List<ApplicationScheduleM2> listAppByCodeAndMerchandise(Map<String, Object> map);
	
}