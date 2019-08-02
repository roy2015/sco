package com.powere2e.sco.service.impl.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.selllinkrelative.MerchandiseLinkRelativeDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative.MerchandiseLinkRelativeService;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 销售环比service实现
 * 
 * @author Joyce.li
 * @since 2015年7月7日 上午11:38:44
 * @version 1.0
 */
public class MerchandiseLinkRelativeServiceImpl extends ServiceImpl implements MerchandiseLinkRelativeService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MerchandiseLinkRelativeDao merchandiseLinkRelativeDao;
	private Font font = null;

	public MerchandiseLinkRelativeDao getMerchandiseLinkRelativeDao() {
		return merchandiseLinkRelativeDao;
	}

	public void setMerchandiseLinkRelativeDao(MerchandiseLinkRelativeDao merchandiseLinkRelativeDao) {
		this.merchandiseLinkRelativeDao = merchandiseLinkRelativeDao;
	}

	public static SellYearOnYearService getInstance() {
		return (SellYearOnYearService) ConfigFactory.getInstance().getBean("merchandiseLinkRelativeService");
	}

	// 查询销售环比列表
	@Override
	public List<LinkRelative> listLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return merchandiseLinkRelativeDao.listLinkRelative(map, pageInfo);
	}

	//查询单品销售环比的单品信息
	@Override
	public List<LinkRelative> queryMerchandiseLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return merchandiseLinkRelativeDao.queryMerchandiseLinkRelative(map, pageInfo);
	}

	// 单品销售环比
	@Override
	public List<SellLinkRelativeAnalysis> queryProductSellLinkRelative(Map<String, Object> map, PageInfo pageInfo) {
		return merchandiseLinkRelativeDao.queryProductSellLinkRelative(map, pageInfo);
	}

	// 保存商品销售环比数据为html页面
	@Override
	public String saveProductSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath = null;
		List<LinkRelative> sellList = this.queryMerchandiseLinkRelative(paraMap, null);
		List<SellLinkRelativeAnalysis> merchandiseList = this.queryProductSellLinkRelative(paraMap, null);

		paraMap.put("sellList", sellList);
		paraMap.put("merchandiseList", merchandiseList);

		try {
			String fileDirName = "sellAnalysis/sellLinkRelative/merchandiseSellLinkRelativeReport.ftl";
			tarPath = FreeMarkerUtil.generateHtml(fileDirName,
					"merchandiseSellLinkRelative".concat("/").concat(fileName), paraMap);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.SMS.toString(), fileName,
						tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandiseLinkRelativeServiceImpl.saveProductSellToHtml删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错!");
			}
		} else {
			return "生成Html文件失败!";
		}
		return null;
	}

	@Override
	public void exportMerchandiseSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception {
		List<LinkRelative> list = this.queryMerchandiseLinkRelative(map, null);
		List<SellLinkRelativeAnalysis> merchandiseList = this.queryProductSellLinkRelative(map, null);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet1 = wb.createSheet();
		Sheet sheet2 = wb.createSheet();
		wb.setSheetName(0, "商品信息");
		wb.setSheetName(1, "商品环比信息");
		font = wb.createFont();
		font.setFontName("微软雅黑");

		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet1);
		this.fillMerchandiseSellHeaderCell(map, wb, sheet2);

		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCell(list, wb, sheet1);
		}
		if (!merchandiseList.isEmpty()) {
			this.fillMerchandiseSellDataCell(map, merchandiseList, wb, sheet2);
		}
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
	private void fillHeaderCell(Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.setFrame(firstRowStyle, true);
		Cell cell = row.createCell(0);
		cell.setCellValue("商品信息");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 10; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:J1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		sencondRowStyle.setFont(font);
		sencondRowStyle.setAlignment(CellStyle.ALIGN_LEFT);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

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
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
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
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

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
	private void fillDataCell(List<LinkRelative> list, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		strStyle.setFont(font);
		ExcelUtils.setFrame(strStyle, true);

		for (int i = 0; i < list.size(); i++) {
			this.writeOneRowDate(sheet, i, list.get(i), strStyle);
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
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, LinkRelative linkRelative, CellStyle strStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getMerchandiseCode());// 商品编号

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getMerchandiseName());// 商品名称

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getDxRoleName());// 定性角色

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getDlRoleName());// 定量角色

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getCentreTypeName());// 中分类

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getSmallTypeName());// 小分类

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getDetailTypeName());// 明细类

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getFineTypeName());// 细分类

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getSupplierCode());// 供应商编号

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(linkRelative.getSupplierName());// 供应商名称

	}

	/**
	 * 创建excel表头(第一行和第二行)
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            sheet
	 */
	private void fillMerchandiseSellHeaderCell(Map<String, Object> map, Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(0);
		String rationed = (String) map.get("rationed");
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.setFrame(firstRowStyle, true);
		Cell cell = row.createCell(0);
		cell.setCellValue("商品环比信息");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 18; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, 0, 0, 0, 18);

		// 第二行
		row = sheet.createRow(1);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		sencondRowStyle.setFont(font);
		sencondRowStyle.setAlignment(CellStyle.ALIGN_RIGHT);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("日期");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量环比增长");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额环比增长");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额环比增长");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比PSD销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量环比增长");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比PSD销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额环比增长");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("环比PSD毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD环比毛利额增长");

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
	private void fillMerchandiseSellDataCell(Map<String, Object> map, List<SellLinkRelativeAnalysis> list, Workbook wb,
			Sheet sheet) {
		CellStyle strStyle = wb.createCellStyle();// 字符串样式
		strStyle.setFont(font);
		ExcelUtils.setFrame(strStyle, true);
		strStyle.setAlignment(CellStyle.ALIGN_LEFT);
		
		CellStyle dateStyle = wb.createCellStyle();// 日期样式
		dateStyle.setFont(font);
		ExcelUtils.setFrame(dateStyle, true);
		dateStyle.setAlignment(CellStyle.ALIGN_LEFT);
		
		CellStyle amtStyle = wb.createCellStyle();// 金额样式
		amtStyle.setFont(font);
		ExcelUtils.setFrame(amtStyle, true);
		amtStyle.setAlignment(CellStyle.ALIGN_RIGHT);

		for (int i = 0; i < list.size(); i++) {
			this.writeMerchandiseSellOneRowData(map, sheet, i, list.get(i), strStyle, dateStyle, amtStyle);
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
	private void writeMerchandiseSellOneRowData(Map<String, Object> map, Sheet sheet, Integer rowIndex,
			SellLinkRelativeAnalysis merchandiseSell, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		for (int j = 0; j <= 18; j++) {
			Cell cell = row.createCell(j);
			cell.setCellStyle(amtStyle);
			if (j == 0) {
				cell.setCellValue(merchandiseSell.getSellDate());// 月份
			} else if (j == 1) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellQuantity()));// 销售量
			} else if (j == 2) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellQuantity()));// 环比销售量
			} else if (j == 3) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellQuantityChange()));// 销售量环比增长
			} else if (j == 4) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellTotalPrice()));// 销售额
			} else if (j == 5) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellTotalPrice()));// 销售额
			} else if (j == 6) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellTotalPriceChange()));// 销售额环比增长
			} else if (j == 7) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellProfit()));// 毛利额
			} else if (j == 8) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellProfit()));// 环比毛利额
			} else if (j == 9) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellProfitChange()));// 毛利额环比增长
			} else if (j == 10) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellQuantity()));// PSD销售量
			} else if (j == 11) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellQuantity()));// 环比PSD销售量
			} else if (j == 12) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellQuantityChange()));// PSD销售量环比增长
			} else if (j == 13) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellTotalPrice()));// PSD销售额
			} else if (j == 14) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellTotalPrice()));// 环比PSD销售额
			} else if (j == 15) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellTotalPriceChange()));// PSD销售额环比增长
			} else if (j == 16) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellProfit()));// PSD毛利额
			} else if (j == 17) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellProfit()));// 环比PSD毛利额
			} else if (j == 18) {
				cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellProfitChange()));// PSD毛利额环比增长
			}
		}
	}

}