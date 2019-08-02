package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.math.BigDecimal;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class QuotedForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486118879955261432L;

	private String lastQuotedRank;// 最后一次报价排名
	private String supplierCode;//

	private BigDecimal lastProductionCycle;// 最后一次报价生成周期

	private List<QuotedDetailForm> quotedDetailFormList;

	public String getLastQuotedRank() {
		return lastQuotedRank;
	}

	public void setLastQuotedRank(String lastQuotedRank) {
		this.lastQuotedRank = lastQuotedRank;
	}

	

	public BigDecimal getLastProductionCycle() {
		return lastProductionCycle;
	}

	public void setLastProductionCycle(BigDecimal lastProductionCycle) {
		this.lastProductionCycle = lastProductionCycle;
	}

	public List<QuotedDetailForm> getQuotedDetailFormList() {
		return quotedDetailFormList;
	}

	public void setQuotedDetailFormList(List<QuotedDetailForm> quotedDetailFormList) {
		this.quotedDetailFormList = quotedDetailFormList;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

}