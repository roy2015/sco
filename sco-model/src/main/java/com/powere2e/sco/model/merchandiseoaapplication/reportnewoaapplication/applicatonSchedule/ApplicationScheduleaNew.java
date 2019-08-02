package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.applicatonSchedule;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 新品引进进度信息 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月22日
 */
public class ApplicationScheduleaNew extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573448153888148037L;
	private String applicationCode;// 申请单号
	private String intentionCode;// 意向品/商品编号
	private String intentionName;// 意向品/商品名称
	private String supplierCode;// 供应商编号(供应商/意向供应商编号)
	private String supplierName;// (供应商/意向供应商名称)
	private Date firstDate;// 首单日期
	private Date publishDate;// 新品发放日期

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	public String getIntentionCode() {
		return intentionCode;
	}

	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}
	
	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getFirstDate() {
		return firstDate;
	}

	public void setFirstDate(Date firstDate) {
		this.firstDate = firstDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

}