package com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import com.powere2e.frame.commons.service.Service;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationLackFileM;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.ApplicationReportNew;

/**
 * 申请报告(新品引进)Service接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public interface ApplicationReportNewService extends Service {
	/**
	 * 没有报告的商品
	 */
	public List<ApplicationLackFileM> queryNotHaveReportMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 查询 申请报告(新品引进)列表
	 */
	public List<ApplicationReportNew> listApplicationReportNew(String applicationCode,String intentionAndSupplierCodes) throws Exception;

	/**
	 * 根据ID号加载一个申请报告(新品引进)
	 */
	public ApplicationReportNew loadApplicationReportNew(String applicationCode,String intentionAndSupplierCodes) throws Exception;

	/**
	 * 添加申请报告(新品引进)
	 */
	public String insertApplicationReportNew(List<ApplicationReportNew> applicationList,String applicationCode,String intentionAndSupplierCodes) throws Exception;
	
	/**
	 * 新增或修改报告
	 */
	public void completeInsertOrUpdateReport(List<ApplicationReportNew> applicationList, String applicationCode) throws Exception;

	/**
	 * 删除申请报告(新品引进)
	 */
	public void deleteApplicationReportNew(String reportCode) throws Exception;

	/**
	 * 修改申请报告(新品引进)
	 */
	public void updateApplicationReportNew(ApplicationReportNew applicationReportNew) throws Exception;
	
	/**
	 * 根据申请单号删除申请报告(新品引进)
	 */
	public void completeDeleteReportNewByApplicationCode(Map<String, Object> map) throws Exception;
	/**
	 * 导出申请报告(新品引进)
	 * 
	 * @param map
	 *            查询参数
	 * @param out
	 *            输出流
	 */
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out);

	/**
	 * 根据申请编号删除检验标准
	 */
	public void deleteCheckStandardNewByApplicationCode(Map<String, Object> map);
	
}