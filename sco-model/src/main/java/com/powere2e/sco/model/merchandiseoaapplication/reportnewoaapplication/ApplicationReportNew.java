package com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.apache.struts2.json.annotations.JSON;
import com.powere2e.frame.server.model.AppModel;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;

/**
 * 申请报告(新品引进)实体类
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月28日
 */
public class ApplicationReportNew extends AppModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4542275775141727404L;
	private String reportCode;//
	private String applicationCode;//
	private String intentionCode;//
	private String intentionName;//
	private String supplierCode;//
	private String supplierName;//
	private String supplierAttribute;//
	private String supplierSite;//
	private String dlRoleCode;// 定量角色编码
	private String dlRoleName;// 定量角色名称
	
	private String dxRoleCode;// 定性角色编码
	private String dxRoleName;// 定性角色名称
	private String centreTypeCode;//中分类编码
	private String centreTypeName;// 中分类名称
	private String smallTypeCode;// 小分类编码
	private String smallTypeName;// 小分类名称
	private String elseTypeName;//
	private String detailTypeCode;// 明细类编码
	private String detailTypeName;// 明细类名称
	private String fineTypeCode;// 细分类编码
	private String fineTypeName;// 细分类名称
	
	private String development;//
	private String visitFactory;//
	private Integer smallTypeSku;//
	private Integer detailTypeSku;//
	private Date foretasteDate;//
	private Double foretasteGrade;//
	private String foretasteEvaluate;//
	private Integer sameMerchandisePrice;//
	private Integer anticipatedSellStoreQuantit;// 预计销售总门店数
	private BigDecimal anticipatedSellQuantity;// 预计销售量(最小单位/单店单天)
	private String purchaseOpinion;//
	private Date created;//
	private String createby;//
	private Date updated;//
	private String updateby;//
	private String isHaveSameReport;// 是否有同类商品市场零售价
	private BigDecimal anticipatedSellProfit;// 预计毛利额(最小单位/单店单天)
	private String path;

	// 商品价格(新品引进)
	private MerchandisePriceNew priceArr[] = new MerchandisePriceNew[0];
	// 同类商品市场零售价(新品引进)
	private SameMerchandiseNew sameArr[] = new SameMerchandiseNew[0];
	// 同类商品市场零售价(新品引进)
	private SellAnticipatedNew sellArr[] = new SellAnticipatedNew[0];
	// 商品原料(新品引进)
	private MerchandiseMaterial materialArr[] = new MerchandiseMaterial[0];

	// 用于生成html使用
	private List<MerchandisePriceNew> priceList = null;
	private List<SameMerchandiseNew> sameList = null;
	private List<SellAnticipatedNew> sellList = null;
	private List<MerchandiseMaterial> materialList = null;
	
	private String colour;// 色泽
	private String smell;// 滋气味	
	private String form;// 组织形态	
	private String moistureContent;// 水份含量
	private String purchaseAnalysis;// 采购员分析
	
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
	public String getIntentionCode() {
		return intentionCode;
	}

	// 设置
	public void setIntentionCode(String intentionCode) {
		this.intentionCode = intentionCode;
	}

	// 获取
	public String getIntentionName() {
		return intentionName;
	}

	// 设置
	public void setIntentionName(String intentionName) {
		this.intentionName = intentionName;
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

	public String getElseTypeName() {
		return elseTypeName;
	}

	public void setElseTypeName(String elseTypeName) {
		this.elseTypeName = elseTypeName;
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
	public String getVisitFactory() {
		return visitFactory;
	}

	// 设置
	public void setVisitFactory(String visitFactory) {
		this.visitFactory = visitFactory;
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
	@JSON(format = "yyyy-MM-dd HH:mm:ss")
	public Date getForetasteDate() {
		return foretasteDate;
	}

	// 设置
	public void setForetasteDate(Date foretasteDate) {
		this.foretasteDate = foretasteDate;
	}

	// 获取
	public Double getForetasteGrade() {
		return foretasteGrade;
	}

	// 设置
	public void setForetasteGrade(Double foretasteGrade) {
		this.foretasteGrade = foretasteGrade;
	}

	// 获取
	public String getForetasteEvaluate() {
		return foretasteEvaluate;
	}

	// 设置
	public void setForetasteEvaluate(String foretasteEvaluate) {
		this.foretasteEvaluate = foretasteEvaluate;
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

	public MerchandisePriceNew[] getPriceArr() {
		return priceArr;
	}

	public void setPriceArr(MerchandisePriceNew[] priceArr) {
		this.priceArr = priceArr;
	}

	public SameMerchandiseNew[] getSameArr() {
		return sameArr;
	}

	public void setSameArr(SameMerchandiseNew[] sameArr) {
		this.sameArr = sameArr;
	}

	public SellAnticipatedNew[] getSellArr() {
		return sellArr;
	}

	public void setSellArr(SellAnticipatedNew[] sellArr) {
		this.sellArr = sellArr;
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

	public List<MerchandisePriceNew> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<MerchandisePriceNew> priceList) {
		this.priceList = priceList;
	}

	public List<SameMerchandiseNew> getSameList() {
		return sameList;
	}

	public void setSameList(List<SameMerchandiseNew> sameList) {
		this.sameList = sameList;
	}

	public List<SellAnticipatedNew> getSellList() {
		return sellList;
	}

	public void setSellList(List<SellAnticipatedNew> sellList) {
		this.sellList = sellList;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getSmell() {
		return smell;
	}

	public void setSmell(String smell) {
		this.smell = smell;
	}

	public String getForm() {
		return form;
	}

	public void setForm(String form) {
		this.form = form;
	}

	public String getMoistureContent() {
		return moistureContent;
	}

	public void setMoistureContent(String moistureContent) {
		this.moistureContent = moistureContent;
	}

	public MerchandiseMaterial[] getMaterialArr() {
		return materialArr;
	}

	public void setMaterialArr(MerchandiseMaterial[] materialArr) {
		this.materialArr = materialArr;
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

	public List<MerchandiseMaterial> getMaterialList() {
		return materialList;
	}

	public void setMaterialList(List<MerchandiseMaterial> materialList) {
		this.materialList = materialList;
	}
	
}