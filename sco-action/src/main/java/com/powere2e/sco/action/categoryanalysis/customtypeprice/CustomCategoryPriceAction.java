package com.powere2e.sco.action.categoryanalysis.customtypeprice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONNull;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.action.categoryanalysis.CategoryPriceAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearService;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;

/**
 * 自定义商品价格带 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年07月08日
 */
public class CustomCategoryPriceAction extends CategoryPriceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1844392108680494098L;
	
	/**
	 * 显示自定义商品价格带主界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice")
	public void doShowCustomCategoryPriceMain() throws Exception {
		this.forwardPage("sco/categoryanalysis/customtypeprice/customCategoryPriceGrid.ftl");
	}
	
	/**
	 * 查询商品
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.searchMerchandise")
	public void doListMerchandise() throws Exception {
		SellYearOnYearService sellYearService = (SellYearOnYearService) ConfigFactory
				.getInstance().getBean("sellYearOnYearService");
		List<SellYearOnYear> list = sellYearService.listSellYearOnYear(super
				.getCategoryPriceMap().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 查询所有商品价格带数据
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.viewAllCategory")
	public void doListCollectData() throws Exception {
		Map<String, Object> paraMap = super.generateAllExportAndSavePara();
		Map<String, Object> pubMap = (Map<String, Object>) paraMap.get("pubMap");
		pubMap.put("merTitle", "所有商品：自选商品");
		pubMap.put("module", "CM");
		
		this.putObject("allList", JSONArray.fromObject(paraMap).toString().replaceAll("\"", "%"));
		this.generateParamByJson(paraMap);//处理前台页面所需参数
		this.putObject("paraMap", paraMap);
		this.forwardPage("sco/categoryanalysis/customtypeprice/allCategoryForm.ftl");
	}
	
	/**
	 * 所有价格带导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.exportCategory")
	@SuppressWarnings("unchecked")
	public void doExportAllCategoryToExcel() throws Exception {
		try {
			String rows = this.asString("rows");
			if (StringUtils.isBlank(rows)) return;
			
			/*JSONArray jsonarray = JSONArray.fromObject(rows);  
			JSONObject jo = (JSONObject)jsonarray.get(0);*/
			com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(rows);
			com.alibaba.fastjson.JSONObject jo = (com.alibaba.fastjson.JSONObject) jsonarray.get(0);
			Map<String, Object> pubMap = (Map<String, Object>) jo.get("pubMap");
			
			String tol1 = jo.getString("tol1");
//			List<CategoryPrice> data1 = (List<CategoryPrice>) JSONArray.toCollection(jo.getJSONArray("data1"), CategoryPrice.class);
			List<CategoryPrice> data1 = (List<CategoryPrice>) com.alibaba.fastjson.JSONArray
					.parseArray(jo.getString("data1"), CategoryPrice.class);

			String tol2 = jo.getString("tol2");
			Object obj = jo.get("data2");
			List<CategoryPrice> data2 = null;
			if (!JSONNull.getInstance().equals(obj)) {
//				data2 = (List<CategoryPrice>) JSONArray.toCollection(jo.getJSONArray("data2"), CategoryPrice.class);
				data2 = (List<CategoryPrice>) com.alibaba.fastjson.JSONArray.parseArray(jo.getString("data2"), CategoryPrice.class);
			}
			
			String date1 = pubMap.get("searchDate1").toString();
			String fileName = "自选商品价格带汇总表-".concat(pubMap.get("regionName").toString()).concat("-")
					.concat(date1).concat(".xlsx");
			ServletOutputStream out = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
			
			super.categoryPriceService.exportAllCategoryToExcel(tol1, data1, tol2, data2, pubMap, out);
		} catch (Exception e) {
			this.forwardData(false, null, null);
		}
		this.forwardData(true, null, null);
	}

	/**
	 * 保存所有价格带报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.saveCategory")
	public void doSaveAllCategory() throws Exception {
		String rows = this.asString("rows");
		if (StringUtils.isBlank(rows)) return;
		
		String reportType = BusinessConstants.myReportType.CMP.toString(); 
		String fileName = this.asString("fileName");
		if (super.ifFileNameExists(reportType, fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		
		String msg = super.categoryPriceService.completeSaveAllCategory(fileName,
				"customCategory", reportType, this.generateParamByJson(rows));
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		} else {
			this.forwardData(false, null, msg);
			return;
		}
	}
	
	/**
	 * 前台页面所需参数
	 * 
	 * @param paraMap
	 */
	@SuppressWarnings("unchecked")
	public void generateParamByJson(Map<String, Object> paraMap) {
		Object data1 = paraMap.get("data1");
		Object data2 = paraMap.get("data2");
		if (data1 != null) {
			List<CategoryPrice> d1 = (List<CategoryPrice>) data1;
			paraMap.put("d1Size", d1.size());
			paraMap.put("data1Json", JSONArray.fromObject(data1).toString());
		} else {
			paraMap.put("d1Size", 0);
			paraMap.put("data1Json", "[]");
		}
		if (data2 != null) {
			List<CategoryPrice> d2 = (List<CategoryPrice>) data2;
			paraMap.put("d2Size", d2.size());
			paraMap.put("data2Json", JSONArray.fromObject(data2).toString());
		} else {
			paraMap.put("d2Size", 0);
			paraMap.put("data2Json", "[]");
		}
	}
	
	/**
	 * 生成模板所需参数
	 * 
	 * @param rows
	 *            json对象
	 * @param paraMap
	 *            所需参数
	 * @return 参数
	 */
	private Map<String, Object> generateParamByJson(String rows) {
//		JSONArray jsonarray = JSONArray.fromObject(rows);
		com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(rows);
		Map<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.putAll((Map<String, Object>) (JSONObject)jsonarray.get(0));
		paraMap.putAll((Map<String, Object>) (com.alibaba.fastjson.JSONObject)jsonarray.get(0));
		Object obj = paraMap.get("data1");
		if (!JSONNull.getInstance().equals(obj)) {
			paraMap.put("data1Json", JSONArray.fromObject(obj));
			paraMap.put("flag1", true);
		} 
		
		obj = paraMap.get("data2");
		if (!JSONNull.getInstance().equals(obj)) {
			paraMap.put("data2Json", JSONArray.fromObject(paraMap.get("data2")));
			paraMap.put("flag2", true);
		}
		return paraMap; 
	}
	
	/**
	 * 查看明细数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.viewDetCategory")
	@SuppressWarnings("unchecked")
	public void doListDetailData() throws Exception {
		Map<String, Object> paraMap = super.generateDetExportAndSavePara();
		Map<String, Object> pubMap = (Map<String, Object>) paraMap.get("pubMap");
		pubMap.put("merTitle", "所有商品：自选商品");
		pubMap.put("module", "CM");
		
		this.putObject("allList", JSONArray.fromObject(paraMap).toString().replaceAll("\"", "%"));
		this.generateParamByJson(paraMap);
		this.putObject("paraMap", paraMap);
		this.forwardPage("sco/categoryanalysis/customtypeprice/detailCategoryForm.ftl");
	}
	
	/**
	 * 导出明细数据到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.exportCategory")
	@SuppressWarnings("unchecked")
	public void doExportDetCategoryToExcel() throws Exception {
		try {
			String rows = this.asString("rows");
			if (StringUtils.isBlank(rows)) return;
			
			/*JSONArray jsonarray = JSONArray.fromObject(rows);  
			JSONObject jo = (JSONObject)jsonarray.get(0);*/
			com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(rows);
			com.alibaba.fastjson.JSONObject jo = (com.alibaba.fastjson.JSONObject) jsonarray.get(0);
			Map<String, Object> pubMap = (Map<String, Object>) jo.get("pubMap");
			
//			List<CategoryPrice> data1 = (List<CategoryPrice>) JSONArray.toCollection(jo.getJSONArray("data1"), CategoryPrice.class);
			List<CategoryPrice> data1 = (List<CategoryPrice>) com.alibaba.fastjson.JSONArray
					.parseArray(jo.getString("data1"), CategoryPrice.class);
			
			Object obj = jo.get("data2");
			List<CategoryPrice> data2 = null;
			if (!JSONNull.getInstance().equals(obj)) {
//				data2 = (List<CategoryPrice>) JSONArray.toCollection(jo.getJSONArray("data2"), CategoryPrice.class);
				data2 = (List<CategoryPrice>) com.alibaba.fastjson.JSONArray
						.parseArray(jo.getString("data2"), CategoryPrice.class);
			}
			
			String date1 = pubMap.get("searchDate1").toString();
			String fileName = "自选商品价格带明细表-".concat(pubMap.get("regionName").toString()).concat("-")
					.concat(date1).concat(".xlsx");
			ServletOutputStream out = response.getOutputStream();
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
			
			super.categoryPriceService.exportDetCategoryToExcel(data1, data2, pubMap, out);
		} catch (Exception e) {
			this.forwardData(false, null, null);
		}
		this.forwardData(true, null, null);
	}
	
	/**
	 * 保存明细报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.customCategoryPrice.saveCategory")
	public void doSaveDetCategory() throws Exception {
		String rows = this.asString("rows");
		if (StringUtils.isBlank(rows)) return;
		
		String reportType = BusinessConstants.myReportType.CMP.toString(); 
		String fileName = this.asString("fileName");
		if (super.ifFileNameExists(reportType, fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		
		String msg = super.categoryPriceService.completeSaveDetCategory(
				fileName, "customCategory", reportType, generateParamByJson(rows));
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		} else {
			this.forwardData(false, null, msg);
			return;
		}
	}
	
}
