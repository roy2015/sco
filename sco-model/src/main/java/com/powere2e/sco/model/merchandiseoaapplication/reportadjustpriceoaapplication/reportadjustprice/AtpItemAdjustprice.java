package com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 商品签量情况-明细(调价)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class AtpItemAdjustprice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3030188072145013057L;
	private String reportCode;//
	private Date atpQuantityDate;//
	private Integer atpQuantityCount;//
	private String remarks;//

	
	// 获取
	public String getReportCode() {
		return reportCode;
	}
	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getAtpQuantityDate() {
		return atpQuantityDate;
	}
	// 设置
	public void setAtpQuantityDate(Date atpQuantityDate) {
		this.atpQuantityDate = atpQuantityDate;
	}
	
	// 获取
	public Integer getAtpQuantityCount() {
		return atpQuantityCount;
	}
	// 设置
	public void setAtpQuantityCount(Integer atpQuantityCount) {
		this.atpQuantityCount = atpQuantityCount;
	}
	
	// 获取
	public String getRemarks() {
		return remarks;
	}
	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
}