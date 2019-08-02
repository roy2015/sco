package com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;

/**
 * 申请报告(调价)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class ApplicationReportAdjustprice extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1962501323178036213L;
	private String reportCode;//
	private String priceAdjust;
	private String applicationCode;//
	private String merchandiseCode;//
	private String merchandiseName;//
	private String supplierCode;//
	private String supplierName;//
	private String supplierAttribute;//
	private String supplierSite;//
	private Integer smallTypeSku;//
	private Integer detailTypeSku;//
	private String dlRoleCode;//
	private String dlRoleName;// 商品定量角色名称
	private String dxRoleCode;//
	private String dxRoleName;// 商品定性角色名称
	private String centreTypeCode;//
	private String centreTypeName;// 中分类名称
	private String smallTypeCode;//
	private String smallTypeName;// 小分类名称
	private String detailTypeCode;//
	private String detailTypeName;// 明细类名称
	private String fineTypeCode;//
	private String fineTypeName;// 细分类名称
	private String development;//
	private Integer sameMerchandisePrice;//
	private Integer anticipatedSellStoreQuantit;//
	private Integer anticipatedSellQuantity;//
	private String purchaseOpinion;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//

	// 检验标准
	private String oldStandardColour;//
	private String newStandardColour;//
	private String oldStandardSmell;//
	private String newStandardSmell;//
	private String oldStandardFrom;//
	private String newStandardForm;//
	private String path;

	// 商品历史价格(正常调价)
	private HistoryPriceAdjustprice oldPriceArr[] = new HistoryPriceAdjustprice[0];
	// 商品本次价格(正常调价)
	private HistoryPriceAdjustprice nowPriceArr[] = new HistoryPriceAdjustprice[0];
	// 同类商品市场零售价(正常调价)
	private SameMerchandiseAdjustprice sameArr[] = new SameMerchandiseAdjustprice[0];
	// 商品原料(正常调价)
	private MerchandiseMaterial materialArr[] = new MerchandiseMaterial[0];

	private String isHaveSameReport;// 是否有同类商品市场零售价
	private Date qlDate;// 签量日期
	private BigDecimal atpQuantitySum;// 签量数量
	private BigDecimal atpQuantitySumAccomplish;// 已完成量
	private BigDecimal atpQuantitySumUnfinished;// 未完成量
	private String remarks;// 备注

	// 商品历史价格(正常调价)
	private List<HistoryPriceAdjustprice> oldPriceList;
	// 商品本次价格(正常调价)
	private List<HistoryPriceAdjustprice> nowPriceList;
	// 同类商品市场零售价(正常调价)
	private List<SameMerchandiseAdjustprice> sameList;
	// 销售预计(正常调价)
	private List<MerchandiseMaterial> materialList;
	// 本次价格比历史价格高(正常调价)
	private List<HistoryPriceAdjustprice> nowThanOldPriceList;

	//历史水分含量
	private String oldMoistureContent;
	//本次水分含量
	private String newMoistureContent;
	//采购分析
	private String purchaseAnalysis;
		
	// 获取
	public String getReportCode() {
		return reportCode;
	}

	// 设置
	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
	}

	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
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
	public String getMerchandiseName() {
		return merchandiseName;
	}

	// 设置
	public void setMerchandiseName(String merchandiseName) {
		this.merchandiseName = merchandiseName;
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
	public String getSupplierName() {
		return supplierName;
	}

	// 设置
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	// 获取
	public String getSupplierAttribute() {
		return supplierAttribute;
	}

	// 设置
	public void setSupplierAttribute(String supplierAttribute) {
		this.supplierAttribute = supplierAttribute;
	}

	// 获取
	public String getSupplierSite() {
		return supplierSite;
	}

	// 设置
	public void setSupplierSite(String supplierSite) {
		this.supplierSite = supplierSite;
	}

	// 获取
	public Integer getSmallTypeSku() {
		return smallTypeSku;
	}

	// 设置
	public void setSmallTypeSku(Integer smallTypeSku) {
		this.smallTypeSku = smallTypeSku;
	}

	// 获取
	public Integer getDetailTypeSku() {
		return detailTypeSku;
	}

	// 设置
	public void setDetailTypeSku(Integer detailTypeSku) {
		this.detailTypeSku = detailTypeSku;
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
	public String getCentreTypeCode() {
		return centreTypeCode;
	}

	// 设置
	public void setCentreTypeCode(String centreTypeCode) {
		this.centreTypeCode = centreTypeCode;
	}

	// 获取
	public String getSmallTypeCode() {
		return smallTypeCode;
	}

	// 设置
	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}

	// 获取
	public String getDetailTypeCode() {
		return detailTypeCode;
	}

	// 设置
	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}

	// 获取
	public String getFineTypeCode() {
		return fineTypeCode;
	}

	// 设置
	public void setFineTypeCode(String fineTypeCode) {
		this.fineTypeCode = fineTypeCode;
	}

	// 获取
	public String getDevelopment() {
		return development;
	}

	// 设置
	public void setDevelopment(String development) {
		this.development = development;
	}

	// 获取
	public Integer getSameMerchandisePrice() {
		return sameMerchandisePrice;
	}

	// 设置
	public void setSameMerchandisePrice(Integer sameMerchandisePrice) {
		this.sameMerchandisePrice = sameMerchandisePrice;
	}

	// 获取
	public Integer getAnticipatedSellStoreQuantit() {
		return anticipatedSellStoreQuantit;
	}

	// 设置
	public void setAnticipatedSellStoreQuantit(Integer anticipatedSellStoreQuantit) {
		this.anticipatedSellStoreQuantit = anticipatedSellStoreQuantit;
	}

	// 获取
	public Integer getAnticipatedSellQuantity() {
		return anticipatedSellQuantity;
	}

	// 设置
	public void setAnticipatedSellQuantity(Integer anticipatedSellQuantity) {
		this.anticipatedSellQuantity = anticipatedSellQuantity;
	}

	// 获取
	public String getPurchaseOpinion() {
		return purchaseOpinion;
	}

	// 设置
	public void setPurchaseOpinion(String purchaseOpinion) {
		this.purchaseOpinion = purchaseOpinion;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getCreated() {
		return created;
	}

	// 设置
	public void setCreated(Date created) {
		this.created = created;
	}

	// 获取
	public String getCreateby() {
		return createby;
	}

	// 设置
	public void setCreateby(String createby) {
		this.createby = createby;
	}

	// 获取
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdated() {
		return updated;
	}

	// 设置
	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	// 获取
	public String getUpdateby() {
		return updateby;
	}

	// 设置
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}

	public String getOldStandardColour() {
		return oldStandardColour;
	}

	public void setOldStandardColour(String oldStandardColour) {
		this.oldStandardColour = oldStandardColour;
	}

	public String getNewStandardColour() {
		return newStandardColour;
	}

	public void setNewStandardColour(String newStandardColour) {
		this.newStandardColour = newStandardColour;
	}

	public String getOldStandardSmell() {
		return oldStandardSmell;
	}

	public void setOldStandardSmell(String oldStandardSmell) {
		this.oldStandardSmell = oldStandardSmell;
	}

	public String getNewStandardSmell() {
		return newStandardSmell;
	}

	public void setNewStandardSmell(String newStandardSmell) {
		this.newStandardSmell = newStandardSmell;
	}

	public String getOldStandardFrom() {
		return oldStandardFrom;
	}

	public void setOldStandardFrom(String oldStandardFrom) {
		this.oldStandardFrom = oldStandardFrom;
	}

	public String getNewStandardForm() {
		return newStandardForm;
	}

	public void setNewStandardForm(String newStandardForm) {
		this.newStandardForm = newStandardForm;
	}

	public HistoryPriceAdjustprice[] getOldPriceArr() {
		return oldPriceArr;
	}

	public void setOldPriceArr(HistoryPriceAdjustprice[] oldPriceArr) {
		this.oldPriceArr = oldPriceArr;
	}

	public HistoryPriceAdjustprice[] getNowPriceArr() {
		return nowPriceArr;
	}

	public void setNowPriceArr(HistoryPriceAdjustprice[] nowPriceArr) {
		this.nowPriceArr = nowPriceArr;
	}

	public SameMerchandiseAdjustprice[] getSameArr() {
		return sameArr;
	}

	public void setSameArr(SameMerchandiseAdjustprice[] sameArr) {
		this.sameArr = sameArr;
	}

	public MerchandiseMaterial[] getMaterialArr() {
		return materialArr;
	}

	public void setMaterialArr(MerchandiseMaterial[] materialArr) {
		this.materialArr = materialArr;
	}

	public String getIsHaveSameReport() {
		return isHaveSameReport;
	}

	public void setIsHaveSameReport(String isHaveSameReport) {
		this.isHaveSameReport = isHaveSameReport;
	}

	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getQlDate() {
		return qlDate;
	}

	public void setQlDate(Date qlDate) {
		this.qlDate = qlDate;
	}

	public BigDecimal getAtpQuantitySum() {
		return atpQuantitySum;
	}

	public void setAtpQuantitySum(BigDecimal atpQuantitySum) {
		this.atpQuantitySum = atpQuantitySum;
	}

	public BigDecimal getAtpQuantitySumAccomplish() {
		return atpQuantitySumAccomplish;
	}

	public void setAtpQuantitySumAccomplish(BigDecimal atpQuantitySumAccomplish) {
		this.atpQuantitySumAccomplish = atpQuantitySumAccomplish;
	}

	public BigDecimal getAtpQuantitySumUnfinished() {
		return atpQuantitySumUnfinished;
	}

	public void setAtpQuantitySumUnfinished(BigDecimal atpQuantitySumUnfinished) {
		this.atpQuantitySumUnfinished = atpQuantitySumUnfinished;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getPriceAdjust() {
		return priceAdjust;
	}

	public void setPriceAdjust(String priceAdjust) {
		this.priceAdjust = priceAdjust;
	}

	public List<HistoryPriceAdjustprice> getOldPriceList() {
		return oldPriceList;
	}

	public void setOldPriceList(List<HistoryPriceAdjustprice> oldPriceList) {
		this.oldPriceList = oldPriceList;
	}

	public List<HistoryPriceAdjustprice> getNowPriceList() {
		return nowPriceList;
	}

	public void setNowPriceList(List<HistoryPriceAdjustprice> nowPriceList) {
		this.nowPriceList = nowPriceList;
	}

	public List<SameMerchandiseAdjustprice> getSameList() {
		return sameList;
	}

	public void setSameList(List<SameMerchandiseAdjustprice> sameList) {
		this.sameList = sameList;
	}

	public List<MerchandiseMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<MerchandiseMaterial> materialList) {
		this.materialList = materialList;
	}

	public List<HistoryPriceAdjustprice> getNowThanOldPriceList() {
		return nowThanOldPriceList;
	}

	public void setNowThanOldPriceList(List<HistoryPriceAdjustprice> nowThanOldPriceList) {
		this.nowThanOldPriceList = nowThanOldPriceList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getOldMoistureContent() {
		return oldMoistureContent;
	}

	public void setOldMoistureContent(String oldMoistureContent) {
		this.oldMoistureContent = oldMoistureContent;
	}

	public String getNewMoistureContent() {
		return newMoistureContent;
	}

	public void setNewMoistureContent(String newMoistureContent) {
		this.newMoistureContent = newMoistureContent;
	}

	public String getPurchaseAnalysis() {
		return purchaseAnalysis;
	}

	public void setPurchaseAnalysis(String purchaseAnalysis) {
		this.purchaseAnalysis = purchaseAnalysis;
	}

	public String getDlRoleName() {
		return dlRoleName;
	}

	public void setDlRoleName(String dlRoleName) {
		this.dlRoleName = dlRoleName;
	}

	public String getDxRoleName() {
		return dxRoleName;
	}

	public void setDxRoleName(String dxRoleName) {
		this.dxRoleName = dxRoleName;
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
	
}