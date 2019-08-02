package com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.util.Date;

import com.powere2e.frame.server.model.AppModel;
import org.apache.struts2.json.annotations.JSON;

public class SupplierEvaluate extends AppModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2800198264086009233L;
	private String evaluateTableCode;//考评表编号
	private String templateCode;//考评模板编号
	private String supplierCode;//供应商编号
	private Date created;//创建时间
	private String createby;//创建人
	private Date updated;//更新时间
	private String updateby;//更新人
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getEvaluateTableCode() {
		return evaluateTableCode;
	}
	public void setEvaluateTableCode(String evaluateTableCode) {
		this.evaluateTableCode = evaluateTableCode;
	}
	
}
