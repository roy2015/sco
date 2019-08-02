package com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpItemAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpTotalAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.CheckStandardAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
/**
 * 历史与本次价格(调价)DAO接口
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;

/**
 * 历史与本次价格(调价)DAO接口
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public interface HistoryPriceAdjustpriceDao extends Dao {
	/**
	 * 历史与本次价格(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	public List<HistoryPriceAdjustprice> listHistoryPriceAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 本次价格比历史价格高(正常调价)
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	public List<HistoryPriceAdjustprice> listComparePriceAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加历史与本次价格(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertHistoryPriceAdjustprice(Map<String, Object> map);

	/**
	 * 删除历史与本次价格(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的历史与本次价格(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteHistoryPriceAdjustprice(Map<String, Object> map);

	/**
	 * 根据申请单号删除历史与本次价格(调价)
	 * 
	 */
	public void deleteHistoryPriceAdjustpriceByCode(Map<String, Object> map);

	// 检验标注模块
	/**
	 * 报告检验标准(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回报告检验标准(调价)列表
	 */
	public List<CheckStandardAdjustprice> listCheckStandardAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加报告检验标准(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertCheckStandardAdjustprice(Map<String, Object> map);

	/**
	 * 删除报告检验标准(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的报告检验标准(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCheckStandardAdjustprice(Map<String, Object> map);

	/**
	 * 根据申请单号 删除报告检验标准(调价)
	 */
	public void deleteCheckStandardAdjustpriceByCode(Map<String, Object> map);

	// 商品原料模块
	/**
	 * 商品原料情况(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品原料情况(调价)列表
	 */
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品原料情况(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 删除商品原料情况(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品原料情况(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseMaterial(Map<String, Object> map);

	/**
	 * 根据申请单号删除商品原料情况(调价)
	 */
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map);

	// 商品签量情况总查询模块
	/**
	 * 商品签量情况-总查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品签量情况-总列表
	 */
	public List<AtpTotalAdjustprice> listAtpTotalAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品签量情况-总
	 * 
	 * @param map
	 * 
	 */
	public void insertAtpTotalAdjustprice(Map<String, Object> map);

	/**
	 * 删除商品签量情况-总
	 * 
	 * @param map
	 *            必须参数id为要删除的商品签量情况-总id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAtpTotalAdjustprice(Map<String, Object> map);

	/**
	 * 根据申请单号删除商品签量情况-总
	 */
	public void deleteAtpTotalAdjustpriceByCode(Map<String, Object> map);

	// 商品签量明细模块
	/**
	 * 商品签量情况-明细(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回商品签量情况-明细(调价)列表
	 */
	public List<AtpItemAdjustprice> listAtpItemAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加商品签量情况-明细(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertAtpItemAdjustprice(Map<String, Object> map);

	/**
	 * 删除商品签量情况-明细(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品签量情况-明细(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAtpItemAdjustprice(Map<String, Object> map);

	/**
	 * 根据申请单号 删除商品签量情况-明细(调价)
	 */
	public void deleteAtpItemAdjustpriceByCode(Map<String, Object> map);

	// 同类商品市场零售价模块
	/**
	 * 同类商品市场零售价(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回同类商品市场零售价(调价)列表
	 */
	public List<SameMerchandiseAdjustprice> listSameMerchandiseAdjustprice(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 添加同类商品市场零售价(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertSameMerchandiseAdjustprice(Map<String, Object> map);

	/**
	 * 删除同类商品市场零售价(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseAdjustprice(Map<String, Object> map);

	/**
	 * 根据申请单号删除同类商品市场零售价(调价)
	 */
	public void deleteSameMerchandiseAdjustpriceByCode(Map<String, Object> map);
	
	/**
	 * 根据申请单号删除关联分析报表(商品OA)(新品引进)
	 */
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception;
}