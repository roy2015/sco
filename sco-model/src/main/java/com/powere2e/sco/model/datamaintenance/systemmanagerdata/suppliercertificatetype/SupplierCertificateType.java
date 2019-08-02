package com.powere2e.sco.model.datamaintenance.systemmanagerdata.suppliercertificatetype;

import java.util.Date;
import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;

/**
 * 证件名称实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年4月17日
 */
public class SupplierCertificateType extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -866814060056410158L;
	private String certificateTypeCode;//证件编号
	private String certificateTypeName;//证件名称
	private Date created;//创建日期
	private String createby;//创建人
	private Date updated;//更新日期
	private String updateby;//更新人

	// 获取
	public String getCertificateTypeCode() {
		return certificateTypeCode;
	}

	// 设置
	public void setCertificateTypeCode(String certificateTypeCode) {
		this.certificateTypeCode = certificateTypeCode.trim();
	}

	// 获取
	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	// 设置
	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName.trim();
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