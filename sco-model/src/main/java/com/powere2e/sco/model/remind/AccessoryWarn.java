package com.powere2e.sco.model.remind;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料商品预警
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月30日
 */
public class AccessoryWarn extends AppModel{

	private static final long serialVersionUID = 3663176655822200812L;
	private String warnCode;//预警编号
	private Date warnDate;//预警日期
	private String warnType;//预警方式
	private String relevanceId;//辅料原料关联公示网站原料编号
	private BigDecimal merchandisePurchasePrice;//辅料商品进价
	private BigDecimal supplierPurchasePrice;//供应商原料采购价
	private BigDecimal supplierInputPrice;//供应商原料投入价
	private BigDecimal supplierInputPriceCompute;//供应商原料投入价试算
	private BigDecimal paperWeight;//纸张克重
	private BigDecimal websitePrice;//公示网站原料行情原价
	private BigDecimal websiteLmPrice;//公示网站上月原料行情价
	private BigDecimal merchandisePriceCompute;//辅料商品进价试算
	private BigDecimal merchandisePriceGrowth;//辅料商品进价增长数值
	private BigDecimal merchandisePriceProportion;//辅料商品进价变化百分比
	
	public String getWarnCode() {
		return warnCode;
	}
	public void setWarnCode(String warnCode) {
		this.warnCode = warnCode;
	}
	public Date getWarnDate() {
		return warnDate;
	}
	public void setWarnDate(Date warnDate) {
		this.warnDate = warnDate;
	}
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	public String getRelevanceId() {
		return relevanceId;
	}
	public void setRelevanceId(String relevanceId) {
		this.relevanceId = relevanceId;
	}
	public BigDecimal getMerchandisePurchasePrice() {
		return merchandisePurchasePrice;
	}
	public void setMerchandisePurchasePrice(BigDecimal merchandisePurchasePrice) {
		this.merchandisePurchasePrice = merchandisePurchasePrice;
	}
	public BigDecimal getSupplierPurchasePrice() {
		return supplierPurchasePrice;
	}
	public void setSupplierPurchasePrice(BigDecimal supplierPurchasePrice) {
		this.supplierPurchasePrice = supplierPurchasePrice;
	}
	public BigDecimal getSupplierInputPrice() {
		return supplierInputPrice;
	}
	public void setSupplierInputPrice(BigDecimal supplierInputPrice) {
		this.supplierInputPrice = supplierInputPrice;
	}
	public BigDecimal getSupplierInputPriceCompute() {
		return supplierInputPriceCompute;
	}
	public void setSupplierInputPriceCompute(BigDecimal supplierInputPriceCompute) {
		this.supplierInputPriceCompute = supplierInputPriceCompute;
	}
	public BigDecimal getPaperWeight() {
		return paperWeight;
	}
	public void setPaperWeight(BigDecimal paperWeight) {
		this.paperWeight = paperWeight;
	}
	public BigDecimal getWebsitePrice() {
		return websitePrice;
	}
	public void setWebsitePrice(BigDecimal websitePrice) {
		this.websitePrice = websitePrice;
	}
	public BigDecimal getWebsiteLmPrice() {
		return websiteLmPrice;
	}
	public void setWebsiteLmPrice(BigDecimal websiteLmPrice) {
		this.websiteLmPrice = websiteLmPrice;
	}
	public BigDecimal getMerchandisePriceCompute() {
		return merchandisePriceCompute;
	}
	public void setMerchandisePriceCompute(BigDecimal merchandisePriceCompute) {
		this.merchandisePriceCompute = merchandisePriceCompute;
	}
	public BigDecimal getMerchandisePriceGrowth() {
		return merchandisePriceGrowth;
	}
	public void setMerchandisePriceGrowth(BigDecimal merchandisePriceGrowth) {
		this.merchandisePriceGrowth = merchandisePriceGrowth;
	}
	public BigDecimal getMerchandisePriceProportion() {
		return merchandisePriceProportion;
	}
	public void setMerchandisePriceProportion(BigDecimal merchandisePriceProportion) {
		this.merchandisePriceProportion = merchandisePriceProportion;
	}

}
