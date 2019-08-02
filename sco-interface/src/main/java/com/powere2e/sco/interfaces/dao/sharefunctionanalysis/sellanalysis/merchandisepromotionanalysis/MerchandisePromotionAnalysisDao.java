package com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.Dao;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.merchandisepromotionanalysis.MerchandisePromotionAnalysis;

/**
 * 商品促销分析DAO接口
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public interface MerchandisePromotionAnalysisDao extends Dao {
	/**
	 * 商品促销分析档期查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 促销分析查询
	 *
	 * @param map
	 *            查询参数
	 * @param pageInfo
	 *            分页参数
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysis(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 查询商品
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandise(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandiseInfo(Map<String, Object> map, PageInfo pageInfo);

	/**
	 * 促销分析明细
	 * 
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public List<MerchandisePromotionAnalysis> listMerchandisePromotionAnalysisDetailInfo(Map<String, Object> map, PageInfo pageInfo);
}