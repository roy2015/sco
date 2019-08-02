package com.powere2e.sco.service.impl.categoryanalysis.merchandisepricetrend;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.categoryanalysis.merchandisepricetrend.MerchandisePriceTrendDao;
import com.powere2e.sco.interfaces.service.categoryanalysis.merchandisepricetrend.MerchandisePriceTrendService;
import com.powere2e.sco.model.categoryanalysis.merchandisepricetrend.MerchandisePriceTrend;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 商品价格趋势类的实现
 * 
 * @author len.zhao
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePriceTrendServiceImpl extends ServiceImpl implements MerchandisePriceTrendService {

	private static final long serialVersionUID = -8706563568612476319L;
	private MerchandisePriceTrendDao merchandisePriceTrendDao;

	public static MerchandisePriceTrendService getInstance() {
		return (MerchandisePriceTrendService) ConfigFactory.getInstance().getBean("merchandisePriceTrendService");
	}

	// 获取MerchandisePriceTrend Dao实例
	public MerchandisePriceTrendDao getMerchandisePriceTrendDao() {
		return merchandisePriceTrendDao;
	}

	// 设置MerchandisePriceTrend Dao实例
	public void setMerchandisePriceTrendDao(MerchandisePriceTrendDao merchandisePriceTrendDao) {
		this.merchandisePriceTrendDao = merchandisePriceTrendDao;
	}

	@Override
	public List<Option> listRegion(String regionCode) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionCode", regionCode);
		return this.getMerchandisePriceTrendDao().listRegion(map);
	}

	@Override
	public List<MerchandisePriceTrend> listMerchandisePriceTrend(Map<String, Object> map, PageInfo pageInfo) {
		List<MerchandisePriceTrend> list = this.merchandisePriceTrendDao.listMerchandisePriceTrend(map, pageInfo);
		if (list.size() == 0) {
			return list;
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("show", map.get("itemValue"));
		paraMap.put("merchandiseCode", map.get("merchandiseCode"));
		paraMap.put("supplierCode", map.get("supplierCode"));
		paraMap.put("regionCode", map.get("regionCode"));
		paraMap.put("qlStartDate", map.get("qlStartDate"));
		paraMap.put("qlEndDate", map.get("qlEndDate"));
		MerchandisePriceTrend mpt;
		MerchandisePriceTrend upMpt;
		MerchandisePriceTrend sellMpt;

		BigDecimal sellPriceGains;
		BigDecimal psdSalesGains;
		BigDecimal psdResultsGains;
		BigDecimal psdGrossProfitGains;
		int listSize = list.size();

		BigDecimal minusOne = new BigDecimal(-1.00);
		BigDecimal hundred = new BigDecimal(100);
		//SimpleDateFormat sFormat = new SimpleDateFormat("yyyy-MM-dd");
		String bool = "";// 是否属于同一商品
		if (listSize > 0) {
			for (int i = 0; i < list.size(); i++) {
				mpt = list.get(i);
				paraMap.put("qlStartDate", mpt.getPriceChangeDate());
				paraMap.put("merchandiseCode", mpt.getMerchandiseCode());
				paraMap.put("supplierCode", mpt.getSupplierCode());
				paraMap.put("regionCode", mpt.getRegionCode());
				paraMap.put("qlEndDate", map.get("qlEndDate"));
				if (i == listSize - 1) {
					bool += "false";
					List<MerchandisePriceTrend> priceDate = this.merchandisePriceTrendDao.getPriceOrDate(paraMap);
					for (int p = 0; p < priceDate.size(); p++) {
						if (!mpt.getSellPrice().equals(priceDate.get(p).getSellPrice())) {
							paraMap.put("qlEndDate", priceDate.get(p).getPriceChangeDate());
							break;
						}
						if (p == priceDate.size() - 1) {
							paraMap.put("qlEndDate", map.get("qlEndDate"));
						}
					}
				} else if (!mpt.getMerchandiseCode().equals(list.get(i + 1).getMerchandiseCode()) || !mpt.getSupplierCode().equals(list.get(i + 1).getSupplierCode())) {
					bool += "true,";
					List<MerchandisePriceTrend> priceDate = this.merchandisePriceTrendDao.getPriceOrDate(paraMap);
					for (int p = 0; p < priceDate.size(); p++) {
						if (!mpt.getSellPrice().equals(priceDate.get(p).getSellPrice())) {
							paraMap.put("qlEndDate", priceDate.get(p).getPriceChangeDate());
							break;
						}
						if (p == priceDate.size() - 1) {
							paraMap.put("qlEndDate", map.get("qlEndDate"));
						}
					}
				} else {
					bool += "false,";
					List<MerchandisePriceTrend> priceDate = this.merchandisePriceTrendDao.getPriceOrDate(paraMap);
					for (int p = 0; p < priceDate.size(); p++) {
						if (!mpt.getSellPrice().equals(priceDate.get(p).getSellPrice())) {
							paraMap.put("qlEndDate", priceDate.get(p).getPriceChangeDate());
							break;
						}
						if (p == priceDate.size() - 1) {
							paraMap.put("qlEndDate", map.get("qlEndDate"));
						}
					}
				}
				List<MerchandisePriceTrend> merchandisePriceTrendList = this.merchandisePriceTrendDao.getMerchandiseSellData(paraMap);
				if (merchandisePriceTrendList.size() == 0) {
					sellMpt = null;
				} else {
					sellMpt = merchandisePriceTrendList.get(0);
				}

				if (sellMpt == null) {
					mpt.setSellQuantity(BigDecimal.ZERO);
					mpt.setSellTotalPrice(BigDecimal.ZERO);
					mpt.setSellProfit(BigDecimal.ZERO);
					mpt.setSellStoreQuantity(0);
					mpt.setPermissionStoreQuantity(0);
					mpt.setActiveDegrees("0.00%");
					mpt.setPsdSellQuantity(BigDecimal.ZERO);
					mpt.setPsdSellTotalPrice(BigDecimal.ZERO);
					mpt.setPsdSellProfit(BigDecimal.ZERO);
				} else {
					if (sellMpt.getSellQuantity() == null) {
						mpt.setSellQuantity(BigDecimal.ZERO);
					} else {
						mpt.setSellQuantity(sellMpt.getSellQuantity());
					}

					if (sellMpt.getSellTotalPrice() == null) {
						mpt.setSellTotalPrice(BigDecimal.ZERO);
					} else {
						mpt.setSellTotalPrice(sellMpt.getSellTotalPrice());
					}

					if (sellMpt.getSellProfit() == null) {
						mpt.setSellProfit(BigDecimal.ZERO);
					} else {
						mpt.setSellProfit(sellMpt.getSellProfit());
					}

					mpt.setSellStoreQuantity(sellMpt.getSellStoreQuantity());// 销售店天
					mpt.setPermissionStoreQuantity(sellMpt.getPermissionStoreQuantity());// 权限店天

					// 计算活跃度
					if (mpt.getPermissionStoreQuantity() == 0 || mpt.getSellStoreQuantity() == 0) {
						mpt.setActiveDegrees("0.00%");
					} else {
						BigDecimal active = new BigDecimal(mpt.getSellStoreQuantity()).multiply(hundred).divide(new BigDecimal(mpt.getPermissionStoreQuantity()), 2, BigDecimal.ROUND_HALF_UP);
						if (active.compareTo(new BigDecimal(100)) == 1) {
							mpt.setActiveDegrees("100.00%");
						} else {
							mpt.setActiveDegrees(active + "%");
						}
					}

					if (sellMpt.getSellStoreQuantity() == 0) {
						mpt.setPsdSellQuantity(BigDecimal.ZERO);
						mpt.setPsdSellTotalPrice(BigDecimal.ZERO);
						mpt.setPsdSellProfit(BigDecimal.ZERO);
					} else {
						mpt.setPsdSellQuantity(sellMpt.getPsdSellQuantity());
						mpt.setPsdSellTotalPrice(sellMpt.getPsdSellTotalPrice());
						mpt.setPsdSellProfit(sellMpt.getPsdSellProfit());
					}
				}
				list.set(i, mpt);
			}
		}

		if (listSize > 0) {
			String[] bools = bool.split(",");
			for (int i = 0; i < listSize; i++) {

				mpt = list.get(i);

				if (i == 0) {
					mpt.setSellPriceGains("0.00");
					mpt.setPsdSalesGains("0.00");
					mpt.setPsdResultsGains("0.00");
					mpt.setPsdGrossProfitGains("0.00");
				} else if (bools[i - 1].equals("true")) {
					mpt.setSellPriceGains("0.00");
					mpt.setPsdSalesGains("0.00");
					mpt.setPsdResultsGains("0.00");
					mpt.setPsdGrossProfitGains("0.00");
				} else {
					upMpt = list.get(i - 1);

					if (mpt.getSellPrice().doubleValue() == 0 && upMpt.getSellPrice().doubleValue() != 0) {
						sellPriceGains = minusOne.multiply(hundred);
					} else if (upMpt.getSellPrice().doubleValue() == 0 && mpt.getSellPrice().doubleValue() != 0) {
						sellPriceGains = hundred;
					} else if (!mpt.getSellPrice().equals(upMpt.getSellPrice())) {
						sellPriceGains = (mpt.getSellPrice().subtract(upMpt.getSellPrice()).multiply(hundred).divide(upMpt.getSellPrice(), 2, BigDecimal.ROUND_HALF_UP));
					} else {
						sellPriceGains = BigDecimal.ZERO;
					}

					if (mpt.getPsdSellQuantity().doubleValue() == 0 && upMpt.getPsdSellQuantity().doubleValue() != 0) {
						psdSalesGains = minusOne.multiply(hundred);
					} else if (upMpt.getPsdSellQuantity().doubleValue() == 0 && mpt.getPsdSellQuantity().doubleValue() != 0) {
						psdSalesGains = hundred;
					} else if (!mpt.getPsdSellQuantity().equals(upMpt.getPsdSellQuantity())) {
						psdSalesGains = (mpt.getPsdSellQuantity().subtract(upMpt.getPsdSellQuantity()).multiply(hundred).divide(upMpt.getPsdSellQuantity(), 2, BigDecimal.ROUND_HALF_UP));
					} else {
						psdSalesGains = BigDecimal.ZERO;
					}

					if (mpt.getPsdSellTotalPrice().doubleValue() == 0 && upMpt.getPsdSellTotalPrice().doubleValue() != 0) {
						psdResultsGains = minusOne.multiply(hundred);
					} else if (upMpt.getPsdSellTotalPrice().doubleValue() == 0 && mpt.getPsdSellTotalPrice().doubleValue() != 0) {
						psdResultsGains = hundred;
					} else if (!mpt.getPsdSellTotalPrice().equals(upMpt.getPsdSellTotalPrice())) {
						psdResultsGains = (mpt.getPsdSellTotalPrice().subtract(upMpt.getPsdSellTotalPrice()).multiply(hundred).divide(upMpt.getPsdSellTotalPrice(), 2, BigDecimal.ROUND_HALF_UP));
					} else {
						psdResultsGains = BigDecimal.ZERO;
					}

					if (mpt.getPsdSellProfit().doubleValue() == 0 && upMpt.getPsdSellProfit().doubleValue() != 0) {
						psdGrossProfitGains = minusOne.multiply(hundred);
					} else if (upMpt.getPsdSellProfit().doubleValue() == 0 && mpt.getPsdSellProfit().doubleValue() != 0) {
						psdGrossProfitGains = hundred;
					} else if (!mpt.getPsdSellProfit().equals(upMpt.getPsdSellProfit())) {
						psdGrossProfitGains = (mpt.getPsdSellProfit().subtract(upMpt.getPsdSellProfit()).multiply(hundred).divide(upMpt.getPsdSellProfit(), 2, BigDecimal.ROUND_HALF_UP));
					} else {
						psdGrossProfitGains = BigDecimal.ZERO;
					}

					mpt.setSellPriceGains(new DecimalFormat("#,##0.00").format(sellPriceGains));
					mpt.setPsdSalesGains(new DecimalFormat("#,##0.00").format(psdSalesGains));
					mpt.setPsdResultsGains(new DecimalFormat("#,##0.00").format(psdResultsGains));
					mpt.setPsdGrossProfitGains(new DecimalFormat("#,##0.00").format(psdGrossProfitGains));
				}

				list.set(i, mpt);
			}
		}

		return list;
	}

	@Override
	public List<MerchandisePriceTrend> listMerchandiseData(Map<String, Object> map, PageInfo pageInfo) {
		return this.merchandisePriceTrendDao.listMerchandiseData(map, pageInfo);
	}

	@Override
	public void exportPriceTrendExcel(Map<String, Object> map, ServletOutputStream out) {
		List<MerchandisePriceTrend> list = this.listMerchandisePriceTrend(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("商品价格趋势");

		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCell(wb, sheet, map);

		// 2.用数据填充单元格
		if (!list.isEmpty())
			this.fillDataCell(list, wb, sheet, map);
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

	private void fillHeaderCell(Workbook wb, Sheet sheet, Map<String, Object> map) {
		// 第一行
		Row row = sheet.createRow(0);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("商品价格趋势");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 24; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A1:X1");

		// 第二行
		row = sheet.createRow(1);
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
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
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品规格");
		
		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("价格维护日期");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("售价（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (80 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销量（KG/件）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("活跃度");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量 （KG/件）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD业绩（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利（元）");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("售价涨幅");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量涨幅");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD业绩涨幅");

		cell = row.createCell(i);
		cell.setCellStyle(sencondRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利涨幅");
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
	private void fillDataCell(List<MerchandisePriceTrend> list, Workbook wb, Sheet sheet, Map<String, Object> radioValuesMap) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDate(sheet, i, list.get(i), radioValuesMap, strStyle, dateStyle, amtStyle);
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
	private void writeOneRowDate(Sheet sheet, Integer rowIndex, MerchandisePriceTrend pt, Map<String, Object> radioValuesMap, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle) {
		Row row = sheet.createRow(rowIndex + 2);

		int j = 0;
		// 商品品编号
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getMerchandiseCode());

		// 商品名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getMerchandiseName());

		// 中分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getCentreName());

		// 小分类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getSmallName());

		// 明细类
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getDetailName());

		// 供应商编号
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getSupplierCode());

		// 供应商名称
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getSupplierName());

		// 规格
		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(pt.getSpecification());

		// 价格维护日期
		cell = row.createCell(j++);
		cell.setCellStyle(dateStyle);
		cell.setCellValue(pt.getRegionName());
				
				
		// 价格维护日期
		cell = row.createCell(j++);
		cell.setCellStyle(dateStyle);
		if (pt.getPriceChangeDate() != null)
			cell.setCellValue(pt.getPriceChangeDate());

		// 售价
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (pt.getSellPrice() != null)
			cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getSellPrice()));

		// 销售量
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getSellQuantity()));

		// 销售额
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (pt.getSellTotalPrice() != null)
			cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getSellTotalPrice()));

		// 毛利额
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (pt.getSellProfit() != null)
			cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getSellProfit()));

		// 销售店天
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(pt.getSellStoreQuantity()));

		// 权限店天
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(pt.getPermissionStoreQuantity()));

		// 活跃度
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(pt.getActiveDegrees());

		// PSD销售量
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getPsdSellQuantity()));

		// PSD业绩
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (pt.getPsdSellTotalPrice() != null)
			cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getPsdSellTotalPrice()));

		// PSD毛利
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (pt.getPsdSellProfit() != null)
			cell.setCellValue(new DecimalFormat("#,##0.00").format(pt.getPsdSellProfit()));

		// 售价涨幅
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(pt.getSellPriceGains() + "%");

		// PSD销售量涨幅
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(pt.getPsdSalesGains() + "%");

		// PSD业绩涨幅
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(pt.getPsdResultsGains() + "%");

		// PSD毛利涨幅
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(pt.getPsdGrossProfitGains() + "%");
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String saveSearchDataForm(String fileName, Map<String, Object> paraMap) {
		List<MerchandisePriceTrend> list = this.listMerchandisePriceTrend(paraMap, null);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dataList", list);
		String[] supplierCode = paraMap.get("supplierCode").toString().split(",");
		String[] merchandiseCode = paraMap.get("merchandiseCode").toString().split(",");
		List<List> listTB = new ArrayList<List>();
		List<Merchandise> merchandiseList = new ArrayList<Merchandise>();
		for (int i = 0; i < supplierCode.length; i++) {
			Merchandise merchandise = new Merchandise();
			merchandise.setSupplierCode(supplierCode[i]);
			merchandise.setMerchandiseCode(merchandiseCode[i]);
			merchandiseList.add(merchandise);
			paraMap.put("merchandise", merchandiseList);
			List<MerchandisePriceTrend> listTb = this.listMerchandisePriceTrend(paraMap, null);
			if (listTb.size() > 0) {
				listTB.add(listTb);
			}
			merchandiseList.clear();
		}
		map.put("line", JSONArray.fromObject(listTB).toString());
		String tarPath;
		try {
			tarPath = FreeMarkerUtil.generateHtml("merchandisepricetrend/merchandisepricetrendReport.ftl", "merchandisePriceTrend".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.MPT.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandisePriceTrendServiceImpl.saveSearchDataForm删除文件["+ file.getPath() +"]");
				return "记录报表文件信息时出错";
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}
}
