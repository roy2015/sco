package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class QuotedTotalForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5486118879955261432L;

	private String lastQuotedRank;// 最后一次报价排名

	private List<QuotedDetailTotalForm> quotedDetailTotalFormList;

	private List<QuotedDetailTotalForm> quotedDetailTotalFormListHs;

	public String getLastQuotedRank() {
		return lastQuotedRank;
	}

	public void setLastQuotedRank(String lastQuotedRank) {
		this.lastQuotedRank = lastQuotedRank;
	}

	public List<QuotedDetailTotalForm> getQuotedDetailTotalFormList() {
		return quotedDetailTotalFormList;
	}

	public void setQuotedDetailTotalFormList(List<QuotedDetailTotalForm> quotedDetailTotalFormList) {
		this.quotedDetailTotalFormList = quotedDetailTotalFormList;
	}

	public List<QuotedDetailTotalForm> getQuotedDetailTotalFormListHs() {
		return quotedDetailTotalFormListHs;
	}

	public void setQuotedDetailTotalFormListHs(List<QuotedDetailTotalForm> quotedDetailTotalFormListHs) {
		this.quotedDetailTotalFormListHs = quotedDetailTotalFormListHs;
	}

}