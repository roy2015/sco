package com.powere2e.sco.interfaces.dao.accessoryintention;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryIntentionSupplier;
import com.powere2e.security.model.Option;
/**
 * 辅料意向品DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public interface AccessoryIntentionDao extends Dao {
	/**
	 * 辅料意向品查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料意向品列表
	 */
	public List<AccessoryIntention> listAccessoryIntention(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 关联供应商查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回辅料意向品列表
	 */
	public List<AccessoryIntentionSupplier> listAccessoryIntentionSupplier(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个辅料意向品
	 *
	 * @param map
	 *				
	 * @return
	 */
	public AccessoryIntention loadAccessoryIntention(Map<String,Object> map);
	/**
	 * 添加辅料意向品
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryIntention(Map<String, Object> map);
	/**
	 * 添加关联供应商
	 *
	 * @param map
	 *				
	 */
	public void insertAccessoryIntentionSupplier(Map<String, Object> map);
	/**
	 * 页面添加供应商到表
	 *
	 * @param map
	 *				
	 */
	public void insertIntentionSupplier(Map<String, Object> map);
	/**
	 * 删除辅料意向品
	 *
	 * @param map 
	 *				必须参数id为要删除的辅料意向品id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccessoryIntention(Map<String, Object> map);
	
	/**
	 * 删除关联供应商
	 *
	 * @param map 
	 *				
	 */
	public void deleteAccessoryIntentionSupplier(Map<String, Object> map);
	/**
	 * 修改辅料意向品
	 *
	 * @param map 
	 *				必须参数id为要修改辅料意向品的id号，不能为数组
	 */
	public void updateAccessoryIntention(Map<String, Object> map);
	
	/**
	 * 供应商数据查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 供应商数据列表
	 */
	public List<Option> listSupplier(Map<String, Object> map);
	
	/**
	 *辅料细分类查询
	 * 
	 * @param map
	 *            查询参数
	 * @return 供应商数据列表
	 */
	public List<Option> listMinceType(Map<String, Object> map);
	public AccessoryIntentionSupplier loadIntentionSupplier(Map<String, Object> map2);
}