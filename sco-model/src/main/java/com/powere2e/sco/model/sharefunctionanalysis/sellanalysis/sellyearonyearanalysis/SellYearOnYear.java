package com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.sellyearonyearanalysis;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * 销售同比实体类
 * 
 * @author Joyce.li
 * @since 2015年7月7日 上午10:33:51
 * @version 1.0
 */
public class SellYearOnYear extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4330232768839103814L;
	private String merchandiseCode;// 商品编码
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String centreTypeCode;// 中分类
	private String smallTypeCode;// 小分类
	private String detailTypeCode;// 明细类
	private String fineTypeCode;// 细分类
	private String centreTypeName;// 中分类名称
	private String smallTypeName;// 小分类名称
	private String detailTypeName;// 明细类名称
	private String fineTypeName;// 细分类名称
	private String dxRoleCode;// 定性角色code
	private String dxRoleName;// 定性角色名称
	private String dlRoleCode;// 定量角色code
	private String dlRoleName;// 定量角色名称
	private String purchaseDepartments;// 采购部门
	private String sellRegion;// 地区
	private Date startDate;
	private Date endDate;
	private String rationed;// 是否定量

	private Integer detailTypeSku;// 明细类SKU数
	private Integer smallTypeSku;// 小分类SKU数

	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
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

	public String getDxRoleCode() {
		return dxRoleCode;
	}

	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
	}

	public String getDxRoleName() {
		return dxRoleName;
	}

	public void setDxRoleName(String dxRoleName) {
		this.dxRoleName = dxRoleName;
	}

	public String getDlRoleCode() {
		return dlRoleCode;
	}

	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}

	public String getDlRoleName() {
		return dlRoleName;
	}

	public void setDlRoleName(String dlRoleName) {
		this.dlRoleName = dlRoleName;
	}

	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}

	public String getSellRegion() {
		return sellRegion;
	}

	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	@JSON(format = "yyyy-MM")
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@JSON(format = "yyyy-MM")
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getRationed() {
		return rationed;
	}

	public void setRationed(String rationed) {
		this.rationed = rationed;
	}

	public Integer getDetailTypeSku() {
		return detailTypeSku;
	}

	public void setDetailTypeSku(Integer detailTypeSku) {
		this.detailTypeSku = detailTypeSku;
	}

	public Integer getSmallTypeSku() {
		return smallTypeSku;
	}

	public void setSmallTypeSku(Integer smallTypeSku) {
		this.smallTypeSku = smallTypeSku;
	}

}