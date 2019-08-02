package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
/**
 * 上下市时间(老品新上)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class UpDownMarketOldup extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 55358172055078462L;
	private String reportCode;//
	private Date upDate;//
	private Date downDate;//

	
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
	public Date getUpDate() {
		return upDate;
	}
	// 设置
	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public Date getDownDate() {
		return downDate;
	}
	// 设置
	public void setDownDate(Date downDate) {
		this.downDate = downDate;
	}
}