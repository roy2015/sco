package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品投料表明细实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月18日
 */
public class IngredientItem extends AppModel {

	private static final long serialVersionUID = -6326328410134659860L;
	private String ingredientCode;// 投料编号
	private String materialType;// 原材料类型
	private String materialCode;// 原料编号
	private String materialName;// 原材料名称
	private String materialOrigin;// 原材料产地
	private String materialBrand;// 原材料品牌
	private String materialLevelSpecification;// 原材料等级与规格
	private BigDecimal purchasePrice;// 采购价格
	private BigDecimal inputCount;// 投入量
	private BigDecimal inputCost;// 投入成本
	private BigDecimal avgCost;// 平均成品成本
	private BigDecimal inputCountProportion;// 投入量占比
	private BigDecimal inputCostProportion;// 投入成品占比
	private String moisture;// 含水率
	private String remarks;// 备注
	private String materialTypeName;// 原料类型名称
	// 前段传json假如为空，BigDecimal类型会报错
	private String inputCountProportionStr;// 投入量占比Str
	private String inputCostProportionStr;// 投入成品占比Str

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialOrigin() {
		return materialOrigin;
	}

	public void setMaterialOrigin(String materialOrigin) {
		this.materialOrigin = materialOrigin;
	}

	public String getMaterialBrand() {
		return materialBrand;
	}

	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}

	public String getMaterialLevelSpecification() {
		return materialLevelSpecification;
	}

	public void setMaterialLevelSpecification(String materialLevelSpecification) {
		this.materialLevelSpecification = materialLevelSpecification;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getInputCount() {
		return inputCount;
	}

	public void setInputCount(BigDecimal inputCount) {
		this.inputCount = inputCount;
	}

	public BigDecimal getInputCost() {
		return inputCost;
	}

	public void setInputCost(BigDecimal inputCost) {
		this.inputCost = inputCost;
	}

	public BigDecimal getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(BigDecimal avgCost) {
		this.avgCost = avgCost;
	}

	public BigDecimal getInputCountProportion() {
		return inputCountProportion;
	}

	public void setInputCountProportion(BigDecimal inputCountProportion) {
		this.inputCountProportion = inputCountProportion;
		if (this.inputCountProportion != null) {
			this.inputCountProportionStr = inputCountProportion.toString();
		}
	}

	public BigDecimal getInputCostProportion() {
		return inputCostProportion;
	}

	public void setInputCostProportion(BigDecimal inputCostProportion) {
		this.inputCostProportion = inputCostProportion;
		if (this.inputCostProportion != null) {
			this.inputCostProportionStr = inputCostProportion.toString();
		}
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getInputCountProportionStr() {
		return inputCountProportionStr;
	}

	public void setInputCountProportionStr(String inputCountProportionStr) {
		if (!StringUtils.isBlank(inputCountProportionStr)) {
			this.inputCountProportion = new BigDecimal(inputCountProportionStr);
		}
		this.inputCountProportionStr = inputCountProportionStr;
	}

	public String getInputCostProportionStr() {
		return inputCostProportionStr;
	}

	public void setInputCostProportionStr(String inputCostProportionStr) {
		if (!StringUtils.isBlank(inputCostProportionStr)) {
			this.inputCostProportion = new BigDecimal(inputCostProportionStr);
		}
		this.inputCostProportionStr = inputCostProportionStr;
	}
}