package com.powere2e.sco.model.peripheral.sap;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品销售价格信息实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年8月21日
 */
public class MerchandisePrice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8850850770300718263L;
	private String merchandiseCode;//
	private String supplierCode;//
	private String sellRegion;//
	private String directJoin;//
	private BigDecimal sellPrice;//
	private Date priceDate;//
	private Date syncDate;//

	private List<MerchandisePrice> list;

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	// 获取
	public String getSellRegion() {
		return sellRegion;
	}

	// 设置
	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	// 获取
	public String getDirectJoin() {
		return directJoin;
	}

	// 设置
	public void setDirectJoin(String directJoin) {
		this.directJoin = directJoin;
	}

	// 获取
	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	// 设置
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getPriceDate() {
		return priceDate;
	}

	// 设置
	public void setPriceDate(Date priceDate) {
		this.priceDate = priceDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getSyncDate() {
		return syncDate;
	}

	// 设置
	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public List<MerchandisePrice> getList() {
		return list;
	}

	public void setList(List<MerchandisePrice> list) {
		this.list = list;
	}

}