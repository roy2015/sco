package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-人工实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingManpower extends AppModel {

	private static final long serialVersionUID = 1332901805907861076L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private Integer manpowerCount;// 车间工人数
	private BigDecimal avgWage;// 平均工资
	private BigDecimal monthCapacity;// 月产量t
	private BigDecimal unitsWage;// 每kg成品人工费
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

	public Integer getManpowerCount() {
		return manpowerCount;
	}

	public void setManpowerCount(Integer manpowerCount) {
		this.manpowerCount = manpowerCount;
	}

	public BigDecimal getAvgWage() {
		return avgWage;
	}

	public void setAvgWage(BigDecimal avgWage) {
		this.avgWage = avgWage;
	}

	public BigDecimal getMonthCapacity() {
		return monthCapacity;
	}

	public void setMonthCapacity(BigDecimal monthCapacity) {
		this.monthCapacity = monthCapacity;
	}

	public BigDecimal getUnitsWage() {
		return unitsWage;
	}

	public void setUnitsWage(BigDecimal unitsWage) {
		this.unitsWage = unitsWage;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}