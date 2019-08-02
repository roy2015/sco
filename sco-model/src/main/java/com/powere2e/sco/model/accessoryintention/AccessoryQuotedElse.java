package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-其他成本实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedElse extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8956309886346435491L;
	private String enquiryCode;//
	private String quotedCode;//
	private String orderCount;//
	private String costType;//
	private BigDecimal cost;//

	
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
	public String getCostType() {
		return costType;
	}
	// 设置
	public void setCostType(String costType) {
		this.costType = costType;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	
	
}