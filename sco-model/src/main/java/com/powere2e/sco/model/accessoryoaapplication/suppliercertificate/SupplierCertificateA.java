package com.powere2e.sco.model.accessoryoaapplication.suppliercertificate;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商证件(辅料OA)实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月6日
 */
public class SupplierCertificateA extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5375333828766874699L;
	private String applicationCode;//oa申请编号
	private String supplierCode;//供应商编号
	private String certificateCode;//证件编号
	private String supplierName;//供应商名称
	private String intentionSupplierName;//意向供应商名称
	private String certificateTypeName;//证件类型名称
	private String path;//地址
	private String createdate;//创建日期
	private String createby;//创建人
	private String startDate;//开始日期
	private String endDate;//结束时间

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

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	// 获取
	public String getCertificateCode() {
		return certificateCode;
	}

	// 设置
	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}
}