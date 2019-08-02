package com.powere2e.sco.action.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportadjustpriceoaapplication.reportanalysis.AnalysisReportAdjustPriceService;
import com.powere2e.sco.model.reports.Reports;

/**
 * 商品正常调价报表分析 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月14日
 */
public class AnalysisReportAdjustPriceAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3750665805551109044L;
	private AnalysisReportAdjustPriceService analysisReportAdjustPriceService;

	@Override
	protected void beforeBuild() {
		analysisReportAdjustPriceService = (AnalysisReportAdjustPriceService) ConfigFactory
				.getInstance().getBean("analysisReportAdjustPriceService");
	}

	/**
	 * 显示已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doListAnalysisReportAdjustPrice() throws Exception {
		List<Reports> list = analysisReportAdjustPriceService.listAnalysisReportAdjustPrice(
				this.getReports().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示已关联的报表界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doShowLinkAnalysisReportAdjustPriceForm() {
		this.forwardPage("sco/merchandiseOaApplication/reportAdjustpriceOaApplication/reportAnalysis/adjustPriceOaReportAnalysisForm.ftl");
	}
	
	/**
	 * 添加关联报表
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doInsertLinkAnalysisReportAdjustPrice() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		this.analysisReportAdjustPriceService.insertLinkAnalysisReportAdjustPrice(map);
		this.forwardData(true, null, "关联报表成功");
	}
	
	/**
	 * 删除已关联的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.oaApplication.reportAdjustprice.insert")
	public void doDeleteAnalysisReportAdjustPrice() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("intentionAndSupplierCodes", this.asString("intentionAndSupplierCodes"));
		analysisReportAdjustPriceService.deleteAnalysisReportAdjustPrice(map);
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
