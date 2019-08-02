package com.powere2e.sco.model.remind;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

public class RemindMerchandsieQL extends AppModel{

	private static final long serialVersionUID = -8342055815959767809L;
	
	private String qlCode; //签量单号
	private String merchandiseCode; //商品编号
	private String supplierCode; //供应商编号
	private Date qlStartDate; //签量开始日期
	private BigDecimal finishPercent; //签量完成百分比
	private String merchandiseName; //商品名称
	private String supplierName; //供应商名称
	private String configCode; //配置编号
	
	public String getQlCode() {
		return qlCode;
	}
	public void setQlCode(String qlCode) {
		this.qlCode = qlCode;
	}
	public String getMerchandiseCode() {
		return merchandiseCode;
	}
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public Date getQlStartDate() {
		return qlStartDate;
	}
	public void setQlStartDate(Date qlStartDate) {
		this.qlStartDate = qlStartDate;
	}
	public BigDecimal getFinishPercent() {
		return finishPercent;
	}
	public void setFinishPercent(BigDecimal finishPercent) {
		this.finishPercent = finishPercent;
	}
	public String getMerchandiseName() {
		return merchandiseName;
	}
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getConfigCode() {
		return configCode;
	}
	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}
}
