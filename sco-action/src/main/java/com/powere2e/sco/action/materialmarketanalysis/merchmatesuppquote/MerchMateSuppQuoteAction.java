package com.powere2e.sco.action.materialmarketanalysis.merchmatesuppquote;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants.myReportType;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuoteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuote;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品原料供应商报价 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月30日
 * @version 1.0
 */
public class MerchMateSuppQuoteAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2905952215286674331L;
	private MerchMateSuppQuoteService merchMateSuppQuoteService;

	@Override
	protected void beforeBuild() {
		merchMateSuppQuoteService = (MerchMateSuppQuoteService) ConfigFactory
				.getInstance().getBean("merchMateSuppQuoteService");
	}

	/**
	 * 显示商品原料供应商报价主界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote")
	public void doShowMerchMateSuppQuoteMain() throws Exception {
		this.forwardPage("sco/materialmarketanalysis/merchmatesuppquote/merchmatesuppquoteGrid.ftl");
	}

	/**
	 * 查询商品原料数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.listMerMat")
	public void doListMerchdiseMaterial() throws Exception {
		List<RelevanceMaterialAndWebsite> list = this.merchMateSuppQuoteService
				.listMerchandise(getRelMatWeb().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 查看商品原料历史价格
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.listMerMat")
	public void doListMerMateHisPrice() {
		RelevanceMaterialAndWebsite relMatWeb = this.getRelMateWebByJson();
		Map<String, Object> map = relMatWeb.toMap();
		map.put("list", this.getPriceDateRegion());
		map.put("endYear", this.asInteger("endYear") + 1 + "-01");
		List<MerchMateSuppQuote> list = this.merchMateSuppQuoteService.listMerMateHisPrice(map);
		this.putObject("dataList", list);
		this.putObject("dataListJson", JSONArray.fromObject(list).toString());
		this.putObject("relMatWeb", relMatWeb);
		
		Calendar cl = Calendar.getInstance();
		this.putObject("curYear", cl.get(Calendar.YEAR));//当前年
		this.putObject("curMonth", cl.get(Calendar.MONTH));//当前月(实际月份-1)
		this.forwardPage("sco/materialmarketanalysis/merchmatesuppquote/merchmatehistoryPrice.ftl");
	}

	/**
	 * 导出数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.exportMerMat")
	public void doExportMerMat() throws Exception {
		RelevanceMaterialAndWebsite relMatWeb = this.getRelMateWebByJson();
		Map<String, Object> map = relMatWeb.toMap();
		map.put("list", this.getPriceDateRegion());
		map.put("endYear", this.asInteger("endYear") + 1 + "-01");
		List<MerchMateSuppQuote> list = this.merchMateSuppQuoteService.listMerMateHisPrice(map);
		map.put("dataList", list);
		String fileName = relMatWeb.getMerchandiseName() + "-"
				+ relMatWeb.getSupplierName() + "-"
				+ relMatWeb.getIngredientCodeName() + "-"
				+ "-历史价格.xlsx";
		//4.生成excel
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.merchMateSuppQuoteService.exportMerMat(out, list, relMatWeb);
	}
	
	/**
	 * 保存报表文件
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.merchmatesuppquote.saveData")
	public void doSaveMerMat() throws Exception {
		//校验
		String fileName = this.asString("fileName");
		if (this.ifFileNameExists(myReportType.GSH.toString(), fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		
		RelevanceMaterialAndWebsite relMatWeb = this.getRelMateWebByJson();
		Map<String, Object> map = relMatWeb.toMap();
		map.put("list", this.getPriceDateRegion());
		map.put("endYear", this.asInteger("endYear") + 1 + "-01");
		List<MerchMateSuppQuote> list = this.merchMateSuppQuoteService.listMerMateHisPrice(map);
		map.put("dataList", list);
		map.put("dataListJson", JSONArray.fromObject(list).toString());
		map.put("relMatWeb", relMatWeb);
		
		Calendar cl = Calendar.getInstance();
		map.put("curYear", cl.get(Calendar.YEAR));//当前年
		map.put("curMonth", cl.get(Calendar.MONTH));//当前月(实际月份-1)
		this.merchMateSuppQuoteService.saveMerMat(fileName, map);
		this.forwardData(true, null, "报表保存成功");
	}
	
	/**
	 * 查询商品时获取前台参数
	 * 
	 * @return
	 * @throws Exception
	 */
	private RelevanceMaterialAndWebsite getRelMatWeb()
			throws Exception {
		RelevanceMaterialAndWebsite relMatWeb = new RelevanceMaterialAndWebsite();
		this.asBean(relMatWeb);
		return relMatWeb;
	}
	
	/**
	 * 查询商品时获取前台参数
	 * 
	 * @return
	 * @throws Exception
	 */
	private RelevanceMaterialAndWebsite getRelMateWebByJson() {
		String rows = this.asString("rows");
		RelevanceMaterialAndWebsite relMatWeb = new RelevanceMaterialAndWebsite();
		if (StringUtils.isNotBlank(rows)) {
			JSONArray jArray = JSONArray.fromObject(rows);
			relMatWeb = (RelevanceMaterialAndWebsite) JSONObject.toBean(
					jArray.getJSONObject(0),
					RelevanceMaterialAndWebsite.class);
		}
		return relMatWeb;
	}
	/**
	 * 获取前台日期所选日期的区间
	 * 
	 * @return
	 */
	private List<String[]> getPriceDateRegion() {
		int startYear = this.asInteger("startYear");
		int endYear = this.asInteger("endYear");
		String str = DateUtils.getDateRegionMonth(startYear, endYear);

		List<String[]> list = new ArrayList<String[]>();
		if (StringUtils.isBlank(str)) {
			return list;
		} else {
			str += ",";
			for (int i = 0; 120 * (i + 1) <= str.length(); i++) {
				list.add(str.substring(i * 120, (i + 1) * 120).split(","));
			}
		}
		return list;
	}
	
	/**
	 * 校验文件名称
	 * 
	 * @param reportType
	 *            报表类型
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 是否存在
	 */
	protected boolean ifFileNameExists(String reportType, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", reportType);//报表类型
		map.put("reportsName", fileName);//报表名称
		//校验文件名称
		return ReportsServiceImpl.getInstance().ifFileNameExists(map);
	}
}
