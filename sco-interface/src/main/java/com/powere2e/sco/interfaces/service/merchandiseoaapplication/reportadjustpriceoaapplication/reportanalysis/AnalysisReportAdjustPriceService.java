package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.reports.Reports;

/**
 * 商品正常调价报表分析 Service接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public interface AnalysisReportAdjustPriceService extends Service {

	/**
	 * 商品正常调价报表分析列表
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 */
	public List<Reports> listAnalysisReportAdjustPrice(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 新增关联报表
	 * 
	 * @param map
	 *            关联报表实例
	 */
	public void insertLinkAnalysisReportAdjustPrice(Map<String, Object> map);

	/**
	 * 删除已关联报表
	 * 
	 * @param map
	 *            删除条件
	 */
	public void deleteAnalysisReportAdjustPrice(Map<String, Object> map);

}