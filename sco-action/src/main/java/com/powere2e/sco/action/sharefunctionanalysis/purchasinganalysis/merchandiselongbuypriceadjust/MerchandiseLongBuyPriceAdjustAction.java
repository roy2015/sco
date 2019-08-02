package com.powere2e.sco.action.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust;

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
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandiselongbuypriceadjust.MerchandiseLongBuyPriceAdjust;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class MerchandiseLongBuyPriceAdjustAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MerchandiseLongBuyPriceAdjustService merchandiseLongBuyPriceAdjustService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseLongBuyPriceAdjustService = (MerchandiseLongBuyPriceAdjustService) ConfigFactory.getInstance().getBean("merchandiseLongBuyPriceAdjustService");
	}

	/**
	 * 显示商品进货界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doShowMerchandiseLongBuyPriceAdjustGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/merchandiseLongBuyPriceAdjust/merchandiseLongBuyPriceAdjustGrid.ftl");
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doListMerchandise() throws Exception {
		Map<String, Object> map = getMerchandiseLongBuyPriceAdjust().toMap();
		List<MerchandiseLongBuyPriceAdjust> list = merchandiseLongBuyPriceAdjustService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示商品进货明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doShowMerchandiseLongBuyPriceAdjustInfoGrid() throws Exception {
		this.putObject("merchandiseAndSupplierCodes", this.asString("merchandiseAndSupplierCodes"));
		this.putObject("minDate", this.asString("minDate"));
		this.putObject("maxDate", this.asString("maxDate"));
		this.putObject("show", this.asString("searchTable"));
		this.putObject("showName", this.asString("searchTable").equals("1") ? "只查定量装商品" : "只查非定量装商品");
		this.putObject("regionCode", this.asString("regionCode"));
		this.putObject("regionCode", StrUtils.concatStr(this.asString("regionCode").split(",")));
		this.putObject("warehouseCode", StrUtils.concatStr(this.asString("warehouseCode").split(",")));
		this.putObject("regionCodeSplit", this.asString("regionCode"));
		this.putObject("warehouseCodeSplit", this.asString("warehouseCode"));
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/merchandiseLongBuyPriceAdjust/merchandiseLongBuyPriceAdjustInfoGrid.ftl");
	}

	/**
	 * 商品进货调价明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doListMerchandiseLongBuyPriceAdjustDetailInfo() throws Exception {
		Map<String, Object> map = getMerchandiseLongBuyPriceAdjust().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		List<MerchandiseLongBuyPriceAdjust> list = merchandiseLongBuyPriceAdjustService.getMerchandiseLongBuyPriceAdjustDetailInfo(map, null);
		this.forwardData(true, list, null);
	}

	/**
	 * 商品进货汇总列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doListMerchandiseLongBuyPriceAdjustDetail() throws Exception {
		Map<String, Object> map = getMerchandiseLongBuyPriceAdjust().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		List<MerchandiseLongBuyPriceAdjust> list = merchandiseLongBuyPriceAdjustService.getMerchandiseLongBuyPriceAdjustSum(map, null);
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandiseLongBuyPriceAdjust getMerchandiseLongBuyPriceAdjust() throws Exception {
		MerchandiseLongBuyPriceAdjust MerchandiseLongBuyPriceAdjust = new MerchandiseLongBuyPriceAdjust();
		this.asBean(MerchandiseLongBuyPriceAdjust);
		return MerchandiseLongBuyPriceAdjust;
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doExportMerchandiseLongBuyPriceAdjustInfoToExcel() throws Exception {
		MerchandiseLongBuyPriceAdjust merchandiseLongBuyPriceAdjust = this.getMerchandiseLongBuyPriceAdjust();
		ServletOutputStream out = response.getOutputStream();
		String fileName = "进价调整报表-" + merchandiseLongBuyPriceAdjust.getMinDate() + "~" + merchandiseLongBuyPriceAdjust.getMaxDate() + "".concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = merchandiseLongBuyPriceAdjust.toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		map.put("warehouseCode", this.asString("warehouseCode").toString());
		map.put("sellRegion", this.asString("regionCode").toString());
		this.merchandiseLongBuyPriceAdjustService.exportMerchandiseLongBuyPriceAdjust(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandiseLongBuyPriceAdjust")
	public void doSaveSearchDataForm() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.LTP);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getMerchandiseLongBuyPriceAdjust().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		paraMap.put("list", codeList);
		paraMap.put("warehouseCode", this.asString("warehouseCode").toString());
		paraMap.put("sellRegion", this.asString("regionCode").toString());
		String msg = this.merchandiseLongBuyPriceAdjustService.saveMerchandiseLongBuyPriceAdjust(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
}
