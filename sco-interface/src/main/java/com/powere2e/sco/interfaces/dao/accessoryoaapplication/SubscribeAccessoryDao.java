package com.powere2e.sco.interfaces.dao.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
/**
 * 申购产品信息DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public interface SubscribeAccessoryDao extends Dao {
	/**
	 * 申购产品信息查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回申购产品信息列表
	 */
	public List<SubscribeAccessory> listSubscribeAccessory(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个申购产品信息
	 *
	 * @param map
	 *				
	 * @return
	 */
	public SubscribeAccessory loadSubscribeAccessory(Map<String,Object> map);
	/**
	 * 添加申购产品信息
	 *
	 * @param map
	 *				
	 */
	public void insertSubscribeAccessory(Map<String, Object> map);
	/**
	 * 删除申购产品信息
	 *
	 * @param map 
	 *				必须参数id为要删除的申购产品信息id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSubscribeAccessory(Map<String, Object> map);
	/**
	 * 修改申购产品信息
	 *
	 * @param map 
	 *				必须参数id为要修改申购产品信息的id号，不能为数组
	 */
	public void updateSubscribeAccessory(Map<String, Object> map);
}