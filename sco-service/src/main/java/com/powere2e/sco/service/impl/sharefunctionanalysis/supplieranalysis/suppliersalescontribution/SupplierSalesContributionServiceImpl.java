package com.powere2e.sco.service.impl.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

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
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContributionDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContributionService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution.SupplierSalesContribution;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 供应商销售贡献度排行分析Service实现
 * 
 * @author Joyce.li
 * @since 2015年8月3日 下午3:00:41
 * @version 1.0
 */
public class SupplierSalesContributionServiceImpl extends ServiceImpl implements SupplierSalesContributionService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SupplierSalesContributionDao supplierSalesContributionDao;
	private Font font = null;

	public SupplierSalesContributionDao getSupplierSalesContributionDao() {
		return supplierSalesContributionDao;
	}

	public void setSupplierSalesContributionDao(SupplierSalesContributionDao supplierSalesContributionDao) {
		this.supplierSalesContributionDao = supplierSalesContributionDao;
	}

	@Override
	public List<SupplierSalesContribution> querySupplierSalesContribution(Map<String, Object> map, PageInfo pageInfo) {
		List<SupplierSalesContribution>  list = supplierSalesContributionDao.querySupplierSalesContribution(map, pageInfo);
		return list;
	}

	@Override
	public String saveSalesContributionToHtml(String fileName, Map<String, Object> paraMap) throws Exception {
		List<SupplierSalesContribution> contributionList = this.querySupplierSalesContribution(paraMap, null);
		paraMap.put("contributionList", contributionList);
		String tarPath = null;
		try {
			// 默认查看1年同比数据，用模版一
			String fileDirName = "supplieranalysis/suppliersalescontribution/supplierSalesContributionReport.ftl";
			tarPath = FreeMarkerUtil.generateHtml(fileDirName, "suppliersalescontribution".concat("/").concat(fileName), paraMap);
		} catch (Exception e) {
			return "转换报表模板时异常!";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.SSR.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("SupplierSalesContributionServiceImpl.saveSalesContributionToHtml删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错!");
			}
		} else {
			return "生成Html文件失败!";
		}
		return null;
	}
	
	@Override
	public void exportSignedQtyExcel(Map<String, Object> map, ServletOutputStream out) {
		List<SupplierSalesContribution> list = this.querySupplierSalesContribution(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("供应商销售贡献度排行列表");
		font = wb.createFont();
		font.setFontName("微软雅黑");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet);
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
		Cell cell = row.createCell(0);
		cell.setCellValue("供应商销售贡献度排行");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 32; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:AF1");

		// 第二行
		row = sheet.createRow(1);
		
		CellStyle leftSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		leftSencondRowStyle.setFont(font);
		leftSencondRowStyle.setAlignment(CellStyle.ALIGN_LEFT);
		CellStyle rightSencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		rightSencondRowStyle.setFont(font);
		rightSencondRowStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		firstRowStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(leftSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额同比（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比同比");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比排名");
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比同比排名");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量(公斤)");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量同比(公斤)");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比同比");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比排名");
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比同比排名");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额同比（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比同比");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比排名");
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比同比排名");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额同比（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额占比");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额占比同比");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额占比排名");
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货金额占比同比排名");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量(公斤)");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量同比(公斤)");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量占比");

		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量占比同比");
		
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量占比排名");
		cell = row.createCell(i);
		cell.setCellStyle(rightSencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("进货量占比同比排名");
	}
	
	/**
	 * 为sheet填充数据
	 * 
	 * @param sheet
	 *            sheet
	 * @param wb
	 *            工作簿
	 * @param list
	 *            数据
	 */
	private void fillDataCell(List<SupplierSalesContribution> list, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		strStyle.setFont(font);
		
		CellStyle intStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		intStyle.setFont(font);
		intStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		intStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0"));
		
		CellStyle perStyle = ExcelUtils.getDefaultPercentStyle(wb);// 百分比样式
		perStyle.setFont(font);
		perStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		perStyle.setDataFormat(wb.createDataFormat().getFormat("0.00%"));
		
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		amtStyle.setFont(font);
		amtStyle.setAlignment(CellStyle.ALIGN_RIGHT);
		amtStyle.setDataFormat(wb.createDataFormat().getFormat("#,##0.00"));

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == null) {
				continue;
			}
			writeOneRowDate(sheet, i, list.get(i), strStyle, amtStyle, perStyle, intStyle);
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
	 *@param perStyle
	 *            百分比格式
	 */
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, SupplierSalesContribution sc, CellStyle strStyle,  CellStyle amtStyle, CellStyle perStyle,CellStyle intStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		// 供应商编号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(sc.getSupplierCode());

		// 供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(sc.getSupplierName());
		
		// 销售金额
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getSellTotalPrice()));
		
		// 销售金额同比
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getLastSellTotalPrice()));
		
		// 销售金额占比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getSellTotalPriceChange()));
		
		// 销售金额占比同比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getLastSellTotalPriceChange()));
		
		// 销售金额占比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getSellTotalPriceRank());
		
		// 销售金额占比同比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getLastSellTotalPriceRank());
		
		// 销量(公斤)
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getSellQuantity()));
		
		// 销量同比(公斤)
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getLastSellQuantity()));
		
		// 销量占比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getSellQuantityChange()));
		
		// 销量占比同比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getLastSellQuantityChange()));
		
		// 销量占比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getSellQuantityRank());
		
		// 销量占比同比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getLastSellQuantityRank());

		// 毛利额
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getSellProfit()));
		
		// 毛利额同比
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getLastSellProfit()));
		
		// 毛利额占比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getSellProfitChange()));
		
		// 毛利额占比同比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getLastSellProfitChange()));
		
		// 毛利额占比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getSellProfitRank());
		
		// 毛利额占比同比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getLastSellProfitRank());

		// 进货金额
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getReceiptPrice()));
		
		// 进货金额同比
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getLastReceiptPrice()));
		
		// 进货金额占比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getReceiptPriceChange()));
		
		// 进货金额占比同比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getLastReceiptPriceChange()));
		
		// 进货金额占比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getReceiptPriceRank());
		
		// 进货金额占比同比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getLastReceiptPriceRank());

		// 进货量(公斤)
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getReceiptRationed()));
		
		// 进货量同比(公斤)
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.bigDecimalToString(sc.getLastReceiptRationed()));
		
		// 进货量占比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getReceiptRationedChange()));
		
		// 进货量占比同比
		cell = row.createCell(j++);
		cell.setCellStyle(perStyle);
		cell.setCellValue(StrUtils.formatterStrPercent(sc.getLastReceiptRationedChange()));
		
		// 进货量占比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getReceiptRationedRank());
		
		// 进货量占比同比排名
		cell = row.createCell(j++);
		cell.setCellStyle(intStyle);
		cell.setCellValue(sc.getLastReceiptRationedRank());
		
	}

}