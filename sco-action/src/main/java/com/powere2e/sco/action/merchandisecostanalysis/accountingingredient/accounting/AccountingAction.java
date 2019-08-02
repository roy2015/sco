package com.powere2e.sco.action.merchandisecostanalysis.accountingingredient.accounting;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.DateUtils;
import com.powere2e.sco.common.utils.ExcelUtils;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.UploadUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.accounting.AccountingService;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.ingredient.IngredientService;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.Accounting;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAddedvaluetax;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAggregate;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingAggregateRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingBo;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCostItem;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCustomscharges;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingCustomsduties;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingElsesubtotal;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingElsesubtotalRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingExchangerate;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFactoryPrice;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFreight;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingFreightRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingIngredientScan;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingInterest;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManage;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManageRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingManpower;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingNPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingOceanfreight;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingProfit;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingProfitRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingSbzjwh;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTax;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTaxDiffer;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingTaxRegion;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWPackag;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWastage;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.accounting.AccountingWec;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.Ingredient;
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;
import com.powere2e.sco.service.impl.merchandisecostanalysis.accountingingredient.accounting.AccountingDataServiceImpl;

/**
 * 商品成本分析的WEB请求响应类
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccountingAction extends UploadUtils {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915705722020454704L;
	private AccountingService accountingService;
	private IngredientService ingredientService;
	private MasterDataTypeService masterDataTypeService;

	/**
	 * 核算表主表
	 */
	private Accounting accounting = new Accounting();
	/**
	 * 投料主表
	 */
	private Ingredient ingredient = new Ingredient();
	/**
	 * 地区集合
	 */
	private List<AccountingRegion> accountingRegionList = new ArrayList<AccountingRegion>();
	/**
	 * 成本项
	 */
	private AccountingCostItem accountingCostItem = new AccountingCostItem();
	/**
	 * 内包装集合
	 */
	private List<AccountingNPackag> accountingNPackagList = new ArrayList<AccountingNPackag>();
	/**
	 * 外包装集合
	 */
	private List<AccountingWPackag> accountingWPackagList = new ArrayList<AccountingWPackag>();
	/**
	 * 损耗类型集合
	 */
	private List<AccountingWastage> accountingWastageList = new ArrayList<AccountingWastage>();
	/**
	 * 水电煤
	 */
	private AccountingWec accountingWec = new AccountingWec();
	/**
	 * 设备折旧及维护
	 */
	private AccountingSbzjwh accountingSbzjwh = new AccountingSbzjwh();
	/**
	 * 人工
	 */
	private AccountingManpower accountingManpower = new AccountingManpower();
	/**
	 * 管理
	 */
	private AccountingManage accountingManage = new AccountingManage();
	/**
	 * 管理各地区集合
	 */
	private List<AccountingManageRegion> accountingManageRegionList = new ArrayList<AccountingManageRegion>();
	/**
	 * 运输
	 */
	private AccountingFreight accountingFreight = new AccountingFreight();
	/**
	 * 运输各地区集合
	 */
	private List<AccountingFreightRegion> accountingFreightRegionList = new ArrayList<AccountingFreightRegion>();
	/**
	 * 税收
	 */
	private AccountingTax accountingTax = new AccountingTax();
	/**
	 * 税收各地区集合
	 */
	private List<AccountingTaxRegion> accountingTaxRegionList = new ArrayList<AccountingTaxRegion>();
	/**
	 * 利润
	 */
	private AccountingProfit accountingProfit = new AccountingProfit();
	/**
	 * 利润各地区集合
	 */
	private List<AccountingProfitRegion> accountingProfitRegionList = new ArrayList<AccountingProfitRegion>();
	/**
	 * 其他成本小计
	 */
	private AccountingElsesubtotal accountingElsesubtotal = new AccountingElsesubtotal();
	/**
	 * 其他成本小计各地区集合
	 */
	private List<AccountingElsesubtotalRegion> accountingElsesubtotalRegionList = new ArrayList<AccountingElsesubtotalRegion>();
	/**
	 * 总价
	 */
	private AccountingAggregate accountingAggregate = new AccountingAggregate();
	/**
	 * 总价各地区集合
	 */
	private List<AccountingAggregateRegion> accountingAggregateRegionList = new ArrayList<AccountingAggregateRegion>();

	/**
	 * 商品出厂价格
	 */
	private AccountingFactoryPrice accountingFactoryPrice = new AccountingFactoryPrice();
	/**
	 * 汇率
	 */
	private AccountingExchangerate accountingExchangerate = new AccountingExchangerate();
	/**
	 * 海运费
	 */
	private AccountingOceanfreight accountingOceanfreight = new AccountingOceanfreight();
	/**
	 * 报关服务费
	 */
	private AccountingCustomscharges accountingCustomscharges = new AccountingCustomscharges();
	/**
	 * 关税
	 */
	private AccountingCustomsduties accountingCustomsduties = new AccountingCustomsduties();
	/**
	 * 增值税
	 */
	private AccountingAddedvaluetax accountingAddedvaluetax = new AccountingAddedvaluetax();
	/**
	 * 税差
	 */
	private AccountingTaxDiffer accountingTaxDiffer = new AccountingTaxDiffer();
	/**
	 * 利息
	 */
	private AccountingInterest accountingInterest = new AccountingInterest();

	/**
	 * 投入量总计
	 */
	BigDecimal ingredientItemInputCountSum = new BigDecimal(0);
	/**
	 * 投入成本总计
	 */
	BigDecimal ingredientItemInputCostSum = new BigDecimal(0);

	/**
	 * 平均成品原料成本总计
	 */
	BigDecimal ingredientItemAvgCostSum = new BigDecimal(0);

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {
		accountingService = (AccountingService) ConfigFactory.getInstance().getBean("accountingService");
		ingredientService = (IngredientService) ConfigFactory.getInstance().getBean("ingredientService");
		masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
	}

	/**
	 * 核算表与投料表搜索页面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowAccountingGrid() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/accountingGrid.ftl");
	}

	/**
	 * 商品核算表列表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doListAccounting() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		map.put("minApproveDate", this.asString("minApproveDate"));
		map.put("maxApproveDate", this.asString("maxApproveDate"));
		map.put("minUpdated", this.asString("minUpdated"));
		map.put("maxUpdated", this.asString("maxUpdated"));
		map.put("lastUpdated", this.asString("lastUpdated"));
		map.put("searchType", this.asString("searchType"));
		List<AccountingBo> list = accountingService.listAccounting(map, this.getPageInfo());
		this.forwardData(true, list, null);
	}

	/**
	 * 新增核算表界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowInsertAccountingForm() throws Exception {
		this.accounting = new Accounting();
		this.accounting.setAccountingCode(masterDataTypeService.nextID("S_ACCOUNTING"));
		this.accounting.setInlandImport(this.asString("inlandImport"));
		this.accounting.setIntentionCode(this.asString("intentionCode"));
		this.accounting.setIntentionSupplierCode(this.asString("intentionSupplierCode"));
		this.accounting.setMerchandiseCode(this.asString("merchandiseCode"));
		this.accounting.setSupplierCode(this.asString("supplierCode"));
		this.putObjectAccounting();
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/addAccountingForm.ftl");
	}

	/**
	 * 根据商品CODE和供应商CODE获一个商品核算表和投料表信息
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doLoadAccountingBo() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		AccountingBo accountingBo = accountingService.loadAccountingBo(map);
		List<AccountingBo> accountingBoList = new ArrayList<AccountingBo>();
		accountingBoList.add(accountingBo);
		this.forwardData(true, accountingBoList, null);
	}

	/**
	 * 保存商品核算表
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doSaveAccounting() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", accounting.getAccountingCode());
		AccountingBo accountingBo = accountingService.getAccountingOaStatus(map);
		if (accountingBo != null && !accountingBo.getApplicationStatus().equals("") && !accountingBo.getApplicationStatus().equals(BusinessConstants.ApplicationStatus.CG.toString())) {
			this.forwardData(false, null, "核算表当前OA申请状态不可修改");
			return;
		}
		map.remove("accountingCode");
		map.put("intentionCode", accounting.getIntentionCode());
		map.put("intentionSupplierCode", accounting.getIntentionSupplierCode());
		map.put("merchandiseCode", accounting.getMerchandiseCode());
		map.put("supplierCode", accounting.getSupplierCode());
		map.put("quotedDate", DateUtils.formatDateToStr(accounting.getQuotedDate(), "yyyy-MM-dd HH:mm:ss"));
		Accounting a = accountingService.loadAccounting(map);
		if (a != null && !a.getAccountingCode().equals(accounting.getAccountingCode())) {
			this.forwardData(false, null, "核算表报价日期不可重复");
		} else {
			accountingService.insertAccounting(this.getAccountingMap());
			Map<String, Object> mapSum = new HashMap<String, Object>();
			mapSum.put("ingredientItemInputCountSum", ingredientItemInputCountSum);
			mapSum.put("ingredientItemInputCostSum", ingredientItemInputCostSum);
			mapSum.put("ingredientItemAvgCostSum", ingredientItemAvgCostSum);
			this.forwardData(true, mapSum, this.getText("public.success"));
		}
	}

	/**
	 * 复制核算表界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowCopyAccountingGrid() throws Exception {
		this.accounting = new Accounting();
		this.accounting.setIntentionCode(this.asString("intentionCode"));
		this.accounting.setIntentionSupplierCode(this.asString("intentionSupplierCode"));
		this.accounting.setMerchandiseCode(this.asString("merchandiseCode"));
		this.accounting.setSupplierCode(this.asString("supplierCode"));
		this.putObject("accounting", accounting);
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/copyAccountingGrid.ftl");
	}

	/**
	 * 复制并新增核算表界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowCopyInsertAccountingForm() throws Exception {
		this.accounting = new Accounting();
		accounting.setAccountingCode(this.asString("accountingCode"));
		this.packageAccounting();
		this.accounting.setAccountingCode(masterDataTypeService.nextID("S_ACCOUNTING"));
		this.accounting.setIntentionCode(this.asString("intentionCode"));
		this.accounting.setIntentionSupplierCode(this.asString("intentionSupplierCode"));
		this.accounting.setMerchandiseCode(this.asString("merchandiseCode"));
		this.accounting.setSupplierCode(this.asString("supplierCode"));
		this.putObject("accountingBo", null);
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/addAccountingForm.ftl");
	}

	/**
	 * 查看核算表详细界面
	 *
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowLoadAccountingForm() throws Exception {
		accounting = new Accounting();
		accounting.setAccountingCode(this.asString("accountingCode"));
		this.packageAccounting();
		this.putObject("operate", "load");
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/addAccountingForm");
	}

	/**
	 * 核算表封装为Map
	 * 
	 * @return map
	 */
	public Map<String, Object> getAccountingMap() throws Exception {
		ingredient.setIngredientCode(accounting.getAccountingCode());
		ingredient.setIntentionCode(accounting.getIntentionCode());
		ingredient.setIntentionSupplierCode(accounting.getIntentionSupplierCode());
		ingredient.setMerchandiseCode(accounting.getMerchandiseCode());
		ingredient.setSupplierCode(accounting.getSupplierCode());
		// 解析投料明细json
		Gson gson = new Gson();
		List<IngredientItem> ingredientItemList = new ArrayList<IngredientItem>();
		ingredientItemList = Arrays.asList(gson.fromJson(this.asString("ingredientItem"), IngredientItem[].class));
		// 投料表投入量/投入成本 - 合计
		this.deductIngredientItemCountAndCostSum(ingredientItemList);
		// 投入量占比 计算并赋值
		for (int i = 0; i < ingredientItemList.size(); i++) {
			// 投料编号赋值
			ingredientItemList.get(i).setIngredientCode(ingredient.getIngredientCode());
			// 投入量占比计算并赋值
			BigDecimal inputCount = ingredientItemList.get(i).getInputCount();
			if (null == inputCount || inputCount.compareTo(BigDecimal.ZERO) == 0 || ingredientItemInputCountSum.compareTo(BigDecimal.ZERO) == 0) {
				ingredientItemList.get(i).setInputCountProportion(new BigDecimal(0));
			} else {
				ingredientItemList.get(i).setInputCountProportion(ingredientItemList.get(i).getInputCount().multiply(new BigDecimal(100)).divide(ingredientItemInputCountSum, 2, BigDecimal.ROUND_HALF_UP));
			}
			// 投入成本占比计算并赋值
			BigDecimal inputCost = ingredientItemList.get(i).getInputCost();
			if (null == inputCost || inputCost.compareTo(BigDecimal.ZERO) == 0 || ingredientItemInputCostSum.compareTo(BigDecimal.ZERO) == 0) {
				ingredientItemList.get(i).setInputCostProportion(new BigDecimal(0));
			} else {
				ingredientItemList.get(i).setInputCostProportion(ingredientItemList.get(i).getInputCost().multiply(new BigDecimal(100)).divide(ingredientItemInputCostSum, 2, BigDecimal.ROUND_HALF_UP));
			}
		}
		List<AccountingRegion> accountingRegionListTemp = new ArrayList<AccountingRegion>();
		List<AccountingManageRegion> accountingManageRegionListTemp = new ArrayList<AccountingManageRegion>();
		List<AccountingFreightRegion> accountingFreightRegionListTemp = new ArrayList<AccountingFreightRegion>();
		List<AccountingTaxRegion> accountingTaxRegionListTemp = new ArrayList<AccountingTaxRegion>();
		List<AccountingProfitRegion> accountingProfitRegionListTemp = new ArrayList<AccountingProfitRegion>();
		List<AccountingElsesubtotalRegion> accountingElsesubtotalRegionListTemp = new ArrayList<AccountingElsesubtotalRegion>();
		List<AccountingAggregateRegion> accountingAggregateRegionListTemp = new ArrayList<AccountingAggregateRegion>();
		for (int i = 0; i < accountingRegionList.size(); i++) {
			if (accountingRegionList.get(i) == null) {
				continue;
			}
			accountingRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingManageRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingFreightRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingTaxRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingProfitRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingElsesubtotalRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingAggregateRegionList.get(i).setAccountingCode(accounting.getAccountingCode());
			accountingRegionListTemp.add(accountingRegionList.get(i));
			accountingManageRegionListTemp.add(accountingManageRegionList.get(i));
			accountingFreightRegionListTemp.add(accountingFreightRegionList.get(i));
			accountingTaxRegionListTemp.add(accountingTaxRegionList.get(i));
			accountingProfitRegionListTemp.add(accountingProfitRegionList.get(i));
			accountingElsesubtotalRegionListTemp.add(accountingElsesubtotalRegionList.get(i));
			accountingAggregateRegionListTemp.add(accountingAggregateRegionList.get(i));
		}
		List<AccountingNPackag> accountingNPackagListTemp = new ArrayList<AccountingNPackag>();
		for (AccountingNPackag a : accountingNPackagList) {
			if (a != null) {
				a.setAccountingCode(accounting.getAccountingCode());
				accountingNPackagListTemp.add(a);
			}
		}
		List<AccountingWPackag> accountingWPackagListTemp = new ArrayList<AccountingWPackag>();
		for (AccountingWPackag a : accountingWPackagList) {
			if (a != null) {
				a.setAccountingCode(accounting.getAccountingCode());
				accountingWPackagListTemp.add(a);
			}
		}
		List<AccountingWastage> accountingWastageListTemp = new ArrayList<AccountingWastage>();
		for (AccountingWastage a : accountingWastageList) {
			if (a != null) {
				a.setAccountingCode(accounting.getAccountingCode());
				accountingWastageListTemp.add(a);
			}
		}
		// 非进口
		accountingCostItem.setAccountingCode(accounting.getAccountingCode());
		accountingWec.setAccountingCode(accounting.getAccountingCode());
		accountingSbzjwh.setAccountingCode(accounting.getAccountingCode());
		accountingManpower.setAccountingCode(accounting.getAccountingCode());
		accountingManage.setAccountingCode(accounting.getAccountingCode());
		accountingFreight.setAccountingCode(accounting.getAccountingCode());
		accountingTax.setAccountingCode(accounting.getAccountingCode());
		accountingProfit.setAccountingCode(accounting.getAccountingCode());
		// 进口
		accountingFactoryPrice.setAccountingCode(accounting.getAccountingCode());
		accountingExchangerate.setAccountingCode(accounting.getAccountingCode());
		accountingOceanfreight.setAccountingCode(accounting.getAccountingCode());
		accountingCustomscharges.setAccountingCode(accounting.getAccountingCode());
		accountingCustomsduties.setAccountingCode(accounting.getAccountingCode());
		accountingAddedvaluetax.setAccountingCode(accounting.getAccountingCode());
		accountingTaxDiffer.setAccountingCode(accounting.getAccountingCode());
		accountingInterest.setAccountingCode(accounting.getAccountingCode());
		// 其他小计/总计
		accountingElsesubtotal.setAccountingCode(accounting.getAccountingCode());
		accountingAggregate.setAccountingCode(accounting.getAccountingCode());
		// 将核算各个表放入Map
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", accounting.getAccountingCode());
		map.put("ingredientCode", accounting.getAccountingCode());
		map.put("accounting", accounting);
		map.put("ingredient", ingredient);
		map.put("ingredientItemList", ingredientItemList);
		map.put("accountingRegionList", accountingRegionListTemp);
		map.put("accountingCostItem", accountingCostItem);
		map.put("accountingNPackagList", accountingNPackagListTemp);
		map.put("accountingWPackagList", accountingWPackagListTemp);
		map.put("accountingWastageList", accountingWastageListTemp);
		map.put("accountingWec", accountingWec);
		map.put("accountingSbzjwh", accountingSbzjwh);
		map.put("accountingManpower", accountingManpower);
		map.put("accountingManage", accountingManage);
		map.put("accountingManageRegionList", accountingManageRegionListTemp);
		map.put("accountingFreight", accountingFreight);
		map.put("accountingFreightRegionList", accountingFreightRegionListTemp);
		map.put("accountingTax", accountingTax);
		map.put("accountingTaxRegionList", accountingTaxRegionListTemp);
		map.put("accountingProfit", accountingProfit);
		map.put("accountingProfitRegionList", accountingProfitRegionListTemp);
		map.put("accountingFactoryPrice", accountingFactoryPrice);
		map.put("accountingExchangerate", accountingExchangerate);
		map.put("accountingOceanfreight", accountingOceanfreight);
		map.put("accountingCustomscharges", accountingCustomscharges);
		map.put("accountingCustomsduties", accountingCustomsduties);
		map.put("accountingAddedvaluetax", accountingAddedvaluetax);
		map.put("accountingTaxDiffer", accountingTaxDiffer);
		map.put("accountingInterest", accountingInterest);
		map.put("accountingElsesubtotal", accountingElsesubtotal);
		map.put("accountingElsesubtotalRegionList", accountingElsesubtotalRegionListTemp);
		map.put("accountingAggregate", accountingAggregate);
		map.put("accountingAggregateRegionList", accountingAggregateRegionListTemp);
		return map;
	}

	/**
	 * 查询所有核算表用于返回页面
	 */
	public void packageAccounting() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", accounting.getAccountingCode());
		map.put("ingredientCode", accounting.getAccountingCode());
		accounting = accountingService.loadAccounting(map);
		ingredient = ingredientService.loadIngredient(map);
		List<IngredientItem> ingredientItemList = ingredientService.listIngredientItem(map, null);
		this.deductIngredientItemCountAndCostSum(ingredientItemList);
		accountingRegionList = accountingService.loadAccountingRegion(map);
		accountingCostItem = accountingService.loadAccountingCostItem(map);
		accountingNPackagList = accountingService.loadAccountingNPackag(map);
		accountingWPackagList = accountingService.loadAccountingWPackag(map);
		accountingWastageList = accountingService.loadAccountingWastage(map);
		accountingWec = accountingService.loadAccountingWec(map);
		accountingSbzjwh = accountingService.loadAccountingSbzjwh(map);
		accountingManpower = accountingService.loadAccountingManpower(map);
		accountingManage = accountingService.loadAccountingManage(map);
		accountingManageRegionList = accountingService.loadAccountingManageRegion(map);
		accountingFreight = accountingService.loadAccountingFreight(map);
		accountingFreightRegionList = accountingService.loadAccountingFreightRegion(map);
		accountingTax = accountingService.loadAccountingTax(map);
		accountingTaxRegionList = accountingService.loadAccountingTaxRegion(map);
		accountingProfit = accountingService.loadAccountingProfit(map);
		accountingProfitRegionList = accountingService.loadAccountingProfitRegion(map);
		// 进口
		accountingFactoryPrice = accountingService.loadAccountingFactoryPrice(map);
		accountingExchangerate = accountingService.loadAccountingExchangerate(map);
		accountingOceanfreight = accountingService.loadAccountingOceanfreight(map);
		accountingCustomscharges = accountingService.loadAccountingCustomscharges(map);
		accountingCustomsduties = accountingService.loadAccountingCustomsduties(map);
		accountingAddedvaluetax = accountingService.loadAccountingAddedvaluetax(map);
		accountingTaxDiffer = accountingService.loadAccountingTaxDiffer(map);
		accountingInterest = accountingService.loadAccountingInterest(map);
		
		accountingElsesubtotal = accountingService.loadAccountingElsesubtotal(map);
		accountingElsesubtotalRegionList = accountingService.loadAccountingElsesubtotalRegion(map);
		accountingAggregate = accountingService.loadAccountingAggregate(map);
		accountingAggregateRegionList = accountingService.loadAccountingAggregateRegion(map);
		this.putObject("accountingBo", accountingService.getAccountingOaStatus(map));
		this.putObjectAccounting();
	}

	/**
	 * putObject所有核算表
	 */
	public void putObjectAccounting() {
		this.putObject("accounting", accounting);
		this.putObject("ingredient", ingredient);
		this.putObject("accountingRegionList", accountingRegionList);
		this.putObject("accountingCostItem", accountingCostItem);
		this.putObject("accountingNPackagList", accountingNPackagList);
		this.putObject("accountingWPackagList", accountingWPackagList);
		this.putObject("accountingWastageList", accountingWastageList);
		this.putObject("accountingWec", accountingWec);
		this.putObject("accountingSbzjwh", accountingSbzjwh);
		this.putObject("accountingManpower", accountingManpower);
		this.putObject("accountingManage", accountingManage);
		this.putObject("accountingManageRegionList", accountingManageRegionList);
		this.putObject("accountingFreight", accountingFreight);
		this.putObject("accountingFreightRegionList", accountingFreightRegionList);
		this.putObject("accountingTax", accountingTax);
		this.putObject("accountingTaxRegionList", accountingTaxRegionList);
		this.putObject("accountingProfit", accountingProfit);
		this.putObject("accountingProfitRegionList", accountingProfitRegionList);

		this.putObject("accountingFactoryPrice", accountingFactoryPrice);
		this.putObject("accountingExchangerate", accountingExchangerate);
		this.putObject("accountingOceanfreight", accountingOceanfreight);
		this.putObject("accountingCustomscharges", accountingCustomscharges);
		this.putObject("accountingCustomsduties", accountingCustomsduties);
		this.putObject("accountingAddedvaluetax", accountingAddedvaluetax);
		this.putObject("accountingTaxDiffer", accountingTaxDiffer);
		this.putObject("accountingInterest", accountingInterest);

		this.putObject("accountingElsesubtotal", accountingElsesubtotal);
		this.putObject("accountingElsesubtotalRegionList", accountingElsesubtotalRegionList);
		this.putObject("accountingAggregate", accountingAggregate);
		this.putObject("accountingAggregateRegionList", accountingAggregateRegionList);
		this.putObject("ingredientItemInputCountSum", ingredientItemInputCountSum);
		this.putObject("ingredientItemInputCostSum", ingredientItemInputCostSum);
		this.putObject("ingredientItemAvgCostSum", ingredientItemAvgCostSum);
	}

	/**
	 * 计算投料表投入量和投入成本合计
	 * 
	 * @param ingredientItemList
	 */
	public void deductIngredientItemCountAndCostSum(List<IngredientItem> ingredientItemList) throws Exception {
		ingredientItemInputCountSum = new BigDecimal(0);
		ingredientItemInputCostSum = new BigDecimal(0);
		ingredientItemAvgCostSum = new BigDecimal(0);
		for (IngredientItem ingredientItem : ingredientItemList) {
			ingredientItemInputCountSum = ingredientItemInputCountSum.add(ingredientItem.getInputCount());
			ingredientItemInputCostSum = ingredientItemInputCostSum.add(ingredientItem.getInputCost());
			ingredientItemAvgCostSum = ingredientItemAvgCostSum.add(ingredientItem.getAvgCost());
		}
	}

	/**
	 * 删除商品成本分析
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doDeleteAccounting() throws Exception {
		accountingService.deleteAccounting(asString("accountingCode"));
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 显示上传核算/投料表扫描版界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doShowUploadAccountingIngredientScanForm() throws Exception {
		this.putObject("scanType", this.asString("scanType"));
		this.putObject("accountingCode", this.asString("accountingCode"));
		this.putObject("intentionCode", this.asString("intentionCode"));
		this.putObject("intentionSupplierCode", this.asString("intentionSupplierCode"));
		this.putObject("merchandiseCode", this.asString("merchandiseCode"));
		this.putObject("supplierCode", this.asString("supplierCode"));
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/uploadScanForm.ftl");
	}

	/**
	 * 上传核算/投料表扫描版
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doUploadAccountingIngredientScan() throws Exception {
		String scanType = this.asString("scanType");
		String accountingFilePath = PathUtils.getAccountingFileUploadPath();
		List<File> fileList = this.doUploadBySaveDir(accountingFilePath.concat("/"));
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		// 文件路径
		String path = accountingFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(file.getName());
		Accounting accounting = new Accounting();
		AccountingIngredientScan accountingIngredientScan = new AccountingIngredientScan();
		this.asBean(accounting);
		this.asBean(accountingIngredientScan);
		if (scanType != null && scanType.equals("accounting")) {
			accountingIngredientScan.setAccountingScanPath(path);
		} else if (scanType != null && scanType.equals("ingredient")) {
			accountingIngredientScan.setIngredientScanPath(path);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map = accountingIngredientScan.toMap();
		map.put("scanType", scanType);
		map.put("accounting", accounting);
		// 保存扫描版至数据库，失败时删除已上传的文件
		try {
			accountingService.insertAccountingIngredientScan(map);
			this.forwardData(true, null, "文件导入成功！");
		} catch (Exception e) {
			throw new Exception(e);
			// 删除已上传的文件
		}
	}

	/**
	 * 下载核算/投料表扫描版
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doDownloadAccountingIngredientScan() throws Exception {
		Map<String, Object> map = getAccountingBo().toMap();
		AccountingBo accountingBo = accountingService.loadAccountingBo(map);
		String scanType = this.asString("scanType");
		String scanPath = null;
		String fileName;
		if (accountingBo.getMerchandiseCode() == null) {
			fileName = accountingBo.getIntentionName() + "_";
		} else {
			fileName = accountingBo.getMerchandiseName() + "_";
		}
		if (accountingBo.getSupplierCode() == null) {
			fileName += accountingBo.getIntentionSupplierName() + "_";
		} else {
			fileName += accountingBo.getSupplierName() + "_";
		}
		if (scanType != null && scanType.equals("accounting")) {
			scanPath = "upload/".concat(accountingBo.getAccountingScanPath());
			this.forwardDownload(scanPath, ExcelUtils.getEncodeFileName("扫描版核算表_" + fileName + DateUtils.formateDateTime() + "." + scanPath.substring(scanPath.lastIndexOf(".") + 1, scanPath.length())));
		} else if (scanType != null && scanType.equals("ingredient")) {
			scanPath = "upload/".concat(accountingBo.getIngredientScanPath());
			this.forwardDownload(scanPath, ExcelUtils.getEncodeFileName("扫描版投料表_" + fileName + DateUtils.formateDateTime() + "." + scanPath.substring(scanPath.lastIndexOf(".") + 1, scanPath.length())));
		}
	}

	/**
	 * 删除核算投料表扫描版
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient")
	public void doDeleteAccountingIngredientScan() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", this.asString("accountingCode"));
		map.put("scanType", this.asString("scanType"));
		accountingService.deleteAccountingIngredientScan(map);
		this.forwardData(true, null, this.getText("public.success"));
	}

	/**
	 * 显示上传核算表数据界面
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient.upload")
	public void doShowAccountDataForm() throws Exception {
		this.forwardPage("sco/merchandiseCostAnalysis/accountingIngredient/accounting/accountingUploadForm.ftl");
	}

	/**
	 * 上传核算数据
	 */
	@Authority(privilege = "com.powere2e.sco.merchandisecostanalysis.accountingingredient.upload")
	public void doCompleteImportAccountData() throws Exception {
		List<File> fileList = this.doUpload("XLSX");
		File file = null;
		try {
			file = fileList.get(0);
		} catch (Exception e) {
			this.forwardData(false, null, e.getMessage());
			return;
		}
		String msg = AccountingDataServiceImpl.getInstance().completeImportAccountingData(file);
		if (StringUtils.isBlank(msg)) {
			this.forwardData(true, null, "文件导入成功！");
		} else {
			this.forwardData(false, null, msg);
		}
	}

	/**
	 * 获得页面传递的与实体属性相关的值
	 * 
	 * @throws Exception
	 *             可能抛出Exception异常
	 */
	private AccountingBo getAccountingBo() throws Exception {
		AccountingBo accountingBo = new AccountingBo();
		this.asBean(accountingBo);
		return accountingBo;
	}

	public Accounting getAccounting() {
		return accounting;
	}

	public void setAccounting(Accounting accounting) {
		this.accounting = accounting;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public List<AccountingRegion> getAccountingRegionList() {
		return accountingRegionList;
	}

	public void setAccountingRegionList(List<AccountingRegion> accountingRegionList) {
		this.accountingRegionList = accountingRegionList;
	}

	public AccountingCostItem getAccountingCostItem() {
		return accountingCostItem;
	}

	public void setAccountingCostItem(AccountingCostItem accountingCostItem) {
		this.accountingCostItem = accountingCostItem;
	}

	public List<AccountingNPackag> getAccountingNPackagList() {
		return accountingNPackagList;
	}

	public void setAccountingNPackagList(List<AccountingNPackag> accountingNPackagList) {
		this.accountingNPackagList = accountingNPackagList;
	}

	public List<AccountingWPackag> getAccountingWPackagList() {
		return accountingWPackagList;
	}

	public void setAccountingWPackagList(List<AccountingWPackag> accountingWPackagList) {
		this.accountingWPackagList = accountingWPackagList;
	}

	public List<AccountingWastage> getAccountingWastageList() {
		return accountingWastageList;
	}

	public void setAccountingWastageList(List<AccountingWastage> accountingWastageList) {
		this.accountingWastageList = accountingWastageList;
	}

	public AccountingWec getAccountingWec() {
		return accountingWec;
	}

	public void setAccountingWec(AccountingWec accountingWec) {
		this.accountingWec = accountingWec;
	}

	public AccountingSbzjwh getAccountingSbzjwh() {
		return accountingSbzjwh;
	}

	public void setAccountingSbzjwh(AccountingSbzjwh accountingSbzjwh) {
		this.accountingSbzjwh = accountingSbzjwh;
	}

	public AccountingManpower getAccountingManpower() {
		return accountingManpower;
	}

	public void setAccountingManpower(AccountingManpower accountingManpower) {
		this.accountingManpower = accountingManpower;
	}

	public AccountingManage getAccountingManage() {
		return accountingManage;
	}

	public void setAccountingManage(AccountingManage accountingManage) {
		this.accountingManage = accountingManage;
	}

	public List<AccountingManageRegion> getAccountingManageRegionList() {
		return accountingManageRegionList;
	}

	public void setAccountingManageRegionList(List<AccountingManageRegion> accountingManageRegionList) {
		this.accountingManageRegionList = accountingManageRegionList;
	}

	public AccountingFreight getAccountingFreight() {
		return accountingFreight;
	}

	public void setAccountingFreight(AccountingFreight accountingFreight) {
		this.accountingFreight = accountingFreight;
	}

	public List<AccountingFreightRegion> getAccountingFreightRegionList() {
		return accountingFreightRegionList;
	}

	public void setAccountingFreightRegionList(List<AccountingFreightRegion> accountingFreightRegionList) {
		this.accountingFreightRegionList = accountingFreightRegionList;
	}

	public AccountingTax getAccountingTax() {
		return accountingTax;
	}

	public void setAccountingTax(AccountingTax accountingTax) {
		this.accountingTax = accountingTax;
	}

	public List<AccountingTaxRegion> getAccountingTaxRegionList() {
		return accountingTaxRegionList;
	}

	public void setAccountingTaxRegionList(List<AccountingTaxRegion> accountingTaxRegionList) {
		this.accountingTaxRegionList = accountingTaxRegionList;
	}

	public AccountingProfit getAccountingProfit() {
		return accountingProfit;
	}

	public void setAccountingProfit(AccountingProfit accountingProfit) {
		this.accountingProfit = accountingProfit;
	}

	public List<AccountingProfitRegion> getAccountingProfitRegionList() {
		return accountingProfitRegionList;
	}

	public void setAccountingProfitRegionList(List<AccountingProfitRegion> accountingProfitRegionList) {
		this.accountingProfitRegionList = accountingProfitRegionList;
	}

	public AccountingElsesubtotal getAccountingElsesubtotal() {
		return accountingElsesubtotal;
	}

	public void setAccountingElsesubtotal(AccountingElsesubtotal accountingElsesubtotal) {
		this.accountingElsesubtotal = accountingElsesubtotal;
	}

	public List<AccountingElsesubtotalRegion> getAccountingElsesubtotalRegionList() {
		return accountingElsesubtotalRegionList;
	}

	public void setAccountingElsesubtotalRegionList(List<AccountingElsesubtotalRegion> accountingElsesubtotalRegionList) {
		this.accountingElsesubtotalRegionList = accountingElsesubtotalRegionList;
	}

	public AccountingAggregate getAccountingAggregate() {
		return accountingAggregate;
	}

	public void setAccountingAggregate(AccountingAggregate accountingAggregate) {
		this.accountingAggregate = accountingAggregate;
	}

	public List<AccountingAggregateRegion> getAccountingAggregateRegionList() {
		return accountingAggregateRegionList;
	}

	public void setAccountingAggregateRegionList(List<AccountingAggregateRegion> accountingAggregateRegionList) {
		this.accountingAggregateRegionList = accountingAggregateRegionList;
	}

	public AccountingFactoryPrice getAccountingFactoryPrice() {
		return accountingFactoryPrice;
	}

	public void setAccountingFactoryPrice(AccountingFactoryPrice accountingFactoryPrice) {
		this.accountingFactoryPrice = accountingFactoryPrice;
	}

	public AccountingExchangerate getAccountingExchangerate() {
		return accountingExchangerate;
	}

	public void setAccountingExchangerate(AccountingExchangerate accountingExchangerate) {
		this.accountingExchangerate = accountingExchangerate;
	}

	public AccountingOceanfreight getAccountingOceanfreight() {
		return accountingOceanfreight;
	}

	public void setAccountingOceanfreight(AccountingOceanfreight accountingOceanfreight) {
		this.accountingOceanfreight = accountingOceanfreight;
	}

	public AccountingCustomscharges getAccountingCustomscharges() {
		return accountingCustomscharges;
	}

	public void setAccountingCustomscharges(AccountingCustomscharges accountingCustomscharges) {
		this.accountingCustomscharges = accountingCustomscharges;
	}

	public AccountingCustomsduties getAccountingCustomsduties() {
		return accountingCustomsduties;
	}

	public void setAccountingCustomsduties(AccountingCustomsduties accountingCustomsduties) {
		this.accountingCustomsduties = accountingCustomsduties;
	}

	public AccountingAddedvaluetax getAccountingAddedvaluetax() {
		return accountingAddedvaluetax;
	}

	public void setAccountingAddedvaluetax(AccountingAddedvaluetax accountingAddedvaluetax) {
		this.accountingAddedvaluetax = accountingAddedvaluetax;
	}

	public AccountingTaxDiffer getAccountingTaxDiffer() {
		return accountingTaxDiffer;
	}

	public void setAccountingTaxDiffer(AccountingTaxDiffer accountingTaxDiffer) {
		this.accountingTaxDiffer = accountingTaxDiffer;
	}

	public AccountingInterest getAccountingInterest() {
		return accountingInterest;
	}

	public void setAccountingInterest(AccountingInterest accountingInterest) {
		this.accountingInterest = accountingInterest;
	}

	public BigDecimal getIngredientItemInputCountSum() {
		return ingredientItemInputCountSum;
	}

	public void setIngredientItemInputCountSum(BigDecimal ingredientItemInputCountSum) {
		this.ingredientItemInputCountSum = ingredientItemInputCountSum;
	}

	public BigDecimal getIngredientItemInputCostSum() {
		return ingredientItemInputCostSum;
	}

	public void setIngredientItemInputCostSum(BigDecimal ingredientItemInputCostSum) {
		this.ingredientItemInputCostSum = ingredientItemInputCostSum;
	}
}
