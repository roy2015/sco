package com.powere2e.sco.action.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import org.apache.commons.lang.StringUtils;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.MarketSellYearOnYearService;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYear;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis.SellYearOnYearAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 销售同比分析
 * 
 * @author Joyce.li
 * @since 2015年5月29日 下午2:36:45
 * @version 1.0
 */
public class MarketSellYearOnYearAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 542144012521731712L;
	private MarketSellYearOnYearService marketSellYearOnYearService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		marketSellYearOnYearService = (MarketSellYearOnYearService) ConfigFactory.getInstance().getBean("marketSellYearOnYearService");
	}

	/**
	 * 显示销售同比列表页面
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doShowMarketSellYearOnYearGrid() throws Exception {
		Map<String, Object> map = getSellYearOnYear().toMap();
		String sellRegion = this.asString("sellRegion");// 销售区域
		String startDate = this.asString("startDate");// 开始日期
		String endDate = this.asString("endDate");// 结束日期
		String seeYear = this.asString("seeYear");// 查看年数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		int firstYear = Integer.parseInt(startDate.substring(0, 4));
		int yearCount = 1;// 默认查看一年的同比
		if (StringUtils.isNotBlank(seeYear)) {
			yearCount = Integer.parseInt(seeYear);
		}
		int lastYear = firstYear - 1;
		int beforeLastYear = 0;
		int fourthYear = 0;
		if (yearCount == 2) {
			beforeLastYear = firstYear - 2;
		} else if (yearCount == 3) {
			beforeLastYear = firstYear - 2;
			fourthYear = firstYear - 3;
		}

		map.put("sellRegion", sellRegion);// 销售区域
		map.put("startDate", startDate);// 开始日期
		map.put("endDate", endDate);// 结束日期
		map.put("seeYear", seeYear);// 查看年数
		map.put("rationed", rationed);// 是否定量
		map.put("directJoin", directJoin);// 直营或加盟
		map.put("merchandiseCodes", merchandiseCodes);// 商品编号
		// 根据所选择的日期，获取所查询的月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String regionMonth = DateUtils.getDateRegionMonth(sdf.parse(startDate), sdf.parse(endDate));
		List<String> regionList = new ArrayList<String>();
		if (StringUtils.isNotBlank(regionMonth)) {
			String regionArray[] = regionMonth.split(",");
			if (regionArray.length > 0) {
				for (int j = 0; j < regionArray.length; j++) {
					regionList.add(regionArray[j]);
				}
			}
		}
		map.put("list", regionList);

		List<SellYearOnYearAnalysis> list = marketSellYearOnYearService.queryMarketSellYearOnYear(map, null);
		List<SellYearOnYearAnalysis> sumList = marketSellYearOnYearService.queryMarketSellSum(map, null);
		if (!list.isEmpty()) {
			list.addAll(sumList);
		}

		this.putObject("sellRegion", sellRegion);// 销售区域
		this.putObject("startDate", startDate);// 开始日期
		this.putObject("endDate", endDate);// 结束日期
		this.putObject("seeYear", seeYear);// 查看年数
		this.putObject("rationed", rationed);// 是否定量
		this.putObject("directJoin", directJoin);// 直营或加盟
		this.putObject("merchandiseCodes", merchandiseCodes);// 商品编号
		this.putObject("merchandiseList", list);// 同比结果
		this.putObject("firstYear", firstYear);// 第一年的年份
		this.putObject("lastYear", lastYear);// 第二年的年份
		this.putObject("beforeLastYear", beforeLastYear);// 第三年的年份
		this.putObject("fourthYear", fourthYear);// 第四年的年份

		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/sellYearOnYearAnalysis/marketSellYearOnYearGrid.ftl");
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doSaveMarketSellToHtml() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.GCS);// 报表类型(单品同比分析)
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换!");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = this.getSellYearOnYear().toMap();
		String sellRegion = this.asString("sellRegion");// 销售区域
		String startDate = this.asString("startDate");// 开始日期
		String endDate = this.asString("endDate");// 结束日期
		String seeYear = this.asString("seeYear");// 查看年数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		int firstYear = Integer.parseInt(startDate.substring(0, 4));
		int yearCount = 1;// 默认查看一年的同比
		if (StringUtils.isNotBlank(seeYear)) {
			yearCount = Integer.parseInt(seeYear);
		}
		int lastYear = firstYear - 1;
		int beforeLastYear = 0;
		int fourthYear = 0;
		if (yearCount == 2) {
			beforeLastYear = firstYear - 2;
		} else if (yearCount == 3) {
			beforeLastYear = firstYear - 2;
			fourthYear = firstYear - 3;
		}

		paraMap.put("sellRegion", sellRegion);// 销售区域
		paraMap.put("startDate", startDate);// 开始日期
		paraMap.put("endDate", endDate);// 结束日期
		paraMap.put("seeYear", seeYear);// 查看年数
		paraMap.put("rationed", rationed);// 是否定量
		paraMap.put("directJoin", directJoin);// 直营或加盟
		paraMap.put("merchandiseCodes", merchandiseCodes);// 商品编号
		paraMap.put("firstYear", firstYear);// 第一年年号
		paraMap.put("lastYear", lastYear);// 第二年年号
		paraMap.put("beforeLastYear", beforeLastYear);// 第三年年号
		paraMap.put("fourthYear", fourthYear);// 第三年年号
		// 根据所选择的日期，获取所查询的月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String regionMonth = DateUtils.getDateRegionMonth(sdf.parse(startDate), sdf.parse(endDate));
		List<String> regionList = new ArrayList<String>();
		if (StringUtils.isNotBlank(regionMonth)) {
			String regionArray[] = regionMonth.split(",");
			if (regionArray.length > 0) {
				for (int j = 0; j < regionArray.length; j++) {
					regionList.add(regionArray[j]);
				}
			}
		}
		paraMap.put("list", regionList);

		String msg = marketSellYearOnYearService.saveMarketSellToHtml(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功!");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doExportMarketSellToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "整体销售同比-".concat(this.asString("timeRange")).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getSellYearOnYear().toMap();

		String sellRegion = this.asString("sellRegion");// 销售区域
		String startDate = this.asString("startDate");// 开始日期
		String endDate = this.asString("endDate");// 结束日期
		String seeYear = this.asString("seeYear");// 查看年数
		String directJoin = this.asString("directJoin");// 直营或加盟
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		int firstYear = Integer.parseInt(startDate.substring(0, 4));
		int yearCount = 1;// 默认查看一年的同比
		if (StringUtils.isNotBlank(seeYear)) {
			yearCount = Integer.parseInt(seeYear);
		}
		int lastYear = firstYear - 1;
		int beforeLastYear = 0;
		int fourthYear = 0;
		if (yearCount == 2) {
			beforeLastYear = firstYear - 2;
		} else if (yearCount == 3) {
			beforeLastYear = firstYear - 2;
			fourthYear = firstYear - 3;
		}

		map.put("sellRegion", sellRegion);// 销售区域
		map.put("startDate", startDate);// 开始日期
		map.put("endDate", endDate);// 结束日期
		map.put("seeYear", seeYear);// 查看年数
		map.put("directJoin", directJoin);// 直营或加盟
		map.put("merchandiseCodes", merchandiseCodes);// 商品编号
		map.put("firstYear", firstYear);// 第一年年号
		map.put("lastYear", lastYear);// 第二年年号
		map.put("beforeLastYear", beforeLastYear);// 第三年年号
		map.put("fourthYear", fourthYear);// 第三年年号
		// 根据所选择的日期，获取所查询的月份
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String regionMonth = DateUtils.getDateRegionMonth(sdf.parse(startDate), sdf.parse(endDate));
		List<String> regionList = new ArrayList<String>();
		if (StringUtils.isNotBlank(regionMonth)) {
			String regionArray[] = regionMonth.split(",");
			if (regionArray.length > 0) {
				for (int j = 0; j < regionArray.length; j++) {
					regionList.add(regionArray[j]);
				}
			}
		}
		map.put("list", regionList);

		marketSellYearOnYearService.exportMarketSellToExcel(map, out);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SellYearOnYear getSellYearOnYear() throws Exception {
		SellYearOnYear sellYearOnYear = new SellYearOnYear();
		this.asBean(sellYearOnYear);
		return sellYearOnYear;
	}
}
