package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class QuotedDetailForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7479703477063173647L;

	private Date quotedDate;// 报价日期

	private BigDecimal unitPrice;// 单价

	private BigDecimal quotedTotal;// 报价总计

	@JSON(format = "yyyy-MM-dd")
	public Date getQuotedDate() {
		return quotedDate;
	}

	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BigDecimal getQuotedTotal() {
		return quotedTotal;
	}

	public void setQuotedTotal(BigDecimal quotedTotal) {
		this.quotedTotal = quotedTotal;
	}

}