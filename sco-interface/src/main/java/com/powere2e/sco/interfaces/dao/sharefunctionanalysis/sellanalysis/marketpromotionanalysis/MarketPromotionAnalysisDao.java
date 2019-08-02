package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysis;
import com.powere2e.security.model.Option;

/**
 * 销售明细DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MarketPromotionAnalysisDao extends Dao {
	/**
	 * 大盘促销分析明细档期汇总查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 大盘促销分析明细日销售查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetails(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 大盘促销分析查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return 返回销售明细列表
	 */
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysis(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询区域信息
	 * 
	 * @param map
	 * @return
	 */
	public List<Option> listRegion(Map<String, Object> map);
}