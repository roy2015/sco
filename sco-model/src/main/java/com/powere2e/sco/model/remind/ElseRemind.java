package com.powere2e.sco.model.remind;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 其他提醒
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemind extends AppModel{

	private static final long serialVersionUID = -1414415408935996566L;
	private String remindCode;//提醒编号
	private String remindDate;//提醒发布日期
	private String remindType;//提醒类别
	private String remindTypeString;//提醒类别
	private String remindInfo;//提醒信息
	private String qlCode;    //签量编号
	private String configCode;//配置编号
	
	public String getRemindCode() {
		return remindCode;
	}
	public void setRemindCode(String remindCode) {
		this.remindCode = remindCode;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public String getRemindDate() {
		return remindDate;
	}
	public void setRemindDate(String remindDate) {
		this.remindDate = remindDate;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	public String getRemindTypeString() {
		return remindTypeString;
	}
	public void setRemindTypeString(String remindTypeString) {
		this.remindTypeString = remindTypeString;
	}
	public String getRemindInfo() {
		return remindInfo;
	}
	public void setRemindInfo(String remindInfo) {
		this.remindInfo = remindInfo;
	}
	public String getQlCode() {
		return qlCode;
	}
	public void setQlCode(String qlCode) {
		this.qlCode = qlCode;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	
}
