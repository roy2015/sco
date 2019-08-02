package com.powere2e.sco.dao.impl.merchandisecostanalysis.accountingIngredient.accounting;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDao;
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

/**
 * 商品核算表DAO接口的实现
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public class AccountingDaoImpl extends DaoImpl implements AccountingDao {

	private static final long serialVersionUID = 1864966877246953337L;

	// 查询
	@Override
	public List<AccountingBo> listAccounting(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(AccountingDao.class, "searchAccountingList", map, pageInfo);
	}

	// 装载一个商品核算表情况
	@Override
	public AccountingBo loadAccountingBo(Map<String, Object> map) {
		return (AccountingBo) this.get(AccountingDao.class, "searchAccountingBo", map);
	}

	// 装载一个核算总表
	@Override
	public Accounting loadAccounting(Map<String, Object> map) {
		return (Accounting) this.get(AccountingDao.class, "loadAccounting", map);
	}

	@Override
	public AccountingBo getAccountingOaStatus(Map<String, Object> map) {
		return (AccountingBo) this.get(AccountingDao.class, "getAccountingOaStatus", map);
	}

	// 修改
	@Override
	public void updateAccounting(Map<String, Object> map) {
		this.update(AccountingDao.class, "updateAccounting", map);
	}

	// 修改
	@Override
	public void updateAccountingOfOaWl(Map<String, Object> map) {
		this.update(AccountingDao.class, "updateAccountingOfOaWl", map);
	}

	// 修改
	@Override
	public void updateAccountingIngredientScan(Map<String, Object> map) {
		this.update(AccountingDao.class, "updateAccountingIngredientScan", map);
	}

	@Override
	public String searchAccountingCodeByMerchandiseCode(Map<String, Object> map) {
		return (String) this.get(AccountingDao.class, "searchAccountingCodeByMerchandiseCode", map);
	}

	@Override
	public void insertAccounting(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccounting", map);
	}

	@Override
	public void insertAccountingRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingRegion", map);

	}

	@Override
	public void insertAccountingCostItem(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingCostItem", map);
	}

	@Override
	public void insertAccountingNPackag(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingNPackag", map);
	}

	@Override
	public void insertAccountingWPackag(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingWPackag", map);
	}

	@Override
	public void insertAccountingWastage(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingWastage", map);
	}

	@Override
	public void insertAccountingWec(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingWec", map);
	}

	@Override
	public void insertAccountingSbzjwh(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingSbzjwh", map);
	}

	@Override
	public void insertAccountingManpower(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingManpower", map);
	}

	@Override
	public void insertAccountingManage(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingManage", map);
	}

	@Override
	public void insertAccountingManageRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingManageRegion", map);
	}

	@Override
	public void insertAccountingFreight(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingFreight", map);
	}

	@Override
	public void insertAccountingFreightRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingFreightRegion", map);
	}

	@Override
	public void insertAccountingTax(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingTax", map);
	}

	@Override
	public void insertAccountingTaxRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingTaxRegion", map);
	}

	@Override
	public void insertAccountingProfit(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingProfit", map);
	}

	@Override
	public void insertAccountingProfitRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingProfitRegion", map);
	}

	@Override
	public void insertAccountingFactoryPrice(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingFactoryPrice", map);
	}

	@Override
	public void insertAccountingExchangerate(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingExchangerate", map);
	}

	@Override
	public void insertAccountingOceanfreight(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingOceanfreight", map);
	}

	@Override
	public void insertAccountingCustomscharges(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingCustomscharges", map);
	}

	@Override
	public void insertAccountingCustomsduties(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingCustomsduties", map);
	}

	@Override
	public void insertAccountingAddedvaluetax(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingAddedvaluetax", map);
	}

	@Override
	public void insertAccountingTaxDiffer(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingTaxDiffer", map);
	}

	@Override
	public void insertAccountingInterest(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingInterest", map);
	}

	@Override
	public void insertAccountingElsesubtotal(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingElsesubtotal", map);
	}

	@Override
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingElsesubtotalRegion", map);
	}

	@Override
	public void insertAccountingAggregate(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingAggregate", map);
	}

	@Override
	public void insertAccountingAggregateRegion(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingAggregateRegion", map);
	}

	@Override
	public void insertAccountingIngredientScan(Map<String, Object> map) {
		this.insert(AccountingDao.class, "saveAccountingIngredientScan", map);
	}

	@Override
	public void deleteAccounting(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccounting", map);
	}

	@Override
	public void deleteAccountingRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingRegion", map);
	}

	@Override
	public void deleteAccountingCostItem(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingCostItem", map);
	}

	@Override
	public void deleteAccountingNPackag(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingNPackag", map);
	}

	@Override
	public void deleteAccountingWPackag(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingWPackag", map);
	}

	@Override
	public void deleteAccountingWastage(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingWastage", map);
	}

	@Override
	public void deleteAccountingWec(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingWec", map);
	}

	@Override
	public void deleteAccountingSbzjwh(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingSbzjwh", map);
	}

	@Override
	public void deleteAccountingManpower(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingManpower", map);
	}

	@Override
	public void deleteAccountingManage(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingManage", map);
	}

	@Override
	public void deleteAccountingManageRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingManageRegion", map);
	}

	@Override
	public void deleteAccountingFreight(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingFreight", map);
	}

	@Override
	public void deleteAccountingFreightRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingFreightRegion", map);
	}

	@Override
	public void deleteAccountingTax(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingTax", map);
	}

	@Override
	public void deleteAccountingTaxRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingTaxRegion", map);
	}

	@Override
	public void deleteAccountingProfit(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingProfit", map);
	}

	@Override
	public void deleteAccountingProfitRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingProfitRegion", map);
	}

	@Override
	public void deleteAccountingFactoryPrice(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingFactoryPrice", map);
	}

	@Override
	public void deleteAccountingExchangerate(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingExchangerate", map);
	}

	@Override
	public void deleteAccountingOceanfreight(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingOceanfreight", map);
	}

	@Override
	public void deleteAccountingCustomscharges(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingCustomscharges", map);
	}

	@Override
	public void deleteAccountingCustomsduties(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingCustomsduties", map);
	}

	@Override
	public void deleteAccountingAddedvaluetax(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingAddedvaluetax", map);
	}

	@Override
	public void deleteAccountingTaxDiffer(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingTaxDiffer", map);
	}

	@Override
	public void deleteAccountingInterest(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingInterest", map);
	}

	@Override
	public void deleteAccountingElsesubtotal(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingElsesubtotal", map);
	}

	@Override
	public void deleteAccountingElsesubtotalRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingElsesubtotalRegion", map);
	}

	@Override
	public void deleteAccountingAggregate(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingAggregate", map);
	}

	@Override
	public void deleteAccountingAggregateRegion(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingAggregateRegion", map);
	}

	@Override
	public void deleteAccountingIngredientScan(Map<String, Object> map) {
		this.delete(AccountingDao.class, "deleteAccountingIngredientScan", map);
	}

	@Override
	public List<AccountingRegion> loadAccountingRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingRegion", map, null);
	}

	@Override
	public AccountingCostItem loadAccountingCostItem(Map<String, Object> map) {
		return (AccountingCostItem) this.get(AccountingDao.class, "loadAccountingCostItem", map);
	}

	@Override
	public List<AccountingNPackag> loadAccountingNPackag(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingNPackag", map, null);
	}

	@Override
	public List<AccountingWPackag> loadAccountingWPackag(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingWPackag", map, null);
	}

	@Override
	public List<AccountingWastage> loadAccountingWastage(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingWastage", map, null);
	}

	@Override
	public AccountingWec loadAccountingWec(Map<String, Object> map) {
		return (AccountingWec) this.get(AccountingDao.class, "loadAccountingWec", map);
	}

	@Override
	public AccountingSbzjwh loadAccountingSbzjwh(Map<String, Object> map) {
		return (AccountingSbzjwh) this.get(AccountingDao.class, "loadAccountingSbzjwh", map);
	}

	@Override
	public AccountingManpower loadAccountingManpower(Map<String, Object> map) {
		return (AccountingManpower) this.get(AccountingDao.class, "loadAccountingManpower", map);
	}

	@Override
	public AccountingManage loadAccountingManage(Map<String, Object> map) {
		return (AccountingManage) this.get(AccountingDao.class, "loadAccountingManage", map);
	}

	@Override
	public List<AccountingManageRegion> loadAccountingManageRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingManageRegion", map, null);
	}

	@Override
	public AccountingFreight loadAccountingFreight(Map<String, Object> map) {
		return (AccountingFreight) this.get(AccountingDao.class, "loadAccountingFreight", map);
	}

	@Override
	public List<AccountingFreightRegion> loadAccountingFreightRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingFreightRegion", map, null);
	}

	@Override
	public AccountingTax loadAccountingTax(Map<String, Object> map) {
		return (AccountingTax) this.get(AccountingDao.class, "loadAccountingTax", map);
	}

	@Override
	public List<AccountingTaxRegion> loadAccountingTaxRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingTaxRegion", map, null);
	}

	@Override
	public AccountingProfit loadAccountingProfit(Map<String, Object> map) {
		return (AccountingProfit) this.get(AccountingDao.class, "loadAccountingProfit", map);
	}

	@Override
	public List<AccountingProfitRegion> loadAccountingProfitRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingProfitRegion", map, null);
	}

	@Override
	public AccountingFactoryPrice loadAccountingFactoryPrice(Map<String, Object> map) {
		return (AccountingFactoryPrice) this.get(AccountingDao.class, "loadAccountingFactoryPrice", map);
	}

	@Override
	public AccountingExchangerate loadAccountingExchangerate(Map<String, Object> map) {
		return (AccountingExchangerate) this.get(AccountingDao.class, "loadAccountingExchangerate", map);
	}

	@Override
	public AccountingOceanfreight loadAccountingOceanfreight(Map<String, Object> map) {
		return (AccountingOceanfreight) this.get(AccountingDao.class, "loadAccountingOceanfreight", map);
	}

	@Override
	public AccountingCustomscharges loadAccountingCustomscharges(Map<String, Object> map) {
		return (AccountingCustomscharges) this.get(AccountingDao.class, "loadAccountingCustomscharges", map);
	}

	@Override
	public AccountingCustomsduties loadAccountingCustomsduties(Map<String, Object> map) {
		return (AccountingCustomsduties) this.get(AccountingDao.class, "loadAccountingCustomsduties", map);
	}

	@Override
	public AccountingAddedvaluetax loadAccountingAddedvaluetax(Map<String, Object> map) {
		return (AccountingAddedvaluetax) this.get(AccountingDao.class, "loadAccountingAddedvaluetax", map);
	}

	@Override
	public AccountingTaxDiffer loadAccountingTaxDiffer(Map<String, Object> map) {
		return (AccountingTaxDiffer) this.get(AccountingDao.class, "loadAccountingTaxDiffer", map);
	}

	@Override
	public AccountingInterest loadAccountingInterest(Map<String, Object> map) {
		return (AccountingInterest) this.get(AccountingDao.class, "loadAccountingInterest", map);
	}

	@Override
	public AccountingElsesubtotal loadAccountingElsesubtotal(Map<String, Object> map) {
		return (AccountingElsesubtotal) this.get(AccountingDao.class, "loadAccountingElsesubtotal", map);
	}

	@Override
	public List<AccountingElsesubtotalRegion> loadAccountingElsesubtotalRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingElsesubtotalRegion", map, null);
	}

	@Override
	public AccountingAggregate loadAccountingAggregate(Map<String, Object> map) {
		return (AccountingAggregate) this.get(AccountingDao.class, "loadAccountingAggregate", map);
	}

	@Override
	public List<AccountingAggregateRegion> loadAccountingAggregateRegion(Map<String, Object> map) {
		return this.query(AccountingDao.class, "loadAccountingAggregateRegion", map, null);
	}

	@Override
	public AccountingIngredientScan loadAccountingIngredientScan(Map<String, Object> map) {
		return (AccountingIngredientScan) this.get(AccountingDao.class, "loadAccountingIngredientScan", map);
	}
}