package com.powere2e.sco.interfaces.dao.remind;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.remind.ElseRemind;
import com.powere2e.sco.model.remind.RemindMerchandsieQL;

/**
 * 其他提醒Dao接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface ElseRemindDao extends Dao{
	
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
	 * 查询有签量提醒的商品
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 其他提醒列表
	 */
	public List<RemindMerchandsieQL> listMerchandiseHashQL(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 清空所有其他提醒
	 */
	public void deleteElseRemindByMonth();
	/**
	 * 清空按天更新的其他提醒
	 */
	public void deleteElseRemindByDay();
	/**
	 * 清除已选清除列表的其他提醒
	 */
	public void deleteElseRemind();
	/**
	 * 添加符合触发条件的证件临期提醒(定时任务)
	 */
	public void insertZJLQTXRemind(Map<String, Object> map);
	/**
	 * 添加符合触发条件的证件临期提醒(定时任务)
	 */
	public void insertZJGQTXRemind(Map<String, Object> map);
	/**
	 * 添加符合触发条件的原料价格同环比预警提醒(定时任务)
	 */
	public void insertTHBRemind();
	/**
	 * 添加符合触发条件的商品价格警提醒(定时任务)
	 */
	public void insertMerchandiseWarnRemind();
	/**
	 * 添加符合触发条件的辅料意向品价格警提醒(定时任务)
	 */
	public void insertAccessoryWarnRemind();
	/**
	 * 添加符合触发条件的签量完成提醒(定时任务)
	 */
	public void insertQLWCTXRemind(Map<String, Object> map);
	/**
	 * 添加符合触发条件的调价签量维护提醒(定时任务)
	 */
	public void insertTJQLWHTXRemind();
}
