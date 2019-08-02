package com.powere2e.sco.model.datamaintenance.purchaserdata.marketsurveydata;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.sco.model.masterdata.Merchandise;

/**
 * 竞品价格 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月29日
 * @version 1.0
 */
public class MarketSurveyData extends Merchandise {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3173906488090901369L;
	
	private String id;//数据ID
	private String jpmcName;//竞品卖场名称
	private String mcRegion;//卖场地区
	private String jpPriceUnits;//竞品价格单位
	private BigDecimal jpContent;//竞品净含量(kg)
	private Date marketSurveyDate;//市调价格日期
	private BigDecimal marketSurveyPrice;//市调售价(元)
	private BigDecimal discountPrice;//折后公斤价
	private BigDecimal mdiscountPrice;//竞品折后公斤价
	
	public MarketSurveyData(String id, String merchandiseCode, String jpmcName, String mcRegion,
			String jpPriceUnits, Double jpContent, Date marketSurveyDate, Double marketSurveyPrice) {
		super.setMerchandiseCode(merchandiseCode);
		this.id = id;
		this.jpmcName = jpmcName;
		this.mcRegion = mcRegion;
		this.jpPriceUnits = jpPriceUnits;
		this.jpContent = new BigDecimal(jpContent);
		this.marketSurveyDate = marketSurveyDate;
		this.marketSurveyPrice = new BigDecimal(marketSurveyPrice);
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MarketSurveyData() {
		super();
	}
	
	// 获取
	public String getJpmcName() {
		return jpmcName;
	}
	// 设置
	public void setJpmcName(String jpmcName) {
		this.jpmcName = jpmcName;
	}
	
	// 获取
	public String getMcRegion() {
		return mcRegion;
	}
	// 设置
	public void setMcRegion(String mcRegion) {
		this.mcRegion = mcRegion;
	}
	
	// 获取
	public String getJpPriceUnits() {
		return jpPriceUnits;
	}
	// 设置
	public void setJpPriceUnits(String jpPriceUnits) {
		this.jpPriceUnits = jpPriceUnits;
	}
	
	// 获取
	public BigDecimal getJpContent() {
		return jpContent;
	}
	// 设置
	public void setJpContent(BigDecimal jpContent) {
		this.jpContent = jpContent;
	}
	
	// 获取
	@JSON(format="yyyy-MM-dd")
	public Date getMarketSurveyDate() {
		return marketSurveyDate;
	}
	// 设置
	public void setMarketSurveyDate(Date marketSurveyDate) {
		this.marketSurveyDate = marketSurveyDate;
	}
	
	// 获取
	public BigDecimal getMarketSurveyPrice() {
		return marketSurveyPrice;
	}
	// 设置
	public void setMarketSurveyPrice(BigDecimal marketSurveyPrice) {
		this.marketSurveyPrice = marketSurveyPrice;
	}

	public BigDecimal getDiscountPrice() {
		return discountPrice;
	}

	public void setDiscountPrice(BigDecimal discountPrice) {
		this.discountPrice = discountPrice;
	}

	public BigDecimal getMdiscountPrice() {
		return mdiscountPrice;
	}

	public void setMdiscountPrice(BigDecimal mdiscountPrice) {
		this.mdiscountPrice = mdiscountPrice;
	}
	
}