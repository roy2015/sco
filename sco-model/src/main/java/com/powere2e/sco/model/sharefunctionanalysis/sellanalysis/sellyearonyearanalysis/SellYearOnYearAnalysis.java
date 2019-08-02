package com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品销售同比分析实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年6月1日
 */
public class SellYearOnYearAnalysis extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9185722789144789760L;
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private String directJoin;// 直营\加盟
	private String sellRegion;// 销售地区编号
	private String sellMonth;// 销售月份(YYYY-MM)
	private BigDecimal sellQuantity;// 销售量
	private BigDecimal sellQuantityChange;// 销售量同比
	private BigDecimal sellTotalPrice;// 销售额
	private BigDecimal sellTotalPriceChange;// 销售额同比
	private BigDecimal sellProfit;// 毛利额
	private BigDecimal sellProfitChange;// 毛利额同比
	private BigDecimal psdSellQuantity;// PSD销量
	private BigDecimal psdSellQuantityChange;// PSD销量同比
	private BigDecimal psdSellTotalPrice;// PSD销售额
	private BigDecimal psdSellTotalPriceChange;// PSD销售额同比
	private BigDecimal psdSellProfit;// PSD毛利额
	private BigDecimal psdSellProfitChange;// PSD毛利额同比

	private String lastSellMonth;// 去年销售月份(YYYY-MM)
	private BigDecimal lastSellQuantity;// 去年销售量
	private BigDecimal lastSellQuantityChange;// 去年销售量同比
	private BigDecimal lastSellTotalPrice;// 去年销售额
	private BigDecimal lastSellTotalPriceChange;// 去年销售额同比
	private BigDecimal lastSellProfit;// 去年毛利额
	private BigDecimal lastSellProfitChange;// 去年毛利额同比
	private BigDecimal lastPsdSellQuantity;// 去年PSD销量
	private BigDecimal lastPsdSellQuantityChange;// 去年PSD销量同比
	private BigDecimal lastPsdSellTotalPrice;// 去年PSD销售额
	private BigDecimal lastPsdSellTotalPriceChange;// 去年PSD销售额同比
	private BigDecimal lastPsdSellProfit;// 去年PSD毛利额
	private BigDecimal lastPsdSellProfitChange;// 去年PSD毛利额同比

	private String beforeSellMonth;// 前年销售月份(YYYY-MM)
	private BigDecimal beforeSellQuantity;// 前年销售量
	private BigDecimal beforeSellQuantityChange;// 前年销售量同比
	private BigDecimal beforeSellTotalPrice;// 前年销售额
	private BigDecimal beforeSellTotalPriceChange;// 前年销售额同比
	private BigDecimal beforeSellProfit;// 前年毛利额
	private BigDecimal beforeSellProfitChange;// 前年毛利额同比
	private BigDecimal beforePsdSellQuantity;// 前年PSD销量
	private BigDecimal beforePsdSellQuantityChange;// 前年PSD销量同比
	private BigDecimal beforePsdSellTotalPrice;// 前年PSD销售额
	private BigDecimal beforePsdSellTotalPriceChange;// 前年PSD销售额同比
	private BigDecimal beforePsdSellProfit;// 前年PSD毛利额
	private BigDecimal beforePsdSellProfitChange;// 前年PSD毛利额同比

	private String fourthSellMonth;// 大前年销售月份(YYYY-MM)
	private BigDecimal fourthSellQuantity;// 大前年销售量
	private BigDecimal fourthSellQuantityChange;// 大前年销售量同比
	private BigDecimal fourthSellTotalPrice;// 大前年销售额
	private BigDecimal fourthSellTotalPriceChange;// 大前年销售额同比
	private BigDecimal fourthSellProfit;// 大前年毛利额
	private BigDecimal fourthSellProfitChange;// 大前年毛利额同比
	private BigDecimal fourthPsdSellQuantity;// 大前年PSD销量
	private BigDecimal fourthPsdSellQuantityChange;// 大前年PSD销量同比
	private BigDecimal fourthPsdSellTotalPrice;// 大前年PSD销售额
	private BigDecimal fourthPsdSellTotalPriceChange;// 大前年PSD销售额同比
	private BigDecimal fourthPsdSellProfit;// 大前年PSD毛利额
	private BigDecimal fourthPsdSellProfitChange;// 大前年PSD毛利额同比

	private String fifthSellMonth;// 第五年销售月份(YYYY-MM)
	private BigDecimal fifthSellQuantity;// 第五年销售量
	private BigDecimal fifthSellQuantityChange;// 第五年销售量同比
	private BigDecimal fifthSellTotalPrice;// 第五年销售额
	private BigDecimal fifthSellTotalPriceChange;// 第五年销售额同比
	private BigDecimal fifthSellProfit;// 第五年毛利额
	private BigDecimal fifthSellProfitChange;// 第五年毛利额同比
	private BigDecimal fifthPsdSellQuantity;// 第五年PSD销量
	private BigDecimal fifthPsdSellQuantityChange;// 第五年PSD销量同比
	private BigDecimal fifthPsdSellTotalPrice;// 第五年PSD销售额
	private BigDecimal fifthPsdSellTotalPriceChange;// 第五年PSD销售额同比
	private BigDecimal fifthPsdSellProfit;// 第五年PSD毛利额
	private BigDecimal fifthPsdSellProfitChange;// 第五年PSD毛利额同比

	/*private BigDecimal sumSellQuantity;// 第一年总销售量
	private BigDecimal sumSellQuantityChange;// 第一年总销售量同比
	private BigDecimal sumSellTotalPrice;// 第一年总销售额
	private BigDecimal sumSellTotalPriceChange;// 第一年总销售额同比
	private BigDecimal sumSellProfit;// 第一年总毛利额
	private BigDecimal sumSellProfitChange;// 第一年总毛利额同比
	private BigDecimal sumPsdSellQuantity;// 第一年总PSD销量
	private BigDecimal sumPsdSellQuantityChange;// 第一年总PSD销量同比
	private BigDecimal sumPsdSellTotalPrice;// 第一年总PSD销售额
	private BigDecimal sumPsdSellTotalPriceChange;// 第一年总PSD销售额同比
	private BigDecimal sumPsdSellProfit;// 第一年总PSD毛利额
	private BigDecimal sumPsdSellProfitChange;// 第一年总PSD毛利额同比

	private BigDecimal lastSumSellQuantity;// 第二年总销售量
	private BigDecimal lastSumSellQuantityChange;// 第二年总销售量同比
	private BigDecimal lastSumSellTotalPrice;// 第二年总销售额
	private BigDecimal lastSumSellTotalPriceChange;// 第二年总销售额同比
	private BigDecimal lastSumSellProfit;// 第二年总毛利额
	private BigDecimal lastSumSellProfitChange;// 第二年总毛利额同比
	private BigDecimal lastSumPsdSellQuantity;// 第二年总PSD销量
	private BigDecimal lastSumPsdSellQuantityChange;// 第二年总PSD销量同比
	private BigDecimal lastSumPsdSellTotalPrice;// 第二年总PSD销售额
	private BigDecimal lastSumPsdSellTotalPriceChange;// 第二年总PSD销售额同比
	private BigDecimal lastSumPsdSellProfit;// 第二年总PSD毛利额
	private BigDecimal lastSumPsdSellProfitChange;// 第二年总PSD毛利额同比

	private BigDecimal beforeSumSellQuantity;// 第三年总销售量
	private BigDecimal befSumSellQuantityChange;// 第三年总销售量同比
	private BigDecimal beforeSumSellTotalPrice;// 第三年总销售额
	private BigDecimal befSumSellTotalPriceChange;// 第三年总销售额同比
	private BigDecimal beforeSumSellProfit;// 第三年总毛利额
	private BigDecimal befSumSellProfitChange;// 第三年总毛利额同比
	private BigDecimal beforeSumPsdSellQuantity;// 第三年总PSD销量
	private BigDecimal befSumPsdSellQuantityChange;// 第三年总PSD销量同比
	private BigDecimal beforeSumPsdSellTotalPrice;// 第三年总PSD销售额
	private BigDecimal befSumPsdSellTotalPriceChange;// 第三年总PSD销售额同比
	private BigDecimal beforeSumPsdSellProfit;// 第三年总PSD毛利额
	private BigDecimal befSumPsdSellProfitChange;// 第三年总PSD毛利额同比

	private BigDecimal fourthSumSellQuantity;// 第四年总销售量
	private BigDecimal fourSumSellQuantityChange;// 第四年总销售量同比
	private BigDecimal fourthSumSellTotalPrice;// 第四年总销售额
	private BigDecimal fourSumSellTotalPriceChange;// 第四年总销售额同比
	private BigDecimal fourthSumSellProfit;// 第四年总毛利额
	private BigDecimal fourSumSellProfitChange;// 第四年总毛利额同比
	private BigDecimal fourthSumPsdSellQuantity;// 第四年总PSD销量
	private BigDecimal fourSumPsdSellQuantityChange;// 第四年总PSD销量同比
	private BigDecimal fourthSumPsdSellTotalPrice;// 第四年总PSD销售额
	private BigDecimal fourSumPsdSellTotalPriceChange;// 第四年总PSD销售额同比
	private BigDecimal fourthSumPsdSellProfit;// 第四年总PSD毛利额
	private BigDecimal fourSumPsdSellProfitChange;// 第四年总PSD毛利额同比

	private BigDecimal fifthSumSellQuantity;// 第五年总销售量
	private BigDecimal fifthSumSellQuantityChange;// 第五年总销售量同比
	private BigDecimal fifthSumSellTotalPrice;// 第五年总销售额
	private BigDecimal fifthSumSellTotalPriceChange;// 第五年总销售额同比
	private BigDecimal fifthSumSellProfit;// 第五年总毛利额
	private BigDecimal fifthSumSellProfitChange;// 第五年总毛利额同比
	private BigDecimal fifthSumPsdSellQuantity;// 第五年总PSD销量
	private BigDecimal fifthSumPsdSellQuantityChange;// 第五年总PSD销量同比
	private BigDecimal fifthSumPsdSellTotalPrice;// 第五年总PSD销售额
	private BigDecimal fifthSumPsdSellTotalPriceChange;// 第五年总PSD销售额同比
	private BigDecimal fifthSumPsdSellProfit;// 第五年总PSD毛利额
	private BigDecimal fifthSumPsdSellProfitChange;// 第五年总PSD毛利额同比
*/
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

	public String getDirectJoin() {
		return directJoin;
	}

	public void setDirectJoin(String directJoin) {
		this.directJoin = directJoin;
	}

	public String getSellRegion() {
		return sellRegion;
	}

	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	public String getSellMonth() {
		return sellMonth;
	}

	public void setSellMonth(String sellMonth) {
		this.sellMonth = sellMonth;
	}

	public BigDecimal getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(BigDecimal sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public BigDecimal getSellQuantityChange() {
		return sellQuantityChange;
	}

	public void setSellQuantityChange(BigDecimal sellQuantityChange) {
		this.sellQuantityChange = sellQuantityChange;
	}

	public BigDecimal getSellTotalPrice() {
		return sellTotalPrice;
	}

	public void setSellTotalPrice(BigDecimal sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
	}

	public BigDecimal getSellTotalPriceChange() {
		return sellTotalPriceChange;
	}

	public void setSellTotalPriceChange(BigDecimal sellTotalPriceChange) {
		this.sellTotalPriceChange = sellTotalPriceChange;
	}

	public BigDecimal getSellProfit() {
		return sellProfit;
	}

	public void setSellProfit(BigDecimal sellProfit) {
		this.sellProfit = sellProfit;
	}

	public BigDecimal getSellProfitChange() {
		return sellProfitChange;
	}

	public void setSellProfitChange(BigDecimal sellProfitChange) {
		this.sellProfitChange = sellProfitChange;
	}

	public BigDecimal getPsdSellQuantity() {
		return psdSellQuantity;
	}

	public void setPsdSellQuantity(BigDecimal psdSellQuantity) {
		this.psdSellQuantity = psdSellQuantity;
	}

	public BigDecimal getPsdSellQuantityChange() {
		return psdSellQuantityChange;
	}

	public void setPsdSellQuantityChange(BigDecimal psdSellQuantityChange) {
		this.psdSellQuantityChange = psdSellQuantityChange;
	}

	public BigDecimal getPsdSellTotalPrice() {
		return psdSellTotalPrice;
	}

	public void setPsdSellTotalPrice(BigDecimal psdSellTotalPrice) {
		this.psdSellTotalPrice = psdSellTotalPrice;
	}

	public BigDecimal getPsdSellTotalPriceChange() {
		return psdSellTotalPriceChange;
	}

	public void setPsdSellTotalPriceChange(BigDecimal psdSellTotalPriceChange) {
		this.psdSellTotalPriceChange = psdSellTotalPriceChange;
	}

	public BigDecimal getPsdSellProfit() {
		return psdSellProfit;
	}

	public void setPsdSellProfit(BigDecimal psdSellProfit) {
		this.psdSellProfit = psdSellProfit;
	}

	public BigDecimal getPsdSellProfitChange() {
		return psdSellProfitChange;
	}

	public void setPsdSellProfitChange(BigDecimal psdSellProfitChange) {
		this.psdSellProfitChange = psdSellProfitChange;
	}

	public String getLastSellMonth() {
		return lastSellMonth;
	}

	public void setLastSellMonth(String lastSellMonth) {
		this.lastSellMonth = lastSellMonth;
	}

	public BigDecimal getLastSellQuantity() {
		return lastSellQuantity;
	}

	public void setLastSellQuantity(BigDecimal lastSellQuantity) {
		this.lastSellQuantity = lastSellQuantity;
	}

	public BigDecimal getLastSellQuantityChange() {
		return lastSellQuantityChange;
	}

	public void setLastSellQuantityChange(BigDecimal lastSellQuantityChange) {
		this.lastSellQuantityChange = lastSellQuantityChange;
	}

	public BigDecimal getLastSellTotalPrice() {
		return lastSellTotalPrice;
	}

	public void setLastSellTotalPrice(BigDecimal lastSellTotalPrice) {
		this.lastSellTotalPrice = lastSellTotalPrice;
	}

	public BigDecimal getLastSellTotalPriceChange() {
		return lastSellTotalPriceChange;
	}

	public void setLastSellTotalPriceChange(BigDecimal lastSellTotalPriceChange) {
		this.lastSellTotalPriceChange = lastSellTotalPriceChange;
	}

	public BigDecimal getLastSellProfit() {
		return lastSellProfit;
	}

	public void setLastSellProfit(BigDecimal lastSellProfit) {
		this.lastSellProfit = lastSellProfit;
	}

	public BigDecimal getLastSellProfitChange() {
		return lastSellProfitChange;
	}

	public void setLastSellProfitChange(BigDecimal lastSellProfitChange) {
		this.lastSellProfitChange = lastSellProfitChange;
	}

	public BigDecimal getLastPsdSellQuantity() {
		return lastPsdSellQuantity;
	}

	public void setLastPsdSellQuantity(BigDecimal lastPsdSellQuantity) {
		this.lastPsdSellQuantity = lastPsdSellQuantity;
	}

	public BigDecimal getLastPsdSellQuantityChange() {
		return lastPsdSellQuantityChange;
	}

	public void setLastPsdSellQuantityChange(BigDecimal lastPsdSellQuantityChange) {
		this.lastPsdSellQuantityChange = lastPsdSellQuantityChange;
	}

	public BigDecimal getLastPsdSellTotalPrice() {
		return lastPsdSellTotalPrice;
	}

	public void setLastPsdSellTotalPrice(BigDecimal lastPsdSellTotalPrice) {
		this.lastPsdSellTotalPrice = lastPsdSellTotalPrice;
	}

	public BigDecimal getLastPsdSellTotalPriceChange() {
		return lastPsdSellTotalPriceChange;
	}

	public void setLastPsdSellTotalPriceChange(BigDecimal lastPsdSellTotalPriceChange) {
		this.lastPsdSellTotalPriceChange = lastPsdSellTotalPriceChange;
	}

	public BigDecimal getLastPsdSellProfit() {
		return lastPsdSellProfit;
	}

	public void setLastPsdSellProfit(BigDecimal lastPsdSellProfit) {
		this.lastPsdSellProfit = lastPsdSellProfit;
	}

	public BigDecimal getLastPsdSellProfitChange() {
		return lastPsdSellProfitChange;
	}

	public void setLastPsdSellProfitChange(BigDecimal lastPsdSellProfitChange) {
		this.lastPsdSellProfitChange = lastPsdSellProfitChange;
	}

	public String getBeforeSellMonth() {
		return beforeSellMonth;
	}

	public void setBeforeSellMonth(String beforeSellMonth) {
		this.beforeSellMonth = beforeSellMonth;
	}

	public BigDecimal getBeforeSellQuantity() {
		return beforeSellQuantity;
	}

	public void setBeforeSellQuantity(BigDecimal beforeSellQuantity) {
		this.beforeSellQuantity = beforeSellQuantity;
	}

	public BigDecimal getBeforeSellQuantityChange() {
		return beforeSellQuantityChange;
	}

	public void setBeforeSellQuantityChange(BigDecimal beforeSellQuantityChange) {
		this.beforeSellQuantityChange = beforeSellQuantityChange;
	}

	public BigDecimal getBeforeSellTotalPrice() {
		return beforeSellTotalPrice;
	}

	public void setBeforeSellTotalPrice(BigDecimal beforeSellTotalPrice) {
		this.beforeSellTotalPrice = beforeSellTotalPrice;
	}

	public BigDecimal getBeforeSellTotalPriceChange() {
		return beforeSellTotalPriceChange;
	}

	public void setBeforeSellTotalPriceChange(BigDecimal beforeSellTotalPriceChange) {
		this.beforeSellTotalPriceChange = beforeSellTotalPriceChange;
	}

	public BigDecimal getBeforeSellProfit() {
		return beforeSellProfit;
	}

	public void setBeforeSellProfit(BigDecimal beforeSellProfit) {
		this.beforeSellProfit = beforeSellProfit;
	}

	public BigDecimal getBeforeSellProfitChange() {
		return beforeSellProfitChange;
	}

	public void setBeforeSellProfitChange(BigDecimal beforeSellProfitChange) {
		this.beforeSellProfitChange = beforeSellProfitChange;
	}

	public BigDecimal getBeforePsdSellQuantity() {
		return beforePsdSellQuantity;
	}

	public void setBeforePsdSellQuantity(BigDecimal beforePsdSellQuantity) {
		this.beforePsdSellQuantity = beforePsdSellQuantity;
	}

	public BigDecimal getBeforePsdSellQuantityChange() {
		return beforePsdSellQuantityChange;
	}

	public void setBeforePsdSellQuantityChange(BigDecimal beforePsdSellQuantityChange) {
		this.beforePsdSellQuantityChange = beforePsdSellQuantityChange;
	}

	public BigDecimal getBeforePsdSellTotalPrice() {
		return beforePsdSellTotalPrice;
	}

	public void setBeforePsdSellTotalPrice(BigDecimal beforePsdSellTotalPrice) {
		this.beforePsdSellTotalPrice = beforePsdSellTotalPrice;
	}

	public BigDecimal getBeforePsdSellTotalPriceChange() {
		return beforePsdSellTotalPriceChange;
	}

	public void setBeforePsdSellTotalPriceChange(BigDecimal beforePsdSellTotalPriceChange) {
		this.beforePsdSellTotalPriceChange = beforePsdSellTotalPriceChange;
	}

	public BigDecimal getBeforePsdSellProfit() {
		return beforePsdSellProfit;
	}

	public void setBeforePsdSellProfit(BigDecimal beforePsdSellProfit) {
		this.beforePsdSellProfit = beforePsdSellProfit;
	}

	public BigDecimal getBeforePsdSellProfitChange() {
		return beforePsdSellProfitChange;
	}

	public void setBeforePsdSellProfitChange(BigDecimal beforePsdSellProfitChange) {
		this.beforePsdSellProfitChange = beforePsdSellProfitChange;
	}

	public String getFourthSellMonth() {
		return fourthSellMonth;
	}

	public void setFourthSellMonth(String fourthSellMonth) {
		this.fourthSellMonth = fourthSellMonth;
	}

	public BigDecimal getFourthSellQuantity() {
		return fourthSellQuantity;
	}

	public void setFourthSellQuantity(BigDecimal fourthSellQuantity) {
		this.fourthSellQuantity = fourthSellQuantity;
	}

	public BigDecimal getFourthSellQuantityChange() {
		return fourthSellQuantityChange;
	}

	public void setFourthSellQuantityChange(BigDecimal fourthSellQuantityChange) {
		this.fourthSellQuantityChange = fourthSellQuantityChange;
	}

	public BigDecimal getFourthSellTotalPrice() {
		return fourthSellTotalPrice;
	}

	public void setFourthSellTotalPrice(BigDecimal fourthSellTotalPrice) {
		this.fourthSellTotalPrice = fourthSellTotalPrice;
	}

	public BigDecimal getFourthSellTotalPriceChange() {
		return fourthSellTotalPriceChange;
	}

	public void setFourthSellTotalPriceChange(BigDecimal fourthSellTotalPriceChange) {
		this.fourthSellTotalPriceChange = fourthSellTotalPriceChange;
	}

	public BigDecimal getFourthSellProfit() {
		return fourthSellProfit;
	}

	public void setFourthSellProfit(BigDecimal fourthSellProfit) {
		this.fourthSellProfit = fourthSellProfit;
	}

	public BigDecimal getFourthSellProfitChange() {
		return fourthSellProfitChange;
	}

	public void setFourthSellProfitChange(BigDecimal fourthSellProfitChange) {
		this.fourthSellProfitChange = fourthSellProfitChange;
	}

	public BigDecimal getFourthPsdSellQuantity() {
		return fourthPsdSellQuantity;
	}

	public void setFourthPsdSellQuantity(BigDecimal fourthPsdSellQuantity) {
		this.fourthPsdSellQuantity = fourthPsdSellQuantity;
	}

	public BigDecimal getFourthPsdSellQuantityChange() {
		return fourthPsdSellQuantityChange;
	}

	public void setFourthPsdSellQuantityChange(BigDecimal fourthPsdSellQuantityChange) {
		this.fourthPsdSellQuantityChange = fourthPsdSellQuantityChange;
	}

	public BigDecimal getFourthPsdSellTotalPrice() {
		return fourthPsdSellTotalPrice;
	}

	public void setFourthPsdSellTotalPrice(BigDecimal fourthPsdSellTotalPrice) {
		this.fourthPsdSellTotalPrice = fourthPsdSellTotalPrice;
	}

	public BigDecimal getFourthPsdSellTotalPriceChange() {
		return fourthPsdSellTotalPriceChange;
	}

	public void setFourthPsdSellTotalPriceChange(BigDecimal fourthPsdSellTotalPriceChange) {
		this.fourthPsdSellTotalPriceChange = fourthPsdSellTotalPriceChange;
	}

	public BigDecimal getFourthPsdSellProfit() {
		return fourthPsdSellProfit;
	}

	public void setFourthPsdSellProfit(BigDecimal fourthPsdSellProfit) {
		this.fourthPsdSellProfit = fourthPsdSellProfit;
	}

	public BigDecimal getFourthPsdSellProfitChange() {
		return fourthPsdSellProfitChange;
	}

	public void setFourthPsdSellProfitChange(BigDecimal fourthPsdSellProfitChange) {
		this.fourthPsdSellProfitChange = fourthPsdSellProfitChange;
	}

	public String getFifthSellMonth() {
		return fifthSellMonth;
	}

	public void setFifthSellMonth(String fifthSellMonth) {
		this.fifthSellMonth = fifthSellMonth;
	}

	public BigDecimal getFifthSellQuantity() {
		return fifthSellQuantity;
	}

	public void setFifthSellQuantity(BigDecimal fifthSellQuantity) {
		this.fifthSellQuantity = fifthSellQuantity;
	}

	public BigDecimal getFifthSellQuantityChange() {
		return fifthSellQuantityChange;
	}

	public void setFifthSellQuantityChange(BigDecimal fifthSellQuantityChange) {
		this.fifthSellQuantityChange = fifthSellQuantityChange;
	}

	public BigDecimal getFifthSellTotalPrice() {
		return fifthSellTotalPrice;
	}

	public void setFifthSellTotalPrice(BigDecimal fifthSellTotalPrice) {
		this.fifthSellTotalPrice = fifthSellTotalPrice;
	}

	public BigDecimal getFifthSellTotalPriceChange() {
		return fifthSellTotalPriceChange;
	}

	public void setFifthSellTotalPriceChange(BigDecimal fifthSellTotalPriceChange) {
		this.fifthSellTotalPriceChange = fifthSellTotalPriceChange;
	}

	public BigDecimal getFifthSellProfit() {
		return fifthSellProfit;
	}

	public void setFifthSellProfit(BigDecimal fifthSellProfit) {
		this.fifthSellProfit = fifthSellProfit;
	}

	public BigDecimal getFifthSellProfitChange() {
		return fifthSellProfitChange;
	}

	public void setFifthSellProfitChange(BigDecimal fifthSellProfitChange) {
		this.fifthSellProfitChange = fifthSellProfitChange;
	}

	public BigDecimal getFifthPsdSellQuantity() {
		return fifthPsdSellQuantity;
	}

	public void setFifthPsdSellQuantity(BigDecimal fifthPsdSellQuantity) {
		this.fifthPsdSellQuantity = fifthPsdSellQuantity;
	}

	public BigDecimal getFifthPsdSellQuantityChange() {
		return fifthPsdSellQuantityChange;
	}

	public void setFifthPsdSellQuantityChange(BigDecimal fifthPsdSellQuantityChange) {
		this.fifthPsdSellQuantityChange = fifthPsdSellQuantityChange;
	}

	public BigDecimal getFifthPsdSellTotalPrice() {
		return fifthPsdSellTotalPrice;
	}

	public void setFifthPsdSellTotalPrice(BigDecimal fifthPsdSellTotalPrice) {
		this.fifthPsdSellTotalPrice = fifthPsdSellTotalPrice;
	}

	public BigDecimal getFifthPsdSellTotalPriceChange() {
		return fifthPsdSellTotalPriceChange;
	}

	public void setFifthPsdSellTotalPriceChange(BigDecimal fifthPsdSellTotalPriceChange) {
		this.fifthPsdSellTotalPriceChange = fifthPsdSellTotalPriceChange;
	}

	public BigDecimal getFifthPsdSellProfit() {
		return fifthPsdSellProfit;
	}

	public void setFifthPsdSellProfit(BigDecimal fifthPsdSellProfit) {
		this.fifthPsdSellProfit = fifthPsdSellProfit;
	}

	public BigDecimal getFifthPsdSellProfitChange() {
		return fifthPsdSellProfitChange;
	}

	public void setFifthPsdSellProfitChange(BigDecimal fifthPsdSellProfitChange) {
		this.fifthPsdSellProfitChange = fifthPsdSellProfitChange;
	}

}