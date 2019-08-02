package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew;

import com.powere2e.frame.server.model.AppModel;

/**
 * 报告检验标准(新品引进)实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2016年3月22日
 */
public class CheckStandardNew extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2253046859834406224L;
	private String reportCode;// 报表编号
	private String colour;// 色泽
	private String smell;// 滋气味
	private String form;// 组织形态
	private String moistureContent;// 水份含量
	
	public String getReportCode() {
		return reportCode;
	}
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	public String getColour() {
		return colour;
	}
	public void setColour(String colour) {
		this.colour = colour;
	}
	public String getSmell() {
		return smell;
	}
	public void setSmell(String smell) {
		this.smell = smell;
	}
	public String getForm() {
		return form;
	}
	public void setForm(String form) {
		this.form = form;
	}
	public String getMoistureContent() {
		return moistureContent;
	}
	public void setMoistureContent(String moistureContent) {
		this.moistureContent = moistureContent;
	}

}