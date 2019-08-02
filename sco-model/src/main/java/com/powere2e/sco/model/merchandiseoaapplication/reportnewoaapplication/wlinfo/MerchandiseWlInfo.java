package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.wlinfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品物料信息实体类
 * 
 * @author Gavillen.Zhou
 * @version 1.0
 * @since 2015年05月15日
 */
public class MerchandiseWlInfo extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3573562884444856763L;
	private String applicationCode;//申请单号
	private String intentionCode;//意向品编号
	private String intentionName;//意向品名称
	private String supplierCode;//供应商编号
	private String supplierName;//供应商名称
	private String accessorySAPCode;//SAP物料号
	private String supplierSAPCode;//SAP供应商编号
	private String region;//价格地区
	private BigDecimal sumPrice;//合同进价
	
	//用于存储价格地区
	private List<MerchandiseWlInfo> areaList = new ArrayList<MerchandiseWlInfo>();

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

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

	public String getAccessorySAPCode() {
		return accessorySAPCode;
	}

	public void setAccessorySAPCode(String accessorySAPCode) {
		this.accessorySAPCode = accessorySAPCode;
	}

	public String getSupplierSAPCode() {
		return supplierSAPCode;
	}

	public void setSupplierSAPCode(String supplierSAPCode) {
		this.supplierSAPCode = supplierSAPCode;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}
	
	public BigDecimal getSumPrice() {
		return sumPrice;
	}

	public void setSumPrice(BigDecimal sumPrice) {
		this.sumPrice = sumPrice;
	}

	public List<MerchandiseWlInfo> getAreaList() {
		return areaList;
	}

	public void setAreaList(List<MerchandiseWlInfo> areaList) {
		this.areaList = areaList;
	}
	
	public void addAreaList(MerchandiseWlInfo wl) {
		this.areaList.add(wl);
	}
	
}