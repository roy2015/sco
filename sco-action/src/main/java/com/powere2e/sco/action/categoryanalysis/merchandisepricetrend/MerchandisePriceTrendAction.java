package com.powere2e.sco.action.categoryanalysis.merchandisepricetrend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.categoryanalysis.merchandisepricetrend.MerchandisePriceTrendService;
import com.powere2e.sco.model.categoryanalysis.merchandisepricetrend.MerchandisePriceTrend;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品价格趋势的WEB请求响应类
 * 
 * @author Len.zhao
 * @version 1.0
 * @since 2015年5月7日
 */
public class MerchandisePriceTrendAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1407276839406651169L;
	private MerchandisePriceTrendService merchandisePriceTrendService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandisePriceTrendService = (MerchandisePriceTrendService) ConfigFactory.getInstance().getBean("merchandisePriceTrendService");
	}

	/**
	 * 商品查询页面
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doShowMerchandisePriceTrendGrid() throws Exception {
		this.forwardPage("sco/categoryanalysis/merchandisepricetrend/merchandisePriceTrendGrid.ftl");
	}

	/**
	 * 显示价格趋势页面
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doShowPriceTrendDetail() throws Exception {

		this.putObject("itemValue", this.asString("itemValue"));
		this.putObject("regionCode", this.asString("regionCode"));
		this.putObject("regionName", this.asString("regionCode").equals("all") ? "全国" : this.merchandisePriceTrendService.listRegion(this.asString("regionCode")).get(0).getText());
		this.putObject("qlStartDate", this.asString("qlStartDate"));
		this.putObject("qlEndDate", this.asString("qlEndDate"));
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.forwardPage("sco/categoryanalysis/merchandisepricetrend/priceTrendDetail.ftl");
	}

	/**
	 * 商品信息列表
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doListMerchandiseData() throws Exception {
		Map<String, Object> map = this.getMerchandisePriceTrend().toMap();
		String purchaseDepartments = this.asString("purchaseDepartments");
		if (purchaseDepartments != null) {
			map.put("purchaseDepartments", this.asString("purchaseDepartments").equals("IMPORT") ? "国际" : this.asString("purchaseDepartments").equals("INLAND") ? "国内" : "");
		}
		List<MerchandisePriceTrend> list = this.merchandisePriceTrendService.listMerchandiseData(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 商品价格趋势列表
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doListMerchandisePriceTrend() throws Exception {
		Map<String, Object> map = this.getMerchandisePriceTrend().toMap();
		map.put("show", this.asString("itemValue"));
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		map.put("merchandise", merchandiseList);
		map.put("regionCode", this.asString("regionCode"));
		map.put("qlStartDate", this.asString("qlStartDate"));
		map.put("qlEndDate", this.asString("qlEndDate"));
		List<MerchandisePriceTrend> list = this.merchandisePriceTrendService.listMerchandisePriceTrend(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 导出Excel文件
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品价格趋势-".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getMerchandisePriceTrend().toMap();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		map.put("show", this.asString("itemValue"));
		map.put("merchandise", merchandiseList);
		map.put("regionCode", this.asString("regionCode"));
		map.put("qlStartDate", this.asString("qlStartDate"));
		map.put("qlEndDate", this.asString("qlEndDate"));
		this.merchandisePriceTrendService.exportPriceTrendExcel(map, out);
	}

	/**
	 * 保存文件
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.merchandisepricetrend")
	public void doSaveSearchDataForm() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> mp = new HashMap<String, Object>();
		mp.put("reportsTypeCode", BusinessConstants.myReportType.MPT);// 报表类型
		mp.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(mp);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> map = this.getMerchandisePriceTrend().toMap();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		map.put("show", this.asString("itemValue"));
		map.put("merchandise", merchandiseList);
		map.put("merchandiseCode", this.asString("merchandiseCode"));
		map.put("supplierCode", this.asString("supplierCode"));
		map.put("regionCode", this.asString("regionCode"));
		map.put("qlStartDate", this.asString("qlStartDate"));
		map.put("qlEndDate", this.asString("qlEndDate"));
		String msg = this.merchandisePriceTrendService.saveSearchDataForm(fileName, map);
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
	private MerchandisePriceTrend getMerchandisePriceTrend() throws Exception {
		MerchandisePriceTrend merchandisePriceTrend = new MerchandisePriceTrend();
		this.asBean(merchandisePriceTrend);
		return merchandisePriceTrend;
	}
}
