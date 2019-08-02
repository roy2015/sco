package com.powere2e.sco.model.merchandiseintention;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 意向供应商实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月19日
 */
public class MerchandiseIntentionSupplier extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7698938627447305075L;
	private String intentionSupplierCode;// 意向供应商编号
	private String intentionSupplierName;// 意向供应商名称
	private Date created;// 创建时间
	private String createby;// 创建人
	private Date updated;// 修改时间
	private String updateby;// 修改人
	private String supplierAddress;// 供应商地址
	private String id;// code
	private String text;// value

	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	// 获取
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	// 设置
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
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

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}