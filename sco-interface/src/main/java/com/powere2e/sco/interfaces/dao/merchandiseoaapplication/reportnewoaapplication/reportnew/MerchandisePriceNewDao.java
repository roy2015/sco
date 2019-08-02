package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportnewoaapplication.reportnew;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.MerchandisePriceNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SameMerchandiseNew;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.reportnew.SellAnticipatedNew;

/**
 * 商品价格(新品引进)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年4月27日
 */
public interface MerchandisePriceNewDao extends Dao {
	/**
	 * 商品价格(新品引进)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品价格(新品引进)列表
	 */
	public List<MerchandisePriceNew> listMerchandisePriceNew(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个商品价格(新品引进)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public MerchandisePriceNew loadMerchandisePriceNew(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品价格(新品引进)
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandisePriceNew(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品价格(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品价格(新品引进)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandisePriceNew(Map<String, Object> map) throws Exception;

	/**
	 * 修改商品价格(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要修改商品价格(新品引进)的id号，不能为数组
	 */
	public void updateMerchandisePriceNew(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请编号删除商品价格(新品引进)
	 */
	public void deletePriceNewByApplicationCode(Map<String, Object> map) throws Exception;

	// 同类商品模块
	/**
	 * 同类商品市场零售价(新品引进)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(新品引进)列表
	 */
	public List<SameMerchandiseNew> listSameMerchandiseNew(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个同类商品市场零售价(新品引进)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public SameMerchandiseNew loadSameMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 添加同类商品市场零售价(新品引进)
	 * 
	 * @param map
	 * 
	 */
	public void insertSameMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 删除同类商品市场零售价(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(新品引进)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 修改同类商品市场零售价(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要修改同类商品市场零售价(新品引进)的id号，不能为数组
	 */
	public void updateSameMerchandiseNew(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请编号删除同类商品(新品引进)
	 */
	public void deleteSameNewByApplicationCode(Map<String, Object> map) throws Exception;

	// 销售预计模块
	/**
	 * 商品销售预计(新品引进)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品销售预计(新品引进)列表
	 */
	public List<SellAnticipatedNew> listSellAnticipatedNew(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 根据ID号加载一个商品销售预计(新品引进)
	 * 
	 * @param map
	 * 
	 * @return
	 */
	public SellAnticipatedNew loadSellAnticipatedNew(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品销售预计(新品引进)
	 * 
	 * @param map
	 * 
	 */
	public void insertSellAnticipatedNew(Map<String, Object> map) throws Exception;

	/**
	 * 删除商品销售预计(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品销售预计(新品引进)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSellAnticipatedNew(Map<String, Object> map) throws Exception;

	/**
	 * 修改商品销售预计(新品引进)
	 * 
	 * @param map
	 *            必须参数id为要修改商品销售预计(新品引进)的id号，不能为数组
	 */
	public void updateSellAnticipatedNew(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请编号删除销售预计(新品引进)
	 */
	public void deleteSellNewByApplicationCode(Map<String, Object> map) throws Exception;
	
	/**
	 * 根据申请单号删除关联分析报表(商品OA)(新品引进)
	 */
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 删除报告检验标准(新品引进)
	 */
	public void deleteCheckStandardNew(Map<String, Object> map);

	/**
	 * 添加报告检验标准(新品引进)
	 */
	public void insertCheckStandardNew(Map<String, Object> map);

	/**
	 * 商品原料(新品引进)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品原料
	 */
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo);
	
	/**
	 * 添加商品原料情况(新品引进)
	 * 
	 * @param map
	 *            商品原料实例
	 */
	public void insertMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 删除商品原料情况(新品引进)
	 * 
	 * @param map
	 */
	public void deleteMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 根据申请单号删除商品原料情况(新品引进)
	 */
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map);

}