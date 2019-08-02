package com.powere2e.sco.model.peripheral.webservice;
import com.powere2e.frame.server.model.AppModel;
/**
 * 商品包装设计申请单实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年10月21日
 */
public class ApplicationVisitFactoryM extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5638275443849779637L;
	private String applicationVfCode;//
	private String applicationCode;//
	private String merchandiseCode;//
	private String supplierCode;//

	
	// 获取
	public String getApplicationVfCode() {
		return applicationVfCode;
	}
	// 设置
	public void setApplicationVfCode(String applicationVfCode) {
		this.applicationVfCode = applicationVfCode;
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