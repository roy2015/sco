package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表利息实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingInterest extends AppModel {

	private static final long serialVersionUID = -4088358517488884384L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private BigDecimal loanRate;// 贷款利率(%)
	private String interestComputeType;// 利息计算方式
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

	public BigDecimal getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(BigDecimal loanRate) {
		this.loanRate = loanRate;
	}

	public String getInterestComputeType() {
		return interestComputeType;
	}

	public void setInterestComputeType(String interestComputeType) {
		this.interestComputeType = interestComputeType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}