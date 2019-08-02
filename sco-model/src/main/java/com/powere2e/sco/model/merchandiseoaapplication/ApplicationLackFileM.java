package com.powere2e.sco.model.merchandiseoaapplication;

import com.powere2e.frame.server.model.AppModel;

/**
 * 缺失文件说明实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月14日
 */
public class ApplicationLackFileM extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1487246751858571304L;
	private String applicationCode;// 申请单号
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String lackFileName;// 缺少文件名称
	private String lackFileVersions;// 缺少文件版本
	private String lackFileCause;// 缺少文件原因

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
	public String getLackFileName() {
		return lackFileName;
	}

	// 设置
	public void setLackFileName(String lackFileName) {
		this.lackFileName = lackFileName;
	}

	// 获取
	public String getLackFileVersions() {
		return lackFileVersions;
	}

	// 设置
	public void setLackFileVersions(String lackFileVersions) {
		this.lackFileVersions = lackFileVersions;
	}

	// 获取
	public String getLackFileCause() {
		return lackFileCause;
	}

	// 设置
	public void setLackFileCause(String lackFileCause) {
		this.lackFileCause = lackFileCause;
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

}