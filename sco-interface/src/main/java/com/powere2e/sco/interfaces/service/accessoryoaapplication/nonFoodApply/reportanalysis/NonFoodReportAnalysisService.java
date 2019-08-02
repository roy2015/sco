package com.powere2e.sco.interfaces.service.accessoryoaapplication.nonFoodApply.reportanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.reports.Reports;

/**
 * 非食品竞价单OA申请报表分析 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public interface NonFoodReportAnalysisService extends Service {

	/**
	 * 查询关联的系统报表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询关联的上传的报表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 查询关联的申购单
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 新增关联报表
	 * 
	 * @param map
	 *            关联报表实例
	 */
	public void insertLinkAnalysisReportNonFood(Map<String, Object> map);

	/**
	 * 上传报表
	 * 
	 * @param map
	 *            报表属性
	 * @param file
	 *            上传文件
	 */
	public void completeUploadReport(Map<String, Object> map, File file);
	
	/**
	 * 上传申购单
	 * 
	 * @param map
	 *            报表属性
	 * @param file
	 *            上传文件
	 */
	public void completeUploadReportPurOrder(Map<String, Object> map, File file);
	
	/**
	 * 删除已关联的系统报表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportSystem(Map<String, Object> map);

	/**
	 * 删除已关联的上传报表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportUpload(Map<String, Object> map);

	/**
	 * 删除关联的申购单
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportPurOrder(Map<String, Object> map);

}