package com.powere2e.sco.model.accessoryintention;
import java.math.BigDecimal;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis.QuotedForm;
/**
 * 询价单报价数量实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月27日
 */
public class AccessoryEnquiryQuotedCount extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7652290353887334754L;
	private String enquiryCode;//
	private BigDecimal quotedCount;//
	private BigDecimal purchaseCount;//实际采购数量
	private List<QuotedForm> quotedFormList;
	
	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}
	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}
	
	// 获取
	
	public List<QuotedForm> getQuotedFormList() {
		return quotedFormList;
	}
	public void setQuotedFormList(List<QuotedForm> quotedFormList) {
		this.quotedFormList = quotedFormList;
	}
	public BigDecimal getQuotedCount() {
		return quotedCount;
	}
	public void setQuotedCount(BigDecimal quotedCount) {
		this.quotedCount = quotedCount;
	}
	public BigDecimal getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(BigDecimal purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	
	
	
}