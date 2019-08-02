package com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.suppliersalescontribution;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商销售贡献度
 * 
 * @author Joyce.li
 * @since 2015年8月3日 下午3:20:54
 * @version 1.0
 */
public class SupplierSalesContribution extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9185722789144789760L;
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String sellDate;// 销售日期(周期开始日期~周期结束日期)
	private Date minDate;// 周期开始日期
	private Date maxDate;// 周期结束日期

	private BigDecimal sellQuantity;// 销售量
	private BigDecimal lastSellQuantity;// 销售量同比
	private String sellQuantityChange;// 销售量占比
	private String lastSellQuantityChange;// 销售量占比同比
	private Integer sellQuantityRank;// 销售量占比排名
	private Integer lastSellQuantityRank;// 销售量占比同比排名

	private BigDecimal sellTotalPrice;// 销售金额
	private BigDecimal lastSellTotalPrice;// 销售金额同比
	private String sellTotalPriceChange;// 销售金额占比
	private String lastSellTotalPriceChange;// 销售金额占比同比
	private Integer sellTotalPriceRank;// 销售金额占比排名
	private Integer lastSellTotalPriceRank;// 销售金额占比同比排名

	private BigDecimal sellProfit;// 毛利额
	private BigDecimal lastSellProfit;// 毛利额同比
	private String sellProfitChange;// 毛利额占比
	private String lastSellProfitChange;// 毛利额占比同比
	private Integer sellProfitRank;// 毛利额占比排名
	private Integer lastSellProfitRank;// 毛利额占比同比排名

	private BigDecimal receiptPrice;// 进货金额
	private BigDecimal lastReceiptPrice;// 进货金额同比
	private String receiptPriceChange;// 进货金额占比
	private String lastReceiptPriceChange;// 进货金额占比同比
	private Integer receiptPriceRank;// 进货金额占比排名
	private Integer lastReceiptPriceRank;// 进货金额占比同比排名

	private BigDecimal receiptRationed;// 进货量
	private BigDecimal lastReceiptRationed;// 进货量同比
	private String receiptRationedChange;// 进货量占比
	private String lastReceiptRationedChange;// 进货量占比同比
	private Integer receiptRationedRank;// 进货量占比排名
	private Integer lastReceiptRationedRank;// 进货量占比同比排名

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

	public BigDecimal getLastSellQuantity() {
		return lastSellQuantity;
	}

	public void setLastSellQuantity(BigDecimal lastSellQuantity) {
		this.lastSellQuantity = lastSellQuantity;
	}

	public String getSellQuantityChange() {
		return sellQuantityChange;
	}

	public void setSellQuantityChange(String sellQuantityChange) {
		this.sellQuantityChange = sellQuantityChange;
	}

	public String getLastSellQuantityChange() {
		return lastSellQuantityChange;
	}

	public void setLastSellQuantityChange(String lastSellQuantityChange) {
		this.lastSellQuantityChange = lastSellQuantityChange;
	}

	public Integer getSellQuantityRank() {
		return sellQuantityRank;
	}

	public void setSellQuantityRank(Integer sellQuantityRank) {
		this.sellQuantityRank = sellQuantityRank;
	}

	public Integer getLastSellQuantityRank() {
		return lastSellQuantityRank;
	}

	public void setLastSellQuantityRank(Integer lastSellQuantityRank) {
		this.lastSellQuantityRank = lastSellQuantityRank;
	}

	public BigDecimal getSellTotalPrice() {
		return sellTotalPrice;
	}

	public void setSellTotalPrice(BigDecimal sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
	}

	public BigDecimal getLastSellTotalPrice() {
		return lastSellTotalPrice;
	}

	public void setLastSellTotalPrice(BigDecimal lastSellTotalPrice) {
		this.lastSellTotalPrice = lastSellTotalPrice;
	}

	public String getSellTotalPriceChange() {
		return sellTotalPriceChange;
	}

	public void setSellTotalPriceChange(String sellTotalPriceChange) {
		this.sellTotalPriceChange = sellTotalPriceChange;
	}

	public String getLastSellTotalPriceChange() {
		return lastSellTotalPriceChange;
	}

	public void setLastSellTotalPriceChange(String lastSellTotalPriceChange) {
		this.lastSellTotalPriceChange = lastSellTotalPriceChange;
	}

	public Integer getSellTotalPriceRank() {
		return sellTotalPriceRank;
	}

	public void setSellTotalPriceRank(Integer sellTotalPriceRank) {
		this.sellTotalPriceRank = sellTotalPriceRank;
	}

	public Integer getLastSellTotalPriceRank() {
		return lastSellTotalPriceRank;
	}

	public void setLastSellTotalPriceRank(Integer lastSellTotalPriceRank) {
		this.lastSellTotalPriceRank = lastSellTotalPriceRank;
	}

	public BigDecimal getSellProfit() {
		return sellProfit;
	}

	public void setSellProfit(BigDecimal sellProfit) {
		this.sellProfit = sellProfit;
	}

	public BigDecimal getLastSellProfit() {
		return lastSellProfit;
	}

	public void setLastSellProfit(BigDecimal lastSellProfit) {
		this.lastSellProfit = lastSellProfit;
	}

	public String getSellProfitChange() {
		return sellProfitChange;
	}

	public void setSellProfitChange(String sellProfitChange) {
		this.sellProfitChange = sellProfitChange;
	}

	public String getLastSellProfitChange() {
		return lastSellProfitChange;
	}

	public void setLastSellProfitChange(String lastSellProfitChange) {
		this.lastSellProfitChange = lastSellProfitChange;
	}

	public Integer getSellProfitRank() {
		return sellProfitRank;
	}

	public void setSellProfitRank(Integer sellProfitRank) {
		this.sellProfitRank = sellProfitRank;
	}

	public Integer getLastSellProfitRank() {
		return lastSellProfitRank;
	}

	public void setLastSellProfitRank(Integer lastSellProfitRank) {
		this.lastSellProfitRank = lastSellProfitRank;
	}

	public BigDecimal getReceiptPrice() {
		return receiptPrice;
	}

	public void setReceiptPrice(BigDecimal receiptPrice) {
		this.receiptPrice = receiptPrice;
	}

	public BigDecimal getLastReceiptPrice() {
		return lastReceiptPrice;
	}

	public void setLastReceiptPrice(BigDecimal lastReceiptPrice) {
		this.lastReceiptPrice = lastReceiptPrice;
	}

	public String getReceiptPriceChange() {
		return receiptPriceChange;
	}

	public void setReceiptPriceChange(String receiptPriceChange) {
		this.receiptPriceChange = receiptPriceChange;
	}

	public String getLastReceiptPriceChange() {
		return lastReceiptPriceChange;
	}

	public void setLastReceiptPriceChange(String lastReceiptPriceChange) {
		this.lastReceiptPriceChange = lastReceiptPriceChange;
	}

	public Integer getReceiptPriceRank() {
		return receiptPriceRank;
	}

	public void setReceiptPriceRank(Integer receiptPriceRank) {
		this.receiptPriceRank = receiptPriceRank;
	}

	public Integer getLastReceiptPriceRank() {
		return lastReceiptPriceRank;
	}

	public void setLastReceiptPriceRank(Integer lastReceiptPriceRank) {
		this.lastReceiptPriceRank = lastReceiptPriceRank;
	}

	public BigDecimal getReceiptRationed() {
		return receiptRationed;
	}

	public void setReceiptRationed(BigDecimal receiptRationed) {
		this.receiptRationed = receiptRationed;
	}

	public BigDecimal getLastReceiptRationed() {
		return lastReceiptRationed;
	}

	public void setLastReceiptRationed(BigDecimal lastReceiptRationed) {
		this.lastReceiptRationed = lastReceiptRationed;
	}

	public String getReceiptRationedChange() {
		return receiptRationedChange;
	}

	public void setReceiptRationedChange(String receiptRationedChange) {
		this.receiptRationedChange = receiptRationedChange;
	}

	public String getLastReceiptRationedChange() {
		return lastReceiptRationedChange;
	}

	public void setLastReceiptRationedChange(String lastReceiptRationedChange) {
		this.lastReceiptRationedChange = lastReceiptRationedChange;
	}

	public Integer getReceiptRationedRank() {
		return receiptRationedRank;
	}

	public void setReceiptRationedRank(Integer receiptRationedRank) {
		this.receiptRationedRank = receiptRationedRank;
	}

	public Integer getLastReceiptRationedRank() {
		return lastReceiptRationedRank;
	}

	public void setLastReceiptRationedRank(Integer lastReceiptRationedRank) {
		this.lastReceiptRationedRank = lastReceiptRationedRank;
	}

}