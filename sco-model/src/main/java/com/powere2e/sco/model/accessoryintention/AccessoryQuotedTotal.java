package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-总报价实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTotal extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1044337626089565390L;
	private String enquiryCode;//
	private String quotedCode;//
	private String orderCount;//
	private BigDecimal proofingCycle;//
	private String dailyCapacity;//
	private BigDecimal productionCycle;//
	private BigDecimal unitPrice;//
	private String supplierCode;//

	
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
	public String getOrderCount() {
		return orderCount;
	}
	// 设置
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	
	// 获取
	public String getDailyCapacity() {
		return dailyCapacity;
	}
	// 设置
	public void setDailyCapacity(String dailyCapacity) {
		this.dailyCapacity = dailyCapacity;
	}
	public BigDecimal getProofingCycle() {
		return proofingCycle;
	}
	public void setProofingCycle(BigDecimal proofingCycle) {
		this.proofingCycle = proofingCycle;
	}
	public BigDecimal getProductionCycle() {
		return productionCycle;
	}
	public void setProductionCycle(BigDecimal productionCycle) {
		this.productionCycle = productionCycle;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	
}