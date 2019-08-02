package com.powere2e.sco.common.service;

/**
 * 业务状态类常量
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月19日
 * @version 1.0
 */
public class BusinessConstants {

	/**
	 * 签量单状态标识
	 */
	public static enum signedQuantityType {
		/**
		 * 新增
		 */
		A("新增"),

		/**
		 * 改签单
		 */
		S("改签单"),

		/**
		 * 已改签
		 */
		D("已改签"),

		/**
		 * 已终止
		 */
		T("已终止");

		private String statusName;

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		private signedQuantityType(String statusName) {
			this.statusName = statusName;
		}
	}

	/**
	 * 采购部门
	 */
	public static enum ProcurementDepartments {
		/**
		 * 国内
		 */
		INLAND("国内"),
		/**
		 * 国际
		 */
		IMPORT("国际");

		private String departmentsName;

		public String getDepartmentsName() {
			return departmentsName;
		}

		public void setDepartmentsName(String departmentsName) {
			this.departmentsName = departmentsName;
		}

		private ProcurementDepartments(String departmentsName) {
			this.departmentsName = departmentsName;
		}
	}

	/**
	 * 投料表-原料类型
	 */
	public static enum MaterialType {
		/**
		 * 主料
		 */
		ZL("主料"),
		/**
		 * 辅料
		 */
		FL("辅料");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private MaterialType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 是否定量装
	 */
	public static enum Rationed {
		/**
		 * 公斤装
		 */
		GJZ("公斤装"),
		/**
		 * 定量装
		 */
		DLZ("定量装");

		private String rationedName;

		public String getRationedName() {
			return rationedName;
		}

		public void setRationedName(String rationedName) {
			this.rationedName = rationedName;
		}

		private Rationed(String rationedName) {
			this.rationedName = rationedName;
		}
	}

	/**
	 * 采购类型
	 */
	public static enum PurchaseType {
		/**
		 * 直采
		 */
		ZC("直采"),
		/**
		 * 非直采
		 */
		FZC("非直采");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private PurchaseType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * OA申请类型
	 */
	public static enum ApplicationType {
		/**
		 * 商品新品引进
		 */
		MERCHANDISE_NEW("商品新品引进"),
		/**
		 * 商品老品新上
		 */
		MERCHANDISE_OLDUP("商品老品新上"),
		/**
		 * 商品正常调价
		 */
		MERCHANDISE_ADJUSTPRICE("商品正常调价"),
		/**
		 * 商品快速调价
		 */
		MERCHANDISE_FASTADJUSTPRICE("商品快速调价"),
		/**
		 * 商品快速引进
		 */
		MERCHANDISE_FASTIMPORT("商品快速引进"),
		/**
		 * 辅料采购委员会竞价单
		 */
		ACCESSORY_CGWYHJJD("辅料采购委员会竞价单"),
		/**
		 * 辅料非食品竞价单
		 */
		ACCESSORY_FSPJJD("辅料非食品竞价单"),
		/**
		 * 辅料询价比较
		 */
		ACCESSORY_XJBJ("询价比较");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private ApplicationType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 辅料OA申请单状态
	 */
	public static enum ApplicationStatus {
		/**
		 * 无
		 */
		W("无"),
		/**
		 * 草稿
		 */
		CG("草稿"),
		/**
		 * 关闭
		 */
		GB("关闭"),
		/**
		 * 允许OA同步
		 */
		YX("允许OA同步"),
		/**
		 * OA审批中
		 */
		SPZ("OA审批中"),
		/**
		 * OA审批通过
		 */
		SPTG("OA审批通过"),
		
		/**
		 * 驳回
		 */
		BH("驳回");

		private String statusName;

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		private ApplicationStatus(String statusName) {
			this.statusName = statusName;
		}
	}
	
	/**
	 * 商品OA申请单状态
	 */
	public static enum MerchandiseApplicationStatus {
		/**
		 * 无
		 */
		W("无"),
		/**
		 * 草稿
		 */
		CG("草稿"),
		/**
		 * 审批中
		 */
		SPZ("审批中"),
		/**
		 * 驳回
		 */
		BH("驳回"),
		/**
		 * 审批通过
		 */
		SPTG("审批通过");

		private String statusName;

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		private MerchandiseApplicationStatus(String statusName) {
			this.statusName = statusName;
		}
	}

	/**
	 * 巡厂申请单状态
	 */
	public static enum VisitApplicationStatus {
		/**
		 * 无
		 */
		W("无"),
		/**
		 * 审批中
		 */
		SPZ("审批中"),
		/**
		 * 驳回
		 */
		BH("驳回"),
		/**
		 * 审批通过
		 */
		SPTG("审批通过");
		/**
		 * 2016-04-11 去掉审批不通过状态
		 * 
		 * 审批不通过
		 *
		SPBTG("审批不通过");
		 */

		private String statusName;

		public String getStatusName() {
			return statusName;
		}

		public void setStatusName(String statusName) {
			this.statusName = statusName;
		}

		private VisitApplicationStatus(String statusName) {
			this.statusName = statusName;
		}
	}
	
	/**
	 * 报表类型
	 */
	public static enum myReportType {
		/**
		 * 商品总成本类比分析表
		 */
		MCA("商品总成本类比分析表"),
		
		/**
		 * 商品分项成本类比分析表
		 */
		MAA("商品分项成本类比分析表"),

		/**
		 * 商品促销表现对比表
		 */
		MPC("商品促销表现对比表"),

		/**
		 * 公司整体促销分析表
		 */
		CPC("公司整体促销分析表"),

		/**
		 * 单品同比分析表
		 */
		SCA("单品同比分析表"),

		/**
		 * 明细类同比分析表
		 */
		DCA("明细类同比分析表"),

		/**
		 * 小分类同比分析表
		 */
		SCS("小分类同比分析表"),

		/**
		 * 整体同比分析表
		 */
		GCS("整体同比分析表"),

		/**
		 * 单品环比分析表
		 */
		SMS("单品环比分析表"),
		
		/**
		 * 明细类环比分析表
		 */
		DSS("明细类环比分析表"),

		/**
		 * 小分类环比分析表
		 */
		SSS("小分类环比分析表"),

		/**
		 * 整体环比分析表
		 */
		GSS("整体环比分析表"),

		/**
		 * 销售明细表
		 */
		SDT("销售明细表"),

		/**
		 * 所有商品价格带
		 */
		AMP("所有商品价格带"),

		/**
		 * 小分类价格带
		 */
		SPP("小分类价格带"),

		/**
		 * 明细类价格带
		 */
		DPP("明细类价格带"),

		/**
		 * 自选商品价格带
		 */
		CMP("自选商品价格带"),

		/**
		 * 公示网站原料历史行情
		 */
		PSH("公示网站原料历史行情"),

		/**
		 * 商品原料供应商历史报价
		 */
		GSH("商品原料供应商历史报价"),

		/**
		 * 商品价格趋势
		 */
		MPT("商品价格趋势"),

		/**
		 * 进货明细
		 */
		STD("进货明细"),

		/**
		 * 长期进价调整报表
		 */
		LTP("长期进价调整报表"),

		/**
		 * 促销进价调整报表
		 */
		PTP("促销进价调整报表"),

		/**
		 * 商品供应商销售贡献度排行
		 */
		SSR("商品供应商销售贡献度排行"),

		/**
		 * 供应商综合分析表
		 */
		SSA("供应商综合分析表"),

		/**
		 * 竞品价格市调数据
		 */
		MPD("竞品价格市调数据"),
		
		/**
		 * 辅料总价分析表
		 */
		FQA("辅料总价分析表"),

		/**
		 * 辅料成本类比分析表
		 */
		FCA("辅料成本类比分析表"),

		/**
		 * 辅料供应商历史报价分析表
		 */
		FSP("辅料供应商历史报价分析表");
		
		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private myReportType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 商品内包装类型
	 */
	public static enum MerchandiseNPackag {
		/**
		 * 无
		 */
		W("无"),
		/**
		 * 复合袋-卷膜
		 */
		FHD_JM("复合袋-卷膜"),
		/**
		 * 复合袋-拉伸膜
		 */
		FHD_LSM("复合袋-拉伸膜"),
		/**
		 * 复合袋-制袋
		 */
		FHD_ZD("复合袋-制袋"),
		/**
		 * 塑托
		 */
		ST("塑托"),
		/**
		 * 脱氧剂
		 */
		TYJ("脱氧剂"),
		/**
		 * 内胆袋
		 */
		NDD("内胆袋"),
		/**
		 * 标签
		 */
		BQ("标签"),
		/**
		 * 纸张类
		 */
		ZZL("纸张类"),
		/**
		 * 其它
		 */
		ELSE("其它");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private MerchandiseNPackag(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 商品外包装类型
	 */
	public static enum MerchandiseWPackag {
		/**
		 * 无
		 */
		W("无"),
		/**
		 * 封箱带
		 */
		FXD("封箱带"),
		/**
		 * 复合袋-制袋
		 */
		ZX("纸箱"),
		/**
		 * 其它
		 */
		ELSE("其它");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private MerchandiseWPackag(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 上传扫描版附件
	 */
	public static enum SupplierAttachmentType {
		/**
		 * 空
		 */
		K(""),
		/**
		 * 封箱带
		 */
		XPXXDJB("新品信息登记表"),
		/**
		 * 复合袋-制袋
		 */
		XGYSXXDJB("新供应商信息登记表"),
		/**
		 * 感官标准
		 */
		GGBZ("感官标准"),
		/**
		 * 其它
		 */
		ELSE("其它");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private SupplierAttachmentType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 辅料细分类类型
	 */
	public static enum AccessoryDetailType {
		/**
		 * 包材-小纸袋
		 */
		PS("包材-小纸袋"),
		/**
		 * 包材-大纸袋
		 */
		PB("包材-大纸袋"),
		/**
		 * 包材-自封袋
		 */
		PL("包材-自封袋");
		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private AccessoryDetailType(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 考评模板状态
	 */
	public static enum EvaluateTemplateStatus {
		/**
		 * 草稿
		 */
		CG("草稿"),
		/**
		 * 已发布
		 */
		YFB("已发布"),
		/**
		 * 已关闭
		 */
		YGB("已关闭");
		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private EvaluateTemplateStatus(String typeName) {
			this.typeName = typeName;
		}
	}

	/**
	 * 考评模板打分部门
	 */
	public static enum EvaluateTemplateMarkDepartments {
		/**
		 * 采购
		 */
		CG("采购部门"),
		/**
		 * 品控
		 */
		PK("品控部门"),
		/**
		 * 库存
		 */
		KC("库存部门"),
		/**
		 * 品类
		 */
		PL("品类部门");

		private String typeName;

		public String getTypeName() {
			return typeName;
		}

		public void setTypeName(String typeName) {
			this.typeName = typeName;
		}

		private EvaluateTemplateMarkDepartments(String typeName) {
			this.typeName = typeName;
		}

	}

	/**
	 * 其他成本
	 */
	public static enum ElseCost {
		/**
		 * 损耗
		 */
		SH("损耗"),
		/**
		 * 水电煤
		 */
		SDM("水电煤"),
		/**
		 * 设备折旧及维护
		 */
		SBZJWH("设备折旧及维护"),
		/**
		 * 人工
		 */
		RG("人工"),
		/**
		 * 管理
		 */
		GL("管理"),
		/**
		 * 运输
		 */
		YS("运输"),
		/**
		 * 税收
		 */
		SS("税收"),
		/**
		 * 利润
		 */
		LR("利润");
		private String elseCostName;

		public String getElseCostName() {
			return elseCostName;
		}

		public void setElseCostName(String elseCostName) {
			this.elseCostName = elseCostName;
		}

		private ElseCost(String elseCostName) {
			this.elseCostName = elseCostName;
		}
	}

	/**
	 * 进口相关成本
	 */
	public static enum ImportRelevantCost {
		/**
		 * 商品报价
		 */
		CC("商品报价"),
		/**
		 * 海运费
		 */
		HY("海运费"),
		/**
		 * 空运费
		 */
		KY("空运费"),
		/**
		 * 换单费
		 */
		HD("换单费"),
		/**
		 * 保险费
		 */
		BX("保险费"),
		/**
		 * 报关服务费
		 */
		BG("报关服务费"),
		/**
		 * 关税
		 */
		GS("关税"),
		/**
		 * 增值税
		 */
		ZZ("增值税");
		private String importName;

		public String getImportName() {
			return importName;
		}

		public void setImportName(String importName) {
			this.importName = importName;
		}

		private ImportRelevantCost(String importName) {
			this.importName = importName;
		}
	}

	/**
	 * 研发
	 */
	public static enum Development {
		/**
		 * 来伊份
		 */
		LYF("来伊份"),
		/**
		 * 供应商
		 */
		GYS("供应商");
		private String developmentName;

		public String getDevelopmentName() {
			return developmentName;
		}

		public void setDevelopmentName(String developmentName) {
			this.developmentName = developmentName;
		}

		private Development(String developmentName) {
			this.developmentName = developmentName;
		}

	}

	/**
	 * 研发
	 */
	public static enum QuotedCurrency {
		/**
		 * CNY
		 */
		CNY("CNY"),
		/**
		 * USD
		 */
		USD("USD"),
		/**
		 * EUR
		 */
		EUR("EUR");
		private String quotedCurrencyName;

		public String getQuotedCurrencyName() {
			return quotedCurrencyName;
		}

		public void setQuotedCurrencyName(String quotedCurrencyName) {
			this.quotedCurrencyName = quotedCurrencyName;
		}

		private QuotedCurrency(String quotedCurrencyName) {
			this.quotedCurrencyName = quotedCurrencyName;
		}

	}

	/**
	 * 提醒
	 */
	public static enum Remind {
		/**
		 * 证件临期提醒
		 */
		ZJLQTX("证件临期提醒"),
		/**
		 * 证件过期提醒
		 */
		ZJGQTX("证件过期提醒"),
		/**
		 * 原料价格预警
		 */
		YLJGTBYJ("原料价格同比预警"),
		/**
		 * 原料价格预警
		 */
		YLJGHBYJ("原料价格环比预警"),
		/**
		 * 原料价格预警
		 */
		YLJGYJ("原料价格预警"),
		/**
		 * 商品价格预警
		 */
		SPJGYJ("商品价格预警"),
		/**
		 * 辅料商品价格预警
		 */
		FLYXPJGYJ("辅料意向品价格预警"),
		/**
		 * 签量完成提醒
		 */
		QLWCTX("签量完成提醒"),
		/**
		 * 调价签量维护提醒
		 */
		TJQLWHTX("调价签量维护提醒");
		private String remindName;

		public String getRemindName() {
			return remindName;
		}

		public void setRemindName(String remindName) {
			this.remindName = remindName;
		}

		private Remind(String remindName) {
			this.remindName = remindName;
		}
	}

	/**
	 * 核算表-进口/国内
	 */
	public static enum InlandImport {
		/**
		 * 国内
		 */
		INLAND("国内"),
		/**
		 * 进口
		 */
		IMPORT("进口");

		private String inlandImportName;

		public String getInlandImportName() {
			return inlandImportName;
		}

		public void setInlandImportName(String inlandImportName) {
			this.inlandImportName = inlandImportName;
		}

		private InlandImport(String inlandImportName) {
			this.inlandImportName = inlandImportName;
		}
	}

	/**
	 * 核算表-运费类型
	 */
	public static enum FreightType {
		/**
		 * 海运费
		 */
		HYF("海运费"),
		/**
		 * 空运费
		 */
		KYF("空运费");

		private String freightTypeName;

		public String getFreightTypeName() {
			return freightTypeName;
		}

		public void setFreightTypeName(String freightTypeName) {
			this.freightTypeName = freightTypeName;
		}

		private FreightType(String freightTypeName) {
			this.freightTypeName = freightTypeName;
		}
	}

	/**
	 * 核算表-货柜类型
	 */
	public static enum ContainerType {
		/**
		 * 常温柜
		 */
		CWG("常温柜"),
		/**
		 * 恒温柜
		 */
		HWG("恒温柜"),
		/**
		 * 冷藏柜
		 */
		LCG("冷藏柜"),
		/**
		 * 冷冻柜
		 */
		LDG("冷冻柜"),
		/**
		 * 其它
		 */
		ELSE("其它");

		private String containerTypeName;

		public String getContainerTypeName() {
			return containerTypeName;
		}

		public void setContainerTypeName(String containerTypeName) {
			this.containerTypeName = containerTypeName;
		}

		private ContainerType(String containerTypeName) {
			this.containerTypeName = containerTypeName;
		}
	}

	/**
	 * 核算表-出厂价类型
	 */
	public static enum FactoryPriceType {
		/**
		 * FOB
		 */
		FOB("FOB"),
		/**
		 * CIF
		 */
		CIF("CIF"),
		/**
		 * C&F
		 */
		C_F("C&F"),
		/**
		 * 其它
		 */
		ELSE("其它");

		private String factoryPriceTypeName;

		public String getFactoryPriceTypeName() {
			return factoryPriceTypeName;
		}

		public void setFactoryPriceTypeName(String factoryPriceTypeName) {
			this.factoryPriceTypeName = factoryPriceTypeName;
		}

		private FactoryPriceType(String factoryPriceTypeName) {
			this.factoryPriceTypeName = factoryPriceTypeName;
		}
	}
	
	/**
	 * 是/否
	 */
	public static enum YesNo {
		/**
		 * 是
		 */
		Y("是"),
		/**
		 * 否
		 */
		N("否");

		private String yesNoName;

		public String getYesNoName() {
			return yesNoName;
		}

		public void setYesNoName(String yesNoName) {
			this.yesNoName = yesNoName;
		}

		private YesNo(String yesNoName) {
			this.yesNoName = yesNoName;
		}
	}
	
	/**
	 * 是否试吃通过
	 */
	public static enum ForetasteType {
		/**
		 * 是
		 */
		Y("是"),
		/**
		 * 否
		 */
		N("否");

		private String foretasteTypeName;

		public String getForetasteTypeName() {
			return foretasteTypeName;
		}

		public void setForetasteTypeName(String foretasteTypeName) {
			this.foretasteTypeName = foretasteTypeName;
		}

		private ForetasteType(String foretasteTypeName) {
			this.foretasteTypeName = foretasteTypeName;
		}

	}
}
