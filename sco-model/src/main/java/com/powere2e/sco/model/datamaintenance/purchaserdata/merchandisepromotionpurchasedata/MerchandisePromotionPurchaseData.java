package com.powere2e.sco.model.datamaintenance.purchaserdata.merchandisepromotionpurchasedata;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品促销进货价格维护实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年12月9日
 */
public class MerchandisePromotionPurchaseData extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9021412383980667294L;
	private String promotionSchedule;//
	private Date sapUpdateDate;//
	private String promotionCode;//
	private String promotionName;//
	private String merchandiseCode;//
	private String merchandiseName;//
	private String supplierCode;//
	private String supplierName;//
	private String warehouseCode;//
	private String minDate;//
	private String maxDate;//
	private BigDecimal price;//
	private String priceMax;//
	private String syncDate;//

	public String getPriceMax() {
		return priceMax;
	}

	public void setPriceMax(String priceMax) {
		this.priceMax = priceMax;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	// 获取
	public String getPromotionSchedule() {
		return promotionSchedule;
	}

	// 设置
	public void setPromotionSchedule(String promotionSchedule) {
		this.promotionSchedule = promotionSchedule;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSapUpdateDate() {
		return sapUpdateDate;
	}

	// 设置
	public void setSapUpdateDate(Date sapUpdateDate) {
		this.sapUpdateDate = sapUpdateDate;
	}

	// 获取
	public String getPromotionCode() {
		return promotionCode;
	}

	// 设置
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	// 获取
	public String getPromotionName() {
		return promotionName;
	}

	// 设置
	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}

	// 设置
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	// 获取
	public BigDecimal getPrice() {
		return price;
	}

	// 设置
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	// 获取
	public String getSyncDate() {
		return syncDate;
	}

	// 设置
	public void setSyncDate(String syncDate) {
		this.syncDate = syncDate;
	}

	public MerchandisePromotionPurchaseData(String promotionSchedule, String promotionName, String merchandiseCode, String supplierCode, String minDate, String maxDate, BigDecimal price) {
		super();
		this.promotionSchedule = promotionSchedule;
		this.promotionName = promotionName;
		this.merchandiseCode = merchandiseCode;
		this.supplierCode = supplierCode;
		this.minDate = minDate;
		this.maxDate = maxDate;
		this.price = price;
	}

	public MerchandisePromotionPurchaseData() {
		// TODO Auto-generated constructor stub
	}

}