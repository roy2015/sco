package com.powere2e.sco.action.accessoryoaapplication.committeeApply.reportanalysis;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.accessoryoaapplication.committeeApply.reportanalysis.CommitteeReportAnalysisService;
import com.powere2e.sco.model.reports.Reports;

/**
 * 辅料采购委员会竞价报表分析 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月30日
 */
public class CommitteeReportAnalysisAction extends UploadUtils {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3750665805551109044L;
	private CommitteeReportAnalysisService committeeReportAnalysisService;

	@Override
	protected void beforeBuild() {
		committeeReportAnalysisService = (CommitteeReportAnalysisService) ConfigFactory
				.getInstance().getBean("committeeReportAnalysisService");
	}

	/**
	 * 显示已关联的系统报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListAnalysisReportSystem() throws Exception {
		List<Reports> list = committeeReportAnalysisService
				.listAnalysisReportSystem(this.getReports().toMap(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示已关联的上传的报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListAnalysisReportUpload() throws Exception {
		List<Reports> list = committeeReportAnalysisService
				.listAnalysisReportUpload(this.getReports().toMap(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示上传的申购单
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListAnalysisReportPurOrder() throws Exception {
		List<Reports> list = committeeReportAnalysisService
				.listAnalysisReportPurOrder(this.getReports().toMap(),
						this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 显示关联的报表界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doShowLinkAnalysisReportCommitteeForm() {
		this.forwardPage("sco/accessoryOaApplication/committeeApply/reportAnalysis/committeeReportAnalysisForm.ftl");
	}

	/**
	 * 添加关联报表
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doInsertLinkAnalysisReportCommittee() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		this.committeeReportAnalysisService
				.insertLinkAnalysisReportCommittee(map);
		this.forwardData(true, null, "关联报表成功");
	}

	/**
	 * 上传报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doUploadAnalysisReport() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		
		String oaReportNativeFilePath = PathUtils.getOaReportNativeFilePath();
		List<File> fList = this.doUploadBySaveDir(oaReportNativeFilePath.concat("/"));
		if (!fList.isEmpty()) {
			File file = fList.get(0);
			map.put("reportsName", StrUtils.getStrBefLastSplit(file.getName(), "_").concat(".")
					.concat(StrUtils.getStrAftLastSplit(file.getName(), ".")));
			map.put("reportsUrl", oaReportNativeFilePath
					.replaceAll(ConfigPath.getUploadFilePath(), "").concat(file.getName()));
			this.committeeReportAnalysisService.completeUploadReport(map, file);
		}
		this.forwardData(true, null, "报表上传成功");
	}

	/**
	 * 上传申购单
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doUploadAnalysisReportPurOrder() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		
		String oaReportSubscribeFilePath = PathUtils.getOaReportSubscribeFilePath();
		List<File> fList = this.doUploadBySaveDir(oaReportSubscribeFilePath.concat("/"));
		if (!fList.isEmpty()) {
			File file = fList.get(0);
			map.put("reportsName", this.asString("reportsName"));
			map.put("reportsUrl", oaReportSubscribeFilePath
					.replaceAll(ConfigPath.getUploadFilePath(), "").concat(file.getName()));
			this.committeeReportAnalysisService.completeUploadReportPurOrder(map, file);
		}
		this.forwardData(true, null, "申购单上传成功");
	}
	
	/**
	 * 显示申购单上传界面
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doShowPurOrderUploadForm() {
		this.forwardPage("sco/accessoryOaApplication/committeeApply/reportAnalysis/committeePurOrderUploadForm.ftl");
	}
	
	/**
	 * 删除已关联的系统报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doDeleteAnalysisReportSystem() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("reportsCodes", this.asString("reportsCodes"));
		
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		committeeReportAnalysisService.deleteAnalysisReportSystem(map);
		this.forwardData(true, null, "成功取消关联关系");
	}

	/**
	 * 删除已关联的上传报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doDeleteAnalysisReportUpload() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("reportsCodes", this.asString("reportsCodes"));
		
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		committeeReportAnalysisService.deleteAnalysisReportUpload(map);
		this.forwardData(true, null, "成功取消关联关系");
	}

	/**
	 * 删除已关联的申购单
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doDeleteAnalysisReportPurOrder() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		map.put("reportsCodes", this.asString("reportsCodes"));
		
		map.put("applicationCode", this.asString("applicationCode"));
		map.put("quotedCodes", this.asString("quotedCodes"));
		committeeReportAnalysisService.deleteAnalysisReportPurOrder(map);
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
