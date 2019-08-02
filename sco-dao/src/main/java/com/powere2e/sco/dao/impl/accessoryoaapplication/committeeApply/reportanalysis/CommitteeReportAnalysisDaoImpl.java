package com.powere2e.sco.dao.impl.accessoryoaapplication.committeeApply.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料采购委员会竞价报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public class CommitteeReportAnalysisDaoImpl extends DaoImpl implements
		CommitteeReportAnalysisDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportSystem(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CommitteeReportAnalysisDao.class,
				"listAnalysisReportSystem", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportUpload(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CommitteeReportAnalysisDao.class,
				"listAnalysisReportUpload", map, pageInfo);
	}

	@Override
	public List<Reports> listAnalysisReportPurOrder(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(CommitteeReportAnalysisDao.class,
				"listAnalysisReportPurOrder", map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(CommitteeReportAnalysisDao.class,
				"searchMCAData", map);
	}

	@Override
	public String validateAnalysisReportCommittee(Map<String, Object> map) {
		return (String) this.get(CommitteeReportAnalysisDao.class,
				"validateAnalysisReportCommittee", map);
	}

	@Override
	public void insertAnalysisUploadReport(Map<String, Object> map) {
		this.insert(CommitteeReportAnalysisDao.class,
				"insertLinkAnalysisReportUpload", map);
	}
	
	@Override
	public Reports loadRepeatPurOrderByName(Map<String, Object> map) {
		return (Reports) this.get(CommitteeReportAnalysisDao.class,
				"loadRepeatPurOrderByName", map);
	}
	
	@Override
	public List<Reports> listLinkReportByAppCode(Map<String, Object> map) {
		return this.query(CommitteeReportAnalysisDao.class,
				"listLinkReportByAppCode", map, null);
	}
	
	@Override
	public void insertAnalysisUploadPurOrder(Map<String, Object> map) {
		this.insert(CommitteeReportAnalysisDao.class,
				"insertAnalysisUploadPurOrder", map);
	}
	
	@Override
	public void insertLinkAnalysisReportCommittee(Map<String, Object> map) {
		this.insert(CommitteeReportAnalysisDao.class,
				"insertLinkAnalysisReportCommittee", map);
	}

	@Override
	public void deleteAnalysisReportLink(Map<String, Object> map) {
		this.delete(CommitteeReportAnalysisDao.class,
				"deleteAnalysisReportLink", map);
	}

	@Override
	public void deleteAnalysisReportUpload(Map<String, Object> map) {
		this.delete(CommitteeReportAnalysisDao.class,
				"deleteAnalysisReportUpload", map);
	}

	@Override
	public void deleteAnalysisReportPurOrder(Map<String, Object> map) {
		this.delete(CommitteeReportAnalysisDao.class,
				"deleteAnalysisReportPurOrder", map);
	}

	@Override
	public List<Reports> listUploadByApp(Map<String, Object> map) {
		return this.query(CommitteeReportAnalysisDao.class, "listUploadByApp",
				map, null);
	}

	@Override
	public List<Reports> listPurOrderByApp(Map<String, Object> map) {
		return this.query(CommitteeReportAnalysisDao.class,
				"listPurOrderByApp", map, null);
	}

}