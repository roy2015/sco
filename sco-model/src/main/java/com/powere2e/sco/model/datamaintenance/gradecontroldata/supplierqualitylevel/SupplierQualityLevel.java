package com.powere2e.sco.model.datamaintenance.gradecontroldata.supplierqualitylevel;
import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;
/**
 * 供应商质量星级实体类
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月10日
 */
public class SupplierQualityLevel extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3051922098523633512L;
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private Integer qualityLevelYear;//质量星级年度
	private BigDecimal qualityLevel;//质量星级
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	
	
	public SupplierQualityLevel() {
	}
	
	
	public SupplierQualityLevel(String supplierCode,Integer qualityLevelYear, BigDecimal qualityLevel ) {
		this.supplierCode = supplierCode;
		this.qualityLevel = qualityLevel;
		this.qualityLevelYear = qualityLevelYear;
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
	public String getSupplierCode() {
		return supplierCode;
	}
	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	
	// 获取
	public Integer getQualityLevelYear() {
		return qualityLevelYear;
	}
	// 设置
	public void setQualityLevelYear(Integer qualityLevelYear) {
		this.qualityLevelYear = qualityLevelYear;
	}
	
	// 获取
	public BigDecimal getQualityLevel() {
		return qualityLevel;
	}
	// 设置
	public void setQualityLevel(BigDecimal qualityLevel) {
		this.qualityLevel = qualityLevel;
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
}