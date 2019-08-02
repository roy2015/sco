package com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selllinkrelative;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品显示环比分析实体类
 * 
 * @author Joyce.li
 * @since 2015年7月10日 下午4:06:56
 * @version 1.0
 */
public class SellLinkRelativeAnalysis extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9185722789144789760L;
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private String directJoin;// 直营\加盟
	private String sellRegion;// 销售地区编号
	private String sellDate;// 销售日期(周期开始日期~周期结束日期)
	private Date minDate;//周期开始日期
	private Date maxDate;//周期结束日期
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

	private Date beforeMinDate;//周期开始日期
	private Date beforeMaxDate;//周期结束日期
	private BigDecimal beforeSellQuantity;// 前一个周期销售量
	private BigDecimal beforeSellQuantityChange;// 前一个周期销售量同比
	private BigDecimal beforeSellTotalPrice;// 前一个周期销售额
	private BigDecimal beforeSellTotalPriceChange;// 前一个周期销售额同比
	private BigDecimal beforeSellProfit;// 前一个周期毛利额
	private BigDecimal beforeSellProfitChange;// 前一个周期毛利额同比
	private BigDecimal beforePsdSellQuantity;// 前一个周期PSD销量
	private BigDecimal beforePsdSellQuantityChange;// 前一个周期PSD销量同比
	private BigDecimal beforePsdSellTotalPrice;// 前一个周期PSD销售额
	private BigDecimal beforePsdSellTotalPriceChange;// 前一个周期PSD销售额同比
	private BigDecimal beforePsdSellProfit;// 前一个周期PSD毛利额
	private BigDecimal beforePsdSellProfitChange;// 前一个周期PSD毛利额同比
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
	public String getSellDate() {
		return sellDate;
	}
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}
	public Date getMinDate() {
		return minDate;
	}
	public void setMinDate(Date minDate) {
		this.minDate = minDate;
	}
	public Date getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
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
	public Date getBeforeMinDate() {
		return beforeMinDate;
	}
	public void setBeforeMinDate(Date beforeMinDate) {
		this.beforeMinDate = beforeMinDate;
	}
	public Date getBeforeMaxDate() {
		return beforeMaxDate;
	}
	public void setBeforeMaxDate(Date beforeMaxDate) {
		this.beforeMaxDate = beforeMaxDate;
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

	

}