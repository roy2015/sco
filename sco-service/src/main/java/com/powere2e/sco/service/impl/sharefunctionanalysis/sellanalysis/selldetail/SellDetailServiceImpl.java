package com.powere2e.sco.service.impl.sharefunctionanalysis.sellanalysis.selldetail;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selldetail.SellDetailDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selldetail.SellDetailService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail.SellDetail;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 销售明细业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class SellDetailServiceImpl extends ServiceImpl implements SellDetailService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private SellDetailDao SellDetailDao;

	public static SellDetailService getInstance() {
		return (SellDetailService) ConfigFactory.getInstance().getBean("SellDetailService");
	}

	// 获得销售明细DAO实例
	public SellDetailDao getSellDetailDao() {
		return SellDetailDao;
	}

	// 设置销售明细DAO实例
	public void setSellDetailDao(SellDetailDao SellDetailDao) {
		this.SellDetailDao = SellDetailDao;
	}

	// 查询
	@Override
	public List<SellDetail> getSellDetail(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSellDetailDao().listSellDetail(map, pageInfo);
	}

	// 查询汇总
	@Override
	public List<SellDetail> getSellDetailSum(Map<String, Object> map, PageInfo pageInfo) {
		return this.getSellDetailDao().listSellDetails(map, pageInfo);
	}

	// 加载一个销售明细
	@Override
	public SellDetail getSellDetail(String merchandiseCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("merchandiseCode", merchandiseCode);
		return this.getSellDetailDao().loadSellDetail(map);
	}

	@Override
	public List<SellDetail> getMerchandise(Map<String, Object> map, PageInfo pageInfo) {

		return this.getSellDetailDao().listMerchandise(map, pageInfo);
	}

	// 导出execl
	@Override
	public void exportSellDetail(Map<String, Object> map, ServletOutputStream out) {
		List<SellDetail> list = this.getSellDetail(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("销售明细表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(0, wb, sheet);
		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet, 0);
		List<SellDetail> lists = this.getSellDetailSum(map, null);
		this.fillHeaderCell(list.size() + 2, wb, sheet);
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setMinDate(map.get("minDate").toString());
			lists.get(i).setMaxDate(map.get("maxDate").toString());
		}
		this.fillDataCell(lists, wb, sheet, list.size() + 2);
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
			}
		}
	}

	/**
	 * 创建excel表头(第一行和第二行)
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            sheet
	 */
	private void fillHeaderCell(Integer num, Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(num);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		if (num < 1) {
			cell.setCellValue("明细表");
		} else {
			cell.setCellValue("汇总表");
		}
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 36; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (num + 1) + ":AJ" + (num + 1));

		// 第二行
		row = sheet.createRow(num + 1);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售日期");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");
		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");
		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (140 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("采购部门");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量（基本价格单位）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量占比(占小分类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额占比(占小分类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比(占小分类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量占比(占明细类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额占比(占明细类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比(占明细类)");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量（基本价格单位）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("活跃度");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("A级店数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("B级店数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("C级店数");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("D级店数");

	}

	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            单据明细list
	 */
	private void fillDataCell(List<SellDetail> list, Workbook wb, Sheet sheet, Integer num) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, (i + num), list.get(i), strStyle, dateStyle, amtStyle, num);
		}
	}

	/**
	 * 
	 * @param sheet
	 *            sheet
	 * @param rowIndex
	 *            当前行
	 * @param list
	 *            数据list
	 * @param strStyle
	 *            字符串数据格式
	 * @param dateStyle
	 *            时间日期格式
	 * @param amtStyle
	 *            金额、数量格式
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, SellDetail msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Integer num) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;

		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSellRegion());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		if (num == 0) {
			cell.setCellValue(msd.getSellDate());
		} else {
			cell.setCellValue(msd.getMinDate() + "~" + msd.getMaxDate());
		}
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSupplierName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDxRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDlRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getCentreName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSmallName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getDetailName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getFineName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getPurchaseDepartments());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellQuantity = msd.getSellQuantity();
		if (sellQuantity != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellQuantity));
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellTotalPrice = msd.getSellTotalPrice();
		if (sellTotalPrice != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellTotalPrice));
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellProfit = msd.getSellProfit();
		if (sellProfit != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellProfit));
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellQuantityProportionM = msd.getSellQuantityProportionM();
		if (sellQuantityProportionM != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellQuantityProportionM) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellTotalPriceProportionM = msd.getSellTotalPriceProportionM();
		if (sellTotalPriceProportionM != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellTotalPriceProportionM) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellProfitProportionM = msd.getSellProfitProportionM();
		if (sellProfitProportionM != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellProfitProportionM) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellQuantityProportionS = msd.getSellQuantityProportionS();
		if (sellQuantityProportionS != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellQuantityProportionS) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellTotalPriceProportionS = msd.getSellTotalPriceProportionS();
		if (sellTotalPriceProportionS != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellTotalPriceProportionS) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellProfitPriceProportionS = msd.getSellProfitProportionS();
		if (sellProfitPriceProportionS != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellProfitPriceProportionS) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellQuantityProportionD = msd.getSellQuantityProportionD();
		if (sellQuantityProportionD != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellQuantityProportionD) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellTotalPriceProportionD = msd.getSellTotalPriceProportionD();
		if (sellTotalPriceProportionD != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellTotalPriceProportionD) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal sellProfitProportionD = msd.getSellProfitProportionD();
		if (sellProfitProportionD != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(sellProfitProportionD) + "%");
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdSellQuantity = msd.getPsdSellQuantity();
		if (psdSellQuantity != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(psdSellQuantity));
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdSellTotalPrice = msd.getPsdSellTotalPrice();
		if (psdSellTotalPrice != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(psdSellTotalPrice));
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdSellProfit = msd.getPsdSellProfit();
		if (psdSellProfit != null) {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(psdSellProfit));
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getSumStore()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getPermissionStoreQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getSellStoreQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (msd.getActive() != null) {
			if (msd.getActive().compareTo(new BigDecimal(100)) == 1) {
				cell.setCellValue("100.00%");
			} else {
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getActive()) + "%");
			}
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getStoreA()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getStoreB()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getStoreC()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getStoreD()));
	}

	/**
	 * 保存页面查询结果
	 */
	@Override
	public String saveSellDetail(String fileName, Map<String, Object> paraMap) {
		String tarPath;
		List<SellDetail> lists = this.getSellDetailSum(paraMap, null);
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setMinDate(paraMap.get("minDate").toString());
			lists.get(i).setMaxDate(paraMap.get("maxDate").toString());
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", this.getSellDetail(paraMap, null));
		map.put("dataLists", lists);
		try {
			tarPath = FreeMarkerUtil.generateHtml("sellAnalysis/sellDetail/sellDetailInfoGrid.ftl", "sellDetail".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.SDT.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("SellDetailServiceImpl.saveSellDetail删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}
}