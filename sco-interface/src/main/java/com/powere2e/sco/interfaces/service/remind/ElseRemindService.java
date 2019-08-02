package com.powere2e.sco.interfaces.service.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.remind.ElseRemind;

/**
 * 其他提醒Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface ElseRemindService extends Service {

	/**
	 * 其他提醒查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 其他提醒列表
	 */
	public List<ElseRemind> listElseRemind(Map<String, Object> map, PageInfo pageInfo);
	/**
	 * 插入按天提醒的数据(每天执行一次)
	 */
	public void insertElseRemindByDay();
	/**
	 * 插入按月提醒的数据(每个月执行一次)
	 */
	public void insertElseRemindByMonth();
	
	
}
