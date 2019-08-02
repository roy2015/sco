package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料意向品实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月20日
 */
public class AccessoryIntentionSupplier extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7871325930414434532L;
	private String intentionSupplierCode;//
	private String intentionSupplierName;//
	private String companyName;//公司名称
	private String contacts;//联系人
	private String phone;//电话
	private String email;//邮箱
	private String fax;//传真
	private String site;//地址
	private String invoiceType;//发票类型
	private BigDecimal taxRate;//税率
	private BigDecimal registerCapital;//注册资本
	private BigDecimal yearTurnover;//年营业额
	private BigDecimal factoryArea;//工厂面积
	private BigDecimal staffCount;//工人数
	private String hzgpp;//合作过品牌
	private String paymentType;//付款方式
	private String deliveryType;//交货方式
	

	
	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	
	// 获取
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	// 设置
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
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
	public String getSite() {
		return site;
	}
	// 设置
	public void setSite(String site) {
		this.site = site;
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
	public BigDecimal getRegisterCapital() {
		return registerCapital;
	}
	public void setRegisterCapital(BigDecimal registerCapital) {
		this.registerCapital = registerCapital;
	}
	public BigDecimal getYearTurnover() {
		return yearTurnover;
	}
	public void setYearTurnover(BigDecimal yearTurnover) {
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
	
	
}