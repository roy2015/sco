package com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.accessorystockdetail;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 进货明细实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年7月2日
 */
public class AccessoryStockDetail extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3295557033419103087L;
	private String merchandiseCode;// 商品编码
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String fineTypeCode;// 细分类ID
	private String fineName;// 细分类名称
	private String maxDate;// 最大日期
	private String minDate;// 最小日期
	private String type;// 查看日月明细
	private String regionCode;// 地区编号
	private String warehouseCode;// 工厂编号
	private Integer receiptRationed;// 公司总到货量
	private BigDecimal receiptTotalPrice;// 公司总到货额
	private Integer receiptRationedM;// 商品到货量
	private BigDecimal receiptTotalPriceM;// 商品到货额
	private BigDecimal receiptRationedMA;// 到货量占公司整体比重
	private BigDecimal receiptTotalPriceMA;// 到货额占公司整体比重
	private String realityReceiptDate;// 到货日期

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getFineTypeCode() {
		return fineTypeCode;
	}

	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	public String getFineName() {
		return fineName;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getReceiptRationedM() {
		return receiptRationedM;
	}

	public void setReceiptRationedM(Integer receiptRationedM) {
		this.receiptRationedM = receiptRationedM;
	}

	public BigDecimal getReceiptTotalPriceM() {
		return receiptTotalPriceM;
	}

	public void setReceiptTotalPriceM(BigDecimal receiptTotalPriceM) {
		this.receiptTotalPriceM = receiptTotalPriceM;
	}

	public BigDecimal getReceiptRationedMA() {
		return receiptRationedMA;
	}

	public void setReceiptRationedMA(BigDecimal receiptRationedMA) {
		this.receiptRationedMA = receiptRationedMA;
	}

	public BigDecimal getReceiptTotalPriceMA() {
		return receiptTotalPriceMA;
	}

	public void setReceiptTotalPriceMA(BigDecimal receiptTotalPriceMA) {
		this.receiptTotalPriceMA = receiptTotalPriceMA;
	}

	public void setReceiptTotalPrice(BigDecimal receiptTotalPrice) {
		this.receiptTotalPrice = receiptTotalPrice;
	}

	public void setRealityReceiptDate(String realityReceiptDate) {
		this.realityReceiptDate = realityReceiptDate;
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

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
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
	public Integer getReceiptRationed() {
		return receiptRationed;
	}

	// 设置
	public void setReceiptRationed(Integer receiptRationed) {
		this.receiptRationed = receiptRationed;
	}

	public BigDecimal getReceiptTotalPrice() {
		return receiptTotalPrice;
	}

	public String getRealityReceiptDate() {
		return realityReceiptDate;
	}

}