package com.powere2e.sco.model.merchandiseoaapplication.fastadjustpriceoaapplication;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 申请报告(快速调价)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class ApplicationFastadjustprice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5095071767538945059L;
	private String reportCode;// 报告编号
	private String applicationCode;// 申请单号
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String reportFileName;// 上传文件名称
	private String path;// 上传路径
	private Date uploadDate;// 上传日期
	private Date created;// 创建日期
	private String createby;//
	private Date updated;//
	private String updateby;//

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
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
	public String getReportFileName() {
		return reportFileName;
	}

	// 设置
	public void setReportFileName(String reportFileName) {
		this.reportFileName = reportFileName;
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
	public Date getUploadDate() {
		return uploadDate;
	}

	// 设置
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

}