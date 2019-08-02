package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportnewoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewDao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.reports.Reports;

/**
 * 新品引进报表分析 DAO接口的实现
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月20日
 */
public class AnalysisReportNewDaoImpl extends DaoImpl implements
		AnalysisReportNewDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2964929357101553003L;

	@Override
	public List<Reports> listAnalysisReportNew(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(AnalysisReportNewDao.class, "listAnalysisReportNew",
				map, pageInfo);
	}

	@Override
	public String searchMCAData(Map<String, Object> map) {
		return (String) this.get(AnalysisReportNewDao.class, "searchMCAData", map);
	}
	
	@Override
	public String validateAnalysisReportNew(Map<String, Object> map) {
		return (String) this.get(AnalysisReportNewDao.class, "validateAnalysisReportNew", map);
	}

	@Override
	public void insertLinkAnalysisReportNew(Map<String, Object> map) {
		this.insert(AnalysisReportNewDao.class, "insertLinkAnalysisReportNew", map);
	}

	@Override
	public void deleteAnalysisReportNew(Map<String, Object> map) {
		this.delete(AnalysisReportNewDao.class, "deleteAnalysisReportNew", map);
	}

	@Override
	public List<ApplicationLackFileM> listNoLinkReportApplication(
			Map<String, Object> map) {
		return this.query(AnalysisReportNewDao.class, "listNoLinkReportApplication", map, null);
	}

}