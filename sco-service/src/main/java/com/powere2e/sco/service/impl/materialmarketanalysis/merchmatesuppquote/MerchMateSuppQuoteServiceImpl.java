package com.powere2e.sco.service.impl.materialmarketanalysis.merchmatesuppquote;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
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

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants.myReportType;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuoteDao;
import com.powere2e.sco.interfaces.service.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuoteService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;
import com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote.MerchMateSuppQuote;
import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品原料供应商报价 业务类的实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月30日
 * @version 1.0
 */
public class MerchMateSuppQuoteServiceImpl extends ServiceImpl implements
		MerchMateSuppQuoteService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2844100114546309754L;
	private MerchMateSuppQuoteDao merchMateSuppQuoteDao;

	public MerchMateSuppQuoteDao getMerchMateSuppQuoteDao() {
		return merchMateSuppQuoteDao;
	}

	public void setMerchMateSuppQuoteDao(MerchMateSuppQuoteDao merchMateSuppQuoteDao) {
		this.merchMateSuppQuoteDao = merchMateSuppQuoteDao;
	}

	@Override
	public List<RelevanceMaterialAndWebsite> listMerchandise(
			Map<String, Object> map, PageInfo pageInfo) {
		return this.merchMateSuppQuoteDao.listMerchandise(map, pageInfo);
	}

	@Override
	public List<MerchMateSuppQuote> listMerMateHisPrice(Map<String, Object> map) {
		List<MerchMateSuppQuote> list = this.merchMateSuppQuoteDao.listMerMateHisPrice(map);
		MerchMateSuppQuote sq = this.merchMateSuppQuoteDao.listAccAndIng(map);
		Map<String, BigDecimal> accMap = new HashMap<String, BigDecimal>();
		for (MerchMateSuppQuote acc : sq.getAccList()) {
			accMap.put(acc.getPriceYear(), acc.getPrice());
		}
		Map<String, BigDecimal> ingMap = new HashMap<String, BigDecimal>();
		for (MerchMateSuppQuote ing : sq.getIngList()) {
			ingMap.put(ing.getPriceYear(), ing.getPrice());
		}
		for (MerchMateSuppQuote dl : list) {
			String year = dl.getPriceYear();
			this.setListVal(year, dl.getAccList(), accMap);
			this.setListVal(year, dl.getIngList(), ingMap);
		}
		return list;
	}

	/**
	 * 设置采购价格、投料占比的值
	 * 
	 * @param year
	 *            年份
	 * @param list
	 *            采购价格或投料占比
	 * @param resMap
	 *            采购价格或投料占比最近的记录
	 */
	private void setListVal(String year, List<MerchMateSuppQuote> list, Map<String, BigDecimal> resMap) {
		int priceYear = Integer.valueOf(year);
		
		BigDecimal firVal = this.searchFirRecentlyVal(priceYear, 1, resMap);
		list.add(new MerchMateSuppQuote(year + "-01", firVal));
		for (int j = 2; j <= 12; j++) {
			String thisYear = year + "-" + StrUtils.formatStrLength(j, 2);
			BigDecimal curVal = resMap.get(thisYear);
			if (curVal == null) {//如果本月为null，设置为上月的值
				list.add(new MerchMateSuppQuote(thisYear, firVal));
			} else { //不然就是bd
				list.add(new MerchMateSuppQuote(thisYear, curVal));
				firVal = curVal;
			}
		}
	}

	/**
	 * 查询一月份及之前的值
	 * 
	 * @param year
	 *            年
	 * @param month
	 *            月
	 * @param map
	 *            所有数据
	 * @return
	 */
	private BigDecimal searchFirRecentlyVal(int year, int month, Map<String, BigDecimal> map) {
		for (int i = year; i >= 1999; i--) {
			for (int j = 12; j >= 1; j--) {
				if (i == year && j > month) continue;//若是当前年，但若大于所在月份，才不用查找
					BigDecimal bd = map.get(i + "-" + StrUtils.formatStrLength(j, 2)); 
					if (bd != null) return bd; 
			}
		}
		return null;
	}
	
	@Override
	public void exportMerMat(ServletOutputStream out,
			List<MerchMateSuppQuote> list, RelevanceMaterialAndWebsite relMatWeb) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品原料历史价格数据");
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);
		// 1. 创建excel表头(第一行、第二行、第三行)
		this.fillHisHeader(wb, sheet, strStyle, relMatWeb);
		// 2.用数据填充单元格
		if (!list.isEmpty()) this.fillAllDataCell(sheet, list, strStyle, amtStyle);
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
	public String saveMerMat(String fileName, Map<String, Object> map) {
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("materialmarketanalysis/merchmatesuppquote/merchmatehistoryPrice.ftl",
					"merchMateSuppQuote/".concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);//报表文件
		if(file.exists()) {
			try {
				Reports myReport = new Reports(myReportType.GSH.toString(), fileName,
						tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchMateSuppQuoteServiceImpl.saveMerMat删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}
	
	/**
	 * 表格前几行
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 * @param strStyle
	 *            字符串格式
	 * @param relMatWeb
	 *            查询条件
	 */
	private void fillHisHeader(Workbook wb, Sheet sheet, CellStyle strStyle,
			RelevanceMaterialAndWebsite relMatWeb) {
		// 第一行
		int index = 0;
		Row row = sheet.createRow(index);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		ExcelUtils.createCellAndmerged(sheet, row, 0, 14, firstRowStyle, "A1:N1", "商品原料历史价格数据");
		
		row = sheet.createRow(++index);
		CellStyle secRowStyle = wb.createCellStyle();
		secRowStyle.setWrapText(true);//换行
		ExcelUtils.setFrame(secRowStyle, true);
		Cell cell = row.createCell(0);
		sheet.setColumnWidth(0, (short) (90 * 90));// 设置当前行单元格宽度
		cell.setCellStyle(secRowStyle);//设置改行样式
		String searchText = "商品编号：" + relMatWeb.getMerchandiseCode()
						+ "\n商品名称：" + relMatWeb.getMerchandiseName()
						+ "\n供应商编号：" + relMatWeb.getSupplierCode()
						+ "\n供应商名称：" + relMatWeb.getSupplierName()
						+ "\n商品原料类型：" + relMatWeb.getMaterialTypeName()
						+ "\n商品原料名称：" + relMatWeb.getIngredientCodeName();
		cell.setCellValue(searchText);
		
		for (int i = 1; i < 14; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(strStyle);//设置改行样式
		}
		row.setHeightInPoints((6*sheet.getDefaultRowHeightInPoints()));//换行后的自适应单元格高度
		ExcelUtils.addMergedRegion(sheet, "A2:N2");
	}

	/**
	 * 填写数据
	 * 
	 * @param sheet
	 * @param list
	 * @param strStyle
	 * @param amtStyle
	 */
	private void fillAllDataCell(Sheet sheet, List<MerchMateSuppQuote> list,
			CellStyle strStyle, CellStyle amtStyle) {
		int index = 2;
		Calendar cl = Calendar.getInstance();
		int curMonth = cl.get(Calendar.MONTH);
		int curYear = cl.get(Calendar.YEAR);
		for (MerchMateSuppQuote dl : list) {
			this.crateTabHeader(sheet, ++index, dl.getPriceYear(), strStyle, amtStyle);
			index = this.fillTabData(sheet, ++index, dl, curYear,curMonth, strStyle, amtStyle);
		}
	}
	
	/**
	 * 创建表头
	 * 
	 * @param sheet
	 * @param index
	 * @param year
	 * @param strStyle
	 * @param amtStyle
	 */
	private void crateTabHeader(Sheet sheet, int index, String year,
			CellStyle strStyle, CellStyle amtStyle) {
		Row row = sheet.createRow(index);
		Cell cell = row.createCell(0);
		cell.setCellStyle(strStyle);
		cell.setCellValue(year+"年");
		for (int i = 1; i < 13; i++) {
			cell = row.createCell(i);
			sheet.setColumnWidth(i, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellStyle(amtStyle);
			cell.setCellValue(i+"月");
		}
		cell = row.createCell(13);
		sheet.setColumnWidth(13, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellStyle(amtStyle);
		cell.setCellValue("年平均");
	}
	
	/**
	 * 填充表数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param index
	 *            当前行
	 * @param dl
	 *            数据
	 * @param curYear
	 *            当前所处年
	 * @param curMonth
	 *            当前所处月
	 * @param strStyle
	 *            字符串格式
	 * @param amtStyle
	 *            数字格式
	 * @return 移动后所在行
	 */
	private int fillTabData(Sheet sheet, int index, MerchMateSuppQuote dl,
			int curYear, int curMonth, CellStyle strStyle, CellStyle amtStyle) {
		Row row = sheet.createRow(index++);
		int col = 0 ;
		Cell cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		//1.投料表内供应商原料采购价格
		cell.setCellValue("投料表内供应商原料采购价格");
		for (MerchMateSuppQuote al : dl.getAccList()) {
			cell = row.createCell(col++);
			cell.setCellStyle(amtStyle);
			BigDecimal price = al.getPrice();
			if (price != null) cell.setCellValue(DecimalFormatUtils.removeRemain(price, "#,##0.00"));
		}
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		
		//2.公示网站
		String oYear = dl.getPriceYear();
		Integer thisYear = Integer.valueOf(oYear);
		for (WebsiteHistory w : dl.getWebList()) {
			col = 0;
			String webName = w.getPriceYear();
			if (webName == null) continue;
			row = sheet.createRow(index++);
			cell = row.createCell(col++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(webName);
			
			boolean addFlag = true;//是否计算平均值
			BigDecimal sumPrice = BigDecimal.ZERO;
			if (thisYear >= curYear) addFlag = false;//不用计算平均
			
			for (int i = 1; i < 13; i++) {
				cell = row.createCell(col++);
				cell.setCellStyle(amtStyle);
				Double bd = null;
				if (thisYear > curYear) continue;//如大于当前年，什么都不做
				for (WebsiteHistory z : w.getList()) {
					if (oYear.equalsIgnoreCase(z.getPriceDate())
							&& z.getPriceYear().equalsIgnoreCase(
									String.valueOf(i))) {
						if (thisYear <= curYear) {
							BigDecimal price = z.getPrice();
							if (price != null ){//设置本月值
								if ((thisYear == curYear && i<= curMonth) || 
									thisYear < curYear) {
									cell.setCellValue(DecimalFormatUtils.removeRemain(price, "#,##0.00"));
								}
							} else {
								addFlag = false;//当前月价格为Null
							}
							if (addFlag) {
								sumPrice = sumPrice.add(price);//计算所有月份值
							}
						}
					} 
				}	
				if (bd == null) addFlag = false;//当前月无数据
			}
			cell = row.createCell(col);
			cell.setCellStyle(amtStyle);
			if (addFlag) {//计算平均值
				BigDecimal d = (sumPrice.divide(new BigDecimal(12), BigDecimal.ROUND_HALF_UP, 2));
				cell.setCellValue(DecimalFormatUtils.removeRemain(d, "#,##0.00"));
			}
		}
		
		//3.投料占比
		row = sheet.createRow(index++);
		col = 0 ;
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		cell.setCellValue("投料占比");
		for (MerchMateSuppQuote il : dl.getIngList()) {
			cell = row.createCell(col++);
			cell.setCellStyle(amtStyle);
			BigDecimal price = il.getPrice();
			if (price != null) {
				cell.setCellValue(DecimalFormatUtils.removeRemain(price, "0.00") + "%");
			} else if (price == BigDecimal.ZERO) {
				cell.setCellValue("0");
			}
		}
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		
		//4.商品进价
		row = sheet.createRow(index++);
		col = 0 ;
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		cell.setCellValue("商品进价");
		for ( MerchMateSuppQuote pl : dl.getPurList()) {
			cell = row.createCell(col++);
			cell.setCellStyle(amtStyle);
			BigDecimal price = pl.getPrice();
			if (price != null) cell.setCellValue(DecimalFormatUtils.removeRemain(price, "#,##0.00"));
		}
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		
		//5.商品售价
		row = sheet.createRow(index++);
		col = 0 ;
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		cell.setCellValue("商品售价");
		for ( MerchMateSuppQuote sl : dl.getSoldList()) {
			cell = row.createCell(col++);
			cell.setCellStyle(amtStyle);
			BigDecimal price = sl.getPrice();
			if (price != null) cell.setCellValue(DecimalFormatUtils.removeRemain(price, "#,##0.00"));
		}
		cell = row.createCell(col++);
		cell.setCellStyle(strStyle);
		
		return index;
	}

}
