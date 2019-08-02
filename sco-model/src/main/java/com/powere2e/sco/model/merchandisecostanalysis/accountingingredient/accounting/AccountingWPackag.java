package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-外包装实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingWPackag extends AppModel {

	private static final long serialVersionUID = 4051862730763833974L;
	private String accountingCode;// 核算表编号
	private String wpackagType;// 外包装类型
	private String wpackagName;// 外包装类型名称
	private String elseName;// 其他名称
	private BigDecimal price;// 价格
	private BigDecimal unitsPrice;// 单价
	private BigDecimal useQuantity;// 使用量
	private String texture;// 具体材质
	private BigDecimal length;// 长（cm）
	private BigDecimal width;// 宽（cm）
	private BigDecimal height;// 高（cm）
	private BigDecimal area;// 纸箱用料面积（㎡）
	private BigDecimal ylUnitsPrice;// 纸箱用料单价（元/㎡）
	private String specification;// 箱规
	private String remarks;// 备注

	public String getWpackagType() {
		return wpackagType;
	}

	public void setWpackagType(String wpackagType) {
		this.wpackagType = wpackagType;
	}
	
	public String getWpackagName() {
		return wpackagName;
	}

	public void setWpackagName(String wpackagName) {
		this.wpackagName = wpackagName;
	}

	public String getElseName() {
		return elseName;
	}

	public void setElseName(String elseName) {
		this.elseName = elseName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getUnitsPrice() {
		return unitsPrice;
	}

	public void setUnitsPrice(BigDecimal unitsPrice) {
		this.unitsPrice = unitsPrice;
	}

	public BigDecimal getUseQuantity() {
		return useQuantity;
	}

	public void setUseQuantity(BigDecimal useQuantity) {
		this.useQuantity = useQuantity;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public BigDecimal getLength() {
		return length;
	}

	public void setLength(BigDecimal length) {
		this.length = length;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getYlUnitsPrice() {
		return ylUnitsPrice;
	}

	public void setYlUnitsPrice(BigDecimal ylUnitsPrice) {
		this.ylUnitsPrice = ylUnitsPrice;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}
}