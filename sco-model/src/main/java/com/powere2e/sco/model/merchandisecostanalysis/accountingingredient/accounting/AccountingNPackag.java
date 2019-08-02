package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-内包装实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingNPackag extends AppModel {

	private static final long serialVersionUID = 2394589197057083795L;
	private String accountingCode;// 核算表编号
	private String npackagType;// 内包装类型
	private String npackagName;// 内包装类型名称
	private String elseName;// 其他名称
	private BigDecimal price;// 价格
	private String texture;// 具体材质
	private BigDecimal kgPrice;// 公斤价格
	private BigDecimal weightProportion;// 重量占比
	private String materialSize;// 尺寸（cm）
	private BigDecimal weight;// 单个克重（g）
	private BigDecimal unitsPrice;// 单价
	private BigDecimal quantity;// 数量
	private String technologyRequirements;// 工艺要求
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getNpackagType() {
		return npackagType;
	}

	public void setNpackagType(String npackagType) {
		this.npackagType = npackagType;
	}

	public String getNpackagName() {
		return npackagName;
	}

	public void setNpackagName(String npackagName) {
		this.npackagName = npackagName;
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

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public BigDecimal getKgPrice() {
		return kgPrice;
	}

	public void setKgPrice(BigDecimal kgPrice) {
		this.kgPrice = kgPrice;
	}

	public BigDecimal getWeightProportion() {
		return weightProportion;
	}

	public void setWeightProportion(BigDecimal weightProportion) {
		this.weightProportion = weightProportion;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getUnitsPrice() {
		return unitsPrice;
	}

	public void setUnitsPrice(BigDecimal unitsPrice) {
		this.unitsPrice = unitsPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getTechnologyRequirements() {
		return technologyRequirements;
	}

	public void setTechnologyRequirements(String technologyRequirements) {
		this.technologyRequirements = technologyRequirements;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}