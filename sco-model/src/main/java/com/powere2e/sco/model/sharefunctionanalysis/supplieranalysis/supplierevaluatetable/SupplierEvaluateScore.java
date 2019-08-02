package com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetable;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.sharefunctionanalysis.supplieranalysis.supplierevaluatetemplate.SupplierEvaluateTemplate;

/**
 * 供应商考评表实体类
 * 
 * @author caoliqiang
 * @version 1.0
 * @since 2015年5月12日
 */
public class SupplierEvaluateScore extends SupplierEvaluateTemplate {
	/**
	 * 
	 */
	private static final long serialVersionUID = -218084026841141091L;
	private String evaluateTableCode;// 考评表编号
	private String supplierCode;// 供应商编号
	private String templateCode;// 考评模版编号
	private String evaluateItemCode;// 考核项编号
	private double evaluateItemScore;// 考核项分数
	private Date created;// 创建时间
	private String createby;// 创建人
	private String createbySE;// 创建人
	private Date updated;// 更新时间
	private String updateby;// 更新人
	private BigDecimal scoreCG;// 采购评分
	private BigDecimal scorePK;// 品控评分
	private BigDecimal scoreKC;// 库存评分
	private BigDecimal scorePL;// 品类评分
	private BigDecimal scoreTotal;// 总分
	private Integer scoreRank;// 总分排行

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getCreatebySE() {
		return createbySE;
	}

	public void setCreatebySE(String createbySE) {
		this.createbySE = createbySE;
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
	public String getEvaluateItemCode() {
		return evaluateItemCode;
	}

	// 设置
	public void setEvaluateItemCode(String evaluateItemCode) {
		this.evaluateItemCode = evaluateItemCode;
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

	public String getEvaluateTableCode() {
		return evaluateTableCode;
	}

	public void setEvaluateTableCode(String evaluateTableCode) {
		this.evaluateTableCode = evaluateTableCode;
	}

	public double getEvaluateItemScore() {
		return evaluateItemScore;
	}

	public void setEvaluateItemScore(double evaluateItemScore) {
		this.evaluateItemScore = evaluateItemScore;
	}

	public BigDecimal getScoreCG() {
		return scoreCG;
	}

	public void setScoreCG(BigDecimal scoreCG) {
		this.scoreCG = scoreCG;
	}

	public BigDecimal getScorePK() {
		return scorePK;
	}

	public void setScorePK(BigDecimal scorePK) {
		this.scorePK = scorePK;
	}

	public BigDecimal getScoreKC() {
		return scoreKC;
	}

	public void setScoreKC(BigDecimal scoreKC) {
		this.scoreKC = scoreKC;
	}

	public BigDecimal getScoreTotal() {
		return scoreTotal;
	}

	public void setScoreTotal(BigDecimal scoreTotal) {
		this.scoreTotal = scoreTotal;
	}

	public Integer getScoreRank() {
		return scoreRank;
	}

	public void setScoreRank(Integer scoreRank) {
		this.scoreRank = scoreRank;
	}

	public BigDecimal getScorePL() {
		return scorePL;
	}

	public void setScorePL(BigDecimal scorePL) {
		this.scorePL = scorePL;
	}

}