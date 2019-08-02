package com.powere2e.sco.model.accessoryoaapplication;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;
/**
 * 物料信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年5月13日
 */
public class WlInfo extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1644638759567762541L;
	private String applicationCode;//
	private String intentionCode;//
	private String intentionName;//
	private String intentionSupplierCode;//
	private String accessoryCode;//
	private String supplierCode;//
	private String supplierName;//
	private String intentionSupplierName;//
	private String enquiryCode;//
	private String quotedCode;//
	private String enquiryCount;//
	private String purchaseCount;//
	private BigDecimal contractPrice;//
	private BigDecimal purchaseMoney;//
	private String invoiceType;//
	private BigDecimal taxRate;//
	private String accessorySapCode;//
	private String supplierSapCode;//
	private String sjyjSpecification;//

	
	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}
	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
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
	public String getAccessoryCode() {
		return accessoryCode;
	}
	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
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
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	public String getQuotedCode() {
		return quotedCode;
	}
	// 设置
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}
	
	// 获取
	public String getEnquiryCount() {
		return enquiryCount;
	}
	// 设置
	public void setEnquiryCount(String enquiryCount) {
		this.enquiryCount = enquiryCount;
	}
	
	// 获取
	public String getPurchaseCount() {
		return purchaseCount;
	}
	// 设置
	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	
	// 获取
	
	// 获取
	public String getInvoiceType() {
		return invoiceType;
	}
	// 设置
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	
	
	
	public BigDecimal getContractPrice() {
		return contractPrice;
	}
	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}
	public BigDecimal getPurchaseMoney() {
		return purchaseMoney;
	}
	public void setPurchaseMoney(BigDecimal purchaseMoney) {
		this.purchaseMoney = purchaseMoney;
	}
	public BigDecimal getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}
	// 获取
	public String getAccessorySapCode() {
		return accessorySapCode;
	}
	// 设置
	public void setAccessorySapCode(String accessorySapCode) {
		this.accessorySapCode = accessorySapCode;
	}
	
	// 获取
	public String getSupplierSapCode() {
		return supplierSapCode;
	}
	// 设置
	public void setSupplierSapCode(String supplierSapCode) {
		this.supplierSapCode = supplierSapCode;
	}
	
	// 获取
	public String getSjyjSpecification() {
		return sjyjSpecification;
	}
	// 设置
	public void setSjyjSpecification(String sjyjSpecification) {
		this.sjyjSpecification = sjyjSpecification;
	}
	public String getIntentionName() {
		return intentionName;
	}
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}
	
}