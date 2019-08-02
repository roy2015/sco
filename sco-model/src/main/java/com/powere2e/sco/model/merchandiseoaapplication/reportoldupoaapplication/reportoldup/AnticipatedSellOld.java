package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup;
import com.powere2e.frame.server.model.AppModel;
/**
 * 同类商品市场零售价(老品新上)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class AnticipatedSellOld extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6283814156015340800L;
	private String reportCode;//
	private String sellRegion;//
	private Integer sellStoreCount;//

	
	// 获取
	public String getReportCode() {
		return reportCode;
	}
	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}
	
	// 获取
	public String getSellRegion() {
		return sellRegion;
	}
	// 设置
	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}
	
	// 获取
	public Integer getSellStoreCount() {
		return sellStoreCount;
	}
	// 设置
	public void setSellStoreCount(Integer sellStoreCount) {
		this.sellStoreCount = sellStoreCount;
	}
}