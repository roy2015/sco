package com.powere2e.sco.model.peripheral.sap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseSap extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1111318823048832796L;
	private String merchandiseCode;//
	private String supplierCode;//
	private String merchandiseName;//
	private String industryStandards;//
	private String wlType;//
	private String purchaseDepartments;//
	private String purchaseGroup;//
	private String centreTypeCode;//
	private String smallTypeCode;//
	private String detailTypeCode;//
	private String storageForm;//
	private String merchandiseStatus;//
	private BigDecimal netWeight;//
	private String companySite;
	private Date syncDate;//

	private List<MerchandiseSap> list;

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
	public String getMerchandiseName() {
		return merchandiseName;
	}

	// 设置
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	// 获取
	public String getIndustryStandards() {
		return industryStandards;
	}

	// 设置
	public void setIndustryStandards(String industryStandards) {
		this.industryStandards = industryStandards;
	}

	// 获取
	public String getWlType() {
		return wlType;
	}

	// 设置
	public void setWlType(String wlType) {
		this.wlType = wlType;
	}

	// 获取
	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	// 设置
	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}

	// 获取
	public String getPurchaseGroup() {
		return purchaseGroup;
	}

	// 设置
	public void setPurchaseGroup(String purchaseGroup) {
		this.purchaseGroup = purchaseGroup;
	}

	// 获取
	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	// 设置
	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	// 获取
	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	// 设置
	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	// 获取
	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	// 设置
	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	// 获取
	public String getStorageForm() {
		return storageForm;
	}

	// 设置
	public void setStorageForm(String storageForm) {
		this.storageForm = storageForm;
	}

	// 获取
	public String getMerchandiseStatus() {
		return merchandiseStatus;
	}

	// 设置
	public void setMerchandiseStatus(String merchandiseStatus) {
		this.merchandiseStatus = merchandiseStatus;
	}

	// 获取
	public BigDecimal getNetWeight() {
		return netWeight;
	}

	// 设置
	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	public String getCompanySite() {
		return companySite;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
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

	public List<MerchandiseSap> getList() {
		return list;
	}

	public void setList(List<MerchandiseSap> list) {
		this.list = list;
	}

}