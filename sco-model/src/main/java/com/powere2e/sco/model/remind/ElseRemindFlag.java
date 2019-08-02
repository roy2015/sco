package com.powere2e.sco.model.remind;

import com.powere2e.frame.server.model.AppModel;

/**
 * 其他提醒-已阅清除
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class ElseRemindFlag extends AppModel {

	private static final long serialVersionUID = 4893254977298261351L;
	private String remindCode;//提醒编号
	private String flagPerson;//已阅清楚人
	private String remindType;//提醒类型
	private String configCode;//配置编号
	private String qlCode;    // 签量单号
	
	
	public String getRemindCode() {
		return remindCode;
	}
	public void setRemindCode(String remindCode) {
		this.remindCode = remindCode;
	}
	public String getFlagPerson() {
		return flagPerson;
	}
	public void setFlagPerson(String flagPerson) {
		this.flagPerson = flagPerson;
	}
	public String getRemindType() {
		return remindType;
	}
	public void setRemindType(String remindType) {
		this.remindType = remindType;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
	public String getQlCode() {
		return qlCode;
	}
	public void setQlCode(String qlCode) {
		this.qlCode = qlCode;
	}
	
}
