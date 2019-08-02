package com.powere2e.sco.action.merchandiseintention;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseIntentionService;
import com.powere2e.sco.interfaces.service.merchandiseintention.MerchandiseQuotedService;
import com.powere2e.sco.interfaces.service.merchandiseintention.QuotedCompareService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntentionSupplier;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.sco.service.impl.SupplierDataServiceImpl;

/**
 * 报价比价的Action
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月10日
 */
public class QuotedCompareAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5163620339872494109L;
	private MerchandiseIntentionService merchandiseIntentionService;
	private QuotedCompareService quotedCompareService;
	private MerchandiseQuotedService merchandiseQuotedService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseIntentionService = (MerchandiseIntentionService) ConfigFactory.getInstance().getBean("merchandiseIntentionService");
		quotedCompareService = (QuotedCompareService) ConfigFactory.getInstance().getBean("quotedCompareService");
		merchandiseQuotedService = (MerchandiseQuotedService) ConfigFactory.getInstance().getBean("merchandiseQuotedService");
	}

	/**
	 * 导出所有报价单记录
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doExportAllQuoted() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String intentionCode = this.asString("intentionCode");
		map.put("intentionCode", intentionCode);
		MerchandiseIntention merchandiseIntention = merchandiseIntentionService.loadMerchandiseIntention(intentionCode);// 根据意向品编号查询意向品名称

		List<MerchandiseQuoted> quotedList = merchandiseQuotedService.listMerchandiseQuoted(map, null);// 查询该意向品的所有报价单
		this.putObject("quotedList", quotedList);
		this.forwardDownload("excel/sco/merchandiseIntention/intentionAllQuotedTemplate.xlsx",
				ExcelUtils.getEncodeFileName(((merchandiseIntention != null) ? merchandiseIntention.getIntentionName() : "") + "_所有报价记录_" + DateUtils.formateDateTime() + ".xlsx"));
	}

	/**
	 * 分析已有报价记录时，显示统一对比单位对话框
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowConvertUnitDialog() throws Exception {
		String intentionCode = this.asString("intentionCode");

		this.putObject("intentionCode", intentionCode);
		// 设置convertUnit的默认值为1
		this.putObject("convertUnit", 1);
		this.forwardPage("sco/merchandiseIntention/convertUnitDialog.ftl");
	}

	/**
	 * 显示分析已有报价记录界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowAnalysisQuoted() throws Exception {
		String intentionCode = this.asString("intentionCode");
		Double convertUnit = this.asDouble("convertUnit");
		this.putObject("intentionCode", intentionCode);
		this.putObject("convertUnit", convertUnit);
		this.forwardPage("sco/merchandiseIntention/analysisQuotedGrid.ftl");
	}

	/**
	 * 分析已有报价记录
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doAnalysisQuoted() throws Exception {
		String intentionCode = this.asString("intentionCode");
		Double convertUnit = this.asDouble("convertUnit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		map.put("convertUnit", convertUnit);

		// 查询已有报价记录分析结果
		List<MerchandiseQuoted> analysisList = quotedCompareService.analysisQuoted(map, this.getPageInfo());
		this.forwardData(true, analysisList, null);
	}

	/**
	 * 获取统计结果
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doGetCheapQuoted() throws Exception {
		String intentionCode = this.asString("intentionCode");
		Double convertUnit = this.asDouble("convertUnit");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		map.put("convertUnit", convertUnit);

		// 查询已有报价记录分析结果
		List<MerchandiseQuoted> analysisList = quotedCompareService.analysisQuoted(map, null);
		if (analysisList != null && analysisList.size() > 0) {
			map.clear();
			// map.put("analysisList", analysisList);
			map.put("supplierNumbers", analysisList.get(0).getSupplierNumbers());
			map.put("intentionSupplierName", analysisList.get(0).getIntentionSupplierName());
		}

		this.forwardData(true, map, null);
	}

	/**
	 * 显示查看供应商历史报价页面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowHistoryQuoted() throws Exception {
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		String intentionCode = this.asString("intentionCode");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("intentionCode", intentionCode);
		map.put("intentionSupplierCode", intentionSupplierCode);

		// 查询该供应商最晚报价
		MerchandiseQuoted lastQuoted = quotedCompareService.queryLastQuoted(map);
		this.putObject("intentionSupplierCode", intentionSupplierCode);
		this.putObject("intentionCode", intentionCode);
		this.putObject("lastQuoted", lastQuoted);

		this.forwardPage("sco/merchandiseIntention/historyQuotedGrid.ftl");
	}

	/**
	 * 查询供应商历史报价
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListHistoryQuoted() throws Exception {
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("intentionSupplierCode", intentionSupplierCode);
		List<MerchandiseQuoted> historyList = quotedCompareService.listHistoryQuoted(map, this.getPageInfo());

		this.forwardData(true, historyList, null);
	}

	/**
	 * 查询对比商品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListCompareMerchandise() throws Exception {
		String currentIntentionCode = this.asString("currentIntentionCode");
		String somePart = this.asString("somePart");
		String createDateStart = this.asString("createDateStart");
		String createDateEnd = this.asString("createDateEnd");
		Map<String, Object> map = this.getMerchandiseIntention().toMap();

		map.put("currentIntentionCode", currentIntentionCode);
		map.put("createDateStart", createDateStart);
		map.put("createDateEnd", createDateEnd);
		map.put("somePart", somePart);

		List<MerchandiseIntention> comparetList = quotedCompareService.listCompareMerchandise(map, this.getPageInfo());
		this.forwardData(true, comparetList, null);
	}

	/**
	 * 查询对比商品时，弹出统一对比单位对话框
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowCompareResultDialog() throws Exception {
		String intentionCode = this.asString("intentionCode");
		String compareIntentionCodes = this.asString("compareIntentionCodes");

		this.putObject("intentionCode", intentionCode);
		this.putObject("compareIntentionCodes", compareIntentionCodes);
		// 设置compareUnit的默认值为1
		this.putObject("compareUnit", 1);
		this.forwardPage("sco/merchandiseIntention/compareResultDialog.ftl");
	}

	/**
	 * 显示查看对比结果页面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doShowCompareResult() throws Exception {
		String compareIntentionCodes = this.asString("compareIntentionCodes");
		String intentionCode = this.asString("intentionCode");
		Double compareUnit = this.asDouble("compareUnit");

		this.putObject("intentionCode", intentionCode);
		this.putObject("compareIntentionCodes", compareIntentionCodes);
		this.putObject("compareUnit", compareUnit);

		this.forwardPage("sco/merchandiseIntention/compareResultGrid.ftl");
	}
	
	/**
	 * 查询对比结果
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListCompareResult() throws Exception {
		String compareIntentionCodes = this.asString("compareIntentionCodes");
		String intentionCode = this.asString("intentionCode");
		Double compareUnit = this.asDouble("compareUnit");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("compareIntentionCodes", compareIntentionCodes);
		map.put("intentionCode", intentionCode);
		map.put("compareUnit", compareUnit);
		List<MerchandiseQuoted> compareResultList = quotedCompareService.listCompareResult(map, this.getPageInfo());

		this.forwardData(true, compareResultList, null);
	}
	
	/**
	 * 查询所有对比结果(不分页)
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doListAllCompareResult() throws Exception {
		String compareIntentionCodes = this.asString("compareIntentionCodes");
		String intentionCode = this.asString("intentionCode");
		Double compareUnit = this.asDouble("compareUnit");
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("compareIntentionCodes", compareIntentionCodes);
		map.put("intentionCode", intentionCode);
		map.put("compareUnit", compareUnit);
		List<MerchandiseQuoted> compareResultList = quotedCompareService.listCompareResult(map, null);

		this.forwardData(true, compareResultList, null);
	}

	/**
	 * 导出历史报价记录
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doExportHistoryQuoted() throws Exception {
		String intentionSupplierCode = this.asString("intentionSupplierCode");
		Map<String, Object> map = new HashMap<String, Object>();
		// 根据供应商编号查询供应商名称，如果直接获取从前台传递的供应商名称，会导致乱码
		//意向供应商  MerchandiseIntentionSupplier intentionSupplier = merchandiseIntentionService.findSupplierIsTrue(intentionSupplierCode);
		map.put("supplierCode", intentionSupplierCode);
		List<?> opList = SupplierDataServiceImpl.getInstance().listAllSupplier(map, null);
		String supplierName = "";
		MerchandiseIntentionSupplier opt = null;
		if (!opList.isEmpty()) {
			opt = (MerchandiseIntentionSupplier) opList.get(0);
			supplierName = opt.getText();
		}
		
		map.clear();
		map.put("intentionSupplierCode", intentionSupplierCode);
		map.put("intentionCode", this.asString("intentionCode"));
		// 查询该供应商最晚报价
		MerchandiseQuoted lastQuoted = quotedCompareService.queryLastQuoted(map);
		this.putObject("lastQuoted", lastQuoted);

		List<MerchandiseQuoted> quotedList = quotedCompareService.listHistoryQuoted(map, null);
		this.putObject("quotedList", quotedList);
		this.forwardDownload("excel/sco/merchandiseIntention/supplierHistoryQuotedAnalysisTemplate.xlsx",
				ExcelUtils.getEncodeFileName(supplierName + "_历史报价分析报表_" + DateUtils.formateDateTime() + ".xlsx"));
	}

	/**
	 * 判断是否保存意向品
	 */
	@Authority(privilege = "com.powere2e.sco.merchandiseIntention")
	public void doIsSaveMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = this.merchandiseIntentionService.loadMerchandiseIntention(asString("intentionCode"));
		if (merchandiseIntention != null) {
			this.forwardData(true, null, null);
			return;
		} else {
			this.forwardData(false, null, "请先保存商品意向品信息!");
		}
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 */
	private MerchandiseIntention getMerchandiseIntention() throws Exception {
		MerchandiseIntention merchandiseIntention = new MerchandiseIntention();
		this.asBean(merchandiseIntention);
		return merchandiseIntention;
	}
}
