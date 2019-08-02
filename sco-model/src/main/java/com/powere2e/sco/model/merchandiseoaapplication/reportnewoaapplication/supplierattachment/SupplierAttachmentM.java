package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment;

import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;

/**
 * 供应商附件实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月21日
 */
public class SupplierAttachmentM extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 839436000807893826L;
	private String applicationCode;// 申请单号
	private String accountingCode;// 核算表编号
	private String intentionCode;// 意向品编号
	private String intentionName;// 意向品名称
	private String intentionSupplierCode;// 意向供应商编号
	private String intentionSupplierName;// 意向供应商名称
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String attachmentType;// 附件类型
	private String elseAttachmentType;// 附件类型
	private String attachmentName;// 附件名称
	private String typeCode;//核算投料表
	private String path;// 地址
	private String inFrom;// 属于那个oa申请
	private Date created;// 创建时间
	private String createDate;// 格式化创建时间
	private String createby;// 创建人
	private Date updated;// 更新时间
	private String updateDate;// 格式化更新时间
	private String updateby;// 更新人

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getAccountingCode() {
		return accountingCode;
	}

	public void setAccountingCode(String accountingCode) {
		this.accountingCode = accountingCode;
	}

	public String getElseAttachmentType() {
		return elseAttachmentType;
	}

	public void setElseAttachmentType(String elseAttachmentType) {
		this.elseAttachmentType = elseAttachmentType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getInFrom() {
		return inFrom;
	}

	public void setInFrom(String inFrom) {
		this.inFrom = inFrom;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
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
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
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
	public String getAttachmentType() {
		return attachmentType;
	}

	// 设置
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}

	// 获取
	public String getAttachmentName() {
		return attachmentName;
	}

	// 设置
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	// 获取
	public String getPath() {
		return path;
	}

	// 设置
	public void setPath(String path) {
		this.path = path;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
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
}