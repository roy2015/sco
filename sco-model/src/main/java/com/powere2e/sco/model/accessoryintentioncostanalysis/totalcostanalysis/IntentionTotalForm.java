package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;
import java.util.List;



import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class IntentionTotalForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3805805152068501738L;
	private String quotedCount;//
	private List<QuotedTotalForm> quotedTotalFormList;
	public String getQuotedCount() {
		return quotedCount;
	}
	public void setQuotedCount(String quotedCount) {
		this.quotedCount = quotedCount;
	}
	public List<QuotedTotalForm> getQuotedTotalFormList() {
		return quotedTotalFormList;
	}
	public void setQuotedTotalFormList(List<QuotedTotalForm> quotedTotalFormList) {
		this.quotedTotalFormList = quotedTotalFormList;
	}

	
	
}