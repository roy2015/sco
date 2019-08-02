package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportoldupoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportanalysis.AnalysisReportOldDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 旧品新上报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportOldDaoImpl extends DaoImpl implements
		AnalysisReportOldDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportOld(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(AnalysisReportOldDao.class, "listAnalysisReportOld",
				map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(AnalysisReportOldDao.class, "searchMCAData", map);
	}

	@Override
	public String validateAnalysisReportOld(Map<String, Object> map) {
		return (String) this.get(AnalysisReportOldDao.class, "validateAnalysisReportOld", map);
	}
	
	@Override
	public void insertLinkAnalysisReportOld(Map<String, Object> map) {
		this.insert(AnalysisReportOldDao.class, "insertLinkAnalysisReportOld", map);
	}

	@Override
	public void deleteAnalysisReportOld(Map<String, Object> map) {
		this.delete(AnalysisReportOldDao.class, "deleteAnalysisReportOld", map);
	}

}