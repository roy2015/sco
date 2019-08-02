package com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.applicationschedule;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule.AccessoryApplicationSchedulea;

/**
 * 非食品竞价单OA申请 进度信息 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月6日
 */
public interface NonFoodApplicationScheduleService extends Service {

	/**
	 * 进度信息列表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 进度信息list
	 */
	public List<AccessoryApplicationSchedulea> listApplicationScheduleNonFood(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 保存进度信息
	 * 
	 * @param map
	 *            相关参数
	 * @param dataArray
	 *            进度实例
	 */
	public void insertApplicationScheduleNonFood(
			Map<String, Object> map, AccessoryApplicationSchedulea[] dataArray);
}