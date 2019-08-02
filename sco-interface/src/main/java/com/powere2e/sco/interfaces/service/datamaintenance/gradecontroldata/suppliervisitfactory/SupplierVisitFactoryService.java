package com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.suppliervisitfactory;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliervisitfactory.SupplierVisitFactory;
/**
 * 供应商年度巡厂得分Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月14日
 */
public interface SupplierVisitFactoryService extends Service {
	/**
	 * 供应商年度巡厂得分查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商年度巡厂得分列表
	 */
	public List<SupplierVisitFactory> listSupplierVisitFactory(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 
	 * 上传供应商年度巡厂得分表
	 * 
	 * @param file 
	 *            上传文件
	 * @return
	 *            导入消息
	 */         
	public String completeImportSupplierVisitFactoryData(File file);
}