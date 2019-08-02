package com.powere2e.sco.model.accessoryintention;
import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;
/**
 * 询价单实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiry extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9124553229234441819L;
	private String enquiryCode;//
	private String enquiryName;//
	private String intentionCode;//
	private String quotedCurrency;//
	private String quotedUnits;//
	private String paymentType;//
	private String deliveryType;//
	private String remarks;//
	private String supplierCount;
	private String attachment;//
	private Date lastQuotedDate;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
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
	public String getQuotedCurrency() {
		return quotedCurrency;
	}
	// 设置
	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}
	
	// 获取
	public String getQuotedUnits() {
		return quotedUnits;
	}
	// 设置
	public void setQuotedUnits(String quotedUnits) {
		this.quotedUnits = quotedUnits;
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
	public String getRemarks() {
		return remarks;
	}
	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	// 获取
	public String getAttachment() {
		return attachment;
	}
	// 设置
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getLastQuotedDate() {
		return lastQuotedDate;
	}
	// 设置
	public void setLastQuotedDate(Date lastQuotedDate) {
		this.lastQuotedDate = lastQuotedDate;
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
	public String getSupplierCount() {
		return supplierCount;
	}
	public void setSupplierCount(String supplierCount) {
		this.supplierCount = supplierCount;
	}
	public String getEnquiryName() {
		return enquiryName;
	}
	public void setEnquiryName(String enquiryName) {
		this.enquiryName = enquiryName;
	}
	
}