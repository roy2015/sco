package com.powere2e.sco.model.masterdata;

import java.util.Date;

/**
 * SAP主数据商品上下市信息
 * 
 * @author lipegjie
 * @since 2016年3月30日
 * @version 1.0
 */
public class MerchandiseMarket {
	private String merchandiseCode; //商品编码
	private String supplierCode; //供应商编码
	private String marketType; //上市/下市
	private Date marketDate; //上市/下市日期
	private Date syncDate; //同步时间
	private String isUpDown; //是否是上下市通知
	
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getMarketType() {
		return marketType;
	}
	public void setMarketType(String marketType) {
		this.marketType = marketType;
	}
	public Date getMarketDate() {
		return marketDate;
	}
	public void setMarketDate(Date marketDate) {
		this.marketDate = marketDate;
	}
	public Date getSyncDate() {
		return syncDate;
	}
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	public String getIsUpDown() {
		return isUpDown;
	}
	public void setIsUpDown(String isUpDown) {
		this.isUpDown = isUpDown;
	}
}
