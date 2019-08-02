package com.powere2e.sco.model.datamaintenance.purchaserdata.suppliercertificate;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商证件 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierCertificate extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 679207999473125994L;
	private String certificateCode;// 供应商证照ID
	private String supplierCode;// 供应商编码
	private String supplierName;// 供应商名称
	private String intentionSupplierName;// 意向供应商名称
	private String certificateTypeCode;// 证件类型编号
	private String certificateTypeName;// 证件类型名称
	private Date created;// 上传日期
	private Date startDate;// 开始时间
	private Date endDate;// 结束时间
	private String validRegion;// 有效日期区间

	private String isperpetual;// 是否永久
	private String path;// 证件路径

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = (supplierCode == null ? supplierCode : supplierCode
				.trim());
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = (intentionSupplierName == null ? intentionSupplierName
				: intentionSupplierName.trim());
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = (supplierName == null ? supplierName : supplierName
				.trim());
	}

	public String getCertificateTypeCode() {
		return certificateTypeCode;
	}

	public void setCertificateTypeCode(String certificateTypeCode) {
		this.certificateTypeCode = (certificateTypeCode == null ? certificateTypeCode
				: certificateTypeCode.trim());
	}

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = (certificateTypeName == null ? certificateTypeName
				: certificateTypeName.trim());
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getStartDate() {
		return startDate;
	}

	// 设置
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getEndDate() {
		return endDate;
	}

	// 设置
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	// 获取
	public String getIsperpetual() {
		return isperpetual;
	}

	// 设置
	public void setIsperpetual(String isperpetual) {
		this.isperpetual = isperpetual;
	}

	// 获取
	public String getPath() {
		return path;
	}

	// 设置
	public void setPath(String path) {
		this.path = path;
	}

	public String getCertificateCode() {
		return certificateCode;
	}

	public void setCertificateCode(String certificateCode) {
		this.certificateCode = certificateCode;
	}

	public String getValidRegion() {
		return validRegion;
	}

	public void setValidRegion(String validRegion) {
		this.validRegion = validRegion;
	}

}