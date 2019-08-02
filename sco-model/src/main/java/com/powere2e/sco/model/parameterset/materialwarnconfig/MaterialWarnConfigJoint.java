package com.powere2e.sco.model.parameterset.materialwarnconfig;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

public class MaterialWarnConfigJoint extends AppModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4833705024621120900L;
	private String configCode;//编号
	private String materialBigTypeCode;//原料大类编号
	private String materialBigName;//原料大类名称
	private String materialSmallTypeCode;//原料小类编号
	private String materialSmallName;//原料小类名称
	private String materialCode;//原料编号
	private String materialName;//原料名称
	private String websiteCode;//原料公示编号
	private String websiteName;//原料公示名称
	private String warnType;//预警方式
	private BigDecimal thresholdValue;//阀值
	private Date created;//设置时间
	private Date createds;//设置时间
	private String createby;//设置人
	@JSON(format="yyyy-MM-dd")
	public Date getCreateds() {
		return createds;
	}
	@JSON(format="yyyy-MM-dd")
	public void setCreateds(Date createds) {
		this.createds = createds;
	}
	//获取
	public String getConfigCode() {
		return configCode;
	}
	//设置
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
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
	public String getMaterialBigName() {
		return materialBigName;
	}
	// 设置
	public void setMaterialBigName(String materialBigName) {
		this.materialBigName = materialBigName;
	}
	// 获取
	public String getMaterialSmallTypeCode() {
		return materialSmallTypeCode;
	}
	// 设置
	public void setMaterialSmallTypeCode(String materialSmallTypeCode) {
		this.materialSmallTypeCode = materialSmallTypeCode;
	}
	// 获取
	public String getMaterialSmallName() {
		return materialSmallName;
	}
	// 设置
	public void setMaterialSmallName(String materialSmallName) {
		this.materialSmallName = materialSmallName;
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
		this.materialName = materialName;
	}
	// 获取
	public String getWebsiteCode() {
		return websiteCode;
	}
	// 设置
	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode;
	}
	// 获取
	public String getWebsiteName() {
		return websiteName;
	}
	// 设置
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}
	// 获取
	public String getWarnType() {
		return warnType;
	}
	// 设置
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	// 获取
	public BigDecimal getThresholdValue() {
		return thresholdValue;
	}
	// 设置
	public void setThresholdValue(BigDecimal thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}
	// 设置
	@JSON(format="yyyy-MM-dd")
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
	
}
