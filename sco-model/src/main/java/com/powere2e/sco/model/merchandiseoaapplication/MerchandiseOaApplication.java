package com.powere2e.sco.model.merchandiseoaapplication;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品OA申请单实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月22日
 */
public class MerchandiseOaApplication extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8318412262537526528L;
	private String applicationCode;//
	private String applicationType;//
	private String applicationStatus;//
	private String oaApplicationCode;//
	private String oaContacts;//
	private Date oaApproveDate;//
	private String oaApplicationUrl;//
	private String remarks;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	// 获取
	public String getApplicationType() {
		return applicationType;
	}

	// 设置
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}

	// 获取
	public String getApplicationStatus() {
		return applicationStatus;
	}

	// 设置
	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	// 获取
	public String getOaApplicationCode() {
		return oaApplicationCode;
	}

	// 设置
	public void setOaApplicationCode(String oaApplicationCode) {
		this.oaApplicationCode = oaApplicationCode;
	}

	// 获取
	public String getOaContacts() {
		return oaContacts;
	}

	// 设置
	public void setOaContacts(String oaContacts) {
		this.oaContacts = oaContacts;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOaApproveDate() {
		return oaApproveDate;
	}

	// 设置
	public void setOaApproveDate(Date oaApproveDate) {
		this.oaApproveDate = oaApproveDate;
	}

	// 获取
	public String getOaApplicationUrl() {
		return oaApplicationUrl;
	}

	// 设置
	public void setOaApplicationUrl(String oaApplicationUrl) {
		this.oaApplicationUrl = oaApplicationUrl;
	}

	// 获取
	public String getRemarks() {
		return remarks;
	}

	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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