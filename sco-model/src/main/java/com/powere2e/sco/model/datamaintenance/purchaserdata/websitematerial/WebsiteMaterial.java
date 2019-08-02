package com.powere2e.sco.model.datamaintenance.purchaserdata.websitematerial;

import com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance.Material;


/**
 * 公示网站 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年4月7日
 * @version 1.0
 */
public class WebsiteMaterial extends Material {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2020027191397853518L;
	private String id;//原料价格对应ID
	private String createby;// 录入人
	private String month;//年月日期

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 获取
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

}
