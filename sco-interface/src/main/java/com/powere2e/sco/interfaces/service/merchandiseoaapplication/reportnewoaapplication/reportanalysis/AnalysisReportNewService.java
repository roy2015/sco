package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.reports.Reports;

/**
 * 新品引进报表分析 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public interface AnalysisReportNewService extends Service {

	/**
	 * 新品引进报表分析列表
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 */
	public List<Reports> listAnalysisReportNew(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 新增关联报表
	 * 
	 * @param map
	 *            关联报表实例
	 */
	public void insertLinkAnalysisReportNew(Map<String, Object> map);

	/**
	 * 删除已关联报表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportNew(Map<String, Object> map);

	/**
	 * 通过审批单号删除与之关联的报表信息
	 * 
	 * @param applicationCode
	 *            审批单号
	 */
	public void deleteAnalysisReportByAppCode(String applicationCode);

	/**
	 * 查询未管理成本分析的申请单及其关联的商品
	 * 
	 * @param applicationCode
	 *            申请单号
	 * @return 未关联的申请单及关联的商品
	 */
	public List<ApplicationLackFileM> listNoLinkMCAReportApplication(String applicationCode);
	
}