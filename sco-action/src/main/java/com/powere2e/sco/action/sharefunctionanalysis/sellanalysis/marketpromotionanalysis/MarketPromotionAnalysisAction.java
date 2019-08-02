package com.powere2e.sco.action.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysisService;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class MarketPromotionAnalysisAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MarketPromotionAnalysisService marketPromotionAnalysisService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		marketPromotionAnalysisService = (MarketPromotionAnalysisService) ConfigFactory.getInstance().getBean("marketPromotionAnalysisService");
	}

	/**
	 * 显示公司整体促销分析界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doShowMarketPromotionAnalysisGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/marketPromotionAnalysis/marketPromotionAnalysisGrid.ftl");
	}

	/**
	 * 整体促销分析明细
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doListMarketPromotionAnalysisDetail() throws Exception {
		Map<String, Object> map = getMarketPromotionAnalysis().toMap();
		List<MarketPromotionAnalysis> list = marketPromotionAnalysisService.listMarketPromotionAnalysisDetail(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 销售明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doListMarketPromotionAnalysis() throws Exception {
		Map<String, Object> map = getMarketPromotionAnalysis().toMap();
		List<MarketPromotionAnalysis> list = marketPromotionAnalysisService.listMarketPromotionAnalysis(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 销售明细汇总列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doListMarketPromotionAnalysisDetails() throws Exception {
		Map<String, Object> map = getMarketPromotionAnalysis().toMap();
		List<MarketPromotionAnalysis> list = marketPromotionAnalysisService.listMarketPromotionAnalysisDetails(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MarketPromotionAnalysis getMarketPromotionAnalysis() throws Exception {
		MarketPromotionAnalysis MarketPromotionAnalysis = new MarketPromotionAnalysis();
		this.asBean(MarketPromotionAnalysis);
		return MarketPromotionAnalysis;
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doExportMarketPromotionAnalysisToExcel() throws Exception {
		MarketPromotionAnalysis MarketPromotionAnalysis = this.getMarketPromotionAnalysis();
		ServletOutputStream out = response.getOutputStream();
		String date = MarketPromotionAnalysis.getMinDate() + "~" + MarketPromotionAnalysis.getMaxDate();
		if (MarketPromotionAnalysis.getMinDates() != "" && MarketPromotionAnalysis.getMinDates() != null) {
			date += "-" + MarketPromotionAnalysis.getMinDates() + "~" + MarketPromotionAnalysis.getMaxDates();
		}
		String fileName = "公司整体促销对比汇总表-" + date + "".concat(".xlsx");
		String export = this.asString("export");
		if (export.equals("2")) {
			fileName = "公司整体促销明细表-" + date + "".concat(".xlsx");
		}
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = MarketPromotionAnalysis.toMap();
		//区分导出类型(1为整体促销对比,2为整体促销明细)
		if (export.equals("1")) {
			this.marketPromotionAnalysisService.exportMarketPromotionAnalysis(map, out);
		} else if (export.equals("2")) {
			this.marketPromotionAnalysisService.exportMarketPromotionAnalysisDetail(map, out);
		}
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.marketPromotionAnalysis")
	public void doSaveSearchDataForm() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.CPC);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getMarketPromotionAnalysis().toMap();
		String saveType = this.asString("saveType");
		paraMap.put("saveType", saveType);
		String msg = "";
		//区分报表类型(1为整体促销对比,2为整体促销明细)
		if (saveType.equals("1")) {
			msg = this.marketPromotionAnalysisService.saveMarketPromotionAnalysis(fileName, paraMap);
		} else if (saveType.equals("2")) {
			msg = this.marketPromotionAnalysisService.saveMarketPromotionAnalysisDetail(fileName, paraMap);
		}
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
}
