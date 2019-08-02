package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.reports.Reports;

/**
 * 商品正常调价报表分析 DAO接口
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public interface AnalysisReportAdjustPriceDao extends Dao {

	/**
	 * 查询关联列表
	 * 
	 * @param map
	 *            查询条件
	 * @param pageInfo
	 *            分页参数
	 * @return 关联报表list
	 */
	public List<Reports> listAnalysisReportAdjustPrice(Map<String, Object> map,
			PageInfo pageInfo);

	/**
	 * 当关联的报表为成本分析报表时,用于查询已关联的成本分析报表
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
	public String validateAnalysisReportAdjustPrice(Map<String, Object> map);
	
	/**
	 * 添加关联报表
	 * 
	 * @param map 关联报表实例
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