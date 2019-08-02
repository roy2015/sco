package com.powere2e.sco.interfaces.dao.accessoryoaapplication.compareApply.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料询价单比较 报表分析 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月19日
 */
public interface CompareReportAnalysisDao extends Dao {

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
	 * 当关联的报表为成本分析报表时,用于 查询已关联的成本分析报表
	 * 
	 * @param map
	 *            查询条件
	 * @return 已关联的成本分析报表code
	 */
	public String searchMCAData(Map<String, Object> map);

	/**
	 * 校验是否已被其他申请关联
	 * 
	 * @param map
	 *            相关参数
	 * @return 核算表编号
	 */
	public String validateAnalysisReportCompare(Map<String, Object> map);
	
	/**
	 * 保存上传报表
	 * 
	 * @param map
	 *            报表实例
	 */
	public void insertAnalysisUploadReport(Map<String, Object> map);
	
	/**
	 * 保存上传的申购单
	 * 
	 * @param map
	 *            报表实例
	 */
	public void insertAnalysisUploadPurOrder(Map<String, Object> map);
	
	/**
	 * 根据申购单名称查询重复的数据
	 * 
	 * @param map
	 *            查询条件
	 * @return 数据实例
	 */
	public Reports loadRepeatPurOrderByName(Map<String, Object> map);
	
	/**
	 * 添加关联报表
	 * 
	 * @param map 关联报表实例
	 */
	public void insertLinkAnalysisReportCompare(Map<String, Object> map);
	
	/**
	 * 删除关联表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportLink(Map<String, Object> map);

	/**
	 * 删除已关联的上传报表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportUpload(Map<String, Object> map);
	
	/**
	 * 删除已关联的申购单
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportPurOrder(Map<String, Object> map);

}