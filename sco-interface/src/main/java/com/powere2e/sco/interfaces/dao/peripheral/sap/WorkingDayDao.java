package com.powere2e.sco.interfaces.dao.peripheral.sap;

import java.util.Map;

/**
 * 工作日DAO接口
 * 
 */
public interface WorkingDayDao {
	/**
	 * 添加工作日
	 *
	 * @param map
	 *
	 */
	public void insertWorkingDay(Map<String, Object> map);

	/**
	 * 删除工作日
	 */
	public void deleteWorkingDay(Map<String, Object> map);

	/**
	 * 查询两个日期间的工作日期天数
	 * 
	 * @param map 
	 * 		<li>startDate 开始日期</li>
	 * 		<li>endDate 结束日期</li>
	 * @return 之间的工作日天数
	 */
	public int searchWeekDayByDate(Map<String, Object> map);
}
