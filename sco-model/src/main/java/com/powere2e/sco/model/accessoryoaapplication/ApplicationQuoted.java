package com.powere2e.sco.model.accessoryoaapplication;
import com.powere2e.frame.server.model.AppModel;
/**
 * 报价单实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月30日
 */
public class ApplicationQuoted extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1156437028607600246L;
	private String applicationCode;//
	private String intentionCode;//
	private String accessoryCode;//
	private String supplierCode;//
	private String enquiryCode;//
	private String quotedCode;//
	private String intentionSupplierName;
	private String supplierName;
	private String intentionName;
	
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
	public String getIntentionName() {
		return intentionName;
	}
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}
	
}