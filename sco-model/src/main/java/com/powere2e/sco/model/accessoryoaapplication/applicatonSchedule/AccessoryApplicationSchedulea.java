package com.powere2e.sco.model.accessoryoaapplication.applicatonSchedule;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 辅料采购委员会竞价进度信息 实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年5月6日
 */
public class AccessoryApplicationSchedulea extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3573448153888148037L;
	private String applicationCode;// 申请单号
	private String intentionCode;// 意向品/商品编号
	private String intentionName;//意向品/商品名称
	private String intentionSupplierCode;// 供应商编号(供应商/意向供应商编号)
	private String intentionSupplierName;//(供应商/意向供应商名称)
	private String enquiryCode;// 询价单号
	private String quotedCode;// 报价单号
	private String enquiryCount;// 询价数量
	private BigDecimal realPurCount;//实际采购数量
	private BigDecimal contractPrice;//合同进价
	private BigDecimal realPurPrice;//实际采购金额
	private Date subscribeBillDate;// 收到申购单日期
	private Date sjwgDate;// 设计完稿日期[进度表]
	private Date requirementsDwDate;// 要求到货日期
	private Date jjdtjDate;// 竞价单提交日期
	private Date jjdwcDate;// 竞价单完成日期
	private BigDecimal jjdspwcts;// 竞价单审批完成天数
	private Date orderDate;// 下达采购订单日期[进度表]
	private Date aogDate;// 实际到货日期[进度表]
	private BigDecimal proofingCycle;// 样品打样周期
	private BigDecimal normalFlowDate;// 正常作业流程时间（天）[进度表]
	private BigDecimal realityFlowDate;// 实际作业流程时间（天）
	private BigDecimal differenceDate;// 差异天数

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
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

	// 获取
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	public BigDecimal getRealPurCount() {
		return realPurCount;
	}

	public void setRealPurCount(BigDecimal realPurCount) {
		this.realPurCount = realPurCount;
	}

	public BigDecimal getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(BigDecimal contractPrice) {
		this.contractPrice = contractPrice;
	}

	public BigDecimal getRealPurPrice() {
		return realPurPrice;
	}

	public void setRealPurPrice(BigDecimal realPurPrice) {
		this.realPurPrice = realPurPrice;
	}

	// 获取
	public String getEnquiryCode() {
		return enquiryCode;
	}

	// 设置
	public void setEnquiryCode(String enquiryCode) {
		this.enquiryCode = enquiryCode;
	}

	// 获取
	public String getQuotedCode() {
		return quotedCode;
	}

	// 设置
	public void setQuotedCode(String quotedCode) {
		this.quotedCode = quotedCode;
	}

	// 获取
	public String getEnquiryCount() {
		return enquiryCount;
	}

	// 设置
	public void setEnquiryCount(String enquiryCount) {
		this.enquiryCount = enquiryCount;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getSubscribeBillDate() {
		return subscribeBillDate;
	}

	// 设置
	public void setSubscribeBillDate(Date subscribeBillDate) {
		this.subscribeBillDate = subscribeBillDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getSjwgDate() {
		return sjwgDate;
	}

	// 设置
	public void setSjwgDate(Date sjwgDate) {
		this.sjwgDate = sjwgDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getRequirementsDwDate() {
		return requirementsDwDate;
	}

	// 设置
	public void setRequirementsDwDate(Date requirementsDwDate) {
		this.requirementsDwDate = requirementsDwDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getJjdtjDate() {
		return jjdtjDate;
	}

	// 设置
	public void setJjdtjDate(Date jjdtjDate) {
		this.jjdtjDate = jjdtjDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getJjdwcDate() {
		return jjdwcDate;
	}

	// 设置
	public void setJjdwcDate(Date jjdwcDate) {
		this.jjdwcDate = jjdwcDate;
	}

	// 获取
	public BigDecimal getJjdspwcts() {
		return jjdspwcts;
	}

	// 设置
	public void setJjdspwcts(BigDecimal jjdspwcts) {
		this.jjdspwcts = jjdspwcts;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getOrderDate() {
		return orderDate;
	}

	// 设置
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getAogDate() {
		return aogDate;
	}

	// 设置
	public void setAogDate(Date aogDate) {
		this.aogDate = aogDate;
	}

	// 获取
	public BigDecimal getProofingCycle() {
		return proofingCycle;
	}

	// 设置
	public void setProofingCycle(BigDecimal proofingCycle) {
		this.proofingCycle = proofingCycle;
	}

	// 获取
	public BigDecimal getNormalFlowDate() {
		return normalFlowDate;
	}

	// 设置
	public void setNormalFlowDate(BigDecimal normalFlowDate) {
		this.normalFlowDate = normalFlowDate;
	}

	// 获取
	public BigDecimal getRealityFlowDate() {
		return realityFlowDate;
	}

	// 设置
	public void setRealityFlowDate(BigDecimal realityFlowDate) {
		this.realityFlowDate = realityFlowDate;
	}

	// 获取
	public BigDecimal getDifferenceDate() {
		return differenceDate;
	}

	// 设置
	public void setDifferenceDate(BigDecimal differenceDate) {
		this.differenceDate = differenceDate;
	}
}