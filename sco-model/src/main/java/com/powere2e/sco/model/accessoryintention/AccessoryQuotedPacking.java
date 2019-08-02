package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料报价-内外包装信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedPacking extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2429237761707286325L;
	private String enquiryCode;//
	private String quotedCode;//
	private String packingCode;//
	private String packingName;//
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
	public String getPackingCode() {
		return packingCode;
	}
	// 设置
	public void setPackingCode(String packingCode) {
		this.packingCode = packingCode;
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
	public String getPackingName() {
		return packingName;
	}
	public void setPackingName(String packingName) {
		this.packingName = packingName;
	}
	
	
}