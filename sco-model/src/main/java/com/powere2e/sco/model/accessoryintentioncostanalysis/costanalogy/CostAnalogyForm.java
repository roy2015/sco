package com.powere2e.sco.model.accessoryintentioncostanalysis.costanalogy;


import java.math.BigDecimal;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class CostAnalogyForm extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4419949052834660979L;
	
	private List<SupplierForm> supplierFormList;//供应商信息list
	
	private List<AccessoryQuotedMaterialAndSubtotalList> accessoryQuotedMaterialAndSubtotalList;//供应商信息list
	private List<AccessoryQuotedAccessoryAndSubtotalList> accessoryQuotedAccessoryAndSubtotalList;//供应商信息list
	private List<AccessoryQuotedPackingAndSubtotalList> accessoryQuotedPackingAndSubtotalList;//供应商信息list
	private List<AccessoryQuotedTechnologyAndSubtotalList> accessoryQuotedTechnologyAndSubtotalList;//供应商信息list
	private List<AccessoryQuotedElseList> accessoryQuotedElseList;//供应商信息list
	private List<TotalAndCooperativePrice> totalAndCooperativePriceList;//合作价格
	private List<BigDecimal> totalMaterialList;
	private List<BigDecimal> totalAccessoryList;
	private List<BigDecimal> totalMaterialAccessoryList;
	private List<BigDecimal> totalPackingList;
	private List<BigDecimal> totalTechnologyList;
	private List<BigDecimal> allTotalList;
	private List<String> cooperativePriceList;
	private String intentionSupplierCodeShow;//
	private String intentionSupplierNameShow;//
	private String contactsShow;// 联系人
	private String phoneShow;// 电话
	private String merchandiseCodeShow;// SAP物料号
	private String quotedDateShow;// SAP物料号
	private String quotedCountShow;// 报价数量
	private String quotedCodeShow;// 报价数量
	private String companySiteShow;//
	private String factorySiteShow;//
	private String invoiceTypeShow;// 发票类型
	private String taxRateShow;// 税率
	private String registerCapitalShow;// 注册资本
	private String yearTurnoverShow;// 年营业额
	private String factoryAreaShow;// 工厂面积
	private String staffCountShow;// 工人数
	private String hzgppShow;// 合作过品牌
	private String paymentTypeShow;// 付款方式
	private String deliveryTypeShow;// 交货方式
	private String dailyCapacityShow;// 日产能
	private String proofingContentBeforeShow;// 打样内容前
	private String proofingEvaluateBeforeShow;// 打样评价前
	private String proofingContentAfterShow;// 打样内容前
	private String proofingEvaluateAfterShow;// 打样评价前
	private String json;
	public List<SupplierForm> getSupplierFormList() {
		return supplierFormList;
	}
	public void setSupplierFormList(List<SupplierForm> supplierFormList) {
		this.supplierFormList = supplierFormList;
	}
	
	public List<AccessoryQuotedMaterialAndSubtotalList> getAccessoryQuotedMaterialAndSubtotalList() {
		return accessoryQuotedMaterialAndSubtotalList;
	}
	public void setAccessoryQuotedMaterialAndSubtotalList(List<AccessoryQuotedMaterialAndSubtotalList> accessoryQuotedMaterialAndSubtotalList) {
		this.accessoryQuotedMaterialAndSubtotalList = accessoryQuotedMaterialAndSubtotalList;
	}
	
	public List<AccessoryQuotedAccessoryAndSubtotalList> getAccessoryQuotedAccessoryAndSubtotalList() {
		return accessoryQuotedAccessoryAndSubtotalList;
	}
	public void setAccessoryQuotedAccessoryAndSubtotalList(List<AccessoryQuotedAccessoryAndSubtotalList> accessoryQuotedAccessoryAndSubtotalList) {
		this.accessoryQuotedAccessoryAndSubtotalList = accessoryQuotedAccessoryAndSubtotalList;
	}
	public List<AccessoryQuotedPackingAndSubtotalList> getAccessoryQuotedPackingAndSubtotalList() {
		return accessoryQuotedPackingAndSubtotalList;
	}
	public void setAccessoryQuotedPackingAndSubtotalList(List<AccessoryQuotedPackingAndSubtotalList> accessoryQuotedPackingAndSubtotalList) {
		this.accessoryQuotedPackingAndSubtotalList = accessoryQuotedPackingAndSubtotalList;
	}
	public List<AccessoryQuotedTechnologyAndSubtotalList> getAccessoryQuotedTechnologyAndSubtotalList() {
		return accessoryQuotedTechnologyAndSubtotalList;
	}
	public void setAccessoryQuotedTechnologyAndSubtotalList(List<AccessoryQuotedTechnologyAndSubtotalList> accessoryQuotedTechnologyAndSubtotalList) {
		this.accessoryQuotedTechnologyAndSubtotalList = accessoryQuotedTechnologyAndSubtotalList;
	}
	
	public List<AccessoryQuotedElseList> getAccessoryQuotedElseList() {
		return accessoryQuotedElseList;
	}
	public void setAccessoryQuotedElseList(List<AccessoryQuotedElseList> accessoryQuotedElseList) {
		this.accessoryQuotedElseList = accessoryQuotedElseList;
	}
	public List<TotalAndCooperativePrice> getTotalAndCooperativePriceList() {
		return totalAndCooperativePriceList;
	}
	public void setTotalAndCooperativePriceList(List<TotalAndCooperativePrice> totalAndCooperativePriceList) {
		this.totalAndCooperativePriceList = totalAndCooperativePriceList;
	}
	public List<BigDecimal> getTotalMaterialList() {
		return totalMaterialList;
	}
	public void setTotalMaterialList(List<BigDecimal> totalMaterialList) {
		this.totalMaterialList = totalMaterialList;
	}
	public List<BigDecimal> getTotalAccessoryList() {
		return totalAccessoryList;
	}
	public void setTotalAccessoryList(List<BigDecimal> totalAccessoryList) {
		this.totalAccessoryList = totalAccessoryList;
	}
	public List<BigDecimal> getTotalPackingList() {
		return totalPackingList;
	}
	public void setTotalPackingList(List<BigDecimal> totalPackingList) {
		this.totalPackingList = totalPackingList;
	}
	public List<BigDecimal> getTotalTechnologyList() {
		return totalTechnologyList;
	}
	public void setTotalTechnologyList(List<BigDecimal> totalTechnologyList) {
		this.totalTechnologyList = totalTechnologyList;
	}
	public List<BigDecimal> getAllTotalList() {
		return allTotalList;
	}
	public void setAllTotalList(List<BigDecimal> allTotalList) {
		this.allTotalList = allTotalList;
	}
	
	public List<String> getCooperativePriceList() {
		return cooperativePriceList;
	}
	public void setCooperativePriceList(List<String> cooperativePriceList) {
		this.cooperativePriceList = cooperativePriceList;
	}
	public List<BigDecimal> getTotalMaterialAccessoryList() {
		return totalMaterialAccessoryList;
	}
	public void setTotalMaterialAccessoryList(List<BigDecimal> totalMaterialAccessoryList) {
		this.totalMaterialAccessoryList = totalMaterialAccessoryList;
	}
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	public String getIntentionSupplierCodeShow() {
		return intentionSupplierCodeShow;
	}
	public void setIntentionSupplierCodeShow(String intentionSupplierCodeShow) {
		this.intentionSupplierCodeShow = intentionSupplierCodeShow;
	}
	public String getIntentionSupplierNameShow() {
		return intentionSupplierNameShow;
	}
	public void setIntentionSupplierNameShow(String intentionSupplierNameShow) {
		this.intentionSupplierNameShow = intentionSupplierNameShow;
	}
	public String getContactsShow() {
		return contactsShow;
	}
	public void setContactsShow(String contactsShow) {
		this.contactsShow = contactsShow;
	}
	public String getPhoneShow() {
		return phoneShow;
	}
	public void setPhoneShow(String phoneShow) {
		this.phoneShow = phoneShow;
	}
	public String getMerchandiseCodeShow() {
		return merchandiseCodeShow;
	}
	public void setMerchandiseCodeShow(String merchandiseCodeShow) {
		this.merchandiseCodeShow = merchandiseCodeShow;
	}
	public String getQuotedCountShow() {
		return quotedCountShow;
	}
	public void setQuotedCountShow(String quotedCountShow) {
		this.quotedCountShow = quotedCountShow;
	}
	public String getQuotedCodeShow() {
		return quotedCodeShow;
	}
	public void setQuotedCodeShow(String quotedCodeShow) {
		this.quotedCodeShow = quotedCodeShow;
	}
	public String getCompanySiteShow() {
		return companySiteShow;
	}
	public void setCompanySiteShow(String companySiteShow) {
		this.companySiteShow = companySiteShow;
	}
	public String getFactorySiteShow() {
		return factorySiteShow;
	}
	public void setFactorySiteShow(String factorySiteShow) {
		this.factorySiteShow = factorySiteShow;
	}
	public String getInvoiceTypeShow() {
		return invoiceTypeShow;
	}
	public void setInvoiceTypeShow(String invoiceTypeShow) {
		this.invoiceTypeShow = invoiceTypeShow;
	}
	public String getTaxRateShow() {
		return taxRateShow;
	}
	public void setTaxRateShow(String taxRateShow) {
		this.taxRateShow = taxRateShow;
	}
	public String getRegisterCapitalShow() {
		return registerCapitalShow;
	}
	public void setRegisterCapitalShow(String registerCapitalShow) {
		this.registerCapitalShow = registerCapitalShow;
	}
	public String getYearTurnoverShow() {
		return yearTurnoverShow;
	}
	public void setYearTurnoverShow(String yearTurnoverShow) {
		this.yearTurnoverShow = yearTurnoverShow;
	}
	public String getFactoryAreaShow() {
		return factoryAreaShow;
	}
	public void setFactoryAreaShow(String factoryAreaShow) {
		this.factoryAreaShow = factoryAreaShow;
	}
	public String getStaffCountShow() {
		return staffCountShow;
	}
	public void setStaffCountShow(String staffCountShow) {
		this.staffCountShow = staffCountShow;
	}
	public String getHzgppShow() {
		return hzgppShow;
	}
	public void setHzgppShow(String hzgppShow) {
		this.hzgppShow = hzgppShow;
	}
	public String getPaymentTypeShow() {
		return paymentTypeShow;
	}
	public void setPaymentTypeShow(String paymentTypeShow) {
		this.paymentTypeShow = paymentTypeShow;
	}
	public String getDeliveryTypeShow() {
		return deliveryTypeShow;
	}
	public void setDeliveryTypeShow(String deliveryTypeShow) {
		this.deliveryTypeShow = deliveryTypeShow;
	}
	public String getDailyCapacityShow() {
		return dailyCapacityShow;
	}
	public void setDailyCapacityShow(String dailyCapacityShow) {
		this.dailyCapacityShow = dailyCapacityShow;
	}
	public String getProofingContentBeforeShow() {
		return proofingContentBeforeShow;
	}
	public void setProofingContentBeforeShow(String proofingContentBeforeShow) {
		this.proofingContentBeforeShow = proofingContentBeforeShow;
	}
	public String getProofingEvaluateBeforeShow() {
		return proofingEvaluateBeforeShow;
	}
	public void setProofingEvaluateBeforeShow(String proofingEvaluateBeforeShow) {
		this.proofingEvaluateBeforeShow = proofingEvaluateBeforeShow;
	}
	public String getProofingContentAfterShow() {
		return proofingContentAfterShow;
	}
	public void setProofingContentAfterShow(String proofingContentAfterShow) {
		this.proofingContentAfterShow = proofingContentAfterShow;
	}
	public String getProofingEvaluateAfterShow() {
		return proofingEvaluateAfterShow;
	}
	public void setProofingEvaluateAfterShow(String proofingEvaluateAfterShow) {
		this.proofingEvaluateAfterShow = proofingEvaluateAfterShow;
	}
	
	public String getQuotedDateShow() {
		return quotedDateShow;
	}
	public void setQuotedDateShow(String quotedDateShow) {
		this.quotedDateShow = quotedDateShow;
	}
}