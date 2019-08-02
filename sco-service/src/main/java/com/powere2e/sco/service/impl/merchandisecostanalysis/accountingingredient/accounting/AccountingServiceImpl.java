package com.powere2e.sco.service.impl.merchandisecostanalysis.accountingingredient.accounting;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.utils.FileUtils;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDao;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.ingredient.IngredientDao;
import com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.accounting.AccountingService;
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
import com.powere2e.sco.model.merchandisecostanalysis.accountingingredient.ingredient.IngredientItem;

/**
 * 商品核算表业务类的实现
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccountingServiceImpl extends ServiceImpl implements AccountingService {

	private static final long serialVersionUID = -6985893755909439711L;
	private AccountingDao accountingDao;
	private IngredientDao ingredientDao;

	public static AccountingService getInstance() {
		return (AccountingService) ConfigFactory.getInstance().getBean("accountingService");
	}

	@Override
	public List<AccountingBo> listAccounting(Map<String, Object> map, PageInfo pageInfo) {
		return this.accountingDao.listAccounting(map, pageInfo);
	}

	@Override
	public Accounting loadAccounting(Map<String, Object> map) {
		return this.accountingDao.loadAccounting(map);
	}

	@Override
	public AccountingBo loadAccountingBo(Map<String, Object> map) {
		return this.accountingDao.loadAccountingBo(map);
	}

	@Override
	public AccountingIngredientScan loadAccountingIngredientScan(Map<String, Object> map) {
		return this.accountingDao.loadAccountingIngredientScan(map);
	}

	@Override
	public AccountingBo getAccountingOaStatus(Map<String, Object> map) {
		return this.accountingDao.getAccountingOaStatus(map);
	}

	@Override
	public void insertAccounting(Map<String, Object> map) {
		Accounting accounting = this.loadAccounting(map);
		// 判断核算表是否存在
		if (accounting != null && accounting.getAccountingCode() != null) {
			this.accountingDao.updateAccounting(map);
			this.deleteAllAccounting(map);
		} else {
			this.accountingDao.insertAccounting(map);
		}
		this.insertAllAccounting(map);
	}

	@Override
	public void insertAccountingIngredientScan(Map<String, Object> map) throws Exception {
		Accounting accounting = this.loadAccounting(map);
		// 判断是否有核算表
		if (accounting == null) {
			this.accountingDao.insertAccounting(map);
		}
		AccountingIngredientScan accountingIngredientScan = this.accountingDao.loadAccountingIngredientScan(map);
		// 判断是否有上传过扫描版文件
		if (accountingIngredientScan != null && accountingIngredientScan.getAccountingCode() != null) {
			// 判断扫描版文件类型
			if (map.get("scanType").equals("accounting")) {
				map.put("ingredientScanPath", accountingIngredientScan.getIngredientScanPath());
			} else if (map.get("scanType").equals("ingredient")) {
				map.put("accountingScanPath", accountingIngredientScan.getAccountingScanPath());
			}
			this.accountingDao.updateAccountingIngredientScan(map);
		} else {
			this.accountingDao.insertAccountingIngredientScan(map);
		}
	}

	@Override
	public void updateAccounting(Map<String, Object> map) {
		this.accountingDao.updateAccounting(map);
		this.deleteAllAccounting(map);
		this.insertAllAccounting(map);
	}

	@Override
	public void deleteAccounting(String accountingCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("accountingCode", accountingCode);
		map.put("ingredientCode", accountingCode);
		this.accountingDao.deleteAccounting(map);
		this.accountingDao.deleteAccountingIngredientScan(map);
		this.deleteAllAccounting(map);
	}

	@Override
	public void deleteAccountingIngredientScan(Map<String, Object> map) {
		AccountingIngredientScan accountingIngredientScan = this.accountingDao.loadAccountingIngredientScan(map);
		// 判断扫描版是否存在
		if (accountingIngredientScan != null && accountingIngredientScan.getAccountingCode() != null) {
			// 判断扫描版类型
			if (map.get("scanType").equals("accounting")) {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(accountingIngredientScan.getAccountingScanPath()));
				accountingIngredientScan.setAccountingScanPath("");
			} else if (map.get("scanType").equals("ingredient")) {
				FileUtils.deleteFile(ConfigPath.getUploadFilePath().concat(accountingIngredientScan.getIngredientScanPath()));
				accountingIngredientScan.setIngredientScanPath("");
			}
			this.accountingDao.updateAccountingIngredientScan(accountingIngredientScan.toMap());
		}
	}

	/**
	 * 新增非进口核算表其他所有表
	 * 
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	public void insertAllAccounting(Map<String, Object> map) {
		this.ingredientDao.insertIngredient(map);
		// 判断投料表明细是否为空
		if (((List<IngredientItem>) map.get("ingredientItemList")).size() > 0) {
			this.ingredientDao.insertIngredientItem(map);
		}
		this.accountingDao.insertAccountingRegion(map);
		this.accountingDao.insertAccountingCostItem(map);
		this.accountingDao.insertAccountingNPackag(map);
		this.accountingDao.insertAccountingWPackag(map);
		this.accountingDao.insertAccountingWastage(map);
		this.accountingDao.insertAccountingWec(map);
		this.accountingDao.insertAccountingSbzjwh(map);
		this.accountingDao.insertAccountingManpower(map);
		this.accountingDao.insertAccountingManage(map);
		this.accountingDao.insertAccountingManageRegion(map);
		this.accountingDao.insertAccountingFreight(map);
		this.accountingDao.insertAccountingFreightRegion(map);
		this.accountingDao.insertAccountingTax(map);
		this.accountingDao.insertAccountingTaxRegion(map);
		this.accountingDao.insertAccountingProfit(map);
		this.accountingDao.insertAccountingProfitRegion(map);
		this.accountingDao.insertAccountingElsesubtotal(map);
		this.accountingDao.insertAccountingElsesubtotalRegion(map);
		this.accountingDao.insertAccountingAggregate(map);
		this.accountingDao.insertAccountingAggregateRegion(map);
		// 判断是否为进口核算表
		if (((Accounting) map.get("accounting")).getInlandImport().equals(BusinessConstants.InlandImport.IMPORT.toString())) {
			this.accountingDao.insertAccountingFactoryPrice(map);
			this.accountingDao.insertAccountingExchangerate(map);
			this.accountingDao.insertAccountingOceanfreight(map);
			this.accountingDao.insertAccountingCustomscharges(map);
			this.accountingDao.insertAccountingCustomsduties(map);
			this.accountingDao.insertAccountingAddedvaluetax(map);
			this.accountingDao.insertAccountingTaxDiffer(map);
			this.accountingDao.insertAccountingInterest(map);
		}
	}

	/**
	 * 删除核算表其他所有表
	 * 
	 * @param map
	 */
	public void deleteAllAccounting(Map<String, Object> map) {
		this.ingredientDao.deleteIngredient(map);
		this.ingredientDao.deleteIngredientItem(map);
		this.accountingDao.deleteAccountingRegion(map);
		this.accountingDao.deleteAccountingCostItem(map);
		this.accountingDao.deleteAccountingNPackag(map);
		this.accountingDao.deleteAccountingWPackag(map);
		this.accountingDao.deleteAccountingWastage(map);
		this.accountingDao.deleteAccountingWec(map);
		this.accountingDao.deleteAccountingSbzjwh(map);
		this.accountingDao.deleteAccountingManpower(map);
		this.accountingDao.deleteAccountingManage(map);
		this.accountingDao.deleteAccountingManageRegion(map);
		this.accountingDao.deleteAccountingFreight(map);
		this.accountingDao.deleteAccountingFreightRegion(map);
		this.accountingDao.deleteAccountingTax(map);
		this.accountingDao.deleteAccountingTaxRegion(map);
		this.accountingDao.deleteAccountingProfit(map);
		this.accountingDao.deleteAccountingProfitRegion(map);
		this.accountingDao.deleteAccountingElsesubtotal(map);
		this.accountingDao.deleteAccountingElsesubtotalRegion(map);
		this.accountingDao.deleteAccountingAggregate(map);
		this.accountingDao.deleteAccountingAggregateRegion(map);
		// 进口
		this.accountingDao.deleteAccountingFactoryPrice(map);
		this.accountingDao.deleteAccountingExchangerate(map);
		this.accountingDao.deleteAccountingOceanfreight(map);
		this.accountingDao.deleteAccountingCustomscharges(map);
		this.accountingDao.deleteAccountingCustomsduties(map);
		this.accountingDao.deleteAccountingAddedvaluetax(map);
		this.accountingDao.deleteAccountingTaxDiffer(map);
		this.accountingDao.deleteAccountingInterest(map);
	}

	@Override
	public void updateAccountingOfOaWl(String applicationCode, String merchandiseCode, String supplierCode, String wlMerchandiseCode, String wlSupplierCode) {
		if (StringUtils.isNotEmpty(applicationCode) && StringUtils.isNotEmpty(merchandiseCode) && StringUtils.isNotEmpty(supplierCode) && StringUtils.isNotEmpty(wlMerchandiseCode) && StringUtils.isNotEmpty(wlSupplierCode)) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("applicationCode", applicationCode);
			map.put("merchandiseCode", merchandiseCode);
			map.put("supplierCode", supplierCode);
			map.put("wlMerchandiseCode", wlMerchandiseCode);
			map.put("wlSupplierCode", wlSupplierCode);
			this.accountingDao.updateAccountingOfOaWl(map);
		}
	}

	@Override
	public List<AccountingRegion> loadAccountingRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingRegion(map);
	}

	@Override
	public AccountingCostItem loadAccountingCostItem(Map<String, Object> map) {
		return this.accountingDao.loadAccountingCostItem(map);
	}

	@Override
	public List<AccountingNPackag> loadAccountingNPackag(Map<String, Object> map) {
		return this.accountingDao.loadAccountingNPackag(map);
	}

	@Override
	public List<AccountingWPackag> loadAccountingWPackag(Map<String, Object> map) {
		return this.accountingDao.loadAccountingWPackag(map);
	}

	@Override
	public List<AccountingWastage> loadAccountingWastage(Map<String, Object> map) {
		return this.accountingDao.loadAccountingWastage(map);
	}

	@Override
	public AccountingWec loadAccountingWec(Map<String, Object> map) {
		return this.accountingDao.loadAccountingWec(map);
	}

	@Override
	public AccountingSbzjwh loadAccountingSbzjwh(Map<String, Object> map) {
		return this.accountingDao.loadAccountingSbzjwh(map);
	}

	@Override
	public AccountingManpower loadAccountingManpower(Map<String, Object> map) {
		return this.accountingDao.loadAccountingManpower(map);
	}

	@Override
	public AccountingManage loadAccountingManage(Map<String, Object> map) {
		return this.accountingDao.loadAccountingManage(map);
	}

	@Override
	public List<AccountingManageRegion> loadAccountingManageRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingManageRegion(map);
	}

	@Override
	public AccountingFreight loadAccountingFreight(Map<String, Object> map) {
		return this.accountingDao.loadAccountingFreight(map);
	}

	@Override
	public List<AccountingFreightRegion> loadAccountingFreightRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingFreightRegion(map);
	}

	@Override
	public AccountingTax loadAccountingTax(Map<String, Object> map) {
		return this.accountingDao.loadAccountingTax(map);
	}

	@Override
	public List<AccountingTaxRegion> loadAccountingTaxRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingTaxRegion(map);
	}

	@Override
	public AccountingProfit loadAccountingProfit(Map<String, Object> map) {
		return this.accountingDao.loadAccountingProfit(map);
	}

	@Override
	public List<AccountingProfitRegion> loadAccountingProfitRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingProfitRegion(map);
	}

	@Override
	public AccountingFactoryPrice loadAccountingFactoryPrice(Map<String, Object> map) {
		return this.accountingDao.loadAccountingFactoryPrice(map);
	}

	@Override
	public AccountingExchangerate loadAccountingExchangerate(Map<String, Object> map) {
		return this.accountingDao.loadAccountingExchangerate(map);
	}

	@Override
	public AccountingOceanfreight loadAccountingOceanfreight(Map<String, Object> map) {
		return this.accountingDao.loadAccountingOceanfreight(map);
	}

	@Override
	public AccountingCustomscharges loadAccountingCustomscharges(Map<String, Object> map) {
		return this.accountingDao.loadAccountingCustomscharges(map);
	}

	@Override
	public AccountingCustomsduties loadAccountingCustomsduties(Map<String, Object> map) {
		return this.accountingDao.loadAccountingCustomsduties(map);
	}

	@Override
	public AccountingAddedvaluetax loadAccountingAddedvaluetax(Map<String, Object> map) {
		return this.accountingDao.loadAccountingAddedvaluetax(map);
	}

	@Override
	public AccountingTaxDiffer loadAccountingTaxDiffer(Map<String, Object> map) {
		return this.accountingDao.loadAccountingTaxDiffer(map);
	}

	@Override
	public AccountingInterest loadAccountingInterest(Map<String, Object> map) {
		return this.accountingDao.loadAccountingInterest(map);
	}

	@Override
	public AccountingElsesubtotal loadAccountingElsesubtotal(Map<String, Object> map) {
		return this.accountingDao.loadAccountingElsesubtotal(map);
	}

	@Override
	public List<AccountingElsesubtotalRegion> loadAccountingElsesubtotalRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingElsesubtotalRegion(map);
	}

	@Override
	public AccountingAggregate loadAccountingAggregate(Map<String, Object> map) {
		return this.accountingDao.loadAccountingAggregate(map);
	}

	@Override
	public List<AccountingAggregateRegion> loadAccountingAggregateRegion(Map<String, Object> map) {
		return this.accountingDao.loadAccountingAggregateRegion(map);
	}

	public AccountingDao getAccountingDao() {
		return accountingDao;
	}

	public void setAccountingDao(AccountingDao accountingDao) {
		this.accountingDao = accountingDao;
	}

	public IngredientDao getIngredientDao() {
		return ingredientDao;
	}

	public void setIngredientDao(IngredientDao ingredientDao) {
		this.ingredientDao = ingredientDao;
	}
}