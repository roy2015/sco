package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-运输实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingFreight extends AppModel {

	private static final long serialVersionUID = -5467844156665216538L;
	private String accountingCode;// 核算表编号
	private String units;// 单位
	private BigDecimal unitsCost;// 单位成本
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public BigDecimal getUnitsCost() {
		return unitsCost;
	}

	public void setUnitsCost(BigDecimal unitsCost) {
		this.unitsCost = unitsCost;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}