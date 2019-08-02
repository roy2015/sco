package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-成本项实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年5月8日
 */
public class AccountingCostItem extends AppModel {

	private static final long serialVersionUID = 5866305636953708614L;
	private String accountingCode;// 核算表编号
	private BigDecimal yieldValue;// 得率值
	private String yieldRemarks;// 得率备注
	private BigDecimal zlsubtotalValue;// 主料小计值
	private String zlsubtotalRemarks;// 主料小计备注
	private BigDecimal flsubtotalValue;// 辅料小计值
	private String flsubtotalRemarks;// 辅料小计备注
	private BigDecimal itotalcostValue;// 商品总投入成本值
	private String itotalcostRemarks;// 商品总投入成本备注
	private BigDecimal mtotalcostValue;// 原材料总成本值
	private String mtotalcostRemarks;// 原材料总成本备注
	private BigDecimal packagproportionValue;// 包装占比值
	private String packagproportionRemarks;// 包装占比备注
	private BigDecimal deductptcostValue;// 扣除包装后总成本值
	private String deductptcostRemarks;// 扣除包装后总成本备注
	private BigDecimal nwpackagsubtotalValue;// 内外包装材料小计值
	private String nwpackagsubtotalRemarks;// 内外包装材料小计备注
	private BigDecimal wastagesubtotalValue;// 损耗小计值
	private String wastagesubtotalRemarks;// 损耗小计备注
	// 进口
	private BigDecimal rmbSettlementPrice;// 商品人民币结算价格
	private String rmbSettlementPriceRemarks;// 商品人民币结算价格备注
	private BigDecimal updateOrderFee;// 换单费
	private String updateOrderFeeRemarks;// 换单费备注
	private BigDecimal premium;// 保险费
	private String premiumRemarks;// 保险费备注
	private BigDecimal importFeeTotal;// 进口费用小计
	private String importFeeTotalRemarks;// 进口费用小计备注
	private BigDecimal cdAvtTotal;// 关税/增值税小计
	private String cdAvtTotalRemarks;// 关税/增值税小计备注
	private BigDecimal customsClearanceTotal;// 清关后商品总成本
	private String customsClearanceTotalRemark;// 清关后商品总成本备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public BigDecimal getYieldValue() {
		return yieldValue;
	}

	public void setYieldValue(BigDecimal yieldValue) {
		this.yieldValue = yieldValue;
	}

	public String getYieldRemarks() {
		return yieldRemarks;
	}

	public void setYieldRemarks(String yieldRemarks) {
		this.yieldRemarks = yieldRemarks;
	}

	public BigDecimal getZlsubtotalValue() {
		return zlsubtotalValue;
	}

	public void setZlsubtotalValue(BigDecimal zlsubtotalValue) {
		this.zlsubtotalValue = zlsubtotalValue;
	}

	public String getZlsubtotalRemarks() {
		return zlsubtotalRemarks;
	}

	public void setZlsubtotalRemarks(String zlsubtotalRemarks) {
		this.zlsubtotalRemarks = zlsubtotalRemarks;
	}

	public BigDecimal getFlsubtotalValue() {
		return flsubtotalValue;
	}

	public void setFlsubtotalValue(BigDecimal flsubtotalValue) {
		this.flsubtotalValue = flsubtotalValue;
	}

	public String getFlsubtotalRemarks() {
		return flsubtotalRemarks;
	}

	public void setFlsubtotalRemarks(String flsubtotalRemarks) {
		this.flsubtotalRemarks = flsubtotalRemarks;
	}

	public BigDecimal getItotalcostValue() {
		return itotalcostValue;
	}

	public void setItotalcostValue(BigDecimal itotalcostValue) {
		this.itotalcostValue = itotalcostValue;
	}

	public String getItotalcostRemarks() {
		return itotalcostRemarks;
	}

	public void setItotalcostRemarks(String itotalcostRemarks) {
		this.itotalcostRemarks = itotalcostRemarks;
	}

	public BigDecimal getMtotalcostValue() {
		return mtotalcostValue;
	}

	public void setMtotalcostValue(BigDecimal mtotalcostValue) {
		this.mtotalcostValue = mtotalcostValue;
	}

	public String getMtotalcostRemarks() {
		return mtotalcostRemarks;
	}

	public void setMtotalcostRemarks(String mtotalcostRemarks) {
		this.mtotalcostRemarks = mtotalcostRemarks;
	}

	public BigDecimal getPackagproportionValue() {
		return packagproportionValue;
	}

	public void setPackagproportionValue(BigDecimal packagproportionValue) {
		this.packagproportionValue = packagproportionValue;
	}

	public String getPackagproportionRemarks() {
		return packagproportionRemarks;
	}

	public void setPackagproportionRemarks(String packagproportionRemarks) {
		this.packagproportionRemarks = packagproportionRemarks;
	}

	public BigDecimal getDeductptcostValue() {
		return deductptcostValue;
	}

	public void setDeductptcostValue(BigDecimal deductptcostValue) {
		this.deductptcostValue = deductptcostValue;
	}

	public String getDeductptcostRemarks() {
		return deductptcostRemarks;
	}

	public void setDeductptcostRemarks(String deductptcostRemarks) {
		this.deductptcostRemarks = deductptcostRemarks;
	}

	public BigDecimal getNwpackagsubtotalValue() {
		return nwpackagsubtotalValue;
	}

	public void setNwpackagsubtotalValue(BigDecimal nwpackagsubtotalValue) {
		this.nwpackagsubtotalValue = nwpackagsubtotalValue;
	}

	public String getNwpackagsubtotalRemarks() {
		return nwpackagsubtotalRemarks;
	}

	public void setNwpackagsubtotalRemarks(String nwpackagsubtotalRemarks) {
		this.nwpackagsubtotalRemarks = nwpackagsubtotalRemarks;
	}

	public BigDecimal getWastagesubtotalValue() {
		return wastagesubtotalValue;
	}

	public void setWastagesubtotalValue(BigDecimal wastagesubtotalValue) {
		this.wastagesubtotalValue = wastagesubtotalValue;
	}

	public String getWastagesubtotalRemarks() {
		return wastagesubtotalRemarks;
	}

	public void setWastagesubtotalRemarks(String wastagesubtotalRemarks) {
		this.wastagesubtotalRemarks = wastagesubtotalRemarks;
	}

	public BigDecimal getRmbSettlementPrice() {
		return rmbSettlementPrice;
	}

	public void setRmbSettlementPrice(BigDecimal rmbSettlementPrice) {
		this.rmbSettlementPrice = rmbSettlementPrice;
	}

	public String getRmbSettlementPriceRemarks() {
		return rmbSettlementPriceRemarks;
	}

	public void setRmbSettlementPriceRemarks(String rmbSettlementPriceRemarks) {
		this.rmbSettlementPriceRemarks = rmbSettlementPriceRemarks;
	}

	public BigDecimal getUpdateOrderFee() {
		return updateOrderFee;
	}

	public void setUpdateOrderFee(BigDecimal updateOrderFee) {
		this.updateOrderFee = updateOrderFee;
	}

	public String getUpdateOrderFeeRemarks() {
		return updateOrderFeeRemarks;
	}

	public void setUpdateOrderFeeRemarks(String updateOrderFeeRemarks) {
		this.updateOrderFeeRemarks = updateOrderFeeRemarks;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public String getPremiumRemarks() {
		return premiumRemarks;
	}

	public void setPremiumRemarks(String premiumRemarks) {
		this.premiumRemarks = premiumRemarks;
	}

	public BigDecimal getImportFeeTotal() {
		return importFeeTotal;
	}

	public void setImportFeeTotal(BigDecimal importFeeTotal) {
		this.importFeeTotal = importFeeTotal;
	}

	public String getImportFeeTotalRemarks() {
		return importFeeTotalRemarks;
	}

	public void setImportFeeTotalRemarks(String importFeeTotalRemarks) {
		this.importFeeTotalRemarks = importFeeTotalRemarks;
	}

	public BigDecimal getCdAvtTotal() {
		return cdAvtTotal;
	}

	public void setCdAvtTotal(BigDecimal cdAvtTotal) {
		this.cdAvtTotal = cdAvtTotal;
	}

	public String getCdAvtTotalRemarks() {
		return cdAvtTotalRemarks;
	}

	public void setCdAvtTotalRemarks(String cdAvtTotalRemarks) {
		this.cdAvtTotalRemarks = cdAvtTotalRemarks;
	}

	public BigDecimal getCustomsClearanceTotal() {
		return customsClearanceTotal;
	}

	public void setCustomsClearanceTotal(BigDecimal customsClearanceTotal) {
		this.customsClearanceTotal = customsClearanceTotal;
	}

	public String getCustomsClearanceTotalRemark() {
		return customsClearanceTotalRemark;
	}

	public void setCustomsClearanceTotalRemark(String customsClearanceTotalRemark) {
		this.customsClearanceTotalRemark = customsClearanceTotalRemark;
	}
}