package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class SupplierForm extends AppModel implements Cloneable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8999873153874350045L;

	private String intentionSupplierCode;//
	private String intentionSupplierName;//
	private String companyName;// 公司名称
	private String contacts;// 联系人
	private String phone;// 电话
	private String email;// 邮箱
	private String fax;// 传真
	private String companySite;//
	private String factorySite;//
	private String invoiceType;// 发票类型
	private String quotedCurrency;// 报价币种
	private BigDecimal taxRate;// 税率
	private String registerCapital;// 注册资本
	private String yearTurnover;// 年营业额
	private BigDecimal factoryArea;// 工厂面积
	private BigDecimal staffCount;// 工人数
	private String hzgpp;// 合作过品牌
	private String paymentType;// 付款方式
	private String deliveryType;// 交货方式
	private String dailyCapacity;// 日产能
	private String proofingContentBefore;// 打样内容前
	private String proofingEvaluateBefore;// 打样评价前
	private String proofingContentAfter;// 打样内容后
	private String proofingEvaluateAfter;// 打样评价后

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

	
	public String getQuotedCurrency() {
		return quotedCurrency;
	}

	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}

	public String getQuotedCurrencyShow() {
		return quotedCurrencyShow;
	}

	public void setQuotedCurrencyShow(String quotedCurrencyShow) {
		this.quotedCurrencyShow = quotedCurrencyShow;
	}

	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getCompanySite() {
		return companySite;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	public String getFactorySite() {
		return factorySite;
	}

	public void setFactorySite(String factorySite) {
		this.factorySite = factorySite;
	}

	public String getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	

	public String getRegisterCapital() {
		return registerCapital;
	}

	public void setRegisterCapital(String registerCapital) {
		this.registerCapital = registerCapital;
	}

	public String getYearTurnover() {
		return yearTurnover;
	}

	public void setYearTurnover(String yearTurnover) {
		this.yearTurnover = yearTurnover;
	}

	public BigDecimal getFactoryArea() {
		return factoryArea;
	}

	public void setFactoryArea(BigDecimal factoryArea) {
		this.factoryArea = factoryArea;
	}

	public BigDecimal getStaffCount() {
		return staffCount;
	}

	public void setStaffCount(BigDecimal staffCount) {
		this.staffCount = staffCount;
	}

	public String getHzgpp() {
		return hzgpp;
	}

	public void setHzgpp(String hzgpp) {
		this.hzgpp = hzgpp;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDailyCapacity() {
		return dailyCapacity;
	}

	public void setDailyCapacity(String dailyCapacity) {
		this.dailyCapacity = dailyCapacity;
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


	public String getProofingContentBefore() {
		return proofingContentBefore;
	}

	public void setProofingContentBefore(String proofingContentBefore) {
		this.proofingContentBefore = proofingContentBefore;
	}

	public String getProofingEvaluateBefore() {
		return proofingEvaluateBefore;
	}

	public void setProofingEvaluateBefore(String proofingEvaluateBefore) {
		this.proofingEvaluateBefore = proofingEvaluateBefore;
	}

	public String getProofingContentAfter() {
		return proofingContentAfter;
	}

	public void setProofingContentAfter(String proofingContentAfter) {
		this.proofingContentAfter = proofingContentAfter;
	}

	public String getProofingEvaluateAfter() {
		return proofingEvaluateAfter;
	}

	public void setProofingEvaluateAfter(String proofingEvaluateAfter) {
		this.proofingEvaluateAfter = proofingEvaluateAfter;
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

	public Object clone() {
		SupplierForm o = null;
		try {
			o = (SupplierForm) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return o;
	}
}