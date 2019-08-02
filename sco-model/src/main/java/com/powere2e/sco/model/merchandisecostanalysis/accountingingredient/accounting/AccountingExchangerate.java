package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表汇率实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingExchangerate extends AppModel {

	private static final long serialVersionUID = -8516743191780821051L;
	private String accountingCode;// 核算表编号
	private BigDecimal exchangerate;// 汇率
	private String referenceDate;// 参考日期
	private String referenceBank;// 参考银行
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}
	
	public String getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	public String getReferenceBank() {
		return referenceBank;
	}

	public void setReferenceBank(String referenceBank) {
		this.referenceBank = referenceBank;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}