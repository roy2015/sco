package com.powere2e.sco.model.parameterset.qlremindconfig;
import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 签量提醒设置实体类
 * @author pudge
 * @version 1.0
 * @since 2015年4月3日
 */
public class QlRemindConfig extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5744022323824019462L;
	private String configCode;//
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
	public BigDecimal getThresholdValue() {
		return thresholdValue;
	}
	// 设置
	public void setThresholdValue(BigDecimal thresholdValue) {
		this.thresholdValue = thresholdValue;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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