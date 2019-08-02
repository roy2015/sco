package com.powere2e.sco.action.merchandiseoaapplication.reportnewoaapplication.reportanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.reportanalysis.AnalysisReportNewService;
import com.powere2e.sco.model.reports.Reports;

/**
 * 新品引进报表分析 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月14日
 */
public class AnalysisReportNewAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3750665805551109044L;
	private AnalysisReportNewService analysisReportNewService;

	@Override
	protected void beforeBuild() {
		analysisReportNewService = (AnalysisReportNewService) ConfigFactory
				.getInstance().getBean("analysisReportNewService");
	}

	/**
	 * 显示已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportNew.insert")
	public void doListAnalysisReportNew() throws Exception {
		List<Reports> list = analysisReportNewService.listAnalysisReportNew(
				this.getReports().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示已关联的报表界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportNew.insert")
	public void doShowLinkAnalysisReportNewForm() {
		this.forwardPage("sco/merchandiseOaApplication/reportNewOaApplication/reportAnalysis/newOaReportAnalysisForm.ftl");
	}
	
	/**
	 * 添加关联报表
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportNew.insert")
	public void doInsertLinkAnalysisReportNew() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		this.analysisReportNewService.insertLinkAnalysisReportNew(map);
		this.forwardData(true, null, "关联报表成功");
	}
	
	/**
	 * 删除已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportNew.insert")
	public void doDeleteAnalysisReportNew() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		analysisReportNewService.deleteAnalysisReportNew(map);
		this.forwardData(true, null, "成功取消关联关系");
	}

	/**
	 * 下载报表文件
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication")
	public void doDownloadOriginalFile() throws Exception {
		String address = this.asString("path");
		this.forwardDownload("upload/" + address, ExcelUtils
				.getEncodeFileName(new File(address).getName()));
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
