package com.powere2e.sco.model.datamaintenance.purchaserdata.materialrolemaintenance;

import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;

/**
 * 原料大类实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月15日
 */
public class MaterialBigType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3192613178182711660L;
	private String materialBigTypeCode;//原料大类编号
	private String materialBigTypeName;//原料大类名称
	private Date created;//创建日期
	private String createds;//格式化创建日期
	private String createby;//创建人
	private Date updated;//更新日期
	private String updateds;//格式化更新日期
	private String updateby;//更新人

	public String getCreateds() {
		return createds;
	}

	public void setCreateds(String createds) {
		this.createds = createds;
	}

	public String getUpdateds() {
		return updateds;
	}

	public void setUpdateds(String updateds) {
		this.updateds = updateds;
	}

	// 获取
	public String getMaterialBigTypeCode() {
		return materialBigTypeCode;
	}

	// 设置
	public void setMaterialBigTypeCode(String materialBigTypeCode) {
		this.materialBigTypeCode = materialBigTypeCode;
	}

	// 获取
	public String getMaterialBigTypeName() {
		return materialBigTypeName;
	}

	// 设置
	public void setMaterialBigTypeName(String materialBigTypeName) {
		this.materialBigTypeName = materialBigTypeName.trim();
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