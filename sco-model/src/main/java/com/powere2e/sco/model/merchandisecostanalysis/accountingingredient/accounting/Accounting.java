package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品核算表实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class Accounting extends AppModel {

	private static final long serialVersionUID = -984298611853859377L;
	private String accountingCode;// 核算表编号
	private String inlandImport;// 国内/进口
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向供应商编号
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private String quotedCurrency;// 报价币种
	private Date quotedDate;// 报价日期
	private String factoryPriceType;// 出厂价类型
	private String factoryPriceTypeElse;// 出厂价类型其他名称
	private String units;// 单位
	private BigDecimal quantity;// 量
	private String importCountry;// 进口国
	private String packRegion;// 分装地
	private String isoem;// 是否OEM

	private String merchandiseName;// 商品名称
	private String supplierName;// 供应商名称
	private String intentionName;// 意向品名称
	private String intentionSupplierName;// 意向供应商名称

	// 获取
	public String getAccountingCode() {
		return accountingCode;
	}

	// 设置
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	// 获取
	public String getInlandImport() {
		return inlandImport;
	}

	// 设置
	public void setInlandImport(String inlandImport) {
		this.inlandImport = inlandImport;
	}

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
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
	public String getQuotedCurrency() {
		return quotedCurrency;
	}

	// 设置
	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQuotedDate() {
		return quotedDate;
	}

	// 设置
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	// 获取
	public String getFactoryPriceType() {
		return factoryPriceType;
	}

	public String getFactoryPriceTypeElse() {
		return factoryPriceTypeElse;
	}

	public void setFactoryPriceTypeElse(String factoryPriceTypeElse) {
		this.factoryPriceTypeElse = factoryPriceTypeElse;
	}

	// 设置
	public void setFactoryPriceType(String factoryPriceType) {
		this.factoryPriceType = factoryPriceType;
	}

	// 获取
	public String getUnits() {
		return units;
	}

	// 设置
	public void setUnits(String units) {
		this.units = units;
	}

	// 获取
	public BigDecimal getQuantity() {
		return quantity;
	}

	// 设置
	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	// 获取
	public String getImportCountry() {
		return importCountry;
	}

	// 设置
	public void setImportCountry(String importCountry) {
		this.importCountry = importCountry;
	}

	// 获取
	public String getPackRegion() {
		return packRegion;
	}

	// 设置
	public void setPackRegion(String packRegion) {
		this.packRegion = packRegion;
	}

	// 获取
	public String getIsoem() {
		return isoem;
	}

	// 设置
	public void setIsoem(String isoem) {
		this.isoem = isoem;
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

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

}