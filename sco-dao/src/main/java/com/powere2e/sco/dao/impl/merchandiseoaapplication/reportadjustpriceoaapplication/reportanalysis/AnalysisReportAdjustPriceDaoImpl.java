package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis.AnalysisReportAdjustPriceDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 商品正常调价报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportAdjustPriceDaoImpl extends DaoImpl implements
		AnalysisReportAdjustPriceDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportAdjustPrice(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(AnalysisReportAdjustPriceDao.class, "listAnalysisReportAdjustPrice",
				map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(AnalysisReportAdjustPriceDao.class, "searchMCAData", map);
	}
	
	@Override
	public String validateAnalysisReportAdjustPrice(Map<String, Object> map) {
		return (String) this.get(AnalysisReportAdjustPriceDao.class, "validateAnalysisReportAdjustPrice", map);
	}

	@Override
	public void insertLinkAnalysisReportAdjustPrice(Map<String, Object> map) {
		this.insert(AnalysisReportAdjustPriceDao.class, "insertLinkAnalysisReportAdjustPrice", map);
	}

	@Override
	public void deleteAnalysisReportAdjustPrice(Map<String, Object> map) {
		this.delete(AnalysisReportAdjustPriceDao.class, "deleteAnalysisReportAdjustPrice", map);
	}

}