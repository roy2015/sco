package com.powere2e.sco.model.datamaintenance.purchaserdata.relevanceaccessoryandwebsite;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.accessoryintention.AccessoryIntention;

/**
 * 辅料与公示网站原料关联 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月17日
 */
public class RelevanceAccessoryAndWebsite extends AccessoryIntention {
	/**
	 * 
	 */
	private static final long serialVersionUID = 870550657112310730L;
	private String id;// ID
	
	private String accessoryCode;//辅料编码
	private String accessoryName;//辅料名称
	
	private String intentionSupplierCode;//意向供应商编码
	private String intentionSupplierName;//意向供应商名称
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private String websiteMaterialName;//公示网站原料名称
	private String websiteName;//网站名称
	private String websiteUrl;//网站地址
	private String priceRegion;//价格地区
	private String materialBigTypeName;//原料大类
	private String materialSmallTypeName;//原料小类
	
	private BigDecimal weight;//克重 
	private String ifRelevanced;//是否已经关联到网站
	private Date created;//关联时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccessoryCode() {
		return accessoryCode;
	}
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}
	public String getAccessoryName() {
		return accessoryName;
	}
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
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
	public String getWebsiteMaterialName() {
		return websiteMaterialName;
	}
	public void setWebsiteMaterialName(String websiteMaterialName) {
		this.websiteMaterialName = websiteMaterialName;
	}
	public String getWebsiteName() {
		return websiteName;
	}
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	public String getWebsiteUrl() {
		return websiteUrl;
	}
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl;
	}
	public String getPriceRegion() {
		return priceRegion;
	}
	public void setPriceRegion(String priceRegion) {
		this.priceRegion = priceRegion;
	}
	public String getMaterialBigTypeName() {
		return materialBigTypeName;
	}
	public void setMaterialBigTypeName(String materialBigTypeName) {
		this.materialBigTypeName = materialBigTypeName;
	}
	public String getMaterialSmallTypeName() {
		return materialSmallTypeName;
	}
	public void setMaterialSmallTypeName(String materialSmallTypeName) {
		this.materialSmallTypeName = materialSmallTypeName;
	}
	public BigDecimal getWeight() {
		return weight;
	}
	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}
	public String getIfRelevanced() {
		return ifRelevanced;
	}
	public void setIfRelevanced(String ifRelevanced) {
		this.ifRelevanced = ifRelevanced;
	}
	@JSON(format = "yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	
}