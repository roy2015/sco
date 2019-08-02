package com.powere2e.sco.model.peripheral.sap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品促销销售价实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePromotionSell extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5883295234434635607L;
	private String merchandiseCode;//
	private String supplierCode;//
	private String promotionCode;//
	private String promotionName;//
	private String regionCode;//
	private Date startDate;//
	private Date endDate;//
	private BigDecimal price;//
	private Date sapUpdateDate;//
	private Date syncDate;//

	private List<MerchandisePromotionSell> list;

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
	public String getRegionCode() {
		return regionCode;
	}

	// 设置
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getStartDate() {
		return startDate;
	}

	// 设置
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getEndDate() {
		return endDate;
	}

	// 设置
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSapUpdateDate() {
		return sapUpdateDate;
	}

	// 设置
	public void setSapUpdateDate(Date sapUpdateDate) {
		this.sapUpdateDate = sapUpdateDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	// 设置
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandisePromotionSell> getList() {
		return list;
	}

	public void setList(List<MerchandisePromotionSell> list) {
		this.list = list;
	}

}