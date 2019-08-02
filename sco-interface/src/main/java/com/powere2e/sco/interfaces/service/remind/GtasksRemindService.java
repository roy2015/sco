package com.powere2e.sco.interfaces.service.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.remind.GtasksRemind;

/**
 * 待办事项提醒Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface GtasksRemindService extends Service{

	/**
	 * 待办事项提醒查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 待办事项提醒列表
	 */
	public List<GtasksRemind> listGtasksRemind(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 添加待办事项(定时任务，每小时执行一次)
	 */
	public void insertGtasksRemind();
}
