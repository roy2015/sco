package com.powere2e.sco.interfaces.dao.masterdata;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.masterdata.Supplier;
/**
 * 供应商DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public interface SupplierDao extends Dao {
	/**
	 * 供应商查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商列表
	 */
	public List<Supplier> listSupplier(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 供应商编码查询
	 *
	 * @return 返回供应商编码列表
	 */
	public List<String> listSupplierCode(Map<String, Object> map);
	/**
	 * 根据ID号加载一个供应商
	 *
	 * @param map
	 *				
	 * @return
	 */
	public Supplier loadSupplier(Map<String,Object> map);
	/**
	 * 根据ID号加载一个意向供应商
	 *
	 * @param map
	 *				
	 * @return
	 */
	public Supplier loadIntentionSupplier(Map<String,Object> map);
	/**
	 * 添加供应商
	 *
	 * @param map
	 *				
	 */
	public void insertSupplier(Map<String, Object> map);
	/**
	 * 删除供应商
	 *
	 * @param map 
	 *				必须参数id为要删除的供应商id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSupplier(Map<String, Object> map);
	/**
	 * 修改供应商
	 *
	 * @param map 
	 *				必须参数id为要修改供应商的id号，不能为数组
	 */
	public void updateSupplier(Map<String, Object> map);
}