package com.powere2e.sco.model.peripheral.sap;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 仓库实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class Warehouse extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5460285059988926628L;
	private String warehouseCode;//
	private String warehouseName;//
	private String regionCode;//
	private Date syncDate;//

	List<Warehouse> list;
	// 获取
	public String getWarehouseCode() {
		return warehouseCode;
	}
	// 设置
	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}
	
	// 获取
	public String getWarehouseName() {
		return warehouseName;
	}
	// 设置
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
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
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}
	// 设置
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}
	public List<Warehouse> getList() {
		return list;
	}
	public void setList(List<Warehouse> list) {
		this.list = list;
	}
	
	
}