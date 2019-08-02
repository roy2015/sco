package com.powere2e.sco.interfaces.dao.merchandisecostanalysis.accountingingredient.accounting;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
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
 * 商品核算表DAO接口
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public interface AccountingDao extends Dao {
	/**
	 * 商品核算表查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品成本分析列表
	 */
	public List<AccountingBo> listAccounting(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据核算表编号加载一个核算总表
	 *
	 * @param map
	 *
	 * @return
	 */
	public Accounting loadAccounting(Map<String, Object> map);

	/**
	 * 根据商品CODE加载一个商品核算表情况
	 *
	 * @param map
	 *
	 * @return
	 */
	public AccountingBo loadAccountingBo(Map<String, Object> map);

	/**
	 * 根据核算表编号查询该核算表OA申请状态
	 *
	 * @param map
	 *
	 * @return
	 */
	public AccountingBo getAccountingOaStatus(Map<String, Object> map);

	/**
	 * 修改核算总表
	 *
	 * @param map
	 *            必须参数id为要修改商品成本分析的id号，不能为数组
	 */
	public void updateAccounting(Map<String, Object> map);

	/**
	 * 根据OA物料信息修改商品核算表正式商品信息
	 * 
	 * @param map
	 */
	public void updateAccountingOfOaWl(Map<String, Object> map);

	/**
	 * 修改商品核算/投料表扫描版
	 *
	 * @param map
	 *            必须参数id为要修改商品成本分析的id号，不能为数组
	 */
	public void updateAccountingIngredientScan(Map<String, Object> map);

	/**
	 * 根据商品编号查询核算表生成的核算编号
	 * 
	 * @param map
	 * @return
	 */
	public String searchAccountingCodeByMerchandiseCode(Map<String, Object> map);

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
	public void insertAccountingNPackag(Map<String, Object> map);

	/**
	 * 增加核算表外包装
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingWPackag(Map<String, Object> map);

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
	 * 增加核算表管理各地区
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
	 * 增加核算表商品出厂价格
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingFactoryPrice(Map<String, Object> map);

	/**
	 * 增加核算表汇率
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingExchangerate(Map<String, Object> map);

	/**
	 * 增加核算表海运费
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingOceanfreight(Map<String, Object> map);

	/**
	 * 增加核算表报关服务费
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingCustomscharges(Map<String, Object> map);

	/**
	 * 增加核算表关税
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingCustomsduties(Map<String, Object> map);

	/**
	 * 增加核算表增值税
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingAddedvaluetax(Map<String, Object> map);

	/**
	 * 增加核算表税差
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingTaxDiffer(Map<String, Object> map);

	/**
	 * 增加核算表利息
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingInterest(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 增加核算表其他成本小计各地区
	 * 
	 * @param map
	 *            map数据
	 */
	public void insertAccountingElsesubtotalRegion(Map<String, Object> map);

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
	 * 增加核算/投料表扫描版
	 * 
	 * @param map
	 */
	public void insertAccountingIngredientScan(Map<String, Object> map);

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
	public void deleteAccountingNPackag(Map<String, Object> map);

	/**
	 * 删除核算表外包装
	 */
	public void deleteAccountingWPackag(Map<String, Object> map);

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
	 * 删除核算表管理各地区
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
	 * 删除核算表商品出厂价格
	 */
	public void deleteAccountingFactoryPrice(Map<String, Object> map);

	/**
	 * 删除核算表汇率
	 */
	public void deleteAccountingExchangerate(Map<String, Object> map);

	/**
	 * 删除核算表海运费
	 */
	public void deleteAccountingOceanfreight(Map<String, Object> map);

	/**
	 * 删除核算表报关服务费
	 */
	public void deleteAccountingCustomscharges(Map<String, Object> map);

	/**
	 * 删除核算表关税
	 */
	public void deleteAccountingCustomsduties(Map<String, Object> map);

	/**
	 * 删除核算表增值税
	 */
	public void deleteAccountingAddedvaluetax(Map<String, Object> map);

	/**
	 * 删除核算表税差
	 */
	public void deleteAccountingTaxDiffer(Map<String, Object> map);

	/**
	 * 删除核算表利息
	 */
	public void deleteAccountingInterest(Map<String, Object> map);

	/**
	 * 删除核算表其他成本小计
	 */
	public void deleteAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 删除核算表其他成本小计各地区
	 */
	public void deleteAccountingElsesubtotalRegion(Map<String, Object> map);

	/**
	 * 删除核算表总价
	 */
	public void deleteAccountingAggregate(Map<String, Object> map);

	/**
	 * 删除核算表总价各地区
	 */
	public void deleteAccountingAggregateRegion(Map<String, Object> map);

	/**
	 * 删除核算/投料表扫描版
	 * 
	 * @param map
	 */
	public void deleteAccountingIngredientScan(Map<String, Object> map);

	/**
	 * 查询核算表地区表
	 */
	public List<AccountingRegion> loadAccountingRegion(Map<String, Object> map);

	/**
	 * 查询核算表成本项
	 */
	public AccountingCostItem loadAccountingCostItem(Map<String, Object> map);

	/**
	 * 查询核算表内包装
	 */
	public List<AccountingNPackag> loadAccountingNPackag(Map<String, Object> map);

	/**
	 * 查询核算表外包装
	 */
	public List<AccountingWPackag> loadAccountingWPackag(Map<String, Object> map);

	/**
	 * 查询核算表损耗类型
	 */
	public List<AccountingWastage> loadAccountingWastage(Map<String, Object> map);

	/**
	 * 查询核算表水电煤
	 */
	public AccountingWec loadAccountingWec(Map<String, Object> map);

	/**
	 * 查询核算表设备折旧及维护
	 */
	public AccountingSbzjwh loadAccountingSbzjwh(Map<String, Object> map);

	/**
	 * 查询核算表人工
	 */
	public AccountingManpower loadAccountingManpower(Map<String, Object> map);

	/**
	 * 查询核算表管理
	 */
	public AccountingManage loadAccountingManage(Map<String, Object> map);

	/**
	 * 查询核算表管理各地区
	 */
	public List<AccountingManageRegion> loadAccountingManageRegion(Map<String, Object> map);

	/**
	 * 查询核算表运输备注
	 */
	public AccountingFreight loadAccountingFreight(Map<String, Object> map);

	/**
	 * 查询核算表运输各地区
	 */
	public List<AccountingFreightRegion> loadAccountingFreightRegion(Map<String, Object> map);

	/**
	 * 查询核算表税收
	 */
	public AccountingTax loadAccountingTax(Map<String, Object> map);

	/**
	 * 查询核算表税收各地区
	 */
	public List<AccountingTaxRegion> loadAccountingTaxRegion(Map<String, Object> map);

	/**
	 * 查询核算表利润
	 */
	public AccountingProfit loadAccountingProfit(Map<String, Object> map);

	/**
	 * 查询核算表税收各地区
	 */
	public List<AccountingProfitRegion> loadAccountingProfitRegion(Map<String, Object> map);

	/**
	 * 查询进口核算表商品出厂价格
	 */
	public AccountingFactoryPrice loadAccountingFactoryPrice(Map<String, Object> map);

	/**
	 * 查询进口核算表汇率
	 */
	public AccountingExchangerate loadAccountingExchangerate(Map<String, Object> map);

	/**
	 * 查询进口核算表海运费
	 */
	public AccountingOceanfreight loadAccountingOceanfreight(Map<String, Object> map);

	/**
	 * 查询进口核算表报关服务费
	 */
	public AccountingCustomscharges loadAccountingCustomscharges(Map<String, Object> map);

	/**
	 * 查询进口核算表关税
	 */
	public AccountingCustomsduties loadAccountingCustomsduties(Map<String, Object> map);

	/**
	 * 查询进口核算表增值税
	 */
	public AccountingAddedvaluetax loadAccountingAddedvaluetax(Map<String, Object> map);

	/**
	 * 查询进口核算表税差
	 */
	public AccountingTaxDiffer loadAccountingTaxDiffer(Map<String, Object> map);

	/**
	 * 查询进口核算表利息
	 */
	public AccountingInterest loadAccountingInterest(Map<String, Object> map);

	/**
	 * 查询核算表其他成本小计
	 */
	public AccountingElsesubtotal loadAccountingElsesubtotal(Map<String, Object> map);

	/**
	 * 查询核算表其他成本小计各地区
	 */
	public List<AccountingElsesubtotalRegion> loadAccountingElsesubtotalRegion(Map<String, Object> map);

	/**
	 * 查询核算表总价
	 */
	public AccountingAggregate loadAccountingAggregate(Map<String, Object> map);

	/**
	 * 查询核算表总价各地区
	 */
	public List<AccountingAggregateRegion> loadAccountingAggregateRegion(Map<String, Object> map);

	/**
	 * 查询核算/投料表扫描版
	 */
	public AccountingIngredientScan loadAccountingIngredientScan(Map<String, Object> map);
}