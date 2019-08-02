package com.powere2e.sco.action.categoryanalysis.allcategoryprice;

import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.action.categoryanalysis.CategoryPriceAction;
import com.powere2e.sco.common.service.BusinessConstants;

/**
 * 所有商品价格带 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月28日
 */
public class AllCategoryPriceAction extends CategoryPriceAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6415463452030702403L;
	
	/**
	 * 显示所有商品价格带主界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice")
	public void doShowAllCategoryPriceMain() throws Exception {
		this.forwardPage("sco/categoryanalysis/allcategoryprice/allCategoryPriceGrid.ftl");
	}
	
	/**
	 * 查询所有商品价格带数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.viewAllCategory")
	public void doListCollectData() throws Exception {
		super.doListCollectData();
	}
	
	/**
	 * 查看明细数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.viewDetCategory")
	public void doListDetailData() throws Exception {
		super.doListDetailData();
	}

	/**
	 * 所有商品价格带datagrid上的统计数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.viewAllCategory")
	public void doListCalculateTotal() throws Exception {
		super.doListCalculateTotal();
	}

	/**
	 * 所有价格带导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.exportAllCategory")
	public void doExportAllCategoryToExcel() throws Exception {
		String date1 = super.getExcelDate1Name();
		String fileName = "所有商品价格带汇总表-".concat(this.asString("regionName")).concat("-")
				.concat(date1).concat(".xlsx");
		super.doExportAllCategoryToExcel(fileName, this.asString("merTitle"));
	}

	/**
	 * 保存所有价格带报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.saveAllCategory")
	public void doSaveAllCategory() throws Exception {
		super.doSaveAllCategory(BusinessConstants.myReportType.AMP.toString(),
				"allCategory", this.asString("merTitle"), "所有商品价格带");
	}
	
	/**
	 * 导出明细数据到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.exportAllCategory")
	public void doExportDetCategoryToExcel() throws Exception {
		String date1 = super.getExcelDate1Name();
		String fileName = "所有商品价格带明细表-".concat(this.asString("regionName")).concat("-")
				.concat(date1).concat(".xlsx");
		super.doExportDetCategoryToExcel(fileName, this.asString("merTitle"));
	}
	
	/**
	 * 保存明细报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.allCategoryPrice.saveAllCategory")
	public void doSaveDetCategory() throws Exception {
		super.doSaveDetCategory(BusinessConstants.myReportType.AMP.toString(),
				"allCategory", this.asString("merTitle"));
	}
	
}
