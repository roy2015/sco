package com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.supplierqualitylevel;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevel;
/**
 * 供应商质量星级DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public interface SupplierQualityLevelDao extends Dao {
	/**
	 * 供应商质量星级查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商质量星级列表
	 */
	public List<SupplierQualityLevel> listSupplierQualityLevel(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加供应商质量星级
	 *
	 * @param map
	 *				
	 */
	public void insertSupplierQualityLevel(Map<String, Object> map);
	/**
	 * 修改供应商质量星级
	 *
	 * @param map 
	 *				必须参数id为要修改供应商质量星级的id号，不能为数组
	 */
	public void updateSupplierQualityLevel(Map<String, Object> map);
}