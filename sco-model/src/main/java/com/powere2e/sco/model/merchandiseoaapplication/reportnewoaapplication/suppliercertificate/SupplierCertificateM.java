package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.suppliercertificate;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商证件实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月23日
 */
public class SupplierCertificateM extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2748697797348128462L;
	private String applicationCode;// oa申请单号
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String intentionSupplierCode;// 供应商编号
	private String intentionSupplierName;// 供应商名称
	private String certificateCode;// 证件编号
	private String certificateTypeName;// 真假类型名称
	private String path;// 地址
	private Date created;// 创建时间
	private String createby;// 创建人
	private String startDate;// 开始时间
	private String endDate;// 结束时间
	private String merchandiseCode;// 商品名称
	private String merchandiseName;// 商品名称

	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
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

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
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