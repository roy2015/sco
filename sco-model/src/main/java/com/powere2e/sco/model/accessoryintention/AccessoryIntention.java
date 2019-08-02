package com.powere2e.sco.model.accessoryintention;
import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;
/**
 * 辅料意向品实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月10日
 */
public class AccessoryIntention extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 260514644345240297L;
	private String intentionCode;//
	private String intentionName;//
	private String centreTypeCode;//
	private String smallTypeCode;//
	private String detailTypeCode;//
	private String fineTypeCodes;//
	private String centreTypeName;//
	private String smallTypeName;//
	private String detailTypeName;//
	private String fineTypeName;//soaNo
	private String soaNo;//
	private String enquiryCount;
	private String supplierCount;
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private Date createdEnd;//查询报表日期时的结束时间
	
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
	
	
	
	public String getFineTypeCodes() {
		return fineTypeCodes;
	}
	public void setFineTypeCodes(String fineTypeCodes) {
		this.fineTypeCodes = fineTypeCodes;
	}
	// 获取
//	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	public String getEnquiryCount() {
		return enquiryCount;
	}
	public void setEnquiryCount(String enquiryCount) {
		this.enquiryCount = enquiryCount;
	}
	public String getSupplierCount() {
		return supplierCount;
	}
	public void setSupplierCount(String supplierCount) {
		this.supplierCount = supplierCount;
	}
	public String getSoaNo() {
		return soaNo;
	}
	public void setSoaNo(String soaNo) {
		this.soaNo = soaNo;
	}
	public Date getCreatedEnd() {
		return createdEnd;
	}
	public void setCreatedEnd(Date createdEnd) {
		this.createdEnd = createdEnd;
	}
	
}