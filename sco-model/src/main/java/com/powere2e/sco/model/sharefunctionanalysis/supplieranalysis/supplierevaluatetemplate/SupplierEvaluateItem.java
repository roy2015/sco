package com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Supplier;

public class SupplierEvaluateItem extends Supplier{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6382801598677026124L;
	private String templateCode;//考评模板编号
	private String evaluateItemName;//考评项名称
	private String evaluateItemCode;//考评项编号
	private Double weight;//评分项权重
	private double score;//分值
	private String departments;//部门
	private String according;//评分依据
	private String standard;//评分标准
	private Date created;//创建时间
	private String createdby;//创建人
	private Date updated;//更新时间
	private String updatedby;//更新时间
	public String getTemplateCode() {
		return templateCode;
	}
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}
	public String getEvaluateItemName() {
		return evaluateItemName;
	}
	public void setEvaluateItemName(String evaluateItemName) {
		this.evaluateItemName = evaluateItemName;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double d) {
		this.score = d;
	}
	public String getDepartments() {
		return departments;
	}
	public void setDepartments(String departments) {
		this.departments = departments;
	}
	public String getAccording() {
		return according;
	}
	public void setAccording(String according) {
		this.according = according;
	}
	public String getStandard() {
		return standard;
	}
	public void setStandard(String standard) {
		this.standard = standard;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}
	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	public String getCreatedby() {
		return createdby;
	}
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}
	public String getUpdatedby() {
		return updatedby;
	}
	public void setUpdatedby(String updatedby) {
		this.updatedby = updatedby;
	}
	public String getEvaluateItemCode() {
		return evaluateItemCode;
	}
	public void setEvaluateItemCode(String evaluateItemCode) {
		this.evaluateItemCode = evaluateItemCode;
	}
	//获取权重
	public Double getWeight() {
		return weight;
	}
	//设置权重
	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
