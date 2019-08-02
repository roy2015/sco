package com.powere2e.sco.service.impl.datamaintenance.purchaserdata.marketsurveydata;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyDataDao;
import com.powere2e.sco.interfaces.service.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyDataService;
import com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata.MarketSurveyData;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.masterdata.MerchandiseServiceImpl;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 竞品价格数据Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月30日
 * @version 1.0
 */
public class MarketSurveyDataServiceImpl extends ServiceImpl implements
		MarketSurveyDataService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -964184845108630490L;
	private MarketSurveyDataDao marketSurveyDataDao;

	// 获取MarketSurveyData Dao实例
	public MarketSurveyDataDao getMarketSurveyDataDao() {
		return marketSurveyDataDao;
	}

	// 设置MarketSurveyData Dao实例
	public void setMarketSurveyDataDao(MarketSurveyDataDao marketSurveyDataDao) {
		this.marketSurveyDataDao = marketSurveyDataDao;
	}

	@Override
	public List<MarketSurveyData> listMarketSurveyData(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.marketSurveyDataDao.listMarketSurveyData(map, pageInfo);
	}
	
	@Override
	public String completeImportMarketSurveyData(File file) {
		
		//查询主数据中所有商品
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> mList = MerchandiseServiceImpl.getInstance().listMerchandiseCode(map);
		
		//用于存储消息
		String msg = "";
		if(mList.isEmpty()) return "SAP中目前没有商品数据,请先同步商品主数据";
		//用于存储excel中的数据
		List<MarketSurveyData> dataList = new ArrayList<MarketSurveyData>();
		try {
			//注册Double类型默认值为null
//			ConvertUtils.register(new BigDecimalConverter("&&"), Double.class);
			dataList = this.parseExcel(file, mList, dataList);
		} catch (Exception e) {
			msg = e.getMessage();
		}finally {
			//取消注册Double类型默认值为null
//			ConvertUtils.deregister();
		}
		if (msg.length() > 0) {//有错误消息
			return msg.toString();
		} else if (dataList.isEmpty()) {//文件中无数据
			return "请在文件中填写需上传数据";
		} else {
			this.insertMarketSurverData(dataList);
		}
		return null;
	}
	
	@Override
	public void deleteMarketSurveyData(Map<String, Object> map) {
		this.marketSurveyDataDao.deleteMarketSurveyData(map);
	}

	@Override
	public void exportSignedQtyExcel(Map<String, Object> map,
			ServletOutputStream out) {
		List<MarketSurveyData> list = this.listMarketSurveyData(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("竞品价格市调数据");

		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet);
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
	 * 保存页面查询结果
	 */
	@Override
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", this.listMarketSurveyData(paraMap, null));
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("marketSurveyData/marketSurveyDataReport.ftl",
					"marketSurveyData".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);//报表文件
		if(file.exists()) {
			try {
				Reports myReport = new Reports(
						BusinessConstants.myReportType.MPD.toString(),
						fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MarketSurveyDataServiceImpl.saveSearchDataForm删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	/**
	 * 解析Excel
	 * 
	 * @param file
	 *            上传的文件
	 * @param mList
	 *            商品编码
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的商品调价数据
	 * @throws Exception
	 */
	private List<MarketSurveyData> parseExcel(File file,
			final List<String> mList, final List<MarketSurveyData> dataList)
			throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		// 获取表格(开始行号)
		int idx = 2;

		StringBuilder sb = new StringBuilder();//存储错误信息
		for (; idx <= handler.getLastRowNum(); idx++) {//循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			MarketSurveyData msd = this.processRow(idx, rowData, mList, sb);
			if(sb.length() == 0 && msd != null) {
				dataList.add(msd);
			}
		}
		// 检测是否有错误
		if (sb.length() > 0) {
			throw new EscmException(sb.toString());
		}
		return dataList;
	}
	
	/**
	 * 处理excel一行的数据
	 * 
	 * @param idx
	 *            当前行数
	 * @param rowData
	 *            当前行数据
	 * @param mList
	 *            商品主数据
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private MarketSurveyData processRow(int idx, ExcelRow rowData,
			List<String> mList, StringBuilder msg) {
		StringBuilder sb = new StringBuilder();

		String dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		if ("".equals(dataA)) {
			sb.append("第" + idx + "行A列:商品编号不可为空<br>");
		} else {
			if (!mList.contains(dataA)) {
				sb.append("第" + idx + "行A列:商品编号在SAP中不存在<br>");
			} else {
				if (dataA.length() > 30){
					sb.append("第" + idx + "行A列:商品编号长度不能超过30<br>");
				}
			}
		}
		
		String dataB = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
		if ("".equals(dataB)) {
			sb.append("第" + idx + "行B列:竞品卖场名称不可为空<br>");
		} else {
			if (dataB.length() > 100) {
				sb.append("第" + idx + "行B列:竞品卖场名称长度不能超过100<br>");
			}
		}
		
		String dataC = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
		if ("".equals(dataC)) {
			sb.append("第" + idx + "行C列:竞品卖场地区不可为空<br>");
		} else {
			if (dataC.length() > 100) {
				sb.append("第" + idx + "行C列:竞品卖场地区长度不能超过100<br>");
			}
		}
		
		String dataD = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("D"));
		if ("".equals(dataD)) {
			sb.append("第" + idx + "行D列:竞品价格单位不可为空<br>");
		} else {
			if (dataD.length() > 30) {
				sb.append("第" + idx + "行D列:竞品价格单位长度不能超过30<br>");
			}
		}
		
		Double dataE = null;
		try {
			dataE = rowData.getDoubleCellValue("E");
		} catch (Exception e) {
			sb.append("第" + idx + "行E列:请填入数字类型<br>");
			dataE = 0.0;
		}
		if (dataE == null) {
			sb.append("第" + idx + "行E列:竞品净含量不可为空<br>");
		} 
		
		Date dataF = null;
		try {
			dataF = rowData.getDateCellValue("F", Constant.DATA_INTEFACE_DATEFORMATE);
		} catch (Exception e) {
			sb.append("第" +idx + "行F列:市调价格日期格式不符,格式应为YYYY-MM-DD<br>");
			dataF = new Date();
		}
		if (dataF == null){
			sb.append("第" +idx + "行F列:市调价格日期不可为空<br>");
		} else {
			Calendar cl = Calendar.getInstance();
			cl.set(Calendar.YEAR, 5000);
			if(cl.getTimeInMillis() < dataF.getTime()) {
				sb.append("第" +idx + "行F列:市调价格日期格式不符,格式应为YYYY-MM-DD<br>");
			}
		}
		
		Double dataG = null;
		try {
			dataG = rowData.getDoubleCellValue("G");
		} catch (Exception e) {
			sb.append("第" + idx + "行G列:请填入数字类型<br>");
			dataG = 0.0;
		}
		if (dataG == null) sb.append("第" +idx + "行G列:市调售价不可为空<br>");
		// dataA~~dataG只要有一个为null,必会有错误消息,返回必为null
		/*if("".equals(dataA) && "".equals(dataB) && "".equals(dataC)
				&& "".equals(dataD) && null == dataE && null == dataF 
				&& null == dataG){
			return null;
		} */
		msg.append(sb.toString());
		if(sb.length() > 0) {
			return null;
		}
//		String id = MasterDataTypeServiceImpl.getInstance().nextID("s_market_survey_data");
//		取出的ID重复,在xml中再取值
		return new MarketSurveyData("XXX", dataA, dataB, dataC, dataD, dataE, dataF, dataG);
	}

	/**
	 * 新增竞品价格市调数据
	 * 
	 * @param dataList
	 */
	private void insertMarketSurverData(List<MarketSurveyData> list) {
		// 批量插入
		Map<String, Object> map = new HashMap<String, Object>();
		// List分批插入(每批100条)
		int listSize = list.size();
		int maxCount = listSize / Constant.BATCH_SIZE;
		int i = 0;
		for (; i < maxCount; i++) {
			int toIndex = (i + 1) * Constant.BATCH_SIZE > listSize ? listSize
					: (i + 1) * Constant.BATCH_SIZE;
			map.put("list", list.subList(i * Constant.BATCH_SIZE, toIndex));
			this.marketSurveyDataDao.insertMarketSurverData(map);
		}
		// 防止遗漏数据
		int currentIndex = i * Constant.BATCH_SIZE;
		if (currentIndex < list.size()) {
			map.put("list", list.subList(currentIndex, list.size()));
			this.marketSurveyDataDao.insertMarketSurverData(map);
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
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("市调数据");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 21; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:U1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 90));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("细分类");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品售价（元）");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("价格单位");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("净含量（kg）");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("折后公斤价（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("卖场名称");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("卖场地区");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("竞品价格单位（元/kg）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("竞品净含量（kg）");
		
		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("市调价格日期");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("市调售价（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("竞品折后公斤价（元）");
		
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
	private void fillDataCell(List<MarketSurveyData> list, Workbook wb,
			Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 日期样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, i, list.get(i), strStyle, dateStyle,
					amtStyle);
		}
	}
	
	/**
	 * excel一行的数据
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
	private void writeOneRowDate(Sheet sheet, Integer rowIndex,
			MarketSurveyData msd, CellStyle strStyle, CellStyle dateStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
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
		cell.setCellStyle(amtStyle);
		BigDecimal sellPrice = msd.getSellPrice();
		if (sellPrice != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(sellPrice, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		String storageForm = msd.getStorageForm();
		cell.setCellValue(storageForm);

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal netWeight = msd.getNetWeight();
		if (netWeight != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(netWeight, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal discountPrice = msd.getDiscountPrice();
		if(discountPrice != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(discountPrice, "#,##0.00"));
		}
		
		/*if (sellPrice != null && netWeight != null) {
			if ("公斤".equals(storageForm)) {
				cell.setCellValue(sellPrice.doubleValue());
			} else {
				cell.setCellValue(sellPrice.divide(netWeight, 2, BigDecimal.ROUND_HALF_UP)
						.doubleValue());
			}
		}*/

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getJpmcName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMcRegion());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		String jpPriceUnits = msd.getJpPriceUnits();
		cell.setCellValue(jpPriceUnits);

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal jpContent = msd.getJpContent();
		if (jpContent != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(jpContent, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		Date marketSurveyDate = msd.getMarketSurveyDate();
		if(marketSurveyDate != null)
			cell.setCellValue(DateUtils.formatDateToStr(marketSurveyDate, Constant.DATA_INTEFACE_DATEFORMATE));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal marketSurveyPrice = msd.getMarketSurveyPrice();
		if (marketSurveyPrice != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(marketSurveyPrice, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal mdiscountPrice = msd.getMdiscountPrice();
		if(mdiscountPrice != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(mdiscountPrice, "#,##0.00"));
		}
		/*if (marketSurveyPrice != null && jpContent != null) {
			if ("公斤".equals(jpPriceUnits)) {
				cell.setCellValue(marketSurveyPrice.doubleValue());
			} else {
				cell.setCellValue(marketSurveyPrice.divide(jpContent, 2,
						BigDecimal.ROUND_HALF_UP).doubleValue());
			}
		}*/

	}

}