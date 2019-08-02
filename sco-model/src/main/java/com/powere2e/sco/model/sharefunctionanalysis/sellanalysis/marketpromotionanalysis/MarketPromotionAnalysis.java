package com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 公司大盘促销分析实体类
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MarketPromotionAnalysis extends AppModel {
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
	private String dxRoleCode;// 定性角色code
	private String dxRoleName;// 定性角色名称
	private String dlRoleCode;// 定量角色code
	private String dlRoleName;// 定量角色名称
	private String sellRegion;// 地区
	private String sellRegionName;// 地区名称
	private String sellDate;// 销售日期
	private String time;// 时间
	private String maxDate;// 促销档期最大日期
	private String minDate;// 促销档期最小日期
	private String maxDates;// 促销档期最大日期
	private String minDates;// 促销档期最小日期
	private BigDecimal sellPrice;// 销售价格
	private BigDecimal sku;// sku数
	private BigDecimal sellQuantity;// 销量
	private BigDecimal sellTotalPrice;// 销售额
	private BigDecimal sellProfit;// 毛利额
	private BigDecimal psdSellQuantity;// PSD销量
	private BigDecimal psdSellTotalPrice;// PSD销售额
	private BigDecimal psdSellProfit;// PSD毛利额
	private BigDecimal sellQuantityS;// 销售量（小分类）
	private BigDecimal sellTotalPriceS;// 销售额（小分类）
	private BigDecimal sellProfitS;// 毛利额（小分类）
	private BigDecimal sellQuantityPS;// 平均销售量（小分类）
	private BigDecimal sellTotalPricePS;// 平均销售额（小分类）
	private BigDecimal sellProfitPS;// 平均毛利额（小分类）
	private BigDecimal psdSellQuantityS;// psd销售量（小分类）
	private BigDecimal psdSellTotalPriceS;// psd销售额（小分类）
	private BigDecimal psdSellProfitS;// psd毛利额（小分类）
	private BigDecimal sellQuantityD;// 销售量（明细类）
	private BigDecimal sellTotalPriceD;// 销售额（明细类）
	private BigDecimal sellProfitD;// 毛利额（明细类）
	private BigDecimal sellQuantityPD;// 平均销售量（明细类）
	private BigDecimal sellTotalPricePD;// 平均销售额（明细类）
	private BigDecimal sellProfitPD;// 平均毛利额（明细类）
	private BigDecimal psdSellQuantityD;// psd销售量（明细类）
	private BigDecimal psdSellTotalPriceD;// psd销售额（明细类）
	private BigDecimal psdSellProfitD;// psd毛利额（明细类）
	private BigDecimal sellQuantityA;// 销售量（所有）
	private BigDecimal sellTotalPriceA;// 销售额（所有）
	private BigDecimal sellProfitA;// 毛利额（所有）
	private BigDecimal sellQuantityPA;// 平均销售量（所有）
	private BigDecimal sellTotalPricePA;// 平均销售额（所有）
	private BigDecimal sellProfitPA;// 平均毛利额（所有）
	private BigDecimal psdSellQuantityA;// psd销售量（所有）
	private BigDecimal psdSellTotalPriceA;// psd销售额（所有）
	private BigDecimal psdSellProfitA;// psd毛利额（所有）
	private BigDecimal sellQuantityProportionM;// 销售量占比(占所有商品)
	private BigDecimal sellTotalPriceProportionM;// 销售额占比(占所有商品)
	private BigDecimal sellProfitProportionM;// 毛利额占比(占所有商品)
	private BigDecimal sellQuantityProportionS;// 销售量占比(占所有小分类)
	private BigDecimal sellTotalPriceProportionS;// 销售额占比(占所有小分类)
	private BigDecimal sellProfitProportionS;// 毛利额占比(占所有小分类)
	private BigDecimal sellQuantityProportionD;// 销售量占比(占所有明细类)
	private BigDecimal sellTotalPriceProportionD;// 销售饿占比(占所有明细类)
	private BigDecimal sellProfitProportionD;// 毛利额占比(占所有明细类)
	private BigDecimal sellStoreQuantity;// 销售店天数
	private BigDecimal permissionStoreQuantity;// 权限店天数
	private String searchType;// 定量非定量
	private String searchTable;// 直营加盟
	private BigDecimal active;// 活跃度
	private String bfb;// 是否显示%
	private Integer dayS;
	private Integer dayD;
	private Integer dayA;

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public BigDecimal getSellQuantity() {
		return sellQuantity;
	}

	public void setSellQuantity(BigDecimal sellQuantity) {
		this.sellQuantity = sellQuantity;
	}

	public BigDecimal getSellQuantityS() {
		return sellQuantityS;
	}

	public void setSellQuantityS(BigDecimal sellQuantityS) {
		this.sellQuantityS = sellQuantityS;
	}

	public BigDecimal getSellQuantityPS() {
		return sellQuantityPS;
	}

	public void setSellQuantityPS(BigDecimal sellQuantityPS) {
		this.sellQuantityPS = sellQuantityPS;
	}

	public BigDecimal getSellQuantityD() {
		return sellQuantityD;
	}

	public void setSellQuantityD(BigDecimal sellQuantityD) {
		this.sellQuantityD = sellQuantityD;
	}

	public BigDecimal getSellQuantityPD() {
		return sellQuantityPD;
	}

	public void setSellQuantityPD(BigDecimal sellQuantityPD) {
		this.sellQuantityPD = sellQuantityPD;
	}

	public BigDecimal getSellQuantityA() {
		return sellQuantityA;
	}

	public void setSellQuantityA(BigDecimal sellQuantityA) {
		this.sellQuantityA = sellQuantityA;
	}

	public BigDecimal getSellQuantityPA() {
		return sellQuantityPA;
	}

	public void setSellQuantityPA(BigDecimal sellQuantityPA) {
		this.sellQuantityPA = sellQuantityPA;
	}

	public Integer getDayS() {
		return dayS;
	}

	public void setDayS(Integer dayS) {
		this.dayS = dayS;
	}

	public Integer getDayD() {
		return dayD;
	}

	public void setDayD(Integer dayD) {
		this.dayD = dayD;
	}

	public Integer getDayA() {
		return dayA;
	}

	public void setDayA(Integer dayA) {
		this.dayA = dayA;
	}

	public BigDecimal getSellTotalPricePS() {
		return sellTotalPricePS;
	}

	public void setSellTotalPricePS(BigDecimal sellTotalPricePS) {
		this.sellTotalPricePS = sellTotalPricePS;
	}

	public BigDecimal getSellProfitPS() {
		return sellProfitPS;
	}

	public void setSellProfitPS(BigDecimal sellProfitPS) {
		this.sellProfitPS = sellProfitPS;
	}

	public BigDecimal getSellTotalPricePD() {
		return sellTotalPricePD;
	}

	public void setSellTotalPricePD(BigDecimal sellTotalPricePD) {
		this.sellTotalPricePD = sellTotalPricePD;
	}

	public BigDecimal getSellProfitPD() {
		return sellProfitPD;
	}

	public void setSellProfitPD(BigDecimal sellProfitPD) {
		this.sellProfitPD = sellProfitPD;
	}

	public BigDecimal getSellTotalPricePA() {
		return sellTotalPricePA;
	}

	public void setSellTotalPricePA(BigDecimal sellTotalPricePA) {
		this.sellTotalPricePA = sellTotalPricePA;
	}

	public BigDecimal getSellProfitPA() {
		return sellProfitPA;
	}

	public void setSellProfitPA(BigDecimal sellProfitPA) {
		this.sellProfitPA = sellProfitPA;
	}

	public String getSellRegionName() {
		return sellRegionName;
	}

	public void setSellRegionName(String sellRegionName) {
		this.sellRegionName = sellRegionName;
	}

	public String getBfb() {
		return bfb;
	}

	public void setBfb(String bfb) {
		this.bfb = bfb;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getSku() {
		return sku;
	}

	public void setSku(BigDecimal sku) {
		this.sku = sku;
	}

	public BigDecimal getSellTotalPriceS() {
		return sellTotalPriceS;
	}

	public void setSellTotalPriceS(BigDecimal sellTotalPriceS) {
		this.sellTotalPriceS = sellTotalPriceS;
	}

	public BigDecimal getSellProfitS() {
		return sellProfitS;
	}

	public void setSellProfitS(BigDecimal sellProfitS) {
		this.sellProfitS = sellProfitS;
	}

	public BigDecimal getPsdSellQuantityS() {
		return psdSellQuantityS;
	}

	public void setPsdSellQuantityS(BigDecimal psdSellQuantityS) {
		this.psdSellQuantityS = psdSellQuantityS;
	}

	public BigDecimal getPsdSellTotalPriceS() {
		return psdSellTotalPriceS;
	}

	public void setPsdSellTotalPriceS(BigDecimal psdSellTotalPriceS) {
		this.psdSellTotalPriceS = psdSellTotalPriceS;
	}

	public BigDecimal getPsdSellProfitS() {
		return psdSellProfitS;
	}

	public void setPsdSellProfitS(BigDecimal psdSellProfitS) {
		this.psdSellProfitS = psdSellProfitS;
	}

	public BigDecimal getSellTotalPriceD() {
		return sellTotalPriceD;
	}

	public void setSellTotalPriceD(BigDecimal sellTotalPriceD) {
		this.sellTotalPriceD = sellTotalPriceD;
	}

	public BigDecimal getSellProfitD() {
		return sellProfitD;
	}

	public void setSellProfitD(BigDecimal sellProfitD) {
		this.sellProfitD = sellProfitD;
	}

	public BigDecimal getPsdSellQuantityD() {
		return psdSellQuantityD;
	}

	public void setPsdSellQuantityD(BigDecimal psdSellQuantityD) {
		this.psdSellQuantityD = psdSellQuantityD;
	}

	public BigDecimal getPsdSellTotalPriceD() {
		return psdSellTotalPriceD;
	}

	public void setPsdSellTotalPriceD(BigDecimal psdSellTotalPriceD) {
		this.psdSellTotalPriceD = psdSellTotalPriceD;
	}

	public BigDecimal getPsdSellProfitD() {
		return psdSellProfitD;
	}

	public void setPsdSellProfitD(BigDecimal psdSellProfitD) {
		this.psdSellProfitD = psdSellProfitD;
	}

	public BigDecimal getSellTotalPriceA() {
		return sellTotalPriceA;
	}

	public void setSellTotalPriceA(BigDecimal sellTotalPriceA) {
		this.sellTotalPriceA = sellTotalPriceA;
	}

	public BigDecimal getSellProfitA() {
		return sellProfitA;
	}

	public void setSellProfitA(BigDecimal sellProfitA) {
		this.sellProfitA = sellProfitA;
	}

	public BigDecimal getPsdSellQuantityA() {
		return psdSellQuantityA;
	}

	public void setPsdSellQuantityA(BigDecimal psdSellQuantityA) {
		this.psdSellQuantityA = psdSellQuantityA;
	}

	public BigDecimal getPsdSellTotalPriceA() {
		return psdSellTotalPriceA;
	}

	public void setPsdSellTotalPriceA(BigDecimal psdSellTotalPriceA) {
		this.psdSellTotalPriceA = psdSellTotalPriceA;
	}

	public BigDecimal getPsdSellProfitA() {
		return psdSellProfitA;
	}

	public void setPsdSellProfitA(BigDecimal psdSellProfitA) {
		this.psdSellProfitA = psdSellProfitA;
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

	public BigDecimal getPermissionStoreQuantity() {
		return permissionStoreQuantity;
	}

	public void setPermissionStoreQuantity(BigDecimal permissionStoreQuantity) {
		this.permissionStoreQuantity = permissionStoreQuantity;
	}

	public BigDecimal getActive() {
		return active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
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
	public BigDecimal getPsdSellQuantity() {
		return psdSellQuantity;
	}

	// 设置
	public void setPsdSellQuantity(BigDecimal psdSellQuantity) {
		this.psdSellQuantity = psdSellQuantity;
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
	public BigDecimal getSellStoreQuantity() {
		return sellStoreQuantity;
	}

	// 设置
	public void setSellStoreQuantity(BigDecimal sellStoreQuantity) {
		this.sellStoreQuantity = sellStoreQuantity;
	}

}