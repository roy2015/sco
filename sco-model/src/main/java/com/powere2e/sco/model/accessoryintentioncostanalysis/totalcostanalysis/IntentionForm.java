package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;
import java.util.List;


import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class IntentionForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3805805152068501738L;
	private String intentionCode;//意向品编号
	private String intentionName;//意向品编号
	private int count;//意向品编号
	private List<EnquiryForm> enquiryList;

	public String getIntentionCode() {
		return intentionCode;
	}

	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	public List<EnquiryForm> getEnquiryList() {
		return enquiryList;
	}

	public void setEnquiryList(List<EnquiryForm> enquiryList) {
		this.enquiryList = enquiryList;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}
	
	
	
}