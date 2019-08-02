package com.powere2e.sco.model.reports;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 我的报表实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月14日
 */
public class Reports extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7053596818432418253L;
	private String applicationCode;//申请单号
	private String reportsCode;// 报表编号
	private String reportsTypeCode;// 报表类型编号
	private String reportsTypeName;// 报表类型名称
	private String reportsName;// 报表名称
	private String reportsUrl;// 报表地址

	// ---- 新增成本分析-商品 -----
	private String accountingCode;// 核算表编号
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向供应商编号
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号

	private String oaStatus;//
	private String oaNo;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	private Date createdEnd;//查询报表日期时的结束时间
	/**
	 * 报表有参构造器
	 * 
	 * @param reportsTypeCode
	 *            报表类型编码
	 * @param reportsName
	 *            报表名称
	 * @param reportsUrl
	 *            报表文件所在路径
	 */
	public Reports(String reportsTypeCode, String reportsName, String reportsUrl) {
		this.reportsTypeCode = reportsTypeCode;
		this.reportsName = reportsName;
		this.reportsUrl = reportsUrl;
	}

	public Reports() {
		super();
	}
	
	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	// 获取
	public String getReportsCode() {
		return reportsCode;
	}
	// 设置
	public void setReportsCode(String reportsCode) {
		this.reportsCode = reportsCode;
	}
	
	// 获取
	public String getReportsTypeCode() {
		return reportsTypeCode;
	}
	// 设置
	public void setReportsTypeCode(String reportsTypeCode) {
		this.reportsTypeCode = reportsTypeCode;
	}
	
	// 获取
	public String getReportsName() {
		return reportsName;
	}
	// 设置
	public void setReportsName(String reportsName) {
		this.reportsName = reportsName;
	}
	
	// 获取
	public String getReportsUrl() {
		return reportsUrl;
	}
	// 设置
	public void setReportsUrl(String reportsUrl) {
		this.reportsUrl = reportsUrl;
	}
	
	public String getAccountingCode() {
		return accountingCode;
	}
	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
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
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	public String getOaStatus() {
		return oaStatus;
	}
	public void setOaStatus(String oaStatus) {
		this.oaStatus = oaStatus;
	}
	public String getOaNo() {
		return oaNo;
	}
	public void setOaNo(String oaNo) {
		this.oaNo = oaNo;
	}
	public String getReportsTypeName() {
		return reportsTypeName;
	}
	public void setReportsTypeName(String reportsTypeName) {
		this.reportsTypeName = reportsTypeName;
	}

	public Date getCreatedEnd() {
		return createdEnd;
	}

	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}
	
}