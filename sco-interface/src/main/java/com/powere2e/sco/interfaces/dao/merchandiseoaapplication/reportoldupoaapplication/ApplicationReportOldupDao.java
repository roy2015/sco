package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;

/**
 * 申请报告(老品新上)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportOldupDao extends Dao {

	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseOldup(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回申请报告(老品新上)列表
	 */
	public List<ApplicationReportOldup> listApplicationReportOldup(Map<String, Object> map) throws Exception;

	/**
	 * 查询报告从商品中带入的信息(老品新上)
	 */
	public List<ApplicationReportOldup> queryReportOfOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(老品新上)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationReportOldup loadApplicationReportOldup(Map<String, Object> map) throws Exception;

	/**
	 * 添加申请报告(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertApplicationReportOldup(Map<String, Object> map) throws Exception;

	/**
	 * 删除申请报告(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的申请报告(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationReportOldup(Map<String, Object> map) throws Exception;

	/**
	 * 修改申请报告(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要修改申请报告(老品新上)的id号，不能为数组
	 */
	public void updateApplicationReportOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除申请报告(新品引进)
	 */
	public void deleteReportOldupByApplicationCode(Map<String, Object> map) throws Exception;
}