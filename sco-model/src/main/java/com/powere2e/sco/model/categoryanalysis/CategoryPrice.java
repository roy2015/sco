package com.powere2e.sco.model.categoryanalysis;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.powere2e.frame.server.model.AppModel;

/**
 * 商品价格带 实体类
 * 
 * @author Gavillen.Zhou
 * @since 2015年05月28日
 * @version 1.0
 */
public class CategoryPrice extends AppModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 31469762251326794L;
	private String regionCode;// 地区编号
	private String regionName;// 地区名称
	private String merchandiseCode;// 商品编号
	private String merchandiseName;// 商品名称
	private String supplierCode;// 供应商编号
	private String supplierName;// 供应商名称
	private String dxRoleCode;// 定性角色编号
	private String dlRoleCode;// 定量角色编号
	private String dxRoleName;// 定性角色名称
	private String dlRoleName;// 定量绝色名称
	private String rationed;// 是否定量
	private String purchaseDepartments;// 
	private String centreTypeCode;// 中分类编码
	private String centreTypeName;// 中分类名称
	private String smallTypeCode;// 小分类编码
	private String smallTypeName;// 小分类名称
	private String detailTypeCode;// 明细类编码
	private String detailTypeName;// 明细类名称
	private String fineTypeCode;//细分类
	private BigDecimal powerCount;// 权限数
	private BigDecimal powerPercent;// 权限开通率
	private BigDecimal aCount;// A类门店个数
	private BigDecimal bCount;// B类门店个数
	private BigDecimal cCount;// C类门店个数
	private BigDecimal dCount;// D类门店个数
	private BigDecimal actSoldStorCount;// 实际销售门店数
	private BigDecimal actSoldStorPercent;// 实际占权限门店比
	private BigDecimal bucketPercent;// 格斗占比
	private BigDecimal volumePercent;// 
	private BigDecimal valPercent;//
	
	
	private Date sellMinDate;// 开始日期
	private Date sellMaxDate;// 结束日期
	private String dateRegionStr;// 日期过滤
	private String dateRegionStr2;// 日期过滤
	private String netWeightType;// 定量或非定量装
	private String dataType;// 数据来源(直营D、加盟J、所有A)
	
	private BigDecimal regionStoreSum;// 地区门店总数
	private BigDecimal regionSKUSum;// 地区SKU总数
	private String regionPrice;// 地区价格带
	private BigDecimal skuUnits;// SKU数量
	private BigDecimal skuPercent;// SKU数量占比
	private BigDecimal soldUnits;// 销量
	private BigDecimal soldPrice;// 销售金额
	private BigDecimal grossProfit;// 毛利额
	private BigDecimal soldPercent;// 销售占比
	private BigDecimal soldPricePercent;// 销量金额占比
	private BigDecimal grossProfitPenrcent;// 毛利额占比
	private BigDecimal powerShopDay;// 权限店天
	private BigDecimal soldShopDay;//销售店天
	private BigDecimal vitality;// 活跃度
	private BigDecimal powerShopDayPercent;// 权限店天占比
	private BigDecimal soldShopPercent;// 销售店天占比
	private BigDecimal psdUnits;// PSD销量
	private BigDecimal psdSoldPrice;// PSD销售金额
	private BigDecimal psdGrossProfit;// PSD毛利额
	private BigDecimal notLessPsdUnitsSku;// 大于等于PSD销量的SKU
	private BigDecimal lessPsdUnitsSku;// 小于PSD销量的SKU
	private BigDecimal notLessPsdSoldPrice;// 大于等于PSD销售额的SKU数
	private BigDecimal lessPsdSoldPrice;// 小于PSD销售额的SKU数
	private BigDecimal notLessPsdGrossProfit;// 大于等于PSD毛利额的SKU数
	private BigDecimal lessPsdGrossProfit;// 小于PSD毛利额的SKU数
	
	/**
	 * 价格带[千分符,两位小数]
	 * 
	 * @return
	 */
	public String getRegionPriceStr() {
		if (StringUtils.isBlank(regionPrice)) return regionPrice;
		if (regionPrice.indexOf("-") >= 0) {//如果是A-B这种格式
			String[] arr = regionPrice.split("-"); 
			if (arr.length > 1) {
				arr[0] = this.roundNumber(arr[0], 2);
				arr[1] = this.roundNumber(arr[1], 2);
			}
			return StringUtils.join(arr, "-");
		} else if (regionPrice.indexOf(">") >= 0 && regionPrice.length() > 1) { //如果是 >A这种格式
			return ">" + this.roundNumber(regionPrice.substring(1), 2);
		} else {
			return this.roundNumber(regionPrice, 2);
		}
	}	
		
	/**
	 * 保留指定位数的小数,不足补零
	 * 
	 * @param value
	 *            值
	 * @param precision
	 *            精度
	 * @return 结果 如果异常返回空的字符串
	 */
	private String roundNumber(String value, int precision) {
		if (value == null) {
			return "";
		} else {
			try {
				NumberFormat formatter = NumberFormat.getNumberInstance();
				formatter.setMinimumFractionDigits(precision);
				formatter.setMaximumFractionDigits(precision);
				return formatter.format(Double.parseDouble(value.toString()));
			} catch (Exception e) {
				throw e;
			}
		}
	}
		
	public String getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(String regionCode) {
		this.regionCode = regionCode;
	}
	public String getRegionName() {
		return regionName;
	}
	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}
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
	public String getNetWeightType() {
		return netWeightType;
	}
	public void setNetWeightType(String netWeightType) {
		this.netWeightType = netWeightType;
	}
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
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
	public String getPurchaseDepartments() {
		return purchaseDepartments;
	}
	public void setPurchaseDepartments(String purchaseDepartments) {
		this.purchaseDepartments = purchaseDepartments;
	}
	public BigDecimal getPowerCount() {
		return powerCount;
	}
	public void setPowerCount(BigDecimal powerCount) {
		this.powerCount = powerCount;
	}
	public BigDecimal getPowerPercent() {
		return powerPercent;
	}
	public void setPowerPercent(BigDecimal powerPercent) {
		this.powerPercent = powerPercent;
	}
	public BigDecimal getaCount() {
		return aCount;
	}
	public void setaCount(BigDecimal aCount) {
		this.aCount = aCount;
	}
	public BigDecimal getbCount() {
		return bCount;
	}
	public void setbCount(BigDecimal bCount) {
		this.bCount = bCount;
	}
	public BigDecimal getcCount() {
		return cCount;
	}
	public void setcCount(BigDecimal cCount) {
		this.cCount = cCount;
	}
	public BigDecimal getdCount() {
		return dCount;
	}
	public void setdCount(BigDecimal dCount) {
		this.dCount = dCount;
	}
	public BigDecimal getActSoldStorCount() {
		return actSoldStorCount;
	}
	public void setActSoldStorCount(BigDecimal actSoldStorCount) {
		this.actSoldStorCount = actSoldStorCount;
	}
	public BigDecimal getActSoldStorPercent() {
		return actSoldStorPercent;
	}
	public void setActSoldStorPercent(BigDecimal actSoldStorPercent) {
		this.actSoldStorPercent = actSoldStorPercent;
	}
	public BigDecimal getBucketPercent() {
		return bucketPercent;
	}
	public void setBucketPercent(BigDecimal bucketPercent) {
		this.bucketPercent = bucketPercent;
	}
	public BigDecimal getVolumePercent() {
		return volumePercent;
	}
	public void setVolumePercent(BigDecimal volumePercent) {
		this.volumePercent = volumePercent;
	}
	public BigDecimal getValPercent() {
		return valPercent;
	}
	public void setValPercent(BigDecimal valPercent) {
		this.valPercent = valPercent;
	}
	public Date getSellMinDate() {
		return sellMinDate;
	}
	public void setSellMinDate(Date sellMinDate) {
		this.sellMinDate = sellMinDate;
	}
	public Date getSellMaxDate() {
		return sellMaxDate;
	}
	public String getDateRegionStr() {
		return dateRegionStr;
	}
	public void setDateRegionStr(String dateRegionStr) {
		this.dateRegionStr = dateRegionStr;
	}
	
	public String getDateRegionStr2() {
		return dateRegionStr2;
	}
	public void setDateRegionStr2(String dateRegionStr2) {
		this.dateRegionStr2 = dateRegionStr2;
	}
	public void setSellMaxDate(Date sellMaxDate) {
		this.sellMaxDate = sellMaxDate;
	}
	public BigDecimal getRegionStoreSum() {
		return regionStoreSum;
	}
	public void setRegionStoreSum(BigDecimal regionStoreSum) {
		this.regionStoreSum = regionStoreSum;
	}
	public BigDecimal getRegionSKUSum() {
		return regionSKUSum;
	}
	public void setRegionSKUSum(BigDecimal regionSKUSum) {
		this.regionSKUSum = regionSKUSum;
	}
	public String getRegionPrice() {
		return regionPrice;
	}
	public void setRegionPrice(String regionPrice) {
		this.regionPrice = regionPrice;
	}
	public BigDecimal getSkuUnits() {
		return skuUnits;
	}
	public void setSkuUnits(BigDecimal skuUnits) {
		this.skuUnits = skuUnits;
	}
	public BigDecimal getSkuPercent() {
		return skuPercent;
	}
	public void setSkuPercent(BigDecimal skuPercent) {
		this.skuPercent = skuPercent;
	}
	public BigDecimal getSoldUnits() {
		return soldUnits;
	}
	public void setSoldUnits(BigDecimal soldUnits) {
		this.soldUnits = soldUnits;
	}
	public BigDecimal getSoldPrice() {
		return soldPrice;
	}
	public void setSoldPrice(BigDecimal soldPrice) {
		this.soldPrice = soldPrice;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	public BigDecimal getSoldPercent() {
		return soldPercent;
	}
	public void setSoldPercent(BigDecimal soldPercent) {
		this.soldPercent = soldPercent;
	}
	public BigDecimal getSoldPricePercent() {
		return soldPricePercent;
	}
	public void setSoldPricePercent(BigDecimal soldPricePercent) {
		this.soldPricePercent = soldPricePercent;
	}
	public BigDecimal getGrossProfitPenrcent() {
		return grossProfitPenrcent;
	}
	public void setGrossProfitPenrcent(BigDecimal grossProfitPenrcent) {
		this.grossProfitPenrcent = grossProfitPenrcent;
	}
	public BigDecimal getPowerShopDay() {
		return powerShopDay;
	}
	public void setPowerShopDay(BigDecimal powerShopDay) {
		this.powerShopDay = powerShopDay;
	}
	public BigDecimal getSoldShopDay() {
		return soldShopDay;
	}
	public void setSoldShopDay(BigDecimal soldShopDay) {
		this.soldShopDay = soldShopDay;
	}
	public BigDecimal getVitality() {
		return vitality;
	}
	public void setVitality(BigDecimal vitality) {
		this.vitality = vitality;
	}
	public BigDecimal getPowerShopDayPercent() {
		return powerShopDayPercent;
	}
	public void setPowerShopDayPercent(BigDecimal powerShopDayPercent) {
		this.powerShopDayPercent = powerShopDayPercent;
	}
	public BigDecimal getSoldShopPercent() {
		return soldShopPercent;
	}
	public void setSoldShopPercent(BigDecimal soldShopPercent) {
		this.soldShopPercent = soldShopPercent;
	}
	public BigDecimal getPsdUnits() {
		return psdUnits;
	}
	public void setPsdUnits(BigDecimal psdUnits) {
		this.psdUnits = psdUnits;
	}
	public BigDecimal getPsdSoldPrice() {
		return psdSoldPrice;
	}
	public void setPsdSoldPrice(BigDecimal psdSoldPrice) {
		this.psdSoldPrice = psdSoldPrice;
	}
	public BigDecimal getPsdGrossProfit() {
		return psdGrossProfit;
	}
	public void setPsdGrossProfit(BigDecimal psdGrossProfit) {
		this.psdGrossProfit = psdGrossProfit;
	}
	public BigDecimal getNotLessPsdUnitsSku() {
		return notLessPsdUnitsSku;
	}
	public void setNotLessPsdUnitsSku(BigDecimal notLessPsdUnitsSku) {
		this.notLessPsdUnitsSku = notLessPsdUnitsSku;
	}
	public BigDecimal getLessPsdUnitsSku() {
		return lessPsdUnitsSku;
	}
	public void setLessPsdUnitsSku(BigDecimal lessPsdUnitsSku) {
		this.lessPsdUnitsSku = lessPsdUnitsSku;
	}
	public BigDecimal getNotLessPsdSoldPrice() {
		return notLessPsdSoldPrice;
	}
	public void setNotLessPsdSoldPrice(BigDecimal notLessPsdSoldPrice) {
		this.notLessPsdSoldPrice = notLessPsdSoldPrice;
	}
	public BigDecimal getLessPsdSoldPrice() {
		return lessPsdSoldPrice;
	}
	public void setLessPsdSoldPrice(BigDecimal lessPsdSoldPrice) {
		this.lessPsdSoldPrice = lessPsdSoldPrice;
	}
	public BigDecimal getNotLessPsdGrossProfit() {
		return notLessPsdGrossProfit;
	}
	public void setNotLessPsdGrossProfit(BigDecimal notLessPsdGrossProfit) {
		this.notLessPsdGrossProfit = notLessPsdGrossProfit;
	}
	public BigDecimal getLessPsdGrossProfit() {
		return lessPsdGrossProfit;
	}
	public void setLessPsdGrossProfit(BigDecimal lessPsdGrossProfit) {
		this.lessPsdGrossProfit = lessPsdGrossProfit;
	}
	
}