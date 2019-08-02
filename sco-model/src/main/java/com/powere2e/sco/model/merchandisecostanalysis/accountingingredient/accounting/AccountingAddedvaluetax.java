package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表增值税实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingAddedvaluetax extends AppModel {

	private static final long serialVersionUID = 1572343751895053372L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private BigDecimal taxRate;// 税率
	private String addedvaluetaxComputeType;// 增值税计算方式
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getAddedvaluetaxComputeType() {
		return addedvaluetaxComputeType;
	}

	public void setAddedvaluetaxComputeType(String addedvaluetaxComputeType) {
		this.addedvaluetaxComputeType = addedvaluetaxComputeType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}