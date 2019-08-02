package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表报关服务费实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingCustomscharges extends AppModel {

	private static final long serialVersionUID = 9054569620853567793L;
	private String accountingCode;// 核算表编号
	private BigDecimal price;// 价格
	private String customscharges;// 报关费
	private String portSurcharge;// 港杂费
	private String demurrageCharge;// 滞港费
	private String containerDirtynessChange;// 污箱费
	private String elseFee;// 其他费用
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

	public String getCustomscharges() {
		return customscharges;
	}

	public void setCustomscharges(String customscharges) {
		this.customscharges = customscharges;
	}

	public String getPortSurcharge() {
		return portSurcharge;
	}

	public void setPortSurcharge(String portSurcharge) {
		this.portSurcharge = portSurcharge;
	}

	public String getDemurrageCharge() {
		return demurrageCharge;
	}

	public void setDemurrageCharge(String demurrageCharge) {
		this.demurrageCharge = demurrageCharge;
	}

	public String getContainerDirtynessChange() {
		return containerDirtynessChange;
	}

	public void setContainerDirtynessChange(String containerDirtynessChange) {
		this.containerDirtynessChange = containerDirtynessChange;
	}

	public String getElseFee() {
		return elseFee;
	}

	public void setElseFee(String elseFee) {
		this.elseFee = elseFee;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}