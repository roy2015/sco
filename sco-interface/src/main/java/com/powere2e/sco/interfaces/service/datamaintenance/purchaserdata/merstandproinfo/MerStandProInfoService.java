package com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.merstandproinfo;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.ApplicationScheduleM2;
import com.powere2e.sco.model.datamaintenance.purchaserdata.merStandProInfo.MerStandProInfo;

/**
 * 商品引进标准进度信息维护 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年09月28日
 */
public interface MerStandProInfoService extends Service {

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
	 * 新增标准进度信息
	 * 
	 * @param merStandProInfo
	 *            实例对象
	 */
	public void insertMerStandProInfo(MerStandProInfo merStandProInfo);
	
	/**
	 * 修改标准进度信息
	 * 
	 * @param merStandProInfo
	 *            标准信息实例对象
	 */
	public void updateMerStandProInfo(MerStandProInfo merStandProInfo);

	/**
	 * 删除标准进度信息
	 * 
	 * @param merStandProInfo
	 *            相关参数
	 */
	public void deleteMerStandProInfo(MerStandProInfo merStandProInfo);

	/**
	 * 是否存在
	 * 
	 * @param merStandProInfo
	 *            相关参数
	 * @return 返回存在数据的信息
	 */
	public Boolean ifMerStandProInfoExists(MerStandProInfo merStandProInfo);

	/**
	 * 根据审批号和商品信息查询信息
	 * 
	 * @param oaApplicationCode
	 *            SCO申请单号
	 * @param intentionCode
	 *            SCO意向品编号
	 * @param supplierCode
	 *            SCO供应商编号
	 * @return 审批进度信息
	 */
	public ApplicationScheduleM2 searchAppByCodeAndMerchandise(
			String oaApplicationCode, String intentionCode, String supplierCode);

	/**
	 * 根据意向品创建日期查询生效日期最近的标准进度数据
	 * 
	 * @param appSchedule
	 *            意向品相关信息
	 * @return 标准进度信息
	 */
	public MerStandProInfo searchMerStandProInfoByCreated(ApplicationScheduleM2 appSchedule);

	/**
	 * 更新/记录审批单进度信息
	 * 
	 * @param appSchedule
	 *            进度信息
	 */
	public void completeAppSchedule(ApplicationScheduleM2 appSchedule);

	/**
	 * 根据条件查询商品信息
	 * 
	 * @param map
	 *            查询条件
	 * @return 审批进度信息
	 */
	public List<ApplicationScheduleM2> searchAppByCodeAndMerchandise(Map<String, Object> map);

}