package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-损耗类型实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingWastage extends AppModel {

	private static final long serialVersionUID = -9158372220743234934L;
	private String accountingCode;// 核算表编号
	private String wastageType;// 损耗类型
	private String wastageName;// 损耗类型名称
	private BigDecimal price;// 值
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getWastageType() {
		return wastageType;
	}

	public void setWastageType(String wastageType) {
		this.wastageType = wastageType;
	}

	public String getWastageName() {
		return wastageName;
	}

	public void setWastageName(String wastageName) {
		this.wastageName = wastageName;
	}
	
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}