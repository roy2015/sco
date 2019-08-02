package com.powere2e.sco.interfaces.dao.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
/**
 * 询价单DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public interface AccessoryEnquiryDao extends Dao {
	/**
	 * 询价单查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回询价单列表
	 */
	public List<AccessoryEnquiry> listAccessoryEnquiry(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个询价单
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryEnquiry loadAccessoryEnquiry(Map<String,Object> map);
	/**
	 * 添加询价单
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryEnquiry(Map<String, Object> map);
	/**
	 * 删除询价单
	 *
	 * @param map 
	 *				必须参数id为要删除的询价单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryEnquiry(Map<String, Object> map);
	/**
	 * 修改询价单
	 *
	 * @param map 
	 *				必须参数id为要修改询价单的id号，不能为数组
	 */
	public void updateAccessoryEnquiry(Map<String, Object> map);
}