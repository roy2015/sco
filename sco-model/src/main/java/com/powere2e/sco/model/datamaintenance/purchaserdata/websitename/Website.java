package com.powere2e.sco.model.datamaintenance.purchaserdata.websitename;

import com.powere2e.frame.server.model.AppModel;

/**
 * 公示网站名称维护实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class Website extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6352581568558076054L;
	private String websiteCode;// 公示网站编号
	private String websiteName;// 公示网站名称
	private String websiteUrl;// 公示网站地址
	private String materialName;// 原料名称
	private String materialCode;// 原料编号
	private String priceRegion;// 价格地区
	private String createdate;// 创建日期
	private String createby;// 创建人
	private String updatedate;// 更新日期
	private String updateby;// 更新人

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName.trim();
	}

	public String getPriceRegion() {
		return priceRegion;
	}

	public void setPriceRegion(String priceRegion) {
		this.priceRegion = priceRegion.trim();
	}

	// 获取
	public String getWebsiteCode() {
		return websiteCode;
	}

	// 设置
	public void setWebsiteCode(String websiteCode) {
		this.websiteCode = websiteCode.trim();
	}

	// 获取
	public String getWebsiteName() {
		return websiteName;
	}

	// 设置
	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName.trim();
	}

	// 获取
	public String getWebsiteUrl() {
		return websiteUrl;
	}

	// 设置
	public void setWebsiteUrl(String websiteUrl) {
		this.websiteUrl = websiteUrl.trim();
	}

	// 获取
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	// 获取
	public String getUpdateby() {
		return updateby;
	}

	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
}