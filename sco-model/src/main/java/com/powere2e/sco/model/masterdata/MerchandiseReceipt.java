package com.powere2e.sco.model.masterdata;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

/**
 * 商品收货单信息 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月13日
 */
public class MerchandiseReceipt extends Merchandise {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4347830195483776443L;
	private String regionCode;// 区域编码
	private String warehouseCode;// 仓库编码
	private String warehouseSiteCode;// 仓位编码
	private Date orderDate;// 订单时间
	private String orderCode;// 订单编号
	private BigDecimal receiptRationed;// 到货数量
	private BigDecimal receiptTotalPrice;// 到货总金额
	private Date realityReceiptDate;// 实际到货时间

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
	@JSON(format = "yyyy-MM-dd")
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

	public BigDecimal getReceiptRationed() {
		return receiptRationed;
	}

	public void setReceiptRationed(BigDecimal receiptRationed) {
		this.receiptRationed = receiptRationed;
	}

	public BigDecimal getReceiptTotalPrice() {
		return receiptTotalPrice;
	}

	public void setReceiptTotalPrice(BigDecimal receiptTotalPrice) {
		this.receiptTotalPrice = receiptTotalPrice;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getRealityReceiptDate() {
		return realityReceiptDate;
	}

	// 设置
	public void setRealityReceiptDate(Date realityReceiptDate) {
		this.realityReceiptDate = realityReceiptDate;
	}
}