package com.powere2e.sco.action.categoryanalysis.detailtypeprice;

import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.action.categoryanalysis.CategoryPriceAction;
import com.powere2e.sco.common.service.BusinessConstants;

/**
 * 明细类价格带 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年07月01日
 */
public class DetailTypePriceAction extends CategoryPriceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8322639502908500570L;

	/**
	 * 显示所有商品价格带主界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice")
	public void doShowDetailTypePriceMain() throws Exception {
		this.forwardPage("sco/categoryanalysis/detailtypeprice/detailTypePriceGrid.ftl");
	}
	
	/**
	 * 查询所有商品价格带数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.viewAllCategory")
	public void doListCollectData() throws Exception {
		super.doListCollectData();
	}
	
	/**
	 * 查看明细数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.viewDetCategory")
	public void doListDetailData() throws Exception {
		super.doListDetailData();
	}
	
	/**
	 * 所有商品价格带datagrid上的统计数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.viewAllCategory")
	public void doListCalculateTotal() throws Exception {
		super.doListCalculateTotal();
	}

	/**
	 * 所有价格带导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.exportAllCategory")
	public void doExportAllCategoryToExcel() throws Exception {
		String merTitle = this.asString("merTitle");
		String date1 = super.getExcelDate1Name();
		String fileName = "明细类价格带汇总表-".concat(merTitle).concat("-")
				.concat(this.asString("regionName")).concat("-").concat(date1)
				.concat(".xlsx");
		super.doExportAllCategoryToExcel(fileName, "明细类名称：" + merTitle);
	}

	/**
	 * 保存所有价格带报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.saveAllCategory")
	public void doSaveAllCategory() throws Exception {
		super.doSaveAllCategory(BusinessConstants.myReportType.DPP.toString(),
				"detailCategory", "明细类名称：" + this.asString("merTitle"), "明细类价格带");
	}
	
	/**
	 * 导出明细数据到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.exportAllCategory")
	public void doExportDetCategoryToExcel() throws Exception {
		String merTitle = this.asString("merTitle");
		String date1 = super.getExcelDate1Name();
		String fileName = "明细类价格带明细表-".concat(merTitle).concat("-")
				.concat(this.asString("regionName")).concat("-").concat(date1)
				.concat(".xlsx");
		super.doExportDetCategoryToExcel(fileName, "明细类名称：" + merTitle);
	}
	
	/**
	 * 保存明细报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.detailTypePrice.saveAllCategory")
	public void doSaveDetCategory() throws Exception {
		super.doSaveDetCategory(BusinessConstants.myReportType.DPP.toString(),
				"detailCategory", "明细类名称：" + this.asString("merTitle"));
	}
	
}
