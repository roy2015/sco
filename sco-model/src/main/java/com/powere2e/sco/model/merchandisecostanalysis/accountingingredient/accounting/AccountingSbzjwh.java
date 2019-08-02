package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-设备折旧及维护实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingSbzjwh extends AppModel {

	private static final long serialVersionUID = 1530896436824191725L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private BigDecimal totalPrice;// 设备总价万元
	private BigDecimal ageLimit;// 折旧年限
	private BigDecimal depreciation;// 折旧值万元/年
	private BigDecimal capacity;// 产能值t成品/年
	private BigDecimal total;// 合计折旧值元/kg
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

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(BigDecimal ageLimit) {
		this.ageLimit = ageLimit;
	}

	public BigDecimal getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(BigDecimal depreciation) {
		this.depreciation = depreciation;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
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