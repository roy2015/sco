package com.powere2e.sco.model.merchandiseoaapplication.fastimportoaapplication;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品快速引进 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年10月12日
 * @version 1.0
 */
public class ApplicationFastImport extends AppModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5729372430023436256L;
	private String applicationCode;// 申请单号
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向品供应商编号
	private String visitApplicationStatus;// 巡厂申请状态
	private String packageApplicationStatus;// 包装设计申请
	private String intSuppcode;//意向品+供应商编号
	
	public String getApplicationCode() {
		return applicationCode;
	}
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}
	public String getIntentionCode() {
		return intentionCode;
	}
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	public String getVisitApplicationStatus() {
		return visitApplicationStatus;
	}
	public void setVisitApplicationStatus(String visitApplicationStatus) {
		this.visitApplicationStatus = visitApplicationStatus;
	}
	public String getPackageApplicationStatus() {
		return packageApplicationStatus;
	}
	public void setPackageApplicationStatus(String packageApplicationStatus) {
		this.packageApplicationStatus = packageApplicationStatus;
	}
	public String getIntSuppcode() {
		return intSuppcode;
	}
	public void setIntSuppcode(String intSuppcode) {
		this.intSuppcode = intSuppcode;
	}
	
}
