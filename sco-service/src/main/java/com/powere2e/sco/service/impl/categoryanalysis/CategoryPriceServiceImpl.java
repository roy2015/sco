package com.powere2e.sco.service.impl.categoryanalysis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
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
import com.powere2e.sco.common.utils.DecimalFormatUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.categoryanalysis.CategoryPriceDao;
import com.powere2e.sco.interfaces.service.categoryanalysis.CategoryPriceService;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品价格带  公共Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年05月28日
 * @version 1.0
 */
public class CategoryPriceServiceImpl extends ServiceImpl implements
		CategoryPriceService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6320584393843875130L;
	private CategoryPriceDao categoryPriceDao;

	public CategoryPriceDao getCategoryPriceDao() {
		return categoryPriceDao;
	}

	public void setCategoryPriceDao(CategoryPriceDao categoryPriceDao) {
		this.categoryPriceDao = categoryPriceDao;
	}

	@Override
	public List<CategoryPrice> listCollectData(Map<String, Object> map,
			PageInfo pageInfo) {
		List<CategoryPrice> list = this.categoryPriceDao.listCollectData(map, pageInfo);
		List<CategoryPrice> soldQtyList = this.categoryPriceDao.listSoldQty(map);//每个商品的PSD销量  
		List<CategoryPrice> soldPriceList = this.categoryPriceDao.listSoldPrice(map);//每个商品的PSD销售额  
		List<CategoryPrice> grossFitList = this.categoryPriceDao.listGrossFitQty(map);//每个商品的PSD毛利额
		
		//用于记录计算过的商品
		//[Q+priceRegion+mCode:已计算过PSD销量的商品; P+priceRegion+mCode:已计算过PSD销售额的商品;
		// G+priceRegion+mCode:已计算过PSD毛利额的商品]
		List<String> flagList = new ArrayList<String>();
		int i = 0, j = 0, k = 0;
		for (CategoryPrice mainData : list) {
			String regionPrice = mainData.getRegionPrice();
			mainData.setNotLessPsdUnitsSku(BigDecimal.ZERO);//初始化为0
			mainData.setLessPsdUnitsSku(BigDecimal.ZERO);//初始化为0
			
			mainData.setNotLessPsdSoldPrice(BigDecimal.ZERO);//初始化为0
			mainData.setLessPsdSoldPrice(BigDecimal.ZERO);//初始化为0
			
			mainData.setNotLessPsdGrossProfit(BigDecimal.ZERO);//初始化为0
			mainData.setLessPsdGrossProfit(BigDecimal.ZERO);//初始化为0
			
			BigDecimal psdQty = mainData.getPsdUnits();
			//PSD销量
			for (; i < soldQtyList.size(); i++) {
				CategoryPrice soldQty =  soldQtyList.get(i);
				String mRegionPrece = soldQty.getRegionPrice();//目前该商品的价格地区
				if (!mRegionPrece.equals(regionPrice)) {
					break;    //[这里本来应该都用continue的，不过查询出来时按照价格带排序,
							 //最重要的是每一个价格带一定会有至少一条数据(统计结果额外null)
							//,故用break也行,提高效率]
				}
				BigDecimal mPsdQty = soldQty.getPsdUnits();//目前该商品的PSD销量
				if (mPsdQty == null) continue; 
				String mCode = soldQty.getMerchandiseCode();//目前的商品
				if (mPsdQty.compareTo(psdQty) > -1 && !flagList.contains("Q"+mRegionPrece+mCode)) {//不小于
					mainData.setNotLessPsdUnitsSku(mainData.getNotLessPsdUnitsSku().add(new BigDecimal(1)));
					flagList.add("Q"+mRegionPrece+mCode);//添加到记录list中
				} else if (!flagList.contains("Q"+mRegionPrece+mCode)) {//小于
					mainData.setLessPsdUnitsSku(mainData.getLessPsdUnitsSku().add(new BigDecimal(1)));
					flagList.add("Q"+mRegionPrece+mCode);//添加到记录list中
				} 
			}
			
			//销售额
			BigDecimal psdPrice = mainData.getPsdSoldPrice();
			for (; j < soldPriceList.size(); j++) {
				CategoryPrice soldPrice =  soldPriceList.get(j);
				String mRegionPrece = soldPrice.getRegionPrice();//目前该商品的价格地区
				if (!mRegionPrece.equals(regionPrice)) {
					break;    //[这里本来应该都用continue的，不过查询出来时按照价格带排序,
							 //最重要的是每一个价格带一定会有至少一条数据(统计结果额外null)
							//,故用break也行,提高效率]
				}
				BigDecimal mPsdPrice = soldPrice.getPsdSoldPrice();//目前该商品的PSD销售额
				if (mPsdPrice == null) continue; 
				String mCode = soldPrice.getMerchandiseCode();//目前的商品
				if (mPsdPrice.compareTo(psdPrice) > -1 && !flagList.contains("P" + mRegionPrece + mCode)) {//不小于
					mainData.setNotLessPsdSoldPrice(mainData.getNotLessPsdSoldPrice().add(new BigDecimal(1)));
					flagList.add("P" + mRegionPrece+mCode);//添加到记录list中
				} else if (!flagList.contains("P" + mRegionPrece + mCode)) {//小于
					mainData.setLessPsdSoldPrice(mainData.getLessPsdSoldPrice().add(new BigDecimal(1)));
					flagList.add("P" + mRegionPrece+mCode);//添加到记录list中
				}
			}
			
			//毛利额
			BigDecimal psdGrossFit = mainData.getPsdGrossProfit();
			for (; k < grossFitList.size(); k++) {
				CategoryPrice grossFit =  grossFitList.get(k);
				String mRegionPrece = grossFit.getRegionPrice();//目前该商品的价格地区
				if (!mRegionPrece.equals(regionPrice)) {
					break;    //[这里本来应该都用continue的，不过查询出来时按照价格带排序,
							 //最重要的是每一个价格带一定会有至少一条数据(统计结果额外null)
							//,故用break也行,提高效率]
				}
				BigDecimal mGrossProfit = grossFit.getPsdGrossProfit();//目前该商品的PSD毛利额
				if (mGrossProfit == null) continue; 
				String mCode = grossFit.getMerchandiseCode();//目前的商品
				if (mGrossProfit.compareTo(psdGrossFit) > -1 && !flagList.contains("G" + mRegionPrece + mCode)) {//不小于
					mainData.setNotLessPsdGrossProfit(mainData.getNotLessPsdGrossProfit().add(new BigDecimal(1)));
					flagList.add("G" + mRegionPrece+mCode);//添加到记录list中
				} else if (!flagList.contains("G" + mRegionPrece + mCode)) {//小于
					mainData.setLessPsdGrossProfit(mainData.getLessPsdGrossProfit().add(new BigDecimal(1)));
					flagList.add("G" + mRegionPrece+mCode);//添加到记录list中
				}
			}
			
		}
		//FIXME 
		//	[如果价格带最大价格为1.6   保留整数就是2   然后步长是1 
		//	那么价格带就是0-1   1-2   2-3    因为价格带是大于等于前面 小于后面  不包括后面的 所以必须增加一个价格带(2-3)
		//	之后再后面统计的时候商品价格是按照1.6来划分属于哪个价格带的  于是就落在了1-2这个价格带  从而导致2-3为空
		//	]
		//此处去掉最后一条价格带商品数量为空的情况
		if (!list.isEmpty()) {
			list = this.removeLastDataWhenNoSku(list);
		}
		return list; 
	}

	/**
	 * 去掉最后一条价格带商品数量为空的情况
	 * 
	 * @param list
	 *            各价格带情况
	 * @return 去掉最后一条价格带商品数量为空后的数据
	 */
	private List<CategoryPrice> removeLastDataWhenNoSku(List<CategoryPrice> list) {
		BigDecimal skuUnits = list.get(list.size() - 1).getSkuUnits();
		if (skuUnits == null || skuUnits.compareTo(BigDecimal.ZERO) == 0) {
			list = list.subList(0, list.size() - 1);//但最后一个价格带商品数量为零时去掉此价格带
		}
		return list;
	}
	
	@Override
	public List<CategoryPrice> listDetailData(Map<String, Object> map,
			PageInfo pageInfo) {
		return this.categoryPriceDao.listDetailData(map, pageInfo);
	}
	
	@Override
	public String listCalculateTotal(Map<String, Object> map) {
		List<String> list = this.formatPriceRegion(this.categoryPriceDao.listCalculateTotal(map));
		return StringUtils.join(list.toArray(), "|");
	}

	@Override
	public String listPriceRegionDate(Map<String, Object> map) {
		return this.categoryPriceDao.listPriceRegionDate(map);
	}

	@Override
	public void exportAllCategoryToExcel(String tol1, List<CategoryPrice> data1,
			String tol2, List<CategoryPrice> data2, Map<String, Object> pubMap, ServletOutputStream out) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet1 = wb.createSheet("日期价格带数据1");
		// 1. 创建excel表头(第一行、第二行、第三行)
		this.fillAllHeaderCell(wb, 1, sheet1, tol1, pubMap);
		// 2.用数据填充单元格
		if (!data1.isEmpty()) this.fillAllDataCell(data1, wb, sheet1);

		// 2.用数据填充单元格
		if (data2 != null) {
			Sheet sheet2 = wb.createSheet("日期价格带数据2");
			this.fillAllHeaderCell(wb, 2, sheet2, tol2, pubMap);
			if (!data2.isEmpty()) this.fillAllDataCell(data2, wb, sheet2);
		}
		
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
	 * 生成excel前两行
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheetIndex
	 *            当前sheet下标
	 * @param sheet
	 *            sheet
	 * @param tol
	 *            统计结果
	 * @param pubMap
	 *            查询参数
	 */
	private void fillAllHeaderCell(Workbook wb, int sheetIndex, Sheet sheet, String tol, Map<String, Object> pubMap) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("统计数据");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 26; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:Z1");

		//统计行开始
		row = sheet.createRow(1);
		cell = row.createCell(0);
		String [] t = new String[] {"", "", "", ""};
		if (tol.length() > 4 ) {
			t = tol.split("\\|");
		}
		CellStyle secRowStyle = wb.createCellStyle();
		secRowStyle.setWrapText(true);//换行
		ExcelUtils.setFrame(secRowStyle, true);
		
		cell = row.createCell(0);
		cell.setCellStyle(secRowStyle);//设置改行样式
		String searchDate = (String) pubMap.get("searchDate" + sheetIndex);
		String netWeightTypeCon = "";
		int rowspan= 7;
		if (!"CM".equals(pubMap.get("module"))) {
			netWeightTypeCon= "\n" + pubMap.get("netWeightTypeCon");
			rowspan = 7;
		}
		cell.setCellValue("占SKU数量最多价格带为：" + t[0]
				+"        销量占比最大价格带为：" + t[1]
				+"        销售额占比最大价格带为：" + t[2]
				+"        毛利额占比最大价格带为：" + t[3]
				+"\n时间范围" + sheetIndex + "：" + searchDate
				+"\n" + pubMap.get("merTitle")
				+"\n地区：" + pubMap.get("regionName")
				+ netWeightTypeCon
				+"\n" + pubMap.get("directCon")
				+"\n" + pubMap.get("priceSetCon")
		);
		for (int i = 1; i < 26; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(secRowStyle);//设置改行样式
		}
		row.setHeightInPoints((rowspan*sheet.getDefaultRowHeightInPoints()));//换行后的自适应单元格高度
		ExcelUtils.addMergedRegion(sheet, "A2:Z2");
		
		// 第二行
		row = sheet.createRow(2);
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区总门店数");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区SKU总数");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区价格带（元）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SKU数量");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("SKU数量占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		String netWeightType = (String) pubMap.get("netWeightType");
		if ("1".equals(netWeightType)) {//
			cell.setCellValue("销量（KG）");
		} else {
			cell.setCellValue("销量（件）");
		}

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("活跃度");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销量（KG）");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售金额（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额（元）");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("大于等于PSD销量的SKU数");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小于PSD销量的SKU数");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("大于等于PSD销售额的SKU数");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小于PSD销售额的SKU数");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("大于等于PSD毛利额的SKU数");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小于PSD毛利额的SKU数");
	}

	/**
	 * 准备写入数据
	 * 
	 * @param data
	 *            数据
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 */
	private void fillAllDataCell(List<CategoryPrice> data, Workbook wb, Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < data.size(); i++) {
			this.writeAllOneRowDate(sheet, i + 1, data.get(i), strStyle, amtStyle);
		}
	}

	/**
	 * sheet写入一行数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param rowIndex
	 *            sheet下标
	 * @param catPrice
	 *            实体
	 * @param strStyle
	 *            字符串样式
	 * @param amtStyle
	 *            数字样式
	 */
	private void writeAllOneRowDate(Sheet sheet, int rowIndex,
			CategoryPrice catPrice, CellStyle strStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getRegionName());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal regionStoreSum = catPrice.getRegionStoreSum();//region表中的(直营或[和]加盟)门店数
		if (regionStoreSum != null) cell.setCellValue(DecimalFormatUtils.removeRemain(regionStoreSum, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal regionSKUSum = catPrice.getRegionSKUSum();//地区商品个数
		if (regionSKUSum != null) cell.setCellValue(DecimalFormatUtils.removeRemain(regionSKUSum, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(catPrice.getRegionPriceStr());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal skuUnits = catPrice.getSkuUnits();//价格带商品个数
		if (skuUnits != null) cell.setCellValue(DecimalFormatUtils.removeRemain(skuUnits, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getSkuPercent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldUnits = catPrice.getSoldUnits();//月直营(加盟)表中的SELL_QUANTITY
		if (soldUnits != null) cell.setCellValue(DecimalFormatUtils.removeRemain(soldUnits, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldPrice = catPrice.getSoldPrice();//月直营(加盟)表中的SELL_TOTAL_PRICE
		if (soldPrice != null) cell.setCellValue(DecimalFormatUtils.removeRemain(soldPrice, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal grossProfit = catPrice.getGrossProfit();//月直营(加盟)表中的SELL_PROFIT
		if (grossProfit != null) cell.setCellValue(DecimalFormatUtils.removeRemain(grossProfit, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getSoldPercent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getSoldPricePercent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getGrossProfitPenrcent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal powerShopDay = catPrice.getPowerShopDay();
		if (powerShopDay != null)//merchandise_jh_permission权限店天表中PERMISSION_STORE_QUANTITY
			cell.setCellValue(DecimalFormatUtils.removeRemain(powerShopDay, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldShopDay = catPrice.getSoldShopDay();
		if(soldShopDay != null) {//月直营(加盟)表中SELL_STORE_QUANTITY
			cell.setCellValue(DecimalFormatUtils.removeRemain(soldShopDay, "#,##0"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal vitality = catPrice.getVitality();
		if(vitality != null) {
			if (vitality.compareTo(new BigDecimal(1)) != -1) {
				cell.setCellValue("100.00%");
			} else {
				cell.setCellValue(DecimalFormatUtils.formatBigPercent(vitality));
			}
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getPowerShopDayPercent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(DecimalFormatUtils.formatBigPercent(catPrice
				.getSoldShopPercent()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdUnits = catPrice.getPsdUnits();
		if(psdUnits != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(psdUnits, "#,##0.00"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdSoldPrice = catPrice.getPsdSoldPrice();
		if (psdSoldPrice != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(psdSoldPrice, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdGrossProfit = catPrice.getPsdGrossProfit();
		if(psdGrossProfit != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(psdGrossProfit, "#,##0.00"));
		
		//----------------------以下六个字段是java代码中计算得出的-------------------------------
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal notLessPsdUnitsSku = catPrice.getNotLessPsdUnitsSku();
		if (notLessPsdUnitsSku != null)
			cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdUnitsSku, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal lessPsdUnitsSku = catPrice.getLessPsdUnitsSku();
		if(lessPsdUnitsSku != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(lessPsdUnitsSku, "#,##0"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal notLessPsdSoldPrice = catPrice.getNotLessPsdSoldPrice();
		if(notLessPsdSoldPrice != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdSoldPrice, "#,##0"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal lessPsdSoldPrice = catPrice.getLessPsdSoldPrice();
		if(lessPsdSoldPrice != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(lessPsdSoldPrice, "#,##0"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal notLessPsdGrossProfit = catPrice.getNotLessPsdGrossProfit();
		if(notLessPsdGrossProfit != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdGrossProfit, "#,##0"));
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal lessPsdGrossProfit = catPrice.getLessPsdGrossProfit();
		if(lessPsdGrossProfit != null) {
			cell.setCellValue(DecimalFormatUtils.removeRemain(lessPsdGrossProfit, "#,##0"));
		}
	}
	
	//---------------------------------------------
	
	@Override
	public void exportDetCategoryToExcel(List<CategoryPrice> data1,
			List<CategoryPrice> data2, Map<String, Object> pubMap,
			ServletOutputStream out) {
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet1 = wb.createSheet("日期价格带数据1");
		// 1. 创建excel表头(第一行、第二行、第三行)
		this.fillDetHeaderCell(wb, 1, sheet1, pubMap);
		// 2.用数据填充单元格
		if (!data1.isEmpty()) this.fillDetDataCell(data1, wb, sheet1);

		// 2.用数据填充单元格
		if (data2 != null) {
			Sheet sheet2 = wb.createSheet("日期价格带数据2");
			this.fillDetHeaderCell(wb, 2, sheet2, pubMap);
			if (!data2.isEmpty()) this.fillDetDataCell(data2, wb, sheet2);
		}

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
	 * 创建表头
	 * 
	 * @param wb
	 *            工作簿
	 * @param sheetIndex
	 *            当前是第几个sheet
	 * @param sheet
	 *            当前sheet
	 * @param pubMap
	 *            上方参数
	 */
	private void fillDetHeaderCell(Workbook wb, int sheetIndex, Sheet sheet,
			Map<String, Object> pubMap) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb,
				ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("统计数据");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 40; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:AN1");

		// 统计行开始
		row = sheet.createRow(1);
		cell = row.createCell(0);
		CellStyle secRowStyle = wb.createCellStyle();
		secRowStyle.setWrapText(true);// 换行
		ExcelUtils.setFrame(secRowStyle, true);

		cell = row.createCell(0);
		cell.setCellStyle(secRowStyle);// 设置改行样式
		String searchDate = (String) pubMap.get("searchDate" + sheetIndex);
		String module = (String) pubMap.get("module");
		String netWeightTypeCon = "\n" + pubMap.get("netWeightTypeCon");
		int colNum = 6;
		if ("CM".equalsIgnoreCase(module)) {
			netWeightTypeCon = "";
			colNum = 5;
		}
		cell.setCellValue("时间范围" + sheetIndex + "：" + searchDate + "\n"
				+ pubMap.get("merTitle")
				+ "\n地区：" + pubMap.get("regionName") 
				+ netWeightTypeCon + "\n"
				+ pubMap.get("directCon") + "\n"
				+ pubMap.get("priceSetCon"));
		for (int i = 1; i < 40; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(secRowStyle);// 设置改行样式
		}
		row.setHeightInPoints((colNum * sheet.getDefaultRowHeightInPoints()));// 换行后的自适应单元格高度
		ExcelUtils.addMergedRegion(sheet, "A2:AN2");
		
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式
		// 第二行
		row = sheet.createRow(2);
		
		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (70 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("门店数");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号 ");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(strStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("所属价格带");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限数");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限开通率");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("A");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("B");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("C");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("D");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际销售门店数");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("实际占权限门店比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量占比 ");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售金额占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("活跃度");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销量");
	
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售金额");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额");
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
		
		if ("ALL".equalsIgnoreCase(module)) {
			cell.setCellValue("高于所有商品平均PSD销量");
		} else if ("SM".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查小分类的所有商品的平均PSD销量");
		} else if ("DE".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查明细类的所有商品的平均PSD销量");
		} else {
			cell.setCellValue("高于用户所选商品的平均PSD销量");
		}
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
		if ("ALL".equalsIgnoreCase(module)) {
			cell.setCellValue("高于所有商品平均PSD销售额");
		} else if ("SM".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查小分类的所有商品的平均PSD销售额");
		} else if ("DE".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查明细类的所有商品的平均PSD销售额");
		} else {
			cell.setCellValue("高于用户所选商品的平均PSD销售额");
		}
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
		if ("ALL".equalsIgnoreCase(module)) {
			cell.setCellValue("高于所有商品平均PSD毛利额");
		} else if ("SM".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查小分类的所有商品的平均PSD毛利额");
		} else if ("DE".equalsIgnoreCase(module)) {
			cell.setCellValue("高于用户所查明细类的所有商品的平均PSD毛利额");
		} else {
			cell.setCellValue("高于用户所选商品的平均PSD毛利额");
		}
		
		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("格斗占比");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("volume&space");

		cell = row.createCell(i);
		cell.setCellStyle(amtStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("value&space");
		
	}

	/**
	 * 准备写入数据
	 * 
	 * @param data
	 *            数据
	 * @param wb
	 *            工作簿
	 * @param sheet
	 *            当前sheet
	 */
	private void fillDetDataCell(List<CategoryPrice> data, Workbook wb,
			Sheet sheet) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < data.size(); i++) {
			this.writeDetOneRowDate(sheet, i + 1, data.get(i), strStyle, amtStyle);
		}
	}

	/**
	 * sheet写入一行数据
	 * 
	 * @param sheet
	 *            当前sheet
	 * @param rowIndex
	 *            sheet下标
	 * @param catPrice
	 *            实体
	 * @param strStyle
	 *            字符串样式
	 * @param amtStyle
	 *            数字样式
	 */
	private void writeDetOneRowDate(Sheet sheet, int rowIndex,
			CategoryPrice catPrice, CellStyle strStyle,
			CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getRegionName());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal regionStoreSum = catPrice.getRegionStoreSum();//region区域信息中直营或[和]加盟门店数
		if (regionStoreSum != null) cell.setCellValue(DecimalFormatUtils.removeRemain(regionStoreSum, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getMerchandiseName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getSupplierName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getDxRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getDlRoleName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getCentreTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getSmallTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(catPrice.getDetailTypeName());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(catPrice.getRegionPrice());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal powerCount = catPrice.getPowerCount();
		if (powerCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(powerCount, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal powerPercent = catPrice.getPowerPercent();
		if(powerPercent != null) cell.setCellValue(DecimalFormatUtils.removeRemain(powerPercent, "0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal aCount = catPrice.getaCount();
		if(aCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(aCount, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal bCount = catPrice.getbCount();
		if(bCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(bCount, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal cCount = catPrice.getcCount();
		if(cCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(cCount, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal dCount = catPrice.getdCount();
		if(dCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(dCount, "#,##0"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal actSoldStorCount = catPrice.getActSoldStorCount();
		if (actSoldStorCount != null) cell.setCellValue(DecimalFormatUtils.removeRemain(actSoldStorCount, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);//实际占权限门店比
		BigDecimal actSoldStorPercent = catPrice.getActSoldStorPercent();
		if(actSoldStorPercent != null) {
			if (actSoldStorPercent.compareTo(new BigDecimal(1)) > -1) {
				cell.setCellValue("100.00%");
			} else {
				cell.setCellValue(DecimalFormatUtils.formatBigPercent(actSoldStorPercent));
			}
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldUnits = catPrice.getSoldUnits();
		if (soldUnits != null) cell.setCellValue(DecimalFormatUtils.removeRemain(soldUnits, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldPrice = catPrice.getSoldPrice();
		if(soldPrice != null) cell.setCellValue(DecimalFormatUtils.removeRemain(soldPrice, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal grossProfit = catPrice.getGrossProfit();
		if(grossProfit != null) cell.setCellValue(DecimalFormatUtils.removeRemain(grossProfit, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldPercent = catPrice.getSoldPercent();
		if(soldPercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(soldPercent));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldPricePercent = catPrice.getSoldPricePercent();
		if(soldPricePercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(soldPricePercent));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal grossProfitPenrcent = catPrice.getGrossProfitPenrcent();
		if(grossProfitPenrcent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(grossProfitPenrcent));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal powerShopDay = catPrice.getPowerShopDay();
		if(powerShopDay != null) cell.setCellValue(DecimalFormatUtils.removeRemain(powerShopDay, "#,##0"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldShopDay = catPrice.getSoldShopDay();
		if(soldShopDay != null) cell.setCellValue(DecimalFormatUtils.removeRemain(soldShopDay, "#,##0"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal vitality = catPrice.getVitality();
		if(vitality != null) {
			if (vitality.compareTo(new BigDecimal(1)) != -1) {
				cell.setCellValue("100.00%");
			} else {
				cell.setCellValue(DecimalFormatUtils.formatBigPercent(vitality));
			}
		}
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal powerShopDayPercent = catPrice.getPowerShopDayPercent();
		if(powerShopDayPercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(powerShopDayPercent));
	
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal soldShopPercent = catPrice.getSoldShopPercent();
		if(soldShopPercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(soldShopPercent));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdUnits = catPrice.getPsdUnits();
		if(psdUnits != null) cell.setCellValue(DecimalFormatUtils.removeRemain(psdUnits, "#,##0.00"));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdSoldPrice = catPrice.getPsdSoldPrice();
		if(psdSoldPrice != null) cell.setCellValue(DecimalFormatUtils.removeRemain(psdSoldPrice, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal psdGrossProfit = catPrice.getPsdGrossProfit();
		if(psdGrossProfit != null) cell.setCellValue(DecimalFormatUtils.removeRemain(psdGrossProfit, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);//高于所有商品平均PSD销量
		BigDecimal notLessPsdUnitsSku = catPrice.getNotLessPsdUnitsSku();
		if(notLessPsdUnitsSku != null) cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdUnitsSku, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal notLessPsdSoldPrice = catPrice.getNotLessPsdSoldPrice();
		if(notLessPsdSoldPrice != null) cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdSoldPrice, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal notLessPsdGrossProfit = catPrice.getNotLessPsdGrossProfit();
		if(notLessPsdGrossProfit != null) cell.setCellValue(DecimalFormatUtils.removeRemain(notLessPsdGrossProfit, "#,##0.00"));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal bucketPercent = catPrice.getBucketPercent();
		if(bucketPercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(bucketPercent));
	
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal volumePercent = catPrice.getVolumePercent();
		if(volumePercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(volumePercent));
		
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		BigDecimal valPercent = catPrice.getValPercent();
		if(valPercent != null) cell.setCellValue(DecimalFormatUtils.formatBigPercent(valPercent));
	}

	@Override
	public String completeSaveAllCategory(String fileName, String dirName, String reportType, Map<String, Object> paraMap) {
		Object data1 = paraMap.get("data1");
		Object data2 = paraMap.get("data2");
		if (data1 != null) {
			paraMap.put("data1Json", JSONArray.fromObject(data1).toString());
		} 
		if (data2 != null) {
			paraMap.put("data2Json", JSONArray.fromObject(data2).toString());
		}
		return this.saveReport(fileName, reportType, paraMap,
				"allCategoryAllDataReport.ftl", dirName);
	}
	
	@Override
	public String completeSaveDetCategory(String fileName, String dirName, String reportType,
			Map<String, Object> paraMap) {
		return this.saveReport(fileName, reportType, paraMap,
				"allCategoryDetailDataReport.ftl", dirName);
	}

	/**
	 * 保存报表
	 * 
	 * @param fileName
	 *            保存后的文件名
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
			tarPath = FreeMarkerUtil.generateHtml("allCategory/" + ftlName,
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
				LoggerUtil.logger.error("CategoryPriceServiceImpl.saveReport删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}
	
	/**
	 * 将List<String>[以'-'连接]中字符串数字格式化两位
	 * 
	 * @param list
	 *            需处理List
	 * @return 处理后的List
	 */
	private List<String> formatPriceRegion(List<String> list) {
		for (int i = 0; i < list.size(); i++) {
			String string = list.get(i);
			if (StringUtils.isBlank(string)) continue;
			String[] arr = string.split("-"); 
			if (arr.length > 1) {
				arr[0] = DecimalFormatUtils.roundNumber(arr[0], 2);
				arr[1] = DecimalFormatUtils.roundNumber(arr[1], 2);
				list.set(i, arr[0] + "元-" + arr[1] + "元");
			}
		}
		return list;
	}
	
}