package com.powere2e.sco.model.accessoryoaapplication;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;

/**
 * 采购委员会竞价单OA申请实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class CompareApply extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4307259987915580609L;
	private String applicationCode;//sco编号
	private String applicationCreateby;//sco申请人
	private String intentionCode;//意向品编号
	private String intentionName;//意向品名称
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private String applicationStatus;//申请状态
	private String centreTypeCode;//
	private String smallTypeCode;//
	private String detailTypeCode;//
	private String fineTypeCodes;//
	private String centreTypeName;// 中分类名称
	private String smallTypeName;// 小分类名称
	private String detailTypeName;// 明细类名称
	private String fineTypeName;// 细分类名称
	private String enquiryCode;//询价单号
	private Date enquiryCreated;//询价单创建日期
	private String enquiryCreateby;//询价单创建人
	private String quotedCode;//报价单号
	private Date quotedDate;//报价供应商日期
	private Date quotedCreated;//报价单上传日期
	private Date quotedCreatedEnd;//报价单上传结束日期
	private Date enquiryCreatedEnd;//询价单创建结束日期
	private Date intentionCreatedEnd;
	private Date intentionCreated;//意向品创建时间
	private String accessorySapCode;//SAP物料号
	private String intentionSupplierCode;//
	private String intentionSupplierName;//
	private String oaApplicationCode;//
	private String oaApplicationUrl;//
	private String oaContacts;
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	
	private List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList;
	
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
	public String getEnquiryCode() {
		return enquiryCode;
	}
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getEnquiryCreated() {
		return enquiryCreated;
	}
	public void setEnquiryCreated(Date enquiryCreated) {
		this.enquiryCreated = enquiryCreated;
	}
	public String getEnquiryCreateby() {
		return enquiryCreateby;
	}
	public void setEnquiryCreateby(String enquiryCreateby) {
		this.enquiryCreateby = enquiryCreateby;
	}
	public String getQuotedCode() {
		return quotedCode;
	}
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getQuotedDate() {
		return quotedDate;
	}
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getQuotedCreated() {
		return quotedCreated;
	}
	public void setQuotedCreated(Date quotedCreated) {
		this.quotedCreated = quotedCreated;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getIntentionCreated() {
		return intentionCreated;
	}
	public void setIntentionCreated(Date intentionCreated) {
		this.intentionCreated = intentionCreated;
	}
	@JSON(format="yyyy-MM-dd")
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
	@JSON(format="yyyy-MM-dd")
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
	
	public String getApplicationCreateby() {
		return applicationCreateby;
	}
	public void setApplicationCreateby(String applicationCreateby) {
		this.applicationCreateby = applicationCreateby;
	}
	public String getAccessorySapCode() {
		return accessorySapCode;
	}
	public void setAccessorySapCode(String accessorySapCode) {
		this.accessorySapCode = accessorySapCode;
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
	public List<AccessoryEnquiryQuotedCount> getAccessoryEnquiryQuotedCountList() {
		return accessoryEnquiryQuotedCountList;
	}
	public void setAccessoryEnquiryQuotedCountList(List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList) {
		this.accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountList;
	}
	public String getOaApplicationCode() {
		return oaApplicationCode;
	}
	public void setOaApplicationCode(String oaApplicationCode) {
		this.oaApplicationCode = oaApplicationCode;
	}
	public String getOaApplicationUrl() {
		return oaApplicationUrl;
	}
	public void setOaApplicationUrl(String oaApplicationUrl) {
		this.oaApplicationUrl = oaApplicationUrl;
	}
	public Date getQuotedCreatedEnd() {
		return quotedCreatedEnd;
	}
	public void setQuotedCreatedEnd(Date quotedCreatedEnd) {
		this.quotedCreatedEnd = quotedCreatedEnd;
	}
	public Date getEnquiryCreatedEnd() {
		return enquiryCreatedEnd;
	}
	public void setEnquiryCreatedEnd(Date enquiryCreatedEnd) {
		this.enquiryCreatedEnd = enquiryCreatedEnd;
	}
	public Date getIntentionCreatedEnd() {
		return intentionCreatedEnd;
	}
	public void setIntentionCreatedEnd(Date intentionCreatedEnd) {
		this.intentionCreatedEnd = intentionCreatedEnd;
	}
	public String getOaContacts() {
		return oaContacts;
	}
	public void setOaContacts(String oaContacts) {
		this.oaContacts = oaContacts;
	}
	
	
}