package com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.selldetail;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 销售明细实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class SellDetail extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4330232768839103814L;
	private String merchandiseCode;// 商品编码
	private String merchandiseName;// 商品名称
	private String wlType;// 物料类型
	private String netWeight;// 物料类型
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String centreTypeCode;// 中分类
	private String smallTypeCode;// 小分类
	private String detailTypeCode;// 明细类
	private String fineTypeCode;// 细分类
	private String centreName;// 中分类名称
	private String smallName;// 小分类名称
	private String detailName;// 明细类名称
	private String fineName;// 细分类名称
	private String dxRoleCode;// 定性角色code
	private String dxRoleName;// 定性角色名称
	private String dlRoleCode;// 定量角色code
	private String dlRoleName;// 定量角色名称
	private String sellRegion;// 地区编号
	private String purchaseDepartments;//
	private String sellDate;// 销售日期
	private String maxDate;// 档期最大日期
	private String minDate;// 档期最小日期
	private String maxDates;// 档期最大日期
	private String minDates;// 档期最小日期
	private Integer sellPrice;// 销售价格
	private BigDecimal sellQuantity;// 销量
	private BigDecimal sellTotalPrice;// 销售额
	private BigDecimal sellProfit;// 毛利额
	private BigDecimal psdSellQuantity;// PSD销量
	private BigDecimal psdSellTotalPrice;// PSD销售额
	private BigDecimal psdSellProfit;// PSD毛利额
	private BigDecimal sellQuantityProportionM;// 销售量占比(占所有商品)
	private BigDecimal sellTotalPriceProportionM;// 销售额占比(占所有商品)
	private BigDecimal sellProfitProportionM;// 毛利额占比(占所有商品)
	private BigDecimal sellQuantityProportionS;// 销售量占比(占所有小分类)
	private BigDecimal sellTotalPriceProportionS;// 销售额占比(占所有小分类)
	private BigDecimal sellProfitProportionS;// 毛利额占比(占所有小分类)
	private BigDecimal sellQuantityProportionD;// 销售量占比(占所有明细类)
	private BigDecimal sellTotalPriceProportionD;// 销售饿占比(占所有明细类)
	private BigDecimal sellProfitProportionD;// 毛利额占比(占所有明细类)
	private Integer sellStoreQuantity;// 销售店天数
	private Integer permissionStoreQuantity;// 权限店天数
	private String searchType;// 定量非定量
	private String searchTable;// 直营加盟
	private BigDecimal active;// 活跃度
	private Integer day;// 天数
	private Integer sumStore;// 门店数
	private Integer storeA;// a门店数
	private Integer storeB;// b门店数
	private Integer storeC;// c门店数
	private Integer storeD;// d门店数

	public BigDecimal getPsdSellQuantity() {
		return psdSellQuantity;
	}

	public void setPsdSellQuantity(BigDecimal psdSellQuantity) {
		this.psdSellQuantity = psdSellQuantity;
	}

	public String getMaxDates() {
		return maxDates;
	}

	public void setMaxDates(String maxDates) {
		this.maxDates = maxDates;
	}

	public String getMinDates() {
		return minDates;
	}

	public void setMinDates(String minDates) {
		this.minDates = minDates;
	}

	public String getNetWeight() {
		return netWeight;
	}

	public void setNetWeight(String netWeight) {
		this.netWeight = netWeight;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public Integer getStoreA() {
		return storeA;
	}

	public void setStoreA(Integer storeA) {
		this.storeA = storeA;
	}

	public Integer getStoreB() {
		return storeB;
	}

	public void setStoreB(Integer storeB) {
		this.storeB = storeB;
	}

	public Integer getStoreC() {
		return storeC;
	}

	public void setStoreC(Integer storeC) {
		this.storeC = storeC;
	}

	public Integer getStoreD() {
		return storeD;
	}

	public void setStoreD(Integer storeD) {
		this.storeD = storeD;
	}

	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}

	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
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

	public String getSearchTable() {
		return searchTable;
	}

	public void setSearchTable(String searchTable) {
		this.searchTable = searchTable;
	}

	public Integer getPermissionStoreQuantity() {
		return permissionStoreQuantity;
	}

	public void setPermissionStoreQuantity(Integer permissionStoreQuantity) {
		this.permissionStoreQuantity = permissionStoreQuantity;
	}

	public BigDecimal getActive() {
		return active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getSumStore() {
		return sumStore;
	}

	public void setSumStore(Integer sumStore) {
		this.sumStore = sumStore;
	}

	// 获取
	public String getMerchandiseCode() {
		return merchandiseCode;
	}

	// 设置
	public void setMerchandiseCode(String merchandiseCode) {
		this.merchandiseCode = merchandiseCode.trim();
	}

	// 获取
	public String getSupplierCode() {
		return supplierCode;
	}

	// 设置
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode.trim();
	}

	// 获取
	public String getSellRegion() {
		return sellRegion;
	}

	// 设置
	public void setSellRegion(String sellRegion) {
		this.sellRegion = sellRegion;
	}

	public String getSellDate() {
		return sellDate;
	}

	public void setSellDate(String sellDate) {
		this.sellDate = sellDate;
	}

	public String getMerchandiseName() {
		return merchandiseName;
	}

	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName.trim();
	}

	public String getWlType() {
		return wlType;
	}

	public void setWlType(String wlType) {
		this.wlType = wlType;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName.trim();
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

	// 获取
	public Integer getSellPrice() {
		return sellPrice;
	}

	// 设置
	public void setSellPrice(Integer sellPrice) {
		this.sellPrice = sellPrice;
	}

	// 获取
	public BigDecimal getSellQuantity() {
		return sellQuantity;
	}

	// 设置
	public void setSellQuantity(BigDecimal sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public BigDecimal getSellTotalPrice() {
		return sellTotalPrice;
	}

	public void setSellTotalPrice(BigDecimal sellTotalPrice) {
		this.sellTotalPrice = sellTotalPrice;
	}

	public BigDecimal getSellProfit() {
		return sellProfit;
	}

	public void setSellProfit(BigDecimal sellProfit) {
		this.sellProfit = sellProfit;
	}

	public BigDecimal getPsdSellTotalPrice() {
		return psdSellTotalPrice;
	}

	public void setPsdSellTotalPrice(BigDecimal psdSellTotalPrice) {
		this.psdSellTotalPrice = psdSellTotalPrice;
	}

	public BigDecimal getPsdSellProfit() {
		return psdSellProfit;
	}

	public void setPsdSellProfit(BigDecimal psdSellProfit) {
		this.psdSellProfit = psdSellProfit;
	}

	public BigDecimal getSellQuantityProportionM() {
		return sellQuantityProportionM;
	}

	public void setSellQuantityProportionM(BigDecimal sellQuantityProportionM) {
		this.sellQuantityProportionM = sellQuantityProportionM;
	}

	public BigDecimal getSellTotalPriceProportionM() {
		return sellTotalPriceProportionM;
	}

	public void setSellTotalPriceProportionM(BigDecimal sellTotalPriceProportionM) {
		this.sellTotalPriceProportionM = sellTotalPriceProportionM;
	}

	public BigDecimal getSellProfitProportionM() {
		return sellProfitProportionM;
	}

	public void setSellProfitProportionM(BigDecimal sellProfitProportionM) {
		this.sellProfitProportionM = sellProfitProportionM;
	}

	public BigDecimal getSellQuantityProportionS() {
		return sellQuantityProportionS;
	}

	public void setSellQuantityProportionS(BigDecimal sellQuantityProportionS) {
		this.sellQuantityProportionS = sellQuantityProportionS;
	}

	public BigDecimal getSellTotalPriceProportionS() {
		return sellTotalPriceProportionS;
	}

	public void setSellTotalPriceProportionS(BigDecimal sellTotalPriceProportionS) {
		this.sellTotalPriceProportionS = sellTotalPriceProportionS;
	}

	public BigDecimal getSellProfitProportionS() {
		return sellProfitProportionS;
	}

	public void setSellProfitProportionS(BigDecimal sellProfitProportionS) {
		this.sellProfitProportionS = sellProfitProportionS;
	}

	public BigDecimal getSellQuantityProportionD() {
		return sellQuantityProportionD;
	}

	public void setSellQuantityProportionD(BigDecimal sellQuantityProportionD) {
		this.sellQuantityProportionD = sellQuantityProportionD;
	}

	public BigDecimal getSellTotalPriceProportionD() {
		return sellTotalPriceProportionD;
	}

	public void setSellTotalPriceProportionD(BigDecimal sellTotalPriceProportionD) {
		this.sellTotalPriceProportionD = sellTotalPriceProportionD;
	}

	public BigDecimal getSellProfitProportionD() {
		return sellProfitProportionD;
	}

	public void setSellProfitProportionD(BigDecimal sellProfitProportionD) {
		this.sellProfitProportionD = sellProfitProportionD;
	}

	// 获取
	public Integer getSellStoreQuantity() {
		return sellStoreQuantity;
	}

	// 设置
	public void setSellStoreQuantity(Integer sellStoreQuantity) {
		this.sellStoreQuantity = sellStoreQuantity;
	}

}