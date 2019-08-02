package com.powere2e.sco.interfaces.service.merchandisecostanalysis.accountingingredient.accounting;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.commons.service.Service;
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
 * 商品核算表Service接口
 * 
 * @author matt.sun
 * @version 1.0
 * @since 2015年3月16日
 */
public interface AccountingService extends Service {
	/**
	 * 商品成本分析查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品成本分析列表
	 */
	public List<AccountingBo> listAccounting(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据ID号加载一个商品成本分析
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
	 * 添加商品核算表
	 *
	 * @param map
	 *
	 */
	public void insertAccounting(Map<String, Object> map);

	/**
	 * 添加核算/投料表扫描版
	 * 
	 * @param map
	 */
	public void insertAccountingIngredientScan(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品成本分析
	 *
	 * @param map
	 *            必须参数id为要删除的商品成本分析id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAccounting(String accountingCode);

	/**
	 * 删除核算/投料表扫描版
	 * 
	 * @param map
	 */
	public void deleteAccountingIngredientScan(Map<String, Object> map);

	/**
	 * 修改商品成本分析
	 *
	 * @param map
	 */
	public void updateAccounting(Map<String, Object> map);
	
	/**
	 * 根据OA物料信息修改商品核算表正式商品信息
	 * 
	 * @param applicationCode 申请单号
	 * @param merchandiseCode 申请单意向品编号
	 * @param supplierCode 申请单供应商编号
	 * @param wlMerchandiseCode 申请单物料信息-商品编号
	 * @param wlSupplierCode 申请单物料信息-供应商编号
	 */
	public void updateAccountingOfOaWl(String applicationCode, String merchandiseCode, String supplierCode, String wlMerchandiseCode, String wlSupplierCode);

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