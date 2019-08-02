package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.ApplicationReportOldup;

/**
 * 申请报告(老品新上)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportOldupService extends Service {
	/**
	 * 查询没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseOldup(Map<String, Object> map) throws Exception;

	/**
	 * 申请报告(老品新上)查询
	 */
	public List<ApplicationReportOldup> listApplicationReportOldup(String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 查询报告从商品中带入的信息(老品新上)
	 */
	public List<ApplicationReportOldup> queryReportOfOldup(String intentionAndSupplierCodes) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(老品新上)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public ApplicationReportOldup loadApplicationReportOldup(String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 添加申请报告(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public String insertApplicationReportOldup(List<ApplicationReportOldup> applicationList, String applicationCode, String intentionAndSupplierCodes) throws Exception;

	/**
	 * 新增或修改老品新上报告
	 */
	public void completeInsertOrUpdateReportOldup(List<ApplicationReportOldup> applicationList, String applicationCode) throws Exception;

	/**
	 * 删除申请报告(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的申请报告(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteApplicationReportOldup(String applicationCode) throws Exception;

	/**
	 * 修改申请报告(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要修改申请报告(老品新上)的id号，不能为数组
	 */
	public void updateApplicationReportOldup(ApplicationReportOldup applicationReportOldup) throws Exception;

	/**
	 * 根据申请单号删除申请报告(老品新上)
	 */
	public void deleteAllReportOldUpByApplicationCode(Map<String, Object> map) throws Exception;
	/**
	 * 导出申请报告(老品新上)
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);
}