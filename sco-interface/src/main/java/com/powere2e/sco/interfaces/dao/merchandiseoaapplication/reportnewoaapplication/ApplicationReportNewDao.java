package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;

/**
 * 申请报告(新品引进)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportNewDao extends Dao {
	/**
	 * 没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 查询申请报告(新品引进)列表
	 */
	public List<ApplicationReportNew> listApplicationReportNew(Map<String, Object> map) throws Exception;

	/**
	 * 查询申请报告从意向品带入的信息(新品引进)
	 */
	public List<ApplicationReportNew> queryReportOfNew(Map<String, Object> map) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(新品引进)
	 */
	public ApplicationReportNew loadApplicationReportNew(Map<String, Object> map) throws Exception;

	/**
	 * 添加申请报告(新品引进)
	 */
	public void insertApplicationReportNew(Map<String, Object> map) throws Exception;

	/**
	 * 删除申请报告(新品引进)
	 */
	public void deleteApplicationReportNew(Map<String, Object> map) throws Exception;

	/**
	 * 修改申请报告(新品引进)
	 */
	public void updateApplicationReportNew(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除申请报告(新品引进)
	 */
	public void deleteReportNewByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请编号删除检验标准
	 */
	public void deleteCheckStandardNewByApplicationCode(Map<String, Object> map);
}