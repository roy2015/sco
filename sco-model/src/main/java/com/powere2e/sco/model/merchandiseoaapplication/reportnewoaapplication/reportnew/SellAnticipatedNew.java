package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew;
import com.powere2e.frame.server.model.AppModel;
/**
 * 商品销售预计(新品引进)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月27日
 */
public class SellAnticipatedNew extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1305263349288271094L;
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