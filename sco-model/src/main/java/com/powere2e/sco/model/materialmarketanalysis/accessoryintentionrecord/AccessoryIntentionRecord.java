package com.powere2e.sco.model.materialmarketanalysis.accessoryintentionrecord;

import java.math.BigDecimal;

import com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord.MaterialWarningRecord;

/**
 * 辅料商品预警记录 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月13日
 * @version 1.0
 */
public class AccessoryIntentionRecord extends MaterialWarningRecord {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5396685071044614521L;
	
	private String merchandiseCode;//辅料意向品编号
	private String merchandiseName;//辅料名称
	private String supplierCode;//辅料意向品供应商编号
	private String supplierName;//辅料意向品供应商名称
	private String fineType;//细分类
	private String fineName;//细分类名称
	private String accessoryName;// 辅料名称
	private BigDecimal merPurPrice;// 辅料商品进价
	private BigDecimal supPurPrice;// 供应商原料采购价
	private BigDecimal paperWeight;// 纸张克重
	private BigDecimal supInpPrice;// 供应商原料投入价
	private BigDecimal websitePrice;// 公示网站原料行情原价
	private BigDecimal webLmPrice;// 公示网站上月原料行情价
	private BigDecimal supInpCom;// 供应商原料投入价试算
	private BigDecimal merPriceCom;// 辅料商品进价试算
	private BigDecimal merPriceGrowth;// 辅料商品进价增长数值
	private BigDecimal merPricePro;// 辅料商品进价变化百分比
	private String recordType;// 查询记录数据类型

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getFineName() {
		return fineName;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
	}

	public String getFineType() {
		return fineType;
	}

	public void setFineType(String fineType) {
		this.fineType = fineType;
	}

	public String getAccessoryName() {
		return accessoryName;
	}

	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	public BigDecimal getMerPurPrice() {
		return merPurPrice;
	}

	public void setMerPurPrice(BigDecimal merPurPrice) {
		this.merPurPrice = merPurPrice;
	}

	public BigDecimal getSupPurPrice() {
		return supPurPrice;
	}

	public void setSupPurPrice(BigDecimal supPurPrice) {
		this.supPurPrice = supPurPrice;
	}

	public BigDecimal getPaperWeight() {
		return paperWeight;
	}

	public void setPaperWeight(BigDecimal paperWeight) {
		this.paperWeight = paperWeight;
	}

	public BigDecimal getSupInpPrice() {
		return supInpPrice;
	}

	public void setSupInpPrice(BigDecimal supInpPrice) {
		this.supInpPrice = supInpPrice;
	}

	public BigDecimal getWebsitePrice() {
		return websitePrice;
	}

	public void setWebsitePrice(BigDecimal websitePrice) {
		this.websitePrice = websitePrice;
	}

	public BigDecimal getWebLmPrice() {
		return webLmPrice;
	}

	public void setWebLmPrice(BigDecimal webLmPrice) {
		this.webLmPrice = webLmPrice;
	}

	public BigDecimal getSupInpCom() {
		return supInpCom;
	}

	public void setSupInpCom(BigDecimal supInpCom) {
		this.supInpCom = supInpCom;
	}

	public BigDecimal getMerPriceCom() {
		return merPriceCom;
	}

	public void setMerPriceCom(BigDecimal merPriceCom) {
		this.merPriceCom = merPriceCom;
	}

	public BigDecimal getMerPriceGrowth() {
		return merPriceGrowth;
	}

	public void setMerPriceGrowth(BigDecimal merPriceGrowth) {
		this.merPriceGrowth = merPriceGrowth;
	}

	public BigDecimal getMerPricePro() {
		return merPricePro;
	}

	public void setMerPricePro(BigDecimal merPricePro) {
		this.merPricePro = merPricePro;
	}

	public String getRecordType() {
		return recordType;
	}

	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
}
