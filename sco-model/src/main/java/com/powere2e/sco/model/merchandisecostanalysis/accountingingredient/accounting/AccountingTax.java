package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-税收实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingTax extends AppModel {

	private static final long serialVersionUID = 6927098020215397173L;
	private String accountingCode;// 核算表编号
	private BigDecimal taxRate;// 税率
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}