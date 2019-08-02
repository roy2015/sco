package com.powere2e.sco.model.masterdata;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;

/**
 * SAP主数据商品实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月17日
 * @version 1.0
 */
public class Merchandise extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3678711688412137759L;
	private String merchandiseCode;// 商品编码
	private String merchandiseName;// 商品名称
	private String wlType;// 物料类型
	private String merchandiseLevel;// 商品等级
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String purchaseDepartments;// 采购部门

	private String centreType;// 中分类
	private String smallType;// 小分类
	private String detailType;// 明细类
	private String fineType;// 细分类
	private String centreTypeCode;// 中分类
	private String smallTypeCode;// 小分类
	private String detailTypeCode;// 明细类
	private String fineTypeCode;// 细分类
	private String centreName;// 中分类名称
	private String smallName;// 小分类名称
	private String detailName;// 明细类名称
	private String fineName;// 细分类名称

	private String storageForm;// 存储形式
	private BigDecimal sellPrice;// 销售价格
	private String units;// 计量单位
	private String specification;// 商品规格
	private String packSpecification;// 箱规
	private String leastPackForm;// 最小包装形式
	private String moreTaste;// 多口味属性
	private Date upMarketDate;// 上市时间
	private Date downMarketDate;// 下市时间
	private String merchandiseStatus;// 商品状态
	private BigDecimal grossWeight;// 毛重
	private BigDecimal netWeight;// 净重
	private Date syncDate;// 同步时间

	private String dxRoleCode;// 定性角色code
	private String dxRoleName;// 定性角色名称
	private String dlRoleCode;// 定量角色code
	private String dlRoleName;// 定量角色名称

	private String rationed;// 是否定量
	private String purchaseType;// 采购类型
	private String saleType;// 销售方式
	private String applicationCode;// OA申请单号
	private String applicationStatus;// OA申请状态

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode;
	}

	// 获取
	public String getMerchandiseName() {
		return merchandiseName;
	}

	// 设置
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName.replace(" ", "");
	}

	// 获取
	public String getWlType() {
		return wlType;
	}

	// 设置
	public void setWlType(String wlType) {
		this.wlType = wlType;
	}

	// 获取
	public String getMerchandiseLevel() {
		return merchandiseLevel;
	}

	// 设置
	public void setMerchandiseLevel(String merchandiseLevel) {
		this.merchandiseLevel = merchandiseLevel;
	}

	// 获取
	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	// 设置
	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}

	// 获取
	public String getCentreType() {
		return centreType;
	}

	// 设置
	public void setCentreType(String centreType) {
		this.centreType = centreType;
	}

	// 获取
	public String getSmallType() {
		return smallType;
	}

	// 设置
	public void setSmallType(String smallType) {
		this.smallType = smallType;
	}

	// 获取
	public String getDetailType() {
		return detailType;
	}

	// 设置
	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getStorageForm() {
		return storageForm;
	}

	public void setStorageForm(String storageForm) {
		this.storageForm = storageForm;
	}

	// 获取
	public String getUnits() {
		return units;
	}

	// 设置
	public void setUnits(String units) {
		this.units = units;
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
	public String getPackSpecification() {
		return packSpecification;
	}

	// 设置
	public void setPackSpecification(String packSpecification) {
		this.packSpecification = packSpecification;
	}

	// 获取
	public String getLeastPackForm() {
		return leastPackForm;
	}

	// 设置
	public void setLeastPackForm(String leastPackForm) {
		this.leastPackForm = leastPackForm;
	}

	// 获取
	public String getMoreTaste() {
		return moreTaste;
	}

	// 设置
	public void setMoreTaste(String moreTaste) {
		this.moreTaste = moreTaste;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpMarketDate() {
		return upMarketDate;
	}

	// 设置
	public void setUpMarketDate(Date upMarketDate) {
		this.upMarketDate = upMarketDate;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getDownMarketDate() {
		return downMarketDate;
	}

	// 设置
	public void setDownMarketDate(Date downMarketDate) {
		this.downMarketDate = downMarketDate;
	}

	// 获取
	public String getMerchandiseStatus() {
		return merchandiseStatus;
	}

	// 设置
	public void setMerchandiseStatus(String merchandiseStatus) {
		this.merchandiseStatus = merchandiseStatus;
	}

	public Merchandise() {
		super();
	}

	public Merchandise(String merchandiseCode, String dxRoleCode, String dlRoleCode) {
		super();
		this.merchandiseCode = merchandiseCode;
		this.dxRoleCode = dxRoleCode;
		this.dlRoleCode = dlRoleCode;
	}

	public BigDecimal getGrossWeight() {
		return grossWeight;
	}

	public void setGrossWeight(BigDecimal grossWeight) {
		this.grossWeight = grossWeight;
	}

	public BigDecimal getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(BigDecimal netWeight) {
		this.netWeight = netWeight;
	}

	// 获取
	public String getDlRoleCode() {
		return dlRoleCode;
	}

	// 设置
	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}

	// 获取
	public String getDxRoleCode() {
		return dxRoleCode;
	}

	// 设置
	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
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

	public String getFineType() {
		return fineType;
	}

	public void setFineType(String fineType) {
		this.fineType = fineType;
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

	public String getFineName() {
		return fineName;
	}

	public void setFineName(String fineName) {
		this.fineName = fineName;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
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

	public void setDetailTypeCode(String detailTypCode) {
		this.detailTypeCode = detailTypCode;
	}

	public String getFineTypeCode() {
		return fineTypeCode;
	}

	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	public String getRationed() {
		return rationed;
	}

	public void setRationed(String rationed) {
		this.rationed = rationed;
	}

	public String getPurchaseType() {
		return purchaseType;
	}

	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}

	public String getSaleType() {
		return saleType;
	}

	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}

	public String getApplicationCode() {
		return applicationCode;
	}

	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

}