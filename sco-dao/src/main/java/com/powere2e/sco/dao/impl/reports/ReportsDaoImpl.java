package com.powere2e.sco.dao.impl.reports;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.reports.ReportsDao;
import com.powere2e.sco.model.reports.Reports;

/**
 * 我的报表DAO接口的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public class ReportsDaoImpl extends DaoImpl implements ReportsDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7359413065310645915L;
	//查询
	@Override
	public List<Reports> listReports(Map<String, Object> map,PageInfo pageInfo){
		return this.query(ReportsDao.class, "searchReports", map,pageInfo);
	}
	@Override
	public List<Reports> listReportsByParams(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.query(ReportsDao.class, "listReportsByParams", map,pageInfo);
	}
	//添加
	@Override
	public void insertReports(Map<String, Object> map){
		this.insert(ReportsDao.class, "saveReports", map);
	}
	//成本分析-商品
	@Override
	public void insertReportAnalysis(Map<String, Object> map) {
		this.insert(ReportsDao.class, "insertReportAnalysis", map);
	}
	//判断报表名称是否已经存在
	@Override
	public String ifFileNameExists(Map<String, Object> map) {
		return (String) this.get(ReportsDao.class, "ifFileNameExists", map);
	}
	//删除
	@Override
	public void deleteReports(Map<String, Object> map){
		this.delete(ReportsDao.class, "deleteReports", map);
	}
	//修改
	@Override
	public void updateReports(Map<String, Object> map){
		this.update(ReportsDao.class, "updateReports", map);
	}
	//装载一个我的报表
	@Override
	public Reports loadReports(Map<String, Object> map) {
		return (Reports)this.get(ReportsDao.class, "searchReports", map);
	}
	@Override
	public String searchCurrSequence() {
		return (String) this.get(ReportsDao.class, "searchCurrSequence", null);
	}
	
}