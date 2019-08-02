package com.powere2e.sco.interfaces.dao.remind;

import java.util.List;
import java.util.Map;
import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.remind.GtasksRemind;

/**
 * 待办事项提醒Dao接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface GtasksRemindDao extends Dao{
	
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
	 * 清空待办事项(定时任务，每隔一小时)
	 */
	public void deleteGtasksRemind();
	/**
	 * 把待办的大货信息插入到待办事项表里(定时任务，每隔一小时)
	 */
	public void insertGtasksRemindDHInfo();
	/**
	 * 把待办的新品引进、老品新上、正常调价的商品OA信息插入到待办事项表里(定时任务，每隔一小时)
	 */
	public void insertGtasksRemindMerchandiseInfo();
	/**
	 * 把待办的快速调价的商品OA信息插入到待办事项表里(定时任务，每隔一小时)
	 */
	public void insertGtasksRemindMerchandiseFastadjustpriceInfo();
	/**
	 * 把待办的辅料OA信息插入到待办事项表里 (定时任务，每隔一小时)
	 */
	public void insertGtasksRemindAccessoryInfo();
	/**
	 *把关联商品原料与公示网站原料”模块中“是否已关联公示网站原料”状态为空的原料所属的商品编号对应的审批通过日期最晚的SCO申请单信息插入到待办事项表里 (定时任务，每隔一小时)
	 */
	public void insertIsNotWebSiteInfo();
	
	
}
