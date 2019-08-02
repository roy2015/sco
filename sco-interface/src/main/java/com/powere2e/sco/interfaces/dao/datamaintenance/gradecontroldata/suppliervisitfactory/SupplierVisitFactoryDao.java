package com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliervisitfactory;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;
/**
 * 供应商年度巡厂得分DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public interface SupplierVisitFactoryDao extends Dao {
	/**
	 * 供应商年度巡厂得分查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商年度巡厂得分列表
	 */
	public List<SupplierVisitFactory> listSupplierVisitFactory(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加供应商年度巡厂得分
	 *
	 * @param map
	 *				
	 */
	public void insertSupplierVisitFactory(Map<String, Object> map);
	/**
	 * 修改供应商年度巡厂得分
	 *
	 * @param map 
	 *				必须参数id为要修改供应商年度巡厂得分的id号，不能为数组
	 */
	public void updateSupplierVisitFactory(Map<String, Object> map);
}