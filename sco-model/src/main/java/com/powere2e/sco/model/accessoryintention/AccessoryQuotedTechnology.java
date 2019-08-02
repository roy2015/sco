package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-工艺信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedTechnology extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4355145930197403936L;
	private String enquiryCode;//
	private String quotedCode;//
	private String technologyCode;//
	private String technologyName;//
	private String orderCount;//
	private String priceUnit;//
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
	public String getTechnologyCode() {
		return technologyCode;
	}
	// 设置
	public void setTechnologyCode(String technologyCode) {
		this.technologyCode = technologyCode;
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
	public String getPriceUnit() {
		return priceUnit;
	}
	// 设置
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getTechnologyName() {
		return technologyName;
	}
	public void setTechnologyName(String technologyName) {
		this.technologyName = technologyName;
	}
	
	
}