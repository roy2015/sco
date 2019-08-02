package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-原材料信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedMaterial extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1380403816747030185L;
	private String enquiryCode;//
	private String quotedCode;//
	private String materialCode;//
	private String materialName;//
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
	public String getMaterialCode() {
		return materialCode;
	}
	// 设置
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
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
	
	// 获取
	public String getPriceUnit() {
		return priceUnit;
	}
	// 设置
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
	public String getMaterialName() {
		return materialName;
	}
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	// 获取
	
}