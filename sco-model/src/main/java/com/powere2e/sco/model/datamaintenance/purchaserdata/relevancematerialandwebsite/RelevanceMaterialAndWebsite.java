package com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 商品原料与公示网站原料关联 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月13日
 */
public class RelevanceMaterialAndWebsite extends Merchandise {
	/**
	 * 
	 */
	private static final long serialVersionUID = 870550657112310730L;
	private String id;// ID
	private String materialType;// 商品原料类型(投料明细表)
	private String materialTypeName;// 商品原料类型名称

	private String ingredientCode; // 商品原料编号(投料明细表)
	private String ingredientCodeName; // 商品原料名称

	private String materialCode;// 物料编码
	private String materialName;// 物料名称(公示网站原料名称)
	private String websiteCode;// 公示网站编号
	private String websiteName;// 公示网站名称
	private String websiteUrl;// 公示网站地址

	private String priceRegion;// 价格地区
	private String materialBigTypeCode;// 原料大类
	private String materialBigTypeName;// 原料大类名称
	private String materialSmallTypeCode;// 原料小类
	private String materialSmallTypeName;// 原料小类名称
	private String ifRelevanced;// 是否已经关联网站
	private Double inputCost;// 投入成本占比

	private Date created;// 关联时间
	private String createby;// 关联人

	public RelevanceMaterialAndWebsite() {

	}
	
	public RelevanceMaterialAndWebsite(String ingredientCode, String materialCode) {
		this.ingredientCode = ingredientCode;
		this.materialCode = materialCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialTypeName() {
		return materialTypeName;
	}

	public void setMaterialTypeName(String materialTypeName) {
		this.materialTypeName = materialTypeName;
	}

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getIngredientCodeName() {
		return ingredientCodeName;
	}

	public void setIngredientCodeName(String ingredientCodeName) {
		this.ingredientCodeName = ingredientCodeName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
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

	public String getMaterialBigTypeCode() {
		return materialBigTypeCode;
	}

	public void setMaterialBigTypeCode(String materialBigTypeCode) {
		this.materialBigTypeCode = materialBigTypeCode;
	}

	public String getMaterialBigTypeName() {
		return materialBigTypeName;
	}

	public void setMaterialBigTypeName(String materialBigTypeName) {
		this.materialBigTypeName = materialBigTypeName;
	}

	public String getMaterialSmallTypeCode() {
		return materialSmallTypeCode;
	}

	public void setMaterialSmallTypeCode(String materialSmallTypeCode) {
		this.materialSmallTypeCode = materialSmallTypeCode;
	}

	public String getMaterialSmallTypeName() {
		return materialSmallTypeName;
	}

	public void setMaterialSmallTypeName(String materialSmallTypeName) {
		this.materialSmallTypeName = materialSmallTypeName;
	}

	public Double getInputCost() {
		return inputCost;
	}

	public void setInputCost(Double inputCost) {
		this.inputCost = inputCost;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}

	// 设置
	public void setCreated(Date created) {
		this.created = created;
	}

	// 获取
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getIfRelevanced() {
		return ifRelevanced;
	}

	public void setIfRelevanced(String ifRelevanced) {
		this.ifRelevanced = ifRelevanced;
	}

}