package com.powere2e.sco.action.suppliercomprehensiveanalysis;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisService;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysis;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisForm;
import com.powere2e.sco.model.suppliercomprehensiveanalysis.SupplierComprehensiveAnalysisSet;
import com.powere2e.sco.service.impl.reports.ReportsServiceImpl;

/**
 * 辅料意向品的WEB请求响应类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */

/**
 * 辅料 —竞价委员会申请
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public class SupplierComprehensiveAnalysisAction extends UploadUtils {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 7467505029752175810L;

	private SupplierComprehensiveAnalysisService supplierComprehensiveAnalysisService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		supplierComprehensiveAnalysisService = (SupplierComprehensiveAnalysisService) ConfigFactory.getInstance().getBean("supplierComprehensiveAnalysisService");
	}

	/**
	 * 供应商综合分析grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowSupplierComprehensiveAnalysisGrid() throws Exception {

		this.forwardPage("sco/shareFunctionAnalysis/supplierComprehensiveAnalysis/supplierComprehensiveAnalysisGrid.ftl");
	}

	/**
	 * 显示综合分析设置
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowSupplierComprehensiveAnalysisSet() throws Exception {

		this.forwardPage("sco/shareFunctionAnalysis/supplierComprehensiveAnalysis/supplierComprehensiveAnalysisSetForm.ftl");
	}

	/**
	 * 显示总成本分析
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowSupplierComprehensiveAnalysis() throws Exception {
		SupplierComprehensiveAnalysisSet supplierComprehensiveAnalysisSet = new SupplierComprehensiveAnalysisSet();
		String rows = this.asString("rows");
		String supplierCode = "";
		JSONArray jsonArr = JSONArray.fromObject(rows);
		SupplierComprehensiveAnalysis[] dataArray = new SupplierComprehensiveAnalysis[jsonArr.size()];
		for (int j = 0; j < jsonArr.size(); j++) {
			dataArray[j] = (SupplierComprehensiveAnalysis) JSONObject.toBean(jsonArr.getJSONObject(j), SupplierComprehensiveAnalysis.class);
		}
		for (int i = 0; i < dataArray.length; i++) {
			if (i == dataArray.length - 1) {
				supplierCode = supplierCode + "v" + dataArray[i].getSupplierCode() + "v";
			} else {
				supplierCode = supplierCode + "v" + dataArray[i].getSupplierCode() + "v@";
			}
		}
		String minDate = this.asString("minDate");
		String maxDate = this.asString("maxDate");
		String smallTypeCode = this.asString("smallTypeCode");
		String detailTypeCode = this.asString("detailTypeCode");
		Integer years = Integer.parseInt(this.asString("years"));
		String xsbx = this.asString("xsbx");
		String zlqk = this.asString("zlqk");
		String jhqk = this.asString("jhqk");
		String shbx = this.asString("shbx");
		String xpphqk = this.asString("xpphqk");
		if (xsbx.indexOf("销售量") > -1) {
			supplierComprehensiveAnalysisSet.setXsl("Y");
		}
		if (xsbx.indexOf("销售金额") > -1) {
			supplierComprehensiveAnalysisSet.setXsje("Y");
		}
		if (xsbx.indexOf("毛利额") > -1) {
			supplierComprehensiveAnalysisSet.setMle("Y");
		}
		if (xsbx.indexOf("销售量占比(占所有商品)") > -1) {
			supplierComprehensiveAnalysisSet.setXslzball("Y");
		}
		if (xsbx.indexOf("销售金额占比(占所有商品)") > -1) {
			supplierComprehensiveAnalysisSet.setXsjezball("Y");
		}
		if (xsbx.indexOf("毛利额占比(占所有商品)") > -1) {
			supplierComprehensiveAnalysisSet.setMlezball("Y");
		}
		if (xsbx.indexOf("销售量占比(占小分类)") > -1) {
			supplierComprehensiveAnalysisSet.setXslzbxfl("Y");
		}
		if (xsbx.indexOf("销售金额占比(占小分类)") > -1) {
			supplierComprehensiveAnalysisSet.setXsjezbxfl("Y");
		}
		if (xsbx.indexOf("毛利额占比(占小分类)") > -1) {
			supplierComprehensiveAnalysisSet.setMlezbxfl("Y");
		}
		if (xsbx.indexOf("销售量占比(占明细类)") > -1) {
			supplierComprehensiveAnalysisSet.setXslzbmxl("Y");
		}
		if (xsbx.indexOf("销售金额占比(占明细类)") > -1) {
			supplierComprehensiveAnalysisSet.setXsjezbmxl("Y");
		}
		if (xsbx.indexOf("毛利额占比(占明细类)") > -1) {
			supplierComprehensiveAnalysisSet.setMlezbmxl("Y");
		}
		if (xsbx.indexOf("PSD销量") > -1) {
			supplierComprehensiveAnalysisSet.setPsdxl("Y");
		}
		if (xsbx.indexOf("PSD销售金额") > -1) {
			supplierComprehensiveAnalysisSet.setPsdxsje("Y");
		}
		if (xsbx.indexOf("PSD毛利") > -1) {
			supplierComprehensiveAnalysisSet.setPsdml("Y");
		}
		if (xsbx.indexOf("权限数") > -1) {
			supplierComprehensiveAnalysisSet.setQxs("Y");
		}
		if (xsbx.indexOf("权限店天") > -1) {
			supplierComprehensiveAnalysisSet.setQxdt("Y");
		}
		if (xsbx.indexOf("销售店天") > -1) {
			supplierComprehensiveAnalysisSet.setXsdt("Y");
		}
		if (xsbx.indexOf("活跃度") > -1) {
			supplierComprehensiveAnalysisSet.setHyd("Y");
		}
		if (xsbx.indexOf("ABCD门店数") > -1) {
			supplierComprehensiveAnalysisSet.setAbcdmds("Y");
		}
		if (jhqk.indexOf("进货量") > -1) {
			supplierComprehensiveAnalysisSet.setJhl("Y");
		}
		if (jhqk.indexOf("进货额") > -1) {
			supplierComprehensiveAnalysisSet.setJhe("Y");
		}
		if (shbx.indexOf("订单及时率") > -1) {
			supplierComprehensiveAnalysisSet.setDdjsl("Y");
		}
		if (shbx.indexOf("订单满足率") > -1) {
			supplierComprehensiveAnalysisSet.setDdmzl("Y");
		}
		if (shbx.indexOf("让步接收数量") > -1) {
			supplierComprehensiveAnalysisSet.setRbjssl("Y");
		}
		if (shbx.indexOf("让步接收次数") > -1) {
			supplierComprehensiveAnalysisSet.setRbjscs("Y");
		}
		if (shbx.indexOf("供应商实际库存") > -1) {
			supplierComprehensiveAnalysisSet.setGyssjkc("Y");
		}
		if (shbx.indexOf("供应商安全库存") > -1) {
			supplierComprehensiveAnalysisSet.setGysaqkc("Y");
		}
		if (zlqk.indexOf("质量星级") > -1) {
			supplierComprehensiveAnalysisSet.setZlxj("Y");
		}
		if (zlqk.indexOf("召回次数") > -1) {
			supplierComprehensiveAnalysisSet.setZhcs("Y");
		}
		/*if (zlqk.indexOf("召回数量") > -1) {
			supplierComprehensiveAnalysisSet.setZhsl("Y");
		}*/
		if (zlqk.indexOf("召回SKU个数") > -1) {
			supplierComprehensiveAnalysisSet.setZhskugs("Y");
		}
		if (zlqk.indexOf("抽检不合格次数") > -1) {
			supplierComprehensiveAnalysisSet.setCjbhgcs("Y");
		}
		if (zlqk.indexOf("抽检不合格SKU数") > -1) {
			supplierComprehensiveAnalysisSet.setCjbhgskus("Y");
		}
		/*if (zlqk.indexOf("入厂合格率") > -1) {
			supplierComprehensiveAnalysisSet.setRchgl("Y");
		}*/
		if (zlqk.indexOf("供应商年度千万元客诉") > -1) {
			supplierComprehensiveAnalysisSet.setGysndqwyks("Y");
		}
		if (zlqk.indexOf("供应商巡厂评分") > -1) {
			supplierComprehensiveAnalysisSet.setGysxcpf("Y");
		}
		if (zlqk.indexOf("供应商考评得分") > -1) {
			supplierComprehensiveAnalysisSet.setGyskpdf("Y");
		}
		if (zlqk.indexOf("供应商考评得分排行") > -1) {
			supplierComprehensiveAnalysisSet.setGyskpdfph("Y");
		}
		if (xpphqk.indexOf("供应商意向品数") > -1) {
			supplierComprehensiveAnalysisSet.setGysyxps("Y");
		}

		Integer lastYear = Integer.parseInt(maxDate.substring(0, 4));
		supplierComprehensiveAnalysisSet.setSupplierCode(supplierCode);
		supplierComprehensiveAnalysisSet.setMinDate(minDate);
		supplierComprehensiveAnalysisSet.setMaxDate(maxDate);
		supplierComprehensiveAnalysisSet.setSmallTypeCode(smallTypeCode);
		supplierComprehensiveAnalysisSet.setDetailTypeCode(detailTypeCode);
		supplierComprehensiveAnalysisSet.setYears(years);
		supplierComprehensiveAnalysisSet.setLastYear(lastYear);
		supplierComprehensiveAnalysisSet.setXsbx(xsbx);
		supplierComprehensiveAnalysisSet.setZlqk(zlqk);
		supplierComprehensiveAnalysisSet.setJhqk(jhqk);
		supplierComprehensiveAnalysisSet.setShbx(shbx);
		supplierComprehensiveAnalysisSet.setXpphqk(xpphqk);
		for (int i = 0; i < years; i++) {
			Integer minYear = Integer.parseInt(minDate.substring(0, 4));
			Integer maxYear = Integer.parseInt(maxDate.substring(0, 4));
			String minMounth = minDate.substring(4);
			String maxMounth = maxDate.substring(4);
			if (i == 0) {
				supplierComprehensiveAnalysisSet.setMinDate1((minYear - 1) + minMounth);
				supplierComprehensiveAnalysisSet.setMaxDate1((maxYear - 1) + maxMounth);
				supplierComprehensiveAnalysisSet.setLastYear1(lastYear - 1);
			}
			if (i == 1) {
				supplierComprehensiveAnalysisSet.setMinDate2((minYear - 2) + minMounth);
				supplierComprehensiveAnalysisSet.setMaxDate2((maxYear - 2) + maxMounth);
				supplierComprehensiveAnalysisSet.setLastYear2(lastYear - 2);
			}
			if (i == 2) {
				supplierComprehensiveAnalysisSet.setMinDate3((minYear - 3) + minMounth);
				supplierComprehensiveAnalysisSet.setMaxDate3((maxYear - 3) + maxMounth);
				supplierComprehensiveAnalysisSet.setLastYear3(lastYear - 3);
			}

		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplierCode", supplierComprehensiveAnalysisSet.getSupplierCode().replaceAll("v", "\'").replaceAll("@", ","));
		map.put("smallTypeCode", supplierComprehensiveAnalysisSet.getSmallTypeCode());
		map.put("detailTypeCode", supplierComprehensiveAnalysisSet.getDetailTypeCode());
		map.put("minDate", supplierComprehensiveAnalysisSet.getMinDate());
		map.put("maxDate", supplierComprehensiveAnalysisSet.getMaxDate());
		map.put("minDate1", supplierComprehensiveAnalysisSet.getMinDate1());
		map.put("maxDate1", supplierComprehensiveAnalysisSet.getMaxDate1());
		map.put("minDate2", supplierComprehensiveAnalysisSet.getMinDate2());
		map.put("maxDate2", supplierComprehensiveAnalysisSet.getMaxDate2());
		map.put("minDate3", supplierComprehensiveAnalysisSet.getMinDate3());
		map.put("maxDate3", supplierComprehensiveAnalysisSet.getMaxDate3());
		map.put("lastYear", supplierComprehensiveAnalysisSet.getLastYear());
		map.put("lastYear1", supplierComprehensiveAnalysisSet.getLastYear1());
		map.put("lastYear2", supplierComprehensiveAnalysisSet.getLastYear2());
		map.put("lastYear3", supplierComprehensiveAnalysisSet.getLastYear3());

		List<SupplierComprehensiveAnalysisForm> list = supplierComprehensiveAnalysisService.listSupplierComprehensiveAnalysisForm(map, null);
		JSONObject object = JSONObject.fromObject(supplierComprehensiveAnalysisSet);
		String json = object.toString().replaceAll("\"", "%");
		this.putObject("json", json);
		this.putObject("supplierComprehensiveAnalysisSet", supplierComprehensiveAnalysisSet);
		this.putObject("list", list);
		this.forwardPage("sco/shareFunctionAnalysis/supplierComprehensiveAnalysis/supplierComprehensiveAnalysisForm.ftl");
		// List<SupplierComprehensiveAnalysisForm> list = supplierComprehensiveAnalysisService.listSupplierComprehensiveAnalysisForm(map, null);
	}

	/**
	 * 保存报表
	 * 
	 * @throws Exception
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doSaveSearchDataForm() throws Exception {

		String fileName = this.asString("fileName");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("reportsTypeCode", BusinessConstants.myReportType.SSA);// 报表类型
		map.put("reportsName", fileName);// 报表名称
		// 校验文件名称
		boolean exists = ReportsServiceImpl.getInstance().ifFileNameExists(map);
		if (exists) {
			this.forwardData(false, null, "所填文件名称已存在,请更换");
			return;
		}
		// 获取前台参数
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("tableInfo", this.asString("data"));
		String msg = supplierComprehensiveAnalysisService.saveSearchDataForm(fileName, paraMap);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "报表保存成功");
			return;
		}
		this.forwardData(false, null, msg);
	}

	/**
	 * 导出到Excel
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doExportDataToExcel() throws Exception {
		String rows = this.asString("date");
		JSONObject jobj = JSONObject.fromObject(rows);
		SupplierComprehensiveAnalysisSet supplierComprehensiveAnalysisSet = (SupplierComprehensiveAnalysisSet) JSONObject.toBean(jobj, SupplierComprehensiveAnalysisSet.class);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplierCode", supplierComprehensiveAnalysisSet.getSupplierCode().replaceAll("v", "\'").replaceAll("@", ","));
		map.put("smallTypeCode", supplierComprehensiveAnalysisSet.getSmallTypeCode());
		map.put("detailTypeCode", supplierComprehensiveAnalysisSet.getDetailTypeCode());
		map.put("minDate", supplierComprehensiveAnalysisSet.getMinDate());
		map.put("maxDate", supplierComprehensiveAnalysisSet.getMaxDate());
		map.put("minDate1", supplierComprehensiveAnalysisSet.getMinDate1());
		map.put("maxDate1", supplierComprehensiveAnalysisSet.getMaxDate1());
		map.put("minDate2", supplierComprehensiveAnalysisSet.getMinDate2());
		map.put("maxDate2", supplierComprehensiveAnalysisSet.getMaxDate2());
		map.put("minDate3", supplierComprehensiveAnalysisSet.getMinDate3());
		map.put("maxDate3", supplierComprehensiveAnalysisSet.getMaxDate3());
		map.put("lastYear", supplierComprehensiveAnalysisSet.getLastYear());
		map.put("lastYear1", supplierComprehensiveAnalysisSet.getLastYear1());
		map.put("lastYear2", supplierComprehensiveAnalysisSet.getLastYear2());
		map.put("lastYear3", supplierComprehensiveAnalysisSet.getLastYear3());

		List<SupplierComprehensiveAnalysisForm> list = supplierComprehensiveAnalysisService.listSupplierComprehensiveAnalysisForm(map, null);
		ServletOutputStream out = response.getOutputStream();
		String fileName = "供应商综合分析结果" + "_".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		supplierComprehensiveAnalysisService.exportExcel(list, supplierComprehensiveAnalysisSet, out);

	}

	/**
	 * 综合分析列表
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierComprehensiveAnalysis() throws Exception {
		Map<String, Object> map = getSupplierComprehensiveAnalysis().toMap();
		List<SupplierComprehensiveAnalysis> list = supplierComprehensiveAnalysisService.listSupplierComprehensiveAnalysis(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 综合分析内容列表
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doListSupplierComprehensiveAnalysisForm() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("supplierCode", this.asString("supplierCode").replaceAll("v", "\'").replaceAll("@", ","));
		map.put("smallTypeCode", this.asString("smallTypeCode"));
		map.put("detailTypeCode", this.asString("detailTypeCode"));
		map.put("minDate", this.asString("minDate"));
		map.put("maxDate", this.asString("maxDate"));
		map.put("minDate1", this.asString("minDate1").replaceAll(" ", ""));
		map.put("maxDate1", this.asString("maxDate1").replaceAll(" ", ""));
		map.put("minDate2", this.asString("minDate2").replaceAll(" ", ""));
		map.put("maxDate2", this.asString("maxDate2").replaceAll(" ", ""));
		map.put("minDate3", this.asString("minDate3").replaceAll(" ", ""));
		map.put("maxDate3", this.asString("maxDate3").replaceAll(" ", ""));
		map.put("lastYear", this.asString("lastYear").replaceAll(" ", ""));
		map.put("lastYear1", this.asString("lastYear1").replaceAll(" ", ""));
		map.put("lastYear2", this.asString("lastYear2").replaceAll(" ", ""));
		map.put("lastYear3", this.asString("lastYear3").replaceAll(" ", ""));

		List<SupplierComprehensiveAnalysisForm> list = supplierComprehensiveAnalysisService.listSupplierComprehensiveAnalysisForm(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private SupplierComprehensiveAnalysis getSupplierComprehensiveAnalysis() throws Exception {
		SupplierComprehensiveAnalysis supplierComprehensiveAnalysis = new SupplierComprehensiveAnalysis();
		this.asBean(supplierComprehensiveAnalysis);
		return supplierComprehensiveAnalysis;
	}

}
