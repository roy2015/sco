package com.powere2e.sco.interfaces.dao.datamaintenance.gradecontroldata.suppliercomplaints;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaints;
/**
 * 供应商年度千万元客诉DAO接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public interface SupplierComplaintsDao extends Dao {
	/**
	 * 供应商年度千万元客诉查询
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商年度千万元客诉列表
	 */
	public List<SupplierComplaints> listSupplierComplaints(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 添加供应商年度千万元客诉
	 *
	 * @param map
	 *				
	 */
	public void insertSupplierComplaints(Map<String, Object> map);
	/**
	 * 修改供应商年度千万元客诉
	 *
	 * @param map 
	 *				必须参数id为要修改供应商年度千万元客诉的id号，不能为数组
	 */
	public void updateSupplierComplaints(Map<String, Object> map);
}