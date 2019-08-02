package com.powere2e.sco.model.merchandiseintention;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 试吃反馈实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月7日
 */
public class ForetasteFeedback extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -495745137435478938L;
	private String feedbackCode;//
	private String intentionCode;//
	private String intentionSupplierCode;//
	private String intentionSupplierName;//
	private Date foretasteDate;//
	private Integer foretasteNumber;//
	private BigDecimal foretasteFull;//
	private BigDecimal foretasteGrade;//
	private String evaluate;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	// 获取
	public String getFeedbackCode() {
		return feedbackCode;
	}

	// 设置
	public void setFeedbackCode(String feedbackCode) {
		this.feedbackCode = feedbackCode;
	}

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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getForetasteDate() {
		return foretasteDate;
	}

	// 设置
	public void setForetasteDate(Date foretasteDate) {
		this.foretasteDate = foretasteDate;
	}

	// 获取
	public Integer getForetasteNumber() {
		return foretasteNumber;
	}

	// 设置
	public void setForetasteNumber(Integer foretasteNumber) {
		this.foretasteNumber = foretasteNumber;
	}

	public BigDecimal getForetasteFull() {
		return foretasteFull;
	}

	public void setForetasteFull(BigDecimal foretasteFull) {
		this.foretasteFull = foretasteFull;
	}

	public BigDecimal getForetasteGrade() {
		return foretasteGrade;
	}

	public void setForetasteGrade(BigDecimal foretasteGrade) {
		this.foretasteGrade = foretasteGrade;
	}

	// 获取
	public String getEvaluate() {
		return evaluate;
	}

	// 设置
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
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