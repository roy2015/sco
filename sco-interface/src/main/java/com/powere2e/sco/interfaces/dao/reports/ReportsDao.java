package com.powere2e.sco.interfaces.dao.reports;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.reports.Reports;

/**
 * 我的报表DAO接口
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public interface ReportsDao extends Dao {
	/**
	 * 我的报表查询(与其他表有关联)
	 *
	 * @param map 
	 *				查询参数
	 * @param pageInfo 
	 *				分页参数
	 * @return 返回我的报表列表
	 */
	public List<Reports> listReports(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 查询报表数据(只查出保存的报表及报表类型等信息)
	 * 
	 * @param map
	 *            查询添加
	 * @param pageInfo
	 *            分页参数
	 * @return 返回我的报表列表
	 */
	public List<Reports> listReportsByParams(Map<String, Object> map,
			PageInfo pageInfo);
	
	/**
	 * 根据ID号加载一个我的报表
	 * 
	 * @param map
	 *            查询添加
	 * @return 对应报表
	 */
	public Reports loadReports(Map<String,Object> map);
	
	/**
	 * 添加我的报表
	 *
	 * @param map
	 *				
	 */
	public void insertReports(Map<String, Object> map);

	/**
	 * 新增成本分析-商品
	 * 
	 * @param myReport
	 *            成本实例
	 */
	public void insertReportAnalysis(Map<String, Object> map);
	
	/**
	 * 查询当前的sequence即报表编号
	 * 
	 * @return 编号
	 */
	public String searchCurrSequence();

	/**
	 * 判断报表名称是否已经存在
	 * 
	 * @param map
	 *            查询参数
	 * @return 报表类型
	 */
	public String ifFileNameExists(Map<String, Object> map);

	/**
	 * 删除我的报表
	 *
	 * @param map 
	 *				必须参数id为要删除的我的报表id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteReports(Map<String, Object> map);
	
	/**
	 * 修改我的报表
	 *
	 * @param map 
	 *				必须参数id为要修改我的报表的id号，不能为数组
	 */
	public void updateReports(Map<String, Object> map);
	
}