package com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;

/**
 * 申请报告(老品新上)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月20日
 */
public class ApplicationReportOldup extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6587214144493118021L;
	private String reportCode;//
	private String applicationCode;//
	private String merchandiseCode;//
	private String merchandiseName;//
	private String supplierCode;//
	private String supplierName;//
	private String supplierAttribute;//
	private String supplierSite;//
	private Integer smallTypeSku;//
	private Integer detailTypeSku;//
	private String dlRoleCode;// 定量角色编码
	private String dlRoleName;// 定量角色名称
	private String dxRoleCode;// 定性角色编码
	private String dxRoleName;// 定性角色名称
	private String centreTypeCode;//中分类编码
	private String centreTypeName;// 中分类名称
	private String smallTypeCode;//小分类编码
	private String smallTypeName;//小分类名称
	
	private String detailTypeCode;//明细类编码
	private String detailTypeName;//明细类名称
	private String fineTypeCode;//细分类编码
	private String fineTypeName;//细分类名称
	
	private String development;//
	private Integer sameMerchandisePrice;//
	private Integer anticipatedSellStoreQuantit;// 预计销售总门店数
	private BigDecimal anticipatedSellQuantity;// 预计销售量(最小单位/单店单天)
	private String purchaseOpinion;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private BigDecimal anticipatedSellProfit;// 预计毛利额(最小单位/单店单天)
	
	// 上下市模块
	private Date upDate;//
	private Date downDate;//
	// 检验标准
	private String oldStandardColour;//
	private String newStandardColour;//
	private String oldStandardSmell;//
	private String newStandardSmell;//
	private String oldStandardFrom;//
	private String newStandardForm;//
	private String path;

	// 上下市时间(老品新上)
	private UpDownMarketOldup upDownArr[] = new UpDownMarketOldup[0];
	// 商品历史价格(老品新上)
	private HistoryPriceOldup oldPriceArr[] = new HistoryPriceOldup[0];
	// 商品本次价格(老品新上)
	private HistoryPriceOldup nowPriceArr[] = new HistoryPriceOldup[0];
	// 商品原料(老品新上)
	private MerchandiseMaterial materialArr[] = new MerchandiseMaterial[0];
	// 同类商品市场零售价(老品新上)
	private SameMerchandiseOldup sameArr[] = new SameMerchandiseOldup[0];
	// 销售预计(老品新上)
	private AnticipatedSellOld sellArr[] = new AnticipatedSellOld[0];

	private String isHaveSameReport;// 是否有同类商品市场零售价

	// 上下市时间(老品新上)
	private List<UpDownMarketOldup> upDownList;
	// 商品历史价格(老品新上)
	private List<HistoryPriceOldup> oldPriceList;
	// 商品本次价格(老品新上)
	private List<HistoryPriceOldup> nowPriceList;
	// 商品原料(老品新上)
	private List<MerchandiseMaterial> materialList;
	// 同类商品市场零售价(老品新上)
	private List<SameMerchandiseOldup> sameList;
	// 销售预计(老品新上)
	private List<AnticipatedSellOld> sellList;
	// 本次价格比历史价格高
	private List<HistoryPriceOldup> nowThanOldPriceList;

	//历史水分含量
	private String oldMoistureContent;
	//本次水分含量
	private String newMoistureContent;
	//采购分析
	private String purchaseAnalysis;
	
	// 获取
	public String getApplicationCode() {
		return applicationCode;
	}

	// 设置
	public void setApplicationCode(String applicationCode) {
		this.applicationCode = applicationCode;
	}

	public String getReportCode() {
		return reportCode;
	}

	public void setReportCode(String reportCode) {
		this.reportCode = reportCode;
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

	public String getDlRoleCode() {
		return dlRoleCode;
	}

	public void setDlRoleCode(String dlRoleCode) {
		this.dlRoleCode = dlRoleCode;
	}

	public String getDxRoleCode() {
		return dxRoleCode;
	}

	public void setDxRoleCode(String dxRoleCode) {
		this.dxRoleCode = dxRoleCode;
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
	public BigDecimal getAnticipatedSellQuantity() {
		return anticipatedSellQuantity;
	}

	// 设置
	public void setAnticipatedSellQuantity(BigDecimal anticipatedSellQuantity) {
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

	public UpDownMarketOldup[] getUpDownArr() {
		return upDownArr;
	}

	public void setUpDownArr(UpDownMarketOldup[] upDownArr) {
		this.upDownArr = upDownArr;
	}

	public HistoryPriceOldup[] getOldPriceArr() {
		return oldPriceArr;
	}

	public void setOldPriceArr(HistoryPriceOldup[] oldPriceArr) {
		this.oldPriceArr = oldPriceArr;
	}

	public HistoryPriceOldup[] getNowPriceArr() {
		return nowPriceArr;
	}

	public void setNowPriceArr(HistoryPriceOldup[] nowPriceArr) {
		this.nowPriceArr = nowPriceArr;
	}

	public List<MerchandiseMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<MerchandiseMaterial> materialList) {
		this.materialList = materialList;
	}

	public SameMerchandiseOldup[] getSameArr() {
		return sameArr;
	}

	public void setSameArr(SameMerchandiseOldup[] sameArr) {
		this.sameArr = sameArr;
	}

	public AnticipatedSellOld[] getSellArr() {
		return sellArr;
	}

	public void setSellArr(AnticipatedSellOld[] sellArr) {
		this.sellArr = sellArr;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public Date getDownDate() {
		return downDate;
	}

	public void setDownDate(Date downDate) {
		this.downDate = downDate;
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

	public String getIsHaveSameReport() {
		return isHaveSameReport;
	}

	public void setIsHaveSameReport(String isHaveSameReport) {
		this.isHaveSameReport = isHaveSameReport;
	}

	public BigDecimal getAnticipatedSellProfit() {
		return anticipatedSellProfit;
	}

	public void setAnticipatedSellProfit(BigDecimal anticipatedSellProfit) {
		this.anticipatedSellProfit = anticipatedSellProfit;
	}

	public List<UpDownMarketOldup> getUpDownList() {
		return upDownList;
	}

	public void setUpDownList(List<UpDownMarketOldup> upDownList) {
		this.upDownList = upDownList;
	}

	public List<HistoryPriceOldup> getOldPriceList() {
		return oldPriceList;
	}

	public void setOldPriceList(List<HistoryPriceOldup> oldPriceList) {
		this.oldPriceList = oldPriceList;
	}

	public List<HistoryPriceOldup> getNowPriceList() {
		return nowPriceList;
	}

	public void setNowPriceList(List<HistoryPriceOldup> nowPriceList) {
		this.nowPriceList = nowPriceList;
	}

	public List<SameMerchandiseOldup> getSameList() {
		return sameList;
	}

	public void setSameList(List<SameMerchandiseOldup> sameList) {
		this.sameList = sameList;
	}

	public List<AnticipatedSellOld> getSellList() {
		return sellList;
	}

	public void setSellList(List<AnticipatedSellOld> sellList) {
		this.sellList = sellList;
	}

	public List<HistoryPriceOldup> getNowThanOldPriceList() {
		return nowThanOldPriceList;
	}

	public void setNowThanOldPriceList(List<HistoryPriceOldup> nowThanOldPriceList) {
		this.nowThanOldPriceList = nowThanOldPriceList;
	}

	public MerchandiseMaterial[] getMaterialArr() {
		return materialArr;
	}

	public void setMaterialArr(MerchandiseMaterial[] materialArr) {
		this.materialArr = materialArr;
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
	
}