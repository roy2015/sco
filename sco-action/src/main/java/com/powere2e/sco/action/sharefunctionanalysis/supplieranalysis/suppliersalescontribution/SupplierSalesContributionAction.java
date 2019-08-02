package com.powere2e.sco.action.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import org.apache.commons.lang.StringUtils;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContributionService;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContribution;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 供应商销售贡献度排行
 * @author Joyce.li
 *  @since 2015年8月4日 下午3:55:29
 *  @version 1.0
 */
public class SupplierSalesContributionAction extends WorkAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private SupplierSalesContributionService supplierSalesContributionService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierSalesContributionService = (SupplierSalesContributionService) ConfigFactory.getInstance().getBean("supplierSalesContributionService");
	}

	/**
	 * 显示销售同比列表页面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doShowSupplierSalesContributionGrid() throws Exception {
		this.forwardPage("sco/shareFunctionAnalysis/supplierAnalysis/supplierSalesContribution/supplierSalesContributionGrid.ftl");
	}

	/**
	 * 查询销售同比列表数据
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doListSupplierSalesContribution() throws Exception {
		Map<String, Object> map = getSupplierSalesContribution().toMap();
		String minDate = this.asString("minDate");
		String maxDate = this.asString("maxDate");
		map.put("minDate", minDate);
		map.put("maxDate", maxDate);

		List<SupplierSalesContribution> list = supplierSalesContributionService.querySupplierSalesContribution(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doSaveSalesContributionToHtml() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.SSR);// 报表类型(商品供应商销售贡献度排行)
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换!");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getSupplierSalesContribution().toMap();
		String minDate = this.asString("minDate");
		String maxDate = this.asString("maxDate");
		paraMap.put("minDate", minDate);
		paraMap.put("maxDate", maxDate);

		String msg = supplierSalesContributionService.saveSalesContributionToHtml(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功!");
			return;
		}
		this.forwardData(false, null, msg);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doExportSalesContributionToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = ("供应商销售贡献度排行列表").concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		Map<String, Object> map = getSupplierSalesContribution().toMap();
		String minDate = this.asString("minDate");
		String maxDate = this.asString("maxDate");
		map.put("minDate", minDate);
		map.put("maxDate", maxDate);

		supplierSalesContributionService.exportSignedQtyExcel(map, out);
//		this.putObject("contributionList", list);
//		this.forwardDownload("excel/sco/suppliersalescontribution/supplierSalesContributionTemplate.xlsx", ExcelUtils.getEncodeFileName("供应商销售贡献度排行列表-" + DateUtils.formateDateTime() + ".xlsx"));
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierSalesContribution getSupplierSalesContribution() throws Exception {
		SupplierSalesContribution salesContribution = new SupplierSalesContribution();
		this.asBean(salesContribution);
		return salesContribution;
	}
}
