package com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.merchandiserecycle;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.merchandiserecycle.MerchandiseRecycle;
/**
 * 商品回收记录DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public interface MerchandiseRecycleDao extends Dao {
	/**
	 * 商品回收记录查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回商品回收记录列表
	 */
	public List<MerchandiseRecycle> listMerchandiseRecycle(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加商品回收记录
	 *
	 * @param map
	 *				
	 */
	public void insertMerchandiseRecycle(Map<String, Object> map);
	/**
	 * 修改商品回收记录
	 *
	 * @param map 
	 *				必须参数id为要修改商品回收记录的id号，不能为数组
	 */
	public void updateMerchandiseRecycle(Map<String, Object> map);
}