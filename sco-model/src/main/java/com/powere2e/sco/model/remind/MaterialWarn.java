package com.powere2e.sco.model.remind;
import java.math.BigDecimal;
import java.util.Date;
import com.powere2e.frame.server.model.AppModel;

/**
 * 原料预警记录实体类
 * @author lipengjie
 * @version 1.0
 * @since 2015年7月10日
 */
public class MaterialWarn extends AppModel {
	
	
	private static final long serialVersionUID = -2085524827496261732L;
	private String warnCode;//预警编号
	private Date warnDate;//预警日期
	private String warnType;//预警方式
	private String materialCode;//原料编号
	private String materialRegionCode;//地区编号
	private BigDecimal lastMonthAvgPrice;//上月平均价格
	private BigDecimal qoqMonthAvgPrice;//同环比月均价格
	private BigDecimal qoqChange;//同环比变化幅度
	private BigDecimal skuCount;//关联SKU数
	public String getWarnCode() {
		return warnCode;
	}
	public void setWarnCode(String warnCode) {
		this.warnCode = warnCode;
	}
	public Date getWarnDate() {
		return warnDate;
	}
	public void setWarnDate(Date warnDate) {
		this.warnDate = warnDate;
	}
	public String getWarnType() {
		return warnType;
	}
	public void setWarnType(String warnType) {
		this.warnType = warnType;
	}
	public String getMaterialCode() {
		return materialCode;
	}
	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}
	public String getMaterialRegionCode() {
		return materialRegionCode;
	}
	public void setMaterialRegionCode(String materialRegionCode) {
		this.materialRegionCode = materialRegionCode;
	}
	public BigDecimal getLastMonthAvgPrice() {
		return lastMonthAvgPrice;
	}
	public void setLastMonthAvgPrice(BigDecimal lastMonthAvgPrice) {
		this.lastMonthAvgPrice = lastMonthAvgPrice;
	}
	public BigDecimal getQoqMonthAvgPrice() {
		return qoqMonthAvgPrice;
	}
	public void setQoqMonthAvgPrice(BigDecimal qoqMonthAvgPrice) {
		this.qoqMonthAvgPrice = qoqMonthAvgPrice;
	}
	public BigDecimal getQoqChange() {
		return qoqChange;
	}
	public void setQoqChange(BigDecimal qoqChange) {
		this.qoqChange = qoqChange;
	}
	public BigDecimal getSkuCount() {
		return skuCount;
	}
	public void setSkuCount(BigDecimal skuCount) {
		this.skuCount = skuCount;
	}

}