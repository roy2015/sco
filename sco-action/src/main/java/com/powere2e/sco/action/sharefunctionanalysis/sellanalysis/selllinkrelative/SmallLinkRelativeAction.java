package com.powere2e.sco.action.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import org.apache.commons.lang.StringUtils;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.interfaces.service.sharefunctionanalysis.sellanalysis.selllinkrelative.SmallLinkRelativeService;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.LinkRelative;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative.SellLinkRelativeAnalysis;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 小分类销售环比的action
 * 
 * @author Joyce.li
 * @since 2015年7月7日 下午2:31:00
 * @version 1.0
 */
public class SmallLinkRelativeAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 183809898635920318L;
	private SmallLinkRelativeService smallLinkRelativeService;

	public SmallLinkRelativeService getSmallLinkRelativeService() {
		return smallLinkRelativeService;
	}

	public void setSmallLinkRelativeService(SmallLinkRelativeService smallLinkRelativeService) {
		this.smallLinkRelativeService = smallLinkRelativeService;
	}

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		smallLinkRelativeService = (SmallLinkRelativeService) ConfigFactory.getInstance().getBean("smallLinkRelativeService");
	}

	/**
	 * 显示小分类销售环比数据页面
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doShowSmallLinkRelativeGrid() throws Exception {
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		String sellRegion = this.asString("sellRegion");// 销售区域
		Date startDate = this.asDate("startDate");// 开始日期
		Date endDate = this.asDate("endDate");// 结束日期
		Integer linkRelativeCycle = this.asInteger("linkRelativeCycle");// 环比周期
		Integer cycleCount = this.asInteger("cycleCount");// 环比次数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟

		this.putObject("sellRegion", sellRegion);// 销售区域
		this.putObject("startDate", startDate);// 开始日期
		this.putObject("endDate", endDate);// 结束日期
		this.putObject("linkRelativeCycle", linkRelativeCycle);// 环比周期
		this.putObject("cycleCount", cycleCount);// 环比次数
		this.putObject("rationed", rationed);// 是否定量
		this.putObject("directJoin", directJoin);// 直营或加盟
		this.putObject("merchandiseAndSupplierCodes", this.asString("merchandiseAndSupplierCodes"));// 商品和供应商编号
		this.putObject("merchandiseCodes", merchandiseCodes);// 商品编号

		this.forwardPage("sco/shareFunctionAnalysis/sellAnalysis/sellLinkRelative/smallSellLinkRelativeGrid.ftl");
	}

	/**
	 * 查询小分类信息
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doListSmallTypeInfo() throws Exception {
		Map<String, Object> map = this.getLinkRelative().toMap();
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");
		String rationed = this.asString("rationed");// 是否定量
		// 根据商品编号和供应商编号组得到对应的商品编号和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);

		map.put("codeList", codeList);
		map.put("rationed", rationed);
		List<LinkRelative> list = smallLinkRelativeService.listSmallTypeInfo(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 查询小分类显示环比数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doListSmallLinkRelativeGrid() throws Exception {
		Map<String, Object> map = this.getLinkRelative().toMap();
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");
		String sellRegion = this.asString("sellRegion");// 销售区域
		Date startDate = this.asDate("startDate");// 开始日期
		// Date endDate = this.asDate("endDate");// 结束日期
		Integer linkRelativeCycle = this.asInteger("linkRelativeCycle");// 环比周期
		Integer cycleCount = this.asInteger("cycleCount");// 环比次数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟
		map.put("merchandiseCodes", merchandiseCodes);
		map.put("linkRelativeCycle", linkRelativeCycle);
		map.put("cycleCount", cycleCount);
		map.put("directJoin", directJoin);
		map.put("sellRegion", sellRegion);
		map.put("rationed", rationed);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 得到每次环比的起始和结束日期
		List<LinkRelative> list = getLinkRelativeCycleList(startDate, linkRelativeCycle, cycleCount, dateFormat);
		map.put("list", list);
		// 根据商品编号和供应商编号组得到对应的商品编号和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("codeList", codeList);

		List<SellLinkRelativeAnalysis> analysisList = smallLinkRelativeService.querySmallSellLinkRelative(map, null);
		this.forwardData(true, analysisList, null);
	}

	/**
	 * 保存页面查询结果
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doSaveSmallSellToHtml() throws Exception {
		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.SSS);// 报表类型(小分类环比分析)
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换!");
			return;
		}
		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");
		String sellRegion = this.asString("sellRegion");// 销售区域
		Date startDate = this.asDate("startDate");// 开始日期
		Date endDate = this.asDate("endDate");// 结束日期
		Integer linkRelativeCycle = this.asInteger("linkRelativeCycle");// 环比周期
		Integer cycleCount = this.asInteger("cycleCount");// 环比次数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟
		map.put("merchandiseCodes", merchandiseCodes);
		map.put("linkRelativeCycle", linkRelativeCycle);
		map.put("cycleCount", cycleCount);
		map.put("directJoin", directJoin);
		map.put("sellRegion", sellRegion);
		map.put("rationed", rationed);
		map.put("startDate", startDate);
		map.put("endDate", endDate);

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 得到每次环比的起始和结束日期
		List<LinkRelative> list = getLinkRelativeCycleList(startDate, linkRelativeCycle, cycleCount, dateFormat);
		map.put("list", list);
		// 根据商品编号和供应商编号组得到对应的商品编号和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("codeList", codeList);

		String msg = smallLinkRelativeService.saveSmallSellToHtml(fileName, map);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功!");
			return;
		}
		this.forwardData(false, null, msg);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco.sharefunctionanalysis")
	public void doExportSmallSellToExcel() throws Exception {
		ServletOutputStream out = response.getOutputStream();
		String fileName = "小分类销售环比-".concat(this.asString("timeRange")).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		Map<String, Object> map = this.getLinkRelative().toMap();

		String merchandiseCodes = this.asString("merchandiseCodes");// 商品编号
		String merchandiseAndSupplierCodes = this.asString("merchandiseAndSupplierCodes");
		String sellRegion = this.asString("sellRegion");// 销售区域
		Date startDate = this.asDate("startDate");// 开始日期
		Date endDate = this.asDate("endDate");// 结束日期
		Integer linkRelativeCycle = this.asInteger("linkRelativeCycle");// 环比周期
		Integer cycleCount = this.asInteger("cycleCount");// 环比次数
		String rationed = this.asString("rationed");// 是否定量
		String directJoin = this.asString("directJoin");// 直营或加盟

		map.put("sellRegion", sellRegion);// 销售区域
		map.put("startDate", startDate);// 开始日期
		map.put("endDate", endDate);// 结束日期
		map.put("linkRelativeCycle", linkRelativeCycle);// 环比周期
		map.put("cycleCount", cycleCount);// 环比次数
		map.put("rationed", rationed);// 是否定量
		map.put("directJoin", directJoin);// 直营或加盟
		map.put("merchandiseCodes", merchandiseCodes);// 商品编号

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 得到每次环比的起始和结束日期
		List<LinkRelative> list = getLinkRelativeCycleList(startDate, linkRelativeCycle, cycleCount, dateFormat);
		map.put("list", list);
		// 根据商品编号和供应商编号组得到对应的商品编号和供应商编号
		List<ApplicationMerchandise> codeList = MerchandiseOaApplicationUtil.getIntentionAndSupplierCodeGroupList(merchandiseAndSupplierCodes);
		map.put("codeList", codeList);

		smallLinkRelativeService.exportSmallSellToExcel(map, out);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	public LinkRelative getLinkRelative() throws Exception {
		LinkRelative linkRelative = new LinkRelative();
		this.asBean(linkRelative);
		return linkRelative;
	}

	/**
	 * 根据起始日期、环比周期和环比次数得到每次环比的起始和结束日期
	 * 
	 * @param startDate
	 *            环比起始日期
	 * @param linkRelativeCycle
	 *            环比周期
	 * @param cycleCount
	 *            环比次数
	 * @param dateFormat
	 *            日期格式
	 * @return
	 */
	private static List<LinkRelative> getLinkRelativeCycleList(Date startDate, Integer linkRelativeCycle, Integer cycleCount, SimpleDateFormat dateFormat) {
		List<LinkRelative> list = new ArrayList<LinkRelative>();
		if (cycleCount != null && linkRelativeCycle != null) {
			for (int i = 1; i <= cycleCount; i++) {
				LinkRelative linkRelative = new LinkRelative();
				Calendar cal = Calendar.getInstance();
				if (i == 1) {
					// 获取第一个周期的结束时间
					cal.setTime(startDate);
					cal.add(Calendar.DATE, linkRelativeCycle - 1);
					Date tempDate = cal.getTime();
					// 获取上一个环比周期的开始时间
					cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.DATE, -linkRelativeCycle);
					Date beforeStartDate = cal.getTime();
					// 获取上一个环比周期的结束时间
					cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.DATE, -1);
					Date beforeEndDate = cal.getTime();

					linkRelative.setMinDate(dateFormat.format(startDate));
					linkRelative.setMaxDate(dateFormat.format(tempDate));
					linkRelative.setBeforeMinDate(dateFormat.format(beforeStartDate));
					linkRelative.setBeforeMaxDate(dateFormat.format(beforeEndDate));

					list.add(linkRelative);
				} else {
					// 获取周期开始时间
					cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.DATE, linkRelativeCycle * (i - 1));
					Date tempMinDate = cal.getTime();
					// 获取周期结束时间
					cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.DATE, linkRelativeCycle * i - 1);
					Date tempMaxDate = cal.getTime();

					linkRelative.setMinDate(dateFormat.format(tempMinDate));
					linkRelative.setMaxDate(dateFormat.format(tempMaxDate));
					// 获取环比周期的时间
					LinkRelative beforeLinkRelative = list.get(i - 2);
					linkRelative.setBeforeMinDate(beforeLinkRelative.getMinDate());
					linkRelative.setBeforeMaxDate(beforeLinkRelative.getMaxDate());
					list.add(linkRelative);
				}
			}
		}
		return list;
	}
}
