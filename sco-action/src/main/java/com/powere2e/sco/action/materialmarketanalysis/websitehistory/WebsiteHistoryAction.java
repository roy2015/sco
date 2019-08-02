package com.powere2e.sco.action.materialmarketanalysis.websitehistory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants.myReportType;
import com.powere2e.sco.common.utils.BigDecimalConverter;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.websitehistory.WebsiteHistoryService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;
import com.powere2e.sco.service.impl.common.MasterDataTypeServiceImpl;
import com.powere2e.sco.service.impl.masterdata.MerchandiseRoleServiceImpl;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 公示网站原料历史行情 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public class WebsiteHistoryAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6292562580796719802L;
	private WebsiteHistoryService websiteHistoryService;

	@Override
	protected void beforeBuild() {
		websiteHistoryService = (WebsiteHistoryService) ConfigFactory
				.getInstance().getBean("websiteHistoryService");
	}

	/**
	 * 公示网站原料历史行情主界面
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory")
	public void doShowWebsiteHistoryMain() throws Exception {
		this.forwardPage("sco/materialmarketanalysis/websitehistory/websiteHistoryGrid.ftl");
	}

	/**
	 * 查看历史价格
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.listHistPrice")
	public void doListHisPrice() throws Exception {
		List<WebsiteHistory> dataList = this.searchHistData();
		this.putObject("dataList", dataList);
		this.putObject("showChart", this.asString("showChart"));
		this.putObject("dataListJson",
				JSONArray.fromObject(dataList, BigDecimalConverter.setBigDecimalDefaultValue()).toString());
		this.forwardPage("sco/materialmarketanalysis/websitehistory/historyPrice.ftl");
	}

	/**
	 * 导出历史价格数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.exportHistPrice")
	public void doExportHisPrice() throws Exception {
		Material mt = this.getMaterial();
		Map<String, Object> pubMap = mt.toMap();
		pubMap.put("priceRegionName", this.asString("priceRegionName"));
		
		Integer startYear = this.asInteger("startYear");
		Integer endYear = this.asInteger("endYear");
		String fileName = mt.getMaterialName() + "-" + startYear
				+ "-" + endYear + "-历史价格.xlsx";
		//4.生成excel
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.websiteHistoryService.exportHisPrice(out, this.searchHistData(), pubMap);
	}
	
	/**
	 * 导出商品数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.exportHistPrice")
	public void doExportHisMerchandise() throws Exception {
		RelevanceMaterialAndWebsite relWeb = this.getRelMatWeb();
		Map<String, Object> pubMap = relWeb.toMap();
		pubMap.put("priceRegionName", this.asString("priceRegionName"));
		List<RelevanceMaterialAndWebsite> list = this.websiteHistoryService
				.listMerchandise(pubMap, null);
		String fileName = relWeb.getMaterialName() + "-商品列表.xlsx";
		
		//4.生成excel
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		this.websiteHistoryService.exportHisMerchandise(out, list, pubMap);
	}
	
	/***
	 * 保存历史价格文件
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.saveHistPrice")
	public void doSaveHistoryPrice() throws Exception {
		//校验
		String fileName = this.asString("fileName");
		if (this.ifFileNameExists(myReportType.PSH.toString(), fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		
		Material mt = this.getMaterial();
		Map<String, Object> pubMap = mt.toMap();
		pubMap.put("priceRegionName", this.asString("priceRegionName"));
		pubMap.put("showChart", this.asString("showChart"));
			
		this.websiteHistoryService.saveHistoryPrice(fileName, this.searchHistData(), pubMap);
		this.forwardData(true, null, "报表保存成功");
	}
	
	/**
	 * 显示商品列表界面
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.searchMer")
	public void doShowSearchMerMain() throws Exception {
		this.putObject("dxList", MerchandiseRoleServiceImpl.getInstance().listQualitative(null));
		this.putObject("dlList", MerchandiseRoleServiceImpl.getInstance().listQuantify(null));
		this.putObject("cList", MasterDataTypeServiceImpl.getInstance().listCenterType(null));
		this.putObject("sList", MasterDataTypeServiceImpl.getInstance().listSmallType(null));
		this.putObject("dList", MasterDataTypeServiceImpl.getInstance().listDetailType(null));
		this.putObject("fList", MasterDataTypeServiceImpl.getInstance().listFineType(null));
		this.forwardPage("sco/materialmarketanalysis/websitehistory/merMaterial.ftl");
	}
	
	/**
	 * 查询商品
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.searchMer")
	public void doListMerchandise() throws Exception {
		List<RelevanceMaterialAndWebsite> list = this.websiteHistoryService
				.listMerchandise(this.getRelMatWeb().toMap(), this.getPageInfo());
		this.forwardData(true, list, null);
	}
	
	/**
	 * 保存商品数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.materialmarketAnalysis.websitehistory.saveHistPrice")
	public void doSaveHistoryMerchandise() throws Exception {
		//校验
		String fileName = this.asString("fileName");
		if (this.ifFileNameExists(myReportType.PSH.toString(), fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		
		RelevanceMaterialAndWebsite relWeb = this.getRelMatWeb();
		Map<String, Object> pubMap = relWeb.toMap();
		pubMap.put("priceRegionName", this.asString("priceRegionName"));
			
		this.websiteHistoryService.saveHistoryMerchandise(fileName,  this.websiteHistoryService
				.listMerchandise(pubMap, null), pubMap);
		this.forwardData(true, null, "报表保存成功");
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Material getMaterial() throws Exception {
		Material material = new Material();
		this.asBean(material);
		return material;
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
	 * 获取前台参数并查询历史价格数据
	 * 
	 * @return
	 * @throws Exception
	 */
	private List<WebsiteHistory> searchHistData() throws Exception {
		Map<String, Object> map = this.getMaterial().toMap();
		Integer startYear = this.asInteger("startYear");
		Integer endYear = this.asInteger("endYear");
		
		List<WebsiteHistory> dataList = new ArrayList<WebsiteHistory>();
		if (startYear != null && endYear != null) {
			String dateStr = DateUtils.getDateRegionMonth(startYear - 1, endYear);
			if (StringUtils.isNotBlank(dateStr)) {
				map.put("startYear", startYear + "-01-01");
				map.put("endYear", (endYear + 1) + "-01-01");
				map.put("list", dateStr.split(","));
				dataList = websiteHistoryService.listHisPrice(map, null);
			}
		}
		return dataList;
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
