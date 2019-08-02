package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-辅料信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedAccessory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9141380485965380130L;
	private String enquiryCode;//
	private String quotedCode;//
	private String accessoryCode;//
	private String accessoryName;//
	private String orderCount;//
	private String brand;//
	private String origin;//
	private String priceUnit;//
	private BigDecimal price;//
	private String wastage;//
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
	public String getAccessoryCode() {
		return accessoryCode;
	}
	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}
	
	
	// 获取
	public String getBrand() {
		return brand;
	}
	// 设置
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	// 获取
	public String getOrigin() {
		return origin;
	}
	// 设置
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(String orderCount) {
		this.orderCount = orderCount;
	}
	public String getPriceUnit() {
		return priceUnit;
	}
	public void setPriceUnit(String priceUnit) {
		this.priceUnit = priceUnit;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public String getWastage() {
		return wastage;
	}
	public void setWastage(String wastage) {
		this.wastage = wastage;
	}
	public BigDecimal getCost() {
		return cost;
	}
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	
	
}