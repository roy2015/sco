package com.powere2e.sco.model.sharefunctionanalysis.purchasinganalysis.merchandisepromotionbuypriceadjust;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 进货明细实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年7月2日
 */
public class MerchandisePromotionBuyPriceAdjust extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3295557033419103087L;
	private String merchandiseCode;// 商品编码
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String centreName;// 中分类名称
	private String centreTypeCode;// 中分类ID
	private String smallName;// 小分类名称
	private String smallTypeCode;// 小分类ID
	private String detailName;// 明细类名称
	private String detailTypeCode;// 明细类ID
	private String fineTypeCode;// 细分类ID
	private String fineName;// 细分类名称
	private String dxRoleName;// 定性角色名称
	private String dxRoleCode;// 定性角色ID
	private String dlRoleName;// 定量角色名称
	private String dlRoleCode;// 定量角色ID
	private String maxDate;// 促销开始时间
	private String minDate;// 促销结束时间
	private String promotionCode;// 促销编号
	private String promotionName;// 促销名称
	private String promotionSchedule;// 促销档期
	private String show;// 定量与非定量
	private String regionCode;// 地区编号
	private String regionName;// 地区名称
	private String warehouseCode;// 工厂编号
	private BigDecimal receiptRationed;// 促销期间采购量
	private BigDecimal receiptTotalPrice;// 调整后价格采购额
	private String priceDate;// 促销档期
	private BigDecimal purchasePrice;// 促销价格

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromotionName() {
		return promotionName;
	}

	public void setPromotionName(String promotionName) {
		this.promotionName = promotionName;
	}

	public String getPromotionSchedule() {
		return promotionSchedule;
	}

	public void setPromotionSchedule(String promotionSchedule) {
		this.promotionSchedule = promotionSchedule;
	}

	public String getWarehouseCode() {
		return warehouseCode;
	}

	public void setWarehouseCode(String warehouseCode) {
		this.warehouseCode = warehouseCode;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getPriceDate() {
		return priceDate;
	}

	public void setPriceDate(String priceDate) {
		this.priceDate = priceDate;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	private String realityReceiptDate;//

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

	public String getFineName() {
		return fineName;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
	}

	public String getDxRoleCode() {
		return dxRoleCode;
	}

	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
	}

	public String getDlRoleCode() {
		return dlRoleCode;
	}

	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}

	public void setReceiptTotalPrice(BigDecimal receiptTotalPrice) {
		this.receiptTotalPrice = receiptTotalPrice;
	}

	public void setRealityReceiptDate(String realityReceiptDate) {
		this.realityReceiptDate = realityReceiptDate;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getCentreName() {
		return centreName;
	}

	public void setCentreName(String centreName) {
		this.centreName = centreName;
	}

	public String getSmallName() {
		return smallName;
	}

	public void setSmallName(String smallName) {
		this.smallName = smallName;
	}

	public String getDetailName() {
		return detailName;
	}

	public void setDetailName(String detailName) {
		this.detailName = detailName;
	}

	public String getDxRoleName() {
		return dxRoleName;
	}

	public void setDxRoleName(String dxRoleName) {
		this.dxRoleName = dxRoleName;
	}

	public String getDlRoleName() {
		return dlRoleName;
	}

	public void setDlRoleName(String dlRoleName) {
		this.dlRoleName = dlRoleName;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	// 获取
	public String getRegionCode() {
		return regionCode;
	}

	// 设置
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}

	// 获取

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
	public BigDecimal getReceiptRationed() {
		return receiptRationed;
	}

	// 设置
	public void setReceiptRationed(BigDecimal receiptRationed) {
		this.receiptRationed = receiptRationed;
	}

	public BigDecimal getReceiptTotalPrice() {
		return receiptTotalPrice;
	}

	public String getRealityReceiptDate() {
		return realityReceiptDate;
	}

}