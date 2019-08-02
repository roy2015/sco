package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表税差实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingTaxDiffer extends AppModel {

	private static final long serialVersionUID = -8274777079173306231L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private String taxDifferComputeType;// 税差计算方式
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

	public String getTaxDifferComputeType() {
		return taxDifferComputeType;
	}

	public void setTaxDifferComputeType(String taxDifferComputeType) {
		this.taxDifferComputeType = taxDifferComputeType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}