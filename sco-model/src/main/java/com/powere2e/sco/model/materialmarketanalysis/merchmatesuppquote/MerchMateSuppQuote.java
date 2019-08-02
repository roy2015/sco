package com.powere2e.sco.model.materialmarketanalysis.merchmatesuppquote;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.powere2e.sco.model.materialmarketanalysis.websitehistory.WebsiteHistory;

/**
 * 商品原料供应商报价 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年7月30日
 * @version 1.0
 */
public class MerchMateSuppQuote extends WebsiteHistory {

	/**
	 * 
	 */
	private static final long serialVersionUID = 231191687127790537L;
	
	private String priceYear;
	
	private BigDecimal price;

	private List<MerchMateSuppQuote> accList = new ArrayList<MerchMateSuppQuote>();//投料表内供应商原料采购价格
	
	private List<WebsiteHistory> webList = new ArrayList<WebsiteHistory>();//公示网站名称-公示网站原料名称价格
	
	private List<MerchMateSuppQuote> ingList = new ArrayList<MerchMateSuppQuote>();;//投料占比
	
	private List<MerchMateSuppQuote> purList = new ArrayList<MerchMateSuppQuote>();//商品进价
	
	private List<MerchMateSuppQuote> soldList = new ArrayList<MerchMateSuppQuote>();//商品售价
	
	//无参构造
	public MerchMateSuppQuote() {

	}
	
	public MerchMateSuppQuote(String priceYear, BigDecimal price) {
		this.priceYear = priceYear;
		this.price = price;
	}

	public void addAccList(String priceYear, BigDecimal price) {
		this.accList.add(new MerchMateSuppQuote(priceYear, price));
	}

	public String getPriceYear() {
		return priceYear;
	}

	public void setPriceYear(String priceYear) {
		this.priceYear = priceYear;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public List<MerchMateSuppQuote> getAccList() {
		return accList;
	}

	public void setAccList(List<MerchMateSuppQuote> accList) {
		this.accList = accList;
	}

	public List<WebsiteHistory> getWebList() {
		return webList;
	}

	public void setWebList(List<WebsiteHistory> webList) {
		this.webList = webList;
	}

	public List<MerchMateSuppQuote> getIngList() {
		return ingList;
	}

	public void setIngList(List<MerchMateSuppQuote> ingList) {
		this.ingList = ingList;
	}

	public List<MerchMateSuppQuote> getPurList() {
		return purList;
	}

	public void setPurList(List<MerchMateSuppQuote> purList) {
		this.purList = purList;
	}

	public List<MerchMateSuppQuote> getSoldList() {
		return soldList;
	}

	public void setSoldList(List<MerchMateSuppQuote> soldList) {
		this.soldList = soldList;
	}
	
}