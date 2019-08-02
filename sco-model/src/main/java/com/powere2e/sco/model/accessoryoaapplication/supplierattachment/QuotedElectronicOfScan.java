package com.powere2e.sco.model.accessoryoaapplication.supplierattachment;

import com.powere2e.frame.server.model.AppModel;

/**
 * 关联辅料报价单-电子和扫描--辅料OA实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月5日
 */
public class QuotedElectronicOfScan extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7379216151296589153L;
	private String quotedCodeScan;//
	private String applicationCode;// oa申请编号
	private String intentionCode;// 意向品编号
	private String intentionName;// 意向品名称
	private String intentionSupplierCode;// 意向供应商编号
	private String intentionSupplierName;// 意向供应名称
	private String accessoryCode;//
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String path;// 地址
	private String quotedCodeElectronic;//
	private String quotedDate;
	private String createDate;// 创建时间

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	// 获取
	public String getQuotedCodeScan() {
		return quotedCodeScan;
	}

	// 设置
	public void setQuotedCodeScan(String quotedCodeScan) {
		this.quotedCodeScan = quotedCodeScan;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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
	public String getQuotedCodeElectronic() {
		return quotedCodeElectronic;
	}

	// 设置
	public void setQuotedCodeElectronic(String quotedCodeElectronic) {
		this.quotedCodeElectronic = quotedCodeElectronic;
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

}