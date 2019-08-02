package com.powere2e.sco.model.accessoryintentioncostanalysis.historicalquotesupplier;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryAccessory;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryElse;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryMaterial;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryPacking;
import com.powere2e.sco.model.accessoryintention.AccessoryEnquiryTechnology;

public class HistoricalQuoteSupplierForm {
	private String intentionCode;//意向品编号
	private String intentionName;//意向品名称
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private String intentionSupplierCode;
	private String intentionSupplierName;
	private String intentionPicture;//意向品图片
	private String lastSpecifications;//最后一次引进规格
	private String actualSpecifications;//实际引进规格
	private String lastQuoteCount;//最后一次报价数量
	private BigDecimal lastQuoteTotal;//最后一次报价总价
	private BigDecimal supplierCount;//报价供应商数量
	private BigDecimal supplierRanking;//供应商报价排行
	private BigDecimal cooperationPrice;//合作价格
	private String purchaseCount;//实际采购数量
	private BigDecimal purchaseMoney;//实际采购金额
	private Date cooperationPriceDate;//合作价格日期
	private Date receivedOADate;//收到申购单日期
	private Date requiredDeliveryDate;//要求到货日期
	private Date oaSubmitDate;//进价单提交日期
	private Date oaCompleteDate;//进价单完成日期
	private BigDecimal oaCompleteDays;//进价单审批完成天数?
	private Date poDate;//下达订单日期
	private Date actualDeliveryDate;//实际到货日期
	private Date receiveOaDate;//收到申请单日期
	private Date sjwgDate;//设计完稿日期
	private BigDecimal proofingCycle;//样品打样周期
	private BigDecimal normalProcessDays;//正常作业流程天数
	private BigDecimal actualProcessDays;//实际作业流程天数?
	private BigDecimal differencesDays;//差异天数?
	private String proofingEvaluate;//产前样评价
	private String enquiryCode;
	private List<AccessoryEnquiryPacking> accessoryEnquiryPackingList;
	private List<AccessoryEnquiryTechnology> accessoryEnquiryTechnologyList;
	private List<AccessoryEnquiryElse> accessoryEnquiryElseList;
	private List<AccessoryEnquiryMaterial> accessoryEnquiryMaterialList;
	private List<AccessoryEnquiryAccessory> accessoryEnquiryAccessoryList;
	public String getIntentionCode() {
		return intentionCode;
	}
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}
	public String getIntentionName() {
		return intentionName;
	}
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}
	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}
	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}
	public String getIntentionPicture() {
		return intentionPicture;
	}
	public void setIntentionPicture(String intentionPicture) {
		this.intentionPicture = intentionPicture;
	}
	public String getLastSpecifications() {
		return lastSpecifications;
	}
	public void setLastSpecifications(String lastSpecifications) {
		this.lastSpecifications = lastSpecifications;
	}
	public String getActualSpecifications() {
		return actualSpecifications;
	}
	public void setActualSpecifications(String actualSpecifications) {
		this.actualSpecifications = actualSpecifications;
	}
	
	
	public String getLastQuoteCount() {
		return lastQuoteCount;
	}
	public void setLastQuoteCount(String lastQuoteCount) {
		this.lastQuoteCount = lastQuoteCount;
	}
	public BigDecimal getLastQuoteTotal() {
		return lastQuoteTotal;
	}
	public void setLastQuoteTotal(BigDecimal lastQuoteTotal) {
		this.lastQuoteTotal = lastQuoteTotal;
	}
	
	public BigDecimal getSupplierCount() {
		return supplierCount;
	}
	public void setSupplierCount(BigDecimal supplierCount) {
		this.supplierCount = supplierCount;
	}
	public BigDecimal getSupplierRanking() {
		return supplierRanking;
	}
	public void setSupplierRanking(BigDecimal supplierRanking) {
		this.supplierRanking = supplierRanking;
	}
	public BigDecimal getCooperationPrice() {
		return cooperationPrice;
	}
	public void setCooperationPrice(BigDecimal cooperationPrice) {
		this.cooperationPrice = cooperationPrice;
	}
	public String getPurchaseCount() {
		return purchaseCount;
	}
	public void setPurchaseCount(String purchaseCount) {
		this.purchaseCount = purchaseCount;
	}
	public BigDecimal getPurchaseMoney() {
		return purchaseMoney;
	}
	public void setPurchaseMoney(BigDecimal purchaseMoney) {
		this.purchaseMoney = purchaseMoney;
	}
	public Date getCooperationPriceDate() {
		return cooperationPriceDate;
	}
	public void setCooperationPriceDate(Date cooperationPriceDate) {
		this.cooperationPriceDate = cooperationPriceDate;
	}
	public Date getReceivedOADate() {
		return receivedOADate;
	}
	public void setReceivedOADate(Date receivedOADate) {
		this.receivedOADate = receivedOADate;
	}
	public Date getRequiredDeliveryDate() {
		return requiredDeliveryDate;
	}
	public void setRequiredDeliveryDate(Date requiredDeliveryDate) {
		this.requiredDeliveryDate = requiredDeliveryDate;
	}
	public Date getOaSubmitDate() {
		return oaSubmitDate;
	}
	public void setOaSubmitDate(Date oaSubmitDate) {
		this.oaSubmitDate = oaSubmitDate;
	}
	public Date getOaCompleteDate() {
		return oaCompleteDate;
	}
	public void setOaCompleteDate(Date oaCompleteDate) {
		this.oaCompleteDate = oaCompleteDate;
	}
	public BigDecimal getOaCompleteDays() {
		return oaCompleteDays;
	}
	public void setOaCompleteDays(BigDecimal oaCompleteDays) {
		this.oaCompleteDays = oaCompleteDays;
	}
	public Date getPoDate() {
		return poDate;
	}
	public void setPoDate(Date poDate) {
		this.poDate = poDate;
	}
	public Date getActualDeliveryDate() {
		return actualDeliveryDate;
	}
	public void setActualDeliveryDate(Date actualDeliveryDate) {
		this.actualDeliveryDate = actualDeliveryDate;
	}
	public Date getReceiveOaDate() {
		return receiveOaDate;
	}
	public void setReceiveOaDate(Date receiveOaDate) {
		this.receiveOaDate = receiveOaDate;
	}
	public Date getSjwgDate() {
		return sjwgDate;
	}
	public void setSjwgDate(Date sjwgDate) {
		this.sjwgDate = sjwgDate;
	}
	public BigDecimal getProofingCycle() {
		return proofingCycle;
	}
	public void setProofingCycle(BigDecimal proofingCycle) {
		this.proofingCycle = proofingCycle;
	}
	public BigDecimal getNormalProcessDays() {
		return normalProcessDays;
	}
	public void setNormalProcessDays(BigDecimal normalProcessDays) {
		this.normalProcessDays = normalProcessDays;
	}
	public BigDecimal getActualProcessDays() {
		return actualProcessDays;
	}
	public void setActualProcessDays(BigDecimal actualProcessDays) {
		this.actualProcessDays = actualProcessDays;
	}
	public BigDecimal getDifferencesDays() {
		return differencesDays;
	}
	public void setDifferencesDays(BigDecimal differencesDays) {
		this.differencesDays = differencesDays;
	}
	public String getProofingEvaluate() {
		return proofingEvaluate;
	}
	public void setProofingEvaluate(String proofingEvaluate) {
		this.proofingEvaluate = proofingEvaluate;
	}
	public String getEnquiryCode() {
		return enquiryCode;
	}
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
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
