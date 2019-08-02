package com.powere2e.sco.service.impl.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
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
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.FreeMarkerUtil;
import com.powere2e.sco.common.utils.LoggerUtil;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysisDao;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysisService;
import com.powere2e.sco.model.reports.Reports;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 商品促销分析业务类的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MerchandisePromotionAnalysisServiceImpl extends ServiceImpl implements MerchandisePromotionAnalysisService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2328541788205369097L;
	private MerchandisePromotionAnalysisDao MerchandisePromotionAnalysisDao;

	public static MerchandisePromotionAnalysisService getInstance() {
		return (MerchandisePromotionAnalysisService) ConfigFactory.getInstance().getBean("MerchandisePromotionAnalysisService");
	}

	// 获得商品促销分析DAO实例
	public MerchandisePromotionAnalysisDao getMerchandisePromotionAnalysisDao() {
		return MerchandisePromotionAnalysisDao;
	}

	// 设置商品促销分析DAO实例
	public void setMerchandisePromotionAnalysisDao(MerchandisePromotionAnalysisDao MerchandisePromotionAnalysisDao) {
		this.MerchandisePromotionAnalysisDao = MerchandisePromotionAnalysisDao;
	}

	// 查询
	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return this.getMerchandisePromotionAnalysisDao().listMerchandisePromotionAnalysis(map, pageInfo);
	}

	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetailInfo(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		String day = map.get("dayA").toString();
		String maxDate = map.get("maxDate").toString();
		map.put("maxDate", DateUtils.getTime(map.get("minDate").toString(), 1));
		map.put("dayA", 0);
		List<MerchandisePromotionAnalysis> MerchandisePAs = this.getMerchandisePromotionAnalysisDao().listMerchandisePromotionAnalysisDetailInfo(map, null);
		map.put("dayA", day);
		map.put("maxDate", maxDate);
		List<MerchandisePromotionAnalysis> list = this.getMerchandisePromotionAnalysisDao().listMerchandisePromotionAnalysisDetailInfo(map, pageInfo);
		for (int i = 0; i < list.size(); i++) {
			if (MerchandisePAs.size() != 0) {
				list.get(i).setSellQuantityDB(list.get(i).getSellQuantity().multiply(new BigDecimal(100)).divide(MerchandisePAs.get(0).getSellQuantity(), 2, RoundingMode.HALF_UP));
				list.get(i).setSellTotalPriceDB(list.get(i).getSellTotalPrice().multiply(new BigDecimal(100)).divide(MerchandisePAs.get(0).getSellTotalPrice(), 2, RoundingMode.HALF_UP));
				list.get(i).setSellQuantityAB(list.get(i).getSellQuantityA().multiply(new BigDecimal(100)).divide(MerchandisePAs.get(0).getSellQuantityA(), 2, RoundingMode.HALF_UP));
				list.get(i).setSellTotalPriceAB(list.get(i).getSellTotalPriceA().multiply(new BigDecimal(100)).divide(MerchandisePAs.get(0).getSellTotalPriceA(), 2, RoundingMode.HALF_UP));
			} else {
				list.get(i).setSellQuantityDB(new BigDecimal(0));
				list.get(i).setSellTotalPriceDB(new BigDecimal(0));
				list.get(i).setSellQuantityAB(new BigDecimal(0));
				list.get(i).setSellTotalPriceAB(new BigDecimal(0));
			}
		}
		return list;
	}

	@Override
	public List<MerchandisePromotionAnalysis> lislistMerchandisePromotionAnalysisDetailTB(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		// 查询商品促销分析明细档期汇总查询
		List<MerchandisePromotionAnalysis> listSum = this.listMerchandisePromotionAnalysisDetail(map, null);
		// 促销明细查询
		List<MerchandisePromotionAnalysis> listDetail = this.listMerchandisePromotionAnalysisDetailInfo(map, null);
		// 创建一个接收查询计算数据的空集合
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		int num = -1;
		for (int i = 0; i < listDetail.size(); i++) {
			if (listDetail.get(i).getTime().equals(map.get("minDate").toString())) {
				num = i;
			}
		}
		for (int i = 0; i < listDetail.size(); i++) {
			int y = 0;
			if (listDetail.get(i).getType().equals("2")) {
				y = 1;
			} else if (listDetail.get(i).getType().equals("3")) {
				y = 2;
			}
			MerchandisePromotionAnalysis merchandisePromotionAnalysis = new MerchandisePromotionAnalysis();
			if (num != -1) {
				merchandisePromotionAnalysis.setTime(listDetail.get(i).getTime());
				merchandisePromotionAnalysis.setSellQuantityDB(listDetail.get(i).getSellQuantityDB());
				merchandisePromotionAnalysis.setSellTotalPriceDB(listDetail.get(i).getSellTotalPriceDB());
				merchandisePromotionAnalysis.setSellQuantityAB(listDetail.get(i).getSellQuantityAB());
				merchandisePromotionAnalysis.setSellTotalPriceAB(listDetail.get(i).getSellTotalPriceAB());
				merchandisePromotionAnalysis.setSellQuantityPDB(listSum.get(y).getSellQuantityP().multiply(new BigDecimal(100)).divide(listDetail.get(num).getSellQuantity(), 2, RoundingMode.HALF_UP));
				merchandisePromotionAnalysis.setSellQuantityPAB(listDetail.get(num).getSellQuantityPA().multiply(new BigDecimal(100))
						.divide(listDetail.get(num).getSellQuantityA(), 2, RoundingMode.HALF_UP));
				merchandisePromotionAnalysis.setSellTotalPricePDB(listSum.get(y).getSellTotalPriceP().multiply(new BigDecimal(100))
						.divide(listDetail.get(num).getSellTotalPrice(), 2, RoundingMode.HALF_UP));
				merchandisePromotionAnalysis.setSellTotalPricePAB(listDetail.get(num).getSellTotalPricePA().multiply(new BigDecimal(100))
						.divide(listDetail.get(num).getSellTotalPriceA(), 2, RoundingMode.HALF_UP));
			} else {
				merchandisePromotionAnalysis.setTime(listDetail.get(i).getTime());
				merchandisePromotionAnalysis.setSellQuantityDB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellTotalPriceDB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellQuantityAB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellTotalPriceAB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellQuantityPAB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellQuantityPDB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellTotalPricePAB(new BigDecimal(0));
				merchandisePromotionAnalysis.setSellTotalPricePDB(new BigDecimal(0));
			}
			lists.add(merchandisePromotionAnalysis);
		}
		return lists;
	}

	@Override
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		int n = 0;
		boolean show = true;
		String regionName = "";
		for (int i = 0; i < 3; i++) {
			map.put("show", i + 1);
			List<MerchandisePromotionAnalysis> list = this.getMerchandisePromotionAnalysisDao().listMerchandisePromotionAnalysisDetail(map, pageInfo);
			if (list.size() == 0) {
				show = false;
				MerchandisePromotionAnalysis merchandisePromotionAnalysis = new MerchandisePromotionAnalysis();
				merchandisePromotionAnalysis.setTime("无");
				list.add(merchandisePromotionAnalysis);
			} else {
				show = true;
				MerchandisePromotionAnalysis mpa = list.get(0);
				list.get(0).setDay(Integer.parseInt(map.get("dayA").toString()));
				regionName = mpa.getSellRegion();
			}
			if (n == 0) {
				if (show != false) {
					String minDate = "";
					String maxDate = "";
					minDate = DateUtils.getTime(map.get("minDate").toString(), -Integer.parseInt(map.get("dayA").toString()));
					maxDate = DateUtils.getTime(map.get("maxDate").toString(), -Integer.parseInt(map.get("dayA").toString()));
					list.get(0).setTime(minDate + "~" + maxDate);
				}
				list.get(0).setType("促销前");
			} else if (n == 1) {
				if (show != false) {
					String minDate = DateUtils.getTime(map.get("minDate").toString(), 0);
					String maxDate = DateUtils.getTime(map.get("maxDate").toString(), 0);
					list.get(0).setTime(minDate + "~" + maxDate);
				}
				list.get(0).setType("促销中");
			} else if (n == 2) {
				if (show != false) {
					String minDate = "";
					String maxDate = "";
					minDate = DateUtils.getTime(map.get("minDate").toString(), (Integer.parseInt(map.get("dayA").toString())));
					maxDate = DateUtils.getTime(map.get("maxDate").toString(), (Integer.parseInt(map.get("dayA").toString())));
					list.get(0).setTime(minDate + "~" + maxDate);
				}
				list.get(0).setType("促销后");
			}
			lists.add(list.get(0));
			n++;
		}
		MerchandisePromotionAnalysis merchandisePromotionAnalysis = new MerchandisePromotionAnalysis();
		merchandisePromotionAnalysis.setTime("促销中比促销前同比增长");
		if (lists.get(0).getTime().equals("无") && !lists.get(1).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, 1);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (!lists.get(0).getTime().equals("无") && lists.get(1).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, -1);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (lists.get(0).getTime().equals("无") && lists.get(1).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, 0);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (!lists.get(0).getTime().equals("无") && !lists.get(1).getTime().equals("无")) {
			if (!lists.get(0).getSellPrice().equals(new BigDecimal(0))) {
				BigDecimal sellPrice = new BigDecimal((lists.get(1).getSellPrice().subtract(lists.get(0).getSellPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellPrice(sellPrice);
			} else if (lists.get(0).getSellPrice().equals(lists.get(1).getSellPrice())) {
				merchandisePromotionAnalysis.setSellPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellPrice(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal sellQuantity = new BigDecimal((lists.get(1).getSellQuantity().subtract(lists.get(0).getSellQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantity(sellQuantity);
			} else if (lists.get(0).getSellQuantity().equals(lists.get(1).getSellQuantity())) {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPrice = new BigDecimal((lists.get(1).getSellTotalPrice().subtract(lists.get(0).getSellTotalPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPrice(sellTotalPrice);
			} else if (lists.get(0).getSellTotalPrice().equals(lists.get(1).getSellTotalPrice())) {
				merchandisePromotionAnalysis.setSellTotalPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPrice(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfit().equals(new BigDecimal(0))) {
				BigDecimal sellProfit = new BigDecimal((lists.get(1).getSellProfit().subtract(lists.get(0).getSellProfit()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfit(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfit(sellProfit);
			} else if (lists.get(0).getSellProfit().equals(lists.get(1).getSellProfit())) {
				merchandisePromotionAnalysis.setSellProfit(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfit(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantityP().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityP = new BigDecimal((lists.get(1).getSellQuantityP().subtract(lists.get(0).getSellQuantityP()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityP(sellQuantityP);
			} else if (lists.get(0).getSellQuantityP() == lists.get(1).getSellQuantityP()) {
				merchandisePromotionAnalysis.setSellQuantityP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityP(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceP().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceP = new BigDecimal((lists.get(1).getSellTotalPriceP().subtract(lists.get(0).getSellTotalPriceP()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceP(sellTotalPriceP);
			} else if (lists.get(0).getSellTotalPriceP().equals(lists.get(1).getSellTotalPriceP())) {
				merchandisePromotionAnalysis.setSellTotalPriceP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceP(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitP().equals(new BigDecimal(0))) {
				BigDecimal sellProfitP = new BigDecimal((lists.get(1).getSellProfitP().subtract(lists.get(0).getSellProfitP()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitP(sellProfitP);
			} else if (lists.get(0).getSellProfitP().equals(lists.get(1).getSellProfitP())) {
				merchandisePromotionAnalysis.setSellProfitP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitP(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantity = new BigDecimal((lists.get(1).getPsdSellQuantity().subtract(lists.get(0).getPsdSellQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantity(psdSellQuantity);
			} else if (lists.get(0).getPsdSellQuantity().equals(lists.get(1).getPsdSellQuantity())) {
				merchandisePromotionAnalysis.setPsdSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPrice = new BigDecimal((lists.get(1).getPsdSellTotalPrice().subtract(lists.get(0).getPsdSellTotalPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellTotalPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPrice(psdSellTotalPrice);
			} else if (lists.get(0).getPsdSellTotalPrice().equals(lists.get(1).getPsdSellTotalPrice())) {
				merchandisePromotionAnalysis.setPsdSellTotalPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPrice(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellProfit().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfit = new BigDecimal((lists.get(1).getPsdSellProfit().subtract(lists.get(0).getPsdSellProfit()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellProfit(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfit(psdSellProfit);
			} else if (lists.get(0).getPsdSellProfit().equals(lists.get(1).getPsdSellProfit())) {
				merchandisePromotionAnalysis.setPsdSellProfit(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfit(new BigDecimal(100));
			}
			if (lists.get(0).getPermissionStoreQuantity().doubleValue() != 0) {
				BigDecimal permissionStoreQuantity = new BigDecimal((lists.get(1).getPermissionStoreQuantity().subtract(lists.get(0).getPermissionStoreQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPermissionStoreQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPermissionStoreQuantity(permissionStoreQuantity);
			} else if (lists.get(0).getPermissionStoreQuantity() == lists.get(1).getPermissionStoreQuantity()) {
				merchandisePromotionAnalysis.setPermissionStoreQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPermissionStoreQuantity(new BigDecimal(0));
			}
			if (lists.get(0).getSellStoreQuantity().doubleValue() != 0) {
				BigDecimal sellStoreQuantity = new BigDecimal((lists.get(1).getSellStoreQuantity().subtract(lists.get(0).getSellStoreQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellStoreQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellStoreQuantity(sellStoreQuantity);
			} else if (lists.get(0).getSellStoreQuantity() == lists.get(1).getSellStoreQuantity()) {
				merchandisePromotionAnalysis.setSellStoreQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellStoreQuantity(new BigDecimal(0));
			}
			if (!lists.get(0).getActive().equals(new BigDecimal(0))) {
				BigDecimal active = new BigDecimal(
						(lists.get(1).getActive().subtract(lists.get(0).getActive()).multiply(new BigDecimal(100)).divide(lists.get(0).getActive(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setActive(active);
			} else if (lists.get(0).getActive().equals(lists.get(1).getActive())) {
				merchandisePromotionAnalysis.setActive(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setActive(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantityProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionM = new BigDecimal((lists.get(1).getSellQuantityProportionM().subtract(lists.get(0).getSellQuantityProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionM(sellQuantityProportionM);
			} else if (lists.get(0).getSellQuantityProportionM().equals(lists.get(1).getSellQuantityProportionM())) {
				merchandisePromotionAnalysis.setSellQuantityProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionM(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionM = new BigDecimal((lists.get(1).getSellTotalPriceProportionM().subtract(lists.get(0).getSellTotalPriceProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(sellTotalPriceProportionM);
			} else if (lists.get(0).getSellTotalPriceProportionM().equals(lists.get(1).getSellTotalPriceProportionM())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionM = new BigDecimal((lists.get(1).getSellProfitProportionM().subtract(lists.get(0).getSellProfitProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionM(sellProfitProportionM);
			} else if (lists.get(0).getSellProfitProportionM().equals(lists.get(1).getSellProfitProportionM())) {
				merchandisePromotionAnalysis.setSellProfitProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionM(new BigDecimal(100));
			}

			if (!lists.get(0).getSellQuantityProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionS = new BigDecimal((lists.get(1).getSellQuantityProportionS().subtract(lists.get(0).getSellQuantityProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionS(sellQuantityProportionS);
			} else if (lists.get(0).getSellQuantityProportionS().equals(lists.get(1).getSellQuantityProportionS())) {
				merchandisePromotionAnalysis.setSellQuantityProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionS = new BigDecimal((lists.get(1).getSellTotalPriceProportionS().subtract(lists.get(0).getSellTotalPriceProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(sellTotalPriceProportionS);
			} else if (lists.get(0).getSellTotalPriceProportionS().equals(lists.get(1).getSellTotalPriceProportionS())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionS = new BigDecimal((lists.get(1).getSellProfitProportionS().subtract(lists.get(0).getSellProfitProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionS(sellProfitProportionS);
			} else if (lists.get(0).getSellProfitProportionS().equals(lists.get(1).getSellProfitProportionS())) {
				merchandisePromotionAnalysis.setSellProfitProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionS(new BigDecimal(100));
			}

			if (!lists.get(0).getSellQuantityProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionD = new BigDecimal((lists.get(1).getSellQuantityProportionD().subtract(lists.get(0).getSellQuantityProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionD(sellQuantityProportionD);
			} else if (lists.get(0).getSellQuantityProportionD().equals(lists.get(1).getSellQuantityProportionD())) {
				merchandisePromotionAnalysis.setSellQuantityProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionD = new BigDecimal((lists.get(1).getSellTotalPriceProportionD().subtract(lists.get(0).getSellTotalPriceProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(sellTotalPriceProportionD);
			} else if (lists.get(0).getSellTotalPriceProportionD().equals(lists.get(1).getSellTotalPriceProportionD())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionD = new BigDecimal((lists.get(1).getSellProfitProportionD().subtract(lists.get(0).getSellProfitProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionD(sellProfitProportionD);
			} else if (lists.get(0).getSellProfitProportionD().equals(lists.get(1).getSellProfitProportionD())) {
				merchandisePromotionAnalysis.setSellProfitProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionD(new BigDecimal(100));
			}

			if (!lists.get(0).getSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityS = new BigDecimal((lists.get(1).getSellQuantityS().subtract(lists.get(0).getSellQuantityS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityS(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setSellQuantityS(sellQuantityS);
			} else if (lists.get(0).getSellQuantityS() == lists.get(1).getSellQuantityS()) {
				merchandisePromotionAnalysis.setSellQuantityS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceS = new BigDecimal((lists.get(1).getSellTotalPriceS().subtract(lists.get(0).getSellTotalPriceS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceS(sellTotalPriceS);
			} else if (lists.get(0).getSellTotalPriceS().equals(lists.get(1).getSellTotalPriceS())) {
				merchandisePromotionAnalysis.setSellTotalPriceS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitS = new BigDecimal((lists.get(1).getSellProfitS().subtract(lists.get(0).getSellProfitS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitS(sellProfitS);
			} else if (lists.get(0).getSellProfitS().equals(lists.get(1).getSellProfitS())) {
				merchandisePromotionAnalysis.setSellProfitS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantityPS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPS = new BigDecimal((lists.get(1).getSellQuantityPS().subtract(lists.get(0).getSellQuantityPS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityPS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityPS(sellQuantityPS);
			} else if (lists.get(0).getSellQuantityPS() == lists.get(1).getSellQuantityPS()) {
				merchandisePromotionAnalysis.setSellQuantityPS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPricePS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePS = new BigDecimal((lists.get(1).getSellTotalPricePS().subtract(lists.get(0).getSellTotalPricePS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPricePS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePS(sellTotalPricePS);
			} else if (lists.get(0).getSellTotalPricePS().equals(lists.get(1).getSellTotalPricePS())) {
				merchandisePromotionAnalysis.setSellTotalPricePS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePS(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitPS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPS = new BigDecimal((lists.get(1).getSellProfitPS().subtract(lists.get(0).getSellProfitPS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitPS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPS(sellProfitPS);
			} else if (lists.get(0).getSellProfitPS().equals(lists.get(1).getSellProfitPS())) {
				merchandisePromotionAnalysis.setSellProfitPS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPS(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityS = new BigDecimal((lists.get(1).getPsdSellQuantityS().subtract(lists.get(0).getPsdSellQuantityS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellQuantityS(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantityS(psdSellQuantityS);
			} else if (lists.get(0).getPsdSellQuantityS().equals(lists.get(1).getPsdSellQuantityS())) {
				merchandisePromotionAnalysis.setPsdSellQuantityS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityS(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceS = new BigDecimal((lists.get(1).getPsdSellTotalPriceS().subtract(lists.get(0).getPsdSellTotalPriceS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellTotalPriceS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(psdSellTotalPriceS);
			} else if (lists.get(0).getPsdSellTotalPriceS().equals(lists.get(1).getPsdSellTotalPriceS())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitS = new BigDecimal((lists.get(1).getPsdSellProfitS().subtract(lists.get(0).getPsdSellProfitS()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellProfitS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitS(psdSellProfitS);
			} else if (lists.get(0).getPsdSellProfitS().equals(lists.get(1).getPsdSellProfitS())) {
				merchandisePromotionAnalysis.setPsdSellProfitS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitS(new BigDecimal(100));
			}

			if (!lists.get(0).getSellQuantityD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityD = new BigDecimal((lists.get(1).getSellQuantityD().subtract(lists.get(0).getSellQuantityD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityD(sellQuantityD);
			} else if (lists.get(0).getSellQuantityD() == lists.get(1).getSellQuantityD()) {
				merchandisePromotionAnalysis.setSellQuantityD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceD = new BigDecimal((lists.get(1).getSellTotalPriceD().subtract(lists.get(0).getSellTotalPriceD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceD(sellTotalPriceD);
			} else if (lists.get(0).getSellTotalPriceD().equals(lists.get(1).getSellTotalPriceD())) {
				merchandisePromotionAnalysis.setSellTotalPriceD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitD = new BigDecimal((lists.get(1).getSellProfitD().subtract(lists.get(0).getSellProfitD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitD(sellProfitD);
			} else if (lists.get(0).getSellProfitD().equals(lists.get(1).getSellProfitD())) {
				merchandisePromotionAnalysis.setSellProfitD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantityPD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPD = new BigDecimal((lists.get(1).getSellQuantityPD().subtract(lists.get(0).getSellQuantityPD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityPD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityPD(sellQuantityPD);
			} else if (lists.get(0).getSellQuantityPD() == lists.get(1).getSellQuantityPD()) {
				merchandisePromotionAnalysis.setSellQuantityPD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPricePD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePD = new BigDecimal((lists.get(1).getSellTotalPricePD().subtract(lists.get(0).getSellTotalPricePD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPricePD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePD(sellTotalPricePD);
			} else if (lists.get(0).getSellTotalPricePD().equals(lists.get(1).getSellTotalPricePD())) {
				merchandisePromotionAnalysis.setSellTotalPricePD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePD(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitPD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPD = new BigDecimal((lists.get(1).getSellProfitPD().subtract(lists.get(0).getSellProfitPD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitPD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPD(sellProfitPD);
			} else if (lists.get(0).getSellProfitPD().equals(lists.get(1).getSellProfitPD())) {
				merchandisePromotionAnalysis.setSellProfitPD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPD(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellQuantityD().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityD = new BigDecimal((lists.get(1).getPsdSellQuantityD().subtract(lists.get(0).getPsdSellQuantityD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellQuantityD(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantityD(psdSellQuantityD);
			} else if (lists.get(0).getPsdSellQuantityD().equals(lists.get(1).getPsdSellQuantityD())) {
				merchandisePromotionAnalysis.setPsdSellQuantityD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityD(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellTotalPriceD().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceD = new BigDecimal((lists.get(1).getPsdSellTotalPriceD().subtract(lists.get(0).getPsdSellTotalPriceD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellTotalPriceD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(psdSellTotalPriceD);
			} else if (lists.get(0).getPsdSellTotalPriceD().equals(lists.get(1).getPsdSellTotalPriceD())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellProfitD().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitD = new BigDecimal((lists.get(1).getPsdSellProfitD().subtract(lists.get(0).getPsdSellProfitD()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellProfitD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitD(psdSellProfitD);
			} else if (lists.get(0).getPsdSellProfitD().equals(lists.get(1).getPsdSellProfitD())) {
				merchandisePromotionAnalysis.setPsdSellProfitD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitD(new BigDecimal(100));
			}

			if (!lists.get(0).getSellQuantityA().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityA = new BigDecimal((lists.get(1).getSellQuantityA().subtract(lists.get(0).getSellQuantityA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityA(sellQuantityA);
			} else if (lists.get(0).getSellQuantityA() == lists.get(1).getSellQuantityA()) {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPriceA().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceA = new BigDecimal((lists.get(1).getSellTotalPriceA().subtract(lists.get(0).getSellTotalPriceA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPriceA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceA(sellTotalPriceA);
			} else if (lists.get(0).getSellTotalPriceA().equals(lists.get(1).getSellTotalPriceA())) {
				merchandisePromotionAnalysis.setSellTotalPriceA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceA(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitA().equals(new BigDecimal(0))) {
				BigDecimal sellProfitA = new BigDecimal((lists.get(1).getSellProfitA().subtract(lists.get(0).getSellProfitA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitA(sellProfitA);
			} else if (lists.get(0).getSellProfitA().equals(lists.get(1).getSellProfitA())) {
				merchandisePromotionAnalysis.setSellProfitA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitA(new BigDecimal(100));
			}
			if (!lists.get(0).getSellQuantityPA().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPA = new BigDecimal((lists.get(1).getSellQuantityPA().subtract(lists.get(0).getSellQuantityPA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellQuantityPA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityPA(sellQuantityPA);
			} else if (lists.get(0).getSellQuantityPA() == lists.get(1).getSellQuantityPA()) {
				merchandisePromotionAnalysis.setSellQuantityPA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPA(new BigDecimal(100));
			}
			if (!lists.get(0).getSellTotalPricePA().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePA = new BigDecimal((lists.get(1).getSellTotalPricePA().subtract(lists.get(0).getSellTotalPricePA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellTotalPricePA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePA(sellTotalPricePA);
			} else if (lists.get(0).getSellTotalPricePA().equals(lists.get(1).getSellTotalPricePA())) {
				merchandisePromotionAnalysis.setSellTotalPricePA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePA(new BigDecimal(100));
			}
			if (!lists.get(0).getSellProfitPA().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPA = new BigDecimal((lists.get(1).getSellProfitPA().subtract(lists.get(0).getSellProfitPA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getSellProfitPA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPA(sellProfitPA);
			} else if (lists.get(0).getSellProfitPA().equals(lists.get(1).getSellProfitPA())) {
				merchandisePromotionAnalysis.setSellProfitPA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPA(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellQuantityA().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityA = new BigDecimal((lists.get(1).getPsdSellQuantityA().subtract(lists.get(0).getPsdSellQuantityA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellQuantityA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellQuantityA(psdSellQuantityA);
			} else if (lists.get(0).getPsdSellQuantityA().equals(lists.get(1).getPsdSellQuantityA())) {
				merchandisePromotionAnalysis.setPsdSellQuantityA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityA(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellTotalPriceA().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceA = new BigDecimal((lists.get(1).getPsdSellTotalPriceA().subtract(lists.get(0).getPsdSellTotalPriceA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellTotalPriceA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(psdSellTotalPriceA);
			} else if (lists.get(0).getPsdSellTotalPriceA().equals(lists.get(1).getPsdSellTotalPriceA())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(new BigDecimal(100));
			}
			if (!lists.get(0).getPsdSellProfitA().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitA = new BigDecimal((lists.get(1).getPsdSellProfitA().subtract(lists.get(0).getPsdSellProfitA()).multiply(new BigDecimal(100))
						.divide(lists.get(0).getPsdSellProfitA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitA(psdSellProfitA);
			} else if (lists.get(0).getPsdSellProfitA().equals(lists.get(1).getPsdSellProfitA())) {
				merchandisePromotionAnalysis.setPsdSellProfitA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitA(new BigDecimal(100));
			}
			merchandisePromotionAnalysis.setBfb("%");
		}
		lists.add(merchandisePromotionAnalysis);
		merchandisePromotionAnalysis = new MerchandisePromotionAnalysis();
		merchandisePromotionAnalysis.setTime("促销后比促销中同比增长");
		if (lists.get(1).getTime().equals("无") && !lists.get(2).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, 1);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (!lists.get(1).getTime().equals("无") && lists.get(2).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, -1);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (lists.get(1).getTime().equals("无") && lists.get(2).getTime().equals("无")) {
			merchandisePromotionAnalysis = this.setValue(merchandisePromotionAnalysis, 0);
			merchandisePromotionAnalysis.setBfb("%");
		} else if (!lists.get(1).getTime().equals("无") && !lists.get(2).getTime().equals("无")) {
			if (!lists.get(1).getSellPrice().equals(new BigDecimal(0))) {
				BigDecimal sellPrice = new BigDecimal((lists.get(2).getSellPrice().subtract(lists.get(1).getSellPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellPrice(sellPrice);
			} else if (lists.get(1).getSellPrice().equals(lists.get(2).getSellPrice())) {
				merchandisePromotionAnalysis.setSellPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellPrice(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal sellQuantity = new BigDecimal((lists.get(2).getSellQuantity().subtract(lists.get(1).getSellQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantity(sellQuantity);
			} else if (lists.get(1).getSellQuantity().equals(lists.get(2).getSellQuantity())) {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPrice = new BigDecimal((lists.get(2).getSellTotalPrice().subtract(lists.get(1).getSellTotalPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPrice(sellTotalPrice);
			} else if (lists.get(1).getSellTotalPrice().equals(lists.get(2).getSellTotalPrice())) {
				merchandisePromotionAnalysis.setSellTotalPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPrice(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfit().equals(new BigDecimal(0))) {
				BigDecimal sellProfit = new BigDecimal((lists.get(2).getSellProfit().subtract(lists.get(1).getSellProfit()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfit(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfit(sellProfit);
			} else if (lists.get(1).getSellProfit().equals(lists.get(2).getSellProfit())) {
				merchandisePromotionAnalysis.setSellProfit(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfit(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantityP().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityP = new BigDecimal((lists.get(2).getSellQuantityP().subtract(lists.get(1).getSellQuantityP()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityP(sellQuantityP);
			} else if (lists.get(1).getSellQuantityP().equals(lists.get(2).getSellQuantityP())) {
				merchandisePromotionAnalysis.setSellQuantityP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityP(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceP().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceP = new BigDecimal((lists.get(2).getSellTotalPriceP().subtract(lists.get(1).getSellTotalPriceP()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceP(sellTotalPriceP);
			} else if (lists.get(1).getSellTotalPriceP().equals(lists.get(2).getSellTotalPriceP())) {
				merchandisePromotionAnalysis.setSellTotalPriceP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceP(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitP().equals(new BigDecimal(0))) {
				BigDecimal sellProfitP = new BigDecimal((lists.get(2).getSellProfitP().subtract(lists.get(1).getSellProfitP()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitP(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitP(sellProfitP);
			} else if (lists.get(1).getSellProfitP().equals(lists.get(2).getSellProfitP())) {
				merchandisePromotionAnalysis.setSellProfitP(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitP(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellQuantity().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantity = new BigDecimal((lists.get(2).getPsdSellQuantity().subtract(lists.get(1).getPsdSellQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantity(psdSellQuantity);
			} else if (lists.get(1).getPsdSellQuantity().equals(lists.get(2).getPsdSellQuantity())) {
				merchandisePromotionAnalysis.setPsdSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellTotalPrice().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPrice = new BigDecimal((lists.get(2).getPsdSellTotalPrice().subtract(lists.get(1).getPsdSellTotalPrice()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellTotalPrice(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPrice(psdSellTotalPrice);
			} else if (lists.get(1).getPsdSellTotalPrice().equals(lists.get(2).getPsdSellTotalPrice())) {
				merchandisePromotionAnalysis.setPsdSellTotalPrice(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPrice(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellProfit().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfit = new BigDecimal((lists.get(2).getPsdSellProfit().subtract(lists.get(1).getPsdSellProfit()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellProfit(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfit(psdSellProfit);
			} else if (lists.get(1).getPsdSellProfit().equals(lists.get(2).getPsdSellProfit())) {
				merchandisePromotionAnalysis.setPsdSellProfit(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfit(new BigDecimal(100));
			}
			if (lists.get(1).getPermissionStoreQuantity().doubleValue() != 0) {
				BigDecimal permissionStoreQuantity = new BigDecimal((lists.get(2).getPermissionStoreQuantity().subtract(lists.get(1).getPermissionStoreQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPermissionStoreQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPermissionStoreQuantity(permissionStoreQuantity);
			} else if (lists.get(1).getPermissionStoreQuantity() == lists.get(2).getPermissionStoreQuantity()) {
				merchandisePromotionAnalysis.setPermissionStoreQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPermissionStoreQuantity(new BigDecimal(0));
			}
			if (lists.get(1).getSellStoreQuantity().doubleValue() != 0) {
				BigDecimal sellStoreQuantity = new BigDecimal((lists.get(2).getSellStoreQuantity().subtract(lists.get(1).getSellStoreQuantity()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellStoreQuantity(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellStoreQuantity(sellStoreQuantity);
			} else if (lists.get(1).getSellStoreQuantity() == lists.get(2).getSellStoreQuantity()) {
				merchandisePromotionAnalysis.setSellStoreQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellStoreQuantity(new BigDecimal(0));
			}
			if (!lists.get(1).getActive().equals(new BigDecimal(0))) {
				BigDecimal active = new BigDecimal(
						(lists.get(2).getActive().subtract(lists.get(1).getActive()).multiply(new BigDecimal(100)).divide(lists.get(1).getActive(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setActive(active);
			} else if (lists.get(1).getActive().equals(lists.get(2).getActive())) {
				merchandisePromotionAnalysis.setActive(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setActive(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantityProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionM = new BigDecimal((lists.get(2).getSellQuantityProportionM().subtract(lists.get(1).getSellQuantityProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionM(sellQuantityProportionM);
			} else if (lists.get(1).getSellQuantityProportionM().equals(lists.get(2).getSellQuantityProportionM())) {
				merchandisePromotionAnalysis.setSellQuantityProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionM(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionM = new BigDecimal((lists.get(2).getSellTotalPriceProportionM().subtract(lists.get(1).getSellTotalPriceProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(sellTotalPriceProportionM);
			} else if (lists.get(1).getSellTotalPriceProportionM().equals(lists.get(2).getSellTotalPriceProportionM())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionM(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitProportionM().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionM = new BigDecimal((lists.get(2).getSellProfitProportionM().subtract(lists.get(1).getSellProfitProportionM()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitProportionM(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionM(sellProfitProportionM);
			} else if (lists.get(1).getSellProfitProportionM().equals(lists.get(2).getSellProfitProportionM())) {
				merchandisePromotionAnalysis.setSellProfitProportionM(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionM(new BigDecimal(100));
			}

			if (!lists.get(1).getSellQuantityProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionS = new BigDecimal((lists.get(2).getSellQuantityProportionS().subtract(lists.get(1).getSellQuantityProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionS(sellQuantityProportionS);
			} else if (lists.get(1).getSellQuantityProportionS().equals(lists.get(2).getSellQuantityProportionS())) {
				merchandisePromotionAnalysis.setSellQuantityProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionS = new BigDecimal((lists.get(2).getSellTotalPriceProportionS().subtract(lists.get(1).getSellTotalPriceProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(sellTotalPriceProportionS);
			} else if (lists.get(1).getSellTotalPriceProportionS().equals(lists.get(2).getSellTotalPriceProportionS())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitProportionS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionS = new BigDecimal((lists.get(2).getSellProfitProportionS().subtract(lists.get(1).getSellProfitProportionS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitProportionS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionS(sellProfitProportionS);
			} else if (lists.get(1).getSellProfitProportionS().equals(lists.get(2).getSellProfitProportionS())) {
				merchandisePromotionAnalysis.setSellProfitProportionS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionS(new BigDecimal(100));
			}

			if (!lists.get(1).getSellQuantityProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityProportionD = new BigDecimal((lists.get(2).getSellQuantityProportionD().subtract(lists.get(1).getSellQuantityProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityProportionD(sellQuantityProportionD);
			} else if (lists.get(1).getSellQuantityProportionD().equals(lists.get(2).getSellQuantityProportionD())) {
				merchandisePromotionAnalysis.setSellQuantityProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityProportionD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceProportionD = new BigDecimal((lists.get(2).getSellTotalPriceProportionD().subtract(lists.get(1).getSellTotalPriceProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(sellTotalPriceProportionD);
			} else if (lists.get(1).getSellTotalPriceProportionD().equals(lists.get(2).getSellTotalPriceProportionD())) {
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceProportionD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitProportionD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitProportionD = new BigDecimal((lists.get(2).getSellProfitProportionD().subtract(lists.get(1).getSellProfitProportionD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitProportionD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitProportionD(sellProfitProportionD);
			} else if (lists.get(1).getSellProfitProportionD().equals(lists.get(2).getSellProfitProportionD())) {
				merchandisePromotionAnalysis.setSellProfitProportionD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitProportionD(new BigDecimal(100));
			}

			if (!lists.get(1).getSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityS = new BigDecimal((lists.get(2).getSellQuantityS().subtract(lists.get(1).getSellQuantityS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityS(sellQuantityS);
			} else if (lists.get(1).getSellQuantityS() == lists.get(2).getSellQuantityS()) {
				merchandisePromotionAnalysis.setSellQuantityS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceS = new BigDecimal((lists.get(2).getSellTotalPriceS().subtract(lists.get(1).getSellTotalPriceS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceS(sellTotalPriceS);
			} else if (lists.get(1).getSellTotalPriceS().equals(lists.get(2).getSellTotalPriceS())) {
				merchandisePromotionAnalysis.setSellTotalPriceS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitS = new BigDecimal((lists.get(2).getSellProfitS().subtract(lists.get(1).getSellProfitS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitS(sellProfitS);
			} else if (lists.get(1).getSellProfitS().equals(lists.get(2).getSellProfitS())) {
				merchandisePromotionAnalysis.setSellProfitS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantityPS().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPS = new BigDecimal((lists.get(2).getSellQuantityPS().subtract(lists.get(1).getSellQuantityPS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityPS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityPS(sellQuantityPS);
			} else if (lists.get(1).getSellQuantityPS() == lists.get(2).getSellQuantityPS()) {
				merchandisePromotionAnalysis.setSellQuantityPS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPricePS().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePS = new BigDecimal((lists.get(2).getSellTotalPricePS().subtract(lists.get(1).getSellTotalPricePS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPricePS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePS(sellTotalPricePS);
			} else if (lists.get(1).getSellTotalPricePS().equals(lists.get(2).getSellTotalPricePS())) {
				merchandisePromotionAnalysis.setSellTotalPricePS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePS(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitPS().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPS = new BigDecimal((lists.get(2).getSellProfitPS().subtract(lists.get(1).getSellProfitPS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitPS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPS(sellProfitPS);
			} else if (lists.get(1).getSellProfitPS().equals(lists.get(2).getSellProfitPS())) {
				merchandisePromotionAnalysis.setSellProfitPS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPS(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellQuantityS().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityS = new BigDecimal((lists.get(2).getPsdSellQuantityS().subtract(lists.get(1).getPsdSellQuantityS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellQuantityS(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantityS(psdSellQuantityS);
			} else if (lists.get(1).getPsdSellQuantityS().equals(lists.get(2).getPsdSellQuantityS())) {
				merchandisePromotionAnalysis.setPsdSellQuantityS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityS(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellTotalPriceS().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceS = new BigDecimal((lists.get(2).getPsdSellTotalPriceS().subtract(lists.get(1).getPsdSellTotalPriceS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellTotalPriceS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(psdSellTotalPriceS);
			} else if (lists.get(1).getPsdSellTotalPriceS().equals(lists.get(2).getPsdSellTotalPriceS())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceS(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellProfitS().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitS = new BigDecimal((lists.get(2).getPsdSellProfitS().subtract(lists.get(1).getPsdSellProfitS()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellProfitS(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitS(psdSellProfitS);
			} else if (lists.get(1).getPsdSellProfitS().equals(lists.get(2).getPsdSellProfitS())) {
				merchandisePromotionAnalysis.setPsdSellProfitS(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitS(new BigDecimal(100));
			}

			if (!lists.get(1).getSellQuantityD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityD = new BigDecimal((lists.get(2).getSellQuantityD().subtract(lists.get(1).getSellQuantityD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityD(sellQuantityD);
			} else if (lists.get(1).getSellQuantityD() == lists.get(2).getSellQuantityD()) {
				merchandisePromotionAnalysis.setSellQuantityD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceD = new BigDecimal((lists.get(2).getSellTotalPriceD().subtract(lists.get(1).getSellTotalPriceD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceD(sellTotalPriceD);
			} else if (lists.get(1).getSellTotalPriceD().equals(lists.get(2).getSellTotalPriceD())) {
				merchandisePromotionAnalysis.setSellTotalPriceD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitD = new BigDecimal((lists.get(2).getSellProfitD().subtract(lists.get(1).getSellProfitD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitD(sellProfitD);
			} else if (lists.get(1).getSellProfitD().equals(lists.get(2).getSellProfitD())) {
				merchandisePromotionAnalysis.setSellProfitD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantityPD().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPD = new BigDecimal((lists.get(2).getSellQuantityPD().subtract(lists.get(1).getSellQuantityPD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityPD(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setSellQuantityPD(sellQuantityPD);
			} else if (lists.get(1).getSellQuantityPD() == lists.get(2).getSellQuantityPD()) {
				merchandisePromotionAnalysis.setSellQuantityPD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPricePD().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePD = new BigDecimal((lists.get(2).getSellTotalPricePD().subtract(lists.get(1).getSellTotalPricePD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPricePD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePD(sellTotalPricePD);
			} else if (lists.get(1).getSellTotalPricePD().equals(lists.get(2).getSellTotalPricePD())) {
				merchandisePromotionAnalysis.setSellTotalPricePD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePD(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitPD().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPD = new BigDecimal((lists.get(2).getSellProfitPD().subtract(lists.get(1).getSellProfitPD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitPD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPD(sellProfitPD);
			} else if (lists.get(1).getSellProfitPD().equals(lists.get(2).getSellProfitPD())) {
				merchandisePromotionAnalysis.setSellProfitPD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPD(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellQuantityD().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityD = new BigDecimal((lists.get(2).getPsdSellQuantityD().subtract(lists.get(1).getPsdSellQuantityD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellQuantityD(), 2, RoundingMode.HALF_UP).doubleValue()));

				merchandisePromotionAnalysis.setPsdSellQuantityD(psdSellQuantityD);
			} else if (lists.get(1).getPsdSellQuantityD().equals(lists.get(2).getPsdSellQuantityD())) {
				merchandisePromotionAnalysis.setPsdSellQuantityD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityD(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellTotalPriceD().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceD = new BigDecimal((lists.get(2).getPsdSellTotalPriceD().subtract(lists.get(1).getPsdSellTotalPriceD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellTotalPriceD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(psdSellTotalPriceD);
			} else if (lists.get(1).getPsdSellTotalPriceD().equals(lists.get(2).getPsdSellTotalPriceD())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceD(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellProfitD().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitD = new BigDecimal((lists.get(2).getPsdSellProfitD().subtract(lists.get(1).getPsdSellProfitD()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellProfitD(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitD(psdSellProfitD);
			} else if (lists.get(1).getPsdSellProfitD().equals(lists.get(2).getPsdSellProfitD())) {
				merchandisePromotionAnalysis.setPsdSellProfitD(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitD(new BigDecimal(100));
			}

			if (!lists.get(1).getSellQuantityA().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityA = new BigDecimal((lists.get(2).getSellQuantityA().subtract(lists.get(1).getSellQuantityA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityA(sellQuantityA);
			} else if (lists.get(1).getSellQuantityA() == lists.get(2).getSellQuantityA()) {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPriceA().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPriceA = new BigDecimal((lists.get(2).getSellTotalPriceA().subtract(lists.get(1).getSellTotalPriceA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPriceA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPriceA(sellTotalPriceA);
			} else if (lists.get(1).getSellTotalPriceA().equals(lists.get(2).getSellTotalPriceA())) {
				merchandisePromotionAnalysis.setSellTotalPriceA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPriceA(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitA().equals(new BigDecimal(0))) {
				BigDecimal sellProfitA = new BigDecimal((lists.get(2).getSellProfitA().subtract(lists.get(1).getSellProfitA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitA(sellProfitA);
			} else if (lists.get(1).getSellProfitA().equals(lists.get(2).getSellProfitA())) {
				merchandisePromotionAnalysis.setSellProfitA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitA(new BigDecimal(100));
			}
			if (!lists.get(1).getSellQuantityPA().equals(new BigDecimal(0))) {
				BigDecimal sellQuantityPA = new BigDecimal((lists.get(2).getSellQuantityPA().subtract(lists.get(1).getSellQuantityPA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellQuantityPA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellQuantityPA(sellQuantityPA);
			} else if (lists.get(1).getSellQuantityPA() == lists.get(2).getSellQuantityPA()) {
				merchandisePromotionAnalysis.setSellQuantityPA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellQuantityPA(new BigDecimal(100));
			}
			if (!lists.get(1).getSellTotalPricePA().equals(new BigDecimal(0))) {
				BigDecimal sellTotalPricePA = new BigDecimal((lists.get(2).getSellTotalPricePA().subtract(lists.get(1).getSellTotalPricePA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellTotalPricePA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellTotalPricePA(sellTotalPricePA);
			} else if (lists.get(1).getSellTotalPricePA().equals(lists.get(2).getSellTotalPricePA())) {
				merchandisePromotionAnalysis.setSellTotalPricePA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellTotalPricePA(new BigDecimal(100));
			}
			if (!lists.get(1).getSellProfitPA().equals(new BigDecimal(0))) {
				BigDecimal sellProfitPA = new BigDecimal((lists.get(2).getSellProfitPA().subtract(lists.get(1).getSellProfitPA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getSellProfitPA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setSellProfitPA(sellProfitPA);
			} else if (lists.get(1).getSellProfitPA().equals(lists.get(2).getSellProfitPA())) {
				merchandisePromotionAnalysis.setSellProfitPA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setSellProfitPA(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellQuantityA().equals(new BigDecimal(0))) {
				BigDecimal psdSellQuantityA = new BigDecimal((lists.get(2).getPsdSellQuantityA().subtract(lists.get(1).getPsdSellQuantityA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellQuantityA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellQuantityA(psdSellQuantityA);
			} else if (lists.get(1).getPsdSellQuantityA().equals(lists.get(2).getPsdSellQuantityA())) {
				merchandisePromotionAnalysis.setPsdSellQuantityA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellQuantityA(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellTotalPriceA().equals(new BigDecimal(0))) {
				BigDecimal psdSellTotalPriceA = new BigDecimal((lists.get(2).getPsdSellTotalPriceA().subtract(lists.get(1).getPsdSellTotalPriceA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellTotalPriceA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(psdSellTotalPriceA);
			} else if (lists.get(1).getPsdSellTotalPriceA().equals(lists.get(2).getPsdSellTotalPriceA())) {
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellTotalPriceA(new BigDecimal(100));
			}
			if (!lists.get(1).getPsdSellProfitA().equals(new BigDecimal(0))) {
				BigDecimal psdSellProfitA = new BigDecimal((lists.get(2).getPsdSellProfitA().subtract(lists.get(1).getPsdSellProfitA()).multiply(new BigDecimal(100))
						.divide(lists.get(1).getPsdSellProfitA(), 2, RoundingMode.HALF_UP).doubleValue()));
				merchandisePromotionAnalysis.setPsdSellProfitA(psdSellProfitA);
			} else if (lists.get(1).getPsdSellProfitA().equals(lists.get(2).getPsdSellProfitA())) {
				merchandisePromotionAnalysis.setPsdSellProfitA(new BigDecimal(0));
			} else {
				merchandisePromotionAnalysis.setPsdSellProfitA(new BigDecimal(100));
			}
			merchandisePromotionAnalysis.setBfb("%");
		}

		lists.add(merchandisePromotionAnalysis);
		for (int i = 0; i < lists.size(); i++) {
			if (lists.get(i).getTime().equals("无")) {

			}
		}
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).setSellRegion(regionName);

		}
		return lists;
	}

	public MerchandisePromotionAnalysis setValue(MerchandisePromotionAnalysis merchandisePromotionAnalysis, Integer type) {
		merchandisePromotionAnalysis.setSellPrice(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantity(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPrice(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfit(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityP(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceP(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitP(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellQuantity(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellTotalPrice(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellProfit(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPermissionStoreQuantity(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellStoreQuantity(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setActive(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityProportionM(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceProportionM(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitProportionM(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityProportionS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceProportionS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitProportionS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityProportionD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceProportionD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitProportionD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityPS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPricePS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitPS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellQuantityS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellTotalPriceS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellProfitS(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityPD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPricePD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitPD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellQuantityD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellTotalPriceD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellProfitD(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPriceA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellQuantityPA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellTotalPricePA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setSellProfitPA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellQuantityA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellTotalPriceA(new BigDecimal(100 * type));
		merchandisePromotionAnalysis.setPsdSellProfitA(new BigDecimal(100 * type));
		return merchandisePromotionAnalysis;
	}

	@Override
	public void exportMerchandisePromotionAnalysisDetail(Map<String, Object> map, ServletOutputStream out) throws Exception {

		List<MerchandisePromotionAnalysis> list = this.listMerchandiseInfo(map, null);
		SXSSFWorkbook wb = ExcelUtils.createSXSSFWorkbook();
		Sheet sheet = wb.createSheet("促销表现对比结果");
		// 1. 创建excel表头(第一行和第二行)
		this.fillHeaderCellDetail(0, wb, sheet);
		// 2.用数据填充单元格
		if (!list.isEmpty()) {
			this.fillDataCellDetail(0, list, wb, sheet);
		}
		String[] minDate = map.get("minDate").toString().split(",");
		String[] maxDate = map.get("maxDate").toString().split(",");
		String[] day = map.get("dayA").toString().split(",");
		String[] sellRegion = map.get("sellRegion").toString().split(",");
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		List<MerchandisePromotionAnalysis> listInfos = new ArrayList<MerchandisePromotionAnalysis>();
		for (int i = 0; i < minDate.length; i++) {
			map.put("minDate", minDate[i]);
			map.put("maxDate", maxDate[i]);
			map.put("dayA", day[i]);
			map.put("sellRegion", sellRegion[i]);
			lists.addAll(this.listMerchandisePromotionAnalysisDetail(map, null));
			listInfos.addAll(this.listMerchandisePromotionAnalysisDetailInfo(map, null));
		}
		this.fillHeaderCellDetail(list.size() + 3, wb, sheet);
		this.fillDataCellDetail(list.size() + 3, lists, wb, sheet);
		this.fillHeaderCellDetail(list.size() + lists.size() + 7, wb, sheet);
		this.fillDataCellDetail(list.size() + lists.size() + 7, listInfos, wb, sheet);
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
	private void fillHeaderCellDetail(Integer num, Workbook wb, Sheet sheet) {
		// 第一行
		Row row = sheet.createRow(num);
		CellStyle firstRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
		if (num == 0) {
			Cell cell = row.createCell(0);
			cell.setCellValue("商品信息");
			cell.setCellStyle(firstRowStyle);
			for (int i = 1; i < 11; i++) {
				cell = row.createCell(i);
				cell.setCellStyle(firstRowStyle);
			}
			ExcelUtils.addMergedRegion(sheet, "A1:K1");
			CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
			// 第二行
			row = sheet.createRow(1);

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
			sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
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
			sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
			cell.setCellValue("细分类");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (50 * 80));// 设置当前行单元格宽度
			cell.setCellValue("采购部门");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("供应商编号");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("供应商名称");
		} else {

			Cell cell = row.createCell(0);
			if (num == 4) {
				cell.setCellStyle(firstRowStyle);
				cell.setCellValue("商品促销表现对比汇总表");
				for (int i = 1; i < 53; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 1) + ":BA" + (num + 1));

				// 第二行
				row = sheet.createRow(num + 1);
				CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
				cell = row.createCell(0);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品销售信息");
				for (int i = 1; i < 17; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 2) + ":Q" + (num + 2));

				cell = row.createCell(17);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品占比信息");

				for (int i = 18; i < 26; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "R" + (num + 2) + ":Z" + (num + 2));

				cell = row.createCell(26);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品所在明细类销售信息");

				for (int i = 27; i < 35; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "AA" + (num + 2) + ":AI" + (num + 2));
				cell = row.createCell(35);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品所在小分类销售信息");
				for (int i = 36; i < 44; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "AJ" + (num + 2) + ":AR" + (num + 2));
				cell = row.createCell(44);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("所有商品销售信息");
				for (int i = 45; i < 53; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "AS" + (num + 2) + ":BA" + (num + 2));

			} else {
				cell.setCellStyle(firstRowStyle);
				cell.setCellValue("商品促销表现对比明细表");
				for (int i = 1; i < 51; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(firstRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 1) + ":AZ" + (num + 1));

				// 第二行
				row = sheet.createRow(num + 1);
				CellStyle sencondRowStyle = ExcelUtils.getHeaderCellStyle(wb, 15);
				
				cell = row.createCell(0);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("地区");
				ExcelUtils.addMergedRegion(sheet, "A" + (num + 2) + ":A" + (num + 3));

				cell = row.createCell(1);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("销售日期");
				ExcelUtils.addMergedRegion(sheet, "B" + (num + 2) + ":B" + (num + 3));

				cell = row.createCell(2);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品销售信息");
				for (int i = 3; i < 14; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "C" + (num + 2) + ":N" + (num + 2));

				cell = row.createCell(14);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品占比信息");
				for (int i = 15; i < 23; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "O" + (num + 2) + ":W" + (num + 2));

				cell = row.createCell(23);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品所在明细类销售信息");

				for (int i = 24; i < 32; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "X" + (num + 2) + ":AF" + (num + 2));
				cell = row.createCell(32);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("商品所在小分类销售信息");
				for (int i = 33; i < 41; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "AG" + (num + 2) + ":AO" + (num + 2));
				cell = row.createCell(41);
				cell.setCellStyle(sencondRowStyle);
				cell.setCellValue("所有商品销售信息");
				for (int i = 42; i < 52; i++) {
					cell = row.createCell(i);
					cell.setCellStyle(sencondRowStyle);
				}
				ExcelUtils.addMergedRegion(sheet, "AP" + (num + 2) + ":AZ" + (num + 2));
			}
			// 第三行

			row = sheet.createRow(num + 2);
			CellStyle thirdRowStyle = ExcelUtils.getHeaderCellStyle(wb, null);
			int i = 0;// 从第零列(第一列)开始
			if (num == 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
				cell.setCellValue("地区");

				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("纬度");

				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
				cell.setCellValue("销售日期");

				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
				cell.setCellValue("天数");
			} else {
				i++;
				i++;
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("价格(元/kg或件)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (100 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售量（KG/件）");
			if (num != 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
				cell.setCellValue("日销售量VS促销后开始日期当天销售量（KG/件）");
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额(元)");
			if (num != 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 100));// 设置当前行单元格宽度
				cell.setCellValue("日销售额VS促销后开始日期当天销售额(元)");
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额(元)");
			if (num == 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("平均销售量（KG/件）");

				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("平均销售额(元)");

				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
				cell.setCellValue("平均毛利额(元)");
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("PSD销售量（KG/件）");

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
			cell.setCellValue("销售量（KG/件）");

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
			cell.setCellValue("平均销售量（KG/件）");

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
			cell.setCellValue("PSD销售量（KG/件）");

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
			cell.setCellValue("销售量（KG/件）");

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
			cell.setCellValue("平均销售量（KG/件）");

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
			cell.setCellValue("PSD销售量（KG/件）");

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
			cell.setCellValue("销售量（KG/件）");
			if (num != 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 150));// 设置当前行单元格宽度
				cell.setCellValue("日销售量VS促销后开始日期当天销售量（KG/件）");
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("销售额(元)");
			if (num != 4) {
				cell = row.createCell(i);
				cell.setCellStyle(thirdRowStyle);
				sheet.setColumnWidth(i++, (short) (100 * 150));// 设置当前行单元格宽度
				cell.setCellValue("日销售额VS促销后开始日期当天销售额(元)");
			}
			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("毛利额(元)");

			cell = row.createCell(i);
			cell.setCellStyle(thirdRowStyle);
			sheet.setColumnWidth(i++, (short) (60 * 80));// 设置当前行单元格宽度
			cell.setCellValue("平均销售量（KG/件）");

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
			cell.setCellValue("PSD销售量（KG/件）");

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
	 * @throws Exception
	 */
	private void fillDataCellDetail(Integer num, List<MerchandisePromotionAnalysis> list, Workbook wb, Sheet sheet) throws Exception {
		CellStyle strStyle = ExcelUtils.getDefaultStringStyle(wb);// 字符串样式
		CellStyle dateStyle = ExcelUtils.getDefaultDateStyle(wb);// 字符串样式
		CellStyle amtStyle = ExcelUtils.getDefaultAmoutStyle(wb);// 金额样式

		for (int i = 0; i < list.size(); i++) {
			writeOneRowDateDetail(sheet, (i + num), list.get(i), strStyle, dateStyle, amtStyle, num);
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
	private void writeOneRowDateDetail(Sheet sheet, Integer rowIndex, MerchandisePromotionAnalysis msd, CellStyle strStyle, CellStyle dateStyle, CellStyle amtStyle, Integer num) throws Exception {
		Row row = null;
		if (num != 0) {
			row = sheet.createRow(rowIndex + 3);
		} else {
			row = sheet.createRow(rowIndex + 2);
		}
		int j = 0;
		String fujia = "";
		if (msd.getBfb() != "" && msd.getBfb() != null) {
			fujia = "%";
		}
		Cell cell = row.createCell(j++);
		if (num == 0) {
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
			cell.setCellValue(msd.getFineName());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getPurchaseDepartments());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getSupplierCode());

			cell = row.createCell(j++);
			cell.setCellStyle(strStyle);
			cell.setCellValue(msd.getSupplierName());
		} else {
			if (num == 4) {

				cell.setCellStyle(strStyle);
				cell.setCellValue(msd.getSellRegion());
				try {
					cell = row.createCell(j++);
					cell.setCellStyle(strStyle);
					cell.setCellValue(msd.getType());
				} catch (Exception e) {

				}
				cell = row.createCell(j++);
				cell.setCellStyle(strStyle);
				cell.setCellValue(msd.getTime());
				try {

					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					cell.setCellValue(new DecimalFormat("#,##0").format(msd.getDay()));
				} catch (Exception e) {

				}
			} else {
				cell.setCellStyle(strStyle);
				cell.setCellValue(msd.getSellRegion());
				Cell cell1 = row.createCell(j++);
				cell1.setCellStyle(strStyle);
				cell1.setCellValue(msd.getTime());
			}
			if (!msd.getTime().equals("无")) {
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellPrice()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellPrice()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantity()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantity()) + fujia);
				}
				if (num != 4) {
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityDB()) + "%");
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPrice()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPrice()) + fujia);
				}
				if (num != 4) {
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceDB()) + "%");
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfit()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfit()) + fujia);
				}
				if (num == 4) {
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					if (fujia != "") {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityP()) + fujia);
					} else {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityP()) + fujia);
					}
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					if (fujia != "") {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceP()) + fujia);
					} else {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceP()) + fujia);
					}
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					if (fujia != "") {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitP()) + fujia);
					} else {
						cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitP()) + fujia);
					}
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
				cell.setCellValue(msd.getActive().compareTo(new BigDecimal(100)) == 1 ? "100.00%" : new DecimalFormat("#,##0.00").format(msd.getActive()) + "%");

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
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionS()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionS()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionS()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityProportionD()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceProportionD()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitProportionD()) + "%");

				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceD()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitD()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitD()) + fujia);
				}
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
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPS()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPS()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePS()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePS()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPS()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPS()) + fujia);
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
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityA()) + fujia);
				}
				if (num != 4) {
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityAB()) + "%");
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceA()) + fujia);
				}
				if (num != 4) {
					cell = row.createCell(j++);
					cell.setCellStyle(amtStyle);
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPriceAB()) + "%");
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellQuantityPA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellTotalPricePA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getSellProfitPA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellQuantityA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellTotalPriceA()) + fujia);
				}
				cell = row.createCell(j++);
				cell.setCellStyle(amtStyle);
				if (fujia != "") {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitA()) + fujia);
				} else {
					cell.setCellValue(new DecimalFormat("#,##0.00").format(msd.getPsdSellProfitA()) + fujia);
				}
			}
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public String saveMerchandisePromotionAnalysisDetail(String fileName, Map<String, Object> paraMap) throws Exception {
		String tarPath;
		Map<String, Object> map = paraMap;
		List<MerchandisePromotionAnalysis> list = this.listMerchandiseInfo(paraMap, null);
		String[] minDate = map.get("minDate").toString().split(",");
		String[] maxDate = map.get("maxDate").toString().split(",");
		String[] day = map.get("dayA").toString().split(",");
		String[] sellRegion = map.get("sellRegion").toString().split(",");
		List<MerchandisePromotionAnalysis> lists = new ArrayList<MerchandisePromotionAnalysis>();
		List<MerchandisePromotionAnalysis> listInfos = new ArrayList<MerchandisePromotionAnalysis>();
		List<List> listTB = new ArrayList<List>();
		for (int i = 0; i < minDate.length; i++) {
			map.put("minDate", minDate[i]);
			map.put("maxDate", maxDate[i]);
			map.put("dayA", day[i]);
			map.put("sellRegion", sellRegion[i]);
			lists.addAll(this.listMerchandisePromotionAnalysisDetail(map, null));
			listInfos.addAll(this.listMerchandisePromotionAnalysisDetailInfo(map, null));
			listTB.add(this.lislistMerchandisePromotionAnalysisDetailTB(map, null));
		}
		map.put("merchandise", list);
		map.put("dataList", lists);
		map.put("dataLists", listInfos);
		map.put("line", JSONArray.fromObject(listTB).toString());

		try {
			tarPath = FreeMarkerUtil.generateHtml("sellAnalysis/merchandisePromotionAnalysis/merchandisePromotionAnalysisGrid.ftl", "merchandisePromotionAnalysis".concat("/").concat(fileName), map);
		} catch (Exception e) {
			e.printStackTrace();
			return "转换报表模板时异常";
		}
		File file = new File(tarPath);// 报表文件
		if (file.exists()) {
			try {
				Reports myReport = new Reports(BusinessConstants.myReportType.MPC.toString(), fileName, tarPath.replace(ConfigPath.getUploadFilePath(), ""));
				ReportsServiceImpl.getInstance().insertReports(myReport);
			} catch (Exception e) {
				file.delete();
				LoggerUtil.logger.error("MerchandisePromotionAnalysisServiceImpl.saveMerchandisePromotionAnalysisDetail删除文件["+ file.getPath() +"]");
				throw new EscmException("记录报表文件信息时出错");
			}
		} else {
			return "生成Html文件失败";
		}
		return null;
	}

	@Override
	public List<MerchandisePromotionAnalysis> listMerchandise(Map<String, Object> map, PageInfo pageInfo) {

		return this.getMerchandisePromotionAnalysisDao().listMerchandise(map, pageInfo);
	}

	@Override
	public List<MerchandisePromotionAnalysis> listMerchandiseInfo(Map<String, Object> map, PageInfo pageInfo) {

		return this.getMerchandisePromotionAnalysisDao().listMerchandiseInfo(map, pageInfo);
	}
}