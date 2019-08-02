package com.powere2e.sco.action.merchandisecostanalysis.totalcostanalogyanalysis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import org.apache.commons.lang3.StringUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalogyAnalysisService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;
import com.powere2e.sco.model.merchandisecostanalysis.totalcostanalogyanalysis.TotalCostAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品总成本类比分析
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年5月7日
 */
public class TotalCostAnalogyAnalysisAction extends UploadUtils {

	private static final long serialVersionUID = -147778323545710267L;
	private TotalCostAnalogyAnalysisService totalCostAnalogyAnalysisService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		totalCostAnalogyAnalysisService = (TotalCostAnalogyAnalysisService) ConfigFactory.getInstance().getBean("totalCostAnalogyAnalysisService");
	}

	/**
	 * 显示商品总成本类比分析页面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis")
	public void doShowTotalCostAnalogyAnalysisGrid() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/totalCostAnalogyAnalysisGrid.ftl");
	}

	/**
	 * 商品总成本类比列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis")
	public void doListTotalCostAnalogyAnalysis() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		map.put("minCreateDate", this.asString("minCreateDate"));
		map.put("maxCreateDate", this.asString("maxCreateDate"));
		map.put("minForetasteDate", this.asString("minForetasteDate"));
		map.put("maxForetasteDate", this.asString("maxForetasteDate"));
		map.put("minApproveDate", this.asString("minApproveDate"));
		map.put("maxApproveDate", this.asString("maxApproveDate"));
		map.put("minUpdated", this.asString("minUpdated"));
		map.put("maxUpdated", this.asString("maxUpdated"));
		map.put("costAnalysis", "true"); //与核算表投料表模块的查询功能分开，因为这个是值查询有核算编号的记录
		List<AccountingBo> list = totalCostAnalogyAnalysisService.listTotalCostAnalogyAnalysis(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获取二维table的纵向表头
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast")
	public void doListHead() throws Exception {
		String data = this.asString("data"); //页面传过来的多组核算编号,商品名称,供应商名称
		String inlandImport = this.asString("inlandImport"); //页面传过来的是进口还是非进口
		if (data == null||inlandImport == null) {
			return;
		}
		List<String> list = new ArrayList<String>();
		AccountingBo temp = null;
		for (Object obj : JSONArray.fromObject(data)) {
			temp = (AccountingBo) JSONObject.toBean(JSONObject.fromObject(obj), AccountingBo.class);
			if (temp.getAccountingCode() != null) {
				if (!list.contains(temp.getAccountingCode())) {
					list.add(temp.getAccountingCode());
				}
			}
		}
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("inlandImport", inlandImport);
		Map<String, Object> map = totalCostAnalogyAnalysisService.listTitle(m);
		this.forwardData(true, map, null);
	}

	/**
	 * 显示商品总成本类比页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis")
	public void doShowCostContrast() throws Exception {
		// 放入页面传过来的参数
		String data = this.asString("data"); //页面传过来的多组核算编号,商品名称,供应商名称
		String inlandImport = this.asString("inlandImport"); //页面传过来的是进口还是非进口
		this.putObject("data", data);
		this.putObject("inlandImport", inlandImport);
		this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/costContrast.ftl");
	}

	/**
	 * 获取本次申请商品的数据
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast")
	public void doListThisTimeApplicationMerchandise() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		TotalCostAnalysis costAnalysis = null;
		JSONObject tempObj = null;
		List<TotalCostAnalysis> list = new ArrayList<TotalCostAnalysis>();
		JSONArray array = JSONArray.fromObject(this.asString("data"));
		for (int i = 0; i < array.size(); i++) {
			JSONObject obj = array.getJSONObject(i);
			map.clear();
			if (null == obj) {continue;}
				map.put("accountingCode", obj.get("accountingCode") instanceof JSONNull?null:obj.getString("accountingCode"));
				map.put("ingredientCode", obj.get("accountingCode") instanceof JSONNull?null:obj.getString("accountingCode"));
				map.put("applicationCode", obj.get("applicationCode") instanceof JSONNull?null:obj.getString("applicationCode"));
				map.put("merchandiseCode", obj.get("merchandiseCode") instanceof JSONNull?null:obj.getString("merchandiseCode"));
				map.put("supplierCode", obj.get("supplierCode") instanceof JSONNull?null:obj.getString("supplierCode"));
		   //如果有报价计量单位转换后的列说明设置了报价计量单位,根据核算编号找转换之前的计量单位列
			if ((obj.get("rowTitleName") == null ?"":obj.getString("rowTitleName")).contains("报价单位转换后")) {
				for (int j = 0; j < array.size(); j++) {
					tempObj = array.getJSONObject(j);
					if (null == tempObj) {continue;}
					if (tempObj.getString("accountingCode").equals(obj.get("accountingCode"))&&!tempObj.getString("rowTitleName").contains("报价单位转换后")) {
						map.put("quantity", tempObj.get("quantity"));  //转换前的报价数
						break;
					}
				}
				map.put("convertAfterQuantity",obj.get("quantity"));  //转换后的报价
			} else {
				map.put("quantity", null); //转换前的报价数
				map.put("convertAfterQuantity", null);//转换后的报价
			}
			// 把转换后的Map对象作为查询条件用来获取本次申请的对比商品
			map.put("inlandImport", this.asString("inlandImport"));
			costAnalysis = totalCostAnalogyAnalysisService.listThisApplicationMerchandise(map);
			list.add(costAnalysis);
		}
		this.forwardData(true, list, null);
	}

/*
	*//**
	 * 获取投料表信息
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 *//*
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast")
	public void doSearchIngredient() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ingredientCode", this.asString("accountingCode"));
		map = totalCostAnalogyAnalysisService.searchIngredient(map);
		this.putObject("ingredient", map.get("ingredient"));
		this.putObject("ingredientItem", map.get("ingredientItem"));
		 this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/table.ftl");
	}*/
	
	/**
	 * 显示添加参照品-商品页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.addrefermerchandise")
	public void doShowAddReferMerchandise() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/addReferMerchandise.ftl");
	}

	/**
	 * 查询添加参照品-商品
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.addrefermerchandise")
	public void doListAddReferMerchandise() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		map.put("lastrecord", this.asString("lastrecord"));
		map.put("minApproveDate", this.asDate("minApproveDate"));
		map.put("maxApproveDate", this.asDate("maxApproveDate"));
		map.put("minCreateDate", this.asDate("minCreateDate"));
		map.put("maxCreateDate", this.asDate("maxCreateDate"));
		map.put("quotedCurrency", this.asString("quotedCurrency"));
		List<AccountingBo> list = totalCostAnalogyAnalysisService.listReferMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示添加参照品-意向品页面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.addreferintention")
	public void doShowAddReferIntention() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/addReferIntention.ftl");
	}

	/**
	 * 查询添加参照品-意向品
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.addreferintention")
	public void doListAddReferIntention() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		map.put("minQuotedDate", this.asDate("minQuotedDate"));
		map.put("maxQuotedDate", this.asDate("maxQuotedDate"));
		map.put("quotedCurrency", this.asString("quotedCurrency"));
		List<AccountingBo> list = totalCostAnalogyAnalysisService.listReferIntention(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示设置统一对比单位窗口
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis")
	public void doShowSetContrastUnits() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/totalCostAnalogyAnalysis/costContrast/setContrastUnits.ftl");
	}
	
	/**
	 * 执行设置统一对比单位窗口
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis")
	public void doSetContrastUnits() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<TotalCostAnalysis> list = new ArrayList<TotalCostAnalysis>();
		JSONArray array = JSONArray.fromObject(this.asString("data"));
		if (array != null) {
			for (Object temp : array) {
				if (temp != null) {
					if (JSONObject.fromObject(temp).get("accountingCode") == null||
						JSONObject.fromObject(temp).get("accountingCode") instanceof JSONNull||
						("/").equals(JSONObject.fromObject(temp).get("accountingCode"))||
						("").equals(JSONObject.fromObject(temp).get("accountingCode"))) {
						continue;
					}
					map.clear();
					map.put("accountingCode", JSONObject.fromObject(temp).get("accountingCode"));
					map.put("ingredientCode", JSONObject.fromObject(temp).get("accountingCode"));
					map.put("quantity", JSONObject.fromObject(temp).get("quantity")); //转换之前的报价计量单位
					map.put("convertAfterQuantity", this.asString("units"));//转换之后的报价计量单位
					map.put("inlandImport", this.asString("inlandImport"));
					list.add(totalCostAnalogyAnalysisService.listThisApplicationMerchandise(map));
				}
			}
		}
		this.forwardData(true, list, null);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.excel")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品总成本类比分析_".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = new HashMap<>();
		map.put("setUnits",this.asString("setUnits"));
		map.put("contrastUnits",this.asString("contrastUnits")==null||this.asString("contrastUnits").equals("null")?0:this.asDouble("contrastUnits"));
		map.put("isShowAllRemarks",this.asString("isShowAllRemarks"));
		map.put("isShowThisRemarks",this.asString("isShowThisRemarks"));
		map.put("isShowUnits",this.asString("isShowUnits"));
		map.put("isShowSubTotal",this.asString("isShowSubTotal"));
		map.put("isHideAllRemarks", this.asString("isHideAllRemarks"));
		map.put("inlandImport", this.asString("inlandImport"));
		map.put("data",this.asString("data"));
		//需要放置的参数
		this.totalCostAnalogyAnalysisService.exportSignedQtyExcel(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.totalcostanalogyanalysis.costcontrast.save")
	public void doSaveSearchDataForm() throws Exception {

		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.MCA);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("tableInfo", this.asString("data"));
		paraMap.put("info", this.asString("info"));
		paraMap.put("width", this.asString("width"));
		String msg =this.totalCostAnalogyAnalysisService.isnertReports(fileName,paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private AccountingBo getAccountingBo() throws Exception {
		AccountingBo totalcostanalogyanalysis = new AccountingBo();
		this.asBean(totalcostanalogyanalysis);
		return totalcostanalogyanalysis;
	}
}
