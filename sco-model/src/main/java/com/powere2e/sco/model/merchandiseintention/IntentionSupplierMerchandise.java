package com.powere2e.sco.model.merchandiseintention;

import java.util.ArrayList;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 意向品供应商关联表实体类,一个意向品对应多个意向供应商
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月19日
 */
public class IntentionSupplierMerchandise extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -9189342684940330717L;

	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 意向品供应商编号
	private String intentionSupplierName;// 意向品供应商名称
	private String merchandiseCode;// 商品编号
	private String supplierCode;// 供应商编号
	private String supplierAddress;// 供应商地址
	private String isForetastePass;// 是否试吃通过
	private Date foretastePassDate;// 试吃通过日期
	private Date created;// 创建时间
	private String createby;// 创建人
	private Date updated;// 修改时间
	private String updateby;// 修改人

	// 意向供应商列表
	private ArrayList<MerchandiseIntentionSupplier> intentionSupplierList = new ArrayList<MerchandiseIntentionSupplier>();

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
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
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public ArrayList<MerchandiseIntentionSupplier> getIntentionSupplierList() {
		return intentionSupplierList;
	}

	public void setIntentionSupplierList(ArrayList<MerchandiseIntentionSupplier> intentionSupplierList) {
		this.intentionSupplierList = intentionSupplierList;
	}

	public String getSupplierAddress() {
		return supplierAddress;
	}

	public void setSupplierAddress(String supplierAddress) {
		this.supplierAddress = supplierAddress;
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
	
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getIsForetastePass() {
		return isForetastePass;
	}

	public void setIsForetastePass(String isForetastePass) {
		this.isForetastePass = isForetastePass;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getForetastePassDate() {
		return foretastePassDate;
	}

	public void setForetastePassDate(Date foretastePassDate) {
		this.foretastePassDate = foretastePassDate;
	}

}