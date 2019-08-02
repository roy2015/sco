package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-水电煤实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingWec extends AppModel {

	private static final long serialVersionUID = -8743016842200192350L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private BigDecimal water;// 耗水
	private BigDecimal electricity;// 耗电
	private BigDecimal gas;// 耗气
	private BigDecimal coal;// 耗煤
	private BigDecimal oil;// 耗油
	private BigDecimal total;// 合计
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

	public BigDecimal getWater() {
		return water;
	}

	public void setWater(BigDecimal water) {
		this.water = water;
	}

	public BigDecimal getElectricity() {
		return electricity;
	}

	public void setElectricity(BigDecimal electricity) {
		this.electricity = electricity;
	}

	public BigDecimal getGas() {
		return gas;
	}

	public void setGas(BigDecimal gas) {
		this.gas = gas;
	}

	public BigDecimal getCoal() {
		return coal;
	}

	public void setCoal(BigDecimal coal) {
		this.coal = coal;
	}

	public BigDecimal getOil() {
		return oil;
	}

	public void setOil(BigDecimal oil) {
		this.oil = oil;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}