package com.powere2e.sco.model.peripheral.sap;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 仓位实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class WarehouseSite extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7970038358076183079L;
	private String warehouseSiteCode;//
	private String warehouseSiteName;//
	private String warehouseCode;//
	private String regionCode;//
	private Date syncDate;//

	List<WarehouseSite> list;

	// 获取
	public String getWarehouseSiteCode() {
		return warehouseSiteCode;
	}

	// 设置
	public void setWarehouseSiteCode(String warehouseSiteCode) {
		this.warehouseSiteCode = warehouseSiteCode;
	}

	// 获取
	public String getWarehouseSiteName() {
		return warehouseSiteName;
	}

	// 设置
	public void setWarehouseSiteName(String warehouseSiteName) {
		this.warehouseSiteName = warehouseSiteName;
	}

	// 获取
	public String getWarehouseCode() {
		return warehouseCode;
	}

	// 设置
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	// 获取
	public String getRegionCode() {
		return regionCode;
	}

	// 设置
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
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

	public List<WarehouseSite> getList() {
		return list;
	}

	public void setList(List<WarehouseSite> list) {
		this.list = list;
	}

}