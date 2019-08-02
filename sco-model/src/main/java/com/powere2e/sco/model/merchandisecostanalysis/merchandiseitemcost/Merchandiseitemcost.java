package com.powere2e.sco.model.merchandisecostanalysis.merchandiseitemcost;

import java.math.BigDecimal;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;

/**
 * 商品分项成本类比
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月23日
 */
public class Merchandiseitemcost extends AccountingBo {

	private static final long serialVersionUID = -7374040631126989526L;

	private String companySite; // 工厂所在地
	// 投料明细表的字段
	private String materialCode; // 原料编号
	private String materialType; // 原料类型
	private String materialName; // 原料名称
	private String materialOrigin; // 原料产地
	private String materialBrand; // 原料品牌
	private String materialLevelSpecification; // 原料等级与规格
	private BigDecimal purchasePrice;// 原料采购价格
	private BigDecimal inputCount; // 原料投入量
	private BigDecimal inputCost; // 原料投入成本
	private BigDecimal avgCost; // 平均成品原料成本
	private BigDecimal inputCountProportion; // 投入量占比
	private String moisture; // 成品含水率
	private BigDecimal yield; // 得率(投料总表字段)
	private String ingredientCode; // 投料编号

	private String intentionPurchaseDepartments; // 意向采购部门
	private String elseTypeName; // 意向商品的小分类其他分类名称
	private String intentionSmallTypeCode; // 意向商品的小分类编码
	private String elseName; // 包装其他名称
	private BigDecimal total;

	// 内包装表字段
	private String packagType; // 包装类型
	private String packagName; // 包装名称
	private String npackagName; // 内包装类型
	private String wpackagName; // 外包装类型
	private BigDecimal price; // 报价
	private String units; // 报价计量单位(核算表)
	private String texture; // 具体材质
	private String materialSize; // 尺寸
	private BigDecimal weight; // 单个克重
	private BigDecimal unitsPrice; // 单价
	private BigDecimal kgPrice; // 公斤价格
	private BigDecimal quantity; // 数量
	private String remarks; // 备注
	private BigDecimal weightProportion; // 重量占比
	private String technologyRequirements; // 工艺要求
	// 外包装字段
	private String specification; // 箱规
	private BigDecimal ylUnitsPrice; // 纸箱用料单价
	private String lwh; // 长*宽*高
	private BigDecimal area; // 纸箱用料面积
	private BigDecimal useQuantity; // 使用量
	// 其他成本项
	private String wastageType; // 损耗类型
	private BigDecimal water; // 耗水
	private BigDecimal electricity; // 耗电
	private BigDecimal gas; // 耗气
	private BigDecimal coal; // 耗煤
	private BigDecimal oil; // 耗油
	private BigDecimal totalPrice; // 设备总价
	private BigDecimal ageLimit; // 折旧年限
	private BigDecimal depreciation; // 折旧值
	private BigDecimal capacity; // 产能值
	private BigDecimal manpowerCount; // 车间工人数
	private BigDecimal avgWage; // 平均工资
	private BigDecimal monthCapacity; // 月产量
	private String jtpc; // 均摊偏差
	private BigDecimal unitsWage; // 每kg成品
	private BigDecimal proportion; // 占比
	private BigDecimal sumKm; // 总公里数
	private BigDecimal taxRate; // 税率
	private BigDecimal unitsCost; // 单位成本
	private BigDecimal x001Region; // X001地区价格
	private String oaContacts; // OA申请人
	
	//商品报价
	private String currency; // 币种
	private String paymentType; //付款方式
	private BigDecimal exchangerate; //汇率
	private String referenceDate; //参考日期
	private String referenceBank; //参考银行
	private BigDecimal rmbSettlementPrice; //商品人民币结算价格
	private String rmbSettlementPriceRemarks; //商品人民币结算价格备注
	private String aeRemarks; //汇率备注
	//海运费
	private String  starting; //出发港
	private String  destination; //到达港
	private String  containerType; //货柜类型
	private String  containerSize; //货柜尺寸
	private BigDecimal  unitPrice; //单价（元/货柜）
	private String  containerCapacity; //货柜内容物数量&重量
	private String  computeType; //计算方式
	
	//换单费
	private BigDecimal updateOrderFee; //换单费
	private String updateOrderFeeRemarks;//换单费备注
	
	//保险费
	private BigDecimal premium; //保险费
	private String premiumRemarks; //保险费备注
	
	//报关服务费
	private String customscharges; //报关费
	private String portSurcharge; //港杂费
	private String demurrageCharge; //滞港费
	private String containerDirtynessChange; //污箱费
	private String elseFee; //其他费用
	
	//关税
	private String  hsCode; //HS编码
	private String customsdutiesComputeType; //关税计算方式
	
	//增值税
	private String addedvaluetaxComputeType; //增值税计算方式
	
	public String getOaContacts() {
		return oaContacts;
	}

	public void setOaContacts(String oaContacts) {
		this.oaContacts = oaContacts;
	}

	public String getCompanySite() {
		return companySite;
	}

	public void setCompanySite(String companySite) {
		this.companySite = companySite;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialOrigin() {
		return materialOrigin;
	}

	public void setMaterialOrigin(String materialOrigin) {
		this.materialOrigin = materialOrigin;
	}

	public String getMaterialBrand() {
		return materialBrand;
	}

	public void setMaterialBrand(String materialBrand) {
		this.materialBrand = materialBrand;
	}

	public String getMaterialLevelSpecification() {
		return materialLevelSpecification;
	}

	public void setMaterialLevelSpecification(String materialLevelSpecification) {
		this.materialLevelSpecification = materialLevelSpecification;
	}

	public BigDecimal getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(BigDecimal purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public BigDecimal getInputCount() {
		return inputCount;
	}

	public void setInputCount(BigDecimal inputCount) {
		this.inputCount = inputCount;
	}

	public BigDecimal getInputCost() {
		return inputCost;
	}

	public void setInputCost(BigDecimal inputCost) {
		this.inputCost = inputCost;
	}

	public BigDecimal getAvgCost() {
		return avgCost;
	}

	public void setAvgCost(BigDecimal avgCost) {
		this.avgCost = avgCost;
	}

	public BigDecimal getInputCountProportion() {
		return inputCountProportion;
	}

	public void setInputCountProportion(BigDecimal inputCountProportion) {
		this.inputCountProportion = inputCountProportion;
	}

	public String getMoisture() {
		return moisture;
	}

	public void setMoisture(String moisture) {
		this.moisture = moisture;
	}

	public BigDecimal getYield() {
		return yield;
	}

	public void setYield(BigDecimal yield) {
		this.yield = yield;
	}

	public String getIngredientCode() {
		return ingredientCode;
	}

	public void setIngredientCode(String ingredientCode) {
		this.ingredientCode = ingredientCode;
	}

	public String getIntentionPurchaseDepartments() {
		return intentionPurchaseDepartments;
	}

	public void setIntentionPurchaseDepartments(String intentionPurchaseDepartments) {
		this.intentionPurchaseDepartments = intentionPurchaseDepartments;
	}

	public String getElseTypeName() {
		return elseTypeName;
	}

	public void setElseTypeName(String elseTypeName) {
		this.elseTypeName = elseTypeName;
	}

	public String getIntentionSmallTypeCode() {
		return intentionSmallTypeCode;
	}

	public void setIntentionSmallTypeCode(String intentionSmallTypeCode) {
		this.intentionSmallTypeCode = intentionSmallTypeCode;
	}

	public String getElseName() {
		return elseName;
	}

	public void setElseName(String elseName) {
		this.elseName = elseName;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public String getPackagType() {
		return packagType;
	}

	public void setPackagType(String packagType) {
		this.packagType = packagType;
	}

	public String getPackagName() {
		return packagName;
	}

	public void setPackagName(String packagName) {
		this.packagName = packagName;
	}

	public String getNpackagName() {
		return npackagName;
	}

	public void setNpackagName(String npackagName) {
		this.npackagName = npackagName;
	}

	public String getWpackagName() {
		return wpackagName;
	}

	public void setWpackagName(String wpackagName) {
		this.wpackagName = wpackagName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getTexture() {
		return texture;
	}

	public void setTexture(String texture) {
		this.texture = texture;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getUnitsPrice() {
		return unitsPrice;
	}

	public void setUnitsPrice(BigDecimal unitsPrice) {
		this.unitsPrice = unitsPrice;
	}

	public BigDecimal getKgPrice() {
		return kgPrice;
	}

	public void setKgPrice(BigDecimal kgPrice) {
		this.kgPrice = kgPrice;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public BigDecimal getWeightProportion() {
		return weightProportion;
	}

	public void setWeightProportion(BigDecimal weightProportion) {
		this.weightProportion = weightProportion;
	}

	public String getTechnologyRequirements() {
		return technologyRequirements;
	}

	public void setTechnologyRequirements(String technologyRequirements) {
		this.technologyRequirements = technologyRequirements;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public BigDecimal getYlUnitsPrice() {
		return ylUnitsPrice;
	}

	public void setYlUnitsPrice(BigDecimal ylUnitsPrice) {
		this.ylUnitsPrice = ylUnitsPrice;
	}

	public String getLwh() {
		return lwh;
	}

	public void setLwh(String lwh) {
		this.lwh = lwh;
	}

	public BigDecimal getArea() {
		return area;
	}

	public void setArea(BigDecimal area) {
		this.area = area;
	}

	public BigDecimal getUseQuantity() {
		return useQuantity;
	}

	public void setUseQuantity(BigDecimal useQuantity) {
		this.useQuantity = useQuantity;
	}

	public String getWastageType() {
		return wastageType;
	}

	public void setWastageType(String wastageType) {
		this.wastageType = wastageType;
	}

	public BigDecimal getWater() {
		return water;
	}

	public void setWater(BigDecimal water) {
		this.water = water;
	}

	public BigDecimal getElectricity() {
		return electricity;
	}

	public void setElectricity(BigDecimal electricity) {
		this.electricity = electricity;
	}

	public BigDecimal getGas() {
		return gas;
	}

	public void setGas(BigDecimal gas) {
		this.gas = gas;
	}

	public BigDecimal getCoal() {
		return coal;
	}

	public void setCoal(BigDecimal coal) {
		this.coal = coal;
	}

	public BigDecimal getOil() {
		return oil;
	}

	public void setOil(BigDecimal oil) {
		this.oil = oil;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getAgeLimit() {
		return ageLimit;
	}

	public void setAgeLimit(BigDecimal ageLimit) {
		this.ageLimit = ageLimit;
	}

	public BigDecimal getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(BigDecimal depreciation) {
		this.depreciation = depreciation;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getManpowerCount() {
		return manpowerCount;
	}

	public void setManpowerCount(BigDecimal manpowerCount) {
		this.manpowerCount = manpowerCount;
	}

	public BigDecimal getAvgWage() {
		return avgWage;
	}

	public void setAvgWage(BigDecimal avgWage) {
		this.avgWage = avgWage;
	}

	public BigDecimal getMonthCapacity() {
		return monthCapacity;
	}

	public void setMonthCapacity(BigDecimal monthCapacity) {
		this.monthCapacity = monthCapacity;
	}

	public String getJtpc() {
		return jtpc;
	}

	public void setJtpc(String jtpc) {
		this.jtpc = jtpc;
	}

	public BigDecimal getUnitsWage() {
		return unitsWage;
	}

	public void setUnitsWage(BigDecimal unitsWage) {
		this.unitsWage = unitsWage;
	}

	public BigDecimal getProportion() {
		return proportion;
	}

	public void setProportion(BigDecimal proportion) {
		this.proportion = proportion;
	}

	public BigDecimal getSumKm() {
		return sumKm;
	}

	public void setSumKm(BigDecimal sumKm) {
		this.sumKm = sumKm;
	}

	public BigDecimal getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(BigDecimal taxRate) {
		this.taxRate = taxRate;
	}

	public BigDecimal getUnitsCost() {
		return unitsCost;
	}

	public void setUnitsCost(BigDecimal unitsCost) {
		this.unitsCost = unitsCost;
	}

	public BigDecimal getX001Region() {
		return x001Region;
	}

	public void setX001Region(BigDecimal x001Region) {
		this.x001Region = x001Region;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public BigDecimal getExchangerate() {
		return exchangerate;
	}

	public void setExchangerate(BigDecimal exchangerate) {
		this.exchangerate = exchangerate;
	}

	public String getReferenceDate() {
		return referenceDate;
	}

	public void setReferenceDate(String referenceDate) {
		this.referenceDate = referenceDate;
	}

	public String getReferenceBank() {
		return referenceBank;
	}

	public void setReferenceBank(String referenceBank) {
		this.referenceBank = referenceBank;
	}

	public BigDecimal getRmbSettlementPrice() {
		return rmbSettlementPrice;
	}

	public void setRmbSettlementPrice(BigDecimal rmbSettlementPrice) {
		this.rmbSettlementPrice = rmbSettlementPrice;
	}

	public String getRmbSettlementPriceRemarks() {
		return rmbSettlementPriceRemarks;
	}

	public void setRmbSettlementPriceRemarks(String rmbSettlementPriceRemarks) {
		this.rmbSettlementPriceRemarks = rmbSettlementPriceRemarks;
	}

	public String getStarting() {
		return starting;
	}

	public void setStarting(String starting) {
		this.starting = starting;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public String getContainerType() {
		return containerType;
	}

	public void setContainerType(String containerType) {
		this.containerType = containerType;
	}

	public String getContainerSize() {
		return containerSize;
	}

	public void setContainerSize(String containerSize) {
		this.containerSize = containerSize;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}

	public String getContainerCapacity() {
		return containerCapacity;
	}

	public void setContainerCapacity(String containerCapacity) {
		this.containerCapacity = containerCapacity;
	}

	public String getComputeType() {
		return computeType;
	}

	public void setComputeType(String computeType) {
		this.computeType = computeType;
	}

	public BigDecimal getUpdateOrderFee() {
		return updateOrderFee;
	}

	public void setUpdateOrderFee(BigDecimal updateOrderFee) {
		this.updateOrderFee = updateOrderFee;
	}

	public String getUpdateOrderFeeRemarks() {
		return updateOrderFeeRemarks;
	}

	public void setUpdateOrderFeeRemarks(String updateOrderFeeRemarks) {
		this.updateOrderFeeRemarks = updateOrderFeeRemarks;
	}

	public BigDecimal getPremium() {
		return premium;
	}

	public void setPremium(BigDecimal premium) {
		this.premium = premium;
	}

	public String getPremiumRemarks() {
		return premiumRemarks;
	}

	public void setPremiumRemarks(String premiumRemarks) {
		this.premiumRemarks = premiumRemarks;
	}

	public String getCustomscharges() {
		return customscharges;
	}

	public void setCustomscharges(String customscharges) {
		this.customscharges = customscharges;
	}

	public String getPortSurcharge() {
		return portSurcharge;
	}

	public void setPortSurcharge(String portSurcharge) {
		this.portSurcharge = portSurcharge;
	}

	public String getDemurrageCharge() {
		return demurrageCharge;
	}

	public void setDemurrageCharge(String demurrageCharge) {
		this.demurrageCharge = demurrageCharge;
	}

	public String getContainerDirtynessChange() {
		return containerDirtynessChange;
	}

	public void setContainerDirtynessChange(String containerDirtynessChange) {
		this.containerDirtynessChange = containerDirtynessChange;
	}

	public String getElseFee() {
		return elseFee;
	}

	public void setElseFee(String elseFee) {
		this.elseFee = elseFee;
	}

	public String getHsCode() {
		return hsCode;
	}

	public void setHsCode(String hsCode) {
		this.hsCode = hsCode;
	}

	public String getCustomsdutiesComputeType() {
		return customsdutiesComputeType;
	}

	public void setCustomsdutiesComputeType(String customsdutiesComputeType) {
		this.customsdutiesComputeType = customsdutiesComputeType;
	}

	public String getAddedvaluetaxComputeType() {
		return addedvaluetaxComputeType;
	}

	public void setAddedvaluetaxComputeType(String addedvaluetaxComputeType) {
		this.addedvaluetaxComputeType = addedvaluetaxComputeType;
	}

	public String getAeRemarks() {
		return aeRemarks;
	}

	public void setAeRemarks(String aeRemarks) {
		this.aeRemarks = aeRemarks;
	}
	
}
