package com.powere2e.sco.model.materialmarketanalysis.websitehistory;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 公示网站原料历史行情 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月20日
 * @version 1.0
 */
public class WebsiteHistory extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8191621347605674254L;
	private String priceYear;//价格日期(年)
	private String priceDate;//(年-月)
	private BigDecimal price;//价格
	private BigDecimal incParcent;//同比增长率
	private BigDecimal avgPrice;
	private List<WebsiteHistory> list = new ArrayList<WebsiteHistory>();
	
	public String getPriceYear() {
		return priceYear;
	}
	public void setPriceYear(String priceYear) {
		this.priceYear = priceYear;
	}
	public String getPriceDate() {
		return priceDate;
	}
	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public List<WebsiteHistory> getList() {
		return list;
	}
	public void setList(List<WebsiteHistory> list) {
		this.list = list;
	}
	
	public BigDecimal getAvgPrice() {
		return avgPrice;
	}
	public void setAvgPrice(BigDecimal avgPrice) {
		this.avgPrice = avgPrice;
	}
	public BigDecimal getIncParcent() {
		return incParcent;
	}
	public void setIncParcent(BigDecimal incParcent) {
		this.incParcent = incParcent;
	}
	
}