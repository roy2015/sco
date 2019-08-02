package com.powere2e.sco.model.peripheral.sap;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 区域信息实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月19日
 */
public class Region extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8179668591529767261L;
	private String regionCode;//
	private String regionName;//
	private Integer directStoreQuantity;//
	private Integer joinStoreQuantity;//
	private Date syncDate;//

	List<Region> list;
	// 获取
	public String getRegionCode() {
		return regionCode;
	}
	// 设置
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	
	// 获取
	public String getRegionName() {
		return regionName;
	}
	// 设置
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	
	// 获取
	public Integer getDirectStoreQuantity() {
		return directStoreQuantity;
	}
	// 设置
	public void setDirectStoreQuantity(Integer directStoreQuantity) {
		this.directStoreQuantity = directStoreQuantity;
	}
	
	// 获取
	public Integer getJoinStoreQuantity() {
		return joinStoreQuantity;
	}
	// 设置
	public void setJoinStoreQuantity(Integer joinStoreQuantity) {
		this.joinStoreQuantity = joinStoreQuantity;
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
	public List<Region> getList() {
		return list;
	}
	public void setList(List<Region> list) {
		this.list = list;
	}
	
	
}