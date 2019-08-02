package com.powere2e.sco.model.accessoryintention;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 辅料打样实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月8日
 */
public class AccessoryProofing extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5535360491935669927L;
	private String proofingCode;//
	private String enquiryCode;//
	private String quotedCode;//
	private String intentionCode;//
	private String intentionSupplierCode;//
	private String supplierCode;//
	private String intentionSupplierName;//
	private String supplierName;//
	private String path;//
	private String proofingType;//打样类型
	private Date askProofingDate;//要求完成日期
	private Date proofingDate;//打样完成日期
	private String proofingCycle;//打样周期
	private String proofingContent;//打样内容
	private String proofingEvaluate;//打样评价
	private String proofingStatus;//打样状态
	private String proofingYorS;//是否打样
	private String havePicture;//是否打样
	private String remarks;//备注
	private Date quotedDate;//
	private Date quotedCreateDate;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	// 获取
	public String getProofingCode() {
		return proofingCode;
	}
	// 设置
	public void setProofingCode(String proofingCode) {
		this.proofingCode = proofingCode;
	}
	
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	public String getQuotedCode() {
		return quotedCode;
	}
	// 设置
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
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
	public String getSupplierCode() {
		return supplierCode;
	}
	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	// 获取
	public String getProofingType() {
		return proofingType;
	}
	// 设置
	public void setProofingType(String proofingType) {
		this.proofingType = proofingType;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getAskProofingDate() {
		return askProofingDate;
	}
	// 设置
	public void setAskProofingDate(Date askProofingDate) {
		this.askProofingDate = askProofingDate;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getProofingDate() {
		return proofingDate;
	}
	// 设置
	public void setProofingDate(Date proofingDate) {
		this.proofingDate = proofingDate;
	}
	
	
	
	
	public String getProofingCycle() {
		return proofingCycle;
	}
	public void setProofingCycle(String proofingCycle) {
		this.proofingCycle = proofingCycle;
	}
	// 获取
	public String getProofingContent() {
		return proofingContent;
	}
	// 设置
	public void setProofingContent(String proofingContent) {
		this.proofingContent = proofingContent;
	}
	
	// 获取
	public String getProofingEvaluate() {
		return proofingEvaluate;
	}
	// 设置
	public void setProofingEvaluate(String proofingEvaluate) {
		this.proofingEvaluate = proofingEvaluate;
	}
	
	// 获取
	public String getProofingStatus() {
		return proofingStatus;
	}
	// 设置
	public void setProofingStatus(String proofingStatus) {
		this.proofingStatus = proofingStatus;
	}
	
	// 获取
	public String getRemarks() {
		return remarks;
	}
	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
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
	@JSON(format="yyyy-MM-dd")
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
	@JSON(format="yyyy-MM-dd")
	public Date getQuotedDate() {
		return quotedDate;
	}
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}
	@JSON(format="yyyy-MM-dd")
	public Date getQuotedCreateDate() {
		return quotedCreateDate;
	}
	public void setQuotedCreateDate(Date quotedCreateDate) {
		this.quotedCreateDate = quotedCreateDate;
	}
	public String getProofingYorS() {
		return proofingYorS;
	}
	public void setProofingYorS(String proofingYorS) {
		this.proofingYorS = proofingYorS;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getHavePicture() {
		return havePicture;
	}
	public void setHavePicture(String havePicture) {
		this.havePicture = havePicture;
	}
	
}