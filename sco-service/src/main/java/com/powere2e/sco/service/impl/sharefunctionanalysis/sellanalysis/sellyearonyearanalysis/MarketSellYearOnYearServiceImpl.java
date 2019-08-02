package com.powere2e.sco.service.impl.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.commons.lang.StringUtils;
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
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.MarketSellYearOnYearDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.MarketSellYearOnYearService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 整体销售同比service实现
 * 
 * @author Joyce.li
 * @since 2015年6月8日 下午7:19:00
 * @version 1.0
 */
public class MarketSellYearOnYearServiceImpl extends ServiceImpl implements MarketSellYearOnYearService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MarketSellYearOnYearDao marketSellYearOnYearDao;
	private Font font = null;

	public MarketSellYearOnYearDao getMarketSellYearOnYearDao() {
		return marketSellYearOnYearDao;
	}

	public void setMarketSellYearOnYearDao(MarketSellYearOnYearDao marketSellYearOnYearDao) {
		this.marketSellYearOnYearDao = marketSellYearOnYearDao;
	}

	public static MarketSellYearOnYearService getInstance() {
		return (MarketSellYearOnYearService) ConfigFactory.getInstance().getBean("marketSellYearOnYearService");
	}

	@Override
	public List<SellYearOnYearAnalysis> queryMarketSellYearOnYear(Map<String, Object> map, PageInfo pageInfo) {
		return marketSellYearOnYearDao.queryMarketSellYearOnYear(map, pageInfo);
	}

	@Override
	public List<SellYearOnYearAnalysis> queryMarketSellSum(Map<String, Object> map, PageInfo pageInfo) {
		return marketSellYearOnYearDao.queryMarketSellSum(map, pageInfo);
	}

	@Override
	public String saveMarketSellToHtml(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath;
		List<SellYearOnYearAnalysis> marketSellList = this.queryMarketSellYearOnYear(paraMap, null);
		List<SellYearOnYearAnalysis> marketSellSumList = this.queryMarketSellSum(paraMap, null);
		if (!marketSellList.isEmpty()) {
			marketSellList.addAll(marketSellSumList);
		}
		paraMap.put("marketSellList", marketSellList);

		try {
			// 默认查看1年同比数据，用模版一
			String fileDirName = "sellAnalysis/sellYearOnYearAnalysis/marketSellYearOnYearReport.ftl";
			tarPath = FreeMarkerUtil.generateHtml(fileDirName, "marketSellYearOnYear".concat("/").concat(fileName), paraMap);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.GCS.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MarketSellYearOnYearServiceImpl.saveMarketSellToHtml删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错!");
			}
		} else {
			return "生成Html文件失败!";
		}
		return null;
	}

	@Override
	public void exportMarketSellToExcel(Map<String, Object> map, ServletOutputStream out) throws Exception {
		List<SellYearOnYearAnalysis> marketList = this.queryMarketSellYearOnYear(map, null);
		List<SellYearOnYearAnalysis> sumList = this.queryMarketSellSum(map, null);
		Workbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet();
		wb.setSheetName(0, "整体同比信息");
		font = wb.createFont();
		font.setFontName("微软雅黑");

		// 1. 创建excel表头(第一行和第二行)
		this.fillMerchandiseSellHeaderCell(map, wb, sheet);

		// 2.用数据填充单元格
		if (!marketList.isEmpty()) {
			marketList.addAll(sumList);
			this.fillMerchandiseSellDataCell(map, marketList, wb, sheet);
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
	private void fillMerchandiseSellHeaderCell(Map<String, Object> map, Workbook wb, Sheet sheet) {
		int defaultYear = 1;
		String rationed = (String) map.get("rationed");
		String seeYear = (String) map.get("seeYear");
		if (StringUtils.isNotBlank(seeYear)) {
			defaultYear = Integer.parseInt(seeYear);
		}
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.setFrame(firstRowStyle, true);
		Cell cell = row.createCell(0);
		cell.setCellValue("整体同比信息");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 25 + (defaultYear - 1) * 12; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, 0, 0, 0, 24 + (defaultYear - 1) * 12);

		// 第二行
		row = sheet.createRow(1);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		sencondRowStyle.setFont(font);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("月份");

		cell = row.createCell(i);
		cell.setCellValue("销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 4 + (defaultYear - 1) * 2; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "B1:E1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 1, 4 + (defaultYear - 1) * 2);
		// sheet.setColumnWidth(i++, (short) (100 * 80*4));// 设置当前行单元格宽度

		i++;
		cell = row.createCell(i);
		cell.setCellValue("销售额（元）");
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 8 + (defaultYear - 1) * 2 * 2; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "F1:I1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 5 + (defaultYear - 1) * 2 * 1, 8 + (defaultYear - 1) * 2 * 2);

		i++;
		cell = row.createCell(i);
		cell.setCellValue("毛利额（元）");
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 12 + (defaultYear - 1) * 2 * 3; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "J1:M1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 9 + (defaultYear - 1) * 2 * 2, 12 + (defaultYear - 1) * 2 * 3);

		i++;
		cell = row.createCell(i);
		cell.setCellValue("PSD销售量"+(rationed.equalsIgnoreCase("dlz")?"（件）":"（KG）"));
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 16 + (defaultYear - 1) * 2 * 4; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "N1:Q1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 13 + (defaultYear - 1) * 2 * 3, 16 + (defaultYear - 1) * 2 * 4);

		i++;
		cell = row.createCell(i);
		cell.setCellValue("PSD销售额（元）");
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 20 + (defaultYear - 1) * 2 * 5; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "R1:U1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 17 + (defaultYear - 1) * 2 * 4, 20 + (defaultYear - 1) * 2 * 5);

		i++;
		cell = row.createCell(i);
		cell.setCellValue("PSD毛利额（元）");
		cell.setCellStyle(sencondRowStyle);
		for (int j = i + 1; j <= 24 + (defaultYear - 1) * 2 * 6; j++) {
			i++;
			cell = row.createCell(j);
			cell.setCellStyle(sencondRowStyle);
		}
		// ExcelUtils.addMergedRegion(sheet, "V1:Y1");
		ExcelUtils.addMergedRegion(sheet, 1, 1, 21 + (defaultYear - 1) * 2 * 5, 24 + (defaultYear - 1) * 2 * 6);

		// 第三行
		row = sheet.createRow(2);
		// i = 0;// 从第零列(第一列)开始

		cell = row.createCell(0);
		cell.setCellStyle(sencondRowStyle);
		cell.setCellValue("月份");
		sheet.setColumnWidth(i++, (short) (30 * 80));// 设置当前行单元格宽度
		ExcelUtils.addMergedRegion(sheet, 1, 2, 0, 0);

		for (int j = 1; j <= 24 + (defaultYear - 1) * 12; j++) {
			if (defaultYear == 1) {
				if (j % 4 == 1) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 4 == 2) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 4 == 3) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 4 == 0) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				}
			} else if (defaultYear == 2) {
				if (j % 6 == 1) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 6 == 2) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 6 == 3) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 6 == 4) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 6 == 5) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("beforeLastYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 6 == 0) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("beforeLastYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				}
			} else if (defaultYear == 3) {
				if (j % 8 == 1) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 2) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("firstYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 3) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 4) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("lastYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				}
				if (j % 8 == 5) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("beforeLastYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 6) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("beforeLastYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 7) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("fourthYear").toString());
					sheet.setColumnWidth(j, (short) (30 * 80));
					cell.setCellStyle(sencondRowStyle);
				} else if (j % 8 == 0) {
					cell = row.createCell(j);
					cell.setCellValue(map.get("fourthYear").toString()+"同比增长");
					sheet.setColumnWidth(j, (short) (60 * 80));
					cell.setCellStyle(sencondRowStyle);
				}
			}
		}
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
	private void fillMerchandiseSellDataCell(Map<String, Object> map, List<SellYearOnYearAnalysis> list, Workbook wb, Sheet sheet) {
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
	private void writeMerchandiseSellOneRowData(Map<String, Object> map, Sheet sheet, Integer rowIndex, SellYearOnYearAnalysis merchandiseSell, CellStyle strStyle, CellStyle dateStyle,
			CellStyle amtStyle) {
		String sellMonth = merchandiseSell.getSellMonth();
		// 截取月份
		if (sellMonth != null && sellMonth.length() > 6) {
			sellMonth = sellMonth.substring(5, 7);
		}
		int defaultYear = 1;
		String seeYear = (String) map.get("seeYear");
		if (StringUtils.isNotBlank(seeYear)) {
			defaultYear = Integer.parseInt(seeYear);
		}
		Row row = sheet.createRow(rowIndex + 3);

		if (defaultYear == 1) {
			for (int j = 0; j <= 24 + (defaultYear - 1) * 12; j++) {
				Cell cell = row.createCell(j);
				cell.setCellStyle(amtStyle);
				if (j == 0) {
					cell.setCellValue(sellMonth);// 月份
				} else if (j == 1) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellQuantity()));// 销售量
				} else if (j == 2) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellQuantityChange()));// 销售量同比
				} else if (j == 3) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellQuantity()));// 去年销售量
				} else if (j == 4) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellQuantityChange()));// 去年销售量同比
				} else if (j == 5) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellTotalPrice()));// 销售额
				} else if (j == 6) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellTotalPriceChange()));// 销售额同比
				} else if (j == 7) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellTotalPrice()));// 去年销售额
				} else if (j == 8) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellTotalPriceChange()));// 去年销售额同比
				} else if (j == 9) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellProfit()));// 毛利额
				} else if (j == 10) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellProfitChange()));// 毛利额同比
				} else if (j == 11) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellProfit()));// 去年毛利额
				} else if (j == 12) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellProfitChange()));// 去年毛利额同比
				} else if (j == 13) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellQuantity()));// PSD销售量
				} else if (j == 14) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellQuantityChange()));// PSD销售量同比
				} else if (j == 15) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellQuantity()));// 去年PSD销售量
				} else if (j == 16) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellQuantityChange()));// 去年PSD销售量同比
				} else if (j == 17) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellTotalPrice()));// PSD销售额
				} else if (j == 18) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellTotalPriceChange()));// PSD销售额同比
				} else if (j == 19) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellTotalPrice()));// 去年PSD销售额
				} else if (j == 20) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellTotalPriceChange()));// 去年PSD销售额同比
				} else if (j == 21) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellProfit()));// PSD毛利额
				} else if (j == 22) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellProfitChange()));// PSD毛利额同比
				} else if (j == 23) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellProfit()));// 去年PSD毛利额
				} else if (j == 24) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellProfitChange()));// 去年PSD毛利额同比
				}
			}
		} else if (defaultYear == 2) {
			for (int j = 0; j <= 24 + (defaultYear - 1) * 12; j++) {
				Cell cell = row.createCell(j);
				cell.setCellStyle(amtStyle);
				if (j == 0) {
					cell.setCellValue(sellMonth);// 月份
				} else if (j == 1) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellQuantity()));// 销售量
				} else if (j == 2) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellQuantityChange()));// 销售量同比
				} else if (j == 3) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellQuantity()));// 去年销售量
				} else if (j == 4) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellQuantityChange()));// 去年销售量同比
				} else if (j == 5) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellQuantity()));// 前年销售量
				} else if (j == 6) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellQuantityChange()));// 前年销售量同比
				} else if (j == 7) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellTotalPrice()));// 销售额
				} else if (j == 8) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellTotalPriceChange()));// 销售额同比
				} else if (j == 9) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellTotalPrice()));// 去年销售额
				} else if (j == 10) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellTotalPriceChange()));// 去年销售额同比
				} else if (j == 11) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellTotalPrice()));// 前年销售额
				} else if (j == 12) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellTotalPriceChange()));// 前年销售额同比
				} else if (j == 13) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellProfit()));// 毛利额
				} else if (j == 14) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellProfitChange()));// 毛利额同比
				} else if (j == 15) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellProfit()));// 去年毛利额
				} else if (j == 16) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellProfitChange()));// 去年毛利额同比
				} else if (j == 17) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellProfit()));// 前年毛利额
				} else if (j == 18) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellProfitChange()));// 前年毛利额同比
				} else if (j == 19) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellQuantity()));// PSD销售量
				} else if (j == 20) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellQuantityChange()));// PSD销售量同比
				} else if (j == 21) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellQuantity()));// 去年PSD销售量
				} else if (j == 22) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellQuantityChange()));// 去年PSD销售量同比
				} else if (j == 23) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellQuantity()));// 前年PSD销售量
				} else if (j == 24) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellQuantityChange()));// 前年PSD销售量同比
				} else if (j == 25) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellTotalPrice()));// PSD销售额
				} else if (j == 26) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellTotalPriceChange()));// PSD销售额同比
				} else if (j == 27) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellTotalPrice()));// 去年PSD销售额
				} else if (j == 28) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellTotalPriceChange()));// 去年PSD销售额同比
				} else if (j == 29) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellTotalPrice()));// 前年PSD销售额
				} else if (j == 30) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellTotalPriceChange()));// 前年PSD销售额同比
				} else if (j == 31) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellProfit()));// PSD毛利额
				} else if (j == 32) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellProfitChange()));// PSD毛利额同比
				} else if (j == 33) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellProfit()));// 去年PSD毛利额
				} else if (j == 34) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellProfitChange()));// 去年PSD毛利额同比
				} else if (j == 35) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellProfit()));// 前年PSD毛利额
				} else if (j == 36) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellProfitChange()));// 前年PSD毛利额同比
				}
			}
		} else if (defaultYear == 3) {
			for (int j = 0; j <= 24 + (defaultYear - 1) * 12; j++) {
				Cell cell = row.createCell(j);
				cell.setCellStyle(amtStyle);
				if (j == 0) {
					cell.setCellValue(sellMonth);// 月份
				} else if (j == 1) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellQuantity()));// 销售量
				} else if (j == 2) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellQuantityChange()));// 销售量同比
				} else if (j == 3) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellQuantity()));// 去年销售量
				} else if (j == 4) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellQuantityChange()));// 去年销售量同比
				} else if (j == 5) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellQuantity()));// 前年销售量
				} else if (j == 6) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellQuantityChange()));// 前年销售量同比
				} else if (j == 7) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthSellQuantity()));// 大前年销售量
				} else if (j == 8) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthSellQuantityChange()));// 大前年销售量同比
				} else if (j == 9) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellTotalPrice()));// 销售额
				} else if (j == 10) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellTotalPriceChange()));// 销售额同比
				} else if (j == 11) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellTotalPrice()));// 去年销售额
				} else if (j == 12) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellTotalPriceChange()));// 去年销售额同比
				} else if (j == 13) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellTotalPrice()));// 前年销售额
				} else if (j == 14) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellTotalPriceChange()));// 前年销售额
				} else if (j == 15) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthSellTotalPrice()));// 大前年销售额
				} else if (j == 16) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthSellTotalPriceChange()));// 大前年销售额同比
				} else if (j == 17) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getSellProfit()));// 毛利额
				} else if (j == 18) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getSellProfitChange()));// 毛利额同比
				} else if (j == 19) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastSellProfit()));// 去年毛利额
				} else if (j == 20) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastSellProfitChange()));// 去年毛利额同比
				} else if (j == 21) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforeSellProfit()));// 前年毛利额
				} else if (j == 22) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforeSellProfitChange()));// 前年毛利额同比
				} else if (j == 23) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthSellProfit()));// 大前年毛利额
				} else if (j == 24) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthSellProfitChange()));// 大前年毛利额同比
				} else if (j == 25) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellQuantity()));// PSD销售量
				} else if (j == 26) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellQuantityChange()));// PSD销售量同比
				} else if (j == 27) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellQuantity()));// 去年PSD销售量
				} else if (j == 28) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellQuantityChange()));// 去年PSD销售量同比
				} else if (j == 29) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellQuantity()));// 前年PSD销售量
				} else if (j == 30) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellQuantityChange()));// 前年PSD销售量同比
				} else if (j == 31) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthPsdSellQuantity()));// 大前年PSD销售量
				} else if (j == 32) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthPsdSellQuantityChange()));// 大前年PSD销售量同比
				} else if (j == 33) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellTotalPrice()));// PSD销售额
				} else if (j == 34) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellTotalPriceChange()));// PSD销售额同比
				} else if (j == 35) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellTotalPrice()));// 去年PSD销售额
				} else if (j == 36) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellTotalPriceChange()));// 去年PSD销售额同比
				} else if (j == 37) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellTotalPrice()));// 前年PSD销售额
				} else if (j == 38) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellTotalPriceChange()));// 前年PSD销售额同比
				} else if (j == 39) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthPsdSellTotalPrice()));// 前年PSD销售额
				} else if (j == 40) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthPsdSellTotalPriceChange()));// 前年PSD销售额同比
				} else if (j == 41) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getPsdSellProfit()));// PSD毛利额
				} else if (j == 42) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getPsdSellProfitChange()));// PSD毛利额同比
				} else if (j == 43) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getLastPsdSellProfit()));// 去年PSD毛利额
				} else if (j == 44) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getLastPsdSellProfitChange()));// 去年PSD毛利额同比
				} else if (j == 45) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getBeforePsdSellProfit()));// 前年PSD毛利额
				} else if (j == 46) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getBeforePsdSellProfitChange()));// 前年PSD毛利额同比
				} else if (j == 47) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToString(merchandiseSell.getFourthPsdSellProfit()));// 大前年PSD毛利额
				} else if (j == 48) {
					cell.setCellValue(DecimalFormatUtils.bigDecimalToPercent(merchandiseSell.getFourthPsdSellProfitChange()));// 大前年PSD毛利额同比
				}
			}
		}
	}

}