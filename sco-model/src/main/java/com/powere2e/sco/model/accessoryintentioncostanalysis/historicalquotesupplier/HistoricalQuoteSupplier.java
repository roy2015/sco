package com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier;

import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class HistoricalQuoteSupplier extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5292780323888555956L;
	private String applicationCode;// sco编号
	private String intentionCreateby;// 意向品创建人
	private String intentionCode;// 意向品编号
	private String intentionName;// 意向品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String applicationStatus;// 申请状态
	private String centreTypeCode;//
	private String smallTypeCode;//
	private String detailTypeCode;//
	private String fineTypeCodes;//
	private String centreTypeName;// 中分类名称
	private String smallTypeName;// 小分类名称
	private String detailTypeName;// 明细类名称
	private String fineTypeName;// 细分类名称
	private String intentionSupplierCode;//
	private String intentionSupplierName;//

	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getIntentionCreateby() {
		return intentionCreateby;
	}

	public void setIntentionCreateby(String intentionCreateby) {
		this.intentionCreateby = intentionCreateby;
	}

	public String getIntentionCode() {
		return intentionCode;
	}

	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
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

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	public String getFineTypeCodes() {
		return fineTypeCodes;
	}

	public void setFineTypeCodes(String fineTypeCodes) {
		this.fineTypeCodes = fineTypeCodes;
	}

	public String getCentreTypeName() {
		return centreTypeName;
	}

	public void setCentreTypeName(String centreTypeName) {
		this.centreTypeName = centreTypeName;
	}

	public String getSmallTypeName() {
		return smallTypeName;
	}

	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName;
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}

	public String getFineTypeName() {
		return fineTypeName;
	}

	public void setFineTypeName(String fineTypeName) {
		this.fineTypeName = fineTypeName;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getUpdateby() {
		return updateby;
	}

	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

}