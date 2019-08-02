package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class QuotedDetailTotalForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7479703477063173647L;

	private BigDecimal quotedTotal;// 报价总计

	public BigDecimal getQuotedTotal() {
		return quotedTotal;
	}

	public void setQuotedTotal(BigDecimal quotedTotal) {
		this.quotedTotal = quotedTotal;
	}

}