package com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;
import com.powere2e.frame.server.model.AppModel;
/**
 * 商品原料情况(调价)实体类
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class MerchandiseMaterial extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2295163623071590458L;
	private String reportCode;//
	private String reportType;//
	private String materialName;//
	private String materialSite;//
	private String materialDate;//
	private String materialCount;//
	private String sellDate;//
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
	public String getReportType() {
		return reportType;
	}
	// 设置
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	
	// 获取
	public String getMaterialName() {
		return materialName;
	}
	// 设置
	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}
	
	// 获取
	public String getMaterialSite() {
		return materialSite;
	}
	// 设置
	public void setMaterialSite(String materialSite) {
		this.materialSite = materialSite;
	}
	
	// 获取
	public String getMaterialDate() {
		return materialDate;
	}
	// 设置
	public void setMaterialDate(String materialDate) {
		this.materialDate = materialDate;
	}
	
	// 获取
	public String getMaterialCount() {
		return materialCount;
	}
	// 设置
	public void setMaterialCount(String materialCount) {
		this.materialCount = materialCount;
	}
	
	// 获取
	public String getSellDate() {
		return sellDate;
	}
	// 设置
	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
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