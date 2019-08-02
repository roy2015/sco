package com.powere2e.sco.service.impl.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.interfaces.dao.reports.ReportsDao;
import com.powere2e.sco.interfaces.service.reports.ReportsService;
import com.powere2e.sco.model.reports.CostAnalysisMerchandise;
import com.powere2e.sco.model.reports.Reports;

/**
 * 我的报表业务类的实现
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public class ReportsServiceImpl extends ServiceImpl implements ReportsService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5007790523649785641L;
	private ReportsDao reportsDao;
	public static ReportsService getInstance(){
		return (ReportsService)ConfigFactory.getInstance().getBean("reportsService");
	}
	//获得我的报表DAO实例
	public ReportsDao getReportsDao() {
		return reportsDao;
	}
	//设置我的报表DAO实例
	public void setReportsDao(ReportsDao reportsDao) {
		this.reportsDao = reportsDao;
	}
	//查询
	@Override
	public List<Reports> listReports(Map<String, Object> map,PageInfo pageInfo){
		return this.getReportsDao().listReports(map, pageInfo);
	}
	
	public List<Reports> listReportsByParams(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.getReportsDao().listReportsByParams(map, pageInfo);
	}
	
	//添加
	@Override
	public void insertReports(Reports reports){
		this.getReportsDao().insertReports(reports.toMap());
	}
	//新增成本分析-商品
	@Override
	public void insertReportAnalysis(CostAnalysisMerchandise costAnalysisMerchandise) {
		this.getReportsDao().insertReportAnalysis(costAnalysisMerchandise.toMap());
	}
	//验证文件名称是否已经存在
	@Override
	public boolean ifFileNameExists(Map<String, Object> map) {
		return !StringUtils.isBlank(this.getReportsDao().ifFileNameExists(map));
	}
	//删除
	@Override
	public void deleteReports(String[] reportsCode){
		for(String code :reportsCode){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsCode", code);
		this.getReportsDao().deleteReports(map);
		}
	}
	//修改
	@Override
	public void updateReports(Reports reports){
		this.getReportsDao().updateReports(reports.toMap());
	}
	//加载一个我的报表
	@Override
	public Reports loadReports(String reportsCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsCode", reportsCode);
		return this.getReportsDao().loadReports(map);
	}
	@Override
	public String searchCurrSequence() {
		return this.getReportsDao().searchCurrSequence();
	}
}