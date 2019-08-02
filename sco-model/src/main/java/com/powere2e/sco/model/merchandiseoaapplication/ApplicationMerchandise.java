package com.powere2e.sco.model.merchandiseoaapplication;

import com.powere2e.frame.server.model.AppModel;

/**
 * 申请商品/意向品(商品OA)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月22日
 */
public class ApplicationMerchandise extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5935064308602885621L;
	private String applicationCode;//
	private String merchandiseCode;//
	private String supplierCode;//
	private String applicationStatus;// oa申请单状态

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

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}