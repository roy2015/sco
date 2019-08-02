package com.powere2e.sco.model.datamaintenance.inventorydata.concessionreceive;

import java.math.BigDecimal;
import java.util.Date;

import com.powere2e.frame.server.model.AppModel;

import org.apache.struts2.json.annotations.JSON;

/**
 * 让步接收实体类
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月8日
 */
public class ConcessionReceive extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1263713499411451618L;
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String specification;// 箱规
	private String orderTaker;//
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private Integer concessionReceiveCount;// 让步接收数量
	private Date applicationDate;// 让步接收申请日期
	private Integer giveAwayCount;//
	private BigDecimal purchasePrice;//
	private BigDecimal giveAwayMoney;//
	private String remarks;//
	private String centreTypeCode;//
	private String centreName;// 中分类
	private String smallTypeCode;//
	private String smallName;// 小分类
	private String detailTypeCode;
	private String detailName;// 明细类
	private String fineTypeCode;//
	private String fineName;// 细分类
	private String dlRoleCode;//
	private String dlRoleName;// 商品定量角色
	private String dxRoleCode;//
	private String dxRoleName;// 商品定性角色

	public ConcessionReceive() {
	}

	public ConcessionReceive(String merchandiseCode, String merchandiseName, String specification, String orderTaker, String supplierCode, String supplierName, Integer concessionReceiveCount,
			Date applicationDate, Integer giveAwayCount, BigDecimal purchasePrice, BigDecimal giveAwayMoney, String remarks) {
		super();
		this.merchandiseCode = merchandiseCode;
		this.merchandiseName = merchandiseName;
		this.specification = specification;
		this.orderTaker = orderTaker;
		this.supplierCode = supplierCode;
		this.supplierName = supplierName;
		this.concessionReceiveCount = concessionReceiveCount;
		this.applicationDate = applicationDate;
		this.giveAwayCount = giveAwayCount;
		this.purchasePrice = purchasePrice;
		this.giveAwayMoney = giveAwayMoney;
		this.remarks = remarks;
	}

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	// 获取
	public String getSpecification() {
		return specification;
	}

	// 设置
	public void setSpecification(String specification) {
		this.specification = specification;
	}

	// 获取
	public String getOrderTaker() {
		return orderTaker;
	}

	// 设置
	public void setOrderTaker(String orderTaker) {
		this.orderTaker = orderTaker;
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
	public Integer getConcessionReceiveCount() {
		return concessionReceiveCount;
	}

	// 设置
	public void setConcessionReceiveCount(Integer concessionReceiveCount) {
		this.concessionReceiveCount = concessionReceiveCount;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd")
	public Date getApplicationDate() {
		return applicationDate;
	}

	// 设置
	public void setApplicationDate(Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	// 获取
	public Integer getGiveAwayCount() {
		return giveAwayCount;
	}

	// 设置
	public void setGiveAwayCount(Integer giveAwayCount) {
		this.giveAwayCount = giveAwayCount;
	}

	// 获取
	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	// 设置
	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	// 获取
	public BigDecimal getGiveAwayMoney() {
		return giveAwayMoney;
	}

	// 设置
	public void setGiveAwayMoney(BigDecimal giveAwayMoney) {
		this.giveAwayMoney = giveAwayMoney;
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
	public String getMerchandiseName() {
		return merchandiseName;
	}

	// 设置
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	// 获取
	public String getSupplierName() {
		return supplierName;
	}

	// 设置
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getFineTypeCode() {
		return fineTypeCode;
	}

	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	public String getFineName() {
		return fineName;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
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

}