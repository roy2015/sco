package com.powere2e.sco.action.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis;

import java.io.IOException;
import java.util.ArrayList;
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
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysisService;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class MerchandisePromotionAnalysisAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MerchandisePromotionAnalysisService merchandisePromotionAnalysisService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandisePromotionAnalysisService = (MerchandisePromotionAnalysisService) ConfigFactory.getInstance().getBean("merchandisePromotionAnalysisService");
	}

	/**
	 * 显示商品促销分析界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doShowMerchandisePromotionAnalysisGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/merchandisePromotionAnalysis/merchandisePromotionAnalysisGrid.ftl");
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandise() throws Exception {
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		map.put("show", this.asString("show"));
		String purchaseDepartments = this.asString("purchaseDepartments");
		if (purchaseDepartments != null) {
			map.put("purchaseDepartments", this.asString("purchaseDepartments").equals("IMPORT") ? "国际" : this.asString("purchaseDepartments").equals("INLAND") ? "国内" : "");
		}
		List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandiseInfo() throws Exception {
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandiseInfo(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示促销分析列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doShowMerchandisePromotionAnalysisDetailGrid() throws Exception {
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("minDate", this.asString("minDate"));
		this.putObject("maxDate", this.asString("maxDate"));
		this.putObject("show", this.asString("searchTable"));
		this.putObject("sellRegion", this.asString("sellRegion"));
		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/merchandisePromotionAnalysis/merchandisePromotionInfoGrid.ftl");
	}

	/**
	 * 促销分析列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandisePromotionAnalysis() throws Exception {
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandisePromotionAnalysis(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 促销分析汇总列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandisePromotionAnalysisDetail() throws Exception {
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		String[] minDate = this.asString("minDate").split(",");
		String[] maxDate = this.asString("maxDate").split(",");
		String[] day = this.asString("dayA").split(",");
		String[] sellRegion = this.asString("sellRegion").split(",");
		for (int i = 0; i < minDate.length; i++) {
			map.put("minDate", minDate[i]);
			map.put("maxDate", maxDate[i]);
			map.put("dayA", day[i]);
			map.put("count", minDate.length);
			map.put("sellRegion", sellRegion[i]);
			List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandisePromotionAnalysisDetail(map, null);
			lists.addAll(list);
		}
		this.forwardData(true, lists, null);
	}

	/**
	 * 促销分析明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandisePromotionAnalysisDetailInfo() throws Exception {
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		String[] minDate = this.asString("minDate").split(",");
		String[] maxDate = this.asString("maxDate").split(",");
		String[] day = this.asString("dayA").split(",");
		String[] sellRegion = this.asString("sellRegion").split(",");
		for (int i = 0; i < minDate.length; i++) {
			map.put("minDate", minDate[i]);
			map.put("maxDate", maxDate[i]);
			map.put("dayA", day[i]);
			map.put("sellRegion", sellRegion[i]);
			List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandisePromotionAnalysisDetailInfo(map, null);
			lists.addAll(list);
		}
		this.forwardData(true, lists, null);
	}

	/**
	 * 图形报表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doListMerchandisePromotionAnalysisDetailMap() throws Exception {
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		String[] minDate = this.asString("minDate").split(",");
		String[] maxDate = this.asString("maxDate").split(",");
		String[] day = this.asString("dayA").split(",");
		for (int i = 0; i < minDate.length; i++) {
			map.put("minDate", minDate[i]);
			map.put("maxDate", maxDate[i]);
			map.put("dayA", day[i]);
			List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.lislistMerchandisePromotionAnalysisDetailTB(map, this.getPageInfo());
			lists.addAll(list);
		}
		this.forwardData(true, lists, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandisePromotionAnalysis getMerchandisePromotionAnalysis() throws Exception {
		MerchandisePromotionAnalysis MerchandisePromotionAnalysis = new MerchandisePromotionAnalysis();
		this.asBean(MerchandisePromotionAnalysis);
		return MerchandisePromotionAnalysis;
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doExportMerchandisePromotionInfoToExcel() throws Exception {
		Map<String, Object> map = getMerchandisePromotionAnalysis().toMap();
		List<MerchandisePromotionAnalysis> list = merchandisePromotionAnalysisService.listMerchandiseInfo(map, null);
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品促销对比汇总表-" + list.get(0).getMerchandiseName() + "".concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.merchandisePromotionAnalysisService.exportMerchandisePromotionAnalysisDetail(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.merchandisePromotionAnalysis")
	public void doSaveSearchDataForm() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.MPC);// 报表类型
		map.put("reportsName", fileName);// 报表名称

		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getMerchandisePromotionAnalysis().toMap();
		String msg = this.merchandisePromotionAnalysisService.saveMerchandisePromotionAnalysisDetail(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
}
