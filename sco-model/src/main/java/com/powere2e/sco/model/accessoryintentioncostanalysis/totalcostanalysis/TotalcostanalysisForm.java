package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class TotalcostanalysisForm extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2013295742012639670L;

	private List<SupplierForm> supplierFormList;// 供应商信息list
	private List<IntentionForm> intentionFormList;// 意向品list
	private List<IntentionTotalForm> intentionTotalFormList;// 意向品list
	// private List<List<QuotedForm>> quotedFormListList;
	private String json;
	private String remark;
	private Boolean allbjd;
	
	private String intentionSupplierCodeShow;//
	private String intentionSupplierNameShow;//
	private String companyNameShow;// 公司名称
	private String contactsShow;// 联系人
	private String phoneShow;// 电话
	private String emailShow;// 邮箱
	private String faxShow;// 传真
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
	private String quotedCurrencyShow;// 报价币种
	
	private Integer showLine;

	public List<SupplierForm> getSupplierFormList() {
		return supplierFormList;
	}

	public void setSupplierFormList(List<SupplierForm> supplierFormList) {
		this.supplierFormList = supplierFormList;
	}

	public List<IntentionForm> getIntentionFormList() {
		return intentionFormList;
	}

	public void setIntentionFormList(List<IntentionForm> intentionFormList) {
		this.intentionFormList = intentionFormList;
	}

	public List<IntentionTotalForm> getIntentionTotalFormList() {
		return intentionTotalFormList;
	}

	public void setIntentionTotalFormList(List<IntentionTotalForm> intentionTotalFormList) {
		this.intentionTotalFormList = intentionTotalFormList;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Boolean getAllbjd() {
		return allbjd;
	}

	public void setAllbjd(Boolean allbjd) {
		this.allbjd = allbjd;
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

	public String getCompanyNameShow() {
		return companyNameShow;
	}

	public void setCompanyNameShow(String companyNameShow) {
		this.companyNameShow = companyNameShow;
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

	public String getEmailShow() {
		return emailShow;
	}

	public void setEmailShow(String emailShow) {
		this.emailShow = emailShow;
	}

	public String getFaxShow() {
		return faxShow;
	}

	public void setFaxShow(String faxShow) {
		this.faxShow = faxShow;
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

	public String getQuotedCurrencyShow() {
		return quotedCurrencyShow;
	}

	public void setQuotedCurrencyShow(String quotedCurrencyShow) {
		this.quotedCurrencyShow = quotedCurrencyShow;
	}

	public Integer getShowLine() {
		return showLine;
	}

	public void setShowLine(Integer showLine) {
		this.showLine = showLine;
	}

}