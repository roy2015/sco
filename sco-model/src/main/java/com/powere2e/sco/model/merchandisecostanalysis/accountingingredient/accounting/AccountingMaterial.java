package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import com.powere2e.frame.server.model.AppModel;

/**
 * 核算表-原料信息实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年4月28日
 */
public class AccountingMaterial extends AppModel {

	private static final long serialVersionUID = -8587363588759269494L;
	private String accountingCode;// 核算表编号
	private String materialName;// 主原料名称
	private String materialType;// 原材料类型
	private String remarks;// 备注

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}