package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 物料信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年6月11日
 */
/**
 * @author LPT12017
 *
 */
public class AccessoryQuotedElectronic extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7455163576469300029L;
	private String quotedCode;//
	private String enquiryCode;//
	private String enquiryName;//
	private String intentionCode;//
	private String intentionSupplierName;//
	private String supplierName;//
	private String intentionSupplierCode;//
	private String supplierCode;//
	private Date quotedDate;//
	private String path;//
	private String remarks;//
	private String companyName;//
	private String contacts;//
	private String phone;//
	private String email;//
	private String fax;//
	private String companySite;//
	private String factorySite;//
	private String invoiceType;//
	private BigDecimal taxRate;//
	private String registerCapital;//
	private String yearTurnover;//
	private BigDecimal factoryArea;//
	private BigDecimal staffCount;//
	private String hzgpp;//
	private String paymentType;//
	private String deliveryType;//
	private String dailyCapacity;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private String quotedCurrency;//报价币种

	
	
	public String getQuotedCode() {
		return quotedCode;
	}
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}
	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}
	
	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	
	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}
	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getQuotedDate() {
		return quotedDate;
	}
	// 设置
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}
	
	// 获取
	public String getPath() {
		return path;
	}
	// 设置
	public void setPath(String path) {
		this.path = path;
	}
	
	// 获取
	public String getRemarks() {
		return remarks;
	}
	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	// 获取
	public String getCompanyName() {
		return companyName;
	}
	// 设置
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	// 获取
	public String getContacts() {
		return contacts;
	}
	// 设置
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	// 获取
	public String getPhone() {
		return phone;
	}
	// 设置
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	// 获取
	public String getEmail() {
		return email;
	}
	// 设置
	public void setEmail(String email) {
		this.email = email;
	}
	
	// 获取
	public String getFax() {
		return fax;
	}
	// 设置
	public void setFax(String fax) {
		this.fax = fax;
	}
	
	// 获取
	public String getCompanySite() {
		return companySite;
	}
	// 设置
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}
	
	// 获取
	public String getFactorySite() {
		return factorySite;
	}
	// 设置
	public void setFactorySite(String factorySite) {
		this.factorySite = factorySite;
	}
	
	// 获取
	public String getInvoiceType() {
		return invoiceType;
	}
	// 设置
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
	// 获取
	public String getHzgpp() {
		return hzgpp;
	}
	// 设置
	public void setHzgpp(String hzgpp) {
		this.hzgpp = hzgpp;
	}
	
	// 获取
	public String getPaymentType() {
		return paymentType;
	}
	// 设置
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	
	// 获取
	public String getDeliveryType() {
		return deliveryType;
	}
	// 设置
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}
	
	// 获取
	public String getDailyCapacity() {
		return dailyCapacity;
	}
	// 设置
	public void setDailyCapacity(String dailyCapacity) {
		this.dailyCapacity = dailyCapacity;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}
	// 设置
	public void setCreated(Date created) {
		this.created = created;
	}
	
	// 获取
	public String getCreateby() {
		return createby;
	}
	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getUpdated() {
		return updated;
	}
	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	// 获取
	public String getUpdateby() {
		return updateby;
	}
	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getEnquiryName() {
		return enquiryName;
	}
	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
	}
	public String getQuotedCurrency() {
		return quotedCurrency;
	}
	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}
	
}