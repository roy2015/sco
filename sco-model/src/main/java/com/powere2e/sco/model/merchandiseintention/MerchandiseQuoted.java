package com.powere2e.sco.model.merchandiseintention;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 供应商报价单实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年3月26日
 */
public class MerchandiseQuoted extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5564277815899350535L;

	private String quotationCode;// 报价单编号
	private String intentionCode;// 意向品编号
	private String intentionSupplierCode;// 供应商编号
	private String intentionSupplierName;// 供应商名称
	private String companyName;// 公司名称
	private String companySite;// 公司地址
	private String contactsName;// 联系人姓名
	private String contactsPhone;// 联系人电话
	private String contactsEmail;// 联系人邮箱
	private String contactsFax;// 联系人传真
	private String quotedCurrency;// 报价币种
	private Date quotedDate;// 报价日期
	private BigDecimal price;// 报价价格
	private BigDecimal quotationUnits;// 基本计量单位
	private String minRationed;// 最小起定量
	private Date quotedEndDate;// 最晚报价日期
	private String packingType;// 包装方式
	private String paymentType;// 付款方式
	private String deliveryType;// 交货方式
	private String remarks;// 备注
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	private String lastContactsName;// 某个供应商最后报价日期的联系人姓名
	private String lastContactsPhone;// 某个供应商最后报价日期的联系人电话

	private Date lastQuotedDate;// 最后一次报价日期
	private BigDecimal lastPrice;// 最后一次报价价格
	private String priceGap;// 最后一次报价和首次报价的价差
	private String priceRange;// 意向品报价价格带
	private Integer supplierQuotedCounts;// 报价次数
	private Integer supplierNumbers;// 共参与报价供应商数量
	private Integer priceRank;// 排名
	private String avgPrice;// 平均价格

	// 意向品相关信息
	private String intentionName;// 意向品名称
	private String centreTypeCode;// 中分类
	private String smallTypeCode;// 小分类
	private String detailTypeCode;// 明细类
	private String fineTypeCode;// 细分类
	private String centreTypeName;// 中分类名称
	private String smallTypeName;// 小分类名称
	private String detailTypeName;// 明细类名称
	private String fineTypeName;// 细分类名称

	// 获取
	public String getQuotationCode() {
		return quotationCode;
	}

	// 设置
	public void setQuotationCode(String quotationCode) {
		this.quotationCode = quotationCode;
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
	public String getIntentionSupplierCode() {
		return intentionSupplierCode;
	}

	// 设置
	public void setIntentionSupplierCode(String intentionSupplierCode) {
		this.intentionSupplierCode = intentionSupplierCode;
	}

	// 获取
	public String getCompanyName() {
		return companyName;
	}

	// 设置
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	// 获取
	public String getCompanySite() {
		return companySite;
	}

	// 设置
	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	// 获取
	public String getContactsName() {
		return contactsName;
	}

	// 设置
	public void setContactsName(String contactsName) {
		this.contactsName = contactsName;
	}

	// 获取
	public String getContactsPhone() {
		return contactsPhone;
	}

	// 设置
	public void setContactsPhone(String contactsPhone) {
		this.contactsPhone = contactsPhone;
	}

	// 获取
	public String getContactsEmail() {
		return contactsEmail;
	}

	// 设置
	public void setContactsEmail(String contactsEmail) {
		this.contactsEmail = contactsEmail;
	}

	// 获取
	public String getContactsFax() {
		return contactsFax;
	}

	// 设置
	public void setContactsFax(String contactsFax) {
		this.contactsFax = contactsFax;
	}

	// 获取
	public String getQuotedCurrency() {
		return quotedCurrency;
	}

	// 设置
	public void setQuotedCurrency(String quotedCurrency) {
		this.quotedCurrency = quotedCurrency;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQuotedDate() {
		return quotedDate;
	}

	// 设置
	public void setQuotedDate(Date quotedDate) {
		this.quotedDate = quotedDate;
	}

	// 获取
	public BigDecimal getPrice() {
		return price;
	}

	// 设置
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	// 获取
	public BigDecimal getQuotationUnits() {
		return quotationUnits;
	}

	// 设置
	public void setQuotationUnits(BigDecimal quotationUnits) {
		this.quotationUnits = quotationUnits;
	}

	// 获取
	public String getMinRationed() {
		return minRationed;
	}

	// 设置
	public void setMinRationed(String minRationed) {
		this.minRationed = minRationed;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQuotedEndDate() {
		return quotedEndDate;
	}

	// 设置
	public void setQuotedEndDate(Date quotedEndDate) {
		this.quotedEndDate = quotedEndDate;
	}

	// 获取
	public String getPackingType() {
		return packingType;
	}

	// 设置
	public void setPackingType(String packingType) {
		this.packingType = packingType;
	}

	// 获取
	public String getPaymentType() {
		return paymentType;
	}

	// 设置
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	// 获取
	public String getDeliveryType() {
		return deliveryType;
	}

	// 设置
	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	// 获取
	public String getRemarks() {
		return remarks;
	}

	// 设置
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}

	// 设置
	public void setCreated(Date created) {
		this.created = created;
	}

	// 获取
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}

	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	// 获取
	public String getUpdateby() {
		return updateby;
	}

	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getIntentionSupplierName() {
		return intentionSupplierName;
	}

	public void setIntentionSupplierName(String intentionSupplierName) {
		this.intentionSupplierName = intentionSupplierName;
	}

	public String getLastContactsName() {
		return lastContactsName;
	}

	public void setLastContactsName(String lastContactsName) {
		this.lastContactsName = lastContactsName;
	}

	public String getLastContactsPhone() {
		return lastContactsPhone;
	}

	public void setLastContactsPhone(String lastContactsPhone) {
		this.lastContactsPhone = lastContactsPhone;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getLastQuotedDate() {
		return lastQuotedDate;
	}

	public void setLastQuotedDate(Date lastQuotedDate) {
		this.lastQuotedDate = lastQuotedDate;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}

	public void setLastPrice(BigDecimal lastPrice) {
		this.lastPrice = lastPrice;
	}

	public String getPriceGap() {
		return priceGap;
	}

	public void setPriceGap(String priceGap) {
		this.priceGap = priceGap;
	}

	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public Integer getSupplierQuotedCounts() {
		return supplierQuotedCounts;
	}

	public void setSupplierQuotedCounts(Integer supplierQuotedCounts) {
		this.supplierQuotedCounts = supplierQuotedCounts;
	}

	public Integer getSupplierNumbers() {
		return supplierNumbers;
	}

	public void setSupplierNumbers(Integer supplierNumbers) {
		this.supplierNumbers = supplierNumbers;
	}

	public Integer getPriceRank() {
		return priceRank;
	}

	public void setPriceRank(Integer priceRank) {
		this.priceRank = priceRank;
	}

	public String getIntentionName() {
		return intentionName;
	}

	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
	}

	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	public String getFineTypeCode() {
		return fineTypeCode;
	}

	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getCentreTypeName() {
		return centreTypeName;
	}

	public void setCentreTypeName(String centreTypeName) {
		this.centreTypeName = centreTypeName;
	}

	public String getSmallTypeName() {
		return smallTypeName;
	}

	public void setSmallTypeName(String smallTypeName) {
		this.smallTypeName = smallTypeName;
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}

	public String getFineTypeName() {
		return fineTypeName;
	}

	public void setFineTypeName(String fineTypeName) {
		this.fineTypeName = fineTypeName;
	}

}