package com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 原料实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class Material extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4130862383657172729L;
	private String materialCode;// 原料编号
	private String materialName;// 原料名称
	private String materialBigTypeCode;// 原料大类编号
	private String materialBigTypeName;// 原料大类名称
	private String materialSmallTypeCode;// 原料小类编号
	private String materialSmallTypeName;// 原料小类名称
	private String websiteCode;// 公示网站ID
	private String websiteName;// 公示网站名称
	private String websiteUrl;// 公示网站地址
	private String websiteMaterialName;// 公示网站原料名称
	private String priceRegionCode;//价格地区编号
	private String priceRegion;// 价格地区
	private String materialRegionName;//价格地区名称
	private Date priceDate;// 价格日期
	private Date created;// 价格创建日志
	private BigDecimal price;// 价格

	public String getMaterialRegionName() {
		return materialRegionName;
	}

	public void setMaterialRegionName(String materialRegionName) {
		this.materialRegionName = materialRegionName.trim();
	}

	public String getPriceRegionCode() {
		return priceRegionCode;
	}

	public void setPriceRegionCode(String priceRegionCode) {
		this.priceRegionCode = priceRegionCode;
	}

	public String getPriceRegion() {
		return priceRegion;
	}

	public void setPriceRegion(String priceRegion) {
		this.priceRegion = priceRegion.trim();
	}

	public String getWebsiteCode() {
		return websiteCode;
	}

	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode;
	}

	public String getWebsiteName() {
		return websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName.trim();
	}

	public String getWebsiteUrl() {
		return websiteUrl;
	}

	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl.trim();
	}

	// 获取
	public String getMaterialCode() {
		return materialCode;
	}

	// 设置
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	// 获取
	public String getMaterialName() {
		return materialName;
	}

	// 设置
	public void setMaterialName(String materialName) {
		this.materialName = materialName.trim();
	}

	// 获取
	public String getMaterialBigTypeCode() {
		return materialBigTypeCode;
	}

	// 设置
	public void setMaterialBigTypeCode(String materialBigTypeCode) {
		this.materialBigTypeCode = materialBigTypeCode;
	}

	// 获取
	public String getMaterialSmallTypeCode() {
		return materialSmallTypeCode;
	}

	// 设置
	public void setMaterialSmallTypeCode(String materialSmallTypeCode) {
		this.materialSmallTypeCode = materialSmallTypeCode;
	}

	public String getMaterialBigTypeName() {
		return materialBigTypeName;
	}

	public void setMaterialBigTypeName(String materialBigTypeName) {
		this.materialBigTypeName = materialBigTypeName.trim();
	}

	public String getMaterialSmallTypeName() {
		return materialSmallTypeName;
	}

	public void setMaterialSmallTypeName(String materialSmallTypeName) {
		this.materialSmallTypeName = materialSmallTypeName.trim();
	}

	public String getWebsiteMaterialName() {
		return websiteMaterialName;
	}

	public void setWebsiteMaterialName(String websiteMaterialName) {
		this.websiteMaterialName = websiteMaterialName.trim();
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	public BigDecimal getPrice() {
		return price;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
