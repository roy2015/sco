package com.powere2e.sco.model.accessoryoaapplication.supplierattachment;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商证件--辅料OA实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月4日
 */
public class SupplierAttachmentA extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5360363724753741604L;
	private String applicationCode;//oa申请编号
	private String intentionCode;//意向品编号
	private String intentionName;//意向品名称
	private String intentionSupplierCode;//意向供应商编号
	private String intentionSupplierName;//意向供应商名称
	private String accessoryCode;//
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private String enquiryCode;//
	private String enquiryCreated;//
	private String enquiryCreateby;//
	private String quotedCode;//
	private String quotedDate;//
	private String quotedCreated;//
	private String fileType;//证件类型
	private String elseType;//其他类型
	private String fileName;//证件类型名称
	private String path;//地址
	private String createDate;//创建日期
	private String createby;//创建人
	private String updateDate;//更新日期
	private String updateby;//更新人

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getEnquiryCreated() {
		return enquiryCreated;
	}

	public void setEnquiryCreated(String enquiryCreated) {
		this.enquiryCreated = enquiryCreated;
	}

	public String getEnquiryCreateby() {
		return enquiryCreateby;
	}

	public void setEnquiryCreateby(String enquiryCreateby) {
		this.enquiryCreateby = enquiryCreateby;
	}

	public String getQuotedCreated() {
		return quotedCreated;
	}

	public void setQuotedCreated(String quotedCreated) {
		this.quotedCreated = quotedCreated;
	}

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
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
	public String getAccessoryCode() {
		return accessoryCode;
	}

	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
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
	public String getEnquiryCode() {
		return enquiryCode;
	}

	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	// 获取
	public String getQuotedCode() {
		return quotedCode;
	}

	// 设置
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}

	// 获取
	public String getFileType() {
		return fileType;
	}

	// 设置
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	// 获取
	public String getElseType() {
		return elseType;
	}

	// 设置
	public void setElseType(String elseType) {
		this.elseType = elseType;
	}

	// 获取
	public String getFileName() {
		return fileName;
	}

	// 设置
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getQuotedDate() {
		return quotedDate;
	}

	public void setQuotedDate(String quotedDate) {
		this.quotedDate = quotedDate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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