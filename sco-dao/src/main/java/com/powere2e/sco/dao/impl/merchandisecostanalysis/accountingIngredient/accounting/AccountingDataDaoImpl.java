package com.powere2e.sco.dao.impl.merchandisecostanalysis.accountingIngredient.accounting;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting.AccountingDataDao;

/**
 * 核算表内包装DAO接口的实现
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月17日
 */
public class AccountingDataDaoImpl extends DaoImpl implements AccountingDataDao {

	private static final long serialVersionUID = -4052500177541249352L;

	@Override
	public void insertIngredientItem(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveIngredientItem", map);
	}

	@Override
	public void insertIngredient(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveIngredient", map);
	}

	@Override
	public void insertAccounting(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccounting", map);
	}

	@Override
	public void insertAccountingRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingRegion", map);

	}

	@Override
	public void insertAccountingCostItem(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingCostItem", map);
	}

	@Override
	public void insertAccountingNpackag(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingNpackag", map);
	}

	@Override
	public void insertAccountingWpackag(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingWpackag", map);
	}

	@Override
	public void insertAccountingWastage(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingWastage", map);
	}

	@Override
	public void insertAccountingWec(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingWec", map);
	}

	@Override
	public void insertAccountingSbzjwh(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingSbzjwh", map);
	}

	@Override
	public void insertAccountingManpower(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingManpower", map);
	}

	@Override
	public void insertAccountingManage(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingManage", map);
	}

	@Override
	public void insertAccountingManageRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingManageRegion", map);
	}

	@Override
	public void insertAccountingFreight(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingFreight", map);
	}

	@Override
	public void insertAccountingFreightRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingFreightRegion", map);
	}

	@Override
	public void insertAccountingTax(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingTax", map);
	}

	@Override
	public void insertAccountingTaxRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingTaxRegion", map);
	}

	@Override
	public void insertAccountingProfit(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingProfit", map);
	}

	@Override
	public void insertAccountingProfitRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingProfitRegion", map);
	}

	@Override
	public void insertAccountingAggregate(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingAggregate", map);
	}

	@Override
	public void insertAccountingAggregateRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingAggregateRegion", map);
	}

	@Override
	public void insertAccountingElsesubtotal(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingElsesubtotal", map);
	}

	@Override
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map) {
		this.insert(AccountingDataDao.class, "saveAccountingElsesubtotalRegion", map);
	}

	@Override
	public List<String> searchAccountingCode(Map<String, Object> map) {
		return this.query(AccountingDataDao.class, "searchAccountingCode", map, null);
	}

	@Override
	public void deleteIngredientItem(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteIngredientItem", map);
	}

	@Override
	public void deleteIngredient(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteIngredient", map);
	}

	@Override
	public void deleteAccounting(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccounting", map);
	}

	@Override
	public void deleteAccountingRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingRegion", map);
	}

	@Override
	public void deleteAccountingCostItem(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingCostItem", map);
	}

	@Override
	public void deleteAccountingNpackag(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingNpackag", map);
	}

	@Override
	public void deleteAccountingWpackag(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingWpackag", map);
	}

	@Override
	public void deleteAccountingWastage(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingWastage", map);
	}

	@Override
	public void deleteAccountingWec(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingWec", map);
	}

	@Override
	public void deleteAccountingSbzjwh(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingSbzjwh", map);
	}

	@Override
	public void deleteAccountingManpower(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingManpower", map);
	}

	@Override
	public void deleteAccountingManage(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingManage", map);
	}

	@Override
	public void deleteAccountingManageRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingManageRegion", map);
	}

	@Override
	public void deleteAccountingFreight(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingFreight", map);
	}

	@Override
	public void deleteAccountingFreightRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingFreightRegion", map);
	}

	@Override
	public void deleteAccountingTax(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingTax", map);
	}

	@Override
	public void deleteAccountingTaxRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingTaxRegion", map);
	}

	@Override
	public void deleteAccountingProfit(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingProfit", map);
	}

	@Override
	public void deleteAccountingProfitRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingProfitRegion", map);
	}

	@Override
	public void deleteAccountingAggregate(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingAggregate", map);
	}

	@Override
	public void deleteAccountingAggregateRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingAggregateRegion", map);
	}

	@Override
	public void deleteAccountingElsesubtotal(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingElsesubtotal", map);
	}

	@Override
	public void deleteAccountingElsesubtotalRegion(Map<String, Object> map) {
		this.delete(AccountingDataDao.class, "deleteAccountingElsesubtotalRegion", map);
	}
}