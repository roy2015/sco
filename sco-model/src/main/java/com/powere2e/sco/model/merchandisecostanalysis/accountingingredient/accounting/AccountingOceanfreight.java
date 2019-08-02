package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 进口核算表海运费实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年9月14日
 */
public class AccountingOceanfreight extends AppModel {

	private static final long serialVersionUID = 6411065133906108292L;
	private String accountingCode;// 核算表编号
	private String freightType;// 运输类型
	private BigDecimal price;// 价格
	private Date transportStartDate;// 运输开始时间
	private Date transportEndDate;// 运输结束时间
	private String starting;// 出发港
	private String destination;// 到达港
	private String containerType;// 货柜类型
	private String containerTypeString;// 货柜类型
	private String elseType;// 其它类型
	private String containerSize;// 货柜尺寸
	private BigDecimal unitPrice;// 单价
	private String containerCapacity;// 货柜内容物数量&重量
	private String computeType;// 计算方式
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getFreightType() {
		return freightType;
	}

	public void setFreightType(String freightType) {
		this.freightType = freightType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTransportStartDate() {
		return transportStartDate;
	}

	public void setTransportStartDate(Date transportStartDate) {
		this.transportStartDate = transportStartDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getTransportEndDate() {
		return transportEndDate;
	}

	public void setTransportEndDate(Date transportEndDate) {
		this.transportEndDate = transportEndDate;
	}

	public String getStarting() {
		return starting;
	}

	public void setStarting(String starting) {
		this.starting = starting;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getElseType() {
		return elseType;
	}

	public void setElseType(String elseType) {
		this.elseType = elseType;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getContainerCapacity() {
		return containerCapacity;
	}

	public void setContainerCapacity(String containerCapacity) {
		this.containerCapacity = containerCapacity;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getContainerTypeString() {
		return containerTypeString;
	}

	public void setContainerTypeString(String containerTypeString) {
		this.containerTypeString = containerTypeString;
	}
}