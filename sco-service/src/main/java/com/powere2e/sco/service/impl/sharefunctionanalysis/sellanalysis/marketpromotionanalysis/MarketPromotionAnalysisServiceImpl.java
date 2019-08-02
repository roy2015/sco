package com.powere2e.sco.service.impl.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysisDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysisService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 销售明细业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MarketPromotionAnalysisServiceImpl extends ServiceImpl implements MarketPromotionAnalysisService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private MarketPromotionAnalysisDao MarketPromotionAnalysisDao;

	public static MarketPromotionAnalysisService getInstance() {
		return (MarketPromotionAnalysisService) ConfigFactory.getInstance().getBean("MarketPromotionAnalysisService");
	}

	// 获得销售明细DAO实例
	public MarketPromotionAnalysisDao getMarketPromotionAnalysisDao() {
		return MarketPromotionAnalysisDao;
	}

	// 设置销售明细DAO实例
	public void setMarketPromotionAnalysisDao(MarketPromotionAnalysisDao MarketPromotionAnalysisDao) {
		this.MarketPromotionAnalysisDao = MarketPromotionAnalysisDao;
	}

	// 查询
	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		String minDates = map.get("minDates").toString();
		String minDate = map.get("minDate").toString();
		String regionName = "全国";
		if (!map.get("sellRegion").equals("all")) {
			regionName = this.getMarketPromotionAnalysisDao().listRegion(map).get(0).getText();
		}
		int n = 0;
		map.put("minDates", "");
		List<MarketPromotionAnalysis> list = this.getMarketPromotionAnalysisDao().listMarketPromotionAnalysis(map, pageInfo);
		if (list.size() == 0 || list.get(0).getSellRegion() == "") {
			MarketPromotionAnalysis p = new MarketPromotionAnalysis();
			p.setSku(new BigDecimal(0));
			p.setSellQuantity(new BigDecimal(0));
			p.setSellTotalPrice(new BigDecimal(0));
			p.setSellProfit(new BigDecimal(0));
			p.setPsdSellQuantity(new BigDecimal(0));
			p.setPsdSellTotalPrice(new BigDecimal(0));
			p.setPsdSellProfit(new BigDecimal(0));
			p.setPermissionStoreQuantity(new BigDecimal(0));
			p.setSellStoreQuantity(new BigDecimal(0));
			p.setSellQuantityProportionM(new BigDecimal(0));
			p.setSellTotalPriceProportionM(new BigDecimal(0));
			p.setSellProfitProportionM(new BigDecimal(0));
			p.setSellQuantityS(new BigDecimal(0));
			p.setSellTotalPriceS(new BigDecimal(0));
			p.setSellProfitS(new BigDecimal(0));
			p.setPsdSellQuantityS(new BigDecimal(0));
			p.setPsdSellTotalPriceS(new BigDecimal(0));
			p.setPsdSellProfitS(new BigDecimal(0));
			list.add(p);
			n++;
		}
		list.get(0).setTime(map.get("minDate") + "~" + map.get("maxDate"));
		list.get(0).setSellRegion(regionName);
		if (minDates != "") {
			map.put("minDate", "");
			map.put("minDates", minDates);
			list.addAll(this.getMarketPromotionAnalysisDao().listMarketPromotionAnalysis(map, pageInfo));
			if (list.size() == 1) {
				MarketPromotionAnalysis p = new MarketPromotionAnalysis();
				p.setSku(new BigDecimal(0));
				p.setSellQuantity(new BigDecimal(0));
				p.setSellTotalPrice(new BigDecimal(0));
				p.setSellProfit(new BigDecimal(0));
				p.setPsdSellQuantity(new BigDecimal(0));
				p.setPsdSellTotalPrice(new BigDecimal(0));
				p.setPsdSellProfit(new BigDecimal(0));
				p.setPermissionStoreQuantity(new BigDecimal(0));
				p.setSellStoreQuantity(new BigDecimal(0));
				p.setSellQuantityProportionM(new BigDecimal(0));
				p.setSellTotalPriceProportionM(new BigDecimal(0));
				p.setSellProfitProportionM(new BigDecimal(0));
				p.setSellQuantityS(new BigDecimal(0));
				p.setSellTotalPriceS(new BigDecimal(0));
				p.setSellProfitS(new BigDecimal(0));
				p.setPsdSellQuantityS(new BigDecimal(0));
				p.setPsdSellTotalPriceS(new BigDecimal(0));
				p.setPsdSellProfitS(new BigDecimal(0));
				list.add(p);
				n++;
			}
			list.get(1).setSellRegion(regionName);
			map.put("minDate", minDate);
		}
		if (list.size() == 2 && n != 2) {
			list.get(1).setTime(map.get("minDates") + "~" + map.get("maxDates"));
			MarketPromotionAnalysis p = new MarketPromotionAnalysis();
			p.setSellRegion(regionName);
			p.setTime("增长比例");
			if (!list.get(0).getSku().equals(new BigDecimal(0))) {
				BigDecimal sku = ((list.get(1).getSku().subtract(list.get(0).getSku()).multiply(new BigDecimal(100)).divide(list.get(0).getSku(), 2, RoundingMode.HALF_UP)));
				p.setSku(sku);
			} else if (list.get(0).getSku().equals(list.get(1).getSku())) {
				p.setSku(new BigDecimal(0));
			} else {
				p.setSku(new BigDecimal(100));
			}
			if (!list.get(0).getSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal sellQuantity = ((list.get(1).getSellQuantity().subtract(list.get(0).getSellQuantity()).multiply(new BigDecimal(100)).divide(list.get(0).getSellQuantity(), 2,
						RoundingMode.HALF_UP)));
				p.setSellQuantity(sellQuantity);
			} else if (list.get(0).getSellQuantity().equals(list.get(1).getSellQuantity())) {
				p.setSellQuantity(new BigDecimal(0));
			} else {
				p.setSellQuantity(new BigDecimal(100));
			}
			if (!list.get(0).getSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPrice = (list.get(1).getSellTotalPrice().subtract(list.get(0).getSellTotalPrice()).multiply(new BigDecimal(100)).divide(list.get(0).getSellTotalPrice(), 2,
						RoundingMode.HALF_UP));
				p.setSellTotalPrice(sellTotalPrice);
			} else if (list.get(0).getSellTotalPrice().equals(list.get(1).getSellTotalPrice())) {
				p.setSellTotalPrice(new BigDecimal(0));
			} else {
				p.setSellTotalPrice(new BigDecimal(100));
			}
			if (!list.get(0).getSellProfit().equals(new BigDecimal(0))) {
				BigDecimal sellProfit = (list.get(1).getSellProfit().subtract(list.get(0).getSellProfit()).multiply(new BigDecimal(100)).divide(list.get(0).getSellProfit(), 2, RoundingMode.HALF_UP));
				p.setSellProfit(sellProfit);
			} else if (list.get(0).getSellProfit().equals(list.get(1).getSellProfit())) {
				p.setSellProfit(new BigDecimal(0));
			} else {
				p.setSellProfit(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantity = (list.get(1).getPsdSellQuantity().subtract(list.get(0).getPsdSellQuantity()).multiply(new BigDecimal(100)).divide(list.get(0).getPsdSellQuantity(), 2,
						RoundingMode.HALF_UP));
				p.setPsdSellQuantity(psdSellQuantity);
			} else if (list.get(0).getPsdSellQuantity().equals(list.get(1).getPsdSellQuantity())) {
				p.setPsdSellQuantity(new BigDecimal(0));
			} else {
				p.setPsdSellQuantity(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPrice = (list.get(1).getPsdSellTotalPrice().subtract(list.get(0).getPsdSellTotalPrice()).multiply(new BigDecimal(100)).divide(
						list.get(0).getPsdSellTotalPrice(), 2, RoundingMode.HALF_UP));
				p.setPsdSellTotalPrice(psdSellTotalPrice);
			} else if (list.get(0).getPsdSellTotalPrice().equals(list.get(1).getPsdSellTotalPrice())) {
				p.setPsdSellTotalPrice(new BigDecimal(0));
			} else {
				p.setPsdSellTotalPrice(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellProfit().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfit = (list.get(1).getPsdSellProfit().subtract(list.get(0).getPsdSellProfit()).multiply(new BigDecimal(100)).divide(list.get(0).getPsdSellProfit(), 2,
						RoundingMode.HALF_UP));
				p.setPsdSellProfit(psdSellProfit);
			} else if (list.get(0).getPsdSellProfit().equals(list.get(1).getPsdSellProfit())) {
				p.setPsdSellProfit(new BigDecimal(0));
			} else {
				p.setPsdSellProfit(new BigDecimal(100));
			}
			if (!list.get(0).getPermissionStoreQuantity().equals(new BigDecimal(0))) {
				BigDecimal permissionStoreQuantity = ((list.get(1).getPermissionStoreQuantity().subtract(list.get(0).getPermissionStoreQuantity()).multiply(new BigDecimal(100)).divide(list.get(0)
						.getPermissionStoreQuantity(), 2, RoundingMode.HALF_UP)));
				p.setPermissionStoreQuantity(permissionStoreQuantity);
			} else if (list.get(0).getPermissionStoreQuantity().equals(list.get(1).getPermissionStoreQuantity())) {
				p.setPermissionStoreQuantity(new BigDecimal(0));
			} else {
				p.setPermissionStoreQuantity(new BigDecimal(100));
			}
			if (!list.get(0).getSellStoreQuantity().equals(new BigDecimal(0))) {
				BigDecimal sellStoreQuantity = ((list.get(1).getSellStoreQuantity().subtract(list.get(0).getSellStoreQuantity()).multiply(new BigDecimal(100)).divide(list.get(0)
						.getSellStoreQuantity(), 2, RoundingMode.HALF_UP)));
				p.setSellStoreQuantity(sellStoreQuantity);
			} else if (list.get(0).getSellStoreQuantity().equals(list.get(1).getSellStoreQuantity())) {
				p.setSellStoreQuantity(new BigDecimal(0));
			} else {
				p.setSellStoreQuantity(new BigDecimal(100));
			}
			if (!list.get(0).getSellQuantityProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionM = ((list.get(1).getSellQuantityProportionM().subtract(list.get(0).getSellQuantityProportionM()).multiply(new BigDecimal(100)).divide(list.get(0)
						.getSellQuantityProportionM(), 2, RoundingMode.HALF_UP)));
				p.setSellQuantityProportionM(sellQuantityProportionM);
			} else if (list.get(0).getSellQuantityProportionM().equals(list.get(1).getSellQuantityProportionM())) {
				p.setSellQuantityProportionM(new BigDecimal(0));
			} else {
				p.setSellQuantityProportionM(new BigDecimal(100));
			}
			if (!list.get(0).getSellTotalPriceProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionM = list.get(1).getSellTotalPriceProportionM().subtract(list.get(0).getSellTotalPriceProportionM()).multiply(new BigDecimal(100))
						.divide(list.get(0).getSellTotalPriceProportionM(), 2, RoundingMode.HALF_UP);
				p.setSellTotalPriceProportionM(sellTotalPriceProportionM);
			} else if (list.get(0).getSellTotalPriceProportionM().equals(list.get(1).getSellTotalPriceProportionM())) {
				p.setSellTotalPriceProportionM(new BigDecimal(0));
			} else {
				p.setSellTotalPriceProportionM(new BigDecimal(100));
			}
			if (!list.get(0).getSellProfitProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionM = (list.get(1).getSellProfitProportionM().subtract(list.get(0).getSellProfitProportionM()).multiply(new BigDecimal(100)).divide(list.get(0)
						.getSellProfitProportionM(), 2, RoundingMode.HALF_UP));
				p.setSellProfitProportionM(sellProfitProportionM);
			} else if (list.get(0).getSellProfitProportionM().equals(list.get(1).getSellProfitProportionM())) {
				p.setSellProfitProportionM(new BigDecimal(0));
			} else {
				p.setSellProfitProportionM(new BigDecimal(100));
			}
			if (!list.get(0).getSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityS = (list.get(1).getSellQuantityS().subtract(list.get(0).getSellQuantityS()).multiply(new BigDecimal(100)).divide(list.get(0).getSellQuantityS(), 2,
						RoundingMode.HALF_UP));
				p.setSellQuantityS(sellQuantityS);
			} else if (list.get(0).getSellQuantityS().equals(list.get(1).getSellQuantityS())) {
				p.setSellQuantityS(new BigDecimal(0));
			} else {
				p.setSellQuantityS(new BigDecimal(100));
			}
			if (!list.get(0).getSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceS = (list.get(1).getSellTotalPriceS().subtract(list.get(0).getSellTotalPriceS()).multiply(new BigDecimal(100)).divide(list.get(0).getSellTotalPriceS(), 2,
						RoundingMode.HALF_UP));
				p.setSellTotalPriceS(sellTotalPriceS);
			} else if (list.get(0).getSellTotalPriceS().equals(list.get(1).getSellTotalPriceS())) {
				p.setSellTotalPriceS(new BigDecimal(0));
			} else {
				p.setSellTotalPriceS(new BigDecimal(100));
			}
			if (!list.get(0).getSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitS = (list.get(1).getSellProfitS().subtract(list.get(0).getSellProfitS()).multiply(new BigDecimal(100)).divide(list.get(0).getSellProfitS(), 2,
						RoundingMode.HALF_UP));
				p.setSellProfitS(sellProfitS);
			} else if (list.get(0).getSellProfitS().equals(list.get(1).getSellProfitS())) {
				p.setSellProfitS(new BigDecimal(0));
			} else {
				p.setSellProfitS(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityS = (list.get(1).getPsdSellQuantityS().subtract(list.get(0).getPsdSellQuantityS()).multiply(new BigDecimal(100)).divide(list.get(0).getPsdSellQuantityS(), 2,
						RoundingMode.HALF_UP));
				p.setPsdSellQuantityS(psdSellQuantityS);
			} else if (list.get(0).getPsdSellQuantityS().equals(list.get(1).getPsdSellQuantityS())) {
				p.setPsdSellQuantityS(new BigDecimal(0));
			} else {
				p.setPsdSellQuantityS(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceS = (list.get(1).getPsdSellTotalPriceS().subtract(list.get(0).getPsdSellTotalPriceS()).multiply(new BigDecimal(100)).divide(list.get(0)
						.getPsdSellTotalPriceS(), 2, RoundingMode.HALF_UP));
				p.setPsdSellTotalPriceS(psdSellTotalPriceS);
			} else if (list.get(0).getPsdSellTotalPriceS().equals(list.get(1).getPsdSellTotalPriceS())) {
				p.setPsdSellTotalPriceS(new BigDecimal(0));
			} else {
				p.setPsdSellTotalPriceS(new BigDecimal(100));
			}
			if (!list.get(0).getPsdSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitS = (list.get(1).getPsdSellProfitS().subtract(list.get(0).getPsdSellProfitS()).multiply(new BigDecimal(100)).divide(list.get(0).getPsdSellProfitS(), 2,
						RoundingMode.HALF_UP));
				p.setPsdSellProfitS(psdSellProfitS);
			} else if (list.get(0).getPsdSellProfitS().equals(list.get(1).getPsdSellProfitS())) {
				p.setPsdSellProfitS(new BigDecimal(0));
			} else {
				p.setPsdSellProfitS(new BigDecimal(100));
			}
			p.setBfb("%");
			list.add(p);
		} else if (n == 2) {
			list.get(1).setTime(map.get("minDates") + "~" + map.get("maxDates"));
			MarketPromotionAnalysis p = new MarketPromotionAnalysis();
			p.setSellRegion(regionName);
			p.setTime("增长比例");
			p.setSku(new BigDecimal(0));
			p.setSellQuantity(new BigDecimal(0));
			p.setSellTotalPrice(new BigDecimal(0));
			p.setSellProfit(new BigDecimal(0));
			p.setPsdSellQuantity(new BigDecimal(0));
			p.setPsdSellTotalPrice(new BigDecimal(0));
			p.setPsdSellProfit(new BigDecimal(0));
			p.setPermissionStoreQuantity(new BigDecimal(0));
			p.setSellStoreQuantity(new BigDecimal(0));
			p.setSellQuantityProportionM(new BigDecimal(0));
			p.setSellTotalPriceProportionM(new BigDecimal(0));
			p.setSellProfitProportionM(new BigDecimal(0));
			p.setSellQuantityS(new BigDecimal(0));
			p.setSellTotalPriceS(new BigDecimal(0));
			p.setSellProfitS(new BigDecimal(0));
			p.setPsdSellQuantityS(new BigDecimal(0));
			p.setPsdSellTotalPriceS(new BigDecimal(0));
			p.setPsdSellProfitS(new BigDecimal(0));
			p.setBfb("%");
			list.add(p);
		}
		return list;
	}

	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo) {

		String minDates = map.get("minDates").toString();
		map.put("minDates", "");
		List<MarketPromotionAnalysis> list = this.getMarketPromotionAnalysisDao().listMarketPromotionAnalysisDetail(map, pageInfo);
		if (list.size() != 0) {
			for (int i = 0; i < list.size(); i++) {
				list.get(i).setTime(map.get("minDate") + "~" + map.get("maxDate"));
			}
		}
		if (minDates != "") {
			map.put("minDates", minDates);
			List<MarketPromotionAnalysis> lists = this.getMarketPromotionAnalysisDao().listMarketPromotionAnalysisDetail(map, pageInfo);
			if (lists.size() != 0) {
				if (list.size() != 0) {
					for (int i = 0; i < list.size(); i++) {
						lists.get(i).setTime(map.get("minDate") + "~" + map.get("maxDate"));
					}
				}
				for (int i = list.size(); i < lists.size(); i++) {
					lists.get(i).setTime(map.get("minDates") + "~" + map.get("maxDates"));
				}
			}
			return lists;
		}
		return list;
	}

	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetails(Map<String, Object> map, PageInfo pageInfo) {
		List<MarketPromotionAnalysis> lists = this.getMarketPromotionAnalysisDao().listMarketPromotionAnalysisDetails(map, pageInfo);
		if (lists.size() != 0) {
			for (int i = 0; i < lists.size(); i++) {
				MarketPromotionAnalysis mpa = lists.get(i);
				try {
					if (mpa.getDayA() != 0) {
						lists.get(i).setSellProfitPA(mpa.getSellProfitA().divide(new BigDecimal(mpa.getDayA()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellQuantityPA(mpa.getSellQuantityA().divide(new BigDecimal(mpa.getDayA()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellTotalPricePA(mpa.getSellTotalPriceA().divide(new BigDecimal(mpa.getDayA()), 2, RoundingMode.HALF_UP));
					}
				} catch (Exception e) {

				}
				try {
					if (mpa.getDayS() != 0) {
						lists.get(i).setSellProfitPS(mpa.getSellProfitS().divide(new BigDecimal(mpa.getDayS()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellQuantityPS(mpa.getSellQuantityS().divide(new BigDecimal(mpa.getDayS()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellTotalPricePS(mpa.getSellTotalPriceS().divide(new BigDecimal(mpa.getDayS()), 2, RoundingMode.HALF_UP));
					}
				} catch (Exception e) {
				}
				try {
					if (mpa.getDayD() != 0) {
						lists.get(i).setSellProfitPD(mpa.getSellProfitD().divide(new BigDecimal(mpa.getDayD()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellQuantityPD(mpa.getSellQuantityD().divide(new BigDecimal(mpa.getDayD()), 2, RoundingMode.HALF_UP));
						lists.get(i).setSellTotalPricePD(mpa.getSellTotalPriceD().divide(new BigDecimal(mpa.getDayD()), 2, RoundingMode.HALF_UP));
					}
				} catch (Exception e) {
				}
			}
		}
		return lists;
	}

	@Override
	public void exportMarketPromotionAnalysis(Map<String, Object> map, ServletOutputStream out) {

		List<MarketPromotionAnalysis> list = this.listMarketPromotionAnalysis(map, null);

		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("公司整体促销对比汇总表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellAnalysis(wb, sheet);
		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCellAnalysis(list, wb, sheet, map);
		}
		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null) {
					out.close();
				}
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
	private void fillHeaderCellAnalysis(Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(5);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, ExcelUtils.HEADR_FONT_SIZE);
		Cell cell = row.createCell(0);
		cell.setCellValue("公司整体促销对比汇总表");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 20; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A6:T6");
		CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);

		// 第二行
		row = sheet.createRow(6);

		cell = row.createCell(0);
		cell.setCellStyle(sencondRowStyle);
		cell.setCellValue("促销档期信息");
		for (int i = 1; i < 3; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(sencondRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A7:C7");
		cell = row.createCell(3);
		cell.setCellStyle(sencondRowStyle);
		cell.setCellValue("促销商品销售信息");
		for (int i = 4; i < 11; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(sencondRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "D7:K7");
		cell = row.createCell(11);
		cell.setCellStyle(sencondRowStyle);
		cell.setCellValue("促销商品占比情况");
		for (int i = 12; i < 14; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(sencondRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "L7:N7");
		cell = row.createCell(14);
		cell.setCellStyle(sencondRowStyle);
		cell.setCellValue("所有商品销售信息");
		for (int i = 15; i < 20; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(sencondRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "O7:T7");
		CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		// 第三行
		row = sheet.createRow(7);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("地区");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销档期");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("促销SKU数");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量（KG）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额(元)");
		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量（KG）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (120 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额占比(占所有商品)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量（KG）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额(元)");

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
	private void fillDataCellAnalysis(List<MarketPromotionAnalysis> list, Workbook wb, Sheet sheet, Map<String, Object> map) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDateAnalysis(sheet, i, list.get(i), strStyle, dateStyle, amtStyle, map);
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
	private void writeOneRowDateAnalysis(Sheet sheet, Integer rowIndex, MarketPromotionAnalysis msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Map<String, Object> map) {
		if (rowIndex == 0) {
			Row row = sheet.createRow(rowIndex);
			Cell cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("地区:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getSellRegion());

			row = sheet.createRow(rowIndex + 1);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("促销档期1:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("minDate") + "~" + map.get("maxDate"));

			if (map.get("minDates") != null && map.get("minDates") != "") {
				row = sheet.createRow(rowIndex + 2);
				cell = row.createCell(0);
				cell.setCellStyle(strStyle);
				cell.setCellValue("促销档期2:");

				cell = row.createCell(1);
				cell.setCellStyle(strStyle);
				cell.setCellValue(map.get("minDates") + "~" + map.get("maxDates"));
			}

			row = sheet.createRow(rowIndex + 3);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("searchType").toString().equals("1") ? "只查定量装商品" : map.get("searchType").toString().equals("2") ? "只查非定量装商品" : "定量非定量装商品均查");

			row = sheet.createRow(rowIndex + 4);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("searchTable").toString().equals("direct") ? "只看直营门店数据" : map.get("searchTable").toString().equals("join") ? "只看加盟门店数据" : "直营与加盟门店数据均看");
		}

		Row row = sheet.createRow(rowIndex + 8);

		int j = 0;
		String fujia = "";
		if (msd.getBfb() != "" && msd.getBfb() != null) {
			fujia = "%";
		}
		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSellRegion());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getTime());

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(msd.getSku() + fujia);

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantity()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantity()) + fujia);
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPrice()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPrice()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfit()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfit()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantity()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantity()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPrice()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPrice()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfit()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfit()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPermissionStoreQuantity()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0").format(msd.getPermissionStoreQuantity()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellStoreQuantity()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0").format(msd.getSellStoreQuantity()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionM()) + "%");

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionM()) + "%");

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionM()) + "%");

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityS()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceS()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitS()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityS()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceS()) + fujia);
		}
		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		if (fujia != "") {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitS()) + fujia);
		} else {
			cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitS()) + fujia);
		}
	}

	@Override
	public void exportMarketPromotionAnalysisDetail(Map<String, Object> map, ServletOutputStream out) {

		List<MarketPromotionAnalysis> list = this.listMarketPromotionAnalysisDetail(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("公司整体促销分析明细表");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellDetail(0, wb, sheet);
		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCellDetail(0, list, wb, sheet, map);
		}
		// 获取填充数据(多表格)
		List<MarketPromotionAnalysis> lists = this.listMarketPromotionAnalysisDetails(map, null);
		// 1. 创建excel表头(多表格)
		this.fillHeaderCellDetail(list.size() + 3, wb, sheet);
		// 2.用数据填充单元格(多表格)
		this.fillDataCellDetail(list.size() + 3, lists, wb, sheet, null);

		try {
			wb.write(out);
		} catch (Exception e) {
			throw new EscmException("导出Excel时异常", new String[] { e.getMessage() });
		} finally {
			try {
				if (out != null) {
					out.close();
				}
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
	private void fillHeaderCellDetail(Integer num, Workbook wb, Sheet sheet) {
		Integer rowCount = num + 5;
		// 第一行
		Row row = sheet.createRow(rowCount);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
		Cell cell = row.createCell(0);
		cell.setCellValue("商品销售信息");
		cell.setCellStyle(firstRowStyle);
		for (int i = 1; i < 19; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(firstRowStyle);
		}
		ExcelUtils.addMergedRegion(sheet, "A" + (rowCount + 1) + ":S" + (rowCount + 1));

		if (num != 0) {

			cell = row.createCell(19);
			cell.setCellStyle(firstRowStyle);
			cell.setCellValue("商品占比信息");
			for (int i = 20; i < 28; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "T" + (rowCount + 1) + ":AB" + (rowCount + 1));

			cell = row.createCell(28);
			cell.setCellStyle(firstRowStyle);
			cell.setCellValue("商品所在明细类销售信息");
			for (int i = 29; i < 37; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "AC" + (rowCount + 1) + ":AK" + (rowCount + 1));
			cell = row.createCell(37);
			cell.setCellStyle(firstRowStyle);
			cell.setCellValue("商品所在小分类销售信息");
			for (int i = 38; i < 46; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "AL" + (rowCount + 1) + ":AT" + (rowCount + 1));
			cell = row.createCell(46);
			cell.setCellStyle(firstRowStyle);
			cell.setCellValue("所有商品销售信息");
			for (int i = 47; i < 55; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "AU" + (rowCount + 1) + ":BC" + (rowCount + 1));
		}

		CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
		// 第二行
		row = sheet.createRow(rowCount + 1);

		int i = 0;// 从第零列(第一列)开始

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品名称");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定性角色");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("商品定量角色");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("中分类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("小分类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("明细类");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商编号");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("供应商名称");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		if (num == 0) {
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售档期");
		} else {
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售日期");
		}
		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售量（KG）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("毛利额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售量（KG）");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD销售额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("PSD毛利额(元)");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("权限店天");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
		cell.setCellValue("销售店天");

		cell = row.createCell(i);
		cell.setCellStyle(thirdRowStyle);
		sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
		cell.setCellValue("活跃度");
		if (num != 0) {
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量占比(占所有商品)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额占比(占所有商品)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额占比(占所有商品)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量占比(占小分类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额占比(占小分类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额占比(占小分类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量占比(占明细类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额占比(占明细类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额占比(占明细类)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD毛利额(元)");
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售量（KG）");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD毛利额(元)");

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
	private void fillDataCellDetail(Integer num, List<MarketPromotionAnalysis> list, Workbook wb, Sheet sheet, Map<String, Object> map) {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDateDetail(sheet, (i + num), list.get(i), strStyle, dateStyle, amtStyle, num, map);
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
	private void writeOneRowDateDetail(Sheet sheet, Integer rowIndex, MarketPromotionAnalysis msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Integer num, Map<String, Object> map) {
		if (rowIndex == 0) {
			Row row = sheet.createRow(rowIndex);
			Cell cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("地区:");

			String regionName = "全国";
			if (!map.get("sellRegion").equals("all")) {
				regionName = this.getMarketPromotionAnalysisDao().listRegion(map).get(0).getText();
			}
			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(regionName);

			row = sheet.createRow(rowIndex + 1);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue("促销档期1:");

			cell = row.createCell(1);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("minDate") + "~" + map.get("maxDate"));

			if (map.get("minDates") != null && map.get("minDates") != "") {
				row = sheet.createRow(rowIndex + 2);
				cell = row.createCell(0);
				cell.setCellStyle(strStyle);
				cell.setCellValue("促销档期2:");

				cell = row.createCell(1);
				cell.setCellStyle(strStyle);
				cell.setCellValue(map.get("minDates") + "~" + map.get("maxDates"));
			}

			row = sheet.createRow(rowIndex + 3);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("searchType").toString().equals("1") ? "只查定量装商品" : map.get("searchType").toString().equals("2") ? "只查非定量装商品" : "定量非定量装商品均查");

			row = sheet.createRow(rowIndex + 4);
			cell = row.createCell(0);
			cell.setCellStyle(strStyle);
			cell.setCellValue(map.get("searchTable").toString().equals("direct") ? "只看直营门店数据" : map.get("searchTable").toString().equals("join") ? "只看加盟门店数据" : "直营与加盟门店数据均看");
		}

		Row row = sheet.createRow(rowIndex + 7);

		int j = 0;

		Cell cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getMerchandiseName());

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
		cell.setCellValue(msd.getSupplierCode());

		cell = row.createCell(j++);
		cell.setCellStyle(strStyle);
		cell.setCellValue(msd.getSupplierName());

		cell = row.createCell(j++);
		if (num == 0) {
			cell.setCellStyle(dateStyle);
			cell.setCellValue(msd.getTime());
		} else {
			cell.setCellStyle(dateStyle);
			cell.setCellValue(msd.getSellDate());
		}

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPrice()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfit()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPrice()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfit()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getPermissionStoreQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(new DecimalFormat("#,##0").format(msd.getSellStoreQuantity()));

		cell = row.createCell(j++);
		cell.setCellStyle(amtStyle);
		cell.setCellValue(msd.getActive().compareTo(new BigDecimal(100)) == 1 ? "100.00%" : new DecimalFormat("#,##0.00").format(msd.getActive()) + "%");
		if (num != 0) {
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionM()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionM()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionM()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionS()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionS()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionS()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionD()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionD()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionD()) + "%");
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitD()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitS()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceA()));
			} catch (Exception e) {

			}
			try {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitA()));
			} catch (Exception e) {

			}
		}
	}

	@Override
	public String saveMarketPromotionAnalysisDetail(String fileName, Map<String, Object> paraMap) {
		String tarPath;
		Map<String, Object> map = new HashMap<String, Object>();
		String regionName = "全国";
		if (!paraMap.get("sellRegion").equals("all")) {
			regionName = this.getMarketPromotionAnalysisDao().listRegion(paraMap).get(0).getText();
		}
		map.put("sellRegion", regionName);
		map.put("time1", paraMap.get("minDate") + "~" + paraMap.get("maxDate"));
		if (paraMap.get("minDates") != null && paraMap.get("minDates") != "") {
			map.put("time2", paraMap.get("minDates") + "~" + paraMap.get("maxDates"));
		}
		map.put("searchType", paraMap.get("searchType").toString().equals("1") ? "只查定量装商品" : paraMap.get("searchType").toString().equals("2") ? "只查非定量装商品" : "定量非定量装商品均查");
		map.put("searchTable", paraMap.get("searchTable").toString().equals("direct") ? "只看直营门店数据" : paraMap.get("searchTable").toString().equals("join") ? "只看加盟门店数据" : "直营与加盟门店数据均看");
		map.put("saveType", paraMap.get("saveType"));
		List<MarketPromotionAnalysis> dataList = this.listMarketPromotionAnalysisDetail(paraMap, null);
		List<MarketPromotionAnalysis> dataLists = this.listMarketPromotionAnalysisDetails(paraMap, null);
		map.put("dataList", dataList);
		map.put("dataLists", dataLists);
		try {
			tarPath = FreeMarkerUtil.generateHtml("sellAnalysis/marketPromotionAnalysis/marketPromotionAnalysisGrid.ftl", "marketPromotionAnalysis".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.CPC.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MarketPromotionAnalysisServiceImpl.saveMarketPromotionAnalysisDetail1删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public String saveMarketPromotionAnalysis(String fileName, Map<String, Object> paraMap) {
		String tarPath;
		Map<String, Object> map = new HashMap<String, Object>();
		String regionName = "全国";
		if (!paraMap.get("sellRegion").equals("all")) {
			regionName = this.getMarketPromotionAnalysisDao().listRegion(paraMap).get(0).getText();
		}
		map.put("sellRegion", regionName);
		map.put("time1", paraMap.get("minDate") + "~" + paraMap.get("maxDate"));
		if (paraMap.get("minDates") != null && paraMap.get("minDates") != "") {
			map.put("time2", paraMap.get("minDates") + "~" + paraMap.get("maxDates"));
		}
		map.put("searchType", paraMap.get("searchType").toString().equals("1") ? "只查定量装商品" : paraMap.get("searchType").toString().equals("2") ? "只查非定量装商品" : "定量非定量装商品均查");
		map.put("searchTable", paraMap.get("searchTable").toString().equals("direct") ? "只看直营门店数据" : paraMap.get("searchTable").toString().equals("join") ? "只看加盟门店数据" : "直营与加盟门店数据均看");
		map.put("saveType", paraMap.get("saveType"));
		map.put("dataList", this.listMarketPromotionAnalysis(paraMap, null));
		try {
			tarPath = FreeMarkerUtil.generateHtml("sellAnalysis/marketPromotionAnalysis/marketPromotionAnalysisGrid.ftl", "marketPromotionAnalysis".concat("/").concat(fileName), map);
		} catch (Exception e) {
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.CPC.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MarketPromotionAnalysisServiceImpl.saveMarketPromotionAnalysis2删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

}