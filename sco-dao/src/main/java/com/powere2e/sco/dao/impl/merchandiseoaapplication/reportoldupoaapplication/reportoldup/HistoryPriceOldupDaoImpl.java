package com.powere2e.sco.dao.impl.merchandiseoaapplication.reportoldupoaapplication.reportoldup;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.merchandiseoaapplication.reportoldupoaapplication.reportOldup.HistoryPriceOldupDao;
import com.powere2e.sco.model.merchandiseoaapplication.reportadjustpriceoaapplication.reportadjustprice.MerchandiseMaterial;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.AnticipatedSellOld;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.CheckStandardOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.HistoryPriceOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.SameMerchandiseOldup;
import com.powere2e.sco.model.merchandiseoaapplication.reportoldupoaapplication.reportoldup.UpDownMarketOldup;

/**
 * 历史与本次价格(老品新上)DAO接口的实现
 * 
 * @author Joyce.Li
 * @version 1.0
 * @since 2015年5月5日
 */
public class HistoryPriceOldupDaoImpl extends DaoImpl implements HistoryPriceOldupDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2172859185485017317L;

	// 历史与本次价格(老品新上)查询
	@Override
	public List<HistoryPriceOldup> listHistoryPriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "searchHistoryPriceOldup", map, pageInfo);
	}

	// 本次价格比历史价格高(老品新上)查询
	@Override
	public List<HistoryPriceOldup> listComparePriceOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "searchComparePriceOldup", map, pageInfo);
	}

	// 添加历史与本次价格(老品新上)
	@Override
	public void insertHistoryPriceOldup(Map<String, Object> map) throws Exception {
		this.insert(HistoryPriceOldupDao.class, "saveHistoryPriceOldup", map);
	}

	// 删除历史与本次价格(老品新上)
	@Override
	public void deleteHistoryPriceOldup(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteHistoryPriceOldup", map);
	}

	// 根据申请单号删除历史与本次价格(老品新上)
	@Override
	public void deleteHistoryPriceOldupByCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteHistoryPriceOldupByCode", map);
	}

	// 上下市模块
	// 查询上下市时间(老品新上)查询
	@Override
	public List<UpDownMarketOldup> listUpDownMarketOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "listUpDownMarketOldup", map, pageInfo);
	}

	// 添加上下市时间(老品新上)
	@Override
	public void insertUpDownMarketOldup(Map<String, Object> map) throws Exception {
		this.insert(HistoryPriceOldupDao.class, "saveUpDownMarketOldup", map);
	}

	// 删除上下市时间(老品新上)
	@Override
	public void deleteUpDownMarketOldup(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteUpDownMarketOldup", map);
	}

	// 根据申请单号删除上下市时间(老品新上)
	@Override
	public void deleteUpDownMarketOldupByCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteUpDownMarketOldupByCode", map);
	}

	// 报告检验标准
	// 报告检验标准(老品新上)查询
	@Override
	public List<CheckStandardOldup> listCheckStandardOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "searchCheckStandardOldup", map, pageInfo);
	}

	// 添加报告检验标准(老品新上)
	@Override
	public void insertCheckStandardOldup(Map<String, Object> map) throws Exception {
		this.insert(HistoryPriceOldupDao.class, "saveCheckStandardOldup", map);
	}

	// 删除报告检验标准(老品新上)
	@Override
	public void deleteCheckStandardOldup(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteCheckStandardOldup", map);
	}

	// 根据申请单号删除报告检验标准(老品新上)
	@Override
	public void deleteCheckStandardOldupByCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteCheckStandardOldupByCode", map);
	}

	// 同类商品市场零售价
	// 同类商品市场零售价(老品新上)查询
	@Override
	public List<SameMerchandiseOldup> listSameMerchandiseOldup(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "searchSameMerchandiseOldup", map, pageInfo);
	}

	// 添加同类商品市场零售价(老品新上)
	@Override
	public void insertSameMerchandiseOldup(Map<String, Object> map) throws Exception {
		this.insert(HistoryPriceOldupDao.class, "saveSameMerchandiseOldup", map);
	}

	// 删除同类商品市场零售价(老品新上)
	@Override
	public void deleteSameMerchandiseOldup(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteSameMerchandiseOldup", map);
	}

	// 根据申请单号删除同类商品市场零售价(老品新上)
	@Override
	public void deleteSameMerchandiseOldupByCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteSameMerchandiseOldupByCode", map);
	}

	// 商品销售预计
	// 同类商品市场零售价(老品新上)查询
	@Override
	public List<AnticipatedSellOld> listAnticipatedSellOld(Map<String, Object> map, PageInfo pageInfo) throws Exception {
		return this.query(HistoryPriceOldupDao.class, "searchAnticipatedSellOld", map, pageInfo);
	}

	// 添加同类商品市场零售价(老品新上)
	@Override
	public void insertAnticipatedSellOld(Map<String, Object> map) throws Exception {
		this.insert(HistoryPriceOldupDao.class, "saveAnticipatedSellOld", map);
	}

	// 删除同类商品市场零售价(老品新上)
	@Override
	public void deleteAnticipatedSellOld(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteAnticipatedSellOld", map);
	}

	// 根据申请单号删除同类商品市场零售价(老品新上)
	@Override
	public void deleteAnticipatedSellOldByCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteAnticipatedSellOldByCode", map);
	}

	//根据申请单号删除关联分析报表(商品OA)(新品引进)
	@Override
	public void deleteAnalysisReportsMByApplicationCode(Map<String, Object> map) throws Exception {
		this.delete(HistoryPriceOldupDao.class, "deleteAnalysisReportsMByApplicationCode", map);		
	}

	//添加商品原料情况(老品新上)
	@Override
	public void insertMerchandiseMaterial(Map<String, Object> map) {
		this.insert(HistoryPriceOldupDao.class, "saveMerchandiseMaterial", map);
	}

	//删除商品原料情况(老品新上)
	@Override
	public void deleteMerchandiseMaterial(Map<String, Object> map) {
		this.delete(HistoryPriceOldupDao.class, "deleteMerchandiseMaterial", map);
	}

	//商品原料情况(老品新上)查询
	@Override
	public List<MerchandiseMaterial> listMerchandiseMaterial(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(HistoryPriceOldupDao.class, "searchMerchandiseMaterial", map, pageInfo);
	}

	//根据申请单号删除商品原料情况(老品新上)
	@Override
	public void deleteMerchandiseMaterialByCode(Map<String, Object> map) {
		this.delete(HistoryPriceOldupDao.class, "deleteMerchandiseMaterialByCode", map);
	}
}