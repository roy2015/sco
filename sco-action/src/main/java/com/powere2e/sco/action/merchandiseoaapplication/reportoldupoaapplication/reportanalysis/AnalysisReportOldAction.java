package com.powere2e.sco.action.merchandiseoaapplication.reportoldupoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportoldupoaapplication.reportanalysis.AnalysisReportOldService;
import com.powere2e.sco.model.reports.Reports;

/**
 * 旧品新上报表分析 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月14日
 */
public class AnalysisReportOldAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3750665805551109044L;
	private AnalysisReportOldService analysisReportOldService;

	@Override
	protected void beforeBuild() {
		analysisReportOldService = (AnalysisReportOldService) ConfigFactory
				.getInstance().getBean("analysisReportOldService");
	}

	/**
	 * 显示已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportOldup.insert")
	public void doListAnalysisReportOld() throws Exception {
		List<Reports> list = analysisReportOldService.listAnalysisReportOld(
				this.getReports().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示已关联的报表界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportOldup.insert")
	public void doShowLinkAnalysisReportOldForm() {
		this.forwardPage("sco/merchandiseOaApplication/reportOldupOaApplication/reportAnalysis/oldOaReportAnalysisForm.ftl");
	}
	
	/**
	 * 添加关联报表
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportOldup.insert")
	public void doInsertLinkAnalysisReportOld() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		this.analysisReportOldService.insertLinkAnalysisReportOld(map);
		this.forwardData(true, null, "关联报表成功");
	}
	
	/**
	 * 删除已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportOldup.insert")
	public void doDeleteAnalysisReportOld() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		analysisReportOldService.deleteAnalysisReportOld(map);
		this.forwardData(true, null, "成功取消关联关系");
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Reports getReports() throws Exception {
		Reports reports = new Reports();
		this.asBean(reports);
		return reports;
	}

}
