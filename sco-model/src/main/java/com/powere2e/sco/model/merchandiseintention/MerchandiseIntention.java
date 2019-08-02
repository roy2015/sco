package com.powere2e.sco.model.merchandiseintention;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品意向品实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月18日
 */
public class MerchandiseIntention extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2115209275925028633L;
	private String intentionCode;// 意向品编号
	private String intentionName;// 意向品名称
	private String centreTypeCode;// 中分类
	private String smallTypeCode;// 小分类
	private String elseTypeName;// 小分类其他分类名称
	private String detailTypeCode;// 明细类
	private String fineTypeCode;// 细分类
	private String purchaseDepartments;// 采购部门
	private String rationed;// 是否定量
	private String purchaseType;// 采购类型
	private String purchaseDepartmentsName;// 采购部门的名称
	private String rationedName;// 是否定量的名称
	private String purchaseTypeName;// 采购类型的名称
	private String saleType;// 销售方式
	private String orderType;// 订购数量
	private String quotedCurrency;// 报价币种
	private String paymentType;// 付款方式
	private String deliveryType;// 交货方式
	private String specification;// 规格
	private String packingType;// 包装要求
	private Date created;// 创建日期
	private String createby;// 创建人
	private Date updated;// 修改日期
	private String updateby;// 修改人

	private String intentionSupplierCode;// 意向品供应商编号
	private String intentionSupplierName;// 供应商名称
	private String supCompanySite;// 供应商地址
	private String supContacts;// 供应商联系人
	private String supContactsPhone;// 联系电话
	
	private String merchandiseCode;// SAP物料号
	private String merchandiseName;// SAP物料号
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String intentionSapCode;// 意向品SAP物料号
	private Date quotedDate;// 最后一次报价日期
	private Date foretasteDate;// 最后一次试吃日期
	private BigDecimal foretasteGrade;// 最后一次试吃评分
	private String evaluate;// 最后一次试吃反馈
	private String isForetastePass;// 是否试吃通过
	private Date foretastePassDate;// 试吃通过时间

	private String centreTypeName;// 中分类名称
	private String smallTypeName;// 小分类名称
	private String detailTypeName;// 明细类名称
	private String fineTypeName;// 细分类名称
	private Date enquiryDate;// 报价日期
	private String isTrueMerchandise;// 是否为正式商品(Y:商品,N:意向品),在报价比价中用到
	private String applicationCode;// OA申请单号
	private String applicationStatus;// OA申请状态
	private String dxRoleCode;// OA申请定性角色
	private String dlRoleCode;// OA申请定量角色
	private String applicationStatusName;// OA申请状态名称
	private String dxRoleName;// OA申请定性角色名称
	private String dlRoleName;// OA申请定量角色名称
	private String oaApplicationCode;// OA系统申请单号
	private String oaApplicationName;// OA系统申请人
	private Date oaApplicationDate;// OA系统申请日期
	private String oaContacts;// OA系统联系人

	private String createUserName;

	// 进度信息
	private Date xcsqDate;// 巡厂申请日期
	private Date pkxcDate;// 品控巡厂日期
	// private Date bzsjsqDate;// 包装设计申请日期
	private Date yjbgtjDate;// 引进报告提交日期
	private Date yjbgwcDate;// 引进报告完成日期
	private Date zsjsqDate;// 主数据申请日期
	private Date zsjsqwcDate;// 主数据申请完成日期
	private Date htqdDate;// 合同签订日期
	private Date bbtgDate;// 八标提供日期
	private Date qdgpDate;// 取得光盘日期
	private Date gyssdgpDate;// 供应商收到光盘日期
	private Date gzysqrDate;// 包装印刷确认日期
	private Date dyqrDate;// 大样确认日期
	private Date spdddcDate;// 商品到达大仓日期
	private Date ssDate;// 上市日期

	private String applicationLink;// 新品引进申请审批环节,即目前由谁审批中
	private String visitApplicationStatus;// 巡厂申请审批状态
	private String visitApplicationStatusName;// 巡厂申请审批状态
	private String visitApplicationLink;// 巡厂申请审批环节
	private String packageApplicationStatus;// 包装设计申请审批状态
	private String packageApplicationStatusName;// 包装设计申请审批状态
	private String packageApplicationLink;// 包装设计申请审批环节

	private Date xcsqtjDate; // 巡厂申请提交日期
	private Integer xcsqtjStatus; // 巡厂申请提交状态
	private Integer xcsqtjBzDay; // 巡厂申请提交标准天数
	private Integer xcsqtjSjDay; // 巡厂申请提交实际天数
	private Date xcsqshtgDate; // 巡厂申请审批通过日期
	private Integer xcsqshtgStatus; // 巡厂申请审批通过状态
	private Integer xcsqshtgBzDay; // 巡厂申请审批通过标准天数
	private Integer xcsqshtgSjDay; // 巡厂申请审批通过实际天数
	private Date pksjxcDate; // 品控实际巡厂日期
	private Integer pksjxcStatus; // 品控实际巡厂状态
	private Integer pksjxcBzDay; // 品控实际巡厂标准天数
	private Integer pksjxcSjDay; // 品控实际巡厂实际天数
	private Date xpyjbgtjDate; // 新品引进申请提交日期
	private Integer xpyjbgtjStatus; // 新品引进申请提交状态
	private Integer xpyjbgtjBzDay; // 新品引进申请提交标准天数
	private Integer xpyjbgtjSjDay; // 新品引进申请提交实际天数
	private Date xpyjbgsptgDate; // 新品引进审批通过日期
	private Integer xpyjbgsptgStatus; // 新品引进审批通过状态
	private Integer xpyjbgsptgBzDay; // 新品引进审批通过标准天数
	private Integer xpyjbgsptgSjDay; // 新品引进审批通过实际天数
	private Date zsjsqsptgDate; // 物料主数据申请审批通过日期
	private Integer zsjsqsptgStatus; // 物料主数据申请审批通过状态
	private Integer zsjsqsptgBzDay; // 物料主数据申请审批通过标准天数
	private Integer zsjsqsptgSjDay; // 物料主数据申请审批通过实际天数
	private Date bzsjsqDate; // 包装设计申请提交日期
	private Integer bzsjsqStatus; // 包装设计申请提交状态
	private Integer bzsjsqBzDay; // 包装设计申请提交标准天数
	private Integer bzsjsqSjDay; // 包装设计申请提交实际天数
	private Date bzsjsqwcDate; // 包装设计审批通过日期
	private Integer bzsjsqwcStatus; // 包装设计审批通过状态
	private Integer bzsjsqwcBzDay; // 包装设计审批通过标准天数
	private Integer bzsjsqwcSjDay; // 包装设计审批通过实际天数
	private Date bzsjcgqrDate; // 包装设计初稿确认日期
	private Integer bzsjcgqrStatus; // 包装设计初稿确认状态
	private Integer bzsjcgqrBzDay; // 包装设计初稿确认标准天数
	private Integer bzsjcgqrSjDay; // 包装设计初稿确认实际天数
	private Date bzsjwgtgDate; // 包装设计完稿通过日期
	private Integer bzsjwgtgStatus; // 包装设计完稿通过状态
	private Integer jlbzsjcgqrBzDay; // 距离包装设计初稿确认日期标准天数
	private Integer jlbzsjcgqrSjDay; // 距离包装设计初稿确认日期实际天数
	private Integer jlwlzsjsqsptgBzDay; // 距离物料主数据申请审批通过日期标准天数
	private Integer jlwlzsjsqsptgSjDay; // 距离物料主数据申请审批通过日期实际天数
	private Date xpffDate;// 新品发放日期
	private Integer xpffStatus; // 新品发放状态
	private Integer xpffBzDay; // 新品发放标准天数
	private Integer xpffSjDay; // 新品发放实际天数

	private Integer bzDay; // 标准总天数
	private Integer sjDay; // 实际总天数

	private Integer sctgBzDay; // 试吃通过标准天数
	private Integer sctgSjDay; // 试吃通过实际天数

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionName() {
		return intentionName;
	}

	// 设置
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	// 获取
	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	// 设置
	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	// 获取
	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	// 设置
	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	// 获取
	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	// 设置
	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	// 获取
	public String getFineTypeCode() {
		return fineTypeCode;
	}

	// 设置
	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}

	// 获取
	public String getRationed() {
		return rationed;
	}

	// 设置
	public void setRationed(String rationed) {
		this.rationed = rationed;
	}

	// 获取
	public String getPurchaseType() {
		return purchaseType;
	}

	// 设置
	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	// 获取
	public String getSaleType() {
		return saleType;
	}

	// 设置
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	// 获取
	public String getOrderType() {
		return orderType;
	}

	// 设置
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	// 获取
	public String getQuotedCurrency() {
		return quotedCurrency;
	}

	// 设置
	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}

	// 获取
	public String getPaymentType() {
		return paymentType;
	}

	// 设置
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	// 获取
	public String getDeliveryType() {
		return deliveryType;
	}

	// 设置
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	// 获取
	public String getSpecification() {
		return specification;
	}

	// 设置
	public void setSpecification(String specification) {
		this.specification = specification;
	}

	// 获取
	public String getPackingType() {
		return packingType;
	}

	// 设置
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
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
	@JSON(format = "yyyy-MM-dd")
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

	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
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

	public String getIntentionSapCode() {
		return intentionSapCode;
	}

	public void setIntentionSapCode(String intentionSapCode) {
		this.intentionSapCode = intentionSapCode;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getQuotedDate() {
		return quotedDate;
	}

	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getForetasteDate() {
		return foretasteDate;
	}

	public void setForetasteDate(Date foretasteDate) {
		this.foretasteDate = foretasteDate;
	}

	public BigDecimal getForetasteGrade() {
		return foretasteGrade;
	}

	public void setForetasteGrade(BigDecimal foretasteGrade) {
		this.foretasteGrade = foretasteGrade;
	}

	public String getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getEnquiryDate() {
		return enquiryDate;
	}

	public void setEnquiryDate(Date enquiryDate) {
		this.enquiryDate = enquiryDate;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

	public String getSupCompanySite() {
		return supCompanySite;
	}

	public void setSupCompanySite(String supCompanySite) {
		this.supCompanySite = supCompanySite;
	}

	public String getSupContacts() {
		return supContacts;
	}

	public void setSupContacts(String supContacts) {
		this.supContacts = supContacts;
	}

	public String getSupContactsPhone() {
		return supContactsPhone;
	}

	public void setSupContactsPhone(String supContactsPhone) {
		this.supContactsPhone = supContactsPhone;
	}

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
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

	public String getPurchaseDepartmentsName() {
		return purchaseDepartmentsName;
	}

	public void setPurchaseDepartmentsName(String purchaseDepartmentsName) {
		this.purchaseDepartmentsName = purchaseDepartmentsName;
	}

	public String getRationedName() {
		return rationedName;
	}

	public void setRationedName(String rationedName) {
		this.rationedName = rationedName;
	}

	public String getPurchaseTypeName() {
		return purchaseTypeName;
	}

	public void setPurchaseTypeName(String purchaseTypeName) {
		this.purchaseTypeName = purchaseTypeName;
	}

	public String getIsTrueMerchandise() {
		return isTrueMerchandise;
	}

	public void setIsTrueMerchandise(String isTrueMerchandise) {
		this.isTrueMerchandise = isTrueMerchandise;
	}

	public String getElseTypeName() {
		return elseTypeName;
	}

	public void setElseTypeName(String elseTypeName) {
		this.elseTypeName = elseTypeName;
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

	public String getDxRoleCode() {
		return dxRoleCode;
	}

	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
	}

	public String getDlRoleCode() {
		return dlRoleCode;
	}

	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}

	public String getApplicationStatusName() {
		return applicationStatusName;
	}

	public void setApplicationStatusName(String applicationStatusName) {
		this.applicationStatusName = applicationStatusName;
	}

	public String getDxRoleName() {
		return dxRoleName;
	}

	public void setDxRoleName(String dxRoleName) {
		this.dxRoleName = dxRoleName;
	}

	public String getDlRoleName() {
		return dlRoleName;
	}

	public void setDlRoleName(String dlRoleName) {
		this.dlRoleName = dlRoleName;
	}

	public String getOaApplicationCode() {
		return oaApplicationCode;
	}

	public void setOaApplicationCode(String oaApplicationCode) {
		this.oaApplicationCode = oaApplicationCode;
	}

	public String getOaContacts() {
		return oaContacts;
	}

	public void setOaContacts(String oaContacts) {
		this.oaContacts = oaContacts;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXcsqDate() {
		return xcsqDate;
	}

	public void setXcsqDate(Date xcsqDate) {
		this.xcsqDate = xcsqDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPkxcDate() {
		return pkxcDate;
	}

	public void setPkxcDate(Date pkxcDate) {
		this.pkxcDate = pkxcDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getYjbgtjDate() {
		return yjbgtjDate;
	}

	public void setYjbgtjDate(Date yjbgtjDate) {
		this.yjbgtjDate = yjbgtjDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getYjbgwcDate() {
		return yjbgwcDate;
	}

	public void setYjbgwcDate(Date yjbgwcDate) {
		this.yjbgwcDate = yjbgwcDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getZsjsqDate() {
		return zsjsqDate;
	}

	public void setZsjsqDate(Date zsjsqDate) {
		this.zsjsqDate = zsjsqDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getZsjsqwcDate() {
		return zsjsqwcDate;
	}

	public void setZsjsqwcDate(Date zsjsqwcDate) {
		this.zsjsqwcDate = zsjsqwcDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getHtqdDate() {
		return htqdDate;
	}

	public void setHtqdDate(Date htqdDate) {
		this.htqdDate = htqdDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBbtgDate() {
		return bbtgDate;
	}

	public void setBbtgDate(Date bbtgDate) {
		this.bbtgDate = bbtgDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getQdgpDate() {
		return qdgpDate;
	}

	public void setQdgpDate(Date qdgpDate) {
		this.qdgpDate = qdgpDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getGyssdgpDate() {
		return gyssdgpDate;
	}

	public void setGyssdgpDate(Date gyssdgpDate) {
		this.gyssdgpDate = gyssdgpDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getGzysqrDate() {
		return gzysqrDate;
	}

	public void setGzysqrDate(Date gzysqrDate) {
		this.gzysqrDate = gzysqrDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getDyqrDate() {
		return dyqrDate;
	}

	public void setDyqrDate(Date dyqrDate) {
		this.dyqrDate = dyqrDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSpdddcDate() {
		return spdddcDate;
	}

	public void setSpdddcDate(Date spdddcDate) {
		this.spdddcDate = spdddcDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getSsDate() {
		return ssDate;
	}

	public void setSsDate(Date ssDate) {
		this.ssDate = ssDate;
	}

	@JSON(format = "yyyy-MM-dd")
	public String getIsForetastePass() {
		return isForetastePass;
	}

	public void setIsForetastePass(String isForetastePass) {
		this.isForetastePass = isForetastePass;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getForetastePassDate() {
		return foretastePassDate;
	}

	public void setForetastePassDate(Date foretastePassDate) {
		this.foretastePassDate = foretastePassDate;
	}

	public String getApplicationLink() {
		return applicationLink;
	}

	public void setApplicationLink(String applicationLink) {
		this.applicationLink = applicationLink;
	}

	public String getVisitApplicationStatus() {
		return visitApplicationStatus;
	}

	public void setVisitApplicationStatus(String visitApplicationStatus) {
		this.visitApplicationStatus = visitApplicationStatus;
	}

	public String getVisitApplicationStatusName() {
		return visitApplicationStatusName;
	}

	public void setVisitApplicationStatusName(String visitApplicationStatusName) {
		this.visitApplicationStatusName = visitApplicationStatusName;
	}

	public String getVisitApplicationLink() {
		return visitApplicationLink;
	}

	public void setVisitApplicationLink(String visitApplicationLink) {
		this.visitApplicationLink = visitApplicationLink;
	}

	public String getPackageApplicationStatus() {
		return packageApplicationStatus;
	}

	public void setPackageApplicationStatus(String packageApplicationStatus) {
		this.packageApplicationStatus = packageApplicationStatus;
	}

	public String getPackageApplicationStatusName() {
		return packageApplicationStatusName;
	}

	public void setPackageApplicationStatusName(String packageApplicationStatusName) {
		this.packageApplicationStatusName = packageApplicationStatusName;
	}

	public String getPackageApplicationLink() {
		return packageApplicationLink;
	}

	public void setPackageApplicationLink(String packageApplicationLink) {
		this.packageApplicationLink = packageApplicationLink;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXcsqtjDate() {
		return xcsqtjDate;
	}

	public void setXcsqtjDate(Date xcsqtjDate) {
		this.xcsqtjDate = xcsqtjDate;
	}

	public Integer getXcsqtjStatus() {
		return xcsqtjStatus;
	}

	public void setXcsqtjStatus(Integer xcsqtjStatus) {
		this.xcsqtjStatus = xcsqtjStatus;
	}

	public Integer getXcsqtjBzDay() {
		return xcsqtjBzDay;
	}

	public void setXcsqtjBzDay(Integer xcsqtjBzDay) {
		this.xcsqtjBzDay = xcsqtjBzDay;
	}

	public Integer getXcsqtjSjDay() {
		return xcsqtjSjDay;
	}

	public void setXcsqtjSjDay(Integer xcsqtjSjDay) {
		this.xcsqtjSjDay = xcsqtjSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXcsqshtgDate() {
		return xcsqshtgDate;
	}

	public void setXcsqshtgDate(Date xcsqshtgDate) {
		this.xcsqshtgDate = xcsqshtgDate;
	}

	public Integer getXcsqshtgStatus() {
		return xcsqshtgStatus;
	}

	public void setXcsqshtgStatus(Integer xcsqshtgStatus) {
		this.xcsqshtgStatus = xcsqshtgStatus;
	}

	public Integer getXcsqshtgBzDay() {
		return xcsqshtgBzDay;
	}

	public void setXcsqshtgBzDay(Integer xcsqshtgBzDay) {
		this.xcsqshtgBzDay = xcsqshtgBzDay;
	}

	public Integer getXcsqshtgSjDay() {
		return xcsqshtgSjDay;
	}

	public void setXcsqshtgSjDay(Integer xcsqshtgSjDay) {
		this.xcsqshtgSjDay = xcsqshtgSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getPksjxcDate() {
		return pksjxcDate;
	}

	public void setPksjxcDate(Date pksjxcDate) {
		this.pksjxcDate = pksjxcDate;
	}

	public Integer getPksjxcStatus() {
		return pksjxcStatus;
	}

	public void setPksjxcStatus(Integer pksjxcStatus) {
		this.pksjxcStatus = pksjxcStatus;
	}

	public Integer getPksjxcBzDay() {
		return pksjxcBzDay;
	}

	public void setPksjxcBzDay(Integer pksjxcBzDay) {
		this.pksjxcBzDay = pksjxcBzDay;
	}

	public Integer getPksjxcSjDay() {
		return pksjxcSjDay;
	}

	public void setPksjxcSjDay(Integer pksjxcSjDay) {
		this.pksjxcSjDay = pksjxcSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXpyjbgtjDate() {
		return xpyjbgtjDate;
	}

	public void setXpyjbgtjDate(Date xpyjbgtjDate) {
		this.xpyjbgtjDate = xpyjbgtjDate;
	}

	public Integer getXpyjbgtjStatus() {
		return xpyjbgtjStatus;
	}

	public void setXpyjbgtjStatus(Integer xpyjbgtjStatus) {
		this.xpyjbgtjStatus = xpyjbgtjStatus;
	}

	public Integer getXpyjbgtjBzDay() {
		return xpyjbgtjBzDay;
	}

	public void setXpyjbgtjBzDay(Integer xpyjbgtjBzDay) {
		this.xpyjbgtjBzDay = xpyjbgtjBzDay;
	}

	public Integer getXpyjbgtjSjDay() {
		return xpyjbgtjSjDay;
	}

	public void setXpyjbgtjSjDay(Integer xpyjbgtjSjDay) {
		this.xpyjbgtjSjDay = xpyjbgtjSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXpyjbgsptgDate() {
		return xpyjbgsptgDate;
	}

	public void setXpyjbgsptgDate(Date xpyjbgsptgDate) {
		this.xpyjbgsptgDate = xpyjbgsptgDate;
	}

	public Integer getXpyjbgsptgStatus() {
		return xpyjbgsptgStatus;
	}

	public void setXpyjbgsptgStatus(Integer xpyjbgsptgStatus) {
		this.xpyjbgsptgStatus = xpyjbgsptgStatus;
	}

	public Integer getXpyjbgsptgBzDay() {
		return xpyjbgsptgBzDay;
	}

	public void setXpyjbgsptgBzDay(Integer xpyjbgsptgBzDay) {
		this.xpyjbgsptgBzDay = xpyjbgsptgBzDay;
	}

	public Integer getXpyjbgsptgSjDay() {
		return xpyjbgsptgSjDay;
	}

	public void setXpyjbgsptgSjDay(Integer xpyjbgsptgSjDay) {
		this.xpyjbgsptgSjDay = xpyjbgsptgSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getZsjsqsptgDate() {
		return zsjsqsptgDate;
	}

	public void setZsjsqsptgDate(Date zsjsqsptgDate) {
		this.zsjsqsptgDate = zsjsqsptgDate;
	}

	public Integer getZsjsqsptgStatus() {
		return zsjsqsptgStatus;
	}

	public void setZsjsqsptgStatus(Integer zsjsqsptgStatus) {
		this.zsjsqsptgStatus = zsjsqsptgStatus;
	}

	public Integer getZsjsqsptgBzDay() {
		return zsjsqsptgBzDay;
	}

	public void setZsjsqsptgBzDay(Integer zsjsqsptgBzDay) {
		this.zsjsqsptgBzDay = zsjsqsptgBzDay;
	}

	public Integer getZsjsqsptgSjDay() {
		return zsjsqsptgSjDay;
	}

	public void setZsjsqsptgSjDay(Integer zsjsqsptgSjDay) {
		this.zsjsqsptgSjDay = zsjsqsptgSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBzsjsqDate() {
		return bzsjsqDate;
	}

	public void setBzsjsqDate(Date bzsjsqDate) {
		this.bzsjsqDate = bzsjsqDate;
	}

	public Integer getBzsjsqStatus() {
		return bzsjsqStatus;
	}

	public void setBzsjsqStatus(Integer bzsjsqStatus) {
		this.bzsjsqStatus = bzsjsqStatus;
	}

	public Integer getBzsjsqBzDay() {
		return bzsjsqBzDay;
	}

	public void setBzsjsqBzDay(Integer bzsjsqBzDay) {
		this.bzsjsqBzDay = bzsjsqBzDay;
	}

	public Integer getBzsjsqSjDay() {
		return bzsjsqSjDay;
	}

	public void setBzsjsqSjDay(Integer bzsjsqSjDay) {
		this.bzsjsqSjDay = bzsjsqSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBzsjsqwcDate() {
		return bzsjsqwcDate;
	}

	public void setBzsjsqwcDate(Date bzsjsqwcDate) {
		this.bzsjsqwcDate = bzsjsqwcDate;
	}

	public Integer getBzsjsqwcStatus() {
		return bzsjsqwcStatus;
	}

	public void setBzsjsqwcStatus(Integer bzsjsqwcStatus) {
		this.bzsjsqwcStatus = bzsjsqwcStatus;
	}

	public Integer getBzsjsqwcBzDay() {
		return bzsjsqwcBzDay;
	}

	public void setBzsjsqwcBzDay(Integer bzsjsqwcBzDay) {
		this.bzsjsqwcBzDay = bzsjsqwcBzDay;
	}

	public Integer getBzsjsqwcSjDay() {
		return bzsjsqwcSjDay;
	}

	public void setBzsjsqwcSjDay(Integer bzsjsqwcSjDay) {
		this.bzsjsqwcSjDay = bzsjsqwcSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBzsjcgqrDate() {
		return bzsjcgqrDate;
	}

	public void setBzsjcgqrDate(Date bzsjcgqrDate) {
		this.bzsjcgqrDate = bzsjcgqrDate;
	}

	public Integer getBzsjcgqrStatus() {
		return bzsjcgqrStatus;
	}

	public void setBzsjcgqrStatus(Integer bzsjcgqrStatus) {
		this.bzsjcgqrStatus = bzsjcgqrStatus;
	}

	public Integer getBzsjcgqrBzDay() {
		return bzsjcgqrBzDay;
	}

	public void setBzsjcgqrBzDay(Integer bzsjcgqrBzDay) {
		this.bzsjcgqrBzDay = bzsjcgqrBzDay;
	}

	public Integer getBzsjcgqrSjDay() {
		return bzsjcgqrSjDay;
	}

	public void setBzsjcgqrSjDay(Integer bzsjcgqrSjDay) {
		this.bzsjcgqrSjDay = bzsjcgqrSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getBzsjwgtgDate() {
		return bzsjwgtgDate;
	}

	public void setBzsjwgtgDate(Date bzsjwgtgDate) {
		this.bzsjwgtgDate = bzsjwgtgDate;
	}

	public Integer getBzsjwgtgStatus() {
		return bzsjwgtgStatus;
	}

	public void setBzsjwgtgStatus(Integer bzsjwgtgStatus) {
		this.bzsjwgtgStatus = bzsjwgtgStatus;
	}

	public Integer getJlbzsjcgqrBzDay() {
		return jlbzsjcgqrBzDay;
	}

	public void setJlbzsjcgqrBzDay(Integer jlbzsjcgqrBzDay) {
		this.jlbzsjcgqrBzDay = jlbzsjcgqrBzDay;
	}

	public Integer getJlbzsjcgqrSjDay() {
		return jlbzsjcgqrSjDay;
	}

	public void setJlbzsjcgqrSjDay(Integer jlbzsjcgqrSjDay) {
		this.jlbzsjcgqrSjDay = jlbzsjcgqrSjDay;
	}

	public Integer getJlwlzsjsqsptgBzDay() {
		return jlwlzsjsqsptgBzDay;
	}

	public void setJlwlzsjsqsptgBzDay(Integer jlwlzsjsqsptgBzDay) {
		this.jlwlzsjsqsptgBzDay = jlwlzsjsqsptgBzDay;
	}

	public Integer getJlwlzsjsqsptgSjDay() {
		return jlwlzsjsqsptgSjDay;
	}

	public void setJlwlzsjsqsptgSjDay(Integer jlwlzsjsqsptgSjDay) {
		this.jlwlzsjsqsptgSjDay = jlwlzsjsqsptgSjDay;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getXpffDate() {
		return xpffDate;
	}

	public void setXpffDate(Date xpffDate) {
		this.xpffDate = xpffDate;
	}

	public Integer getXpffStatus() {
		return xpffStatus;
	}

	public void setXpffStatus(Integer xpffStatus) {
		this.xpffStatus = xpffStatus;
	}

	public Integer getXpffBzDay() {
		return xpffBzDay;
	}

	public void setXpffBzDay(Integer xpffBzDay) {
		this.xpffBzDay = xpffBzDay;
	}

	public Integer getXpffSjDay() {
		return xpffSjDay;
	}

	public void setXpffSjDay(Integer xpffSjDay) {
		this.xpffSjDay = xpffSjDay;
	}

	public Integer getBzDay() {
		return bzDay;
	}

	public void setBzDay(Integer bzDay) {
		this.bzDay = bzDay;
	}

	public Integer getSjDay() {
		return sjDay;
	}

	public void setSjDay(Integer sjDay) {
		this.sjDay = sjDay;
	}

	public Integer getSctgBzDay() {
		return sctgBzDay;
	}

	public void setSctgBzDay(Integer sctgBzDay) {
		this.sctgBzDay = sctgBzDay;
	}

	public Integer getSctgSjDay() {
		return sctgSjDay;
	}

	public void setSctgSjDay(Integer sctgSjDay) {
		this.sctgSjDay = sctgSjDay;
	}

	public String getOaApplicationName() {
		return oaApplicationName;
	}

	public void setOaApplicationName(String oaApplicationName) {
		this.oaApplicationName = oaApplicationName;
	}

	@JSON(format = "yyyy-MM-dd")
	public Date getOaApplicationDate() {
		return oaApplicationDate;
	}

	public void setOaApplicationDate(Date oaApplicationDate) {
		this.oaApplicationDate = oaApplicationDate;
	}

}