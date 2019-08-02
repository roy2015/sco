package com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliercomplaints;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints.SupplierComplaints;
/**
 * 供应商年度千万元客诉Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public interface SupplierComplaintsService extends Service {
	/**
	 * 供应商年度千万元客诉查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商年度千万元客诉列表
	 */
	public List<SupplierComplaints> listSupplierComplaints(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 
	 * 上传供应商年度千万元客诉表
	 * 
	 * @param file 
	 *            上传文件
	 * @return
	 *            导入消息
	 */         
	public String completeImportSupplierComplaintsData(File file,String year);
}