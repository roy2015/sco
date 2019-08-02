package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 投料主表实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年5月4日
 */
public class Ingredient extends AppModel {

	private static final long serialVersionUID = 3131008128063336540L;
	private String ingredientCode;// 投料编号
	private String inlandImport;// 国内/进口
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向品供应商编号
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private BigDecimal productCount;// 产品生成量
	private BigDecimal yield;// 得率
	private String moisture;// 成品含水率

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getInlandImport() {
		return inlandImport;
	}

	public void setInlandImport(String inlandImport) {
		this.inlandImport = inlandImport;
	}

	public String getIntentionCode() {
		return intentionCode;
	}

	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public BigDecimal getProductCount() {
		return productCount;
	}

	public void setProductCount(BigDecimal productCount) {
		this.productCount = productCount;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}
}