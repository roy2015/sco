package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商实体类
 * 
 * @author Joyce.li
 * @since 2015年8月18日 上午10:22:37
 * @version 1.0
 */
public class MerchandiseSupplier extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8089996389577572402L;
	private String supplierCode;//供应商编码
	private String supplierName;//供应商名称
	private String companyCode;//公司代码
	private String companyType;//供应商分类
	private String contacts;//联系人
	private String contactsPhone;//联系人电话
	private String companySite;//公司地址
	private String companyPhone;//公司电话
	private String companyFax;//公司传真
	private String postcode;//邮编
	private Date syncDate;//同步时间

	private List<MerchandiseSupplier> list;

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

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getContactsPhone() {
		return contactsPhone;
	}

	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
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

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseSupplier> getList() {
		return list;
	}

	public void setList(List<MerchandiseSupplier> list) {
		this.list = list;
	}

}