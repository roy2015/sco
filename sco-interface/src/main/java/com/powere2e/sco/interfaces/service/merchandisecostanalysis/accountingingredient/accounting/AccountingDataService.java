package com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.accounting;

import java.io.File;
import java.util.Map;

import com.powere2e.frame.commons.service.Service;

/**
 * 核算数据Service接口
 * 
 * @author lipengjie
 * @version 1.0
 * @since 2015年4月17日
 */
public interface AccountingDataService extends Service {

	/**
	 * 
	 * 上传核算数据
	 * 
	 * @param file
	 * @return
	 */
	public String completeImportAccountingData(File file);

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
	 * 增加核算表总价地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingAggregateRegion(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map);

	/**
	 * 删除所有数据存在的数据
	 * 
	 */
	public void deleteAllAccounting(Map<String, Object> map);

}