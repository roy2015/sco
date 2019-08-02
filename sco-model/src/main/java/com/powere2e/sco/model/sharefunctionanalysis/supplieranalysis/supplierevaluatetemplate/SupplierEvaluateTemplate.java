package com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate;

import java.util.Date;
import org.apache.struts2.json.annotations.JSON;

/**
 * 供应商考评表模板实体类
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年4月24日
 */
public class SupplierEvaluateTemplate extends SupplierEvaluateItem {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4358774588508997837L;
	private String templateCode;// 考评模板编号
	private String templateName;// 考评模板名称
	private String templateType;// 考评模板类型
	private Date evaluateStartDate;// 考核开始时间
	private String evaluateDate;// 考核开始时间
	private Date evaluateEndDate;// 考核结束时间
	private Double evaluateFullScore;// 考评表满分
	private Date publishDate;// 发布时间
	private String status;// 状态
	private Date created;// 创建时间
	private String createby;// 创建人
	private Date updated;// 更新时间
	private String updateby;// 更新人

	public String getEvaluateDate() {
		return evaluateDate;
	}

	public void setEvaluateDate(String evaluateDate) {
		this.evaluateDate = evaluateDate;
	}

	// 获取
	public String getTemplateCode() {
		return templateCode;
	}

	// 设置
	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	// 获取
	public String getTemplateName() {
		return templateName;
	}

	// 设置
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	// 获取
	public String getTemplateType() {
		return templateType;
	}

	// 设置
	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	// 获取
	public Date getEvaluateStartDate() {
		return evaluateStartDate;
	}

	// 设置
	public void setEvaluateStartDate(Date evaluateStartDate) {
		this.evaluateStartDate = evaluateStartDate;
	}

	// 获取
	public Date getEvaluateEndDate() {
		return evaluateEndDate;
	}

	// 设置
	public void setEvaluateEndDate(Date evaluateEndDate) {
		this.evaluateEndDate = evaluateEndDate;
	}

	// 获取
	public String getStatus() {
		return status;
	}

	// 设置
	public void setStatus(String status) {
		this.status = status;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
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

	// 获取发布日期
	@JSON(format = "yyyy-MM-dd")
	public Date getPublishDate() {
		return publishDate;
	}

	// 设置发布日期
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	// 获取考评表满分
	public Double getEvaluateFullScore() {
		return evaluateFullScore;
	}

	public void setEvaluateFullScore(Double evaluateFullScore) {
		this.evaluateFullScore = evaluateFullScore;
	}

}