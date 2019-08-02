package com.powere2e.sco.dao.impl.accessoryoaapplication.compareApply.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.compareApply.reportanalysis.CompareReportAnalysisDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料询价单比较 报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月19日
 */
public class CompareReportAnalysisDaoImpl extends DaoImpl implements
		CompareReportAnalysisDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CompareReportAnalysisDao.class,
				"listAnalysisReportSystem", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CompareReportAnalysisDao.class,
				"listAnalysisReportUpload", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CompareReportAnalysisDao.class,
				"listAnalysisReportPurOrder", map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(CompareReportAnalysisDao.class,
				"searchMCAData", map);
	}

	@Override
	public String validateAnalysisReportCompare(Map<String, Object> map) {
		return (String) this.get(CompareReportAnalysisDao.class,
				"validateAnalysisReportCompare", map);
	}

	@Override
	public void insertAnalysisUploadReport(Map<String, Object> map) {
		this.insert(CompareReportAnalysisDao.class,
				"insertLinkAnalysisReportUpload", map);
	}

	@Override
	public Reports loadRepeatPurOrderByName(Map<String, Object> map) {
		return (Reports) this.get(CompareReportAnalysisDao.class,
				"loadRepeatPurOrderByName", map);
	}

	@Override
	public void insertAnalysisUploadPurOrder(Map<String, Object> map) {
		this.insert(CompareReportAnalysisDao.class,
				"insertAnalysisUploadPurOrder", map);
	}

	@Override
	public void insertLinkAnalysisReportCompare(Map<String, Object> map) {
		this.insert(CompareReportAnalysisDao.class,
				"insertLinkAnalysisReportCompare", map);
	}

	@Override
	public void deleteAnalysisReportLink(Map<String, Object> map) {
		this.delete(CompareReportAnalysisDao.class,
				"deleteAnalysisReportLink", map);
	}

	@Override
	public void deleteAnalysisReportUpload(Map<String, Object> map) {
		this.delete(CompareReportAnalysisDao.class,
				"deleteAnalysisReportUpload", map);
	}

	@Override
	public void deleteAnalysisReportPurOrder(Map<String, Object> map) {
		this.delete(CompareReportAnalysisDao.class,
				"deleteAnalysisReportPurOrder", map);
	}

}