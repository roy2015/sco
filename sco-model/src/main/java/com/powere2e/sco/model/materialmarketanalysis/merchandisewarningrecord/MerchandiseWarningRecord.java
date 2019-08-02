package com.powere2e.sco.model.materialmarketanalysis.merchandisewarningrecord;

import java.math.BigDecimal;

import com.powere2e.sco.model.datamaintenance.purchaserdata.relevancematerialandwebsite.RelevanceMaterialAndWebsite;

/**
 * 商品预警记录 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月11日
 * @version 1.0
 */
public class MerchandiseWarningRecord extends RelevanceMaterialAndWebsite {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8114448748120336071L;
	
	private String regionName;//地区名称
	private BigDecimal merPurPrice;//商品进价
	private BigDecimal suppMatePurPrice;//供应商原料采购价
	private BigDecimal suppMateInputPrice;//供应商原料投入价
	private BigDecimal websiteMatePrice;//公示网站原料行情原价
	private BigDecimal websiteLastMatePrice;//公示网站上月原料行情价
	private BigDecimal suppMateInputTest;//供应商原料投入价试算
	private BigDecimal merPurTest;//商品进价试算
	private BigDecimal merPurIncrease;//商品进价增长数值
	private BigDecimal merPurPercent;//商品进价变化百分比
	private String recordType;//预警、非预警、所有记录
	
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public BigDecimal getMerPurPrice() {
		return merPurPrice;
	}
	public void setMerPurPrice(BigDecimal merPurPrice) {
		this.merPurPrice = merPurPrice;
	}
	public BigDecimal getSuppMatePurPrice() {
		return suppMatePurPrice;
	}
	public void setSuppMatePurPrice(BigDecimal suppMatePurPrice) {
		this.suppMatePurPrice = suppMatePurPrice;
	}
	public BigDecimal getSuppMateInputPrice() {
		return suppMateInputPrice;
	}
	public void setSuppMateInputPrice(BigDecimal suppMateInputPrice) {
		this.suppMateInputPrice = suppMateInputPrice;
	}
	public BigDecimal getWebsiteMatePrice() {
		return websiteMatePrice;
	}
	public void setWebsiteMatePrice(BigDecimal websiteMatePrice) {
		this.websiteMatePrice = websiteMatePrice;
	}
	public BigDecimal getWebsiteLastMatePrice() {
		return websiteLastMatePrice;
	}
	public void setWebsiteLastMatePrice(BigDecimal websiteLastMatePrice) {
		this.websiteLastMatePrice = websiteLastMatePrice;
	}
	public BigDecimal getSuppMateInputTest() {
		return suppMateInputTest;
	}
	public void setSuppMateInputTest(BigDecimal suppMateInputTest) {
		this.suppMateInputTest = suppMateInputTest;
	}
	public BigDecimal getMerPurTest() {
		return merPurTest;
	}
	public void setMerPurTest(BigDecimal merPurTest) {
		this.merPurTest = merPurTest;
	}
	public BigDecimal getMerPurIncrease() {
		return merPurIncrease;
	}
	public void setMerPurIncrease(BigDecimal merPurIncrease) {
		this.merPurIncrease = merPurIncrease;
	}
	public BigDecimal getMerPurPercent() {
		return merPurPercent;
	}
	public void setMerPurPercent(BigDecimal merPurPercent) {
		this.merPurPercent = merPurPercent;
	}
	public String getRecordType() {
		return recordType;
	}
	public void setRecordType(String recordType) {
		this.recordType = recordType;
	}
	
}