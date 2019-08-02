package com.powere2e.sco.action.sharefunctionanalysis.sellanalysis.selldetail;

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
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selldetail.SellDetailService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail.SellDetail;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class SellDetailAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private SellDetailService sellDetailService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		sellDetailService = (SellDetailService) ConfigFactory.getInstance().getBean("sellDetailService");
	}

	/**
	 * 显示销售明细界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doShowSellDetailGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/sellDetail/sellDetailGrid.ftl");
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doListSellDetail() throws Exception {
		Map<String, Object> map = getSellDetail().toMap();
		map.put("show", this.asString("show"));
		String purchaseDepartments=this.asString("purchaseDepartments");
		if(purchaseDepartments!=null){
			map.put("purchaseDepartments", this.asString("purchaseDepartments").equals("IMPORT")?"国际":this.asString("purchaseDepartments").equals("INLAND")?"国内":"");
		}
		List<SellDetail> list = sellDetailService.getMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 销售明细报表
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doShowSellDetailInfo() throws Exception {
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("minDate", this.asString("minDate"));
		this.putObject("maxDate", this.asString("maxDate"));
		this.putObject("show", this.asString("show"));
		this.putObject("dl", this.asString("dl"));
		this.putObject("sellRegion", this.asString("sellRegion"));
		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/sellDetail/sellDetailInfoGrid.ftl");
	}

	/**
	 * 销售明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doListSellDetailInfo() throws Exception {
		Map<String, Object> map = getSellDetail().toMap();
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
		map.put("show", this.asString("show"));
		map.put("dl", this.asString("dl"));
		List<SellDetail> list = sellDetailService.getSellDetail(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 销售明细汇总列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doListSellDetailInfos() throws Exception {
		Map<String, Object> map = getSellDetail().toMap();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		map.put("show", this.asString("show"));
		map.put("dl", this.asString("dl"));
		map.put("merchandise", merchandiseList);
		List<SellDetail> list = sellDetailService.getSellDetailSum(map, this.getPageInfo());
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setMinDate(this.asString("minDate"));
			list.get(i).setMaxDate(this.asString("maxDate"));
		}
		this.forwardData(true, list, null);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "销售明细表-".concat(this.asString("minDate")+"~"+this.asString("maxDate")).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getSellDetail().toMap();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		map.put("show", this.asString("show"));
		map.put("dl", this.asString("dl"));
		map.put("merchandise", merchandiseList);
		this.sellDetailService.exportSellDetail(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.sellAnalysis.sellDetail")
	public void doSaveSearchDataForm() throws Exception {

		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.SDT);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getSellDetail().toMap();
		String[] supplierCode = this.asString("supplierCode").split(",");
		String[] merchandiseCode = this.asString("merchandiseCode").split(",");
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
		}
		paraMap.put("show", this.asString("show"));
		paraMap.put("dl", this.asString("dl"));
		paraMap.put("merchandise", merchandiseList);
		String msg = this.sellDetailService.saveSellDetail(fileName, paraMap);
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
	private SellDetail getSellDetail() throws Exception {
		SellDetail SellDetail = new SellDetail();
		this.asBean(SellDetail);
		return SellDetail;
	}
}
