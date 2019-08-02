package com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;

/**
 * 核算数据DAO接口
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月17日
 */
public interface AccountingDataDao extends Dao {

	/**
	 * 增加投料明细
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertIngredientItem(Map<String, Object> map);

	/**
	 * 增加投料总表
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertIngredient(Map<String, Object> map);

	/**
	 * 增加核算总表
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccounting(Map<String, Object> map);

	/**
	 * 增加核算表地区表
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingRegion(Map<String, Object> map);

	/**
	 * 增加核算表成本项
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingCostItem(Map<String, Object> map);

	/**
	 * 增加核算表内包装
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingNpackag(Map<String, Object> map);

	/**
	 * 增加核算表外包装
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingWpackag(Map<String, Object> map);

	/**
	 * 增加核算表损耗类型
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingWastage(Map<String, Object> map);

	/**
	 * 增加核算表水电煤
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingWec(Map<String, Object> map);

	/**
	 * 增加核算表设备折旧及维护
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingSbzjwh(Map<String, Object> map);

	/**
	 * 增加核算表人工
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingManpower(Map<String, Object> map);

	/**
	 * 增加核算表管理
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingManage(Map<String, Object> map);

	/**
	 * 增加核算表管理地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingManageRegion(Map<String, Object> map);

	/**
	 * 增加核算表运输备注
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingFreight(Map<String, Object> map);

	/**
	 * 增加核算表运输各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingFreightRegion(Map<String, Object> map);

	/**
	 * 增加核算表税收
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingTax(Map<String, Object> map);

	/**
	 * 增加核算表税收各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingTaxRegion(Map<String, Object> map);

	/**
	 * 增加核算表利润
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingProfit(Map<String, Object> map);

	/**
	 * 增加核算表税收各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingProfitRegion(Map<String, Object> map);

	/**
	 * 增加核算表总价
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingAggregate(Map<String, Object> map);

	/**
	 * 增加核算表总价各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingAggregateRegion(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计备注
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计备注各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map);

	/**
	 * 根据商品编号查询核算表生成的核算编号
	 * 
	 * @param map
	 * @return
	 */
	public List<String> searchAccountingCode(Map<String, Object> map);

	/**
	 * 删除投料明细
	 */
	public void deleteIngredientItem(Map<String, Object> map);

	/**
	 * 删除投料总表
	 */
	public void deleteIngredient(Map<String, Object> map);

	/**
	 * 删除核算总表
	 */
	public void deleteAccounting(Map<String, Object> map);

	/**
	 * 删除核算表地区表
	 */
	public void deleteAccountingRegion(Map<String, Object> map);

	/**
	 * 删除核算表成本项
	 */
	public void deleteAccountingCostItem(Map<String, Object> map);

	/**
	 * 删除核算表内包装
	 */
	public void deleteAccountingNpackag(Map<String, Object> map);

	/**
	 * 删除核算表外包装
	 */
	public void deleteAccountingWpackag(Map<String, Object> map);

	/**
	 * 删除核算表损耗类型
	 */
	public void deleteAccountingWastage(Map<String, Object> map);

	/**
	 * 删除核算表水电煤
	 */
	public void deleteAccountingWec(Map<String, Object> map);

	/**
	 * 删除核算表设备折旧及维护
	 */
	public void deleteAccountingSbzjwh(Map<String, Object> map);

	/**
	 * 删除核算表人工
	 */
	public void deleteAccountingManpower(Map<String, Object> map);

	/**
	 * 删除核算表管理
	 */
	public void deleteAccountingManage(Map<String, Object> map);

	/**
	 * 删除核算表管理地区
	 */
	public void deleteAccountingManageRegion(Map<String, Object> map);

	/**
	 * 删除核算表运输备注
	 */
	public void deleteAccountingFreight(Map<String, Object> map);

	/**
	 * 删除核算表运输各地区
	 */
	public void deleteAccountingFreightRegion(Map<String, Object> map);

	/**
	 * 删除核算表税收
	 */
	public void deleteAccountingTax(Map<String, Object> map);

	/**
	 * 删除核算表税收各地区
	 */
	public void deleteAccountingTaxRegion(Map<String, Object> map);

	/**
	 * 删除核算表利润
	 */
	public void deleteAccountingProfit(Map<String, Object> map);

	/**
	 * 删除核算表税收各地区
	 */
	public void deleteAccountingProfitRegion(Map<String, Object> map);

	/**
	 * 删除核算表总价
	 */
	public void deleteAccountingAggregate(Map<String, Object> map);

	/**
	 * 删除核算表总价
	 */
	public void deleteAccountingAggregateRegion(Map<String, Object> map);

	/**
	 * 删除核算表其他成本小计备注
	 */
	public void deleteAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 删除核算表其他成本小计备注
	 */
	public void deleteAccountingElsesubtotalRegion(Map<String, Object> map);
}