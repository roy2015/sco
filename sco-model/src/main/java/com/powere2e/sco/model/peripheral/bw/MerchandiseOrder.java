package com.powere2e.sco.model.peripheral.bw;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品订货单信息实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseOrder extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7558420191346720285L;
	private String regionCode;//
	private String warehouseCode;//
	private String warehouseSiteCode;//
	private String merchandiseCode;//
	private String supplierCode;//
	private Date orderDate;//
	private String orderCode;//
	private String orderStatus;
	private BigDecimal orderTotalPrice;//
	private BigDecimal orderQuantity;//
	private Date expectArriveDate;//

	List<MerchandiseOrder> list;

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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
	public String getWarehouseCode() {
		return warehouseCode;
	}

	// 设置
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	// 获取
	public String getWarehouseSiteCode() {
		return warehouseSiteCode;
	}

	// 设置
	public void setWarehouseSiteCode(String warehouseSiteCode) {
		this.warehouseSiteCode = warehouseSiteCode;
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	// 设置
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	// 获取
	public String getOrderCode() {
		return orderCode;
	}

	// 设置
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	// 获取
	public BigDecimal getOrderTotalPrice() {
		return orderTotalPrice;
	}

	// 设置
	public void setOrderTotalPrice(BigDecimal orderTotalPrice) {
		this.orderTotalPrice = orderTotalPrice;
	}

	// 获取
	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	// 设置
	public void setOrderQuantity(BigDecimal orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getExpectArriveDate() {
		return expectArriveDate;
	}

	// 设置
	public void setExpectArriveDate(Date expectArriveDate) {
		this.expectArriveDate = expectArriveDate;
	}

	public List<MerchandiseOrder> getList() {
		return list;
	}

	public void setList(List<MerchandiseOrder> list) {
		this.list = list;
	}

}