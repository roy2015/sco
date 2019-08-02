package com.powere2e.sco.dao.impl.sharefunctionanalysis.sellanalysis.marketpromotionanalysis;

import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.dao.DaoImpl;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.sco.interfaces.dao.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysisDao;
import com.powere2e.sco.model.sharefunctionanalysis.sellanalysis.marketpromotionanalysis.MarketPromotionAnalysis;
import com.powere2e.security.model.Option;

/**
 * 销售明细DAO接口的实现
 * 
 * @author xiejiajie.w
 * @version 1.0
 * @since 2015年5月8日
 */
public class MarketPromotionAnalysisDaoImpl extends DaoImpl implements MarketPromotionAnalysisDao {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7211178178055872194L;

	// 查询
	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysis(Map<String, Object> map, PageInfo pageInfo) {
		return this.query(MarketPromotionAnalysisDao.class, "searchMarketPromotionAnalysis", map, pageInfo);
	}

	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetail(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MarketPromotionAnalysisDao.class, "searchMarketPromotionAnalysisDetail", map, pageInfo);
	}

	@Override
	public List<MarketPromotionAnalysis> listMarketPromotionAnalysisDetails(Map<String, Object> map, PageInfo pageInfo) {
		// TODO Auto-generated method stub
		return this.query(MarketPromotionAnalysisDao.class, "searchMarketPromotionAnalysisDetails", map, pageInfo);
	}

	/**
	 * 查看库存地点名称
	 */
	@Override
	public List<Option> listRegion(Map<String, Object> map) {
		return this.query(MarketPromotionAnalysisDao.class, "searchRegionName", map, null);
	}
}