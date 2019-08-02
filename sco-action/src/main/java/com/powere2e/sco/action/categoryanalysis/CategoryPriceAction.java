package com.powere2e.sco.action.categoryanalysis;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.WorkAction;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.categoryanalysis.CategoryPriceService;
import com.powere2e.sco.model.categoryanalysis.CategoryPrice;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;
import com.powere2e.security.model.Option;

/**
 * 商品价格带 WEB请求公用模块
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月28日
 */
public class CategoryPriceAction extends WorkAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7078949000869365560L;
	protected CategoryPriceService categoryPriceService;
	
	@Override
	protected void beforeBuild() {
		categoryPriceService = (CategoryPriceService) ConfigFactory
				.getInstance().getBean("categoryPriceService");
	}

	/**
	 * 查询所有商品价格带数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	public void doListCollectData() throws Exception {
		// 查询参数
		String handSetPri = this.asString("handSetPri");
		List<Option> list = new ArrayList<Option>();
		Map<String, Object> map = this.getCategoryPriceMap(handSetPri, list);
		
//		System.out.println(new Date().toLocaleString());
		List<CategoryPrice> dataList = this.listAllCatData(map, handSetPri, list, this.getPageInfo(), true);
//		System.out.println(new Date().toLocaleString());
		
		this.forwardData(true, dataList, null);
	}
	
	/**
	 * 查看明细数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	public void doListDetailData() throws Exception {
		String handSetPri = this.asString("handSetPri");
		List<Option> list = new ArrayList<Option>();
		Map<String, Object> map = this.getCategoryPriceMap(handSetPri, list);
		
		List<CategoryPrice> dataList =  this.listDetData(map, handSetPri, true, list, this.getPageInfo());
		this.forwardData(true, dataList, null);
	}

	/**
	 * 所有商品价格带datagrid上的统计数据
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	public void doListCalculateTotal() throws Exception {
		// 手动设置价格带
		String handSetPri = this.asString("handSetPri");
		List<Option> list = new ArrayList<Option>();
		// 查询参数
		Map<String, Object> map = this.getCategoryPriceMap(handSetPri, list);
		String data = this.totalAllCatData(map, handSetPri, list, true);
		this.forwardData(true, data, null);
	}

	/**
	 * 所有价格带导出到Excel
	 * 
	 * @param merTitle
	 *            商品范围
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	@SuppressWarnings("unchecked")
	public void doExportAllCategoryToExcel(String fileName, String merTitle) throws Exception {
		Map<String, Object> resMap = this.generateAllExportAndSavePara();
		//4.生成excel
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		
		String tol1 =  (String) resMap.get("tol1");
		List<CategoryPrice> data1 = (List<CategoryPrice>) resMap.get("data1");
		String tol2 =  (String) resMap.get("tol2");
		List<CategoryPrice> data2 = (List<CategoryPrice>) resMap.get("data2");
		Map<String, Object> pubMap = (Map<String, Object>) resMap.get("pubMap");
		pubMap.put("merTitle", merTitle);
		this.categoryPriceService.exportAllCategoryToExcel(tol1, data1, tol2, data2, pubMap, out);
	}

	/**
	 * 保存所有价格带报表
	 * 
	 * @param merTitle
	 *            商品范围
	 * @param chartTitle
	 *            图表标题
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	@SuppressWarnings("unchecked")
	public void doSaveAllCategory(String reportType, String dirName,
			String merTitle, String chartTitle) throws Exception {
		String fileName = this.asString("fileName");
		if (this.ifFileNameExists(reportType, fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		Map<String, Object> paraMap = this.generateAllExportAndSavePara();
		Map<String, Object> pubMap = (Map<String, Object>) paraMap
				.get("pubMap");
		pubMap.put("merTitle", merTitle);
		String msg = this.categoryPriceService.completeSaveAllCategory(fileName,
				dirName, reportType, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
	
	/**
	 * 生成所有价格带导出、保存参数
	 * 
	 * @return
	 * @throws Exception
	 */
	protected Map<String, Object> generateAllExportAndSavePara() throws Exception {
		// 查询参数
		String handSetPri = this.asString("handSetPri");
		List<Option> list2 = new ArrayList<Option>();
		Map<String, Object> map = this.getCategoryPriceMap(handSetPri, list2);
		
		List<Option> list1 = new ArrayList<Option>();
		list1.addAll(list2);//list2在查询时会可能新增一个价格带
		
		List<CategoryPrice> data2 = null;
		String tol2 = null;
		Map<String, Object> resMap = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(this.asString("startYear"))) {
			//2.第二个datagrid数据(第二个sheet)
			data2 = this.listAllCatData(map, handSetPri, list2, null, true);
			tol2 = this.totalAllCatData(map, handSetPri, list2, true);
			resMap.put("data2Json", JSONArray.fromObject(data2));
			resMap.put("flag2", true);//是否显示第二个grid
		}

		//3.第一个datagrid数据(第一个sheet)
		String firSYear1 = this.asString("firSYear1");
		Integer firSMonth1 = this.asInteger("firSMonth1");
		map.put("dateRegionStr", firSYear1+ "-" + StrUtils.formatStrLength(firSMonth1, 2));

		Date startDate = DateUtils.concatDate(firSYear1, firSMonth1); 
		map.put("sellMinDate", startDate);
		//结束日期
		String endYear1 = this.asString("endYear1");
		Integer endMonth1 = this.asInteger("endMonth1");
		map.put("dateRegionStr2", endYear1+ "-" + StrUtils.formatStrLength(endMonth1, 2));

		Date endDate = DateUtils.concatDate(endYear1, endMonth1); 
		map.put("sellMaxDate", endDate);

		List<CategoryPrice> data1 = this.listAllCatData(map, handSetPri, list1, null, false);
		String tol1 = this.totalAllCatData(map, handSetPri, list1, false);
		resMap.put("data1Json", JSONArray.fromObject(data1));
		resMap.put("flag1", true);//是否显示第一个grid
		
		resMap.put("tol1", tol1);
		resMap.put("data1", data1);
		resMap.put("tol2", tol2);
		resMap.put("data2", data2);
		resMap.put("pubMap", this.splitCalculate(tol1, tol2));	
		return resMap;
	}

	//保存报表时将统计数据先切割后传入
	private Map<String, Object> splitCalculate(String tol1, String tol2) {
		//1.公共部分(统计条件)
		Map<String, Object> pubMap = this.getExcelPublicPara();
		String [] part1 = new String[] {"", "", "", ""};
		if (tol1.length() > 4 ) {
			part1 = tol1.split("\\|");
		}
		pubMap.put("part10", part1[0]);
		pubMap.put("part11", part1[1]);
		pubMap.put("part12", part1[2]);
		pubMap.put("part13", part1[3]);

		String [] part2 = new String[] {"", "", "", ""};
		if (tol2 != null && tol2.length() > 4 ) {
			part2 = tol2.split("\\|");
		}
		pubMap.put("part20", part2[0]);
		pubMap.put("part21", part2[1]);
		pubMap.put("part22", part2[2]);
		pubMap.put("part23", part2[3]);
		
		return pubMap;
	}
	
	/**
	 * 导出明细数据到Excel
	 * 
	 * @param merTitle
	 *            商品范围
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	@SuppressWarnings("unchecked")
	public void doExportDetCategoryToExcel(String fileName, String merTitle) throws Exception {
		Map<String, Object> resMap = this.generateDetExportAndSavePara();
		// 4.生成excel
		ServletOutputStream out = response.getOutputStream();
		response.setHeader("Content-Disposition", "attachment; filename=\""
				+ new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1),
						Constant.DEFAULT_ENCODED_2) + "\"");
		List<CategoryPrice> data1 = (List<CategoryPrice>) resMap.get("data1");
		List<CategoryPrice> data2 = (List<CategoryPrice>) resMap.get("data2");
		Map<String, Object> pubMap = (Map<String, Object>) resMap.get("pubMap");
		pubMap.put("merTitle", merTitle);
		this.categoryPriceService.exportDetCategoryToExcel(data1,
				data2, pubMap, out);
	}
	
	/**
	 * 保存明细报表
	 * 
	 * @param reportType
	 *            报表类型
	 * @param merTitle
	 *            商品范围
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco.categoryanalysis")
	@SuppressWarnings("unchecked")
	public void doSaveDetCategory(String reportType, String dirName, String merTitle) throws Exception {
		String fileName = this.asString("fileName");
		if (this.ifFileNameExists(reportType, fileName)) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		Map<String, Object> paraMap = this.generateDetExportAndSavePara();
		Map<String, Object> pubMap = (Map<String, Object>) paraMap.get("pubMap");
		pubMap.put("merTitle", merTitle);
		String msg = this.categoryPriceService.completeSaveDetCategory(fileName,
				dirName, reportType, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}
	
	/**
	 * 生成明细导出或保存所需数据
	 * 
	 * @return 所需数据
	 * @throws Exception
	 */
	protected Map<String, Object> generateDetExportAndSavePara() throws Exception {
		// 查询参数
		String handSetPri = this.asString("handSetPri");
		List<Option> list = new ArrayList<Option>();
		Map<String, Object> map = this.getCategoryPriceMap(handSetPri, list);
		// 1.公共部分(统计条件)
		Map<String, Object> pubMap = this.getExcelPublicPara();
		pubMap.put("module", this.asString("module"));

		List<CategoryPrice> data2 = null;
		if (StringUtils.isNotBlank(this.asString("startYear"))) {
			// 2.第二个datagrid数据(第二个sheet)
			data2 = this.listDetData(map, handSetPri, true, list, null);
		}

		// 3.第一个datagrid数据(第一个sheet)
		String firSYear1 = this.asString("firSYear1");
		Integer firSMonth1 = this.asInteger("firSMonth1");
		map.put("dateRegionStr", firSYear1+ "-" + StrUtils.formatStrLength(firSMonth1, 2));

		Date startDate = DateUtils.concatDate(firSYear1, firSMonth1);
		map.put("sellMinDate", startDate);
		
		// 结束日期
		String endYear1 = this.asString("endYear1");
		Integer endMonth1 = this.asInteger("endMonth1");
		map.put("dateRegionStr2", endYear1+ "-" + StrUtils.formatStrLength(endMonth1, 2));

		Date endDate = DateUtils.concatDate(endYear1, endMonth1);
		map.put("sellMaxDate", endDate);
		map.put("days", DateUtils.daysBetweenDate(startDate, endDate));//第一个区间的天数
		
		List<CategoryPrice> data1 = this.listDetData(map, handSetPri, false, list, null);
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("data1", data1);
		resMap.put("flag1", true);//是否显示第一个grid
		resMap.put("data2", data2);
		if (data2 != null) {
			resMap.put("flag2", true);//是否显示第二个grid
		}
		resMap.put("pubMap", pubMap);
		return resMap;
	}
	
	/**
	 * 校验文件名称
	 * 
	 * @param reportType
	 *            报表类型
	 * 
	 * @param fileName
	 *            文件名称
	 * @return 是否存在
	 */
	protected boolean ifFileNameExists(String reportType, String fileName) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", reportType);//报表类型
		map.put("reportsName", fileName);//报表名称
		//校验文件名称
		return ReportsServiceImpl.getInstance().ifFileNameExists(map);
	}
	
	/**
	 * 获取公共参数
	 * 
	 * @return 统计公共数据
	 */
	private Map<String, Object> getExcelPublicPara() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("regionName", this.asString("regionName"));
		map.put("netWeightTypeCon", this.asString("netWeightTypeCon"));
		map.put("netWeightType", this.asString("netWeightType"));//定量还是非定量
		map.put("directCon", this.asString("directCon"));
		map.put("priceSetCon", this.asString("priceSetCon"));
		//第一个日期区间
		String startDate1 = this.asString("firSYear1") + "-"
				+ StrUtils.formatStrLength(this.asInteger("firSMonth1"), 2);
		String endDate1 = this.asString("endYear1") + "-" 
				+ StrUtils.formatStrLength(this.asInteger("endMonth1"), 2);
		map.put("searchDate1", startDate1 + "~" + endDate1);
		//第二个日期区间
		String startDate2 = this.asString("startYear") + "-"
				+ StrUtils.formatStrLength(this.asInteger("startMonth"), 2);
		String endDate2 = this.asString("endYear") + "-" 
				+ StrUtils.formatStrLength(this.asInteger("endMonth"), 2);
		map.put("searchDate2", startDate2 + "~" + endDate2);
		
		return map;
	}
	
	/**
	 * 获取前台参数
	 * 
	 * @return
	 * @throws Exception
	 */
	protected CategoryPrice getCategoryPriceMap() throws Exception {
		CategoryPrice allCategoryPrice = new CategoryPrice();
		this.asBean(allCategoryPrice);
		return allCategoryPrice;
	}
	
	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @param handSetPri
	 *            是否手动设置价格带
	 * @param list
	 *            用于存储价格带
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Map<String, Object> getCategoryPriceMap(String handSetPri,
			List<Option> list) throws Exception {
		CategoryPrice allCategoryPrice = this.getCategoryPriceMap();
	
		String startYear = this.asString("startYear");
		Date startDate = null;
		Date endDate = null;
		if(StringUtils.isNotBlank(startYear)) {
			//开始日期
			Integer startMonth = this.asInteger("startMonth");
			allCategoryPrice.setDateRegionStr(startYear+ "-" + StrUtils.formatStrLength(startMonth, 2));

			startDate = DateUtils.concatDate(startYear, startMonth); 
			allCategoryPrice.setSellMinDate(startDate);
			//结束日期
			String endYear = this.asString("endYear");
			Integer endMonth = this.asInteger("endMonth");
			allCategoryPrice.setDateRegionStr2(endYear+ "-" + StrUtils.formatStrLength(endMonth, 2));
			
			endDate = DateUtils.concatDate(endYear, endMonth); 
			allCategoryPrice.setSellMaxDate(endDate);
					
		}
		Map<String, Object> map = allCategoryPrice.toMap();
		if(StringUtils.isNotBlank(startYear)) {
			map.put("days", DateUtils.daysBetweenDate(startDate, endDate));
		}
		
		//手动价格带
		if (StringUtils.isNotBlank(handSetPri)) {
			String minPriceArr = this.asString("minPrice");
			String maxPriceArr = this.asString("maxPrice");
			String addPriceArr = this.asString("addPrice");
			
			if (minPriceArr != null && minPriceArr.length() > 0) {
				String[] min = minPriceArr.split(",");
				String[] max = maxPriceArr.split(",");
				String[] add = addPriceArr.split(",");
				for (int i = 0; i < min.length; i++) {
					this.generatePriceRegion(min[i], max[i], add[i], list);
				}
			}
		}
		String selectedData = this.asString("selectedData");
		if (StringUtils.isNotBlank(selectedData)) {
			map.put("selectedData", selectedData);
		}
		return map;
	}

	/**
	 * 价格区间
	 * 
	 * @param startPrice
	 *            开始价格
	 * @param endPrice
	 *            结束价格
	 * @param added
	 *            增长步长
	 * @param list
	 *            价格区间
	 */
	private void generatePriceRegion(String startPrice, String endPrice, String added, List<Option> list) {
		Double end = Double.valueOf(endPrice);
		Double add = Double.valueOf(added);
		if(startPrice == null || endPrice == null || end == 0 || added == null || add == 0) {
			return ; 
		}
		Double start = Double.valueOf(startPrice);
		double count = (end - start) / add;
		for (int i = 0; i < count; i++) {
			start = Double.valueOf(startPrice);
			String tmp = String.valueOf(start + add);
			list.add(new Option(startPrice, tmp));
			startPrice = tmp;
		}
		//自动价格带时处理[价格带是大于等于、小于最大价格带，自动时就可能查不到数据]
		if (StringUtils.isBlank(this.asString("handSetPri"))) {
			Double last = Double.valueOf(startPrice);
			if (last.compareTo(end)  == 0) {
				list.add(new Option(startPrice, last + add + ""));
			}
		}
	}
	
	/**
	 * 根据第一个时间区间和第二个时间区间的最大价格大小来控制第二个时间区间是否需要新增一个区间
	 * 
	 * @param paraMap
	 *            参数参数
	 * @param secMaxPrice
	 *            第二个表格最大价格
	 * @param list
	 *            价格区间
	 */
	private void addFirstPriceRegion(Map<String, Object> paraMap, String secMaxPrice, List<Option> list) {
		String startYear = this.asString("firSYear1");
		if (StringUtils.isNotBlank(startYear)) {
			this.setFirstSearchDate(paraMap);
			String firstPriceRegion = this.categoryPriceService.listPriceRegionDate(paraMap);

			if (!StringUtils.isBlank(firstPriceRegion)) {
				String firMaxPrice = firstPriceRegion.split("\\|")[1];
				this.addPriceRegion(firMaxPrice, secMaxPrice, list);
			}
		}
	}

	/**
	 * 将第一个日期内最高价格的商品添加到第二个日期内的价格带中
	 * 
	 * @param firMaxPrice
	 *            第一个日期内最高价格
	 * @param secMaxPrice
	 *            第二个日期内最高价格
	 * @param list
	 *            价格带
	 */
	private void addPriceRegion(String firMaxPrice, String secMaxPrice, List<Option> list) {
		if (Double.valueOf(firMaxPrice) != 0 && Double.valueOf(secMaxPrice) > Double.valueOf(firMaxPrice)) {
			list.add(new Option(firMaxPrice, null));
		}
	}
	
	/**
	 * 设置第一个查询时间
	 * 
	 * @param paraMap
	 *            查询参数
	 */
	private void setFirstSearchDate(Map<String, Object> paraMap) {
		//开始日期
		Date startDate = DateUtils.concatDate(this.asString("firSYear1"), this.asInteger("firSMonth1"));
		//结束日期
		Date endDate = DateUtils.concatDate(this.asString("firEYear1"), this.asInteger("firEMonth1"));
		//查询第一个datagrid的价格
		paraMap.put("sellMinDate", startDate);
		paraMap.put("sellMaxDate", endDate);
	}
	
	/**
	 * excel名称中所需的第一个日期
	 * 
	 * @return
	 */
	public String getExcelDate1Name() {
		return this.asString("firSYear1") + StrUtils.formatStrLength(this.asInteger("firSMonth1"), 2)
				+ "~" + this.asString("firEYear1") + StrUtils.formatStrLength(this.asInteger("firEMonth1"), 2);
	}
	
	/**
	 * 查询所有商品价格带数据
	 * 
	 * @param map
	 *            查询参数
	 * @param handSetPri
	 *            手动设置价格带
	 * @param list
	 *            用于存储价格带
	 * @param pageInfo
	 *            分页参数
	 * @param flag
	 *            用来控制第二个表格数据的查询
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<CategoryPrice> listAllCatData(Map<String, Object> map,
			String handSetPri, List<Option> list, PageInfo pageInfo, Boolean flag) throws Exception {

		List<CategoryPrice> dataList = new ArrayList<CategoryPrice>();
		Map<String, Object> paraMap = new HashMap<String, Object>();
		// 自动计算价格带
		if (StringUtils.isBlank(handSetPri)) {
			dataList = (List<CategoryPrice>) this.searchAllDataByConditonWithAuto(map, paraMap, flag, list, pageInfo, dataList, null)
					.get("dataList");
		} else { // 手动设置
			dataList = (List<CategoryPrice>) this.searchAllDataByConditonWithHand(map, paraMap, flag, list, pageInfo, dataList, null)
					.get("dataList");
		}
		return dataList;
	}

	/**
	 * 所有商品价格带datagrid上的统计数据
	 * 
	 * @param flag
	 *            用来控制第二个表格数据的查询
	 * @param changeDate
	 *            excel导出时更改日期
	 * @return 统计结果
	 * @throws Exception
	 */
	private String totalAllCatData(Map<String, Object> map, String handSetPri, List<Option> list, Boolean flag) throws Exception {
		Map<String, Object> paraMap = new HashMap<String, Object>();// 用户查询商品价格最大值
		String data = "";
		if (StringUtils.isBlank(handSetPri)) {// 自动计算价格带
			data = (String) this.searchAllDataByConditonWithAuto(map, paraMap, flag, list, null, null, data).get("data");
		} else { // 手动设置
			data = (String) this.searchAllDataByConditonWithHand(map, paraMap, flag, list, null, null, data).get("data");
		}
		return data;
	}
	
	/**
	 * 所有商品根据不同条件进行查询(自动设置价格带)
	 * 
	 * @param map
	 *            页面参数条件
	 * @param paraMap
	 *            用于查询第二个grid在使用第一个价格带时的参数
	 * @param flag
	 *            由于导出excel时第一个日期和第二个日期都存在，故还需根据此条件来判断到底目前查询时第几个grid的数据
	 * @param list
	 *            用户存储价格带
	 * @param pageInfo
	 *            分页参数
	 * @param dataList
	 *            用户存储查询的数据
	 * @param data
	 *            用于存储统计数据
	 * @return Map[dataList、data]
	 */
	private Map<String, Object> searchAllDataByConditonWithAuto(Map<String, Object> map,
			Map<String, Object> paraMap, boolean flag, List<Option> list,
			PageInfo pageInfo, List<CategoryPrice> dataList, String data) {
		String firRegion = null;
		String startYear = this.asString("firSYear1");
		if (StringUtils.isNotBlank(startYear) && flag) {//如果是第二个表格需要使用第一个时间段去查询价格
			paraMap.putAll(map);
			this.setFirstSearchDate(paraMap);
			firRegion = this.categoryPriceService.listPriceRegionDate(paraMap);
		}
		String curRegion = this.categoryPriceService.listPriceRegionDate(map);// 为了查出最大价格

		if (StringUtils.isNotBlank(curRegion)) {
			// 自动计算出价格带
			String[] priceRegion = (firRegion == null ? curRegion.split("\\|")
					: firRegion.split("\\|"));
			this.generatePriceRegion(priceRegion[0], priceRegion[1], priceRegion[2], list);
			if (!list.isEmpty()) {
				// 若是第二个grid需判断商品价格最大值是否超过第一个grid中的值
				if (firRegion != null) {
					String[] curPrice = curRegion.split("\\|");
					this.addPriceRegion(priceRegion[1], curPrice[1], list);
				}
				map.put("list", list);
				if (data == null) {//表示当前查询的是数据
					dataList = this.categoryPriceService.listCollectData(map, pageInfo);
				} else {//查询统计
					data = this.categoryPriceService.listCalculateTotal(map);
				}
			}
		}
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("data", data);//查询统计
		resMap.put("dataList", dataList);//表示当前查询的是数据
		return resMap;
	}
	
	/**
	 * 所有商品根据不同条件进行查询(手动设置价格带)
	 * 
	 * @param map
	 *            页面参数条件
	 * @param paraMap
	 *            用于查询第二个grid在使用第一个价格带时的参数
	 * @param flag
	 *            由于导出excel时第一个日期和第二个日期都存在，故还需根据此条件来判断到底目前查询时第几个grid的数据
	 * @param list
	 *            用户存储价格带
	 * @param pageInfo
	 *            分页参数
	 * @param dataList
	 *            用户存储查询的数据
	 * @param data
	 *            用于存储统计数据
	 * @return Map[dataList、data]
	 */
	private Map<String, Object> searchAllDataByConditonWithHand(Map<String, Object> map,
			Map<String, Object> paraMap, boolean flag, List<Option> list,
			PageInfo pageInfo, List<CategoryPrice> dataList, String data) {
		map.put("list", list);
		map.put("handSetPri", "1");
//		map.put("limitMaxPrice", this.asString("limitMaxPrice"));// 最大价格限制
//		map.put("limitMaxlimitMinPricePrice", this.asString("limitMinPrice"));// 最小价格限制
		String startYear = this.asString("firSYear1");// 是否为第二个Grid
		if (StringUtils.isNotBlank(startYear) && flag) {
			paraMap.putAll(map);
			String secPriceRegion = this.categoryPriceService.listPriceRegionDate(paraMap);
			if (StringUtils.isNotBlank(secPriceRegion)) {
				this.addFirstPriceRegion(paraMap, secPriceRegion.split("\\|")[1], list);
			}
		}
		if (!list.isEmpty()) {
			if (data == null) {//查询数据
				dataList = this.categoryPriceService.listCollectData(map, pageInfo);
			} else { // 查询统计
				data = this.categoryPriceService.listCalculateTotal(map);
			}
		}
		
		Map<String, Object> resMap = new HashMap<String, Object>();
		resMap.put("data", data);
		resMap.put("dataList", dataList);
		return resMap;
	}
	
	/**
	 * 明细数据
	 * 
	 * @param map
	 *            查询参数
	 * @param handSetPri
	 *            手动设置价格带
	 * @param list
	 *            用于存储价格带
	 * @param pageInfo
	 *            分页参数
	 * @param flag
	 *            用来控制第二个表格数据的查询
	 * @return
	 * @throws Exception
	 */
	private List<CategoryPrice> listDetData(Map<String, Object> map,
			String handSetPri, boolean flag, List<Option> list, PageInfo pageInfo) throws Exception {
		List<CategoryPrice> dataList = new ArrayList<CategoryPrice>();
		
		map.put("list", list);
		Map<String, Object> paraMap = this.changeParaMapValue(map);
		String startYear = this.asString("firSYear1");
		
		// 查询参数
		if (StringUtils.isBlank(handSetPri)) {		// 自动设置价格带
			// -------第二个表格的价格带还是用第一个表格的---------------------------
			if (StringUtils.isNotBlank(startYear) && flag) {
				this.setFirstSearchDate(paraMap);
			}
			String str = this.categoryPriceService.listPriceRegionDate(paraMap);
			if (StringUtils.isNotBlank(str)) {
				String[] priceRegion = str.split("\\|");// 自动计算出价格带
				this.generatePriceRegion(priceRegion[0], priceRegion[1],
						priceRegion[2], list);
				map.put("MAXREGIONPRICE", priceRegion[1]);//查询明细数据时由于商品平均价格可能不在价格带中，需要额外添加
			}
		} else {									//手动设置价格带
			map.put("handSetPri", "1");
			paraMap.put("handSetPri", "1");
			// -------第二个表格的价格带还是用第一个表格的---------------------------
			if (StringUtils.isNotBlank(startYear) && flag) {
				this.setFirstSearchDate(paraMap);
			}
			String str = this.categoryPriceService.listPriceRegionDate(paraMap);
			if (StringUtils.isNotBlank(str)) {
				String[] priceRegion = str.split("\\|");// 自动计算出价格带
				map.put("MAXREGIONPRICE", priceRegion[1]);//查询明细数据时由于商品平均价格可能不在价格带中，需要额外添加
			}
		}
		
		if (!list.isEmpty()) {
			dataList = this.categoryPriceService.listDetailData(map,
					pageInfo);
		}
		return dataList;
	}

	/**
	 * 查询明细时只看某个商品所占的比例(查看明细数据时不需要过滤，故查询价格带时不能只算当前的商品，故需要去掉)
	 * 
	 * @param map
	 *            查询参数
	 * @return 查询
	 */
	private Map<String, Object> changeParaMapValue(Map<String, Object> map) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.putAll(map);
		paraMap.remove("merchandiseCode");
		paraMap.remove("merchandiseName");
		return paraMap;
	}
	
}
