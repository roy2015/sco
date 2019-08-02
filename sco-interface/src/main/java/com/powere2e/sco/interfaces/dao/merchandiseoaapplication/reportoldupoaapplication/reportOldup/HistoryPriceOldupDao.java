package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportOldup;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.CheckStandardOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;

/**
 * 历史与本次价格(老品新上)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public interface HistoryPriceOldupDao extends Dao {
	/**
	 * 历史与本次价格(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(老品新上)列表
	 */
	public List<HistoryPriceOldup> listHistoryPriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 本次价格比历史价格高(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(老品新上)列表
	 */
	public List<HistoryPriceOldup> listComparePriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加历史与本次价格(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertHistoryPriceOldup(Map<String, Object> map) throws Exception;

	/**
	 * 删除历史与本次价格(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的历史与本次价格(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteHistoryPriceOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除历史与本次价格(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的历史与本次价格(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteHistoryPriceOldupByCode(Map<String, Object> map) throws Exception;

	// 上下市模块
	/**
	 * 上下市时间(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回上下市时间(老品新上)列表
	 */
	public List<UpDownMarketOldup> listUpDownMarketOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加上下市时间(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertUpDownMarketOldup(Map<String, Object> map) throws Exception;

	/**
	 * 删除上下市时间(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的上下市时间(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteUpDownMarketOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除上下市时间(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的上下市时间(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteUpDownMarketOldupByCode(Map<String, Object> map) throws Exception;

	// 报告检验标准
	/**
	 * 报告检验标准(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回报告检验标准(老品新上)列表
	 */
	public List<CheckStandardOldup> listCheckStandardOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加报告检验标准(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertCheckStandardOldup(Map<String, Object> map) throws Exception;

	/**
	 * 删除报告检验标准(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的报告检验标准(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCheckStandardOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除报告检验标准(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的报告检验标准(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCheckStandardOldupByCode(Map<String, Object> map) throws Exception;

	// 同类商品市场零售价
	/**
	 * 同类商品市场零售价(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(老品新上)列表
	 */
	public List<SameMerchandiseOldup> listSameMerchandiseOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertSameMerchandiseOldup(Map<String, Object> map) throws Exception;

	/**
	 * 删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseOldup(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseOldupByCode(Map<String, Object> map) throws Exception;

	// 商品销售预计
	/**
	 * 同类商品市场零售价(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(老品新上)列表
	 */
	public List<AnticipatedSellOld> listAnticipatedSellOld(Map<String, Object> map, PageInfo pageInfo) throws Exception;

	/**
	 * 添加同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 * 
	 */
	public void insertAnticipatedSellOld(Map<String, Object> map) throws Exception;

	/**
	 * 删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAnticipatedSellOld(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除同类商品市场零售价(老品新上)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(老品新上)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAnticipatedSellOldByCode(Map<String, Object> map) throws Exception;

	/**
	 * 根据申请单号删除关联分析报表(商品OA)(新品引进)
	 */
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception;

	/**
	 * 添加商品原料情况(老品新上)
	 * 
	 * @param map
	 *            商品原料实例
	 * 
	 */
	public void insertMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 删除商品原料情况(老品新上)
	 * 
	 * @param map
	 */
	public void deleteMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 商品原料情况(老品新上)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(调价)列表
	 */
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 根据申请单号删除商品原料情况(老品新上)
	 */
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map);

}