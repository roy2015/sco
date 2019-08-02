package com.powere2e.sco.dao.impl.accessoryoaapplication.nonFoodApply.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.nonFoodApply.reportanalysis.NonFoodReportAnalysisDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 非食品竞价单OA申请报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public class NonFoodReportAnalysisDaoImpl extends DaoImpl implements
		NonFoodReportAnalysisDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(NonFoodReportAnalysisDao.class,
				"listAnalysisReportSystem", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(NonFoodReportAnalysisDao.class,
				"listAnalysisReportUpload", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(NonFoodReportAnalysisDao.class,
				"listAnalysisReportPurOrder", map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(NonFoodReportAnalysisDao.class,
				"searchMCAData", map);
	}

	@Override
	public String validateAnalysisReportNonFood(Map<String, Object> map) {
		return (String) this.get(NonFoodReportAnalysisDao.class,
				"validateAnalysisReportNonFood", map);
	}

	@Override
	public void insertAnalysisUploadReport(Map<String, Object> map) {
		this.insert(NonFoodReportAnalysisDao.class,
				"insertLinkAnalysisReportUpload", map);
	}

	@Override
	public Reports loadRepeatPurOrderByName(Map<String, Object> map) {
		return (Reports) this.get(NonFoodReportAnalysisDao.class,
				"loadRepeatPurOrderByName", map);
	}

	@Override
	public void insertAnalysisUploadPurOrder(Map<String, Object> map) {
		this.insert(NonFoodReportAnalysisDao.class,
				"insertAnalysisUploadPurOrder", map);
	}

	@Override
	public void insertLinkAnalysisReportNonFood(Map<String, Object> map) {
		this.insert(NonFoodReportAnalysisDao.class,
				"insertLinkAnalysisReportNonFood", map);
	}

	@Override
	public void deleteAnalysisReportLink(Map<String, Object> map) {
		this.delete(NonFoodReportAnalysisDao.class,
				"deleteAnalysisReportLink", map);
	}

	@Override
	public void deleteAnalysisReportUpload(Map<String, Object> map) {
		this.delete(NonFoodReportAnalysisDao.class,
				"deleteAnalysisReportUpload", map);
	}

	@Override
	public void deleteAnalysisReportPurOrder(Map<String, Object> map) {
		this.delete(NonFoodReportAnalysisDao.class,
				"deleteAnalysisReportPurOrder", map);
	}

}