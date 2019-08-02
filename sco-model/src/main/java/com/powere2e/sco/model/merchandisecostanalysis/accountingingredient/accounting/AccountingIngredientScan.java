package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表/投料表扫描版实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年5月15日
 */
public class AccountingIngredientScan extends AppModel {

	private static final long serialVersionUID = 5265678066955732589L;
	private String accountingCode;// 核算表编号
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向品供应商编号
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private String accountingScanPath;// 核算表扫描版
	private String ingredientScanPath;// 投料表扫描版

	// 获取
	public String getAccountingCode() {
		return accountingCode;
	}

	// 设置
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	// 获取
	public String getAccountingScanPath() {
		return accountingScanPath;
	}

	// 设置
	public void setAccountingScanPath(String accountingScanPath) {
		this.accountingScanPath = accountingScanPath;
	}

	// 获取
	public String getIngredientScanPath() {
		return ingredientScanPath;
	}

	// 设置
	public void setIngredientScanPath(String ingredientScanPath) {
		this.ingredientScanPath = ingredientScanPath;
	}
}