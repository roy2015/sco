package com.powere2e.sco.model.accessoryoaapplication;

import java.util.List;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryQuotedCount;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;

/**
 * 申购产品信息实体类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月27日
 */
public class SubscribeAccessory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9013877967699382068L;
	private String applicationCode;//
	private String specificationRequirements;//
	private String intentionCode;//
	private String accessoryCode;//
	private String accessoryName;//
	private String enquiryCode;//
	private String enquiryCount;//
	private String purchaseCount;//
	private List<AccessoryEnquiryPacking> accessoryEnquiryPackingList;
	private List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList;
	private List<AccessoryEnquiryElse> accessoryEnquiryElseList;
	private List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList;
	private List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList;
	private List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList;

	// private List<AccessoryEnquiryPacking> accessoryEnquiryPackingList;
	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	// 获取
	public String getSpecificationRequirements() {
		return specificationRequirements;
	}

	// 设置
	public void setSpecificationRequirements(String specificationRequirements) {
		this.specificationRequirements = specificationRequirements;
	}

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getAccessoryCode() {
		return accessoryCode;
	}

	// 设置
	public void setAccessoryCode(String accessoryCode) {
		this.accessoryCode = accessoryCode;
	}

	// 获取
	public String getAccessoryName() {
		return accessoryName;
	}

	// 设置
	public void setAccessoryName(String accessoryName) {
		this.accessoryName = accessoryName;
	}

	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}

	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	public String getEnquiryCount() {
		return enquiryCount;
	}

	public void setEnquiryCount(String enquiryCount) {
		this.enquiryCount = enquiryCount;
	}

	public String getPurchaseCount() {
		return purchaseCount;
	}

	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}

	public List<AccessoryEnquiryQuotedCount> getAccessoryEnquiryQuotedCountList() {
		return accessoryEnquiryQuotedCountList;
	}

	public void setAccessoryEnquiryQuotedCountList(List<AccessoryEnquiryQuotedCount> accessoryEnquiryQuotedCountList) {
		this.accessoryEnquiryQuotedCountList = accessoryEnquiryQuotedCountList;
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