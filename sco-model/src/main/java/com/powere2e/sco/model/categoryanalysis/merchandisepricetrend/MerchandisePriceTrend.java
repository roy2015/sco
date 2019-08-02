package com.powere2e.sco.model.categoryanalysis.merchandisepricetrend;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品价格趋势实体类
 * 
 * @author Len.zhao
 * @since 2015年5月8日
 * @version 1.0
 */
public class MerchandisePriceTrend extends Merchandise {

	private static final long serialVersionUID = -3678711688412137719L;
	private String regionCode;// 地区code
	private String regionName;// 地区name
	private Date qlStartDate;// 查询开始日期
	private Date qlEndDate;// 查询结束日期
	private String itemValue;
	private String onlyDirect;
	private String onlyJoin;
	private String directAndJoin;
	private String merchandiseCodes;// 商品编号串
	private String priceChangeDate;// 价格维护日期
	private BigDecimal sellPrice;// 销售价格
	private BigDecimal sellQuantity;// 销量
	private BigDecimal sellTotalPrice;// 销售额
	private BigDecimal sellProfit;// 毛利额
	private int sellStoreQuantity;// 销售店天
	private int permissionStoreQuantity;// 权限店天数
	private String activeDegrees;// 活跃度
	private BigDecimal psdSellQuantity;// PSD销量
	private BigDecimal psdSellTotalPrice;// PSD 销售额
	private BigDecimal psdSellProfit;// PSD毛利额
	private String sellPriceGains;// 售价涨幅
	private String psdSalesGains;// PSD销量涨幅
	private String psdResultsGains;// PSD业绩涨幅
	private String psdGrossProfitGains;// PSD毛利涨幅

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getSellPriceGains() {
		return sellPriceGains;
	}

	public void setSellPriceGains(String sellPriceGains) {
		this.sellPriceGains = sellPriceGains;
	}

	public String getPsdSalesGains() {
		return psdSalesGains;
	}

	public void setPsdSalesGains(String psdSalesGains) {
		this.psdSalesGains = psdSalesGains;
	}

	public String getPsdResultsGains() {
		return psdResultsGains;
	}

	public void setPsdResultsGains(String psdResultsGains) {
		this.psdResultsGains = psdResultsGains;
	}

	public String getPsdGrossProfitGains() {
		return psdGrossProfitGains;
	}

	public void setPsdGrossProfitGains(String psdGrossProfitGains) {
		this.psdGrossProfitGains = psdGrossProfitGains;
	}

	public MerchandisePriceTrend() {
		super();
	}

	public String getPriceChangeDate() {
		return priceChangeDate;
	}

	public void setPriceChangeDate(String priceChangeDate) {
		this.priceChangeDate = priceChangeDate;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(BigDecimal sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public BigDecimal getSellTotalPrice() {
		return sellTotalPrice;
	}

	public void setSellTotalPrice(BigDecimal sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
	}

	public BigDecimal getSellProfit() {
		return sellProfit;
	}

	public void setSellProfit(BigDecimal sellProfit) {
		this.sellProfit = sellProfit;
	}

	public int getSellStoreQuantity() {
		return sellStoreQuantity;
	}

	public void setSellStoreQuantity(int sellStoreQuantity) {
		this.sellStoreQuantity = sellStoreQuantity;
	}

	public int getPermissionStoreQuantity() {
		return permissionStoreQuantity;
	}

	public void setPermissionStoreQuantity(int permissionStoreQuantity) {
		this.permissionStoreQuantity = permissionStoreQuantity;
	}

	public String getActiveDegrees() {
		return activeDegrees;
	}

	public void setActiveDegrees(String activeDegrees) {

		this.activeDegrees = activeDegrees;
	}

	public String getMerchandiseCodes() {
		return merchandiseCodes;
	}

	public void setMerchandiseCodes(String merchandiseCodes) {
		this.merchandiseCodes = merchandiseCodes;
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
	@JSON(format = "yyyy-MM-dd")
	public Date getQlStartDate() {
		return qlStartDate;
	}

	// 设置
	public void setQlStartDate(Date qlStartDate) {
		this.qlStartDate = qlStartDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getQlEndDate() {
		return qlEndDate;
	}

	// 设置
	public void setQlEndDate(Date qlEndDate) {
		this.qlEndDate = qlEndDate;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getOnlyDirect() {
		return onlyDirect;
	}

	public void setOnlyDirect(String onlyDirect) {
		this.onlyDirect = onlyDirect;
	}

	public String getOnlyJoin() {
		return onlyJoin;
	}

	public void setOnlyJoin(String onlyJoin) {
		this.onlyJoin = onlyJoin;
	}

	public String getDirectAndJoin() {
		return directAndJoin;
	}

	public void setDirectAndJoin(String directAndJoin) {
		this.directAndJoin = directAndJoin;
	}

	public BigDecimal getPsdSellQuantity() {
		return psdSellQuantity;
	}

	public void setPsdSellQuantity(BigDecimal psdSellQuantity) {
		this.psdSellQuantity = psdSellQuantity;
	}

	public BigDecimal getPsdSellTotalPrice() {
		return psdSellTotalPrice;
	}

	public void setPsdSellTotalPrice(BigDecimal psdSellTotalPrice) {
		this.psdSellTotalPrice = psdSellTotalPrice;
	}

	public BigDecimal getPsdSellProfit() {
		return psdSellProfit;
	}

	public void setPsdSellProfit(BigDecimal psdSellProfit) {
		this.psdSellProfit = psdSellProfit;
	}

}
