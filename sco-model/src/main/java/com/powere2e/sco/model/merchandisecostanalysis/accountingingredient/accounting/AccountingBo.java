package com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;

/**
 * 商品成本分析搜索结果实体类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccountingBo extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4634222123170987731L;
	private String accountingCode;// 核算表编号
	private String intentionCode;// 商品意向品编号
	private String intentionName;// 商品意向品名称
	private String intentionSupplierCode;// 商品意向品供应商编号
	private String intentionSupplierName;// 商品意向品供应商名称
	private String intentionSmallTypeCode;// 商品意向品小分类编号
	private String elseTypeName;// 其他类型名称
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String merchandiseDxRoleCode;// 商品定性角色编号
	private String merchandiseDxRoleName;// 商品定性角色名称
	private String merchandiseDlRoleCode;// 商品定量角色编号
	private String merchandiseDlRoleName;// 商品定量角色名称
	private String purchaseDepartments;// 采购部门(商品)
	private String intentionPurchaseDepartments; // 采购部门(商品意向品表)
	private String rationed;// 是否定量装(商品意向品表)
	private String netWeight;// 是否定量装(商品表)
	private String purchaseType;// 采购类型(商品意向品表)
	private String saleType;// 销售方式(商品意向品表)
	private String storageForm;// 存储形式(商品)
	private String centreTypeCode;// 中分类编号
	private String centreTypeName;// 中分类名称
	private String smallTypeCode;// 小分类编号
	private String smallTypeName;// 小分类名称
	private String detailTypeCode;// 明细类编号
	private String detailTypeName;// 明细类名称
	private String fineTypeCode;// 细分类编号
	private String fineTypeName;// 细分类名称
	private String accountingScanPath;// 核算表扫描版
	private String ingredientScanPath;// 投料表扫描版
	private String applicationCode;// OA申请单号
	private String applicationStatus;// OA申请状态
	private String applicationStatusValue;// OA申请状态 value
	private Date oaApproveDate;// OA审批通过时间
	private String region;// 进货地区
	private Date quotedDate;// 报价日期
	private Date created;// 创建时间
	private String createby;// 创建人
	private Date updated;// 修改时间
	private String updateby;// 修改人
	
	private BigDecimal contractPrice;//合作价格
	private BigDecimal sumPrice;//地区总价
	private String oaContacts;//OA系统申请人
	private String inlandImport; //进口/非进口

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

	public String getFineTypeCode() {
		return fineTypeCode;
	}

	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
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

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
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

	public String getIntentionSmallTypeCode() {
		return intentionSmallTypeCode;
	}

	public void setIntentionSmallTypeCode(String intentionSmallTypeCode) {
		this.intentionSmallTypeCode = intentionSmallTypeCode;
	}

	public String getElseTypeName() {
		return elseTypeName;
	}

	public void setElseTypeName(String elseTypeName) {
		this.elseTypeName = elseTypeName;
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

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
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

	public String getMerchandiseDxRoleCode() {
		return merchandiseDxRoleCode;
	}

	public void setMerchandiseDxRoleCode(String merchandiseDxRoleCode) {
		this.merchandiseDxRoleCode = merchandiseDxRoleCode;
	}

	public String getMerchandiseDxRoleName() {
		return merchandiseDxRoleName;
	}

	public void setMerchandiseDxRoleName(String merchandiseDxRoleName) {
		this.merchandiseDxRoleName = merchandiseDxRoleName;
	}

	public String getMerchandiseDlRoleCode() {
		return merchandiseDlRoleCode;
	}

	public void setMerchandiseDlRoleCode(String merchandiseDlRoleCode) {
		this.merchandiseDlRoleCode = merchandiseDlRoleCode;
	}

	public String getMerchandiseDlRoleName() {
		return merchandiseDlRoleName;
	}

	public void setMerchandiseDlRoleName(String merchandiseDlRoleName) {
		this.merchandiseDlRoleName = merchandiseDlRoleName;
	}

	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}

	public String getIntentionPurchaseDepartments() {
		return intentionPurchaseDepartments;
	}

	public void setIntentionPurchaseDepartments(String intentionPurchaseDepartments) {
		this.intentionPurchaseDepartments = intentionPurchaseDepartments;
	}

	public String getRationed() {
		return rationed;
	}

	public void setRationed(String rationed) {
		this.rationed = rationed;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getStorageForm() {
		return storageForm;
	}

	public void setStorageForm(String storageForm) {
		this.storageForm = storageForm;
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

	public String getAccountingScanPath() {
		return accountingScanPath;
	}

	public void setAccountingScanPath(String accountingScanPath) {
		this.accountingScanPath = accountingScanPath;
	}

	public String getIngredientScanPath() {
		return ingredientScanPath;
	}

	public void setIngredientScanPath(String ingredientScanPath) {
		this.ingredientScanPath = ingredientScanPath;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public String getApplicationStatusValue() {
		return applicationStatusValue;
	}

	public void setApplicationStatusValue(String applicationStatusValue) {
		this.applicationStatusValue = applicationStatusValue;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getOaApproveDate() {
		return oaApproveDate;
	}

	public void setOaApproveDate(Date approveDate) {
		this.oaApproveDate = approveDate;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQuotedDate() {
		return quotedDate;
	}

	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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
	
	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public String getOaContacts() {
		return oaContacts;
	}

	public void setOaContacts(String oaContacts) {
		this.oaContacts = oaContacts;
	}

	public String getInlandImport() {
		return inlandImport;
	}

	public void setInlandImport(String inlandImport) {
		this.inlandImport = inlandImport;
	}
	
}