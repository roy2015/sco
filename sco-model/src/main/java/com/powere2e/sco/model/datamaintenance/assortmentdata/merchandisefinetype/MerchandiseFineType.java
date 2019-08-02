package com.powere2e.sco.model.datamaintenance.assortmentdata.merchandisefinetype;

import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;

/**
 * 商品细分类维护实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月14日
 */
public class MerchandiseFineType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7623679617357646163L;
	private String fineTypeCode;//细分类编号
	private String fineTypeName;//细分类名称
	private String centreType;// 中分类
	private String smallType;// 小分类
	private String detailType;// 明细类
	private String centreTypeName;//中分类名称
	private String smallTypeName;//小分类名称
	private String detailTypeName;//明细类名称
	private String detailTypeCode;//明细类编号
	private Date created;//创建日期
	private String createby;//创建人
	private Date updated;//更新日期
	private String updateby;//更新人

	public String getCentreType() {
		return centreType;
	}

	public void setCentreType(String centreType) {
		this.centreType = centreType;
	}

	public String getSmallType() {
		return smallType;
	}

	public void setSmallType(String smallType) {
		this.smallType = smallType;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getCentreTypeName() {
		return centreTypeName;
	}

	public void setCentreTypeName(String centreTypeName) {
		this.centreTypeName = centreTypeName.trim();
	}

	public String getSmallTypeName() {
		return smallTypeName;
	}

	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName.trim();
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName.trim();
	}

	// 获取
	public String getFineTypeCode() {
		return fineTypeCode;
	}

	// 设置
	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	// 获取
	public String getFineTypeName() {
		return fineTypeName;
	}

	// 设置
	public void setFineTypeName(String fineTypeName) {
		this.fineTypeName = fineTypeName.trim();
	}

	// 获取
	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	// 设置
	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}

	// 设置
	public void setCreated(Date created) {
		this.created = created;
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}

	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
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