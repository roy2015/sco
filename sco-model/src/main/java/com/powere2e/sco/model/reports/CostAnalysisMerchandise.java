package com.powere2e.sco.model.reports;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品成本分析
 * @author lipengjie
 * @version 1.0
 * @since 2015年6月26日
 */
public class CostAnalysisMerchandise extends AppModel{

	private static final long serialVersionUID = -1808308570797017101L;
	private String accountingCode;
	private String merchandiseCode;
	private String supplierCode;
	private String reportsCode;
	public String getAccountingCode() {
		return accountingCode;
	}
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
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
	public String getReportsCode() {
		return reportsCode;
	}
	public void setReportsCode(String reportsCode) {
		this.reportsCode = reportsCode;
	}
	
	
}
