package com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 辅料采购委员会竞价进度信息 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月06日
 */
public interface CommitteeApplicationScheduleDao extends Dao {

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
	 * 保存进度信息
	 * 
	 * @param map
	 *            : applicationSchedulea 进度实例
	 */
	public void insertApplicationScheduleCommittee(Map<String, Object> map);

	/**
	 * 通过申请单号查询进度信息
	 * 
	 * @param map
	 *            : applicationCode 申请单号
	 * @return 进度信息
	 */
	public List<AccessoryApplicationSchedulea> listApplicationScheduleCommitteeByAppCode(Map<String, Object> map);

	/**
	 * 根据申请单号删除进度信息
	 * 
	 * @param map
	 *            : applicationCode 申请单号
	 */
	public void deleteApplicationScheduleCommitteeByAppCode(Map<String, Object> map);
	
}