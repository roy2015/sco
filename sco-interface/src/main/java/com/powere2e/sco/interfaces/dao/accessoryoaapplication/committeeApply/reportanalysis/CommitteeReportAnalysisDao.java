package com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料采购委员会竞价报表分析 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public interface CommitteeReportAnalysisDao extends Dao {

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
	 * 通过申请单号查询关联的报表
	 * 
	 * @param map
	 *            申请单号
	 * @return 是否
	 */
	public List<Reports> listLinkReportByAppCode(Map<String, Object> map);

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
	public String validateAnalysisReportCommittee(Map<String, Object> map);
	
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
	public void insertLinkAnalysisReportCommittee(Map<String, Object> map);
	
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

	/**
	 * 根据申请号查询上传的文件
	 * 
	 * @param map
	 *            applicationCode 审批单号
	 * @return 改审批单号下上传的报表
	 */
	public List<Reports> listUploadByApp(Map<String, Object> map);

	/**
	 * 根据申请号查询申购单
	 * 
	 * @param map
	 *            applicationCode 审批单号
	 * @return 改审批单号下的申购单
	 */
	public List<Reports> listPurOrderByApp(Map<String, Object> map);
	
}