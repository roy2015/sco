package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 历史与本次价格(老品新上)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class HistoryPriceOldup extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 754776712691421670L;
	private String reportCode;//
	private String piceType;//
	private String stockSite;//
	private BigDecimal purchasePrice;//
	private String purchaseUnits;//
	private String sellRegion;//
	private BigDecimal sellPrice;//
	private String sellUnits;//
	private BigDecimal profitRate;//毛利率
	
	private String profitRatePercent;// 毛利率百分比(为了页面显示)
	private String purchasePricePercent;// 采购价格百分比(为了页面显示)
	private String sellPricePercent;// 销售价格百分比(为了页面显示)

	// 获取
	public String getReportCode() {
		return reportCode;
	}

	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	// 获取
	public String getPiceType() {
		return piceType;
	}

	// 设置
	public void setPiceType(String piceType) {
		this.piceType = piceType;
	}

	// 获取
	public String getStockSite() {
		return stockSite;
	}

	// 设置
	public void setStockSite(String stockSite) {
		this.stockSite = stockSite;
	}

	// 获取
	public String getPurchaseUnits() {
		return purchaseUnits;
	}

	// 设置
	public void setPurchaseUnits(String purchaseUnits) {
		this.purchaseUnits = purchaseUnits;
	}

	// 获取
	public String getSellRegion() {
		return sellRegion;
	}

	// 设置
	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	// 获取
	public String getSellUnits() {
		return sellUnits;
	}

	// 设置
	public void setSellUnits(String sellUnits) {
		this.sellUnits = sellUnits;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public void setProfitRate(BigDecimal profitRate) {
		this.profitRate = profitRate;
	}

	public String getProfitRatePercent() {
		return profitRatePercent;
	}

	public void setProfitRatePercent(String profitRatePercent) {
		this.profitRatePercent = profitRatePercent;
	}

	public String getPurchasePricePercent() {
		return purchasePricePercent;
	}

	public void setPurchasePricePercent(String purchasePricePercent) {
		this.purchasePricePercent = purchasePricePercent;
	}

	public String getSellPricePercent() {
		return sellPricePercent;
	}

	public void setSellPricePercent(String sellPricePercent) {
		this.sellPricePercent = sellPricePercent;
	}

}