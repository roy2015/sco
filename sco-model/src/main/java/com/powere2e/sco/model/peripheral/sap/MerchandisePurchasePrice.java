package com.powere2e.sco.model.peripheral.sap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品进货价格信息实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePurchasePrice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7115775207425295888L;
	private String merchandiseCode;//
	private String supplierCode;//
	private String warehouseCode;//
	private BigDecimal purchasePrice;//
	private Date priceDate;//
	private Date syncDate;//

	private List<MerchandisePurchasePrice> list;

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
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	// 设置
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPriceDate() {
		return priceDate;
	}

	// 设置
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
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

	public List<MerchandisePurchasePrice> getList() {
		return list;
	}

	public void setList(List<MerchandisePurchasePrice> list) {
		this.list = list;
	}

}