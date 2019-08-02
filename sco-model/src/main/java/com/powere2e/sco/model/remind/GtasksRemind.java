package com.powere2e.sco.model.remind;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 待办事项提醒
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class GtasksRemind extends AppModel{

	private static final long serialVersionUID = 245973946210875576L;
	private String remindCode;//提醒编号
	private String applicationCode;//申请单号
	private String applicationType;//申请类型
	private String applicationTypeString;//申请类型
	private String applicationPerson;//申请人
	private String approvalDate;//审批通过日期
	
	public String getRemindCode() {
		return remindCode;
	}
	public void setRemindCode(String remindCode) {
		this.remindCode = remindCode;
	}
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getApplicationType() {
		return applicationType;
	}
	public void setApplicationType(String applicationType) {
		this.applicationType = applicationType;
	}
	public String getApplicationPerson() {
		return applicationPerson;
	}
	public void setApplicationPerson(String applicationPerson) {
		this.applicationPerson = applicationPerson;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public String getApprovalDate() {
		return approvalDate;
	}
	public void setApprovalDate(String approvalDate) {
		this.approvalDate = approvalDate;
	}
	public String getApplicationTypeString() {
		return applicationTypeString;
	}
	public void setApplicationTypeString(String applicationTypeString) {
		this.applicationTypeString = applicationTypeString;
	}
	
}
