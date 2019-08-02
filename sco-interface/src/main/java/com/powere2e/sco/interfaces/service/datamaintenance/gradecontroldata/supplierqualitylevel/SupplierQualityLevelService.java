package com.powere2e.sco.interfaces.service.datamaintenance.gradecontroldata.supplierqualitylevel;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel.SupplierQualityLevel;
/**
 * 供应商质量星级Service接口
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public interface SupplierQualityLevelService extends Service {
	/**
	 * 供应商质量星级查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回供应商质量星级列表
	 */
	public List<SupplierQualityLevel> listSupplierQualityLevel(Map<String, Object> map,PageInfo pageInfo);
	/**
	 * 
	 * 上传质量星级表
	 * 
	 * @param file 
	 *            上传文件
	 * @return
	 *            导入消息
	 */         
	public String completeImportSupplierQualityLevelData(File file,String year);
}