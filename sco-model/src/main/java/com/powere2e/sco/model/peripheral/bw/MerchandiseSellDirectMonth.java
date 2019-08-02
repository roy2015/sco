package com.powere2e.sco.model.peripheral.bw;

import java.math.BigDecimal;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品区域销售情况(月直营)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月17日
 */
public class MerchandiseSellDirectMonth extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7242110780686921396L;
	private String merchandiseCode;//
	private String sellMonth;//
	private String sellRegion;//
	private String supplierCode;//
	private BigDecimal sellQuantity;//
	private BigDecimal sellTotalPrice;//
	private BigDecimal sellProfit;//
	private BigDecimal psdSellQuantity;//
	private BigDecimal psdSellTotalPrice;//
	private BigDecimal psdSellProfit;//
	private BigDecimal sellQuantityProportionM;//
	private BigDecimal sellTotalPriceProportionM;//
	private BigDecimal sellProfitProportionM;//
	private BigDecimal sellQuantityProportionS;//
	private BigDecimal sellTotalPriceProportionS;//
	private BigDecimal sellProfitProportionS;//
	private BigDecimal sellQuantityProportionD;//
	private BigDecimal sellTotalPriceProportionD;//
	private BigDecimal sellProfitProportionD;//
	private Integer sellStoreQuantity;//

	private List<MerchandiseSellDirectMonth> list;

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getSellMonth() {
		return sellMonth;
	}

	public void setSellMonth(String sellMonth) {
		this.sellMonth = sellMonth;
	}

	public String getSellRegion() {
		return sellRegion;
	}

	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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

	public BigDecimal getSellQuantityProportionM() {
		return sellQuantityProportionM;
	}

	public void setSellQuantityProportionM(BigDecimal sellQuantityProportionM) {
		this.sellQuantityProportionM = sellQuantityProportionM;
	}

	public BigDecimal getSellTotalPriceProportionM() {
		return sellTotalPriceProportionM;
	}

	public void setSellTotalPriceProportionM(BigDecimal sellTotalPriceProportionM) {
		this.sellTotalPriceProportionM = sellTotalPriceProportionM;
	}

	public BigDecimal getSellProfitProportionM() {
		return sellProfitProportionM;
	}

	public void setSellProfitProportionM(BigDecimal sellProfitProportionM) {
		this.sellProfitProportionM = sellProfitProportionM;
	}

	public BigDecimal getSellQuantityProportionS() {
		return sellQuantityProportionS;
	}

	public void setSellQuantityProportionS(BigDecimal sellQuantityProportionS) {
		this.sellQuantityProportionS = sellQuantityProportionS;
	}

	public BigDecimal getSellTotalPriceProportionS() {
		return sellTotalPriceProportionS;
	}

	public void setSellTotalPriceProportionS(BigDecimal sellTotalPriceProportionS) {
		this.sellTotalPriceProportionS = sellTotalPriceProportionS;
	}

	public BigDecimal getSellProfitProportionS() {
		return sellProfitProportionS;
	}

	public void setSellProfitProportionS(BigDecimal sellProfitProportionS) {
		this.sellProfitProportionS = sellProfitProportionS;
	}

	public BigDecimal getSellQuantityProportionD() {
		return sellQuantityProportionD;
	}

	public void setSellQuantityProportionD(BigDecimal sellQuantityProportionD) {
		this.sellQuantityProportionD = sellQuantityProportionD;
	}

	public BigDecimal getSellTotalPriceProportionD() {
		return sellTotalPriceProportionD;
	}

	public void setSellTotalPriceProportionD(BigDecimal sellTotalPriceProportionD) {
		this.sellTotalPriceProportionD = sellTotalPriceProportionD;
	}

	public BigDecimal getSellProfitProportionD() {
		return sellProfitProportionD;
	}

	public void setSellProfitProportionD(BigDecimal sellProfitProportionD) {
		this.sellProfitProportionD = sellProfitProportionD;
	}

	public Integer getSellStoreQuantity() {
		return sellStoreQuantity;
	}

	public void setSellStoreQuantity(Integer sellStoreQuantity) {
		this.sellStoreQuantity = sellStoreQuantity;
	}

	public List<MerchandiseSellDirectMonth> getList() {
		return list;
	}

	public void setList(List<MerchandiseSellDirectMonth> list) {
		this.list = list;
	}

}