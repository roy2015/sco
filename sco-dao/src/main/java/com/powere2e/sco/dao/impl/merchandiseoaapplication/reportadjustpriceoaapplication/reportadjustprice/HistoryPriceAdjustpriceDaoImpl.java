package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustpriceDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpItemAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.AtpTotalAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.CheckStandardAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.HistoryPriceAdjustprice;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.SameMerchandiseAdjustprice;

/**
 * 历史与本次价格(调价)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月7日
 */
public class HistoryPriceAdjustpriceDaoImpl extends DaoImpl implements HistoryPriceAdjustpriceDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4442130434192607832L;

	/**
	 * 历史与本次价格(调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	public List<HistoryPriceAdjustprice> listHistoryPriceAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchHistoryPriceAdjustprice", map, pageInfo);
	}

	/**
	 * 本次价格比历史价格高(正常调价)查询
	 * 
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回历史与本次价格(调价)列表
	 */
	@Override
	public List<HistoryPriceAdjustprice> listComparePriceAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchComparePriceAdjustprice", map, pageInfo);
	}

	/**
	 * 添加历史与本次价格(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertHistoryPriceAdjustprice(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveHistoryPriceAdjustprice", map);
	}

	/**
	 * 删除历史与本次价格(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的历史与本次价格(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteHistoryPriceAdjustprice(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteHistoryPriceAdjustprice", map);
	}

	// 根据申请单号删除历史与本次价格(调价)
	@Override
	public void deleteHistoryPriceAdjustpriceByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteHistoryPriceAdjustpriceByCode", map);
	}

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
	public List<CheckStandardAdjustprice> listCheckStandardAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchCheckStandardAdjustprice", map, pageInfo);
	}

	/**
	 * 添加报告检验标准(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertCheckStandardAdjustprice(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveCheckStandardAdjustprice", map);
	}

	/**
	 * 删除报告检验标准(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的报告检验标准(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteCheckStandardAdjustprice(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteCheckStandardAdjustprice", map);
	}

	// 根据申请单号删除报告检验标准(调价)
	@Override
	public void deleteCheckStandardAdjustpriceByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteCheckStandardAdjustpriceByCode", map);
	}

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
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchMerchandiseMaterial", map, pageInfo);
	}

	/**
	 * 添加商品原料情况(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertMerchandiseMaterial(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveMerchandiseMaterial", map);
	}

	/**
	 * 删除商品原料情况(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品原料情况(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteMerchandiseMaterial(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteMerchandiseMaterial", map);
	}

	// 根据申请单号删除商品原料情况(调价)
	@Override
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteMerchandiseMaterialByCode", map);
	}

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
	public List<AtpTotalAdjustprice> listAtpTotalAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchAtpTotalAdjustprice", map, pageInfo);
	}

	/**
	 * 添加商品签量情况-总
	 * 
	 * @param map
	 * 
	 */
	public void insertAtpTotalAdjustprice(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveAtpTotalAdjustprice", map);
	}

	/**
	 * 删除商品签量情况-总
	 * 
	 * @param map
	 *            必须参数id为要删除的商品签量情况-总id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAtpTotalAdjustprice(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteAtpTotalAdjustprice", map);
	}

	// 根据申请单号删除商品签量情况-总
	@Override
	public void deleteAtpTotalAdjustpriceByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteAtpTotalAdjustpriceByCode", map);
	}

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
	public List<AtpItemAdjustprice> listAtpItemAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchAtpItemAdjustprice", map, pageInfo);
	}

	/**
	 * 添加商品签量情况-明细(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertAtpItemAdjustprice(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveAtpItemAdjustprice", map);
	}

	/**
	 * 删除商品签量情况-明细(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的商品签量情况-明细(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteAtpItemAdjustprice(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteAtpItemAdjustprice", map);
	}

	// 根据申请单号 删除商品签量情况-明细(调价)
	@Override
	public void deleteAtpItemAdjustpriceByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteAtpItemAdjustpriceByCode", map);
	}

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
	public List<SameMerchandiseAdjustprice> listSameMerchandiseAdjustprice(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceAdjustpriceDao.class, "searchSameMerchandiseAdjustprice", map, pageInfo);
	}

	/**
	 * 添加同类商品市场零售价(调价)
	 * 
	 * @param map
	 * 
	 */
	public void insertSameMerchandiseAdjustprice(Map<String, Object> map) {
		this.insert(HistoryPriceAdjustpriceDao.class, "saveSameMerchandiseAdjustprice", map);
	}

	/**
	 * 删除同类商品市场零售价(调价)
	 * 
	 * @param map
	 *            必须参数id为要删除的同类商品市场零售价(调价)id号，可以为多个id,用逗号隔开，如'1','2'
	 */
	public void deleteSameMerchandiseAdjustprice(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteSameMerchandiseAdjustprice", map);
	}

	// 根据申请单号删除同类商品市场零售价(调价)
	@Override
	public void deleteSameMerchandiseAdjustpriceByCode(Map<String, Object> map) {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteSameMerchandiseAdjustpriceByCode", map);
	}

	// 根据申请单号删除关联分析报表(商品OA)(新品引进)
	@Override
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceAdjustpriceDao.class, "deleteAnalysisReportsMByApplicationCode", map);
	}

}