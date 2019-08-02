package com.powere2e.sco.model.materialmarketanalysis.materialwarningrecord;

import java.math.BigDecimal;

import com.powere2e.sco.model.parameterset.materialwarnconfig.MaterialWarnConfigJoint;

/**
 * 原料预警记录 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年8月7日
 * @version 1.0
 */
public class MaterialWarningRecord extends MaterialWarnConfigJoint{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8495479136098134791L;
	
	private String region;//地区编号 
	private String regionName;//价格地区名称
	private BigDecimal lasMonthAvgPrice;//上月平均价格
	private BigDecimal momAvgMonthPrice;//同环比月均价格
	private BigDecimal momChangeChange;//同环比变化幅度
	private BigDecimal skuCount;//关联SKU数
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
	public BigDecimal getLasMonthAvgPrice() {
		return lasMonthAvgPrice;
	}
	public void setLasMonthAvgPrice(BigDecimal lasMonthAvgPrice) {
		this.lasMonthAvgPrice = lasMonthAvgPrice;
	}
	public BigDecimal getMomAvgMonthPrice() {
		return momAvgMonthPrice;
	}
	public void setMomAvgMonthPrice(BigDecimal momAvgMonthPrice) {
		this.momAvgMonthPrice = momAvgMonthPrice;
	}
	public BigDecimal getMomChangeChange() {
		return momChangeChange;
	}
	public void setMomChangeChange(BigDecimal momChangeChange) {
		this.momChangeChange = momChangeChange;
	}
	public BigDecimal getSkuCount() {
		return skuCount;
	}
	public void setSkuCount(BigDecimal skuCount) {
		this.skuCount = skuCount;
	}
	
}
