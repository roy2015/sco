package com.powere2e.sco.interfaces.service.reports;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.reports.CostAnalysisMerchandise;
import com.powere2e.sco.model.reports.Reports;
/**
 * 我的报表Service接口
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public interface ReportsService extends Service {
	/**
	 * 我的报表查询
	 
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回我的报表列表(查出报表及其他关联信息)
	 */
	public List<Reports> listReports(Map<String, Object> map,PageInfo pageInfo);
	
	/**
	 * 查询报表数据
	 * 
	 * @param map
	 *            查询添加
	 * @param pageInfo
	 *            分页参数
	 * @return 返回我的报表列表(只查出保存的报表及报表类型等信息)
	 */
	public List<Reports> listReportsByParams(Map<String, Object> map,
			PageInfo pageInfo);
	
	/**
	 * 根据ID号加载一个我的报表
	 *
	 * @param map
	 *				
	 * @return
	 */
	public Reports loadReports(String reportsCode);
	/**
	 * 添加我的报表
	 *
	 * @param map
	 *				
	 */
	public void insertReports(Reports reports);
	
	/**
	 * 新增成本分析-商品
	 * 
	 * @param myReport
	 *            成本实例
	 */
	public void insertReportAnalysis(CostAnalysisMerchandise costAnalysisMerchandise);
	
	/**
	 * 查询当前的sequence即报表编号
	 * 
	 * @return 编号
	 */
	public String searchCurrSequence();

	/**
	 * 验证文件名称是否已经存在
	 * 
	 * @param map
	 *            查询条件
	 * @return 是否存在
	 */
	public boolean ifFileNameExists(Map<String, Object> map);
	
	/**
	 * 删除我的报表
	 *
	 * @param map 
	 *				必须参数id为要删除的我的报表id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteReports(String[] reportsCode);
	/**
	 * 修改我的报表
	 *
	 * @param map 
	 *				必须参数id为要修改我的报表的id号，不能为数组
	 */
	public void updateReports(Reports reports);

}