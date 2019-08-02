package com.powere2e.sco.model.peripheral.bw;

import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品进货权限实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandiseJhPermission extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6896695277610934028L;
	private String merchandiseCode;//
	private String supplierCode;//
	private String regionCode;//
	private String storeType;//
	private Integer permissionStoreQuantity;//
	private Integer aStoreQuantity;//
	private Integer bStoreQuantity;//
	private Integer cStoreQuantity;//
	private Integer dStoreQuantity;//
	private Date permissionDate;//
	private Date syncDate;//

	private List<MerchandiseJhPermission> list;

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
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
	public String getRegionCode() {
		return regionCode;
	}

	// 设置
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	// 获取
	public String getStoreType() {
		return storeType;
	}

	// 设置
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	// 获取
	public Integer getPermissionStoreQuantity() {
		return permissionStoreQuantity;
	}

	// 设置
	public void setPermissionStoreQuantity(Integer permissionStoreQuantity) {
		this.permissionStoreQuantity = permissionStoreQuantity;
	}

	// 获取
	public Integer getAStoreQuantity() {
		return aStoreQuantity;
	}

	// 设置
	public void setAStoreQuantity(Integer aStoreQuantity) {
		this.aStoreQuantity = aStoreQuantity;
	}

	// 获取
	public Integer getBStoreQuantity() {
		return bStoreQuantity;
	}

	// 设置
	public void setBStoreQuantity(Integer bStoreQuantity) {
		this.bStoreQuantity = bStoreQuantity;
	}

	// 获取
	public Integer getCStoreQuantity() {
		return cStoreQuantity;
	}

	// 设置
	public void setCStoreQuantity(Integer cStoreQuantity) {
		this.cStoreQuantity = cStoreQuantity;
	}

	// 获取
	public Integer getDStoreQuantity() {
		return dStoreQuantity;
	}

	// 设置
	public void setDStoreQuantity(Integer dStoreQuantity) {
		this.dStoreQuantity = dStoreQuantity;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPermissionDate() {
		return permissionDate;
	}

	// 设置
	public void setPermissionDate(Date permissionDate) {
		this.permissionDate = permissionDate;
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

	public List<MerchandiseJhPermission> getList() {
		return list;
	}

	public void setList(List<MerchandiseJhPermission> list) {
		this.list = list;
	}

}