package com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.reportanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料采购委员会竞价报表分析 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public interface CommitteeReportAnalysisService extends Service {

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
	 * 查询某申请单是否包含申购单
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @return 是否关联申购单
	 */
	public Boolean ifApplicationExistsPurOrder(String applicationCode);
	
	/**
	 * 查询魔申购单是否包含商品成本分析报表
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @return 是否关联成本分析报表
	 */
	public Boolean ifApplicationExistsMCA(String applicationCode);
	
	/**
	 * 新增关联报表
	 * 
	 * @param map
	 *            关联报表实例
	 */
	public void insertLinkAnalysisReportCommittee(Map<String, Object> map);

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

	/**
	 * 通过审批单号删除与之关联的报表信息
	 * 
	 * @param applicationCode
	 *            申请单号
	 */
	public void deleteAnalysisReportByAppCode(String applicationCode);

	/**
	 * 根据申请号查询上传的文件
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @return 改审批单号下上传的报表
	 */
	public List<Reports> listUploadByApp(String applicationCode);

	/**
	 * 根据申请号查询申购单
	 * 
	 * @param applicationCode
	 *            审批单号
	 * @return 改审批单号下的申购单
	 */
	public List<Reports> listPurOrderByApp(String applicationCode);
	
}