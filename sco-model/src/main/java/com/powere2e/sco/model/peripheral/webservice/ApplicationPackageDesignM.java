package com.powere2e.sco.model.peripheral.webservice;
import com.powere2e.frame.server.model.AppModel;
/**
 * 商品包装设计申请单(商品)实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationPackageDesignM extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2696108427905391575L;
	private String applicationPdCode;//
	private String applicationCode;//
	private String merchandiseCode;//
	private String supplierCode;//

	
	// 获取
	public String getApplicationPdCode() {
		return applicationPdCode;
	}
	// 设置
	public void setApplicationPdCode(String applicationPdCode) {
		this.applicationPdCode = applicationPdCode;
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
}