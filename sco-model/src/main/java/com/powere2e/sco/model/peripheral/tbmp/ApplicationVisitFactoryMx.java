package com.powere2e.sco.model.peripheral.tbmp;

import com.powere2e.frame.server.model.AppModel;

public class ApplicationVisitFactoryMx extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -50265840501860283L;
	
	private String applicationCode;//申请单号
	private String merchandiseCode;//商品编号
	private String merchandiseName;//商品名称
	private String supplierCode;//
	private String supplierName;//
	private String companySite;//
	private String companyPhone;//
	private String contacts;//联系人
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getCompanySite() {
		return companySite;
	}
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}
	public String getCompanyPhone() {
		return companyPhone;
	}
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	
	

}
