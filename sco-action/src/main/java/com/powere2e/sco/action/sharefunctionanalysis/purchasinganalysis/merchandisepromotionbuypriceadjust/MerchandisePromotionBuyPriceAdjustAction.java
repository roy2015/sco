package com.powere2e.sco.action.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

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
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjustService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust.MerchandisePromotionBuyPriceAdjust;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

public class MerchandisePromotionBuyPriceAdjustAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MerchandisePromotionBuyPriceAdjustService merchandisePromotionBuyPriceAdjustService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandisePromotionBuyPriceAdjustService = (MerchandisePromotionBuyPriceAdjustService) ConfigFactory.getInstance().getBean("merchandisePromotionBuyPriceAdjustService");
	}

	/**
	 * 显示商品进货界面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
	public void doShowMerchandisePromotionBuyPriceAdjustGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/merchandisePromotionBuyPriceAdjust/merchandisePromotionBuyPriceAdjustGrid.ftl");
	}

	/**
	 * 商品列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
	public void doListMerchandise() throws Exception {
		Map<String, Object> map = getMerchandisePromotionBuyPriceAdjust().toMap();
		List<MerchandisePromotionBuyPriceAdjust> list = merchandisePromotionBuyPriceAdjustService.listMerchandise(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 显示商品促销进价明细列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
	public void doShowMerchandisePromotionBuyPriceAdjustInfoGrid() throws Exception {
		this.putObject("merchandiseAndSupplierCodes", this.asString("merchandiseAndSupplierCodes"));
		this.putObject("minDate", this.asString("minDate"));
		this.putObject("maxDate", this.asString("maxDate"));
		this.putObject("show", this.asString("searchTable"));
		this.putObject("showName", this.asString("searchTable").equals("1") ? "只查定量装商品" : "只查非定量装商品");
		this.putObject("warehouseCode", StrUtils.concatStr(this.asString("warehouseCode").split(",")));
		this.putObject("warehouseCodeSplit", this.asString("warehouseCode"));
		this.forwardPage("sco/shareFunctionAnalysis/purchasingAnalysis/merchandisePromotionBuyPriceAdjust/merchandisePromotionBuyPriceAdjustInfoGrid.ftl");
	}

	/**
	 * 商品促销进价列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
	public void doListMerchandisePromotionBuyPriceAdjustDetailInfo() throws Exception {
		Map<String, Object> map = getMerchandisePromotionBuyPriceAdjust().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		List<MerchandisePromotionBuyPriceAdjust> list = merchandisePromotionBuyPriceAdjustService.getMerchandisePromotionBuyPriceAdjust(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private MerchandisePromotionBuyPriceAdjust getMerchandisePromotionBuyPriceAdjust() throws Exception {
		MerchandisePromotionBuyPriceAdjust MerchandisePromotionBuyPriceAdjust = new MerchandisePromotionBuyPriceAdjust();
		this.asBean(MerchandisePromotionBuyPriceAdjust);
		return MerchandisePromotionBuyPriceAdjust;
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
	public void doExporterchandisePromotionBuyPriceAdjustInfoToExcel() throws Exception {
		MerchandisePromotionBuyPriceAdjust merchandisePromotionBuyPriceAdjust = this.getMerchandisePromotionBuyPriceAdjust();
		ServletOutputStream out = response.getOutputStream();
		String fileName = "商品促销进价调整报表-" + merchandisePromotionBuyPriceAdjust.getMinDate() + "~" + merchandisePromotionBuyPriceAdjust.getMaxDate() + "".concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = merchandisePromotionBuyPriceAdjust.toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("list", codeList);
		map.put("warehouseCode", this.asString("warehouseCode").toString());
		this.merchandisePromotionBuyPriceAdjustService.exportMerchandisePromotionBuyPriceAdjust(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis.purchasingAnalysis.merchandisePromotionBuyPriceAdjust")
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
		Map<String, Object> paraMap = this.getMerchandisePromotionBuyPriceAdjust().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");// 商品和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		paraMap.put("list", codeList);
		paraMap.put("warehouseCode", this.asString("warehouseCode").toString());
		String msg = this.merchandisePromotionBuyPriceAdjustService.saveMerchandisePromotionBuyPriceAdjust(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
}
