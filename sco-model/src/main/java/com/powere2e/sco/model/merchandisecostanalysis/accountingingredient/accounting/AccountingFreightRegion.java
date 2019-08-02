package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-运输各地区实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingFreightRegion extends AppModel {

	private static final long serialVersionUID = -6916885820702050549L;
	private String accountingCode;// 核算表编号
	private String region;// 地区编号
	private BigDecimal price;// 价格
	private BigDecimal sumKm;// 总公里数
	private String regionName;//地区名称

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSumKm() {
		return sumKm;
	}

	public void setSumKm(BigDecimal sumKm) {
		this.sumKm = sumKm;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
}