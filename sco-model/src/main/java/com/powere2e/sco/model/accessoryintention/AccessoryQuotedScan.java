package com.powere2e.sco.model.accessoryintention;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 辅料报价单-扫描版实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryQuotedScan extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3691872320703397918L;
	private String quotedCode;//
	private String enquiryCode;//
	private String enquiryName;//
	private String intentionCode;//
	private String intentionSupplierCode;//
	private String supplierCode;//
	private String supplierName;
	private String intentionSupplierName;
	private Date quotedDate;//
	private String path;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	// 获取
	public String getQuotedCode() {
		return quotedCode;
	}
	// 设置
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}
	
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}
	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}
	
	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	
	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}
	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getQuotedDate() {
		return quotedDate;
	}
	// 设置
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}
	
	// 获取
	public String getPath() {
		return path;
	}
	// 设置
	public void setPath(String path) {
		this.path = path;
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
	@JSON(format="yyyy-MM-dd")
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
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}
	public String getEnquiryName() {
		return enquiryName;
	}
	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
	}
	
}