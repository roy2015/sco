package com.powere2e.sco.interfaces.dao.accessoryoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.accessoryoaapplication.WlInfo;
/**
 * 物料信息DAO接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年5月13日
 */
public interface WlInfoDao extends Dao {
	/**
	 * 物料信息查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回物料信息列表
	 */
	public List<WlInfo> listWlInfo(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 根据ID号加载一个物料信息
	 *
	 * @param map
	 *				
	 * @return
	 */
	public WlInfo loadWlInfo(Map<String,Object> map);
	/**
	 * 添加物料信息
	 *
	 * @param map
	 *				
	 */
	public void insertWlInfo(Map<String, Object> map);
	/**
	 * 删除物料信息
	 *
	 * @param map 
	 *				必须参数id为要删除的物料信息id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteWlInfo(Map<String, Object> map);
	/**
	 * 修改物料信息
	 *
	 * @param map 
	 *				必须参数id为要修改物料信息的id号，不能为数组
	 */
	public void updateWlInfo(Map<String, Object> map);
}