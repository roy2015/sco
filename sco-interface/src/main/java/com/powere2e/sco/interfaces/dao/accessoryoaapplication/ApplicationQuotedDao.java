package com.powere2e.sco.interfaces.dao.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.ApplicationQuoted;
/**
 * 报价单DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月30日
 */
public interface ApplicationQuotedDao extends Dao {
	/**
	 * 报价单查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回报价单列表
	 */
	public List<ApplicationQuoted> listApplicationQuoted(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 报价单查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回报价单列表
	 */
	public List<ApplicationQuoted> listApplicationQuotedForDhInfo(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个报价单
	 *
	 * @param map
	 *				
	 * @return
	 */
	public ApplicationQuoted loadApplicationQuoted(Map<String,Object> map);
	/**
	 * 添加报价单
	 *
	 * @param map
	 *				
	 */
	public void insertApplicationQuoted(Map<String, Object> map);
	/**
	 * 删除报价单
	 *
	 * @param map 
	 *				必须参数id为要删除的报价单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationQuoted(Map<String, Object> map);
	/**
	 * 修改报价单
	 *
	 * @param map 
	 *				必须参数id为要修改报价单的id号，不能为数组
	 */
	public void updateApplicationQuoted(Map<String, Object> map);
}