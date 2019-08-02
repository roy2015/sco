package com.powere2e.sco.action.accessoryintentioncostanalysis.totalcostanalysis;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisService;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiry;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryIntention;
import com.powere2e.sco.model.accessoryintention.AccessoryProofing;
import com.powere2e.sco.model.accessoryintention.AccessoryQuotedElectronic;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.EnquiryForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.IntentionForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedDetailForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.SupplierForm;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.Totalcostanalysis;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.TotalcostanalysisForm;
import com.powere2e.sco.model.accessoryoaapplication.SubscribeAccessory;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryAccessoryServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryElseServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryMaterialServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryPackingServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryQuotedCountServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryEnquiryTechnologyServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryIntentionServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryProofingServiceImpl;
import com.powere2e.sco.service.impl.accessoryintention.AccessoryQuotedElectronicServiceImpl;
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
public class TotalcostanalysisAction extends UploadUtils {

	/**
	 * 
	 * 
	 */
	private static final long serialVersionUID = 7467505029752175810L;

	private TotalcostanalysisService totalcostanalysisService;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		totalcostanalysisService = (TotalcostanalysisService) ConfigFactory.getInstance().getBean("totalcostanalysisService");
	}

	/**
	 * 总成本分析grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowTotalCostAnalysisGrid() throws Exception {

		this.forwardPage("sco/accessoryIntentionCostAnalysis/totalCostAnalysis/totalCostAnalysisGrid.ftl");
	}

	/**
	 * 成本类比分析grid
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowCostAnalogyGrid() throws Exception {

		this.forwardPage("sco/accessoryIntentionCostAnalysis/costAnalogy/costAnalogyGrid.ftl");
	}

	/**
	 * 显示总成本分析设置
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowTotalCostAnalysisSet() throws Exception {
		String rows = this.asString("rows");
		JSONArray jsonArr = JSONArray.fromObject(rows);
		Totalcostanalysis[] dataArray = new Totalcostanalysis[jsonArr.size()];
		for (int j = 0; j < jsonArr.size(); j++) {
			dataArray[j] = (Totalcostanalysis) JSONObject.toBean(jsonArr.getJSONObject(j), Totalcostanalysis.class);
		}
		List<String> enquiryCodeList = new ArrayList<>();
		for (Totalcostanalysis totalcostanalysis : dataArray) {
			enquiryCodeList.add(totalcostanalysis.getEnquiryCode());
		}
		enquiryCodeList = this.removeRepeat(enquiryCodeList);
		List<AccessoryEnquiry> enquiryList = new ArrayList<>();
		for (String enquiryCode : enquiryCodeList) {
			enquiryList.add(AccessoryEnquiryServiceImpl.getInstance().loadAccessoryEnquiry(enquiryCode));
		}
		this.putObject("enquiryCodeList", enquiryList);
		this.putObject("size", enquiryCodeList.size());
		this.forwardPage("sco/accessoryIntentionCostAnalysis/totalCostAnalysis/totalCostAnalysisSetForm.ftl");
	}

	/**
	 * 显示总成本分析
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doShowTotalCostAnalysis() throws Exception {
		boolean flag = true;// 是否是一个意向品，一个报价数量
		String rows = this.asString("rows");
		String allbjd = this.asString("allbjd");
		String allbjsl = this.asString("allbjsl");
		String zzxx = this.asString("zzxx");
		String ypxx = this.asString("ypxx");
		String zhfp = this.asString("zhfp");
		// String allxjd = this.asString("allxjd");
		String txbjsl = this.asString("txbjsl");// 填写的报价数量
		String enquiryCodelistStr = "";
		int showLine=0;
		// 传给页面的对象
		TotalcostanalysisForm totalcostanalysisForm = new TotalcostanalysisForm();
		if("是".equals(allbjd)){
			totalcostanalysisForm.setAllbjd(true);
		}
		List<SupplierForm> listSup = new ArrayList<SupplierForm>();
		JSONArray jsonArr = JSONArray.fromObject(rows);
		Totalcostanalysis[] dataArray = new Totalcostanalysis[jsonArr.size()];
		for (int j = 0; j < jsonArr.size(); j++) {
			dataArray[j] = (Totalcostanalysis) JSONObject.toBean(jsonArr.getJSONObject(j), Totalcostanalysis.class);
		}
		if (dataArray.length > 1) {
			for (int i = 1; i < dataArray.length; i++) {
				if (!dataArray[0].getIntentionCode().equals(dataArray[i].getIntentionCode())) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			for (int i = 0; i < dataArray.length; i++) {
				if (StringUtils.isBlank(dataArray[i].getLine())) {
					flag = false;
					break;
				}
			}
		}
		if (flag) {
			Totalcostanalysis totalcostanalysis = null;
			for (int i = 0; i < dataArray.length; i++) {
				for (int j = i + 1; j < dataArray.length; j++) {
					if (Integer.parseInt(dataArray[j].getLine()) < Integer.parseInt(dataArray[i].getLine())) {
						totalcostanalysis = dataArray[j];
						dataArray[j] = dataArray[i];
						dataArray[i] = totalcostanalysis;
					}
				}
			}
		}
		Map<String, BigDecimal> enquiryCodemap = new HashMap<String, BigDecimal>();
		for (int i = 0; i < dataArray.length; i++) {

			enquiryCodemap.put(dataArray[i].getEnquiryCode(), dataArray[i].getPurchaseCount());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("intentionCode", dataArray[i].getIntentionCode());
			map.put("intentionSupplierCode", dataArray[i].getSupplierCode() == null ? dataArray[i].getIntentionSupplierCode() : dataArray[i].getSupplierCode());
			// 供应商信息start
			List<AccessoryQuotedElectronic> accessoryQuotedElectronicList = AccessoryQuotedElectronicServiceImpl.getInstance().listAccessoryQuotedElectronic(map, null);
			AccessoryQuotedElectronic accessoryQuotedElectronic = new AccessoryQuotedElectronic();
			if (accessoryQuotedElectronicList != null && accessoryQuotedElectronicList.size() > 0) {
				accessoryQuotedElectronic = accessoryQuotedElectronicList.get(0);
				SupplierForm sup = new SupplierForm();

				sup.setIntentionSupplierCode(accessoryQuotedElectronic.getIntentionSupplierCode() == null ? accessoryQuotedElectronic.getSupplierCode() : accessoryQuotedElectronic
						.getIntentionSupplierCode());
				sup.setIntentionSupplierName(accessoryQuotedElectronic.getSupplierName() == null ? accessoryQuotedElectronic.getIntentionSupplierName() : accessoryQuotedElectronic.getSupplierName());
				if (zzxx.indexOf("供应商编号-供应商名称") > -1 || zzxx.indexOf("全选") > -1) {
					totalcostanalysisForm.setIntentionSupplierCodeShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("联系人") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setContacts(accessoryQuotedElectronic.getContacts());
					totalcostanalysisForm.setContactsShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("联系方式") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setPhone(accessoryQuotedElectronic.getPhone());
					totalcostanalysisForm.setPhoneShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("公司地址") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setCompanySite(accessoryQuotedElectronic.getCompanySite());
					totalcostanalysisForm.setCompanySiteShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("工厂地址") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setFactorySite(accessoryQuotedElectronic.getFactorySite());
					totalcostanalysisForm.setFactorySiteShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("注册资金") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setRegisterCapital(accessoryQuotedElectronic.getRegisterCapital());
					totalcostanalysisForm.setRegisterCapitalShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("年营业额") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setYearTurnover(accessoryQuotedElectronic.getYearTurnover());
					totalcostanalysisForm.setYearTurnoverShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("工厂面积") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setFactoryArea(accessoryQuotedElectronic.getFactoryArea());
					totalcostanalysisForm.setFactoryAreaShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("工人数") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setStaffCount(accessoryQuotedElectronic.getStaffCount());
					totalcostanalysisForm.setStaffCountShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("税率") > -1 || zzxx.indexOf("全选") > -1) {
					totalcostanalysisForm.setTaxRateShow("Y");
					showLine++;
				}
				sup.setTaxRate(accessoryQuotedElectronic.getTaxRate());
				if (zzxx.indexOf("日产能") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setDailyCapacity(accessoryQuotedElectronic.getDailyCapacity());
					totalcostanalysisForm.setDailyCapacityShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("合作过品牌") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setHzgpp(accessoryQuotedElectronic.getHzgpp());
					totalcostanalysisForm.setHzgppShow("Y");
					showLine++;
				}

				sup.setInvoiceType(accessoryQuotedElectronic.getInvoiceType());
				if (zzxx.indexOf("发票类型") > -1 || zzxx.indexOf("全选") > -1) {
					totalcostanalysisForm.setInvoiceTypeShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("报价币种") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setQuotedCurrency(accessoryQuotedElectronic.getQuotedCurrency());
					totalcostanalysisForm.setQuotedCurrencyShow("Y");
					showLine++;
				}
				if (zzxx.indexOf("付款方式") > -1 || zzxx.indexOf("全选") > -1) {
					sup.setPaymentType(accessoryQuotedElectronic.getPaymentType());
					totalcostanalysisForm.setPaymentTypeShow("Y");
					showLine++;
				}
				
				if ("全选".equals(ypxx)) {
					totalcostanalysisForm.setProofingContentAfterShow("Y");
					showLine++;
					showLine++;
					totalcostanalysisForm.setProofingContentBeforeShow("Y");
					showLine++;
					showLine++;
					map.clear();
					map.put("intentionCode", dataArray[i].getIntentionCode());
					map.put("intentionSupplierCode", dataArray[i].getSupplierCode() == null ? dataArray[i].getIntentionSupplierCode() : dataArray[i].getSupplierCode());
					map.put("proofingType", "产前样");
					List<AccessoryProofing> accessoryProofingList = AccessoryProofingServiceImpl.getInstance().listAccessoryProofing(map, null);

					if (accessoryProofingList != null && accessoryProofingList.size() > 0) {
						AccessoryProofing accessoryProofing = accessoryProofingList.get(0);
							sup.setProofingContentBefore(accessoryProofing.getProofingContent() == null ? "" : accessoryProofing.getProofingContent());
							sup.setProofingEvaluateBefore(accessoryProofing.getProofingEvaluate() == null ? "" : accessoryProofing.getProofingEvaluate());

					} else {
						sup.setProofingContentBefore("");
						sup.setProofingEvaluateBefore("");

					}
					map.clear();
					map.put("intentionCode", dataArray[i].getIntentionCode());
					map.put("intentionSupplierCode", dataArray[i].getSupplierCode() == null ? dataArray[i].getIntentionSupplierCode() : dataArray[i].getSupplierCode());
					map.put("proofingType", "非产前样");
					List<AccessoryProofing> accessoryProofingListAfter = AccessoryProofingServiceImpl.getInstance().listAccessoryProofing(map, null);

					if (accessoryProofingListAfter != null && accessoryProofingListAfter.size() > 0) {
						AccessoryProofing accessoryProofingAfter = accessoryProofingListAfter.get(0);

						sup.setProofingContentAfter(accessoryProofingAfter.getProofingContent() == null ? "" : accessoryProofingAfter.getProofingContent());
						sup.setProofingEvaluateAfter(accessoryProofingAfter.getProofingEvaluate() == null ? "" : accessoryProofingAfter.getProofingEvaluate());

					} else {
						sup.setProofingContentAfter("");
						sup.setProofingEvaluateAfter("");
					}
				} else {
					map.clear();
					map.put("intentionCode", dataArray[i].getIntentionCode());
					map.put("intentionSupplierCode", dataArray[i].getSupplierCode() == null ? dataArray[i].getIntentionSupplierCode() : dataArray[i].getSupplierCode());
					if ("产前样".equals(ypxx)){
						totalcostanalysisForm.setProofingContentBeforeShow("Y");
						showLine++;
						showLine++;
						map.put("proofingType", "产前样");
					}
					if ("非产前样".equals(ypxx)){
						totalcostanalysisForm.setProofingContentAfterShow("Y");
						showLine++;
						showLine++;
						map.put("proofingType", "非产前样");
					}

					List<AccessoryProofing> accessoryProofingList = AccessoryProofingServiceImpl.getInstance().listAccessoryProofing(map, null);

					if (accessoryProofingList != null && accessoryProofingList.size() > 0) {
						AccessoryProofing accessoryProofing = accessoryProofingList.get(0);
						if ("产前样".equals(ypxx)) {
							sup.setProofingContentBefore(accessoryProofing.getProofingContent() == null ? "" : accessoryProofing.getProofingContent());
							sup.setProofingEvaluateBefore(accessoryProofing.getProofingEvaluate() == null ? "" : accessoryProofing.getProofingEvaluate());
						}
						if ("非产前样".equals(ypxx)) {
							sup.setProofingContentAfter(accessoryProofing.getProofingContent() == null ? "" : accessoryProofing.getProofingContent());
							sup.setProofingEvaluateAfter(accessoryProofing.getProofingEvaluate() == null ? "" : accessoryProofing.getProofingEvaluate());
						}
					} else {
						sup.setProofingContentBefore("");
						sup.setProofingEvaluateBefore("");
						sup.setProofingContentAfter("");
						sup.setProofingEvaluateAfter("");
					}
				}
				listSup.add(sup);
				if ("是".equals(zhfp) && "增票".equals(accessoryQuotedElectronic.getInvoiceType())) {
					SupplierForm sup1 = (SupplierForm) sup.clone();
					sup1.setInvoiceType("换算为普票");
					listSup.add(sup1);
				}
			}
		}
		if (listSup != null && listSup.size() > 1) {
			for (int i = 0; i < listSup.size(); i++) // 外循环是循环的次数
			{
				for (int j = listSup.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
				{

					if (listSup.get(i).getIntentionSupplierCode().equals(listSup.get(j).getIntentionSupplierCode()) && listSup.get(i).getInvoiceType().equals(listSup.get(j).getInvoiceType())) {
						listSup.remove(j);
					}

				}
			}
		}
		// 供应商信息end
		// 意向品相关信息start
		List<IntentionForm> listIntention = new ArrayList<IntentionForm>();
		// 获得页面选择的意向品编号
		List<String> intentionCodelist = new ArrayList<String>();
		for (int i = 0; i < dataArray.length; i++) {
			intentionCodelist.add(dataArray[i].getIntentionCode());
		}
		// 页面选择的意向品编号去重复
		intentionCodelist = this.removeRepeat(intentionCodelist);
		for (int k = 0; k < intentionCodelist.size(); k++) {
			IntentionForm inton = new IntentionForm();
			int count = 0;
			inton.setIntentionCode(intentionCodelist.get(k));
			AccessoryIntention accessoryIntention = AccessoryIntentionServiceImpl.getInstance().loadAccessoryIntention(intentionCodelist.get(k));
			inton.setIntentionName(accessoryIntention.getIntentionName());
			// 获得页面选择的意向品对应的询价单编号（可能有多个）
			List<String> enquiryCodelist = new ArrayList<String>();
			for (int i = 0; i < dataArray.length; i++) {
				if (intentionCodelist.get(k).equals(dataArray[i].getIntentionCode()))
					enquiryCodelist.add(dataArray[i].getEnquiryCode());
			}
			enquiryCodelist = this.removeRepeat(enquiryCodelist);
			// 选择最晚的询价单编号
			/*
			 * String maxEnquiryCode = enquiryCodelist.get(0); if
			 * (enquiryCodelist.size() > 1) { for (int i = 1; i <
			 * enquiryCodelist.size(); i++) { if
			 * (enquiryCodelist.get(i).compareTo(maxEnquiryCode.toString()) ==
			 * 1) { maxEnquiryCode = enquiryCodelist.get(i); }
			 * 
			 * } }
			 */
			// 获得询价单信息
			List<EnquiryForm> eq = new ArrayList<EnquiryForm>();
			/*
			 * // 是否查看所有询价单 if (!"是".equals(allxjd)) {
			 */
			for (String maxEnquiryCode : enquiryCodelist) {
				enquiryCodelistStr = enquiryCodelistStr + maxEnquiryCode + ",";
				/*
				 * Map<String, Object> map = new HashMap<String, Object>();
				 * map.put("intentionCode", intentionCodelist.get(k));
				 * map.put("enquiryCode", maxEnquiryCode); AccessoryEnquiry
				 * accessoryEnquiry =
				 * AccessoryEnquiryServiceImpl.getInstance().loadAccessoryEnquiry
				 * (maxEnquiryCode);
				 */
				EnquiryForm enquiryForm = new EnquiryForm();
				enquiryForm.setEnquiryCode(maxEnquiryCode);
				/*
				 * List<SubscribeAccessory> subscribeAccessoryList =
				 * SubscribeAccessoryServiceImpl
				 * .getInstance().listSubscribeAccessory(map, null); if
				 * (subscribeAccessoryList != null &&
				 * subscribeAccessoryList.size() > 0) {
				 * enquiryForm.setPurchaseCount
				 * (subscribeAccessoryList.get(0).getPurchaseCount() == null ?
				 * "0" : subscribeAccessoryList.get(0).getPurchaseCount()); }
				 * else { enquiryForm.setPurchaseCount("0"); }
				 */
				List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList = new ArrayList<AccessoryEnquiryQuotedCount>();
				// 是否查看所有报价数量
				if ("是".equals(allbjsl)) {
					Map<String, Object> map1 = new HashMap<String, Object>();
					map1.put("enquiryCode", maxEnquiryCode);
					accessoryEnquiryQuotedCountList = AccessoryEnquiryQuotedCountServiceImpl.getInstance().listAccessoryEnquiryQuotedCount(map1, null);
					for (AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount : accessoryEnquiryQuotedCountList) {
						count++;
						Map<String, Object> map2 = new HashMap<String, Object>();
						/*
						 * map2.put("enquiryCode", maxEnquiryCode);
						 * map2.put("enquiryCount",
						 * accessoryEnquiryQuotedCount.getQuotedCount());
						 * List<WlInfo> wlInfoList =
						 * WlInfoServiceImpl.getInstance().listWlInfo(map2,
						 * null); if (wlInfoList != null && wlInfoList.size() >
						 * 0) {
						 */
						accessoryEnquiryQuotedCount.setPurchaseCount(enquiryCodemap.get(maxEnquiryCode));
						/*
						 * } else {
						 * accessoryEnquiryQuotedCount.setPurchaseCount("0"); }
						 */
						List<QuotedForm> quotedFormList = new ArrayList<QuotedForm>();
						for (int i = 0; i < listSup.size(); i++) {
							map2.clear();
							map2.put("enquiryCode", maxEnquiryCode);
							// 20150731
							// map2.put("supplierCode",
							// listSup.get(i).getIntentionSupplierCode());
							map2.put("enquiryCount", accessoryEnquiryQuotedCount.getQuotedCount());
							// 排名的一些信息
							QuotedForm quotedForm = new QuotedForm();
							List<QuotedForm> quotedFormList1 = totalcostanalysisService.listQuotedForm(map2, null);
							if (quotedFormList1.size() > 0) {
								for (QuotedForm qf : quotedFormList1) {
									if (qf.getSupplierCode().equals(listSup.get(i).getIntentionSupplierCode())) {
										quotedForm = qf;
										break;
									}
								}
							}
							List<QuotedDetailForm> quotedDetailFormList = new ArrayList<QuotedDetailForm>();
							Map<String, Object> map3 = new HashMap<String, Object>();
							map3.put("enquiryCode", maxEnquiryCode);
							map3.put("supplierCode", listSup.get(i).getIntentionSupplierCode());
							map3.put("enquiryCount", accessoryEnquiryQuotedCount.getQuotedCount());
							quotedDetailFormList = totalcostanalysisService.listQuotedDetailForm(map3, null);
							for (QuotedDetailForm quotedDetailForm : quotedDetailFormList) {
								quotedDetailForm.setQuotedTotal(quotedDetailForm.getQuotedTotal().multiply(enquiryCodemap.get(maxEnquiryCode)).divide(new BigDecimal("1"), 3,
										BigDecimal.ROUND_HALF_UP));
							}
							if ("换算为普票".equals(listSup.get(i).getInvoiceType()) && quotedDetailFormList != null && quotedDetailFormList.size() > 0) {
								for (QuotedDetailForm quotedDetailForm : quotedDetailFormList) {
									quotedDetailForm.setUnitPrice(quotedDetailForm.getUnitPrice().divide((new BigDecimal("1").add(listSup.get(i).getTaxRate().divide(new BigDecimal("100")))), 3,
											BigDecimal.ROUND_HALF_UP));
									quotedDetailForm.setQuotedTotal(quotedDetailForm.getQuotedTotal().divide((new BigDecimal("1").add(listSup.get(i).getTaxRate().divide(new BigDecimal("100")))), 3,
											BigDecimal.ROUND_HALF_UP));
								}
							}
							// 是否查看所有报价单
							if ("是".equals(allbjd)) {
								quotedForm.setQuotedDetailFormList(quotedDetailFormList);
							} else {
								if (quotedDetailFormList != null && quotedDetailFormList.size() > 0) {
									QuotedDetailForm quotedDetailForm = quotedDetailFormList.get(quotedDetailFormList.size() - 1);
									quotedDetailFormList.clear();
									quotedDetailFormList.add(quotedDetailForm);
									quotedForm.setQuotedDetailFormList(quotedDetailFormList);
								}
							}
							quotedFormList.add(quotedForm);
							accessoryEnquiryQuotedCount.setQuotedFormList(quotedFormList);
						}

					}
				} else {
					// 不查看所有报价数量,查实际采购数量
					AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount = new AccessoryEnquiryQuotedCount();
					accessoryEnquiryQuotedCount.setEnquiryCode(maxEnquiryCode);
					accessoryEnquiryQuotedCount.setQuotedCount(new BigDecimal(txbjsl) );
					accessoryEnquiryQuotedCountList.add(accessoryEnquiryQuotedCount);
					for (AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount1 : accessoryEnquiryQuotedCountList) {
						count++;
						Map<String, Object> map2 = new HashMap<String, Object>();
						/*
						 * map2.put("enquiryCode", maxEnquiryCode);
						 * map2.put("enquiryCount",
						 * accessoryEnquiryQuotedCount.getQuotedCount());
						 * List<WlInfo> wlInfoList =
						 * WlInfoServiceImpl.getInstance().listWlInfo(map2,
						 * null); if (wlInfoList != null && wlInfoList.size() >
						 * 0) {
						 * accessoryEnquiryQuotedCount.setPurchaseCount(wlInfoList
						 * .get(0).getPurchaseCount() == null ? "0" :
						 * wlInfoList.get(0).getPurchaseCount()); } else {
						 * accessoryEnquiryQuotedCount.setPurchaseCount("0"); }
						 */
						accessoryEnquiryQuotedCount.setPurchaseCount(enquiryCodemap.get(maxEnquiryCode));
						List<QuotedForm> quotedFormList = new ArrayList<QuotedForm>();
						for (int i = 0; i < listSup.size(); i++) {
							map2.clear();
							map2.put("enquiryCode", maxEnquiryCode);
							map2.put("enquiryCount", accessoryEnquiryQuotedCount1.getQuotedCount());
							// 排名的一些信息
							QuotedForm quotedForm = new QuotedForm();
							List<QuotedForm> quotedFormList1 = totalcostanalysisService.listQuotedForm(map2, null);
							if (quotedFormList1.size() > 0) {
								for (QuotedForm qf : quotedFormList1) {
									if (qf.getSupplierCode().equals(listSup.get(i).getIntentionSupplierCode())) {
										quotedForm = qf;
									}
								}
							}
							List<QuotedDetailForm> quotedDetailFormList = new ArrayList<QuotedDetailForm>();
							Map<String, Object> map3 = new HashMap<String, Object>();
							map3.put("enquiryCode", maxEnquiryCode);
							map3.put("supplierCode", listSup.get(i).getIntentionSupplierCode());
							map3.put("enquiryCount", accessoryEnquiryQuotedCount1.getQuotedCount());
							quotedDetailFormList = totalcostanalysisService.listQuotedDetailForm(map3, null);
							for (QuotedDetailForm quotedDetailForm : quotedDetailFormList) {
								quotedDetailForm.setQuotedTotal(quotedDetailForm.getQuotedTotal().multiply(enquiryCodemap.get(maxEnquiryCode)).divide(new BigDecimal("1"), 3,
										BigDecimal.ROUND_HALF_UP));
							}
							if ("换算为普票".equals(listSup.get(i).getInvoiceType()) && quotedDetailFormList != null && quotedDetailFormList.size() > 0) {
								for (QuotedDetailForm quotedDetailForm : quotedDetailFormList) {
									quotedDetailForm.setUnitPrice(quotedDetailForm.getUnitPrice().divide((new BigDecimal("1").add(listSup.get(i).getTaxRate().divide(new BigDecimal("100")))), 3,
											BigDecimal.ROUND_HALF_UP));
									quotedDetailForm.setQuotedTotal(quotedDetailForm.getQuotedTotal().divide((new BigDecimal("1").add(listSup.get(i).getTaxRate().divide(new BigDecimal("100")))), 3,
											BigDecimal.ROUND_HALF_UP));
								}
							}
							// 是否查看所有报价单
							if ("是".equals(allbjd)) {
								quotedForm.setQuotedDetailFormList(quotedDetailFormList);
							} else {
								if (quotedDetailFormList != null && quotedDetailFormList.size() > 0) {
									QuotedDetailForm quotedDetailForm = quotedDetailFormList.get(quotedDetailFormList.size() - 1);
									quotedDetailFormList.clear();
									quotedDetailFormList.add(quotedDetailForm);
									quotedForm.setQuotedDetailFormList(quotedDetailFormList);
								}
							}
							quotedFormList.add(quotedForm);
							accessoryEnquiryQuotedCount1.setQuotedFormList(quotedFormList);
						}

					}

				}
				Map<String, Object> mapEnquiry = new HashMap<String, Object>();
				mapEnquiry.put("enquiryCode", maxEnquiryCode);
				enquiryForm.setAccessoryEnquiryPackingList(AccessoryEnquiryPackingServiceImpl.getInstance().listAccessoryEnquiryPacking(mapEnquiry, null));
				enquiryForm.setAccessoryEnquiryAccessoryList(AccessoryEnquiryAccessoryServiceImpl.getInstance().listAccessoryEnquiryAccessory(mapEnquiry, null));
				enquiryForm.setAccessoryEnquiryMaterialList(AccessoryEnquiryMaterialServiceImpl.getInstance().listAccessoryEnquiryMaterial(mapEnquiry, null));
				enquiryForm.setAccessoryEnquiryTechnologyList(AccessoryEnquiryTechnologyServiceImpl.getInstance().listAccessoryEnquiryTechnology(mapEnquiry, null));
				enquiryForm.setAccessoryEnquiryElseList(AccessoryEnquiryElseServiceImpl.getInstance().listAccessoryEnquiryElse(mapEnquiry, null));
				enquiryForm.setAccessoryEnquiryQuotedCountList(accessoryEnquiryQuotedCountList);
				eq.add(enquiryForm);
				inton.setEnquiryList(eq);
				inton.setCount(count);

			}
			listIntention.add(inton);
		}
		totalcostanalysisForm.setShowLine(showLine/(dataArray.length));
		totalcostanalysisForm.setSupplierFormList(listSup);
		totalcostanalysisForm.setIntentionFormList(listIntention);
		// 最后一次询价单报价合计
		/*
		 * if (listIntention.size() > 1) { List<IntentionTotalForm>
		 * intentionTotalFormList = new ArrayList<IntentionTotalForm>(); //
		 * 最后一个询价单编号list List<String> lastEnquiryCodeList = new
		 * ArrayList<String>(); // 询价单的报价数量去重复 List<String> lastCountCodeList =
		 * new ArrayList<String>(); // 获得每个意向品的最后一个询价单编号list for (String code :
		 * intentionCodelist) { Map<String, Object> mapforenquiry = new
		 * HashMap<String, Object>(); mapforenquiry.put("intentionCode", code);
		 * List<AccessoryEnquiry> accessoryEnquiryList =
		 * AccessoryEnquiryServiceImpl
		 * .getInstance().listAccessoryEnquiry(mapforenquiry, null); if
		 * (accessoryEnquiryList.size() > 0) { for (int i = 0; i <
		 * accessoryEnquiryList.size(); i++) { if
		 * (enquiryCodelistStr.indexOf(accessoryEnquiryList
		 * .get(i).getEnquiryCode()) > -1) {
		 * lastEnquiryCodeList.add(accessoryEnquiryList
		 * .get(i).getEnquiryCode()); break; } } }
		 * 
		 * } lastEnquiryCodeList = this.removeRepeat(lastEnquiryCodeList); //
		 * 活动报价数量 for (String code : lastEnquiryCodeList) { if
		 * ("是".equals(allxjd)) { Map<String, Object> mapforcount = new
		 * HashMap<String, Object>(); mapforcount.put("enquiryCode", code);
		 * List<AccessoryEnquiryQuotedCount> countList =
		 * AccessoryEnquiryQuotedCountServiceImpl
		 * .getInstance().listAccessoryEnquiryQuotedCount(mapforcount, null); if
		 * (countList.size() > 0) { for (AccessoryEnquiryQuotedCount count :
		 * countList) { lastCountCodeList.add(count.getQuotedCount()); } }
		 * 
		 * }else{ List<AccessoryEnquiryQuotedCount> countList=new
		 * ArrayList<AccessoryEnquiryQuotedCount>(); Map<String, Object> map1 =
		 * new HashMap<String, Object>(); map1.put("enquiryCode", code); //
		 * 申购产品信息表 List<SubscribeAccessory> subscribeAccessoryList1 =
		 * SubscribeAccessoryServiceImpl.getInstance
		 * ().listSubscribeAccessory(map1, null); if (subscribeAccessoryList1 !=
		 * null && subscribeAccessoryList1.size() > 0) { SubscribeAccessory
		 * subscribeAccessory = subscribeAccessoryList1.get(0);
		 * AccessoryEnquiryQuotedCount accessoryEnquiryQuotedCount = new
		 * AccessoryEnquiryQuotedCount();
		 * accessoryEnquiryQuotedCount.setEnquiryCode
		 * (subscribeAccessory.getEnquiryCode()); accessoryEnquiryQuotedCount
		 * .setQuotedCount(subscribeAccessory.getEnquiryCount());
		 * countList.add(accessoryEnquiryQuotedCount); } }
		 * 
		 * } lastCountCodeList = this.removeRepeat(lastCountCodeList); //
		 * 循环报价数量，有几个报价数量就有几行 for (int i = 0; i < lastCountCodeList.size(); i++)
		 * { IntentionTotalForm intentionTotalForm = new IntentionTotalForm();
		 * intentionTotalForm.setQuotedCount(lastCountCodeList.get(i));
		 * List<QuotedTotalForm> quotedTotalFormList = new
		 * ArrayList<QuotedTotalForm>(); for (int j = 0; j < listSup.size();
		 * j++) { QuotedTotalForm quotedTotalForm = new QuotedTotalForm();
		 * List<QuotedDetailTotalForm> quotedDetailTotalFormList = new
		 * ArrayList<QuotedDetailTotalForm>(); List<QuotedDetailTotalForm>
		 * quotedDetailTotalFormListHs = new ArrayList<QuotedDetailTotalForm>();
		 * List<List<QuotedDetailForm>> list = new
		 * ArrayList<List<QuotedDetailForm>>(); Map<String, Object> mapfortotal
		 * = new HashMap<String, Object>(); mapfortotal.clear(); for (int k = 0;
		 * k < lastEnquiryCodeList.size(); k++) { mapfortotal.put("enquiryCode",
		 * lastEnquiryCodeList.get(k)); mapfortotal.put("enquiryCount",
		 * lastCountCodeList.get(i)); mapfortotal.put("supplierCode",
		 * listSup.get(j).getIntentionSupplierCode()); // list里是多次报价的内容
		 * List<QuotedDetailForm> detailList =
		 * totalcostanalysisService.listQuotedDetailForm(mapfortotal, null); for
		 * (QuotedDetailForm quotedDetailForm : detailList) {
		 * quotedDetailForm.setQuotedTotal
		 * (quotedDetailForm.getQuotedTotal().multiply
		 * (enquiryCodemap.get(lastEnquiryCodeList.get(k)))); } if (detailList
		 * != null && detailList.size() > 0) list.add(detailList);
		 * mapfortotal.clear(); }
		 * 
		 * // 供应商在最后一次询价单都报价了才统计 if (list.size() == lastEnquiryCodeList.size())
		 * { if("否".equals(zhxjdallbjd)){ int m=0; BigDecimal b =
		 * list.get(0).get(m).getQuotedTotal(); // n从1开始，加下面的相同的第几次的报价 for (int
		 * n = 1; n < list.size(); n++) { if (list.get(n).size() > 0) { if
		 * (list.get(n).size() > m) { b =
		 * b.add(list.get(n).get(m).getQuotedTotal()); QuotedDetailTotalForm
		 * quotedDetailTotalForm = new QuotedDetailTotalForm();
		 * QuotedDetailTotalForm quotedDetailTotalFormHs = new
		 * QuotedDetailTotalForm(); if
		 * ("换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalForm.setQuotedTotal(b.divide(new BigDecimal("1.17"),
		 * 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalForm.setQuotedTotal(b); } if
		 * ("增票".equals(listSup.get(j).getInvoiceType()) ||
		 * "换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalFormHs.setQuotedTotal(b.divide(new
		 * BigDecimal("1.17"), 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalFormHs.setQuotedTotal(b); }
		 * quotedDetailTotalFormList.add(quotedDetailTotalForm);
		 * quotedDetailTotalFormListHs.add(quotedDetailTotalFormHs); } } } //
		 * int m=0; BigDecimal b =
		 * list.get(0).get(list.get(0).size()-1).getQuotedTotal(); //
		 * n从1开始，加下面的相同的第几次的报价 for (int n = 1; n < list.size(); n++) { if
		 * (list.get(n).size() > 0) { if (list.get(n).size() >0) { b =
		 * b.add(list.get(n).get(list.get(n).size()-1).getQuotedTotal());
		 * QuotedDetailTotalForm quotedDetailTotalForm = new
		 * QuotedDetailTotalForm(); QuotedDetailTotalForm
		 * quotedDetailTotalFormHs = new QuotedDetailTotalForm(); if
		 * ("换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalForm.setQuotedTotal(b.divide((new
		 * BigDecimal("1").add(listSup.get(j).getTaxRate().divide(new
		 * BigDecimal("100")))), 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalForm.setQuotedTotal(b); } if
		 * ("增票".equals(listSup.get(j).getInvoiceType()) ||
		 * "换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalFormHs.setQuotedTotal(b.divide((new
		 * BigDecimal("1").add(listSup.get(j).getTaxRate().divide(new
		 * BigDecimal("100")))), 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalFormHs.setQuotedTotal(b); }
		 * quotedDetailTotalFormList.add(quotedDetailTotalForm);
		 * quotedDetailTotalFormListHs.add(quotedDetailTotalFormHs); } } }
		 * 
		 * 
		 * }else{ // m是第几次报价,因为必须报价才计算和，所以可以取任意一个去循环 for (int m = 0; m <
		 * list.get(0).size(); m++) { BigDecimal b =
		 * list.get(0).get(m).getQuotedTotal(); // n从1开始，加下面的相同的第几次的报价 for (int
		 * n = 1; n < list.size(); n++) { if (list.get(n).size() > 0) { if
		 * (list.get(n).size() > m) { b =
		 * b.add(list.get(n).get(m).getQuotedTotal()); QuotedDetailTotalForm
		 * quotedDetailTotalForm = new QuotedDetailTotalForm();
		 * QuotedDetailTotalForm quotedDetailTotalFormHs = new
		 * QuotedDetailTotalForm(); if
		 * ("换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalForm.setQuotedTotal(b.divide((new
		 * BigDecimal("1").add(listSup.get(j).getTaxRate().divide(new
		 * BigDecimal("100")))), 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalForm.setQuotedTotal(b); } if
		 * ("增票".equals(listSup.get(j).getInvoiceType()) ||
		 * "换算为普票".equals(listSup.get(j).getInvoiceType())) {
		 * quotedDetailTotalFormHs.setQuotedTotal(b.divide((new
		 * BigDecimal("1").add(listSup.get(j).getTaxRate().divide(new
		 * BigDecimal("100")))), 2, BigDecimal.ROUND_HALF_UP)); } else {
		 * quotedDetailTotalFormHs.setQuotedTotal(b); }
		 * quotedDetailTotalFormList.add(quotedDetailTotalForm);
		 * quotedDetailTotalFormListHs.add(quotedDetailTotalFormHs); } } } } } }
		 * quotedTotalForm
		 * .setQuotedDetailTotalFormList(quotedDetailTotalFormList);
		 * quotedTotalForm
		 * .setQuotedDetailTotalFormListHs(quotedDetailTotalFormListHs);
		 * quotedTotalFormList.add(quotedTotalForm); }
		 * intentionTotalForm.setQuotedTotalFormList(quotedTotalFormList);
		 * intentionTotalFormList.add(intentionTotalForm); }
		 * totalcostanalysisForm
		 * .setIntentionTotalFormList(intentionTotalFormList);
		 * 
		 * }
		 */
		//JSONObject object = JSONObject.fromObject(totalcostanalysisForm);
		String object=JSON.toJSONString(totalcostanalysisForm);
		String json = object.replaceAll("\"", "@%");
		totalcostanalysisForm.setJson(json);
		this.putObject("totalcostanalysisForm", totalcostanalysisForm);
		this.forwardPage("sco/accessoryIntentionCostAnalysis/totalCostAnalysis/totalCostAnalysisForm.ftl");
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
		map.put("reportsTypeCode", BusinessConstants.myReportType.FQA);// 报表类型
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
		String msg = totalcostanalysisService.saveSearchDataForm(fileName, paraMap);
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
		String remark = this.asString("remark");
		//com.alibaba.fastjson.JSONObject jobj=JSON.parseObject(rows);
		//JSONArray jsonArr = JSONArray.fromObject(rows);
		//JSONObject jobj = JSONObject.fromObject(rows);
		/*Map<String, Class> classMap = new HashMap<String, Class>();
		classMap.put("supplierFormList", SupplierForm.class);
		classMap.put("intentionFormList", IntentionForm.class);
		classMap.put("intentionTotalFormList", IntentionTotalForm.class);
		classMap.put("enquiryList", EnquiryForm.class);
		classMap.put("accessoryEnquiryQuotedCountList", AccessoryEnquiryQuotedCount.class);
		classMap.put("accessoryEnquiryPackingList", AccessoryEnquiryPacking.class);
		classMap.put("accessoryEnquiryElseList", AccessoryEnquiryElse.class);
		classMap.put("accessoryEnquiryMaterialList", AccessoryEnquiryMaterial.class);
		classMap.put("accessoryEnquiryAccessoryList", AccessoryEnquiryAccessory.class);
		classMap.put("accessoryEnquiryTechnologyList", AccessoryEnquiryTechnology.class);
		classMap.put("quotedTotalFormList", QuotedTotalForm.class);
		classMap.put("quotedDetailTotalFormList", QuotedDetailTotalForm.class);
		classMap.put("quotedDetailTotalFormListHs", QuotedDetailTotalForm.class);
		classMap.put("quotedFormList", QuotedForm.class);
		classMap.put("quotedDetailFormList", QuotedDetailForm.class);*/
		TotalcostanalysisForm totalcostanalysisForm = JSON.parseObject(rows,TotalcostanalysisForm.class,Feature.InitStringFieldAsEmpty);
		totalcostanalysisForm.setRemark(remark);
		ServletOutputStream out = response.getOutputStream();
		String fileName = "辅料总价分析结果" + "_".concat(DateUtils.formateDateTime()).concat(".xlsx");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes(Constant.DEFAULT_ENCODED_1), Constant.DEFAULT_ENCODED_2) + "\"");
		totalcostanalysisService.exportExcel(totalcostanalysisForm, out);

	}

	/**
	 * 总价分析列表
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doListTotalcostanalysisIntentionApplication() throws Exception {
		Map<String, Object> map = getTotalcostanalysis().toMap();
		String supplierNo = this.asString("intentionSupplierCode");
		String supplierName = this.asString("intentionSupplierName");

		map.put("search", this.asString("search"));
		map.put("intentionSupplierCode", supplierNo);
		map.put("intentionSupplierName", supplierName);
		map.put("supplierCode", supplierNo);
		map.put("supplierName", supplierName);
		List<Totalcostanalysis> list = totalcostanalysisService.listTotalcostanalysisIntentionApplication(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 
	 */
	@Authority(privilege = "com.powere2e.sco.accessoryOaApplication")
	public void doLoadTotalcostanalysisIntentionApplication() throws Exception {
		Totalcostanalysis totalcostanalysis = totalcostanalysisService.loadTotalcostanalysisIntentionApplication(asString("quotedCode"));
		this.forwardData(true, totalcostanalysis, null);
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private Totalcostanalysis getTotalcostanalysis() throws Exception {
		Totalcostanalysis totalcostanalysis = new Totalcostanalysis();
		this.asBean(totalcostanalysis);
		return totalcostanalysis;
	}

	public void removeDuplicate(List<SubscribeAccessory> list) {
		for (int i = 0; i < list.size() - 1; i++) {
			for (int j = list.size() - 1; j > i; j--) {
				if (list.get(j).getEnquiryCode().equals(list.get(i).getEnquiryCode())) {
					list.remove(j);
				}
			}
		}
	}

	public List<String> removeRepeat(List<String> list) throws Exception {
		if (list != null && list.size() > 1) {
			for (int i = 0; i < list.size(); i++) // 外循环是循环的次数
			{
				for (int j = list.size() - 1; j > i; j--) // 内循环是 外循环一次比较的次数
				{

					if (list.get(i).equals(list.get(j))) {
						list.remove(j);
					}

				}
			}
		}
		return list;
	}
}
