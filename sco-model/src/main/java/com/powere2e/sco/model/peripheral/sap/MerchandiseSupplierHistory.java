package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商历史物料号实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class MerchandiseSupplierHistory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8708035978987118873L;
	private String supplierCode;//
	private String wlReplace;//
	private Date syncDate;//

	List<MerchandiseSupplierHistory> list;

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	// 获取
	public String getWlReplace() {
		return wlReplace;
	}

	// 设置
	public void setWlReplace(String wlReplace) {
		this.wlReplace = wlReplace;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	// 设置
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandiseSupplierHistory> getList() {
		return list;
	}

	public void setList(List<MerchandiseSupplierHistory> list) {
		this.list = list;
	}

}