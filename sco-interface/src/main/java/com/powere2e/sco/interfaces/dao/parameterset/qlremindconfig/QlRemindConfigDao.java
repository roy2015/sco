package com.powere2e.sco.interfaces.dao.parameterset.qlremindconfig;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.parameterset.qlremindconfig.QlRemindConfig;
/**
 * 签量提醒设置DAO接口
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public interface QlRemindConfigDao extends Dao {
	/**
	 * 签量提醒设置查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回签量提醒设置列表
	 */
	public List<QlRemindConfig> listQlRemindConfig(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个签量提醒设置
	 *
	 * @param map
	 *				
	 * @return
	 */
	public QlRemindConfig loadQlRemindConfig(Map<String,Object> map);
	/**
	 * 添加签量提醒设置
	 *
	 * @param map
	 *				
	 */
	public void insertQlRemindConfig(Map<String, Object> map);
	/**
	 * 删除签量提醒设置
	 *
	 * @param map 
	 *				必须参数id为要删除的签量提醒设置id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteQlRemindConfig(Map<String, Object> map);
	/**
	 * 修改签量提醒设置
	 *
	 * @param map 
	 *				必须参数id为要修改签量提醒设置的id号，不能为数组
	 */
	public void updateQlRemindConfig(Map<String, Object> map);
}