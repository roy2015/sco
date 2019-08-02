package com.powere2e.sco.service.impl.materialmarketanalysis.websitehistory;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants.myReportType;
import com.powere2e.sco.common.utils.BigDecimalConverter;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.websitehistory.WebsiteHistoryDao;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.websitehistory.WebsiteHistoryService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 公示网站原料历史行情 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public class WebsiteHistoryServiceImpl extends ServiceImpl implements
		WebsiteHistoryService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4904854378886480008L;
	private WebsiteHistoryDao websiteHistoryDao;

	public WebsiteHistoryDao getWebsiteHistoryDao() {
		return websiteHistoryDao;
	}

	public void setWebsiteHistoryDao(WebsiteHistoryDao websiteHistoryDao) {
		this.websiteHistoryDao = websiteHistoryDao;
	}

	/**
	 * 查看历史价格
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 历史价格数据
	 */
	@Override
	public List<WebsiteHistory> listHisPrice(Map<String, Object> map,
			PageInfo pageInfo) {
		List<WebsiteHistory> list = this.websiteHistoryDao.listHisPrice(map,
				pageInfo);
		if (!list.isEmpty()) {
			this.calculatePrice(list);
			list = list.subList(1, list.size());
		}
		return list;
	}

	/**
	 * 计算、统计价格
	 * 
	 * @param list
	 *            所有年-月数据
	 */
	private void calculatePrice(List<WebsiteHistory> list) {
		for (int i = 1; i < list.size(); i++) {
			WebsiteHistory curYearWeb = list.get(i);
			List<WebsiteHistory> curPriceList = curYearWeb.getList();
			BigDecimal curYear = BigDecimal.ZERO;
			BigDecimal lastYear = BigDecimal.ZERO;
			for (int j = 0; j < curPriceList.size(); j++) {
				// 上一年的月份价格
				BigDecimal lastMonPrice = list.get(i - 1).getList().get(j)
						.getPrice();
				// 当前年月价格
				WebsiteHistory curMonWeb = curPriceList.get(j);
				BigDecimal curMonPrice = curMonWeb.getPrice();
				if (lastMonPrice == null || curMonPrice == null) {

				} else if (lastMonPrice != BigDecimal.ZERO) {
					// 每月同比增长
					curMonWeb.setIncParcent(curMonPrice.subtract(lastMonPrice)
							.divide(lastMonPrice, 4, BigDecimal.ROUND_HALF_UP));
				} else {
					curMonWeb.setIncParcent(BigDecimal.ZERO);
				}

				// 每年合计
				if (lastMonPrice != null && lastYear != null) {
					lastYear = lastYear.add(lastMonPrice);
				} else {
					lastYear = null;
				} 

				if (curMonPrice != null && curYear != null) {
					curYear = curYear.add(curMonPrice);
				} else {
					curYear = null;
				} 
				
			}

			// 全年平均价
			if (curYear != null)
				curYearWeb.setAvgPrice(curYear.divide(new BigDecimal(12), 2,
						BigDecimal.ROUND_HALF_UP));

			// 全年同比增长
			if (curYear == null || lastYear == null) {

			} else if (lastYear != BigDecimal.ZERO) {
				curYearWeb.setIncParcent(curYear.subtract(lastYear).divide(
						lastYear, 4, BigDecimal.ROUND_HALF_UP));
			} else {
				curYearWeb.setIncParcent(BigDecimal.ZERO);
			}
		}
	}

	@Override
	public void exportHisPrice(ServletOutputStream out,
			List<WebsiteHistory> listData, Map<String, Object> pubMap) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("历史行情数据");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle rigthStyle = ExcelUtils.getDefaultPercentStyle(wb);// 金额样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);
		// 1. 创建excel表头(第一行、第二行、第三行)
		this.fillHisHeader(wb, sheet, strStyle, rigthStyle, listData, pubMap);
		// 2.用数据填充单元格
		if (!listData.isEmpty()) this.fillAllDataCell(sheet, listData, strStyle, amtStyle);

		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常",
					new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常",
						new String[] { e.getMessage() });
			}
		}
	}

	@Override
	public String saveHistoryPrice(String fileName, List<WebsiteHistory> list,
			Map<String, Object> pubMap) {
		pubMap.put("dataList", list);
		pubMap.put("dataListJson",
				JSONArray.fromObject(list, BigDecimalConverter.setBigDecimalDefaultValue()).toString());
		return this.saveReport(fileName, myReportType.PSH.toString(), pubMap,
				"historyPrice.ftl", "websitehistory");
	}
	
	@Override
	public List<RelevanceMaterialAndWebsite> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {
		return this.websiteHistoryDao.listMerchandise(map, pageInfo);
	}

	@Override
	public void exportHisMerchandise(ServletOutputStream out,
			List<RelevanceMaterialAndWebsite> listData, Map<String, Object> pubMap) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品列表");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		// 1. 创建excel表头(第一行、第二行、第三行)
		this.fillMerHeader(wb, sheet, strStyle, listData, pubMap);
		// 2.用数据填充单元格
		if (!listData.isEmpty()) this.fillMerDataCell(sheet, listData, strStyle);

		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常",
					new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null)
					out.close();
			} catch (IOException e) {
				throw new EscmException("导出Excel时异常",
						new String[] { e.getMessage() });
			}
		}
	}
	
	/**
	 * 商品表头
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheeet
	 * @param strStyle
	 *            字符串样式
	 * @param listData
	 *            数据
	 * @param pubMap
	 *            公共参数
	 */
	private void fillMerHeader(Workbook wb, Sheet sheet, CellStyle strStyle,
			List<RelevanceMaterialAndWebsite> listData,
			Map<String, Object> pubMap) {
		int index = 0;
		Row row = sheet.createRow(index);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.createCellAndmerged(sheet, row, 0, 13, firstRowStyle, "A1:M1", "商品列表");
		
		row = sheet.createRow(++index);
		String val = "原料大类：" + pubMap.get("materialBigTypeName")
				+ "\n原料小类：" + pubMap.get("materialSmallTypeName")
				+ "\n原料名称：" + pubMap.get("materialName")
				+ "\n公示网站名称：" + pubMap.get("websiteName")
				+ "\n地区：" + pubMap.get("priceRegionName");
		CellStyle secRowStyle = wb.createCellStyle();
		secRowStyle.setWrapText(true);//换行
		ExcelUtils.setFrame(secRowStyle, true);
		ExcelUtils.createCellAndmerged(sheet, row, 0, 13, secRowStyle, "A2:M2", val);
		row.setHeightInPoints((5 * sheet.getDefaultRowHeightInPoints()));//换行后的自适应单元格高度
		
		row = sheet.createRow(++index);
		int col = 0;
		Cell cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (40 * 80));// 设置当前行单元格宽度
		cell.setCellValue("序号");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");
		
		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");
		

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");
		

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");
		
		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");
		

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("关联人");

		cell = row.createCell(col);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(col++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("关联时间");
		
	}

	/**
	 * 写入商品数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param listData
	 *            商品数据
	 * @param strStyle
	 *            字符串样式
	 */
	private void fillMerDataCell(Sheet sheet,
			List<RelevanceMaterialAndWebsite> listData, CellStyle strStyle) {
		for (int i = 0; i < listData.size(); i++) {
			RelevanceMaterialAndWebsite relWeb = listData.get(i);
			Row row = sheet.createRow(3 + i);

			int j = 0;
			Cell cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(i + 1);

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			 cell.setCellValue(relWeb.getMerchandiseCode());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getMerchandiseName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getSupplierCode());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getSupplierName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getDxRoleName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getDlRoleName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getCentreName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getSmallName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getDetailName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getFineName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(relWeb.getCreateby());
			
			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(DateUtils.formatDateToStr(relWeb.getCreated(),
					Constant.DATA_INTEFACE_DATEFORMATE));
		}
	}
	
	/**
	 * 创建历史价格数据Excel表头
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 * @param strStyle
	 *            字符串样式
	 * @param rigthStyle
	 *            居右格式
	 * @param listData
	 *            查询数据
	 * @param pubMap
	 *            公共参数
	 */
	private void fillHisHeader(Workbook wb, Sheet sheet,
			CellStyle strStyle, CellStyle rigthStyle, List<WebsiteHistory> listData, Map<String, Object> pubMap) {
		// 第一行
		int index = 0;
		Row row = sheet.createRow(index);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.createCellAndmerged(sheet, row, 0, 14, firstRowStyle, "A1:N1", "历史行情数据");
		
		row = sheet.createRow(++index);
		String val = "原料大类：" + pubMap.get("materialBigTypeName") 
				+ "\n原料小类：" + pubMap.get("materialSmallTypeName")
				+ "\n原料名称：" + pubMap.get("materialName")
				+ "\n公示网站名称：" + pubMap.get("websiteName")
				+ "\n地区：" + pubMap.get("priceRegionName");
		CellStyle secRowStyle = wb.createCellStyle();
		secRowStyle.setWrapText(true);//换行
		ExcelUtils.setFrame(secRowStyle, true);
		ExcelUtils.createCellAndmerged(sheet, row, 0, 14, secRowStyle, "A2:N2", val);
		row.setHeightInPoints((5 * sheet.getDefaultRowHeightInPoints()));//换行后的自适应单元格高度
		
		this.fillYearAndMonth(sheet, sheet.createRow(++index), strStyle, rigthStyle);
		
		index = 5 + listData.size();
		this.fillYearAndMonth(sheet, sheet.createRow(index), strStyle, rigthStyle);
	}
	
	/**
	 * 表头中的年月
	 * @param sheet 
	 * 
	 * @param row
	 *            当前行
	 * @param strStyle
	 *            居左样式
	 * @param rigthStyle
	 *            居右样式
	 */
	private void fillYearAndMonth(Sheet sheet, Row row, CellStyle strStyle, CellStyle rigthStyle) {
		int index = 0;
		Cell cell = row.createCell(index++);
		cell.setCellStyle(strStyle);
		cell.setCellValue("年月");
		for (; index < 14; ) {
			cell = row.createCell(index);
			sheet.setColumnWidth(index, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellStyle(rigthStyle);
			cell.setCellValue(index);
			index++;
		}
		cell = row.createCell(index - 1);
		cell.setCellStyle(rigthStyle);
		cell.setCellValue("年平均");
		sheet.setColumnWidth(index, (short) (60 * 80));// 设置当前行单元格宽度
	}

	/**
	 * 每月平均价格
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param listData
	 *            数据
	 * @param strStyle
	 *            字符串格式
	 * @param amtStyle
	 *            数组格式
	 */
	private void fillAllDataCell(Sheet sheet,
			List<WebsiteHistory> listData, CellStyle strStyle, CellStyle amtStyle) {
		int blankRow = listData.size() + 6; 
		for (int index = 0; index < listData.size(); index++) {
			WebsiteHistory web = listData.get(index);
			//价格表
			Row row = sheet.createRow(3 + index);
			this.fillTable(sheet, row, web, true, strStyle, amtStyle);
			
			//价格统计价表
			row = sheet.createRow(blankRow + index);
			this.fillTable(sheet, row, web, false, strStyle, amtStyle);
			
		}
	}
	
	/**
	 * 填写表格数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param row
	 *            当前行
	 * @param web
	 *            当前实例
	 * @param flag
	 *            是否为第一个表格
	 * @param strStyle
	 *            字符串样式
	 * @param amtStyle
	 *            数字样式
	 */
	private void fillTable(Sheet sheet, Row row, WebsiteHistory web, boolean flag, CellStyle strStyle, CellStyle amtStyle) {
		Cell cell = row.createCell(0);
		cell.setCellStyle(strStyle);
		String priceYear = web.getPriceYear();
		cell.setCellValue(flag ? priceYear : priceYear + "同比增长");
		sheet.setColumnWidth(0, (short) (60 * 60));// 设置当前行单元格宽度
		List<WebsiteHistory> wList = web.getList();
		//当前年每一个月
		for (int i = 0; i < wList.size(); i++) {
			
			cell = row.createCell(i+1);
			cell.setCellStyle(amtStyle);
			if(flag) {
				BigDecimal price = wList.get(i).getPrice();
				if (price != null) cell.setCellValue(DecimalFormatUtils.removeRemain(price, "#,##0.00"));
			} else {
				BigDecimal incParcent = wList.get(i).getIncParcent();
				if (incParcent != null) {
					cell.setCellValue(DecimalFormatUtils.formatBigPercent(incParcent));
				}
			}
		}
		cell = row.createCell(wList.size()+1);
		cell.setCellStyle(amtStyle);
		//价格表还是统计表
		if (flag) {
			BigDecimal avgPrice = web.getAvgPrice();
			if (avgPrice != null) {
				cell.setCellValue(DecimalFormatUtils.removeRemain(avgPrice, "#,##0.00"));
			}
		} else {
			BigDecimal incParcent = web.getIncParcent();
			if (incParcent != null) {
				cell.setCellValue(DecimalFormatUtils.formatBigPercent(incParcent));
			}
		}
		
	}

	/**
	 * 保存报表
	 * 
	 * @param fileName
	 *            保存后的文件名
	 * @param reportType
	 *            报表类型
	 * @param paraMap
	 *            所需参数
	 * @param ftlName
	 *            使用模板名称
	 * @param dirName
	 *            生成目录名称
	 * @return 保存消息
	 */
	private String saveReport(String fileName, String reportType, Map<String, Object> paraMap,
			String ftlName, String dirName) {
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("materialmarketanalysis/websitehistory/" + ftlName,
					dirName.concat("/").concat(fileName), paraMap);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);//报表文件
		if(file.exists()) {
			try {
				Reports myReport = new Reports(reportType, fileName,
						tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("WebsiteHistoryServiceImpl.saveReport删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public String saveHistoryMerchandise(String fileName,
			List<RelevanceMaterialAndWebsite> list, Map<String, Object> pubMap) {
		pubMap.put("dataList", list);
		return this.saveReport(fileName, myReportType.PSH.toString(), pubMap,
				"historyMerchandise.ftl", "websitehistory");
	}

}
