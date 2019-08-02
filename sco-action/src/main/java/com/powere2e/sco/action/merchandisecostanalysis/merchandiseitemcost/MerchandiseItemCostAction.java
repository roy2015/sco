package com.powere2e.sco.action.merchandisecostanalysis.merchandiseitemcost;

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
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.merchandiseitemcost.MerchandiseItemCostService;
import com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost.Merchandiseitemcost;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品成本分析的WEB请求响应类
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年3月16日
 */
public class MerchandiseItemCostAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915705722020454704L;
	private MerchandiseItemCostService merchandiseItemCostService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		merchandiseItemCostService = (MerchandiseItemCostService) ConfigFactory.getInstance().getBean("merchandiseItemCostService");
	}

	/**
	 * 显示商品分项成本类比页面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.merchandiseitemcost")
	public void doShowMerchandiseItemCostGrid() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/merchandiseItemCost/merchandiseitemcostGrid.ftl");
	}

	/**
	 * 商品分项成本类比列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.merchandiseitemcost")
	public void doListMerchandiseItemCost() throws Exception {
		Map<String, Object> map = getMerchandiseitemcost().toMap();
		map.put("minQuotedDate", this.asDate("minQuotedDate"));
		map.put("maxQuotedDate", this.asDate("maxQuotedDate"));
		map.put("itemValue", this.asString("itemValue"));
		map.put("costValue", this.asString("costValue"));
		map.put("selectComboxValue", this.asString("selectComboxValue"));
		List<Merchandiseitemcost> list = new ArrayList<>();
		list = merchandiseItemCostService.searchMechandiseItemCost(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.merchandiseitemcost")
	public void doExportDataToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String type ="";
		switch (this.asString("itemValue")) {
		case "material":
			type = "原材料";
			break;
		case "npackag":
			type = "内包装";
			break;
		case "wpackag":
			type = "外包装";
			break;
		case "elsecost":
			type = "其他成本";
			break;
		case "importcost":
			type = "进口相关成本";
			break;
		default:
			break;
		}
		String fileName = ("商品分项成本类比分析_"+type+"_").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getMerchandiseitemcost().toMap();
		map.put("minQuotedDate", this.asDate("minQuotedDate"));
		map.put("maxQuotedDate", this.asDate("maxQuotedDate"));
		map.put("itemValue", this.asString("itemValue"));
		map.put("costValue", this.asString("costValue"));
		map.put("selectComboxValue", this.asString("selectComboxValue"));
		this.merchandiseItemCostService.exportSignedQtyExcel(map, out);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.merchandiseitemcost")
	public void doSaveSearchDataForm() throws Exception {

		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.MAA);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getMerchandiseitemcost().toMap();
		paraMap.put("minQuotedDate", this.asDate("minQuotedDate"));
		paraMap.put("maxQuotedDate", this.asDate("maxQuotedDate"));
		paraMap.put("itemValue", this.asString("itemValue"));
		paraMap.put("costValue", this.asString("costValue"));
		paraMap.put("selectComboxValue", this.asString("selectComboxValue"));
		String msg = this.merchandiseItemCostService.saveSearchDataForm(fileName, paraMap);
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
	private Merchandiseitemcost getMerchandiseitemcost() throws Exception {
		Merchandiseitemcost merchandiseitemcost = new Merchandiseitemcost();
		this.asBean(merchandiseitemcost);
		return merchandiseitemcost;
	}
}
