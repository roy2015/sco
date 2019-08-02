package com.powere2e.sco.model.merchandisecostanalysis.totalcostanalogyanalysis;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 地区合作价格
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年6月26日
 */
public class MerchandiseContractPrice extends AppModel {
	
	private static final long serialVersionUID = 1907941405465526719L;
	private String region; //地区
	private BigDecimal price;  //价格
	
	
	
	public MerchandiseContractPrice() {
	}
	
	public MerchandiseContractPrice(String region, BigDecimal price) {
		super();
		this.region = region;
		this.price = price;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
