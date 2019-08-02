package com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品签量情况-总实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class AtpTotalAdjustprice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7757974610297948606L;
	private String reportCode;//
	private Date qlDate;// 签量日期
	private BigDecimal atpQuantitySum;// 签量数量
	private BigDecimal atpQuantitySumAccomplish;// 已完成量
	private BigDecimal atpQuantitySumUnfinished;// 未完成量
	private String remarks;// 备注

	// 获取
	public String getReportCode() {
		return reportCode;
	}

	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQlDate() {
		return qlDate;
	}

	public void setQlDate(Date qlDate) {
		this.qlDate = qlDate;
	}

	public BigDecimal getAtpQuantitySum() {
		return atpQuantitySum;
	}

	public void setAtpQuantitySum(BigDecimal atpQuantitySum) {
		this.atpQuantitySum = atpQuantitySum;
	}

	public BigDecimal getAtpQuantitySumAccomplish() {
		return atpQuantitySumAccomplish;
	}

	public void setAtpQuantitySumAccomplish(BigDecimal atpQuantitySumAccomplish) {
		this.atpQuantitySumAccomplish = atpQuantitySumAccomplish;
	}

	public BigDecimal getAtpQuantitySumUnfinished() {
		return atpQuantitySumUnfinished;
	}

	public void setAtpQuantitySumUnfinished(BigDecimal atpQuantitySumUnfinished) {
		this.atpQuantitySumUnfinished = atpQuantitySumUnfinished;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}