package com.powere2e.sco.model.accessoryoaapplication;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 大货信息实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月29日
 */
public class DhInfo extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7657838647850927933L;
	private String id;//
	private String applicationCode;//
	private String intentionCode;//
	private String accessoryCode;//
	private String supplierCode;//
	private String fileType;//
	private String fileTypeOther;
	private String fileName;//
	private String path;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private String intentionSupplierName;
	private String supplierName;
	private String intentionName;

	
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
	public String getAccessoryCode() {
		return accessoryCode;
	}
	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
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
	public String getFileType() {
		return fileType;
	}
	// 设置
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	// 获取
	public String getFileName() {
		return fileName;
	}
	// 设置
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
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
	public String getFileTypeOther() {
		return fileTypeOther;
	}
	public void setFileTypeOther(String fileTypeOther) {
		this.fileTypeOther = fileTypeOther;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getIntentionName() {
		return intentionName;
	}
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}
	
}