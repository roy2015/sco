package com.powere2e.sco.action.reports;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.BusinessUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.interfaces.service.reports.ReportsService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.security.model.User;
import com.powere2e.security.utils.PowerUtils;

/**
 * 我的报表的WEB请求响应类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public class ReportsAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8869532574567617434L;
	private ReportsService reportsService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		reportsService = (ReportsService) ConfigFactory.getInstance().getBean("reportsService");
	}

	/**
	 * 商品意向品列表grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.report")
	public void doShowReportGrid() throws Exception {
		this.putObject("applicationStatus", BusinessUtils.APPLICATION_STATUS);
		this.putObject("nfsFilePath", PathUtils.getNfsFilePath());
		this.forwardPage("sco/reports/reportsGrid.ftl");
	}

	/**
	 * 我的报表列表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListReports() throws Exception {
		User user = PowerUtils.getCurrentUser();
		Map<String, Object> map = getReports().toMap();
		map.put("userId", user.getUserId());
		/*
		 * if(asString("createdEnd")!=null &&"".equals(asString("createdEnd"))){ Date createdEnd=asDate("createdEnd"); Calendar rightNow = Calendar.getInstance(); rightNow.setTime(createdEnd); rightNow.add(Calendar.DAY_OF_YEAR,1);//日期加1天 createdEnd=rightNow.getTime(); map.put("createdEnd", asDate("createdEnd")); }
		 */
		List<Reports> list = reportsService.listReports(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 根据ID号获一个我的报表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doLoadReports() throws Exception {
		Reports reports = this.reportsService.loadReports(asString("reportsCode"));
		this.forwardData(true, reports, null);
	}

	/**
	 * 添加我的报表界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.insert")
	public void doShowInsertReportsForm() throws Exception {
		Reports reports = new Reports();
		this.putObject("reports", reports);
		this.forwardPage("reports/reportsForm");
	}

	/**
	 * 修改我的报表界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.edit")
	public void doShowUpdateReportsForm() throws Exception {
		Reports reports = reportsService.loadReports(asString("reportsCode"));
		this.putObject("reports", reports);
		this.forwardPage("reports/reportsForm");
	}

	/**
	 * 添加我的报表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.insert")
	public void doInsertReports() throws Exception {
		Reports reports = getReports();
		reports.setReportsCode(MasterDataTypeServiceImpl.getInstance().nextID("s_reports"));
		reportsService.insertReports(reports);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 修改我的报表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.edit")
	public void doUpdateReports() throws Exception {
		reportsService.updateReports(getReports());
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 删除我的报表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDeleteReports() throws Exception {
		Boolean delOk = true;
		String reportsCode[] = asString("reportsCode").split(",");
		for (String code : reportsCode) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("reportsCode", code);
			List<Reports> list = reportsService.listReports(map, null);
			if (list.size() > 1) {
				delOk = false;
				break;
			} else {
				if (!StringUtils.isBlank(list.get(0).getOaStatus())) {
					delOk = false;
					break;
				}
			}
		}
		if (delOk) {
			reportsService.deleteReports(reportsCode);
			this.forwardData(true, null, this.getText("public.success"));
		} else {
			this.forwardData(false, null, "只可删除未关联OA申请单的报表");
		}
	}

	/**
	 * 查询报表类型list(显示所有报表类型)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMyReportType() {
		this.forwardData(true, BusinessUtils.MYREPORT_TYPE, null);
	}

	/**
	 * 查询报表类型list(显示部分报表类型)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListPartMyReportType() {
		this.forwardData(true, BusinessUtils.MYREPORT_PART_TYPE, null);
	}

	/**
	 * 查询报表类型list(商品OA申请专用)
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListMyReportTypeForMOA() {
		this.forwardData(true, BusinessUtils.MYREPORT_FOR_MOA, null);
	}

	
	/**
	 * 根据条件查询报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListReportsByParams() throws Exception {
		Map<String, Object> map = this.getReports().toMap();
		List<Reports> list = reportsService.listReportsByParams(map, this.getPageInfo());
		this.forwardData(true, list, null);
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
