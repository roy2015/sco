package com.powere2e.sco.model.accessoryintentioncostanalysis.totalcostanalysis;

import java.util.List;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;

/**
 * 总成本分析实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class EnquiryForm extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5399962539846650455L;
	private String enquiryCode;// 询价单号

	private List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList;
	private String purchaseCount;// 实际采购数量
	private List<AccessoryEnquiryPacking> accessoryEnquiryPackingList;
	private List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList;
	private List<AccessoryEnquiryElse> accessoryEnquiryElseList;
	private List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList;
	private List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList;

	public String getEnquiryCode() {
		return enquiryCode;
	}

	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	public List<AccessoryEnquiryQuotedCount> getAccessoryEnquiryQuotedCountList() {
		return accessoryEnquiryQuotedCountList;
	}

	public void setAccessoryEnquiryQuotedCountList(List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList) {
		this.accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountList;
	}

	public String getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public List<AccessoryEnquiryPacking> getAccessoryEnquiryPackingList() {
		return accessoryEnquiryPackingList;
	}

	public void setAccessoryEnquiryPackingList(List<AccessoryEnquiryPacking> accessoryEnquiryPackingList) {
		this.accessoryEnquiryPackingList = accessoryEnquiryPackingList;
	}

	public List<AccessoryEnquiryTechnology> getAccessoryEnquiryTechnologyList() {
		return accessoryEnquiryTechnologyList;
	}

	public void setAccessoryEnquiryTechnologyList(List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList) {
		this.accessoryEnquiryTechnologyList = accessoryEnquiryTechnologyList;
	}

	public List<AccessoryEnquiryElse> getAccessoryEnquiryElseList() {
		return accessoryEnquiryElseList;
	}

	public void setAccessoryEnquiryElseList(List<AccessoryEnquiryElse> accessoryEnquiryElseList) {
		this.accessoryEnquiryElseList = accessoryEnquiryElseList;
	}

	public List<AccessoryEnquiryMaterial> getAccessoryEnquiryMaterialList() {
		return accessoryEnquiryMaterialList;
	}

	public void setAccessoryEnquiryMaterialList(List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList) {
		this.accessoryEnquiryMaterialList = accessoryEnquiryMaterialList;
	}

	public List<AccessoryEnquiryAccessory> getAccessoryEnquiryAccessoryList() {
		return accessoryEnquiryAccessoryList;
	}

	public void setAccessoryEnquiryAccessoryList(List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList) {
		this.accessoryEnquiryAccessoryList = accessoryEnquiryAccessoryList;
	}

}