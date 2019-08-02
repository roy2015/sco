package com.powere2e.sco.interfaces.dao.remind;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.remind.ElseRemindFlag;

/**
 * 其他提醒-已阅清除Dao接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public interface ElseRemindFlagDao extends Dao{
	
	
	/**
	 * 查询所有已选清除列表
	 * 
	 * @return 已选清除列表
	 */
	public List<ElseRemindFlag> listElseRemindFlag(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加其他提醒-已阅清除
	 * 
	 * @param map 参数
	 */
	public void insertElseRemindFlag(Map<String, Object> map);
}
