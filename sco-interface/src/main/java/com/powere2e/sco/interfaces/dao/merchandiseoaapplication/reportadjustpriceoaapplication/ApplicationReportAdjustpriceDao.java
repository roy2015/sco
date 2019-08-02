package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.ApplicationReportAdjustprice;

/**
 * 申请报告(调价)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportAdjustpriceDao extends Dao {

	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回申请报告(调价)列表
	 */
	public List<ApplicationReportAdjustprice> listApplicationReportAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 查询报告从商品中带入的信息(正常调价)
	 */
	public List<ApplicationReportAdjustprice> queryReportOfAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(调价)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationReportAdjustprice loadApplicationReportAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 添加申请报告(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationReportAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 删除申请报告(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的申请报告(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationReportAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 修改申请报告(调价)
	 * 
	 * @param map
	 *            必须参数id为要修改申请报告(调价)的id号，不能为数组
	 */
	public void updateApplicationReportAdjustprice(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除申请报告(正常调价)
	 */
	public void deleteReportAdjustpriceByApplicationCode(Map<String, Object> map) throws Exception;
}