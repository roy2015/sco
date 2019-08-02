package com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 辅料采购委员会竞价进度信息 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月6日
 */
public interface CommitteeApplicationScheduleService extends Service {

	/**
	 * 进度信息列表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 进度信息list
	 */
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommittee(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 通过申请单号查询进度信息
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @return 该审批单号对应的进度信息
	 */
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommitteeByAppCode(String applicationCode);
	
	/**
	 * 根据申请单号删除进度信息
	 * 
	 * @param applicationCode
	 *            审批单号
	 */
	public void deleteApplicationScheduleCommitteeByAppCode(String applicationCode);
	
	/**
	 * 保存进度信息
	 * 
	 * @param map
	 *            相关参数
	 * @param dataArray
	 *            进度实例
	 */
	public void insertApplicationScheduleCommittee(
			Map<String, Object> map, AccessoryApplicationSchedulea[] dataArray);
}