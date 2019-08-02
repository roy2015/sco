package com.powere2e.sco.model.masterdata;

import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;

/**
 * 供应商实体类
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class Supplier extends AppModel {

	private static final long serialVersionUID = 8089996389577572402L;
	private String companyPhone;//
	private String companyFax;//
	private String postcode;//
	private Date syncDate;//
	private String supplierCode;//
	private String supplierName;//
	private String companyType;//
	private String contacts;//
	private String contactsPhone;//
	private String companySite;//

	// 获取
	public String getCompanyPhone() {
		return companyPhone;
	}

	// 设置
	public void setCompanyPhone(String companyPhone) {
		this.companyPhone = companyPhone;
	}

	// 获取
	public String getCompanyFax() {
		return companyFax;
	}

	// 设置
	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	// 获取
	public String getPostcode() {
		return postcode;
	}

	// 设置
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	// 设置
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
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
	public String getSupplierName() {
		return supplierName;
	}

	// 设置
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	// 获取
	public String getCompanyType() {
		return companyType;
	}

	// 设置
	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	// 获取
	public String getContacts() {
		return contacts;
	}

	// 设置
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	// 获取
	public String getContactsPhone() {
		return contactsPhone;
	}

	// 设置
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	// 获取
	public String getCompanySite() {
		return companySite;
	}

	// 设置
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}
}