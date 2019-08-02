package com.powere2e.sco.action.sharefunctionanalysis.purchasinganalysis.accessorystockdetail;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetailService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisestockdetail.MerchandiseStockDetail;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class AccessoryStockDetailAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MerchandiseStockDetailService merchandiseStockDetailService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseStockDetailService = (MerchandiseStockDetailService) ConfigFactory.getInstance().getBean("merchandiseStockDetailService");
	}

	/**
	 * 显示商品进货界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doShowAccessoryStockDetailGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/accessoryStockDetail/accessoryStockDetailGrid.ftl");
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doListMerchandise() throws Exception {
		Map<String, Object> map = getAccessoryStockDetail().toMap();
		List<MerchandiseStockDetail> list = merchandiseStockDetailService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示商品进货明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doShowAccessoryStockDetailInfoGrid() throws Exception {
		this.putObject("merchandiseAndSupplierCodes", this.asString("merchandiseAndSupplierCodes"));
		this.putObject("minDateShow", this.asString("searchType").equals("day") ?this.asString("minDate"):this.asString("minDate").substring(0, 7));
		this.putObject("maxDateShow", this.asString("searchType").equals("day") ?this.asString("maxDate"):this.asString("maxDate").substring(0, 7));
		this.putObject("minDate", this.asString("minDate"));
		this.putObject("maxDate", this.asString("maxDate"));
		this.putObject("show", this.asString("searchTable"));
		this.putObject("type", this.asString("searchType"));
		this.putObject("typeName", this.asString("searchType").equals("day") ? "查看日明细" : "查看月明细");
		this.putObject("regionCode", StrUtils.concatStr(this.asString("regionCode").split(",")));
		this.putObject("warehouseCode", StrUtils.concatStr(this.asString("warehouseCode").split(",")));
		this.putObject("regionCodeSplit", this.asString("regionCode"));
		this.putObject("warehouseCodeSplit", this.asString("warehouseCode"));
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/accessoryStockDetail/accessoryStockDetailInfoGrid.ftl");
	}

	/**
	 * 商品进货明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doListAccessoryStockDetailDetailInfo() throws Exception {
		Map<String, Object> map = getAccessoryStockDetail().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		map.put("show", "4");
		List<MerchandiseStockDetail> list = merchandiseStockDetailService.listMerchandiseStockDetailInfo(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseStockDetail getAccessoryStockDetail() throws Exception {
		MerchandiseStockDetail AccessoryStockDetail = new MerchandiseStockDetail();
		this.asBean(AccessoryStockDetail);
		return AccessoryStockDetail;
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doExportAccessoryStockInfoToExcel() throws Exception {
		MerchandiseStockDetail accessoryStockDetail = this.getAccessoryStockDetail();
		ServletOutputStream out = response.getOutputStream();
		String fileName = "进货明细表-" + accessoryStockDetail.getMinDate() + "~" + accessoryStockDetail.getMaxDate() + "".concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = accessoryStockDetail.toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		map.put("show", "4");
		map.put("warehouseCode", this.asString("warehouseCode").toString());
		map.put("sellRegion", this.asString("regionCode").toString());
		map.put("minDateShow", this.asString("type").equals("day") ?this.asString("minDate"):this.asString("minDate").substring(0, 7));
		map.put("maxDateShow", this.asString("type").equals("day") ?this.asString("maxDate"):this.asString("maxDate").substring(0, 7));
		this.merchandiseStockDetailService.exportMerchandiseStockDetail(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.accessoryStockDetail")
	public void doSaveSearchDataForm() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.STD);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getAccessoryStockDetail().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		paraMap.put("list", codeList);
		paraMap.put("show", "4");
		paraMap.put("warehouseCode", this.asString("warehouseCode").toString());
		paraMap.put("sellRegion", this.asString("regionCode").toString());
		paraMap.put("minDateShow", this.asString("type").equals("day") ?this.asString("minDate"):this.asString("minDate").substring(0, 7));
		paraMap.put("maxDateShow", this.asString("type").equals("day") ?this.asString("maxDate"):this.asString("maxDate").substring(0, 7));
		String msg = this.merchandiseStockDetailService.saveMerchandiseStockDetail(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
}
