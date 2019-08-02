package com.powere2e.sco.interfaces.dao.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.OaApplication;
/**
 * 商品OA申请单DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public interface OaApplicationDao extends Dao {
	/**
	 * 商品OA申请单查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品OA申请单列表
	 */
	public List<OaApplication> listOaApplication(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个商品OA申请单
	 *
	 * @param map
	 *				
	 * @return
	 */
	public OaApplication loadOaApplication(Map<String,Object> map);
	/**
	 * 添加商品OA申请单
	 *
	 * @param map
	 *				
	 */
	public void insertOaApplication(Map<String, Object> map);
	/**
	 * 删除商品OA申请单
	 *
	 * @param map 
	 *				必须参数id为要删除的商品OA申请单id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteOaApplication(Map<String, Object> map);
	/**
	 * 修改商品OA申请单
	 *
	 * @param map 
	 *				必须参数id为要修改商品OA申请单的id号，不能为数组
	 */
	public Integer updateOaApplication(Map<String, Object> map);
	/**
	 * 修改商品OA申请单
	 *
	 * @param map 
	 *				必须参数id为要修改商品OA申请单的id号，不能为数组
	 */
	public void updateOaApplicationForUndo(Map<String, Object> map);
	/**
	 * 修改商品OA申请单
	 *
	 * @param map 
	 *				必须参数id为要修改商品OA申请单的id号，不能为数组
	 */
	public void updateOaApplicationForAllow(Map<String, Object> map);
}