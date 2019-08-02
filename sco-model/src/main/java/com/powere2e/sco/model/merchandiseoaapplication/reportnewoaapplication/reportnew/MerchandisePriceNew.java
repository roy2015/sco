package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品价格(新品引进)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月27日
 */
public class MerchandisePriceNew extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5449666117799343907L;
	private String reportCode;// 报告编号
	private String stockSite;// 进货地区
	private BigDecimal purchasePrice;// 采购价格
	private String purchaseUnits;// 采购单位
	private String sellRegion;// 销售地区
	private BigDecimal sellPrice;// 销售价格
	private String sellUnits;// 销售单位
	private BigDecimal profitRate;// 毛利率
	
	private String profitRatePercent;// 毛利率显示字段(方便页面显示)

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	public String getStockSite() {
		return stockSite;
	}

	public void setStockSite(String stockSite) {
		this.stockSite = stockSite;
	}

	public String getPurchaseUnits() {
		return purchaseUnits;
	}

	public void setPurchaseUnits(String purchaseUnits) {
		this.purchaseUnits = purchaseUnits;
	}

	public String getSellRegion() {
		return sellRegion;
	}

	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	public String getSellUnits() {
		return sellUnits;
	}

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

}