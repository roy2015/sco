package com.powere2e.sco.model.parameterset.materialwarnconfig;
import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 原料行情预警设置实体类
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月8日
 */

public class MaterialWarnConfig extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4396902416274753644L;
	private String configCode;//
	private String materialBigTypeCode;//
	private String materialSmallTypeCode;//
	private String materialCode;//
	private String websiteCode;//
	private String warnType;//
	private BigDecimal thresholdValue;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	// 获取
	public String getConfigCode() {
		return configCode;
	}
	// 设置
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
	public String getMaterialSmallTypeCode() {
		return materialSmallTypeCode;
	}
	// 设置
	public void setMaterialSmallTypeCode(String materialSmallTypeCode) {
		this.materialSmallTypeCode = materialSmallTypeCode;
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
	public String getWebsiteCode() {
		return websiteCode;
	}
	// 设置
	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode;
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
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}
	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	// 获取
	public String getUpdateby() {
		return updateby;
	}
	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}