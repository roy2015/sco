package com.powere2e.sco.model.datamaintenance.gradecontroldata.suppliercomplaints;
import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 供应商年度千万元客诉实体类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月13日
 */
public class SupplierComplaints extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2602889457624824204L;
	private String updateby;//
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private BigDecimal complaintsYear;//客诉年限
	private BigDecimal complaintsCount;//客诉数量
	private Date created;//
	private String createby;//
	private Date updated;//

	
	
	
	
	public SupplierComplaints() {
	}
	public SupplierComplaints(String supplierCode, BigDecimal complaintsYear, BigDecimal complaintsCount) {
		this.supplierCode = supplierCode;
		this.complaintsYear = complaintsYear;
		this.complaintsCount = complaintsCount;
	}
	// 获取
	public String getUpdateby() {
		return updateby;
	}
	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
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
	public String getSupplierName() {
		return supplierName;
	}
	// 设置
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	// 获取
	public BigDecimal getComplaintsYear() {
		return complaintsYear;
	}
	// 设置
	public void setComplaintsYear(BigDecimal complaintsYear) {
		this.complaintsYear = complaintsYear;
	}
	
	// 获取
	public BigDecimal getComplaintsCount() {
		return complaintsCount;
	}
	// 设置
	public void setComplaintsCount(BigDecimal complaintsCount) {
		this.complaintsCount = complaintsCount;
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
}