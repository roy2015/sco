package com.powere2e.sco.action.categoryanalysis.smalltypeprice;

import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.action.categoryanalysis.CategoryPriceAction;
import com.powere2e.sco.common.service.BusinessConstants;

/**
 * 小分类价格带 WEB请求响应类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年06月26日
 */
public class SmallTypePriceAction extends CategoryPriceAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1177476975680698507L;

	/**
	 * 显示所有商品价格带主界面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice")
	public void doShowSmallTypePriceMain() throws Exception {
		this.forwardPage("sco/categoryanalysis/smalltypeprice/smallTypePriceGrid.ftl");
	}
	
	/**
	 * 查询所有商品价格带数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.viewAllCategory")
	public void doListCollectData() throws Exception {
		super.doListCollectData();
	}
	
	/**
	 * 查看明细数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.viewDetCategory")
	public void doListDetailData() throws Exception {
		super.doListDetailData();
	}
	
	/**
	 * 所有商品价格带datagrid上的统计数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.viewAllCategory")
	public void doListCalculateTotal() throws Exception {
		super.doListCalculateTotal();
	}

	/**
	 * 所有价格带导出到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.exportAllCategory")
	public void doExportAllCategoryToExcel() throws Exception {
		String merTitle = this.asString("merTitle");
		String date1 = super.getExcelDate1Name();
		String fileName = "小分类价格带汇总表-".concat(merTitle).concat("-")
				.concat(this.asString("regionName")).concat("-").concat(date1)
				.concat(".xlsx");
		super.doExportAllCategoryToExcel(fileName, "小分类名称：" + merTitle);
	}

	/**
	 * 保存所有价格带报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.saveAllCategory")
	public void doSaveAllCategory() throws Exception {
		super.doSaveAllCategory(BusinessConstants.myReportType.SPP.toString(),
				"smallCategory", "小分类名称：" + this.asString("merTitle"), "小分类价格带");
	}
	
	/**
	 * 导出明细数据到Excel
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.exportAllCategory")
	public void doExportDetCategoryToExcel() throws Exception {
		String merTitle = this.asString("merTitle");
		String date1 = super.getExcelDate1Name();
		String fileName = "小分类价格带明细表-".concat(merTitle).concat("-")
				.concat(this.asString("regionName")).concat("-").concat(date1)
				.concat(".xlsx");
		super.doExportDetCategoryToExcel(fileName, "小分类名称：" + merTitle);
	}
	
	/**
	 * 保存明细报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis.smallTypePrice.saveAllCategory")
	public void doSaveDetCategory() throws Exception {
		super.doSaveDetCategory(BusinessConstants.myReportType.SPP.toString(),
				"smallCategory", "小分类名称：" + this.asString("merTitle"));
	}
	
}
